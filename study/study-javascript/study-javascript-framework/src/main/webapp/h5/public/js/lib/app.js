window.$fn = {//封装公用方法
	ajaxfun : function(url,data,method,ansy,callback){//公用的ajax
		 var closeLay=0;
		$.ajax({
				async:ansy,
				type:method,
				cache:false,
				url: url,
				data:"jsonstr="+JSON.stringify(data),
				dataType:"JSON",
				jsonp: "jsonpCallback",
				beforeSend: function() {//loading
	            	closeLay = layer.open({type: 2,content:"正在加载",shadeClose:false});
           		},
				success:function(result){
					layer.close(closeLay);
					callback(result);
				},
				error: function(data) {//系统繁忙，请稍后再试！
					layer.close(closeLay);
					layer.open({content:"系统繁忙，请稍后再试！",time:2});
            	},
			});
	},
	setOpenId : function(){ //获取微信openId  保存到sessionStorage

		var context=apputils.getContextPath();
		var code = apputils.getUrlParameter("code");
		var representOpendId = apputils.getUrlParameter("openid");
		var businessType;//业务类型
		var sign = apputils.getUrlParameter("sign");
		if(apputils.isNull(sign)){
			sign = sessionStorage.getItem("sign");
		}
		var openId;
		//sessionStorage.setItem("openId","o-SEN0mESKiET4esmpLLoOav2Vdk");
		openId = sessionStorage.getItem("openId");
		if(apputils.isNull(openId)&&(!apputils.isNull(code))){
			var code = apputils.getUrlParameter("code");
			var jsondata = {"head":{"state":"wxOpenId","action":"TranAction","method":"service"},
							"info":{"code":code}};
			$.ajax({
				async:false,
				type:"post",
				url: context,
				data:"jsonstr="+JSON.stringify(jsondata),
				dataType:"json",
				success:function(result){
					var jsonObj = eval("("+result+")");
					if(jsonObj.Flag.substring(0, 1) == "N"){
						layer.open({content:jsonObj.Flag.substring(2)});
					}
					openId = jsonObj.Data.openId;
					sessionStorage.setItem("openId",openId);
					var memberId = jsonObj.Data.memberId;
					if(memberId!=null){
						sessionStorage.setItem("memberId",memberId);
						sessionStorage.setItem("loginsuccess", "Y");
						var userName = jsonObj.Data.userName;
						sessionStorage.setItem("userName", userName==null?"东吴用户":userName);
						sessionStorage.setItem("mobilephonenum", jsonObj.Data.loginName);
					}
					$('#btn-login').attr('href',"/home.html");
				}
			})
		}
	},

};

