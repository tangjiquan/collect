define("calcAdditionalRisk",["sm"],function(e) {
	return {
		modal: null,
		calcAdditionalRisk:function(premium){
			var e = premium;
			var risks = riskList;
			for(i=0;i<risks.length;i++){
			    var textAge=risks[i].TextAge;
					var appType=risks[i].appType;
					var sex=risks[i].sex;
					var feeYear=risks[i].feeYear;
			    if(textAge == e.TextAge && appType == e.appType && sex == e.sex && feeYear == e.feeYear){
						//console.log(risks[i].prem)
			        return risks[i].prem;
			    };
			};

		}
	}
}),
define(["module/customer","calcAdditionalRisk","config/json"],
function(b,c,d) {
	function o(b) {
        this._init()
  };
	return o.prototype = {
        modal: null,
        init: function(parameter) {

					document.title = data.headerTitle;
					//保障计划
					var productGuaranteeJuicer = document.getElementById('productGuaranteeJuicer').innerHTML;
					var productGuaranteeHtml = juicer(productGuaranteeJuicer, data);
					$("#productGuarantee").html(productGuaranteeHtml);

					//快速理赔
					var tpl = document.getElementById('tpl').innerHTML;
					var html = juicer(tpl, data);
					$("#container").html(html);

					//常见问题
					var cjwtJuicer = document.getElementById('cjwtJuicer').innerHTML;
					var cjwtHtml = juicer(cjwtJuicer, data);
					$("#cjwt").html(cjwtHtml);

					//表单内容
					var informationInfoJuicer = document.getElementById('informationInfoJuicer').innerHTML;
					var informationInfoHtml = juicer(informationInfoJuicer, data);
					$("#informationInfo").html(informationInfoHtml);

					//条款
					var clauseJuicer = document.getElementById('clauseJuicer').innerHTML;
					var clauseHtml = juicer(clauseJuicer, data);
					$("#clause").html(clauseHtml);

					//产品特色图片
					var productIdImgJuicer = document.getElementById('productIdImgJuicer').innerHTML;
					var productIdImgqHtml = juicer(productIdImgJuicer, data);
					$("#productIdImg").html(productIdImgqHtml);

					var btnNumboxHtml = document.getElementById('btn-numbox').innerHTML;
					$("#informationInfo").append(btnNumboxHtml)


					// 加减功能
					var numberAdd =function(e){
						var num_jia = document.getElementById("num-jia"),
						 		num_jian = document.getElementById("num-jian"),
								input_num = document.getElementById("input-num"),
								max = e.maxNumber == undefined ? 10 : e.maxNumber,
								min = e.minNumber == undefined ? (function(){ input_num.value = 0; return 0;})() : (function(){ input_num.value = e.minNumber; return e.minNumber; })();


						num_jia.onclick = function() {
								if(Number(input_num.value) < max){
									input_num.value = parseInt(input_num.value) + 1;

								};
								e.callBack && e.callBack(input_num.value);
						}
						num_jian.onclick = function() {

								if(Number(input_num.value) <= min) {
										input_num.value = min;
								} else {
										input_num.value = parseInt(input_num.value) - 1;
								}
								e.callBack && e.callBack(input_num.value);
						}
					};
					//通过选择的日历转换成年龄  格式为  12岁 ---12Y
					var getAgeByBirthday = function(str){
							var today = new Date();
							var month = today.getMonth() + 1;
							var day = today.getDate();
							var birthday_str = str.split('-')[0]+str.split('-')[1]+str.split('-')[2];
							var age = today.getFullYear() - birthday_str.substring(0, 4) - 1;
							if (birthday_str.substring(4, 6) < month || birthday_str.substring(4, 6) == month && birthday_str.substring(6, 8) <= day) {
								age++;
							}
							return age+"Y";
					};

					//封装json数据    通过数据调用calcAdditionalRisk方法查找对应的费率表   获取保额
					var calculationInfo =function(p){
							var occupationId = $("#occupation select").val();
							var insuredAmount=0.00;
							var j = {
									TextAge:getAgeByBirthday($("#date").val()),//年龄
									appType:$("#paymentMethod select").val(),//交费方式
									feeYear:$("#payDate select").val(),//交费年限
									sex:$("#sex .active").attr("data-type") //性别
							};
							if(Number(j.TextAge.split("Y")[0]) > 60){
								$.alert('投保人年龄在0-60岁之间');
								return insuredAmount;
							}
							insuredAmount = c.calcAdditionalRisk(j);

							//console.log("insuredAmount"+insuredAmount);
							return insuredAmount;
							// console.log({
							// 	TextAge:getAgeByBirthday($("#date").val()),//年龄
							// 	appType:Number($("#paymentMethod select").val()),//交费方式
							// 	feeYear:$("#payDate select").val(),//交费年限
							// 	sex:$("#sex .active").attr("data-type") //性别
							// });
					};
					var input_num = $("#input-num");
					var insuredAmountId = $("#insuredAmount");

					var toNumber = function(){
						var num = Number(input_num.val()) * Number(calculationInfo());
						return num.toFixed(2);
					};



					var orderAmountNumber = function(){
						var orderAmount=$("#orderAmount"),
								a = 1;
						var payDataValue = $("#payDate .item-select");
						if(payDataValue.val() =="1Y"){
								//交50份
								a =50;
						}else if(payDataValue.val() =="3Y"){
							a =15;
							//交15份
						}else if(payDataValue.val() =="5Y"){
							a =10;
							//交10份
						}else if(payDataValue.val() =="10Y"){
							a =5;
							//交5份
						}
						orderAmount.html(1000*a+".00");
						$("#insuredAmount").val(toNumber());
						input_num.val(a);
						numberAdd({
									maxNumber:20000000,
									minNumber:a,
									callBack:function(money){
											//orderAmountNumber();
											orderAmount.html(1000*money+".00");
											insuredAmountId.val(toNumber());
									}
						});
					}

					//个人信息录入
					~function(){
						//标签选择默认效果
						$(".item-gender-button").click(function(){
							$(this).addClass("active").siblings().removeClass("active");
						});
						//保障计划  显示隐藏
						$(".item-toggle").click(function(){
					  	$(this).next().toggle();
					  });
						//解决键盘位置兼容
						$("input,select").on("blur change",function(){
						   document.body.scrollTop = document.body.scrollHeight;
						});
						$(".item-button").eq(0).addClass("active");
						var myDate = apputils.getCurrentDate(1,28);

						$("#date").val(myDate);
						$("#date").datePicker({
							 maxDate:myDate,
							 dateDays: true,
							 value:myDate
						});
						orderAmountNumber();


						insuredAmountId.val(toNumber());

					}();
					$("input,select").on("blur change",function(){
						orderAmountNumber();
						insuredAmountId.val(toNumber());
					});
					$(".item-button").on("click",function(){
						insuredAmountId.val(toNumber());
					});


					var yylistData = b.tetail_ajaxInfo();//调用初始化接口
					console.log(yylistData)

					//预约详情
					var yuxqJuicer = document.getElementById('yuxqJuicer').innerHTML;
					var yuxqHtml = juicer(yuxqJuicer, yylistData.result);
					$("#yuxq").html(yuxqHtml);

					$("#buy").click(function(){
					    var totalAmnt = $("#insuredAmount").val();
					    var total = $("#orderAmount").html().replace('元', '');
					    sessionStorage.setItem("total",total);
					    sessionStorage.setItem("amnt",totalAmnt);
							sessionStorage.setItem("appointProductCode",data.productId);
					    location.href = "/content/appointmentwritenew.html";
					});
					(function(){
						//调用分享
						var oldvalue = apputils.getUrlParameter("shareIdOld");
						var code = apputils.getUrlParameter("code");//从微信端得到的code
						var context = apputils.getServer();
						if(oldvalue == "" || oldvalue == null || oldvalue == undefined){//登录完进来后 从sessionStorage中获取
							//oldvalue = sessionStorage.getItem("shareIdOld");
							oldvalue="";
						}
						var pic = "";

						var openId = sessionStorage.getItem("openId");//用户的openId
						var currUrl = window.location.href;//当前页面的url
						currUrl=currUrl.replace(/\&/g,"%26");
						var productId = data.productId;
						sessionStorage.setItem(productId+"appointProductUrl", currUrl);
						var productName="东吴卓越金生年金保险（分红型）";
						var detail = productName;//产品描述
						var openIdF = "";
						var productType = "01"//产品类型
						sessionStorage.setItem("shareIdOld", oldvalue);

						var jsondata={"openId":openId,"productName":productName,"productId":productId,"pic":pic,"url":currUrl,"detail":detail,"openIdF":openIdF,"productType":productType,"code":code, "shareIdOld":oldvalue};
						apputils.shareRequest2(jsondata,code,productId,"/h5/modules/insure/detail.html",productName,"卓越不凡，耀燃金生","img/wx144.jpg",openId,productId,"","","",function(){});
					})();
				}
    }
});
