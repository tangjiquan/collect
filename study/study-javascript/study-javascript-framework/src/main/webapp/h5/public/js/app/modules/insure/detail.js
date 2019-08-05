define("util/comp-msg", ["sm"],function(e) {
    function t(e) {
        this.init(e)
    };
    return t.prototype = {
        modal: null,
        init: function(t) {
            var n = this;
            t = t || {},
            t.title = "验证码",
            t.extraClass = "common-msg-modal",
            t.afterText = ['<div class="list-block" style="margin: 0.75rem 0 0 0;">', '<ul style="border: none;">', "<li>", '<div class="item-content h5-input-content veh-msg-input-content">', '<div class="item-inner h5-input-inner veh-msg-input-inner" style="border-bottom:0px solid #e7e7e7;">', '<div class="row no-gutter no-gutter-width-100" style="padding: 0 0.83rem">', '<div class="col-60">', '<div class="item-input" style="margin-top:0;">', '<input type="text" name="smsCode" id="smsCode" value="" maxlength="30" placeholder="请输入验证码" />', "</div>", "</div>", '<div class="col-40 veh-msg-send-content">', '<a class="button button-customer-h5 veh-verificode-send msg-active" id="sendCodeLogin">获取验证码</a>', "</div>", "</div>", "</div>", "</div>", "</li>", "</ul>", "</div>"].join(""),
            t.buttons = [{
                text: "取  消"
            },
            {
                text: "确  认",
                close: !1
            }],
            t.onClick = function(t, a) {
                var i = e(t).find('input[name="smsCode"]');
                0 === a && o && (i.val(""), o(i)),
                1 === a && l && (n.isSend ? l(i) : e.alertMessage("请先发送短信验证码"))
            };
            var a = "",
            i = "",
            o = t.callbackCancel,
            l = t.callbackOk,
            r = (t.onClickSend, document.createElement("div"));
            if (t.buttons && t.buttons.length > 0) for (var s = 0; s < t.buttons.length; s++) i += '<span class="modal-button' + (t.buttons[s].bold ? " modal-button-bold": "") + '">' + t.buttons[s].text + "</span>";
            var u = t.extraClass || "",
            c = t.title ? '<div class="modal-title">' + t.title + "</div>": "",
            d = t.text ? '<div class="modal-text">' + t.text + "</div>": "",
            g = t.afterText ? t.afterText: "",
            p = t.buttons && 0 !== t.buttons.length ? "": "modal-no-buttons",
            m = t.verticalButtons ? "modal-buttons-vertical modal-buttons-vertical-select": "";
            a = '<div class="modal ' + (t.closeable ? "modal-closeable ": "") + u + " " + p + '"><div class="modal-inner">' + (c + d + g) + '</div><div class="modal-buttons ' + m + '">' + i + "</div></div>",
            r.innerHTML = a;
            var h = e(r).children();
            return h.find(".modal-button").each(function(n, a) {
                e(a).on("click",
                function(a) { ! 1 !== t.buttons[n].close && e.closeModal(h),
                    t.buttons[n].onClick && t.buttons[n].onClick(h, a),
                    t.onClick && t.onClick(h, n)
                })
            }),
            h.find(".veh-verificode-send").on("click",
            function(a) {
                if (!n.isFreezedSend) {
                    n.isFreezedSend = !0;
                    var i = e(h).find(".veh-verificode-send");
                    t.onClickSend(i)
                }
            }),
            this.modal = h,
            h
        },
        open: function() {
            e(".page").append(this.modal),
            e.openModal(this.modal)
        },
        close: function() {
            e.closeModal(this.modal)
        },
        disableSendBtn: function(e) {
            var t = this;
            t.isFreezedSend = !0,
            t.waitSend(e)
        },
        enableSendBtn: function() {
            var e = this;
            e.modal.find(".veh-verificode-send").html("获取验证码"),
            e.isFreezedSend = !1
        },
        isSend: !1,
        isFreezedSend: !1,
        waitSend: function(e) {
            var t = this;
            e--,
            0 == e ? t.enableSendBtn() : (t.modal.find(".veh-verificode-send").html("重新获取(" + e + ")"), t.setTimeRegisterObj = setTimeout(function() {
                t.waitSend(e)
            },
            1e3))
        },
        resetComp: function() {
            var e = this;
            clearTimeout(e.setTimeRegisterObj),
            e.modal.find(".veh-verificode-send").html("获取验证码"),
            e.isSend = !1,
            e.isFreezedSend = !1,
            e.modal.find('input[name="smsCode"]').val(""),
            e.close()
        }
    },
    t
}),
define("util/comp-payment", ["sm"],
function(e) {
    function t(e) {
        this.init(e)
    }
    return t.prototype = {
        modal: null,
        init: function(t) {
            t = t || {};
            var n = "",
            a = "",
            i = document.createElement("div");
            if (t.buttons && t.buttons.length > 0) for (var o = 0; o < t.buttons.length; o++) a += '<span class="modal-button' + (t.buttons[o].bold ? " modal-button-bold": "") + '" key="' + t.buttons[o].key + '" index="' + o + '">&nbsp;&nbsp;&nbsp;' + t.buttons[o].value + "</span>";
            var l = t.extraClass || "",
            r = t.title ? '<div class="trade-title">' + t.title + t.close + "</div>": "",
            s = t.text ? '<div class="trade-text">' + t.text + "</div>": "",
            u = t.afterText ? t.afterText: "",
            c = t.buttons && 0 !== t.buttons.length ? "": "modal-no-buttons",
            d = t.verticalButtons ? "modal-buttons-vertical modal-buttons-vertical-select": "";
            n = '<div class="modal ebiz-modal ' + (t.closeable ? "modal-closeable ": "") + l + " " + c + '" style="width:100%;left:0;top:0;height:100%;margin-left:0;margin-top:0 !important;"><div class="modal-inner trade-box" style="top:10%;height:75%;">' + (r + s + u) + '</div><div class="modal-buttons ' + d + '">' + a + "</div></div>",
            i.innerHTML = n;
            var g = e(i).children();
            return g.find(".modal-button").each(function(n, a) {
                var i = e(this);
                e(a).on("click",
                function(a) { ! 1 !== t.buttons[n].close && e.closeModal(g),
                    t.onClick && t.onClick(i, n, g)
                })
            }),
            this.modal = g[0],
            g[0]
        },
        open: function() {
            e(".page").append(this.modal),
            e.openModal(this.modal)
        },
        close: function() {
            e.closeModal(this.modal)
        }
    },
    t
}),
define("util/comp-verificate", ["sm"],
function(e) {
    function t(e) {
        this.init(e)
    }
    return t.prototype = {
        _defaultConfig: {
            title: "请输入验证码",
            buttons: [{
                text: "取消"
            },
            {
                text: "确定",
                bold: !0
            }],
            extraClass: "common-verficate-modal"
        },
        _getImgHTMLText: function(e) {
            var t = "";
            for (var n in e) if (1 == e.length) {
                var a = '<img class="single" src="data:image/jpg;base64,' + e[n].value + '" />';
                t += a
            } else {
                var a = '<img class="double" src="data:image/jpg;base64,' + e[n].value + '" />';
                t += a
            }
            var i = '<div class="col-40"><input type="text" class="modal-text-input modal-verificate-code"></div><div class="col-60">' + t + "</div>";
            return 1 == e.length && (i = '<div class="col-60"><input type="text" class="modal-text-input modal-verificate-code"></div><div class="col-40">' + t + "</div>"),
            i
        },
        modal: null,
        currProviderCode: null,
        remark: null,
        img: null,
        init: function(t) {
            t = e.extend(this._defaultConfig, t);
            var n = "",
            a = "",
            i = document.createElement("div");
            if (t.buttons && t.buttons.length > 0) for (var o = 0; o < t.buttons.length; o++) a += '<span class="modal-button' + (t.buttons[o].bold ? " modal-button-bold": "") + '">' + t.buttons[o].text + "</span>";
            var l = t.extraClass || "",
            r = t.title ? '<div class="modal-title">' + t.title + "</div>": "",
            s = t.text ? '<div class="modal-text">' + t.text + "</div>": "";
            n = '<div class="modal ' + l + " " + (t.buttons && 0 !== t.buttons.length ? "": "modal-no-buttons") + '"><div class="modal-inner">' + (r + s) + '<div class="row no-gutter"></div></div><div class="modal-buttons ' + (t.verticalButtons ? "modal-buttons-vertical": "") + '">' + a + "</div></div>",
            i.innerHTML = n;
            var u = e(i).children();
            return t.onClick = function(n, a) {
                0 === a && t.callbackCancel && t.callbackCancel(e(n).find(".modal-verificate-code")),
                1 === a && t.callbackOk && t.callbackOk(e(n).find(".modal-verificate-code"))
            },
            u.find(".modal-button").each(function(n, a) {
                e(a).on("click",
                function(a) { ! 1 !== t.buttons[n].close && e.closeModal(u),
                    t.buttons[n].onClick && t.buttons[n].onClick(u, a),
                    t.onClick && t.onClick(u, n)
                })
            }),
            this.modal = u,
            u
        },
        open: function(t) {
            e(".page").append(this.modal),
            e.openModal(this.modal)
        },
        close: function() {
            e.closeModal(this.modal)
        },
        updateInfo: function(t) {
            t = e.extend(this._defaultConfig, t),
            this.img = t.img,
            this.currProviderCode = t.currProviderCode,
            this.remark = t.remark;
            var n = this.modal,
            a = this._getImgHTMLText(t.img);
            n.find(".row").get(0).firstChild && (n.find(".row").get(0).removeChild(n.find(".row").get(0).firstChild), n.find(".row").get(0).removeChild(n.find(".row").get(0).firstChild)),
            n.find(".row").get(0).appendChild(e(a).get(0)),
            n.find(".row").get(0).appendChild(e(a).get(1)),
            this.modal = n
        }
    },
    t
}),
define("app/base/base", ["require", "config", "sm", "util/page-router", "then", "juicer", "util/util", "md5", "util/validator", "util/comp-bank", "util/comp-city", "util/comp-pwd", "util/comp-select", "util/comp-msg", "util/comp-login", "util/comp-payment", "util/comp-verificate", "jweixin", "util/comp-wxconfig", "module/customer", "swiper", "util/page-util", "lazyload", "highcharts"],
function(e) {
    function t(e) {
        window.PageScopeSign = null,
        this.sign = e.sign,
        this.autoloader = e.autoloader,
        this.event = e.event,
        this.view = e.view
    }
    var n = (e("config"), e("sm")),
    a = (e("util/page-router"), e("then")),
    i = e("juicer"),
    o = e("util/util"),
    l = (e("md5"), e("util/validator")),
    r = e("util/comp-bank"),
    s = e("util/comp-city"),
    u = e("util/comp-pwd"),
    c = e("util/comp-select"),
    d = e("util/comp-msg"),
    g = e("util/comp-login"),
    p = e("util/comp-payment"),
    m = e("util/comp-verificate"),
    h = e("jweixin"),
    f = e("util/comp-wxconfig"),
    v = e("module/customer"),
    y = e("swiper"),
    b = e("util/page-util"),
    I = e("lazyload"),
    T = e("highcharts");
    return t.prototype = {
        init: function() {
            var e = n.fn.session_get("system_reload_source");
            if (e && "reload" == e) return n.fn.session_remove("system_reload_source"),
            void window.location.reload();
            n.init(),
            n.smConfig.checkAfter ? this.checkLogin() : n.router.isFirstInit() ? (this.checkLogin(), n.router.resetFirstInit(!1)) : (this.autoloader || n.hideMainPageLoader(), this.callbackInit())
        },
        checkLogin: function() {
            var e = this;
            n.filterConfig.checkLogin(window.location.href).then(function(t, a) {
                a && 0 == a.result ? (e.autoloader || n.hideMainPageLoader(), e.callbackInit()) : a && 2 == a.result && (1 == a.type ? o.goRedirectPage(a.href) : location.href = a.href)
            }).fail(function(e, t) {})
        },
        callbackInit: function() {
            n.scope = n,
            n.scope.juicer = i,
            n.scope.then = a,
            n.scope.util = o,
            n.scope.validator = l,
            n.scope.bankListModal = r,
            n.scope.citySelectModal = s,
            n.scope.payModal = u,
            n.scope.selectModal = c,
            n.scope.msgModal = d,
            n.scope.loginModal = g,
            n.scope.paymentModal = p,
            n.scope.verificateModal = m,
            n.scope.wx = h,
            n.scope.customer = v,
            n.scope.swiper = y,
            n.scope.lazyload = I,
            n.scope.pageUtil = b,
            n.scope.highcharts = T,
            this.scope = n.scope;
            var e = o.getSystemAndTypeParams(),
            t = n(".page.page-current header h1").text() || n(".page.page-current").attr("data-title");
            if (e && "app" === e.type) {
                var D = {},
                S = {};
                this.onAppInit(D);
                var P = n(".page.page-current header h1");
                if (P) {
                    var k = P.text();
                    k && (S.setTitle = {
                        title: k
                    }),
                    P.parent().remove()
                }
                var w = n(".page.page-current footer");
                if (w && w.remove(), D.title && (S.setTitle = {
                    title: D.title
                }), D.button && D.button.length > 0) {
                    var C = [];
                    n.each(D.button,
                    function(e, t) {
                        C.push({
                            jsFunction: "PageScope.onAppCallBack",
                            text: t.text,
                            imgUrl: t.imgUrl,
                            jsData: t.jsData
                        })
                    }),
                    C && C.length > 0 && (S.setButton = {
                        buttonArrary: C
                    })
                }
                D.refresh && (S.setFunction = {
                    jsFunction: "PageScope.onAppCallBack",
                    jsData: n.extend({
                        callBackKey: "refresh"
                    },
                    D.refresh)
                }),
                D.setClose && (S.setClose = D.setClose),
                D.setJsFinish && (S.setJsFinish = "PageScope.onAppDelayInit"),
                (S.setTitle || S.setButton || S.setFunction) && o.callAppFuction({
                    functionName: "naviInit",
                    dataObject: S
                })
            } else e && "wx" === e.type ? (o.setPageTitle(null == t ? "": t), this.view && this.view.wxTitle && !0 === this.view.wxTitle || n(".page.page-current header").remove(), this.sign && this.sign.auto && !0 === this.sign.auto && new f({
                apiList: this.sign.apiList,
                callBack: function(e) {
                    window.PageScopeSign = e
                }
            }).init()) : e && "alipay" === e.type ? (o.setPageTitle(null == t ? "": t), this.view && this.view.alipayTitle && !0 === this.view.alipayTitle || n(".page.page-current header").remove()) : e && "page" === e.type && (o.setPageTitle(null == t ? "": t), this.view && this.view.pageTitle && !0 === this.view.pageTitle || n(".page.page-current header").remove());
            this.onPageReady(n.scope)
        },
        onAppInit: function(e) {
            var t = this;
            this.event.onAppInit && this.event.onAppInit(t.scope, e)
        },
        onAppDelayInit: function(e) {
            var t = this;
            this.event.onAppDelayInit && this.event.onAppDelayInit(t.scope, e)
        },
        onAppCallBack: function(e) {
            var t = this;
            this.event.onAppCallBack && this.event.onAppCallBack(t.scope, e)
        },
        onPageReady: function(e) {
            this.event.onPageReady && this.event.onPageReady(e)
        }
    },
    t
}),
define("module/lxb", ["util/ajax-then", "util/util"],
function(e, t) {
    var n = $.moduleConfig.lxb_server_url;
    return {
        itemTypelist: function(t) {
            return new e({
                url: n + "/itemType/list",
                type: "POST",
                data: t
            }).call().then(function(e, t) {
                e(null, t)
            })
        },
        itemList: function(t) {
            return t.type = $.util.getSystemAndTypeParams().type,
            t.system = $.util.getSystemAndTypeParams().system,
            new e({
                url: n + "/item/list",
                type: "POST",
                data: t
            }).call().then(function(e, t) {
                e(null, t)
            })
        },
        itemGet: function(t) {
            return t.token = $.util.getToken(),
            new e({
                url: n + "/item/get",
                type: "POST",
                data: t
            }).call().then(function(e, t) {
                e(null, t)
            })
        },
        accountGet: function(t) {
            return new e({
                url: n + "/account/get",
                type: "POST",
                data: t
            }).call().then(function(e, t) {
                e(null, t)
            })
        },
        accountDetail: function(t) {
            return new e({
                url: n + "/user/get",
                type: "POST",
                data: t
            }).call().then(function(e, t) {
                e(null, t)
            })
        },
        orderList: function(t) {
            return new e({
                url: n + "/order/list",
                type: "POST",
                data: t
            }).call().then(function(e, t) {
                e(null, t)
            })
        },
        orderDetail: function(t) {
            return new e({
                url: n + "/order/get",
                type: "POST",
                data: t
            }).call().then(function(e, t) {
                e(null, t)
            })
        },
        getShareUrl: function(t) {
            return t.token = $.util.getToken(),
            new e({
                url: n + "/item/getShareUrl",
                type: "POST",
                data: t
            }).call().then(function(e, t) {
                e(null, t)
            })
        },
        getTotalBonusByCode: function(t) {
            return new e({
                url: n + "/common/query/getTotalBonusByCode",
                type: "POST",
                data: t
            }).call().then(function(e, t) {
                e(null, t)
            })
        },
        getTotalBonusByAcc: function(t) {
            return new e({
                url: n + "/common/query/getTotalBonusByAcc",
                type: "POST",
                data: t
            }).call().then(function(e, t) {
                e(null, t)
            })
        }
    }
}),
define("app/safeguard/insure/detail", ["require", "app/base/base", "module/hrp", "module/lxb", "util/comp-qrcode", "module/customer", "jweixin", "then", "sm-date-picker"],
function(e) {
    var t = e("app/base/base"),
    n = e("module/hrp"),
    a = e("module/lxb"),
    i = e("util/comp-qrcode"),
    o = e("module/customer"),
    l = e("jweixin"),
    r = e("then");
    e("sm-date-picker");
    var s = function(e, t) {
        t.title = "产品详情",
        t.button = [{
            text: "",
            imgUrl: "http://eservicetest.guohualife.com/app/resources/icon/share.png",
            jsData: {
                callQuote: "share"
            }
        }],
        e.util.setPageTitle("产品详情")
    },
    u = function(e, t) {
        t && t.jsData && "share" == t.jsData.callQuote && n.getProductDetail({
            itemCode: e.util.getRequestParams().itemCode
        }).then(function(t, n) {
            void 0;
            var a = {};
            a.functionName = "share",
            a.dataObject = {};
            var i = new Array;
            e.each(n.shareInfo,
            function(e, t) {
                i.push(t)
            }),
            a.dataObject.shareChannel = i,
            e.util.callAppFuction(a)
        })
    };
    return new t({
        autoloader: !0,
        event: {
            onPageReady: function(t) {
                function s(e) {
                    t.pageUtil.silentLogin().then(function(n, a) {
                        Q = a,
                        Q ? r.parallel([function(e) {
                            t.customer.accountInfo({}).then(function(t, n) {
                                Z = n,
                                n.idNoStar && "" != n.idNoStar && (ee = !0),
                                e(null)
                            })
                        },
                        function(e) {
                            t.customer.getPointsValue({}).then(function(t, n) {
                                V = n.availablePoint,
                                e(null)
                            })
                        }]).then(function(t) {
                            e(null)
                        }) : e(null)
                    })
                }
                function u(a) {
                    n.getProductDetail({
                        itemCode: t.util.getRequestParams().itemCode
                    }).then(function(n, i) {
                        if ("0" != i.result) t.util.goPage("result.html?resultType=noInfo&result=1&resultMessage=" + i.resultMessage);
                        else if ("0" == i.result) {
                            X = i,
                            t.util.setPageTitle(i.productName),
                            t.util.setSessionStorageObj("productDetailInfo", X),
                            0 == i.insureInfo.subjectMatterInfo.length && 0 == i.insureInfo.appntInfo.length && (te = !0),
                            "1" == i.insureInfo.insuredType ? ne = !0 : "2" == i.insureInfo.insuredType && "1" == i.insureInfo.relationToAppnt.length && "00" == i.insureInfo.relationToAppnt[0].value && (ne = !0),
                            _ozprm = "oztitle=" + i.productName + "&id=" + t.util.getRequestParams().itemCode; (new Date).getTime();
                            e(["o-code"])
                        }
                        a(null)
                    })
                }
                function c(e) {
                    var n = X.pointsInfo.rate,
                    a = X.pointsInfo.maxPrice,
                    i = !1;
                    if ("1" == X.pointsInfo.isPoint && ("" != n && null != n || "" != a && null != a) && (i = !0), ie.isPoint = i, i) if (t("#Point-li").show(), Q);
                    else {
                        var o = X.insureInfo.loginType,
                        l = t.util.getRequestParams().comid;
                        1 == O(l, o) ? t("#Point-li").hide() : (t("#transferStatus").prop("checked", !1).attr("disabled", "disabled"), t("#transfer").click(function() {
                            G("")
                        }))
                    } else t("#Point-li").hide();
                    e(null)
                }
                function d() {
                    var e = X.pointsInfo.minPoint,
                    n = X.pointsInfo.minPrice;
                    n = "" != n && null != n ? n: 0;
                    var a = X.pointsInfo.rate;
                    a = "" != a && null != a ? a: 1;
                    var i, o, l, r = X.pointsInfo.maxPrice,
                    s = oe.orderAmount;
                    if ("-1" == s) t("#transferStatus").prop("checked", !1).attr("disabled", "disabled"),
                    t("#pointDesc").html("保费满" + n + "元可用"),
                    t("#transfer").unbind("click"),
                    t("#transfer").click(function() {
                        t.alertMessage("保费满" + n + "元可用")
                    });
                    else if ("" != s) {
                        if (s < n) return void 0,
                        t("#transferStatus").prop("checked", !1).attr("disabled", "disabled"),
                        t("#pointDesc").html("保费满" + n + "元可用"),
                        t("#transfer").unbind("click"),
                        t("#transfer").click(function() {
                            t.alertMessage("保费满" + n + "元可用")
                        }),
                        void(ie.payMoney = oe.orderAmount);
                        var u = V > e ? V: e,
                        c = u / 100,
                        d = null;
                        if (d = "" != a && null != a ? parseFloat(a * s).toFixed(2) : s, i = V >= e ? "" == r || null == r ? c < d ? c: d: c - (d - r < 0 ? d: r) < 0 ? c: d - r < 0 ? d: r: "" == r || null == r ? d: d - r < 0 ? d: r, o = parseFloat(100 * i).toFixed(0), oe.exchangeIntegral = o, ie.exchangeMoney = i, V < e) t("#transferStatus").prop("checked", !1).attr("disabled", "disabled"),
                        t("#pointDesc").html("共" + V + "积分，满" + e + "积分可用"),
                        t("#transfer").unbind("click"),
                        t("#transfer").click(function() {
                            t.alertMessage("共" + V + "积分，满" + e + "积分可用")
                        }),
                        ie.payMoney = oe.orderAmount;
                        else {
                            t("#transfer").unbind("click"),
                            t("#transferStatus").removeAttr("disabled");
                            var g = V >= 1e5 ? V / 1e4 + "万": V;
                            if (t("#transfer").click(function() {
                                if (t("#transferStatus").prop("checked")) t("#orderAmount").html(parseFloat(oe.orderAmount).toFixed(2)),
                                ie.payMoney = oe.orderAmount,
                                t("#pointDesc").html("共" + g + "积分,可用" + parseFloat(o).toFixed(0) + "积分,抵" + parseFloat(i).toFixed(2) + "元"),
                                void 0;
                                else {
                                    var e = oe.orderAmount - ie.exchangeMoney; - 1 == oe.orderAmount ? t("#orderAmount").html("--") : t("#orderAmount").html(parseFloat(e).toFixed(2)),
                                    ie.payMoney = e,
                                    t("#pointDesc").html("共" + g + "积分,可用" + parseFloat(o).toFixed(0) + "积分,抵" + parseFloat(i).toFixed(2) + "元"),
                                    void 0
                                }
                            }), void 0, 0 == ie.isOpen) return t("#transferStatus").prop("checked", !1),
                            t("#pointDesc").html("共" + g + "积分,可用" + o + "积分,抵" + parseFloat(i).toFixed(2) + "元"),
                            void t("#orderAmount").html(parseFloat(s).toFixed(2));
                            t("#transferStatus").prop("checked", !0),
                            t("#pointDesc").html("共" + g + "积分,可用" + o + "积分,抵" + parseFloat(i).toFixed(2) + "元"),
                            l = s - i,
                            t("#orderAmount").html(parseFloat(l).toFixed(2)),
                            ie.payMoney = l,
                            void 0
                        }
                    }
                }
                function g(e) {
                    var n = t.juicer(t("#productBannerTempjuicer").html(), {
                        bannerObj: X
                    });
                    t(".banner").find("img.lazy").lazyload({
                        effect: "fadeIn",
                        vertical_only: !0,
                        threshold: 50
                    }),
                    t(".productBanner").html(n),
                    t(".bar-nav .title").text(X.productName),
                    e(null)
                }
                function p(e) {
                    var n = new RegExp("<em", "g"),
                    a = new RegExp("</em>", "g"),
                    i = new RegExp("&amp;", "g");
                    X.productAdvantageInfo.display = X.productAdvantageInfo.display.replace(n, "<i").replace(a, "</i>").replace(i, "&");
                    var o = t.juicer(t("#productAdvantageTempjuicer").html(), {
                        productAdvantage: X.productAdvantageInfo
                    });
                    t(".productAdvantage").html(o),
                    "" == X.safeguardDetailCode && t("#plansList .safeguardDetail").hide(),
                    t(".advantageDetail").click(function() {
                        t.util.goPage("contentDetail.html?itemCode=" + X.itemCode + "&instaceCode=HR_GUARANTEE_SALE&propertyCode=" + X.productAdvantageInfo.detailCode)
                    }),
                    e(null)
                }
                function m(e) {
                    void 0;
                    var n = t.juicer(t("#plansListTabTempjuicer").html(), X);
                    if (t("#plansListTab").html(n), X.safeguardPlansInfo.length <= 1) {
                        t("#plansListTab").hide();
                        var n = t.juicer(t("#plansListSimpleTempjuicer").html(), X)
                    } else var n = t.juicer(t("#plansListTempjuicer").html(), X);
                    4 == X.safeguardPlansInfo.length && (t(".tab-link b").css("font-size", "0.9rem"), t(".tab-link p").css("font-size", "0.6rem"), t(".planName").removeClass("font-14").addClass("font-12")),
                    t("#plansList").html(n),
                    t("#plansList .safeguardDetail").click(function() {
                        t.util.goPage("contentDetail.html?itemCode=" + X.itemCode + "&instaceCode=HR_GUARANTEE_SALE&propertyCode=" + X.safeguardDetailCode)
                    });
                    var a = X.safeguardPlansInfo[0].premUnit;
                    "1" == a ? (t("#premUnit").show(), t("#premUnit").html("/月")) : "2" == a ? (t("#premUnit").show(), t("#premUnit").html("/年")) : (t("#premUnit").hide(), t("#premUnit").html("")),
                    ie.premUnit = t("#premUnit").html(),
                    t(".tab-link").each(function(e, n) {
                        t(this).click(function() {
                            t(this).hasClass("active") || t(this).addClass("active").siblings().removeClass("active"),
                            t(t(this).attr("value")).css("display", "block").siblings("ul").css("display", "none"),
                            D(e),
                            L(function() {
                                w()
                            });
                            var n = X.safeguardPlansInfo[e].premUnit;
                            "1" == n ? (t("#premUnit").show(), t("#premUnit").html("/月")) : "2" == n ? (t("#premUnit").show(), t("#premUnit").html("/年")) : (t("#premUnit").hide(), t("#premUnit").html("")),
                            ie.premUnit = t("#premUnit").html()
                        })
                    }),
                    oe.productCode && t.each(t(".tab-link"),
                    function(e, n) {
                        t(n).data("planname") == oe.productName && (t(n).hasClass("active") || t(n).addClass("active").siblings().removeClass("active"), t(t(n).attr("value")).css("display", "block").siblings("ul").css("display", "none"), D(e), oe.cvalidDate && t("#cvalidate-picker").val(oe.cvalidDate), P(), a = X.safeguardPlansInfo[e].premUnit, "1" == a ? (t("#premUnit").show(), t("#premUnit").html("/月")) : "2" == a ? (t("#premUnit").show(), t("#premUnit").html("/年")) : (t("#premUnit").hide(), t("#premUnit").html("")), ie.premUnit = t("#premUnit").html())
                    }),
                    e(null)
                }
                function h(e) {
                    var n, o = new Array;
                    if (X.shareInfo && (t.each(X.shareInfo,
                    function(e, t) {
                        ae.comid && ae.comid == t.comid && (o.push(t), n = t.shareUrl)
                    }), o.length <= 0 && t.each(X.shareInfo,
                    function(e, t) {
                        t.comid || (o.push(t), n = t.shareUrl)
                    })), X.lxbInfo && X.lxbInfo.invitationCode) if (X.lxbInfo.bonusPrompt && (Q ? (t(".bonusPrompt").html(X.lxbInfo.bonusPrompt), t("#lxbShare").click(function() {
                        t(".detail-modal").show()
                    }), t(".detail-modal").click(function() {
                        t(this).hide()
                    })) : (t(".bonusPrompt").html("登录后" + X.lxbInfo.bonusPrompt), t("#lxbShare").click(function() {
                        if ("app" == t.util.getSystemType()) {
                            var e = "../../safeguard/insure/detail.html?";
                            t.each(t.util.getRequestParams(),
                            function(t, n) {
                                e = e + t + "=" + n + "&"
                            }),
                            t.util.setLocalStorageObj("redirectLogin", e),
                            t.util.goPage("../../activity/20170511/goLogin.html")
                        } else G("")
                    })), t(".safeguardProductDetail").css("margin-bottom", "5.5rem")), t("#lxbShare").show(), Q)"" != n && void 0 !== n || (n = window.location.href),
                    a.getShareUrl({
                        shareUrl: n,
                        lxbItemCode: X.lxbInfo.invitationCode,
                        itemCode: X.itemCode
                    }).then(function(a, l) {
                        if ("0" == l.result) {
                            if (l.data.shareUrl) {
                                n = l.data.shareUrl;
                                new i("qrcode").initQRCode(l.data.shareUrl, {
                                    width: 150,
                                    height: 150,
                                    codeIco: {
                                        src: t.pageFilterConfig.ctx_base + "/public/img/hrp/huarui_insurance-icon.jpg",
                                        width: .25,
                                        height: .25
                                    },
                                    correctLevel: QRCode.CorrectLevel.L
                                });
                                $(o)
                            }
                        } else {
                            var r = {};
                            r.title = "华瑞保险",
                            r.description = X.productName,
                            r.url = window.location.href,
                            r.image = "https://weixin.huaruisales.com/h5/public/img/huarui-logo.png",
                            o.push(r),
                            $(o)
                        }
                        e(null)
                    });
                    else {
                        if (o.length > 0) $(o);
                        else {
                            var l = {};
                            l.title = "华瑞保险",
                            l.description = X.productName,
                            l.url = window.location.href,
                            l.image = "https://weixin.huaruisales.com/h5/public/img/huarui-logo.png",
                            o.push(l),
                            $(o)
                        }
                        e(null)
                    } else {
                        if (o.length > 0) $(o);
                        else {
                            var l = {};
                            l.title = "华瑞保险",
                            l.description = X.productName,
                            l.url = window.location.href,
                            l.image = "https://weixin.huaruisales.com/h5/public/img/huarui-logo.png",
                            o.push(l),
                            $(o)
                        }
                        e(null)
                    }
                }
                function f(e) {
                    "" == X.product_reading_desc && t("#agreement").hide(),
                    t(".choose-clause").html(X.product_reading_desc),
                    t(".choose-clause").find("a").removeAttr("href"),
                    t(".showClause").click(function() {
                        var e = [];
                        t.each(X.product_clause,
                        function(n, a) {
                            var i = {};
                            i.text = a.clauseName,
                            i.onClick = function() {
                                t.util.goPage(a.clauseLink, {
                                    pageKey: "COMMON_PRIMARY_PAGE",
                                    location: !0
                                })
                            },
                            e.push(i)
                        });
                        var n = [{
                            text: "取消",
                            bg: "yellow"
                        }],
                        a = [e, n];
                        t.actions(a)
                    }),
                    t(".isSelected").click(function() {
                        t(this).toggleClass("active")
                    }),
                    e(null)
                }
                function v(e) {
                    t("#orderAmount").html(X.insureInfo.price),
                    "0" == X.insureInfo.isSale ? (t("#buy").css("background-color", "#ccc"), t("#buy").html("已售罄"), t("#buy").addClass("outSale")) : "1" == X.insureInfo.isSale ? w() : "2" == X.insureInfo.isSale && (t(".productPrice").hide(), t("#buy").removeClass("col-40").addClass("col-80 outSale").css({
                        "background-color": "#E8E9EA",
                        "font-size": ".8889rem",
                        color: "#E8AF6E"
                    }), X.insureInfo.stopSaleDesc ? t("#buy").html(X.insureInfo.stopSaleDesc) : t("#buy").html("审核中")),
                    e(null)
                }
                function y(e) {
                    if (X.insureInfo.insurePeriod && X.insureInfo.insurePeriod.length > 0) {
                        if (X.insureInfo.insurePeriod.length > 1) var n = t.juicer(t("#insurePeriodTempjuicer").html(), X);
                        else var n = t.juicer(t("#insurePeriodSimpleTempjuicer").html(), X);
                        t("#insurePeriod").html(n)
                    } else t("#insurePeriod-li").hide();
                    oe.insurePeriod && t(".insurePeriod").val(oe.insurePeriod),
                    t("#insurePeriod").change(function() {
                        void 0,
                        L()
                    }),
                    e(null)
                }
                function b(e) {
                    if (X.insureInfo.chargePeriod && X.insureInfo.chargePeriod.length > 0) if (X.insureInfo.chargePeriod.length > 1) {
                        var n = t.juicer(t("#chargePeriodTempjuicer").html(), X);
                        t("#chargePeriod").html(n)
                    } else {
                        var n = t.juicer(t("#chargePeriodSimpleTempjuicer").html(), X);
                        t("#chargePeriod").html(n)
                    } else t("#chargePeriod-li").hide();
                    oe.payInterval && t(".chargePeriod").val(oe.payInterval),
                    t("#chargePeriod").change(function() {
                        void 0,
                        L()
                    }),
                    e(null)
                }
                function I(e) {
                    if (! (X.insureInfo.chargeWay && X.insureInfo.chargeWay[0].text || X.insureInfo.chargeType && X.insureInfo.chargeType.label)) return t("#chargeType-li").hide(),
                    void e(null);
                    if (X.insureInfo.chargeWay && X.insureInfo.chargeWay[0].text) {
                        var n = t.juicer(t("#chargeTypeSimpleTempjuicer").html(), X);
                        if (t("#payStyleBox").html(n), oe.chargeWay) {
                            for (var a = 0,
                            i = 0; i < X.insureInfo.chargeWay.length; i++) oe.chargeWay == X.insureInfo.chargeWay[i].value && (a = X.insureInfo.chargeWay[i].value, void 0);
                            t("#payStyle").val(a),
                            void 0
                        }
                        t("#payStyle").change(function() {
                            void 0,
                            L()
                        })
                    } else null != X.insureInfo.chargeType && null != X.insureInfo.chargeType.label && t("#chargeType").val(X.insureInfo.chargeType.label);
                    e(null)
                }
                function T(e, n) {
                    D(n),
                    oe.cvalidDate && t("#cvalidate-picker").val(oe.cvalidDate),
                    e(null)
                }
                function D(e) {
                    if (X.safeguardPlansInfo.length > 1 && void 0 != X.safeguardPlansInfo[e].planCvalidateType && "" != X.safeguardPlansInfo[e].planCvalidateType) var n = X.safeguardPlansInfo[e].planCvalidateType;
                    else if (X.safeguardPlansInfo[e].planCvalidateType && "" != X.safeguardPlansInfo[e].planCvalidateType) var n = X.safeguardPlansInfo[e].planCvalidateType;
                    else var n = X.insureInfo.cvalidDateType;
                    if (ae.orderType && "beiyi" == ae.orderType) t("#cvalidate-li").hide(),
                    t("#cvalidate-picker").val(oe.cvalidDate);
                    else if ("1" == n) {
                        var a = new Date((new Date).getTime() + 864e5),
                        i = a.getMonth() < 9 ? "0" + (a.getMonth() + 1) : a.getMonth() + 1,
                        o = a.getDate() < 10 ? "0" + a.getDate() : a.getDate(),
                        l = a.getFullYear() + "-" + i + "-" + o,
                        r = t("#cvalidateDiv").html();
                        t("#cvalidate-picker").remove(),
                        t("#cvalidateDiv").html(r),
                        t("#cvalidate-picker").val(l)
                    } else if ("3" == n) {
                        var a = new Date((new Date).getTime()),
                        i = a.getMonth() < 9 ? "0" + (a.getMonth() + 1) : a.getMonth() + 1,
                        o = a.getDate() < 10 ? "0" + a.getDate() : a.getDate(),
                        l = a.getFullYear() + "-" + i + "-" + o,
                        r = t("#cvalidateDiv").html();
                        t("#cvalidate-picker").remove(),
                        t("#cvalidateDiv").html(r),
                        t("#cvalidate-picker").val(l)
                    } else { (new Date(X.insureInfo.cvalidDateRange.startDay).getTime() > new Date(t("#cvalidate-picker").val()).getTime() || new Date(t("#cvalidate-picker").val()).getTime() > new Date(X.insureInfo.cvalidDateRange.endDay).getTime()) && t("#cvalidate-picker").val("");
                        var r = t("#cvalidateDiv").html();
                        t("#cvalidate-picker").remove(),
                        t("#cvalidateDiv").html(r),
                        t("#cvalidate-picker").datePicker({
                            minDate: X.insureInfo.cvalidDateRange.startDay,
                            maxDate: X.insureInfo.cvalidDateRange.endDay,
                            change: function(e) {
                                t("#cvalidate-picker").val(e),
                                P(),
                                L()
                            }
                        })
                    }
                }
                function S(e) {
                    0 == X.insureInfo.insuredAgeRange.length && t("#birthday-li").hide(),
                    Q && ("1" == X.insureInfo.insuredType ? "" != Z.birthday && t("#birthday-picker").val(Z.birthday) : "2" == X.insureInfo.insuredType && t.each(X.insureInfo.relationToAppnt,
                    function(e, n) {
                        if ("00" == n.value) return "" != Z.birthday && t("#birthday-picker").val(Z.birthday),
                        !1
                    })),
                    ie.birthday && t("#birthday-picker").val(ie.birthday),
                    P(),
                    e(null)
                }
                function P() {
                    void 0;
                    var e = new Date((new Date).getTime()),
                    n = e.getMonth() < 9 ? "0" + (e.getMonth() + 1) : e.getMonth() + 1,
                    a = e.getDate() < 10 ? "0" + e.getDate() : e.getDate(),
                    i = e.getFullYear() + "-" + n + "-" + a,
                    o = "",
                    l = i;
                    if (X.insureInfo.insuredAgeRange.length > 0) {
                        X.insureInfo.insuredAgeRange[X.insureInfo.insuredAgeRange.length - 1].startDay && (o = X.insureInfo.insuredAgeRange[X.insureInfo.insuredAgeRange.length - 1].startDay),
                        X.insureInfo.insuredAgeRange[X.insureInfo.insuredAgeRange.length - 1].startDay && (l = X.insureInfo.insuredAgeRange[0].endDay);
                        var r = t("#cvalidate-picker").val(),
                        s = X.insureInfo.insuredAgePeriod.beginBirth,
                        u = X.insureInfo.insuredAgePeriod.endBirth,
                        c = X.insureInfo.insuredAgePeriod.beginBirthUnit,
                        d = X.insureInfo.insuredAgePeriod.endBirthUnit;
                        void 0 != r && "" != r && null != r || (r = new Date),
                        "1" == X.insureInfo.insuredAgeType && (r = new Date);
                        var g = new Date;
                        "D" == c ? g = new Date(r).setDate(new Date(r).getDate() - parseInt(s)) : "Y" == c && (g = new Date(r).setFullYear(new Date(r).getFullYear() - parseInt(s)));
                        var p = new Date;
                        "D" == d ? p = new Date(r).setDate(new Date(r).getDate() - parseInt(u)) : "Y" == d && (p = new Date(r).setFullYear(new Date(r).getFullYear() - parseInt(u) - parseInt("1")), p = new Date(p).setDate(new Date(p).getDate() + parseInt("1"))),
                        g = new Date(g),
                        p = new Date(p);
                        var m = g.getMonth() < 9 ? "0" + (g.getMonth() + 1) : g.getMonth() + 1,
                        h = g.getDate() < 10 ? "0" + g.getDate() : g.getDate();
                        l = g.getFullYear() + "-" + m + "-" + h;
                        var f = p.getMonth() < 9 ? "0" + (p.getMonth() + 1) : p.getMonth() + 1,
                        v = p.getDate() < 10 ? "0" + p.getDate() : p.getDate();
                        if (o = p.getFullYear() + "-" + f + "-" + v, void 0, void 0, ie.insuredBeginDate = o, ie.insuredEndDate = l, void 0, new Date(o).getTime() <= new Date(t("#birthday-picker").val()).getTime() && new Date(t("#birthday-picker").val()).getTime() <= new Date(l).getTime()) {
                            var y = t("#birthday-picker").val();
                            void 0
                        } else {
                            var b = new Date(l).getTime() - new Date(o).getTime(),
                            I = Math.floor(b / 864e5) / 2,
                            T = new Date(new Date(o).setDate(new Date(o).getDate() + I)),
                            n = T.getMonth() < 9 ? "0" + (T.getMonth() + 1) : T.getMonth() + 1,
                            a = T.getDate() < 10 ? "0" + T.getDate() : T.getDate(),
                            D = T.getFullYear() + "-" + n + "-" + a;
                            t("#birthday-picker").val(D);
                            var y = D;
                            void 0
                        }
                    } else if (void 0, void 0, new Date(t("#birthday-picker").val()).getTime() <= new Date(l).getTime()) {
                        void 0;
                        var y = t("#birthday-picker").val();
                        void 0
                    } else {
                        var T = new Date((new Date).setYear((new Date).getYear() - 30)),
                        n = T.getMonth() < 9 ? "0" + (T.getMonth() + 1) : T.getMonth() + 1,
                        a = T.getDate() < 10 ? "0" + T.getDate() : T.getDate(),
                        D = T.getFullYear() + "-" + n + "-" + a;
                        t("#birthday-picker").val(D);
                        var y = D;
                        void 0
                    }
                    var S = {};
                    if (void 0, void 0, void 0, S = "" != o ? {
                        minDate: o,
                        maxDate: l,
                        value: y,
                        change: function(e) {
                            void 0,
                            t("#birthday-picker").val(e),
                            L()
                        }
                    }: {
                        maxDate: l,
                        value: y,
                        change: function(e) {
                            void 0,
                            t("#birthday-picker").val(e),
                            L()
                        }
                    },
                    ne && null != Z && null != Z.birthday && "" != Z.birthday) t("#birthday-picker").val(Z.birthday);
                    else {
                        var P = t("#birthdayDiv").html();
                        t("#birthday-picker").remove(),
                        t("#birthdayDiv").html(P),
                        t("#birthday-picker").datePicker(S)
                    }
                }
                function k(e) {
                    Q && ("1" == X.insureInfo.insuredType ? "" != Z.sex && "0" != Z.sex && t("#sex").val(Z.sex) : "2" == X.insureInfo.insuredType && t.each(X.insureInfo.relationToAppnt,
                    function(e, n) {
                        if ("00" == n.value) return "" != Z.sex && "0" != Z.sex && t("#sex").val(Z.sex),
                        !1
                    }), ne && ee && t("#sex").attr("disabled", !0)),
                    "0" == X.insureInfo.sex ? t("#sex-li").hide() : ie.sex && t("#sex").val(ie.sex),
                    t("#sex").change(function() {
                        void 0,
                        L()
                    }),
                    e(null)
                }
                function w() {
                    var e = t("#plansListTab .tab-link.active").data("planname"),
                    n = t("#mult").val().trim();
                    if ("1" == X.insureInfo.isSale) {
                        var a = !0;
                        if ("" == e) {
                            var i = X.safeguardPlansInfo[0].stockNum;
                            null == i || "" != i && parseInt(i) < parseInt(n) ? (t("#buy").css("background-color", "#ccc"), t("#buy").html("已售罄"), t("#buy").addClass("outSale"), a = !1) : (t("#buy").css("background-color", "#e7af6e"), t("#buy").removeClass("outSale"), 0 != oe.orderAmount ? t("#buy").html("立即投保") : t("#buy").html("免费领取"))
                        } else t.each(X.safeguardPlansInfo,
                        function(i, o) {
                            if (e == o.planName) {
                                var l = o.stockNum;
                                return null == l || "" != l && parseInt(l) < parseInt(n) ? (t("#buy").css("background-color", "#ccc"), t("#buy").html("已售罄"), t("#buy").addClass("outSale"), a = !1) : (t("#buy").css("background-color", "#e7af6e"), t("#buy").removeClass("outSale"), 0 != oe.orderAmount ? t("#buy").html("立即投保") : t("#buy").html("免费领取")),
                                !1
                            }
                        })
                    } else a = !1,
                    "2" == X.insureInfo.isSale ? (t(".productPrice").hide(), t("#buy").removeClass("col-40").addClass("col-80 outSale").css({
                        "background-color": "#E8E9EA",
                        "font-size": ".8889rem",
                        color: "#E8AF6E"
                    }), X.insureInfo.stopSaleDesc ? t("#buy").html(X.insureInfo.stopSaleDesc) : t("#buy").html("审核中")) : t("#buy").css("background-color", "#ccc").html("已售罄").addClass("outSale");
                    return a
                }
                function C(e) {
                    "1" == X.insureInfo.maxMult ? t("#amount-li").hide() : z(X.insureInfo.maxMult),
                    oe.mult && t("#mult").val(oe.mult),
                    t("#btn_decrease").click(function() {
                        E()
                    }),
                    t("#btn_plus").on("click",
                    function(e) {
                        W()
                    }),
                    t("#mult").on("change",
                    function(e) {
                        q()
                    }),
                    e(null)
                }
                function x(e) {
                    ye = "",
                    be = "",
                    t("#" + e + "Code").val("").show(),
                    t.customer.graphSend().then(function(n, a) {
                        "0" === a.result ? (void 0, void 0, ye = a.graphId, t("#graphInfoImg").attr("src", a.graphUrl), t("#login_yzm_li").show()) : t.alertMessage(a.resultMessage)
                    })
                }
                function A(e) {
                    t.customer.accountInfo({}).then(function(e, n) {
                        Z = n,
                        "1" == X.pointsInfo.isPoint ? t.customer.getPointsValue({}).then(function(t, a) {
                            V = a.availablePoint,
                            ie.availablePoint = V,
                            e(null, n)
                        }) : e(null, n)
                    }).then(function(n, a) {
                        t.hideBizIndicator(),
                        a.idNoStar && "" != a.idNoStar && (ee = !0),
                        ie.jumpInsure = !!(ee && te && ne),
                        ie.isOpen = !0,
                        d(),
                        t.util.setSessionStorageObj("buyInfo", ie),
                        X.isShowInform && "1" == X.isShowInform ? t.util.goPage("./healthTold.html") : "./insure.html" == e && ie.jumpInsure ? Y(oe) : t.util.goPage(e)
                    })
                }
                function M() {
                    t("#mobile").val(t.trim(t("#mobile").val())),
                    t("#smsCode").val(t.trim(t("#smsCode").val()));
                    var e = t("#mobile").val(),
                    n = t("#smsCode").val();
                    if ("loginMobile" == he) {
                        if ("" == e) return t.alertMessage("手机号不可为空");
                        if (!t.validator.isMobile(e, "手机号码格式有误")) return;
                        if ("" == n) return t.alertMessage("手机验证码不可为空");
                        t("#mobile").val()
                    }
                    if (null != ye && "" != ye) {
                        if (t("#graphInfoCode").val(t.trim(t("#graphInfoCode").val())), "" == (be = t("#graphInfoCode").val())) return t.alertMessage("图形验证码不可为空")
                    } else t("#graphInfoCode").val(""),
                    be = "";
                    var a = {
                        mobile: e,
                        smsId: ve,
                        smsCode: n,
                        graphCode: be,
                        graphId: ye,
                        thirdToken: t.util.getThird()
                    };
                    t.showBizIndicator(),
                    void 0,
                    void 0,
                    t.customer.quickLogin(a).then(function(e, n) {
                        "0" === n.result ? (t.util.setToken(n.token), Q ? (ie.jumpInsure = !!(ee && te && ne), t.util.setSessionStorageObj("buyInfo", ie), X.isShowInform && "1" == X.isShowInform ? t.util.goPage("healthTold.html") : ie.jumpInsure ? Y(oe) : t.util.goPage("insure.html")) : "app" == t.util.getSystemType() ? X.isShowInform && "1" == X.isShowInform ? t.util.goPage("healthTold.html") : t.util.goPage("insure.html") : A(X.isShowInform && "1" == X.isShowInform ? "./healthTold.html": "./insure.html")) : "3" === n.result ? (t.util.setRedirectUrl(backUrl), t.util.goPage(t.util.getAbsoluteUrl(t.pageFilterConfig.ctx_base + "/login/userSetting.html?loginToken=" + n.loginToken + "&regSystemType=" + n.regSystemType))) : "7" === n.result ? (t.hideBizIndicator(), void 0, x("graphLogin"), t.alertMessage(n.resultMessage)) : (t.hideBizIndicator(), null != ye && "" != ye && x("graphLogin"), t.alertMessage(n.resultMessage))
                    })
                }
                function F(e) {
                    function n(e) {
                        e--,
                        0 == e ? (fe = !0, t("#sendInfoLogin").html("获取验证码")) : (t("#sendInfoLogin").html("重新获取(" + e + ")"), window.setTimeObj = setTimeout(function() {
                            n(e)
                        },
                        1e3))
                    }
                    function a(e) {
                        if (fe) {
                            t("#mobile").val(t.trim(t("#mobile").val()));
                            var e = t("#mobile").val();
                            if ("" == e) return t.alertMessage("请输入手机号");
                            if (!t.validator.isMobile(e, "手机号码格式有误")) return;
                            if (null != ye && "" != ye && (t("#graphInfoCode").val(t.trim(t("#graphInfoCode").val())), "" == (be = t("#graphInfoCode").val()))) return t.alertMessage("图形验证码不可为空");
                            fe = !1,
                            t.customer.smsSend({
                                mobile: e,
                                graphId: ye,
                                graphCode: be,
                                operateType: "regandlogin",
                                thirdToken: t.util.getThird()
                            }).then(function(e, a) {
                                "0" === a.result ? (t.alertMessage("验证码已发送至您的手机" + a.mobileStar), fe = !1, ve = a.smsId, n(60), null != ye && "" != ye && (t("#login_yzm_li").show(), x("graphInfo"))) : "2" === a.result ? (t("#login_yzm_li").show(), x("graphInfo"), fe = !0, t.alertMessage(a.resultMessage)) : "1" === a.result ? (t("#login_yzm_li").show(), x("graphInfo"), fe = !0, t.alertMessage(a.resultMessage)) : (fe = !0, t.alertMessage(a.resultMessage))
                            })
                        }
                    }
                    t("#graphInfoImg").click(function() {
                        x("graphInfo")
                    }),
                    window.setTimeObj && (clearTimeout(window.setTimeObj), window.setTimeObj = null),
                    t("#sendInfoLogin").click(function() {
                        a(mobile)
                    })
                }
                function j(e) {
                    var n = X.insureInfo.loginType;
                    1 == O(t.util.getRequestParams().comid, n) ? Q ? (t("#phone-number").hide(), t("#phone-code").hide(), t("#login_yzm_li").hide()) : (t("#phone-number").show(), t("#phone-code").show(), F()) : (t("#phone-number").hide(), t("#phone-code").hide(), t("#login_yzm_li").hide()),
                    e(null)
                }
                function O(e, n) {
                    var a = !1;
                    return t.each(n,
                    function(t, n) {
                        n.comid == e && "1" == n.loginType && (a = !0)
                    }),
                    a
                }
                function R() {
                    t("#buy").click(function() {
                        if (!t(this).hasClass("outSale")) {
                            if (t(this).hasClass("canNotBuy")) return void t.alertMessage("当前选择项不支持投保");
                            t("#transferStatus").prop("checked") ? (ie.isOpen = !0, oe.exchangeIntegral = parseFloat(100 * ie.exchangeMoney).toFixed(0)) : (ie.isOpen = !1, oe.exchangeIntegral = ""),
                            ie.availablePoint = V,
                            ie.minPoint = X.pointsInfo.minPoint,
                            ie.minPrice = X.pointsInfo.minPrice,
                            ie.rate = X.pointsInfo.rate,
                            ie.maxPrice = X.pointsInfo.maxPrice;
                            var e = (t(".insurePeriod").val(), t(".chargePeriod").val(), t("#cvalidate-picker").val()),
                            n = t("#sex").val();
                            "0" == X.insureInfo.sex && (n = "0");
                            var a = t("#birthday-picker").val();
                            if (!t("#terms").is(":checked")) return void t.alertMessage("请勾选阅读条款");
                            if (0 == X.insureInfo.insuredAgeRange.length) var i = !0;
                            else {
                                if (!t.validator.isEmpty(a, "请选择被保人出生日期")) return;
                                var i = !0
                            }
                            if ("1" == X.insureInfo.insuredType) {
                                if (!t.validator.isEmpty(e, "请选择生效日期")) return;
                                if (!J()) return
                            } else {
                                if (!t.validator.isEmpty(e, "请选择生效日期")) return;
                                if (!t.validator.isEmpty(n, "请选择性别")) return;
                                if (!J()) return
                            }
                            if (!w()) return void t.alertMessage("剩余库存不足");
                            if (i) if (void 0, void 0, void 0, t.util.setSessionStorageObj("insureInfoDetail", X.insureInfo), t.util.setSessionStorageObj("orderInfo", oe), void 0, t.util.setSessionStorageObj("buyInfo", ie), t.util.setSessionStorageObj("insuredInfo", le), Q) ie.jumpInsure = !!(ee && te && ne),
                            t.util.setSessionStorageObj("buyInfo", ie),
                            X.isShowInform && "1" == X.isShowInform ? t.util.goPage("healthTold.html") : ie.jumpInsure ? Y(oe) : t.util.goPage("insure.html");
                            else if ("app" == t.util.getSystemType()) X.isShowInform && "1" == X.isShowInform ? t.util.goPage("healthTold.html") : t.util.goPage("insure.html");
                            else {
                                var o = X.insureInfo.loginType,
                                l = t.util.getRequestParams().comid;
                                if (1 == O(l, o)) {
                                    t("#mobile").val(),
                                    t("#smsCode").val();
                                    M()
                                } else G(X.isShowInform && "1" == X.isShowInform ? "./healthTold.html": "./insure.html")
                            }
                        }
                    })
                }
                function L(e) {
                    void 0,
                    N(),
                    void 0,
                    U();
                    var n = H(ie.birthday),
                    a = t("#plansListTab .tab-link.active").data("planname");
                    if (void 0, void 0, void 0, void 0, void 0, void 0, void 0, void 0, void 0, void 0, "" == oe.cvalidDate || "" == ie.sex || "" == ie.birthday && 0 != X.insureInfo.insuredAgeRange.length) {
                        var i = t("#mult").val(),
                        o = X.insureInfo.maxMult;
                        i = parseInt(i) > o ? o: parseInt(t("#mult").val()),
                        t("#mult").val(i),
                        oe.mult = i;
                        var l = t("#plansListTab .tab-link.active").data("planmoney");
                        "" == l && (l = X.insureInfo.price),
                        l *= t("#mult").val(),
                        t("#orderAmount").html(parseFloat(l).toFixed(2)),
                        oe.orderAmount = parseFloat(l).toFixed(2)
                    } else if (void 0, J()) {
                        var l = "";
                        "1" == X.insureInfo.isRateCode ? B(n, e) : (l = _(a, oe.insurePeriod, oe.insurePeriodType, n, ie.sex, oe.payInterval, oe.payIntervalType, oe.chargeWay), void 0, "" == l ? (l = t("#plansListTab .tab-link.active").data("planmoney"), "" == l && (l = X.insureInfo.price), l *= t("#mult").val(), t("#orderAmount").html(parseFloat(l).toFixed(2)), oe.orderAmount = parseFloat(l).toFixed(2), t("#buy").removeClass("canNotBuy")) : "--" == l ? (l = "--", oe.orderAmount = -1, t("#orderAmount").html(l), t("#buy").hasClass("canNotBuy") || t("#buy").addClass("canNotBuy")) : (l *= t("#mult").val(), t("#orderAmount").html(parseFloat(l).toFixed(2)), oe.orderAmount = parseFloat(l).toFixed(2), t("#buy").removeClass("canNotBuy")))
                    } else {
                        var i = t("#mult").val(),
                        o = X.insureInfo.maxMult;
                        i = parseInt(i) > o ? o: parseInt(t("#mult").val()),
                        t("#mult").val(i),
                        oe.mult = i;
                        var l = t("#plansListTab .tab-link.active").data("planmoney");
                        "" == l && (l = X.insureInfo.price),
                        l *= t("#mult").val(),
                        t("#orderAmount").html(parseFloat(l).toFixed(2)),
                        oe.orderAmount = parseFloat(l).toFixed(2)
                    }
                    "1" != X.insureInfo.isRateCode && (0 == oe.orderAmount ? (t(".productPrice").hide(), t("#buy").attr("class", "col-80"), t("#buy").html("免费领取")) : w(), Q && 1 == X.pointsInfo.isPoint && d(), e && e())
                }
                function N() {
                    var e = t(".insurePeriod").val();
                    if (t(".insurePeriod").val() > 0) {
                        if (t(".insurePeriod").find("option").length > 0) var n = t(".insurePeriod").find("option").not(function() {
                            return ! this.selected
                        }).data("insureperiodunit");
                        else var n = t(".insurePeriod").data("insureperiodunit");
                        oe.insurePeriod = e,
                        oe.insurePeriodType = n
                    }
                    if (t(".chargePeriod").val() > 0) {
                        var a = t(".chargePeriod").val();
                        if (t(".chargePeriod").find("option").length > 0) var i = t(".chargePeriod").find("option").not(function() {
                            return ! this.selected
                        }).data("chargeperiodunit");
                        else var i = t(".chargePeriod").data("chargeperiodunit");
                        oe.payInterval = a,
                        oe.payIntervalType = i
                    }
                    if (t("#payStyle").val() > 0) {
                        if (t("#payStyle").find("option").length > 0) {
                            var o = t("#payStyle").find("option").not(function() {
                                return ! this.selected
                            }).attr("data-payStyle");
                            void 0
                        }
                        oe.chargeWay = o
                    }
                    var l = t("#cvalidate-picker").val(),
                    r = t("#plansListTab .tab-link.active").data("productcode"),
                    s = t("#plansListTab .tab-link.active").data("planname");
                    "" == r && (r = X.productCode, s = X.productName),
                    oe.cvalidDate = l,
                    oe.mult = t("#mult").val(),
                    oe.productCode = r,
                    oe.productName = s,
                    void 0
                }
                function U() {
                    "1" == X.insureInfo.insuredType && (le.relationToAppnt = "00", ie.relationToAppntLabel = "本人", le.bnfLegalFlag = "1");
                    var e = t("#sex").val();
                    "0" == X.insureInfo.sex && (e = "0");
                    var n = t("#birthday-picker").val();
                    ie.sex = e,
                    ie.birthday = n,
                    ie.informContent = X.informContent,
                    void 0
                }
                function _(e, n, a, i, o, l, r, s) {
                    void 0,
                    void 0,
                    "1" == o ? o = "男": "2" == o ? o = "女": "0" == o && (o = "不详"),
                    "M" == a ? n += "月": "D" == a ? n += "天": "Y" == a ? n += "年": "A" == a && (n += "岁"),
                    "Y" == r ? l += "年": "M" == r && (l += "月");
                    var u = t("#mult").val(),
                    c = X.insureInfo.propertyMapperExcel;
                    if (void 0 != c && "" != c) {
                        var d = "";
                        return t.each(c,
                        function(a, r) {
                            if ((void 0 == e || "" == r.protectPlan.trim() || r.protectPlan.trim() == e) && !(void 0 != o && "" != r.sex && r.sex != o || void 0 != s && "" != r.chargeWay && r.chargeWay != s || void 0 != l && "" != r.chargePeriod && r.chargePeriod != l || void 0 != n && "" != r.insurePeriod && r.insurePeriod != n)) if (r.insuredAge && "" != r.insuredAge && "-" != r.insuredAge) {
                                var c = "",
                                g = "",
                                p = i.substring(i.length - 1),
                                m = i.substring(0, i.length - 1),
                                h = r.insuredAge,
                                f = h.split("-");
                                if (1 == f.length) {
                                    var v = f[0].substring(f[0].length - 1),
                                    y = f[0].substring(0, f[0].length - 1);
                                    "天" == v ? v = "D": "年" == v && (v = "Y"),
                                    p == v && parseInt(m) == parseInt(y) ? (c = !0, g = !0) : (c = !1, g = !1)
                                } else t.each(f,
                                function(e, t) {
                                    if (e == parseInt("0")) {
                                        var n = t.substring(t.length - 1),
                                        a = t.substring(0, t.length - 1);
                                        "天" == n ? n = "D": "年" == n && (n = "Y"),
                                        p == n ? c = parseInt(m) >= parseInt(a) : "D" == p && "Y" == n ? c = !1 : "Y" == p && "D" == n && (c = !0)
                                    }
                                    if (e == parseInt("1")) {
                                        var i = t.substring(t.length - 1),
                                        o = t.substring(0, t.length - 1);
                                        "天" == i ? i = "D": "年" == i && (i = "Y"),
                                        p == i ? g = parseInt(m) <= parseInt(o) : "D" == p && "Y" == i ? g = !0 : "Y" == p && "D" == i && (g = !1)
                                    }
                                });
                                g && c && (parseInt(r.mult) < parseInt(u) && (t("#mult").val(r.mult), oe.mult = r.mult), z(parseInt(r.mult)), d = void 0 == r.amt || "" == r.amt ? "--": r.amt)
                            } else parseInt(r.mult) < parseInt(u) && (t("#mult").val(r.mult), oe.mult = r.mult),
                            z(parseInt(r.mult)),
                            d = void 0 == r.amt || "" == r.amt ? "--": r.amt
                        }),
                        d = d.replace(/,/g, "")
                    }
                    return ""
                }
                function B(e, a) {
                    var i = X.insureInfo.defaultRateCode,
                    o = "";
                    1 == X.safeguardPlansInfo.length ? "" != X.safeguardPlansInfo[0].rateCode && (i = X.safeguardPlansInfo[0].rateCode, o = X.safeguardPlansInfo[0].remark) : "" != t("#plansListTab .tab-link.active").data("ratecode") && (i = t("#plansListTab .tab-link.active").data("ratecode"), o = t("#plansListTab .tab-link.active").data("remark")),
                    o || (o = X.insureInfo.amount);
                    var l = "";
                    null != oe.insurePeriod && null != oe.insurePeriodType && (l = oe.insurePeriod + oe.insurePeriodType);
                    var r = "";
                    null != oe.payInterval && null != oe.payIntervalType && (r = oe.payInterval + oe.payIntervalType);
                    var s = "";
                    X.insureInfo.chargeWay && X.insureInfo.chargeWay.length > 1 ? s = t("#payStyle").val() : X.insureInfo.chargeType && X.insureInfo.chargeType.key && (s = X.insureInfo.chargeType.key);
                    var u = "";
                    "" != s && ("1" == s ? u = "12": "2" == s ? u = "1": "3" == s ? u = "3": "4" == s ? u = "6": "0" == s && (u = "0"));
                    var c = "";
                    "" != e && ("D" == e.substr( - 1) ? c = 0 : "Y" == e.substr( - 1) && (c = e.substring(0, e.length - 1)));
                    var g = "1" == ie.sex ? "0": "1",
                    p = {};
                    p.InsPeriod = l,
                    p.PayPeriod = r,
                    p.PayType = u,
                    p.MaxAge = "",
                    p.MinAge = "",
                    p.Age = c,
                    p.Sex = g,
                    p.Amount = o;
                    var m = JSON.stringify(p),
                    h = {
                        rateCode: i,
                        elementInfo: m
                    };
                    t.util.setSessionStorageObj("trialInfo", h);
                    var f = "";
                    n.trial(h).then(function(e, n) {
                        void 0,
                        "0" == n.result && (f = n.orderAmount),
                        "" == f ? (f = t("#plansListTab .tab-link.active").data("planmoney"), "" == f && (f = X.insureInfo.price), f *= t("#mult").val(), t("#orderAmount").html(parseFloat(f).toFixed(2)), oe.orderAmount = parseFloat(f).toFixed(2), t("#buy").removeClass("canNotBuy")) : "--" == f ? (f = "--", oe.orderAmount = -1, t("#orderAmount").html(f), t("#buy").hasClass("canNotBuy") || t("#buy").addClass("canNotBuy")) : (f *= t("#mult").val(), t("#orderAmount").html(parseFloat(f).toFixed(2)), oe.orderAmount = parseFloat(f).toFixed(2), t("#buy").removeClass("canNotBuy")),
                        0 == oe.orderAmount ? n.orderAmount ? (t(".productPrice").hide(), t("#buy").attr("class", "col-80"), t("#buy").html("免费领取")) : (t(".productPrice").html("- -"), t("#buy").css("background-color", "#ccc").addClass("canNotBuy")) : w(),
                        Q && 1 == X.pointsInfo.isPoint && d(),
                        a && a()
                    })
                }
                function Y(e) {
                    var a = {
                        relationToAppnt: "00",
                        bnfLegalFlag: "1"
                    },
                    i = new Array;
                    i.push(a);
                    var o = {};
                    o = null == t.util.getSessionStorageObj("activityInfo") ? {
                        orderInfo: e,
                        targetInfo: null,
                        activityInfo: null,
                        insuredInfo: i
                    }: {
                        orderInfo: e,
                        targetInfo: null,
                        activityInfo: t.util.getSessionStorageObj("activityInfo"),
                        insuredInfo: i
                    },
                    t.showBizIndicator(),
                    n.underWrite(o).then(function(a, i) {
                        "0" == i.result ? "0" == i.underWriteStatus ? 0 == e.orderAmount ? n.payAndSign({
                            applicationNo: i.applicationNo,
                            payMode: "wxPay",
                            accountNo: t.util.getThird(),
                            timestamp: "",
                            nonceStr: ""
                        }).then(function(n, a) {
                            var o = a.result;
                            if (ie.payAndSignResultMessage = a.payAndSignResultMessage, ie.applicationNo = i.applicationNo, ie.productName = e.productName, ie.orderAmount = e.orderAmount, void 0, t.hideBizIndicator(), "0" == o) {
                                var l = a.payAndSignResult;
                                "0" == l && t.util.goPage("result.html?resultType=payAndSign&result=0&applicationNo=" + i.applicationNo + "&itemCode=" + ae.itemCode),
                                "1" == l && t.util.goPage("result.html?resultType=payAndSign&result=1&resultMessage=" + encodeURI(a.payAndSignResultMessage)),
                                "2" == l && t.util.goPage("result.html?resultType=payAndSign&result=2")
                            } else t.util.goPage("result.html?resultType=payAndSign&result=1&resultMessage=交易处理异常")
                        }) : t.util.goPage("../../payment/safeguardPayment.html?applicationNo=" + i.applicationNo + "&itemCode=" + e.itemCode, {
                            location: !0,
                            type: !0
                        }) : t.util.goPage("result.html?resultType=underWrite&result=1&resultMessage=" + i.underWriteMsg) : (t.hideBizIndicator(), t.util.goPage("result.html?resultType=underWrite&result=1&resultMessage=" + i.underWriteMsg))
                    })
                }
                function z(e) {
                    var n = parseInt(t("#mult").val());
                    void 0,
                    n <= 1 ? (t("#btn_decrease").addClass("color-gray"), n == e ? t("#btn_plus").addClass("color-gray") : t("#btn_plus").removeClass("color-gray")) : n >= e ? (t("#btn_plus").addClass("color-gray"), 1 == n ? t("#btn_decrease").addClass("color-gray") : t("#btn_decrease").removeClass("color-gray")) : (t("#btn_decrease").removeClass("color-gray"), t("#btn_plus").removeClass("color-gray")),
                    w()
                }
                function E() {
                    var e = t("#mult").val();
                    e = e <= 1 ? 1 : parseInt(e) - 1,
                    t("#mult").val(e),
                    oe.mult = e,
                    z(X.insureInfo.maxMult),
                    L()
                }
                function W() {
                    var e = t("#mult").val(),
                    n = X.insureInfo.maxMult;
                    e = parseInt(e) + 1,
                    e >= n && (e = n),
                    t("#mult").val(e),
                    oe.mult = e,
                    z(X.insureInfo.maxMult),
                    L()
                }
                function q() {
                    var e = t("#mult").val(),
                    n = X.insureInfo.maxMult;
                    e = "" == e || isNaN(e) || e < 1 ? "1": parseInt(e) > n ? "1": parseInt(t("#mult").val()),
                    t("#mult").val(e),
                    oe.mult = e,
                    z(X.insureInfo.maxMult),
                    L()
                }
                function H(e) {
                    if (void 0, void 0 != e) {
                        var n = "";
                        if ("0" == X.insureInfo.insuredAgeType) n = t("#cvalidate-picker").val();
                        else {
                            var a = new Date((new Date).getTime()),
                            i = a.getMonth() < 9 ? "0" + (a.getMonth() + 1) : a.getMonth() + 1,
                            o = a.getDate() < 10 ? "0" + a.getDate() : a.getDate();
                            n = a.getFullYear() + "-" + i + "-" + o
                        }
                        var l = n.split("-"),
                        r = l[0],
                        s = l[1],
                        u = l[2];
                        void 0,
                        void 0,
                        void 0;
                        var c = e.split("-"),
                        d = c[0],
                        g = c[1],
                        p = c[2];
                        if (void 0, void 0, void 0, void 0, void 0, oDate1 = new Date(r, s - 1, u, 0, 0, 0), void 0, oDate2 = new Date(d, g - 1, p, 0, 0, 0), void 0, returnAge = parseInt(Math.abs(oDate1 - oDate2) / 1e3 / 60 / 60 / 24), void 0, r == d || parseInt(r) == parseInt(d) + 1 && parseInt(s) < parseInt(g) || parseInt(r) == parseInt(d) + 1 && parseInt(s) == parseInt(g) && parseInt(u) <= parseInt(p)) oDate1 = new Date(r, s - 1, u, 0, 0, 0),
                        void 0,
                        oDate2 = new Date(d, g - 1, p, 0, 0, 0),
                        void 0,
                        returnAge = parseInt(Math.abs(oDate1 - oDate2) / 1e3 / 60 / 60 / 24),
                        void 0,
                        returnAge += "D";
                        else {
                            var m = r - d;
                            if (m > 0) {
                                if (s == g) {
                                    var h = u - p;
                                    returnAge = h < 0 ? m - 1 : m
                                } else {
                                    var f = s - g;
                                    returnAge = f < 0 ? m - 1 : m
                                }
                                returnAge += "Y"
                            } else returnAge = -1
                        }
                        return returnAge
                    }
                    return ""
                }
                function J() {
                    if (0 == X.insureInfo.insuredAgeRange.length) return ! 0;
                    var e = ie.insuredBeginDate,
                    n = ie.insuredEndDate;
                    return void 0,
                    void 0,
                    new Date(n).getTime() >= new Date(t("#birthday-picker").val()).getTime() && new Date(t("#birthday-picker").val()).getTime() >= new Date(e).getTime() || (t.alertMessage("请确认被保险人年龄在可保范围内"), !1)
                }
                function G(e) {
                    new t.loginModal({
                        loginSuccess: function() {
                            "" == e ? location.reload() : t.customer.accountInfo({}).then(function(e, n) {
                                Z = n,
                                "1" == X.pointsInfo.isPoint ? t.customer.getPointsValue({}).then(function(t, a) {
                                    V = a.availablePoint,
                                    ie.availablePoint = V,
                                    e(null, n)
                                }) : e(null, n)
                            }).then(function(n, a) {
                                t.hideBizIndicator(),
                                a.idNoStar && "" != a.idNoStar && (ee = !0),
                                ie.jumpInsure = !!(ee && te && ne),
                                t.util.setSessionStorageObj("buyInfo", ie),
                                "./insure.html" == e && ie.jumpInsure ? (t("#birthday-picker").val(a.birthday), t("#sex").val(a.sex), L(function() {
                                    Y(oe)
                                })) : t.util.goPage(e)
                            })
                        },
                        backUrl: t.util.getAbsoluteUrl(e)
                    }).open()
                }
                function $(e) {
                    if (!t.util.isPlateformWx()) return ! 1;
                    var n = location.href;
                    o.chImg({
                        shareUrl: n
                    }).then(function(n, a) {
                        var i = a.timestamp,
                        o = a.nonceStr,
                        r = a.signature;
                        l.config({
                            debug: !1,
                            appId: t.util.getAppId(),
                            timestamp: i,
                            nonceStr: o,
                            signature: r,
                            jsApiList: ["onMenuShareAppMessage", "onMenuShareTimeline"]
                        }),
                        l.error(function(t) {
                            Ie >= 0 && (Ie -= 1, $(e))
                        }),
                        n(null)
                    }).then(function(a) {
                        l.ready(function() {
                            if (1 == e.length) l.onMenuShareAppMessage({
                                title: e[0].title,
                                desc: e[0].description,
                                link: e[0].url,
                                imgUrl: e[0].image,
                                success: function(e) {
                                    K(a)
                                },
                                fail: function(e) {
                                    void 0
                                }
                            }),
                            l.onMenuShareTimeline({
                                title: e[0].title,
                                link: e[0].url,
                                imgUrl: e[0].image,
                                success: function(e) {
                                    setTimeout(function() {
                                        K(a)
                                    },
                                    500)
                                },
                                fail: function(e) {
                                    void 0
                                }
                            });
                            else {
                                var i = "0";
                                n.indexOf("&comid=") > 0 && (i = "1"),
                                t.each(e,
                                function(e, t) {
                                    "wx_friend" == t.type && l.onMenuShareAppMessage({
                                        title: t.title,
                                        desc: t.description,
                                        link: "1" == i ? n: t.url,
                                        imgUrl: t.image,
                                        success: function(e) {
                                            K(a)
                                        },
                                        fail: function(e) {
                                            void 0
                                        }
                                    }),
                                    "wx_space" == t.type && l.onMenuShareTimeline({
                                        title: t.title,
                                        link: "1" == i ? n: t.url,
                                        imgUrl: t.image,
                                        success: function(e) {
                                            setTimeout(function() {
                                                K(a)
                                            },
                                            500)
                                        },
                                        fail: function(e) {
                                            void 0
                                        }
                                    })
                                })
                            }
                        }),
                        a(null)
                    })
                }
                function K(e) {
                    n.activityInvolveCheck({
                        comId: t.util.getRequestParams().comid
                    }).then(function(e, a) {
                        if ("1" == a.result) if (Q) n.activityInvolve({
                            activityCode: "HR_20180502",
                            recommendCode: "HR_2018050220000386"
                        }).then(function(e, n) {
                            if (void 0, "1" == n.result) {
                                var a = n.drawTemplate,
                                i = t.modal({
                                    title: "",
                                    extraClass: "Congratulations-to-draw",
                                    text: '<img id="CongratulationsImg" src="' + a + '" alt="">',
                                    afterText: '<div class="gclose" id="closeBtn"><img src="' + t.pageFilterConfig.ctx_base + '/public/img/campaign/gclose.png" alt=""></div>'
                                });
                                t(".Congratulations-to-draw").click(function() {
                                    t.util.setSource({
                                        url: t.util.getAbsoluteUrl("./detail.html")
                                    }),
                                    t.util.goPage("../../campaign/draw.html", {
                                        location: !0,
                                        pageKey: "COMMON_PRIMARY_PAGE"
                                    }),
                                    t.closeModal(i)
                                }),
                                t("#closeBtn").click(function(e) {
                                    e.stopPropagation(),
                                    t.closeModal(i)
                                })
                            }
                        });
                        else {
                            var i = t.modal({
                                title: "提示",
                                extraClass: "Lottery-tomorrow-modal",
                                text: '<div class="getmove">完成登录可赠送刮刮卡机会</div><div class="bt" id="goLoginBtn">确定</div>',
                                afterText: '<div class="gclose closeBTN"><img src="' + t.pageFilterConfig.ctx_base + '/public/img/campaign/gclose.png" alt=""></div>'
                            });
                            t("#goLoginBtn").click(function() {
                                t.util.setSessionStorageObj("afterShare", "1"),
                                t.util.setRedirectUrl(window.location.href),
                                t.util.goPage("../../login/login.html", {
                                    pageKey: "COMMON_PRIMARY_PAGE"
                                })
                            }),
                            t(".closeBTN").click(function() {
                                t.closeModal(i)
                            })
                        }
                    }),
                    e(null, !0)
                }
                var Q = !1,
                V = null,
                X = null,
                Z = null,
                ee = !1,
                te = !1,
                ne = !1,
                ae = t.util.getRequestParams();
                if (null != t.util.getSessionStorageObj("orderInfo") && t.util.getSessionStorageObj("orderInfo").itemCode == ae.itemCode) var ie = t.util.getSessionStorageObj("buyInfo"),
                oe = t.util.getSessionStorageObj("orderInfo"),
                le = null == t.util.getSessionStorageObj("insuredInfo") ? {}: t.util.getSessionStorageObj("insuredInfo");
                else var ie = {},
                oe = {},
                le = {};
                if (ae.orderType && "beiyi" == ae.orderType) {
                    if (ie.sex = parseInt(ae.appntGender) + 1, ie.birthday = ae.appntBirthDay, ie.orderType = "beiyi", ae.operationTime && "" != ae.operationTime) {
                        var re = new Date;
                        re.setFullYear(ae.operationTime.substr(0, 4)),
                        re.setMonth(parseInt(ae.operationTime.substr(4, 2)) - 1),
                        re.setDate(ae.operationTime.substr(6, 2)),
                        re.setHours(ae.operationTime.substr(8, 2)),
                        re.setMinutes(ae.operationTime.substr(10, 2));
                        var se = re.getMonth() < 9 ? "0" + (re.getMonth() + 1) : re.getMonth() + 1,
                        ue = re.getDate() < 10 ? "0" + re.getDate() : re.getDate(),
                        ce = re.getHours() < 10 ? "0" + re.getHours() : re.getHours(),
                        de = re.getMinutes() < 10 ? "0" + re.getMinutes() : re.getMinutes();
                        oe.cvalidDate = re.getFullYear() + "-" + se + "-" + ue + " " + ce + ":" + de,
                        void 0
                    } else {
                        var re = new Date,
                        se = re.getMonth() < 9 ? "0" + (re.getMonth() + 1) : re.getMonth() + 1,
                        ue = re.getDate() < 10 ? "0" + re.getDate() : re.getDate();
                        oe.cvalidDate = re.getFullYear() + "-" + se + "-" + ue
                    }
                    var ge = {};
                    ge.hospitalCity = ae.hospitalCity,
                    ge.hospitalName = ae.hospitalName,
                    ge.operationTime = ae.operationTime,
                    ge.operationLevel = ae.operationLevel,
                    ge.operationName = ae.operationName,
                    ge.anesthesiaLevel = ae.anesthesiaLevel,
                    ge.remark = ae.remark;
                    var pe = new Array;
                    pe.push(ge);
                    var me = JSON.stringify(pe);
                    t.util.setSessionStorageObj("targetInfo", me)
                } else ae.orderType && (oe.orderType = ae.orderType);
                ae.comid ? t.util.setSessionStorageObj("activityInfo", {
                    comId: ae.comid,
                    comRemark: ae.comremark,
                    activityCode: ae.activityCode,
                    recommandCode: ae.recommandCode,
                    activityRemark: ae.activityRemark
                }) : "HR_20180626" == ae.activityCode ? o.thirdInfo({
                    thirdToken: t.util.getThird()
                }).then(function(e, n) {
                    var a = {};
                    n && n.thirdImageUrl && (a.thirdImageUrl = n.thirdImageUrl),
                    t.util.setSessionStorageObj("activityInfo", {
                        comRemark: JSON.stringify(a),
                        activityCode: ae.activityCode,
                        recommandCode: ae.recommandCode
                    })
                }) : t.util.removeSessionStorageObj("activityInfo"),
                r.parallel([function(e) {
                    s(e)
                },
                function(e) {
                    u(e)
                }]).parallel([function(e) {
                    g(e)
                },
                function(e) {
                    p(e)
                },
                function(e) {
                    m(e)
                },
                function(e) {
                    h(e)
                },
                function(e) {
                    f(e)
                },
                function(e) {
                    v(e)
                },
                function(e) {
                    y(e)
                },
                function(e) {
                    b(e)
                },
                function(e) {
                    I(e)
                },
                function(e) {
                    T(e, 0)
                },
                function(e) {
                    S(e)
                },
                function(e) {
                    k(e)
                },
                function(e) {
                    C(e)
                },
                function(e) {
                    c(e)
                },
                function(e) {
                    j(e)
                }]).then(function(e) {
                    oe.itemCode = X.itemCode,
                    L(),
                    R();
                    var n = t.util.getSessionStorageObj("afterShare");
                    n && "1" == n && (t.util.setSessionStorageObj("afterShare", "0"), K(e)),
                    e(null)
                }).then(function(e) {
                    t.hideMainPageLoader()
                });
                var he = "loginMobile",
                fe = !0,
                ve = "",
                ye = "",
                be = "",
                Ie = 3
            },
            onAppInit: s,
            onAppCallBack: u
        },
        view: {
            pageTitle: !0
        }
    })
});