//工具类
var apputils = {
	isNull:function(val){
		if(val==null||val==undefined){
			return true;
		}
		if(Object.prototype.toString.call(val) === "[object String]"){
			val=val.replace(/(^\s*)|(\s*$)/g, "");
		}

		if(val.length==0){
			return true;
		}
		if(val=="null"||val=="undefined"){
			return true;
		}
		return false;
	},
	getCurrentDate:function(format,dateMax) {
	  var now = new Date();
		!apputils.isNull(dateMax) ? now = new Date(now.getTime() - (dateMax*24)*60*60*1000):now = new Date(); //前一天
	  var year = now.getFullYear(); //得到年份
	  var month = now.getMonth();//得到月份
	  var date = now.getDate();//得到日期
	  var day = now.getDay();//得到周几
	  var hour = now.getHours();//得到小时
	  var minu = now.getMinutes();//得到分钟
	  var sec = now.getSeconds();//得到秒
	  month = month + 1;
	  if (month < 10) month = "0" + month;
	  if (date < 10) date = "0" + date;
	  if (hour < 10) hour = "0" + hour;
	  if (minu < 10) minu = "0" + minu;
	  if (sec < 10) sec = "0" + sec;
	  var time = "";
	  //精确到天
	  if(format==1){
		time = year + "-" + month + "-" + date;
	  }
	  //精确到分
	  else if(format==2){
		time = year + "-" + month + "-" + date+ " " + hour + ":" + minu + ":" + sec;
	  }
	  return time;
	},
	shareRequest2:function(jsondata,code,productId,address,title,desc,imgUrl,openId,shareType,dotaskNo,otherArgs,func){
			$.ajax({
				async:true,
				type:"post",
				url:apputils.getServer()+"/wx/w/wxShare/getSignature",
				data:"jsonstr="+JSON.stringify(jsondata),
				datatype:"json",
				success:function(result){
					var jsonResult=eval("("+result+")");
					var noncestr=jsonResult.noncestr;
					var timestamp=jsonResult.timestamp;
					var signature=jsonResult.signature;
					//openId = jsonResult.openId;
	// 			var url=jsonResult.url;
					var appId=jsonResult.appId;

					var shareFriendUrl = jsonResult.shareFriendUrl;
					wx.config({
						debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
						appId: appId, // 必填，公众号的唯一标识
						timestamp:timestamp, // 必填，生成签名的时间戳
						nonceStr: noncestr, // 必填，生成签名的随机串
						signature: signature,// 必填，签名，见附录1
						jsApiList: [
						'checkJsApi',
						'chooseImage',
						'onMenuShareQQ',
						'onMenuShareQZone',
						'previewImage',
						 'uploadImage',
						 'downloadImage',
						  'getNetworkType',//网络状态接口
						  'openLocation',//使用微信内置地图查看地理位置接口
						  'getLocation', //获取地理位置接口
						  'hideOptionMenu',//界面操作接口1
						  'showOptionMenu',//界面操作接口2
						  'closeWindow' ,  ////界面操作接口3
						  'hideMenuItems',////界面操作接口4
						  'showMenuItems',////界面操作接口5
						  'hideAllNonBaseMenuItem',////界面操作接口6
						  'showAllNonBaseMenuItem',////界面操作接口7
						'onMenuShareTimeline', 'onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
					});
					//获取埋点生成的  分享ID标识
					var m_shareoldid = sessionStorage.getItem("m_id");
					if(apputils.isNull(m_shareoldid)){
						m_shareoldid="";
					}
					//var urlandargs = apputils.getServer()+address+"?shareIdOld="+noncestr+otherArgs+"%26m_shareoldid%3D"+m_shareoldid;
					var urlandargs = apputils.getServer()+address+"?shareIdOld="+noncestr+otherArgs;
					var shareUrl = apputils.getServer() +"/content/sharecenter.html?data="+encodeURIComponent(urlandargs);
					var shareContent = shareUrl.replace(/\&/g,"%26");

					wx.error(function(res){
						//alert(res);
					});

					wx.ready(function(){
							wx.hideMenuItems({
								  menuList: [
									'menuItem:readMode', // 阅读模式
									//'menuItem:share:timeline', // 分享到朋友圈
									'menuItem:share:qq',//分享到qq
									'menuItem:share:QZone',//分享到qq空间
									'menuItem:setFont',//调整字体
									'menuItem:openWithSafari',//safari中打开
									'menuItem:copyUrl' // 复制链接
								  ],
							  success: function (res) {

							  },
							  fail: function (res) {

							  }
							});

						var dotaskFlag = sessionStorage.getItem("dotaskInfo");
						// 获取“分享给朋友”按钮点击状态及自定义分享内容接口
						wx.onMenuShareAppMessage({
							title:title, // 分享标题
							desc:desc, // 分享描述
							link:shareUrl,
							imgUrl: apputils.getServer() + imgUrl, // 分享图标
							openid:openId,
							type:'link', // 分享类型,music、video或link，不填默认为link
							success: function () {
								if(dotaskFlag==dotaskNo){
									var jsondata={"head":{"action":"doTask","from":"wechat"},"info":{"openid":openId,"taskNumber":dotaskFlag}};
									$.ajax({
										async: false,
										type:"post",
										url:apputils.getServer()+"/wx/w/task/doTask",
										data:"jsonstr="+JSON.stringify(jsondata),
										dataType:"json",
										success:function(result){
											console.log(result);
										},
										error:function(data){
											layer.open({
												content:"数据加载失败"
											});
										}
									});
								}

								 //保存分享人分享轨迹
								 var jsondataF = {"openId":openId,"appId":appId,"shareContent":shareContent,"shareType":shareType,"shareContentId":"","code":code, "shareId":noncestr,"productId":productId};
								 $.ajax({
									 async:false,
									 type:"post",
									 url:apputils.getServer()+"/wx/w/wxShare/shareSave",
									 data:"jsonstr="+JSON.stringify(jsondataF),
									 datatype:"json",
									 success:function(result){
									 },
									 error:function(){
									 }
								 });
								layer.open({
									content: '分享成功！'
									,skin: 'msg'
									,time: 2 //2秒后自动关闭
									,style:'bottom:50%'
								});
								if(typeof func === "function"){
					 				func();
					 		 	}
							},
							cancel: function () {
								// 用户取消分享后执行的回调函数
	//						layer.msg("取消分享",{time:2000});
								$fn.dataFlow({"triggertype":"shareON"});
								layer.open({
									content: '取消分享！'
									,skin: 'msg'
									,time: 2 //2秒后自动关闭
									,style:'bottom:50%'
								});
							}
						});

						wx.onMenuShareTimeline({
						    title:title, // 分享标题
						    link: shareUrl, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
						    imgUrl: apputils.getServer() +imgUrl, // 分享图标
						    success: function () {
						    	if(dotaskFlag==dotaskNo){
									var jsondata={"head":{"action":"doTask","from":"wechat"},"info":{"openid":openId,"taskNumber":dotaskFlag}};
									$.ajax({
										async: false,
										type:"post",
										url:apputils.getServer()+"/wx/w/task/doTask",
										data:"jsonstr="+JSON.stringify(jsondata),
										dataType:"json",
										success:function(result){
											console.log(result);
										},
										error:function(data){
											layer.open({content:"数据加载失败"});
										}
									});
								}

						    	//保存分享人分享轨迹
								var jsondataF = {"openId":openId,"appId":appId,"shareContent":shareContent,"shareType":shareType,"shareContentId":"","code":code, "shareId":noncestr,"productId":""};
								 $.ajax({
								 async:false,
								 type:"post",
								 url:apputils.getServer()+"/wx/w/wxShare/shareSave",
								 data:"jsonstr="+JSON.stringify(jsondataF),
								 datatype:"json",
								 success:function(result){
								 },
								 error:function(){
								 }
								 });
								layer.open({
									content: '分享成功！'
									,skin: 'msg'
									,time: 1 //2秒后自动关闭
									,style:'bottom:50%'
								});
								$fn.dataFlow({"triggertype":"shareOK"});
								if(typeof func === "function"){
					 				func();
					 		 	}
						    },
						    cancel: function () {
						    	// 用户取消分享后执行的回调函数
									$fn.dataFlow({"triggertype":"shareON"});
						    	layer.open({
									content: '取消分享！'
									,skin: 'msg'
									,time: 1 //2秒后自动关闭
									,style:'bottom:50%'
								});
						    }
						});
					});
				},
				error:function(){
				}
			});

		},

	getUrlParameter:function(paramKey) {
		var sURLVariables, i, sParameterName, sPageURL = window.location.search.substring(1);
		if (sPageURL) {
			sURLVariables = sPageURL.split("&");
			for (i = 0; i < sURLVariables.length; i++) {
				sParameterName = sURLVariables[i].split("=");
				if (sParameterName[0] === paramKey) return sParameterName[1]
			}
		}
	},
	getServer:function(){//IP地址
		return "http://h5test.e-soochowlife.com";
	},
	getAppid:function(){
		return "wxd7977d3e28dec1a1";//h5test
//			return "wx2df449e4110cb5ed";//wxdat
	},
	getContextPath:function(){
		return "http://h5test.e-soochowlife.com/wx/w/html5/forward";
//			return "http://wxdat.e-soochowlife.com/wx/w/html5/forward";
	},
	get3EPath:function(){
		return "http://h5test.e-soochowlife.com/wx/w/thirdParty/threeE";
	},
    //获取浏览器的客户端的IP地址
    getClientIP:function(){
        var RTCPeerConnection = window.RTCPeerConnection || window.webkitRTCPeerConnection || window.mozRTCPeerConnection;
        if (RTCPeerConnection) (function () {
            var rtc = new RTCPeerConnection({iceServers:[]});
            if (1 || window.mozRTCPeerConnection) {
                rtc.createDataChannel('', {reliable:false});
            };

            rtc.onicecandidate = function (evt) {
                if (evt.candidate) grepSDP("a="+evt.candidate.candidate);
            };
            rtc.createOffer(function (offerDesc) {
                grepSDP(offerDesc.sdp);
                rtc.setLocalDescription(offerDesc);
            }, function (e) { console.warn("offer failed", e); });


            var addrs = Object.create(null);
            addrs["0.0.0.0"] = false;
            function updateDisplay(newAddr) {
                if (newAddr in addrs) return;
                else addrs[newAddr] = true;
                var displayAddrs = Object.keys(addrs).filter(function (k) { return addrs[k]; });
                for(var i = 0; i < displayAddrs.length; i++){
                    if(displayAddrs[i].length > 16){
                        displayAddrs.splice(i, 1);
                        i--;
                    }
                }
                alert(displayAddrs[0]);
            }

            function grepSDP(sdp) {
                var hosts = [];
                sdp.split('\r\n').forEach(function (line, index, arr) {
                   if (~line.indexOf("a=candidate")) {
                        var parts = line.split(' '),
                            addr = parts[4],
                            type = parts[7];
                        if (type === 'host') updateDisplay(addr);
                    } else if (~line.indexOf("c=")) {
                        var parts = line.split(' '),
                            addr = parts[2];
                        updateDisplay(addr);
                    }
                });
            }
        })();
        else{
            alert("请使用主流浏览器：chrome,firefox,opera,safari");
        }
    }
};
window.apputils = apputils;
