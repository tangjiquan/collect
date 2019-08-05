define("then", ["sm"],function(){
	return function(fun){
		return{
			then :function(){
				console.log("then");
				return {
					fail:function(){
						console.log("fail");
					}
				}
			},
			result:fun()
		}
	}
}),
define("util/ajax-then", ["zepto","then"],
function(e,t) {
    function n(e) {
        this.params = e,
        this._init()
    }
    return n.prototype = {
        _init: function() {
            var t = this;
        },
        call: function() {
            var n = this;
            return t(function(t) {
							var result;
							e.ajax({
								async: false,
								type: n.params.type,
								cache: false,
								url: n.params.url,
								//data: "jsonstr=" + JSON.stringify(n.params.data),
								data: "jsonstr="+JSON.stringify(n.params.data),
								dataType: "json",
								jsonp:  "jsonpCallback",
								success: function(data) {
									result=data;
								},
								error: function(data) {
									//系统繁忙，请稍后再试！
									n.params.callBack();
								}
							});
							return result;
      		});
			/*.then(function(t, n) {
                void 0,
                0 === n.success ? t(null, n.content) : 1 === n.success && (e.hideMainPageLoader(), 40002 === n.code ? e.alertReloadMessage("由于您长时间未操作本次登录失效，请点击重新登录") : 5001 === n.code ? e.alertMessage("本次操作请求超时，请重新操作") : e.alertMessage("请求系统出现异常，请重新打开", null, 36e5))
            }).fail(function(t, n) {
                e.hideMainPageLoader(),
                e.alertMessage("请求系统出现异常，请重新打开", null, 36e5)
            })*/
        }
    },n
}),
define("module/customer", ["util/ajax-then"],
function(e) {
    //var n = $.moduleConfig.customer_server_url;
		var u = {
				// getServer:"http://wx.e-soochowlife.com",
				// getAppid:"wxda27ca9ad6afdfcd",
				// getContextPath:"http://wx.e-soochowlife.com/wx/w/html5/forward",
				// get3EPath:"http://wx.e-soochowlife.com/wx/w/thirdParty/threeE",
				//uat
				getServer:"http://h5test.e-soochowlife.com",
				getAppid:"wxd7977d3e28dec1a1",
				getContextPath:"http://h5test.e-soochowlife.com/wx/w/html5/forward",
				get3EPath:"http://h5test.e-soochowlife.com/wx/w/thirdParty/threeE",
		};
    return {
        tetail_ajaxInfo: function(t) {
						var listData = new e({
			                url: u.getServer+"/wx/w/wxOrderDetail/getListByProductId",
			                data: {"productId":data.productId},
			                type: "post",
								callBack:function(){
									alert("callBack");
								}
			       }).call();
             return listData;
        },
    }
});
