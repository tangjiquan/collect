$.smVersion = "0.6.2",
function($) {
    "use strict";
    var t = {
        autoInit: !1,
        showPageLoadingIndicator: !0,
        showPageLoadingCustomer: !0,
        router: !0,
        animation: !1,
        ignoreCache: {
            allForward: !0,
            allBack: !0
        },
        checkBefore: !0,
        swipePanel: "left",
        swipePanelOnlyClose: !0
    };
    $.smConfig = $.extend(t, $.config)
}(Zepto),
function($) {
    $.extend($.fn, {
        cookie: function(t, e, n) {
            var i, a, o, r;
            return arguments.length > 1 && "[object Object]" !== String(e) ? (n = $.extend({}, n),
            null !== e && void 0 !== e || (n.expires = -1),
            "number" == typeof n.expires && (i = 24 * n.expires * 60 * 60 * 1e3,
            a = n.expires = new Date,
            a.setTime(a.getTime() + i)),
            e = String(e),
            document.cookie = [encodeURIComponent(t), "=", n.raw ? e : encodeURIComponent(e), n.expires ? "; expires=" + n.expires.toUTCString() : "", n.path ? "; path=" + n.path : "", n.domain ? "; domain=" + n.domain : "", n.secure ? "; secure" : ""].join("")) : (n = e || {},
            r = n.raw ? function(t) {
                return t
            }
            : decodeURIComponent,
            (o = new RegExp("(?:^|; )" + encodeURIComponent(t) + "=([^;]*)").exec(document.cookie)) ? r(o[1]) : null)
        }
    })
}(Zepto),
function($) {
    $.extend($.fn, {
        support_storage: function() {
            var t = "sm.router.storage.ability";
            try {
                return window.sessionStorage.setItem(t, t),
                window.sessionStorage.removeItem(t),
                !0
            } catch (t) {
                return !1
            }
        },
        session_set: function(t, e) {
            window.sessionStorage.setItem(t, e)
        },
        session_get: function(t) {
            return window.sessionStorage.getItem(t)
        },
        session_remove: function(t) {
            window.sessionStorage.removeItem(t)
        }
    })
}(Zepto),
function($) {
    "use strict";
    $.compareVersion = function(t, e) {
        var n = t.split(".")
          , i = e.split(".");
        if (t === e)
            return 0;
        for (var a = 0; a < n.length; a++) {
            var o = parseInt(n[a]);
            if (!i[a])
                return 1;
            var r = parseInt(i[a]);
            if (o < r)
                return -1;
            if (o > r)
                return 1
        }
        return -1
    }
    ,
    $.getCurrentPage = function() {
        return $(".page-current")[0] || $(".page")[0] || document.body
    }
}(Zepto),
function($) {
    "use strict";
    function t(t, e) {
        function n(t) {
            if (t.target === this)
                for (e.call(this, t),
                i = 0; i < a.length; i++)
                    o.off(a[i], n)
        }
        var i, a = t, o = this;
        if (e)
            for (i = 0; i < a.length; i++)
                o.on(a[i], n)
    }
    ["width", "height"].forEach(function(t) {
        var e = t.replace(/./, function(t) {
            return t[0].toUpperCase()
        });
        $.fn["outer" + e] = function(e) {
            var n = this;
            if (n) {
                var i = n[t]();
                return {
                    width: ["left", "right"],
                    height: ["top", "bottom"]
                }[t].forEach(function(t) {
                    e && (i += parseInt(n.css("margin-" + t), 10))
                }),
                i
            }
            return null
        }
    }),
    $.support = function() {
        return {
            touch: !!("ontouchstart"in window || window.DocumentTouch && document instanceof window.DocumentTouch)
        }
    }(),
    $.touchEvents = {
        start: $.support.touch ? "touchstart" : "mousedown",
        move: $.support.touch ? "touchmove" : "mousemove",
        end: $.support.touch ? "touchend" : "mouseup"
    },
    $.getTranslate = function(t, e) {
        var n, i, a, o;
        return void 0 === e && (e = "x"),
        a = window.getComputedStyle(t, null),
        window.WebKitCSSMatrix ? o = new WebKitCSSMatrix("none" === a.webkitTransform ? "" : a.webkitTransform) : (o = a.MozTransform || a.transform || a.getPropertyValue("transform").replace("translate(", "matrix(1, 0, 0, 1,"),
        n = o.toString().split(",")),
        "x" === e && (i = window.WebKitCSSMatrix ? o.m41 : 16 === n.length ? parseFloat(n[12]) : parseFloat(n[4])),
        "y" === e && (i = window.WebKitCSSMatrix ? o.m42 : 16 === n.length ? parseFloat(n[13]) : parseFloat(n[5])),
        i || 0
    }
    ,
    $.requestAnimationFrame = function(t) {
        return window.requestAnimationFrame ? window.requestAnimationFrame(t) : window.webkitRequestAnimationFrame ? window.webkitRequestAnimationFrame(t) : window.mozRequestAnimationFrame ? window.mozRequestAnimationFrame(t) : window.setTimeout(t, 1e3 / 60)
    }
    ,
    $.cancelAnimationFrame = function(t) {
        return window.cancelAnimationFrame ? window.cancelAnimationFrame(t) : window.webkitCancelAnimationFrame ? window.webkitCancelAnimationFrame(t) : window.mozCancelAnimationFrame ? window.mozCancelAnimationFrame(t) : window.clearTimeout(t)
    }
    ,
    $.fn.dataset = function() {
        var t = {}
          , e = this[0].dataset;
        for (var n in e) {
            var i = t[n] = e[n];
            "false" === i ? t[n] = !1 : "true" === i ? t[n] = !0 : parseFloat(i) === 1 * i && (t[n] = 1 * i)
        }
        return $.extend({}, t, this[0].__eleData)
    }
    ,
    $.fn.data = function(t, e) {
        var n = $(this).dataset();
        if (!t)
            return n;
        if (void 0 === e) {
            var i = n[t]
              , a = this[0].__eleData;
            return a && t in a ? a[t] : i
        }
        for (var o = 0; o < this.length; o++) {
            var r = this[o];
            t in n && delete r.dataset[t],
            r.__eleData || (r.__eleData = {}),
            r.__eleData[t] = e
        }
        return this
    }
    ,
    $.fn.animationEnd = function(e) {
        return t.call(this, ["webkitAnimationEnd", "animationend"], e),
        this
    }
    ,
    $.fn.transitionEnd = function(e) {
        return t.call(this, ["webkitTransitionEnd", "transitionend"], e),
        this
    }
    ,
    $.fn.transition = function(t) {
        "string" != typeof t && (t += "ms");
        for (var e = 0; e < this.length; e++) {
            var n = this[e].style;
            n.webkitTransitionDuration = n.MozTransitionDuration = n.transitionDuration = t
        }
        return this
    }
    ,
    $.fn.transform = function(t) {
        for (var e = 0; e < this.length; e++) {
            var n = this[e].style;
            n.webkitTransform = n.MozTransform = n.transform = t
        }
        return this
    }
    ,
    $.fn.prevAll = function(t) {
        var e = []
          , n = this[0];
        if (!n)
            return $([]);
        for (; n.previousElementSibling; ) {
            var i = n.previousElementSibling;
            t ? $(i).is(t) && e.push(i) : e.push(i),
            n = i
        }
        return $(e)
    }
    ,
    $.fn.nextAll = function(t) {
        var e = []
          , n = this[0];
        if (!n)
            return $([]);
        for (; n.nextElementSibling; ) {
            var i = n.nextElementSibling;
            t ? $(i).is(t) && e.push(i) : e.push(i),
            n = i
        }
        return $(e)
    }
    ,
    $.fn.show = function() {
        function t(t) {
            var n, i;
            return e[t] || (n = document.createElement(t),
            document.body.appendChild(n),
            i = getComputedStyle(n, "").getPropertyValue("display"),
            n.parentNode.removeChild(n),
            "none" === i && (i = "block"),
            e[t] = i),
            e[t]
        }
        var e = {};
        return this.each(function() {
            "none" === this.style.display && (this.style.display = ""),
            getComputedStyle(this, "").getPropertyValue("display"),
            this.style.display = t(this.nodeName)
        })
    }
}(Zepto),
function($) {
    "use strict";
    var t = {}
      , e = navigator.userAgent
      , n = e.match(/(Android);?[\s\/]+([\d.]+)?/)
      , i = e.match(/(iPad).*OS\s([\d_]+)/)
      , a = e.match(/(iPod)(.*OS\s([\d_]+))?/)
      , o = !i && e.match(/(iPhone\sOS)\s([\d_]+)/);
    if (t.ios = t.android = t.iphone = t.ipad = t.androidChrome = !1,
    n && (t.os = "android",
    t.osVersion = n[2],
    t.android = !0,
    t.androidChrome = e.toLowerCase().indexOf("chrome") >= 0),
    (i || o || a) && (t.os = "ios",
    t.ios = !0),
    o && !a && (t.osVersion = o[2].replace(/_/g, "."),
    t.iphone = !0),
    i && (t.osVersion = i[2].replace(/_/g, "."),
    t.ipad = !0),
    a && (t.osVersion = a[3] ? a[3].replace(/_/g, ".") : null,
    t.iphone = !0),
    t.ios && t.osVersion && e.indexOf("Version/") >= 0 && "10" === t.osVersion.split(".")[0] && (t.osVersion = e.toLowerCase().split("version/")[1].split(" ")[0]),
    t.webView = (o || i || a) && e.match(/.*AppleWebKit(?!.*Safari)/i),
    t.os && "ios" === t.os) {
        var r = t.osVersion.split(".");
        t.minimalUi = !t.webView && (a || o) && (1 * r[0] == 7 ? 1 * r[1] >= 1 : 1 * r[0] > 7) && $('meta[name="viewport"]').length > 0 && $('meta[name="viewport"]').attr("content").indexOf("minimal-ui") >= 0
    }
    var s = $(window).width()
      , l = $(window).height();
    t.statusBar = !1,
    t.webView && s * l == screen.width * screen.height ? t.statusBar = !0 : t.statusBar = !1;
    var c = [];
    if (t.pixelRatio = window.devicePixelRatio || 1,
    c.push("pixel-ratio-" + Math.floor(t.pixelRatio)),
    t.pixelRatio >= 2 && c.push("retina"),
    t.os && (c.push(t.os, t.os + "-" + t.osVersion.split(".")[0], t.os + "-" + t.osVersion.replace(/\./g, "-")),
    "ios" === t.os))
        for (var h = parseInt(t.osVersion.split(".")[0], 10), p = h - 1; p >= 6; p--)
            c.push("ios-gt-" + p);
    t.statusBar ? c.push("with-statusbar-overlay") : $("html").removeClass("with-statusbar-overlay"),
    c.length > 0 && $("html").addClass(c.join(" ")),
    t.isWeixin = /MicroMessenger/i.test(e),
    t.isAlipay = /AlipayClient/i.test(e),
    t.isApp = -1 != e.indexOf("app_ios") || -1 != e.indexOf("app_android"),
    $.device = t
}(Zepto),
function() {
    "use strict";
    function t(e, i) {
        var a;
        if (i = i || {},
        this.trackingClick = !1,
        this.trackingClickStart = 0,
        this.targetElement = null,
        this.touchStartX = 0,
        this.touchStartY = 0,
        this.lastTouchIdentifier = 0,
        this.touchBoundary = i.touchBoundary || 10,
        this.layer = e,
        this.tapDelay = i.tapDelay || 200,
        this.tapTimeout = i.tapTimeout || 700,
        !t.notNeeded(e)) {
            for (var o = ["onMouse", "onClick", "onTouchStart", "onTouchMove", "onTouchEnd", "onTouchCancel"], r = this, s = 0, l = o.length; s < l; s++)
                r[o[s]] = function(t, e) {
                    return function() {
                        return t.apply(e, arguments)
                    }
                }(r[o[s]], r);
            n && (e.addEventListener("mouseover", this.onMouse, !0),
            e.addEventListener("mousedown", this.onMouse, !0),
            e.addEventListener("mouseup", this.onMouse, !0)),
            e.addEventListener("click", this.onClick, !0),
            e.addEventListener("touchstart", this.onTouchStart, !1),
            e.addEventListener("touchmove", this.onTouchMove, !1),
            e.addEventListener("touchend", this.onTouchEnd, !1),
            e.addEventListener("touchcancel", this.onTouchCancel, !1),
            Event.prototype.stopImmediatePropagation || (e.removeEventListener = function(t, n, i) {
                var a = Node.prototype.removeEventListener;
                "click" === t ? a.call(e, t, n.hijacked || n, i) : a.call(e, t, n, i)
            }
            ,
            e.addEventListener = function(t, n, i) {
                var a = Node.prototype.addEventListener;
                "click" === t ? a.call(e, t, n.hijacked || (n.hijacked = function(t) {
                    t.propagationStopped || n(t)
                }
                ), i) : a.call(e, t, n, i)
            }
            ),
            "function" == typeof e.onclick && (a = e.onclick,
            e.addEventListener("click", function(t) {
                a(t)
            }, !1),
            e.onclick = null)
        }
    }
    var e = navigator.userAgent.indexOf("Windows Phone") >= 0
      , n = navigator.userAgent.indexOf("Android") > 0 && !e
      , i = /iP(ad|hone|od)/.test(navigator.userAgent) && !e
      , a = i && /OS 4_\d(_\d)?/.test(navigator.userAgent)
      , o = i && /OS [6-7]_\d/.test(navigator.userAgent)
      , r = navigator.userAgent.indexOf("BB10") > 0
      , s = !1;
    t.prototype.needsClick = function(t) {
        for (var e = t; e && "BODY" !== e.tagName.toUpperCase(); ) {
            if ("LABEL" === e.tagName.toUpperCase() && (s = !0,
            /\bneedsclick\b/.test(e.className)))
                return !0;
            e = e.parentNode
        }
        switch (t.nodeName.toLowerCase()) {
        case "button":
        case "select":
        case "textarea":
            if (t.disabled)
                return !0;
            break;
        case "input":
            if (i && "file" === t.type || t.disabled)
                return !0;
            break;
        case "label":
        case "iframe":
        case "video":
            return !0
        }
        return /\bneedsclick\b/.test(t.className)
    }
    ,
    t.prototype.needsFocus = function(t) {
        switch (t.nodeName.toLowerCase()) {
        case "textarea":
            return !0;
        case "select":
            return !n;
        case "input":
            switch (t.type) {
            case "button":
            case "checkbox":
            case "file":
            case "image":
            case "radio":
            case "submit":
                return !1
            }
            return !t.disabled && !t.readOnly;
        default:
            return /\bneedsfocus\b/.test(t.className)
        }
    }
    ,
    t.prototype.sendClick = function(t, e) {
        var n, i;
        document.activeElement && document.activeElement !== t && document.activeElement.blur(),
        i = e.changedTouches[0],
        n = document.createEvent("MouseEvents"),
        n.initMouseEvent(this.determineEventType(t), !0, !0, window, 1, i.screenX, i.screenY, i.clientX, i.clientY, !1, !1, !1, !1, 0, null),
        n.forwardedTouchEvent = !0,
        t.dispatchEvent(n)
    }
    ,
    t.prototype.determineEventType = function(t) {
        return n && "select" === t.tagName.toLowerCase() ? "mousedown" : "click"
    }
    ,
    t.prototype.focus = function(t) {
        var e, n = ["date", "time", "month", "number", "email"];
        i && t.setSelectionRange && -1 === n.indexOf(t.type) ? (e = t.value.length,
        t.setSelectionRange(e, e)) : t.focus()
    }
    ,
    t.prototype.updateScrollParent = function(t) {
        var e, n;
        if (!(e = t.fastClickScrollParent) || !e.contains(t)) {
            n = t;
            do {
                if (n.scrollHeight > n.offsetHeight) {
                    e = n,
                    t.fastClickScrollParent = n;
                    break
                }
                n = n.parentElement
            } while (n)
        }
        e && (e.fastClickLastScrollTop = e.scrollTop)
    }
    ,
    t.prototype.getTargetElementFromEventTarget = function(t) {
        return t.nodeType === Node.TEXT_NODE ? t.parentNode : t
    }
    ,
    t.prototype.onTouchStart = function(t) {
        var e, n, o;
        if (t.targetTouches.length > 1)
            return !0;
        if (e = this.getTargetElementFromEventTarget(t.target),
        n = t.targetTouches[0],
        i) {
            if (o = window.getSelection(),
            o.rangeCount && !o.isCollapsed)
                return !0;
            if (!a) {
                if (n.identifier && n.identifier === this.lastTouchIdentifier)
                    return t.preventDefault(),
                    !1;
                this.lastTouchIdentifier = n.identifier,
                this.updateScrollParent(e)
            }
        }
        return this.trackingClick = !0,
        this.trackingClickStart = t.timeStamp,
        this.targetElement = e,
        this.touchStartX = n.pageX,
        this.touchStartY = n.pageY,
        t.timeStamp - this.lastClickTime < this.tapDelay && t.preventDefault(),
        !0
    }
    ,
    t.prototype.touchHasMoved = function(t) {
        var e = t.changedTouches[0]
          , n = this.touchBoundary;
        return Math.abs(e.pageX - this.touchStartX) > n || Math.abs(e.pageY - this.touchStartY) > n
    }
    ,
    t.prototype.onTouchMove = function(t) {
        return !this.trackingClick || ((this.targetElement !== this.getTargetElementFromEventTarget(t.target) || this.touchHasMoved(t)) && (this.trackingClick = !1,
        this.targetElement = null),
        !0)
    }
    ,
    t.prototype.findControl = function(t) {
        return void 0 !== t.control ? t.control : t.htmlFor ? document.getElementById(t.htmlFor) : t.querySelector("button, input:not([type=hidden]), keygen, meter, output, progress, select, textarea")
    }
    ,
    t.prototype.onTouchEnd = function(t) {
        var e, r, s, l, c, h = this.targetElement;
        if (!this.trackingClick)
            return !0;
        if (t.timeStamp - this.lastClickTime < this.tapDelay)
            return this.cancelNextClick = !0,
            !0;
        if (t.timeStamp - this.trackingClickStart > this.tapTimeout)
            return !0;
        if (-1 !== ["date", "time", "month"].indexOf(t.target.type))
            return !1;
        if (this.cancelNextClick = !1,
        this.lastClickTime = t.timeStamp,
        r = this.trackingClickStart,
        this.trackingClick = !1,
        this.trackingClickStart = 0,
        o && (c = t.changedTouches[0],
        h = document.elementFromPoint(c.pageX - window.pageXOffset, c.pageY - window.pageYOffset) || h,
        h.fastClickScrollParent = this.targetElement.fastClickScrollParent),
        "label" === (s = h.tagName.toLowerCase())) {
            if (e = this.findControl(h)) {
                if (this.focus(h),
                n)
                    return !1;
                h = e
            }
        } else if (this.needsFocus(h))
            return t.timeStamp - r > 100 || i && window.top !== window && "input" === s ? (this.targetElement = null,
            !1) : (this.focus(h),
            this.sendClick(h, t),
            i && "select" === s || (this.targetElement = null,
            t.preventDefault()),
            !1);
        return !(!i || a || !(l = h.fastClickScrollParent) || l.fastClickLastScrollTop === l.scrollTop) || (this.needsClick(h) || (t.preventDefault(),
        this.sendClick(h, t)),
        !1)
    }
    ,
    t.prototype.onTouchCancel = function() {
        this.trackingClick = !1,
        this.targetElement = null
    }
    ,
    t.prototype.onMouse = function(t) {
        return !this.targetElement || (!!t.forwardedTouchEvent || (!t.cancelable || (!(!this.needsClick(this.targetElement) || this.cancelNextClick) || (t.stopImmediatePropagation ? t.stopImmediatePropagation() : t.propagationStopped = !0,
        t.stopPropagation(),
        s || t.preventDefault(),
        !1))))
    }
    ,
    t.prototype.onClick = function(t) {
        var e;
        return this.trackingClick ? (this.targetElement = null,
        this.trackingClick = !1,
        !0) : "submit" === t.target.type && 0 === t.detail || (e = this.onMouse(t),
        e || (this.targetElement = null),
        e)
    }
    ,
    t.prototype.destroy = function() {
        var t = this.layer;
        n && (t.removeEventListener("mouseover", this.onMouse, !0),
        t.removeEventListener("mousedown", this.onMouse, !0),
        t.removeEventListener("mouseup", this.onMouse, !0)),
        t.removeEventListener("click", this.onClick, !0),
        t.removeEventListener("touchstart", this.onTouchStart, !1),
        t.removeEventListener("touchmove", this.onTouchMove, !1),
        t.removeEventListener("touchend", this.onTouchEnd, !1),
        t.removeEventListener("touchcancel", this.onTouchCancel, !1)
    }
    ,
    t.notNeeded = function(t) {
        var e, i, a;
        if (void 0 === window.ontouchstart)
            return !0;
        if (i = +(/Chrome\/([0-9]+)/.exec(navigator.userAgent) || [, 0])[1]) {
            if (!n)
                return !0;
            if (e = document.querySelector("meta[name=viewport]")) {
                if (-1 !== e.content.indexOf("user-scalable=no"))
                    return !0;
                if (i > 31 && document.documentElement.scrollWidth <= window.outerWidth)
                    return !0
            }
        }
        if (r && (a = navigator.userAgent.match(/Version\/([0-9]*)\.([0-9]*)/),
        a[1] >= 10 && a[2] >= 3 && (e = document.querySelector("meta[name=viewport]")))) {
            if (-1 !== e.content.indexOf("user-scalable=no"))
                return !0;
            if (document.documentElement.scrollWidth <= window.outerWidth)
                return !0
        }
        return "none" === t.style.msTouchAction || "manipulation" === t.style.touchAction || (!!(+(/Firefox\/([0-9]+)/.exec(navigator.userAgent) || [, 0])[1] >= 27 && (e = document.querySelector("meta[name=viewport]")) && (-1 !== e.content.indexOf("user-scalable=no") || document.documentElement.scrollWidth <= window.outerWidth)) || ("none" === t.style.touchAction || "manipulation" === t.style.touchAction))
    }
    ,
    t.attach = function(e, n) {
        return new t(e,n)
    }
    ,
    window.FastClick = t
}(),
function($) {
    "use strict";
    function t(t) {
        var e, i = $(this), a = (i.attr("href"),
        i.dataset());
        if (i.hasClass("open-popup") && (e = a.popup ? a.popup : ".popup",
        $.popup(e)),
        i.hasClass("close-popup") && (e = a.popup ? a.popup : ".popup.modal-in",
        $.closeModal(e)),
        i.hasClass("modal-overlay")) {
            var o = $(".modal.modal-in");
            o && o.length > 0 && o.hasClass("modal-closeable") ? $.closeModal(".modal.modal-in") : o && o.length > 0 && n.modalCloseByOutside && $.closeModal(".modal.modal-in");
            var r = $(".actions-modal.modal-in");
            r && r.length > 0 && r.hasClass("modal-closeable") ? $.closeModal(".actions-modal.modal-in") : r && r.length > 0 && n.actionsCloseByOutside && $.closeModal(".actions-modal.modal-in")
        }
        i.hasClass("popup-overlay") && $(".popup.modal-in").length > 0 && n.popupCloseByOutside && $.closeModal(".popup.modal-in")
    }
    var e = document.createElement("div");
    $.modalStack = [],
    $.modalStackClearQueue = function() {
        $.modalStack.length && $.modalStack.shift()()
    }
    ,
    $.modal = function(t) {
        t = t || {};
        var i = ""
          , a = "";
        if (t.buttons && t.buttons.length > 0)
            for (var o = 0; o < t.buttons.length; o++)
                a += '<span class="modal-button' + (t.buttons[o].bold ? " modal-button-bold" : "") + '">' + t.buttons[o].text + "</span>";
        var r = t.extraClass || ""
          , s = t.title ? '<div class="modal-title">' + t.title + "</div>" : ""
          , l = t.text ? '<div class="modal-text">' + t.text + "</div>" : ""
          , c = t.afterText ? t.afterText : "";
        i = '<div class="modal ' + r + " " + (t.buttons && 0 !== t.buttons.length ? "" : "modal-no-buttons") + '"><div class="modal-inner">' + (s + l + c) + '</div><div class="modal-buttons ' + (t.verticalButtons ? "modal-buttons-vertical" : "") + '">' + a + "</div></div>",
        e.innerHTML = i;
        var h = $(e).children();
        return $(n.modalContainer).append(h[0]),
        h.find(".modal-button").each(function(e, n) {
            $(n).on("click", function(n) {
                !1 !== t.buttons[e].close && $.closeModal(h),
                t.buttons[e].onClick && t.buttons[e].onClick(h, n),
                t.onClick && t.onClick(h, e)
            })
        }),
        $.openModal(h),
        h[0]
    }
    ,
    $.alert = function(t, e, i) {
        return "function" == typeof e && (i = arguments[1],
        e = void 0),
        $.modal({
            text: t || "",
            title: void 0 === e ? n.modalTitle : e,
            buttons: [{
                text: n.modalButtonOk,
                bold: !0,
                onClick: i
            }]
        })
    }
    ,
    $.confirm = function(t, e, i, a) {
        return "function" == typeof e && (a = arguments[2],
        i = arguments[1],
        e = void 0),
        $.modal({
            text: t || "",
            title: void 0 === e ? n.modalTitle : e,
            buttons: [{
                text: n.modalButtonCancel,
                onClick: a
            }, {
                text: n.modalButtonOk,
                bold: !0,
                onClick: i
            }]
        })
    }
    ,
    $.prompt = function(t, e, i, a) {
        return "function" == typeof e && (a = arguments[2],
        i = arguments[1],
        e = void 0),
        $.modal({
            text: t || "",
            title: void 0 === e ? n.modalTitle : e,
            afterText: '<input type="text" class="modal-text-input">',
            buttons: [{
                text: n.modalButtonCancel
            }, {
                text: n.modalButtonOk,
                bold: !0
            }],
            onClick: function(t, e) {
                0 === e && a && a($(t).find(".modal-text-input").val()),
                1 === e && i && i($(t).find(".modal-text-input").val())
            }
        })
    }
    ,
    $.modalLogin = function(t, e, i, a) {
        return "function" == typeof e && (a = arguments[2],
        i = arguments[1],
        e = void 0),
        $.modal({
            text: t || "",
            title: void 0 === e ? n.modalTitle : e,
            afterText: '<input type="text" name="modal-username" placeholder="' + n.modalUsernamePlaceholder + '" class="modal-text-input modal-text-input-double"><input type="password" name="modal-password" placeholder="' + n.modalPasswordPlaceholder + '" class="modal-text-input modal-text-input-double">',
            buttons: [{
                text: n.modalButtonCancel
            }, {
                text: n.modalButtonOk,
                bold: !0
            }],
            onClick: function(t, e) {
                var n = $(t).find('.modal-text-input[name="modal-username"]').val()
                  , o = $(t).find('.modal-text-input[name="modal-password"]').val();
                0 === e && a && a(n, o),
                1 === e && i && i(n, o)
            }
        })
    }
    ,
    $.modalPassword = function(t, e, i, a) {
        return "function" == typeof e && (a = arguments[2],
        i = arguments[1],
        e = void 0),
        $.modal({
            text: t || "",
            title: void 0 === e ? n.modalTitle : e,
            afterText: '<input type="password" name="modal-password" placeholder="' + n.modalPasswordPlaceholder + '" class="modal-text-input">',
            buttons: [{
                text: n.modalButtonCancel
            }, {
                text: n.modalButtonOk,
                bold: !0
            }],
            onClick: function(t, e) {
                var n = $(t).find('.modal-text-input[name="modal-password"]').val();
                0 === e && a && a(n),
                1 === e && i && i(n)
            }
        })
    }
    ,
    $.showPreloader = function(t) {
        return $.hidePreloader(),
        $.showPreloader.preloaderModal = $.modal({
            title: t || n.modalPreloaderTitle,
            text: '<div class="preloader"></div>'
        }),
        $.showPreloader.preloaderModal
    }
    ,
    $.hidePreloader = function() {
        $.showPreloader.preloaderModal && $.closeModal($.showPreloader.preloaderModal)
    }
    ,
    $.showIndicator = function() {
        $(".preloader-indicator-modal")[0] || $(n.modalContainer).append('<div class="preloader-indicator-overlay"></div><div class="preloader-indicator-modal"><span class="preloader preloader-white"></span></div>')
    }
    ,
    $.hideIndicator = function() {
        $(".preloader-indicator-overlay, .preloader-indicator-modal").remove()
    }
    ,
    $.showSwitchLoader = function() {
        $(".switch-loader-modal")[0] || $("body").append('<div class="switch-loader-overlay"></div><div class="switch-loader-modal"><div class="loader"></div></div>')
    }
    ,
    $.hideSwitchLoader = function() {
        $(".switch-loader-overlay, .switch-loader-modal").remove()
    }
    ,
    $.hideMainPageLoader = function() {
        $(".preloader-main-indicator-overlay").remove(),
        $(".preloader-main-indicator-modal").remove(),
        $(".biz-indicator-overlay, .biz-indicator-modal").remove(),
        $.smConfig.showPageLoadingCustomer ? $.hideSwitchLoader() : $.hideIndicator()
    }
    ,
    $.showBizIndicator = function() {
        $(".biz-indicator-modal")[0] || $(".page").append('<div class="biz-indicator-overlay"></div><div class="biz-indicator-modal"><div class="loader"></div></div>')
    }
    ,
    $.hideBizIndicator = function() {
        $(".biz-indicator-overlay, .biz-indicator-modal").remove()
    }
    ,
    $.showMessageIndicator = function(t) {
        if (!$(".message-indicator-overlay")[0]) {
            var e = $('<div style="min-width:6.82rem;padding:0.6rem 0.95rem !important;opacity:0.55;" class="message-indicator-modal preloader-indicator-modal"><div class="preloader-div"><span style="display:inline-block;width:2rem;height:2rem;" class="preloader preloader-white"></span></div><div style="text-align:center;" class="message-font">' + (t ? t + "..." : "正在处理中...") + "</div></div>")
              , n = $('<div class="message-indicator-overlay"></div>');
            n.append(e),
            $(".page").append(n),
            e.css({
                marginLeft: -Math.round(e.outerWidth() / 2 / 1.185) - 11 + "px"
            })
        }
    }
    ,
    $.hideMessageIndicator = function() {
        $(".message-indicator-overlay, .message-indicator-modal").remove()
    }
    ,
    $.actions = function(t) {
        var i, a, o;
        t = t || [],
        t.length > 0 && !$.isArray(t[0]) && (t = [t]);
        for (var r, s = "", l = 0; l < t.length; l++)
            for (var c = 0; c < t[l].length; c++) {
                0 === c && (s += '<div class="actions-modal-group">');
                var h = t[l][c]
                  , p = h.label ? "actions-modal-label" : "actions-modal-button";
                h.bold && (p += " actions-modal-button-bold"),
                h.color && (p += " color-" + h.color),
                h.bg && (p += " bg-" + h.bg),
                h.disabled && (p += " disabled"),
                s += '<span class="' + p + '">' + h.text + "</span>",
                c === t[l].length - 1 && (s += "</div>")
            }
        return r = '<div class="actions-modal">' + s + "</div>",
        e.innerHTML = r,
        i = $(e).children(),
        $(n.modalContainer).append(i[0]),
        a = ".actions-modal-group",
        o = ".actions-modal-button",
        i.find(a).each(function(e, n) {
            var a = e;
            $(n).children().each(function(e, n) {
                var r, s = e, l = t[a][s];
                $(n).is(o) && (r = $(n)),
                r && r.on("click", function(t) {
                    !1 !== l.close && $.closeModal(i),
                    l.onClick && l.onClick(i, t)
                })
            })
        }),
        $.openModal(i),
        i[0]
    }
    ,
    $.popup = function(t, e) {
        if (void 0 === e && (e = !0),
        "string" == typeof t && t.indexOf("<") >= 0) {
            var i = document.createElement("div");
            if (i.innerHTML = t.trim(),
            !(i.childNodes.length > 0))
                return !1;
            t = i.childNodes[0],
            e && t.classList.add("remove-on-close"),
            $(n.modalContainer).append(t)
        }
        return t = $(t),
        0 !== t.length && (t.show(),
        t.find(".content").scroller("refresh"),
        t.find("." + n.viewClass).length > 0 && $.sizeNavbars(t.find("." + n.viewClass)[0]),
        $.openModal(t),
        t[0])
    }
    ,
    $.pickerModal = function(t, e) {
        if (void 0 === e && (e = !0),
        "string" == typeof t && t.indexOf("<") >= 0) {
            if (t = $(t),
            !(t.length > 0))
                return !1;
            e && t.addClass("remove-on-close"),
            $(n.modalContainer).append(t[0])
        }
        return t = $(t),
        0 !== t.length && (t.show(),
        $.openModal(t),
        t[0])
    }
    ,
    $.loginScreen = function(t) {
        return t || (t = ".login-screen"),
        t = $(t),
        0 !== t.length && (t.show(),
        t.find("." + n.viewClass).length > 0 && $.sizeNavbars(t.find("." + n.viewClass)[0]),
        $.openModal(t),
        t[0])
    }
    ,
    $.toast = function(t, e, n) {
        var i = $('<div class="modal toast ' + (n || "") + '">' + t + "</div>").appendTo(document.body);
        $.openModal(i, function() {
            setTimeout(function() {
                $.closeModal(i)
            }, e || 2e3)
        })
    }
    ,
    $.alertMessage = function(t, e, n, i) {
        var a = $('<div class="modal toast toast-message modal-alert ' + (i || "") + '">' + t + "</div>").appendTo($(".page"));
        $(".page").append('<div class="modal-alert-overlay modal-overlay-visible modal-alert"></div>'),
        $.openModal(a, function() {
            setTimeout(function() {
                $(".toast-message").remove(),
                $(".modal-alert").remove(),
                "function" == typeof e && e()
            }, n || 2e3)
        })
    }
    ,
    $.alertClosableMessage = function(t, e, n) {
        var i = $('<div class="modal toast toast-message modal-alert modal-alert-close"><div class="alert-msg-container">' + t + '</div><span class="alert-msg-toast"><a class="alert-msg-toast-ico alert-msg-toast-a">X</a></span></div>').appendTo($(".page"));
        $(".page").append('<div class="modal-alert-overlay modal-overlay-visible modal-alert"></div>'),
        i.find(".alert-msg-toast-a").click(function() {
            $(".toast-message").remove(),
            $(".modal-alert").remove(),
            "function" == typeof e && e()
        }),
        $.openModal(i, function() {})
    }
    ,
    $.alertReloadMessage = function(t) {
        var e = $('<div class="modal toast toast-message modal-alert modal-alert-reload"><div class="alert-msg-container">' + t + '</div><span class="alert-msg-toast"><a class="alert-msg-toast-ico alert-msg-toast-a"><span class="alert-reload"><span class="button button-middle button-fill button-customer-h5">重新登录</span></span></a></span></div>').appendTo($(".page"));
        $(".page").append('<div class="modal-alert-overlay modal-overlay-visible modal-alert"></div>'),
        e.find(".alert-msg-toast-a .button").click(function() {
            $(".toast-message").remove(),
            $(".modal-alert").remove(),
            window.location.reload()
        }),
        $.openModal(e, function() {})
    }
    ,
    $.openModal = function(t, e) {
        t = $(t);
        var i = t.hasClass("modal")
          , a = !t.hasClass("toast");
        if ($(".modal.modal-in:not(.modal-out)").length && n.modalStack && i && a)
            return void $.modalStack.push(function() {
                $.openModal(t, e)
            });
        var o = t.hasClass("popup")
          , r = t.hasClass("login-screen")
          , s = t.hasClass("picker-modal")
          , l = t.hasClass("toast");
        i && (t.show(),
        t.css({
            marginTop: -Math.round(t[0].clientHeight / 2) + "px"
        })),
        l && t.css({
            marginLeft: -Math.round(t.outerWidth() / 2 / 1.185) + "px"
        });
        var c;
        r || s || l || (0 !== $(".modal-overlay").length || o || $(n.modalContainer).append('<div class="modal-overlay"></div>'),
        0 === $(".popup-overlay").length && o && $(n.modalContainer).append('<div class="popup-overlay"></div>'),
        c = $(o ? ".popup-overlay" : ".modal-overlay"));
        t[0].clientLeft;
        return t.trigger("open"),
        s && $(n.modalContainer).addClass("with-picker-modal"),
        r || s || l || c.addClass("modal-overlay-visible"),
        t.removeClass("modal-out").addClass("modal-in").transitionEnd(function(e) {
            t.hasClass("modal-out") ? t.trigger("closed") : t.trigger("opened")
        }),
        "function" == typeof e && e.call(this),
        !0
    }
    ,
    $.openModalNew = function(t, e) {
        t = $(t);
        var i = t.hasClass("modal")
          , a = !t.hasClass("toast");
        if ($(".modal.modal-in:not(.modal-out)").length && n.modalStack && i && a)
            return void $.modalStack.push(function() {
                $.openModal(t, e)
            });
        var o = t.hasClass("popup")
          , r = t.hasClass("login-screen")
          , s = t.hasClass("picker-modal")
          , l = t.hasClass("toast");
        i && (t.show(),
        t.css({
            marginTop: NaN
        })),
        l && t.css({
            marginLeft: -Math.round(t.outerWidth() / 2 / 1.185) + "px"
        });
        var c;
        r || s || l || (0 !== $(".modal-overlay").length || o || $(n.modalContainer).append('<div class="modal-overlay"></div>'),
        0 === $(".popup-overlay").length && o && $(n.modalContainer).append('<div class="popup-overlay"></div>'),
        c = $(o ? ".popup-overlay" : ".modal-overlay"));
        t[0].clientLeft;
        return t.trigger("open"),
        s && $(n.modalContainer).addClass("with-picker-modal"),
        r || s || l || c.addClass("modal-overlay-visible"),
        0 == $(".modal").length && ($(".page").append(t),
        $(".behalfdrive-photomodal").css("margin-top", "-157px")),
        t.removeClass("modal-out").addClass("modal-in").transitionEnd(function(e) {
            t.hasClass("modal-out") ? t.trigger("closed") : t.trigger("opened")
        }),
        "function" == typeof e && e.call(this),
        !0
    }
    ,
    $.closeModal = function(t) {
        if (void 0 === (t = $(t || ".modal-in")) || 0 !== t.length) {
            var e = t.hasClass("modal")
              , i = t.hasClass("popup")
              , a = t.hasClass("toast")
              , o = t.hasClass("login-screen")
              , r = t.hasClass("picker-modal")
              , s = t.hasClass("remove-on-close")
              , l = $(i ? ".popup-overlay" : ".modal-overlay");
            return i ? t.length === $(".popup.modal-in").length && l.removeClass("modal-overlay-visible") : r || a || l.removeClass("modal-overlay-visible"),
            t.trigger("close"),
            r && ($(n.modalContainer).removeClass("with-picker-modal"),
            $(n.modalContainer).addClass("picker-modal-closing")),
            t.removeClass("modal-in").addClass("modal-out").transitionEnd(function(e) {
                t.hasClass("modal-out") ? t.trigger("closed") : t.trigger("opened"),
                r && $(n.modalContainer).removeClass("picker-modal-closing"),
                i || o || r ? (t.removeClass("modal-out").hide(),
                s && t.length > 0 && t.remove()) : t.remove()
            }),
            e && n.modalStack && $.modalStackClearQueue(),
            !0
        }
    }
    ,
    $(document).on("click", " .modal-overlay, .popup-overlay, .close-popup, .open-popup, .close-picker", t);
    var n = $.modal.prototype.defaults = {
        modalStack: !0,
        modalButtonOk: "确定",
        modalButtonCancel: "取消",
        modalPreloaderTitle: "加载中",
        modalContainer: ".page"
    }
}(Zepto),
function($) {
    "use strict";
    var t = !1
      , e = function(e) {
        function n(t) {
            t = new Date(t);
            var e = t.getFullYear()
              , n = t.getMonth()
              , i = n + 1
              , a = t.getDate()
              , o = t.getDay();
            return r.params.dateFormat.replace(/yyyy/g, e).replace(/yy/g, (e + "").substring(2)).replace(/mm/g, i < 10 ? "0" + i : i).replace(/m/g, i).replace(/MM/g, r.params.monthNames[n]).replace(/M/g, r.params.monthNamesShort[n]).replace(/dd/g, a < 10 ? "0" + a : a).replace(/d/g, a).replace(/DD/g, r.params.dayNames[o]).replace(/D/g, r.params.dayNamesShort[o])
        }
        function i(t) {
            if (t.preventDefault(),
            $.device.isWeixin && $.device.android && r.params.inputReadOnly && (this.focus(),
            this.blur()),
            !r.opened && (r.open(),
            r.params.scrollToInput)) {
                var e = r.input.parents(".content");
                if (0 === e.length)
                    return;
                var n, i = parseInt(e.css("padding-top"), 10), a = parseInt(e.css("padding-bottom"), 10), o = e[0].offsetHeight - i - r.container.height(), s = e[0].scrollHeight - i - r.container.height(), l = r.input.offset().top - i + r.input[0].offsetHeight;
                if (l > o) {
                    var c = e.scrollTop() + l - o;
                    c + o > s && (n = c + o - s + a,
                    o === s && (n = r.container.height()),
                    e.css({
                        "padding-bottom": n + "px"
                    })),
                    e.scrollTop(c, 300)
                }
            }
        }
        function a(t) {
            r.input && r.input.length > 0 ? t.target !== r.input[0] && 0 === $(t.target).parents(".picker-modal").length && r.close() : 0 === $(t.target).parents(".picker-modal").length && r.close()
        }
        function o() {
            r.opened = !1,
            r.input && r.input.length > 0 && r.input.parents(".content").css({
                "padding-bottom": ""
            }),
            r.params.onClose && r.params.onClose(r),
            r.destroyCalendarEvents()
        }
        var r = this
          , s = {
            monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            monthNamesShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
            dayNames: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
            dayNamesShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六"],
            firstDay: 1,
            weekendDays: [0, 6],
            multiple: !1,
            dateFormat: "yyyy-mm-dd",
            direction: "horizontal",
            minDate: null,
            maxDate: null,
            touchMove: !0,
            hidenVal: !1,
            animate: !0,
            closeOnSelect: !0,
            monthPicker: !0,
            monthPickerTemplate: '<div class="picker-calendar-month-picker"><a href="#" class="link icon-only picker-calendar-prev-month"><i class="icon icon-prev"></i></a><div class="current-month-value"></div><a href="#" class="link icon-only picker-calendar-next-month"><i class="icon icon-next"></i></a></div>',
            yearPicker: !0,
            yearPickerTemplate: '<div class="picker-calendar-year-picker"><a href="#" class="link icon-only picker-calendar-prev-year"><i class="icon icon-prev"></i></a><span class="current-year-value"></span><a href="#" class="link icon-only picker-calendar-next-year"><i class="icon icon-next"></i></a></div>',
            weekHeader: !0,
            scrollToInput: !0,
            inputReadOnly: !0,
            toolbar: !0,
            toolbarCloseText: "Done",
            toolbarTemplate: '<div class="toolbar"><div class="toolbar-inner">{{monthPicker}}{{yearPicker}}</div></div>'
        };
        e = e || {};
        for (var l in s)
            void 0 === e[l] && (e[l] = s[l]);
        r.params = e,
        r.initialized = !1,
        r.inline = !!r.params.container,
        r.isH = "horizontal" === r.params.direction;
        var c = r.isH && t ? -1 : 1;
        return r.animating = !1,
        r.addValue = function(t) {
            if (r.params.multiple) {
                r.value || (r.value = []);
                for (var e, n = 0; n < r.value.length; n++)
                    new Date(t).getTime() === new Date(r.value[n]).getTime() && (e = n);
                void 0 === e ? r.value.push(t) : r.value.splice(e, 1),
                r.updateValue()
            } else
                r.value = [t],
                r.updateValue()
        }
        ,
        r.setValue = function(t) {
            r.value = t,
            r.updateValue()
        }
        ,
        r.updateValue = function() {
            r.wrapper.find(".picker-calendar-day-selected").removeClass("picker-calendar-day-selected");
            var t, e;
            for (t = 0; t < r.value.length; t++) {
                var i = new Date(r.value[t]);
                r.wrapper.find('.picker-calendar-day[data-date="' + i.getFullYear() + "-" + i.getMonth() + "-" + i.getDate() + '"]').addClass("picker-calendar-day-selected")
            }
            if (r.params.onChange && r.params.onChange(r, r.value, r.value.map(n)),
            r.input && r.input.length > 0) {
                if (r.params.formatValue)
                    e = r.params.formatValue(r, r.value);
                else {
                    for (e = [],
                    t = 0; t < r.value.length; t++)
                        e.push(n(r.value[t]));
                    e = e.join(", ")
                }
                r.params.hidenVal ? $(r.input).attr("date-value", e) : $(r.input).val(e),
                $(r.input).trigger("change")
            }
        }
        ,
        r.initCalendarEvents = function() {
            function e(t) {
                u || d || (d = !0,
                m = g = "touchstart" === t.type ? t.targetTouches[0].pageX : t.pageX,
                f = g = "touchstart" === t.type ? t.targetTouches[0].pageY : t.pageY,
                y = (new Date).getTime(),
                b = 0,
                E = !0,
                M = void 0,
                k = x = r.monthsTranslate)
            }
            function n(t) {
                if (d) {
                    if (v = "touchmove" === t.type ? t.targetTouches[0].pageX : t.pageX,
                    g = "touchmove" === t.type ? t.targetTouches[0].pageY : t.pageY,
                    void 0 === M && (M = !!(M || Math.abs(g - f) > Math.abs(v - m))),
                    r.isH && M)
                        return void (d = !1);
                    if (t.preventDefault(),
                    r.animating)
                        return void (d = !1);
                    E = !1,
                    u || (u = !0,
                    T = r.wrapper[0].offsetWidth,
                    C = r.wrapper[0].offsetHeight,
                    r.wrapper.transition(0)),
                    t.preventDefault(),
                    S = r.isH ? v - m : g - f,
                    b = S / (r.isH ? T : C),
                    x = 100 * (r.monthsTranslate * c + b),
                    r.wrapper.transform("translate3d(" + (r.isH ? x : 0) + "%, " + (r.isH ? 0 : x) + "%, 0)")
                }
            }
            function i(e) {
                if (!d || !u)
                    return void (d = u = !1);
                d = u = !1,
                w = (new Date).getTime(),
                w - y < 300 ? Math.abs(S) < 10 ? r.resetMonth() : S >= 10 ? t ? r.nextMonth() : r.prevMonth() : t ? r.prevMonth() : r.nextMonth() : b <= -.5 ? t ? r.prevMonth() : r.nextMonth() : b >= .5 ? t ? r.nextMonth() : r.prevMonth() : r.resetMonth(),
                setTimeout(function() {
                    E = !0
                }, 100)
            }
            function a(t) {
                if (E) {
                    var e = $(t.target).parents(".picker-calendar-day");
                    if (0 === e.length && $(t.target).hasClass("picker-calendar-day") && (e = $(t.target)),
                    0 !== e.length && (!e.hasClass("picker-calendar-day-selected") || r.params.multiple) && !e.hasClass("picker-calendar-day-disabled")) {
                        e.hasClass("picker-calendar-day-next") && r.nextMonth(),
                        e.hasClass("picker-calendar-day-prev") && r.prevMonth();
                        var n = e.attr("data-year")
                          , i = e.attr("data-month")
                          , a = e.attr("data-day");
                        r.params.onDayClick && r.params.onDayClick(r, e[0], n, i, a),
                        r.addValue(new Date(n,i,a).getTime()),
                        r.params.closeOnSelect && r.close()
                    }
                }
            }
            function o(t) {
                u || d || (d = !0,
                m = g = "touchstart" === t.type ? t.targetTouches[0].pageX : t.pageX,
                f = g = "touchstart" === t.type ? t.targetTouches[0].pageY : t.pageY,
                y = (new Date).getTime(),
                b = 0,
                E = !0,
                M = void 0,
                k = x = r.yearsTranslate)
            }
            function s(t) {
                if (d) {
                    if (v = "touchmove" === t.type ? t.targetTouches[0].pageX : t.pageX,
                    g = "touchmove" === t.type ? t.targetTouches[0].pageY : t.pageY,
                    void 0 === M && (M = !!(M || Math.abs(g - f) > Math.abs(v - m))),
                    r.isH && M)
                        return void (d = !1);
                    if (t.preventDefault(),
                    r.animating)
                        return void (d = !1);
                    E = !1,
                    u || (u = !0,
                    T = r.yearsPickerWrapper[0].offsetWidth,
                    C = r.yearsPickerWrapper[0].offsetHeight,
                    r.yearsPickerWrapper.transition(0)),
                    t.preventDefault(),
                    S = r.isH ? v - m : g - f,
                    b = S / (r.isH ? T : C),
                    x = 100 * (r.yearsTranslate * c + b),
                    r.yearsPickerWrapper.transform("translate3d(" + (r.isH ? x : 0) + "%, " + (r.isH ? 0 : x) + "%, 0)")
                }
            }
            function l(e) {
                if (!d || !u)
                    return void (d = u = !1);
                d = u = !1,
                w = (new Date).getTime(),
                w - y < 300 ? Math.abs(S) < 10 ? r.resetYearsGroup() : S >= 10 ? t ? r.nextYearsGroup() : r.prevYearsGroup() : t ? r.prevYearsGroup() : r.nextYearsGroup() : b <= -.5 ? t ? r.prevYearsGroup() : r.nextYearsGroup() : b >= .5 ? t ? r.nextYearsGroup() : r.prevYearsGroup() : r.resetYearsGroup(),
                setTimeout(function() {
                    E = !0
                }, 100)
            }
            function h() {
                var t = ($(this).text(),
                r.container.find(".picker-calendar-years-picker"));
                t.show().transform("translate3d(0, 0, 0)"),
                r.updateSelectedInPickers(),
                t.on("click", ".picker-calendar-year-unit", r.pickYear)
            }
            function p() {
                var t = r.container.find(".picker-calendar-months-picker");
                t.show().transform("translate3d(0, 0, 0)"),
                r.updateSelectedInPickers(),
                t.on("click", ".picker-calendar-month-unit", r.pickMonth)
            }
            var d, u, m, f, v, g, y, w, k, x, T, C, b, S, M, E = !0;
            r.container.find(".picker-calendar-prev-month").on("click", r.prevMonth),
            r.container.find(".picker-calendar-next-month").on("click", r.nextMonth),
            r.container.find(".picker-calendar-prev-year").on("click", r.prevYear),
            r.container.find(".picker-calendar-next-year").on("click", r.nextYear),
            r.container.find(".current-year-value").on("click", h),
            r.container.find(".current-month-value").on("click", p),
            r.wrapper.on("click", a),
            r.params.touchMove && (r.yearsPickerWrapper.on($.touchEvents.start, o),
            r.yearsPickerWrapper.on($.touchEvents.move, s),
            r.yearsPickerWrapper.on($.touchEvents.end, l),
            r.wrapper.on($.touchEvents.start, e),
            r.wrapper.on($.touchEvents.move, n),
            r.wrapper.on($.touchEvents.end, i)),
            r.container[0].f7DestroyCalendarEvents = function() {
                r.container.find(".picker-calendar-prev-month").off("click", r.prevMonth),
                r.container.find(".picker-calendar-next-month").off("click", r.nextMonth),
                r.container.find(".picker-calendar-prev-year").off("click", r.prevYear),
                r.container.find(".picker-calendar-next-year").off("click", r.nextYear),
                r.wrapper.off("click", a),
                r.params.touchMove && (r.wrapper.off($.touchEvents.start, e),
                r.wrapper.off($.touchEvents.move, n),
                r.wrapper.off($.touchEvents.end, i))
            }
        }
        ,
        r.destroyCalendarEvents = function(t) {
            "f7DestroyCalendarEvents"in r.container[0] && r.container[0].f7DestroyCalendarEvents()
        }
        ,
        r.yearsGroupHTML = function(t, e) {
            t = new Date(t);
            var n = t.getFullYear()
              , i = (new Date).getFullYear()
              , a = n - Math.floor(12.5)
              , o = "";
            "next" === e && (a += 25),
            "prev" === e && (a -= 25);
            for (var r = 0; r < 5; r += 1) {
                var s = "";
                s += '<div class="picker-calendar-row">';
                for (var l = 0; l < 5; l += 1)
                    s += a === i ? '<div class="picker-calendar-year-unit current-calendar-year-unit" data-year="' + a + '"><span>' + a + "</span></div>" : a === n ? '<div class="picker-calendar-year-unit picker-calendar-year-unit-selected" data-year="' + a + '"><span>' + a + "</span></div>" : '<div class="picker-calendar-year-unit" data-year="' + a + '"><span>' + a + "</span></div>",
                    a += 1;
                s += "</div>",
                o += s
            }
            return o = '<div class="picker-calendar-years-group">' + o + "</div>"
        }
        ,
        r.pickYear = function() {
            var t = $(this).text()
              , e = r.wrapper.find(".picker-calendar-month-current").attr("data-year");
            r.yearsPickerWrapper.find(".picker-calendar-year-unit").removeClass("picker-calendar-year-unit-selected"),
            $(this).addClass("picker-calendar-year-unit-selected"),
            e !== t ? (r.setYearMonth(t),
            r.container.find(".picker-calendar-years-picker").hide().transform("translate3d(0, 100%, 0)")) : r.container.find(".picker-calendar-years-picker").transform("translate3d(0, 100%, 0)")
        }
        ,
        r.onYearsChangeEnd = function(t) {
            r.animating = !1;
            var e, n, i, a = r.yearsPickerWrapper.children(".picker-calendar-years-next").find(".picker-calendar-year-unit").length;
            if ("next" === t) {
                var i = parseInt(r.yearsPickerWrapper.children(".picker-calendar-years-next").find(".picker-calendar-year-unit").eq(Math.floor(a / 2)).text());
                e = r.yearsGroupHTML(new Date(i,r.currentMonth), "next"),
                r.yearsPickerWrapper.append(e),
                r.yearsPickerWrapper.children().first().remove(),
                r.yearsGroups = r.container.find(".picker-calendar-years-group")
            }
            if ("prev" === t) {
                var i = parseInt(r.yearsPickerWrapper.children(".picker-calendar-years-prev").find(".picker-calendar-year-unit").eq(Math.floor(a / 2)).text());
                n = r.yearsGroupHTML(new Date(i,r.currentMonth), "prev"),
                r.yearsPickerWrapper.prepend(n),
                r.yearsPickerWrapper.children().last().remove(),
                r.yearsGroups = r.container.find(".picker-calendar-years-group")
            }
            r.setYearsTranslate(r.yearsTranslate)
        }
        ,
        r.monthsGroupHTML = function(t) {
            t = new Date(t);
            for (var e = t.getMonth() + 1, n = (new Date).getMonth() + 1, i = 1, a = "", o = 0; o < 3; o += 1) {
                var s = "";
                s += '<div class="picker-calendar-row">';
                for (var l = 0; l < 4; l += 1)
                    s += i === n ? '<div class="picker-calendar-month-unit current-calendar-month-unit" data-month="' + (i - 1) + '"><span>' + r.params.monthNames[i - 1] + "</span></div>" : i === e ? '<div class="picker-calendar-month-unit picker-calendar-month-selected" data-month="' + (i - 1) + '"><span>' + r.params.monthNames[i - 1] + "</span></div>" : '<div class="picker-calendar-month-unit" data-month="' + (i - 1) + '"><span>' + r.params.monthNames[i - 1] + "</span></div>",
                    i += 1;
                s += "</div>",
                a += s
            }
            return a = '<div class="picker-calendar-months-group">' + a + "</div>"
        }
        ,
        r.pickMonth = function() {
            var t = $(this).attr("data-month")
              , e = r.wrapper.find(".picker-calendar-month-current").attr("data-year")
              , n = r.wrapper.find(".picker-calendar-month-current").attr("data-month");
            r.monthsPickerWrapper.find(".picker-calendar-month-unit").removeClass("picker-calendar-month-unit-selected"),
            $(this).addClass("picker-calendar-month-unit-selected"),
            n !== t ? (r.setYearMonth(e, t),
            r.container.find(".picker-calendar-months-picker").hide().transform("translate3d(0, 100%, 0)")) : r.container.find(".picker-calendar-months-picker").transform("translate3d(0, 100%, 0)")
        }
        ,
        r.daysInMonth = function(t) {
            var e = new Date(t);
            return new Date(e.getFullYear(),e.getMonth() + 1,0).getDate()
        }
        ,
        r.monthHTML = function(t, e) {
            t = new Date(t);
            var n = t.getFullYear()
              , i = t.getMonth();
            t.getDate();
            "next" === e && (t = 11 === i ? new Date(n + 1,0) : new Date(n,i + 1,1)),
            "prev" === e && (t = 0 === i ? new Date(n - 1,11) : new Date(n,i - 1,1)),
            "next" !== e && "prev" !== e || (i = t.getMonth(),
            n = t.getFullYear());
            var a = r.daysInMonth(new Date(t.getFullYear(),t.getMonth()).getTime() - 864e6)
              , o = r.daysInMonth(t)
              , s = new Date(t.getFullYear(),t.getMonth()).getDay();
            0 === s && (s = 7);
            var l, c, h, p = [], d = "", u = r.params.firstDay - 1 + 0, m = (new Date).setHours(0, 0, 0, 0), f = r.params.minDate ? new Date(r.params.minDate).getTime() : null, v = r.params.maxDate ? new Date(r.params.maxDate).getTime() : null;
            if (r.value && r.value.length)
                for (c = 0; c < r.value.length; c++)
                    p.push(new Date(r.value[c]).setHours(0, 0, 0, 0));
            for (c = 1; c <= 6; c++) {
                var g = "";
                for (h = 1; h <= 7; h++) {
                    var y = h;
                    u++;
                    var w = u - s
                      , k = "";
                    w < 0 ? (w = a + w + 1,
                    k += " picker-calendar-day-prev",
                    l = new Date(i - 1 < 0 ? n - 1 : n,i - 1 < 0 ? 11 : i - 1,w).getTime()) : (w += 1,
                    w > o ? (w -= o,
                    k += " picker-calendar-day-next",
                    l = new Date(i + 1 > 11 ? n + 1 : n,i + 1 > 11 ? 0 : i + 1,w).getTime()) : l = new Date(n,i,w).getTime()),
                    l === m && (k += " picker-calendar-day-today"),
                    p.indexOf(l) >= 0 && (k += " picker-calendar-day-selected"),
                    r.params.weekendDays.indexOf(y - 1) >= 0 && (k += " picker-calendar-day-weekend"),
                    (f && l < f || v && l > v) && (k += " picker-calendar-day-disabled"),
                    l = new Date(l);
                    var x = l.getFullYear()
                      , T = l.getMonth();
                    g += '<div data-year="' + x + '" data-month="' + T + '" data-day="' + w + '" class="picker-calendar-day' + k + '" data-date="' + x + "-" + T + "-" + w + '"><span>' + w + "</span></div>"
                }
                d += '<div class="picker-calendar-row">' + g + "</div>"
            }
            return d = '<div class="picker-calendar-month" data-year="' + n + '" data-month="' + i + '">' + d + "</div>"
        }
        ,
        r.animating = !1,
        r.updateCurrentMonthYear = function(t) {
            void 0 === t ? (r.currentMonth = parseInt(r.months.eq(1).attr("data-month"), 10),
            r.currentYear = parseInt(r.months.eq(1).attr("data-year"), 10)) : (r.currentMonth = parseInt(r.months.eq("next" === t ? r.months.length - 1 : 0).attr("data-month"), 10),
            r.currentYear = parseInt(r.months.eq("next" === t ? r.months.length - 1 : 0).attr("data-year"), 10)),
            r.container.find(".current-month-value").text(r.params.monthNames[r.currentMonth]),
            r.container.find(".current-year-value").text(r.currentYear)
        }
        ,
        r.onMonthChangeStart = function(t) {
            r.updateCurrentMonthYear(t),
            r.months.removeClass("picker-calendar-month-current picker-calendar-month-prev picker-calendar-month-next");
            var e = "next" === t ? r.months.length - 1 : 0;
            r.months.eq(e).addClass("picker-calendar-month-current"),
            r.months.eq("next" === t ? e - 1 : e + 1).addClass("next" === t ? "picker-calendar-month-prev" : "picker-calendar-month-next"),
            r.params.onMonthYearChangeStart && r.params.onMonthYearChangeStart(r, r.currentYear, r.currentMonth)
        }
        ,
        r.onMonthChangeEnd = function(t, e) {
            r.animating = !1;
            var n, i, a;
            r.wrapper.find(".picker-calendar-month:not(.picker-calendar-month-prev):not(.picker-calendar-month-current):not(.picker-calendar-month-next)").remove(),
            void 0 === t && (t = "next",
            e = !0),
            e ? (r.wrapper.find(".picker-calendar-month-next, .picker-calendar-month-prev").remove(),
            i = r.monthHTML(new Date(r.currentYear,r.currentMonth), "prev"),
            n = r.monthHTML(new Date(r.currentYear,r.currentMonth), "next")) : a = r.monthHTML(new Date(r.currentYear,r.currentMonth), t),
            ("next" === t || e) && r.wrapper.append(a || n),
            ("prev" === t || e) && r.wrapper.prepend(a || i),
            r.months = r.wrapper.find(".picker-calendar-month"),
            r.setMonthsTranslate(r.monthsTranslate),
            r.params.onMonthAdd && r.params.onMonthAdd(r, "next" === t ? r.months.eq(r.months.length - 1)[0] : r.months.eq(0)[0]),
            r.params.onMonthYearChangeEnd && r.params.onMonthYearChangeEnd(r, r.currentYear, r.currentMonth),
            r.updateSelectedInPickers()
        }
        ,
        r.updateSelectedInPickers = function() {
            var t = parseInt(r.wrapper.find(".picker-calendar-month-current").attr("data-year"), 10)
              , e = (new Date).getFullYear()
              , n = parseInt(r.wrapper.find(".picker-calendar-month-current").attr("data-month"), 10)
              , i = (new Date).getMonth()
              , a = parseInt(r.yearsPickerWrapper.find(".picker-calendar-year-unit-selected").attr("data-year"), 10)
              , o = parseInt(r.monthsPickerWrapper.find(".picker-calendar-month-unit-selected").attr("data-month"), 10);
            a !== t && (r.yearsPickerWrapper.find(".picker-calendar-year-unit").removeClass("picker-calendar-year-unit-selected"),
            r.yearsPickerWrapper.find('.picker-calendar-year-unit[data-year="' + t + '"]').addClass("picker-calendar-year-unit-selected")),
            o !== n && (r.monthsPickerWrapper.find(".picker-calendar-month-unit").removeClass("picker-calendar-month-unit-selected"),
            r.monthsPickerWrapper.find('.picker-calendar-month-unit[data-month="' + n + '"]').addClass("picker-calendar-month-unit-selected")),
            e !== t ? r.monthsPickerWrapper.find(".picker-calendar-month-unit").removeClass("current-calendar-month-unit") : r.monthsPickerWrapper.find('.picker-calendar-month-unit[data-month="' + i + '"]').addClass("current-calendar-month-unit")
        }
        ,
        r.setYearsTranslate = function(t) {
            t = t || r.yearsTranslate || 0,
            void 0 === r.yearsTranslate && (r.yearsTranslate = t),
            r.yearsGroups.removeClass("picker-calendar-years-current picker-calendar-years-prev picker-calendar-years-next");
            var e = 100 * -(t + 1) * c
              , n = 100 * -t * c
              , i = 100 * -(t - 1) * c;
            r.yearsGroups.eq(0).transform("translate3d(" + (r.isH ? e : 0) + "%, " + (r.isH ? 0 : e) + "%, 0)").addClass("picker-calendar-years-prev"),
            r.yearsGroups.eq(1).transform("translate3d(" + (r.isH ? n : 0) + "%, " + (r.isH ? 0 : n) + "%, 0)").addClass("picker-calendar-years-current"),
            r.yearsGroups.eq(2).transform("translate3d(" + (r.isH ? i : 0) + "%, " + (r.isH ? 0 : i) + "%, 0)").addClass("picker-calendar-years-next")
        }
        ,
        r.nextYearsGroup = function(t) {
            void 0 !== t && "object" != typeof t || (t = "",
            r.params.animate || (t = 0));
            var e = !r.animating;
            r.yearsTranslate--,
            r.animating = !0;
            var n = 100 * r.yearsTranslate * c;
            r.yearsPickerWrapper.transition(t).transform("translate3d(" + (r.isH ? n : 0) + "%, " + (r.isH ? 0 : n) + "%, 0)"),
            e && r.yearsPickerWrapper.transitionEnd(function() {
                r.onYearsChangeEnd("next")
            }),
            r.params.animate || r.onYearsChangeEnd("next")
        }
        ,
        r.prevYearsGroup = function(t) {
            void 0 !== t && "object" != typeof t || (t = "",
            r.params.animate || (t = 0));
            var e = !r.animating;
            r.yearsTranslate++,
            r.animating = !0;
            var n = 100 * r.yearsTranslate * c;
            r.yearsPickerWrapper.transition(t).transform("translate3d(" + (r.isH ? n : 0) + "%, " + (r.isH ? 0 : n) + "%, 0)"),
            e && r.yearsPickerWrapper.transitionEnd(function() {
                r.onYearsChangeEnd("prev")
            }),
            r.params.animate || r.onYearsChangeEnd("prev")
        }
        ,
        r.resetYearsGroup = function(t) {
            void 0 === t && (t = "");
            var e = 100 * r.yearsTranslate * c;
            r.yearsPickerWrapper.transition(t).transform("translate3d(" + (r.isH ? e : 0) + "%, " + (r.isH ? 0 : e) + "%, 0)")
        }
        ,
        r.setMonthsTranslate = function(t) {
            t = t || r.monthsTranslate || 0,
            void 0 === r.monthsTranslate && (r.monthsTranslate = t),
            r.months.removeClass("picker-calendar-month-current picker-calendar-month-prev picker-calendar-month-next");
            var e = 100 * -(t + 1) * c
              , n = 100 * -t * c
              , i = 100 * -(t - 1) * c;
            r.months.eq(0).transform("translate3d(" + (r.isH ? e : 0) + "%, " + (r.isH ? 0 : e) + "%, 0)").addClass("picker-calendar-month-prev"),
            r.months.eq(1).transform("translate3d(" + (r.isH ? n : 0) + "%, " + (r.isH ? 0 : n) + "%, 0)").addClass("picker-calendar-month-current"),
            r.months.eq(2).transform("translate3d(" + (r.isH ? i : 0) + "%, " + (r.isH ? 0 : i) + "%, 0)").addClass("picker-calendar-month-next")
        }
        ,
        r.nextMonth = function(t) {
            void 0 !== t && "object" != typeof t || (t = "",
            r.params.animate || (t = 0));
            var e = parseInt(r.months.eq(r.months.length - 1).attr("data-month"), 10)
              , n = parseInt(r.months.eq(r.months.length - 1).attr("data-year"), 10)
              , i = new Date(n,e)
              , a = i.getTime()
              , o = !r.animating;
            if (r.params.maxDate && a > new Date(r.params.maxDate).getTime())
                return r.resetMonth();
            if (r.monthsTranslate--,
            e === r.currentMonth) {
                var s = 100 * -r.monthsTranslate * c
                  , l = $(r.monthHTML(a, "next")).transform("translate3d(" + (r.isH ? s : 0) + "%, " + (r.isH ? 0 : s) + "%, 0)").addClass("picker-calendar-month-next");
                r.wrapper.append(l[0]),
                r.months = r.wrapper.find(".picker-calendar-month"),
                r.params.onMonthAdd && r.params.onMonthAdd(r, r.months.eq(r.months.length - 1)[0])
            }
            r.animating = !0,
            r.onMonthChangeStart("next");
            var h = 100 * r.monthsTranslate * c;
            r.wrapper.transition(t).transform("translate3d(" + (r.isH ? h : 0) + "%, " + (r.isH ? 0 : h) + "%, 0)"),
            o && r.wrapper.transitionEnd(function() {
                r.onMonthChangeEnd("next")
            }),
            r.params.animate || r.onMonthChangeEnd("next")
        }
        ,
        r.prevMonth = function(t) {
            void 0 !== t && "object" != typeof t || (t = "",
            r.params.animate || (t = 0));
            var e = parseInt(r.months.eq(0).attr("data-month"), 10)
              , n = parseInt(r.months.eq(0).attr("data-year"), 10)
              , i = new Date(n,e + 1,-1)
              , a = i.getTime()
              , o = !r.animating;
            if (r.params.minDate && a < new Date(r.params.minDate).getTime())
                return r.resetMonth();
            if (r.monthsTranslate++,
            e === r.currentMonth) {
                var s = 100 * -r.monthsTranslate * c
                  , l = $(r.monthHTML(a, "prev")).transform("translate3d(" + (r.isH ? s : 0) + "%, " + (r.isH ? 0 : s) + "%, 0)").addClass("picker-calendar-month-prev");
                r.wrapper.prepend(l[0]),
                r.months = r.wrapper.find(".picker-calendar-month"),
                r.params.onMonthAdd && r.params.onMonthAdd(r, r.months.eq(0)[0])
            }
            r.animating = !0,
            r.onMonthChangeStart("prev");
            var h = 100 * r.monthsTranslate * c;
            r.wrapper.transition(t).transform("translate3d(" + (r.isH ? h : 0) + "%, " + (r.isH ? 0 : h) + "%, 0)"),
            o && r.wrapper.transitionEnd(function() {
                r.onMonthChangeEnd("prev")
            }),
            r.params.animate || r.onMonthChangeEnd("prev")
        }
        ,
        r.resetMonth = function(t) {
            void 0 === t && (t = "");
            var e = 100 * r.monthsTranslate * c;
            r.wrapper.transition(t).transform("translate3d(" + (r.isH ? e : 0) + "%, " + (r.isH ? 0 : e) + "%, 0)")
        }
        ,
        r.setYearMonth = function(t, e, n) {
            void 0 === t && (t = r.currentYear),
            void 0 === e && (e = r.currentMonth),
            void 0 !== n && "object" != typeof n || (n = "",
            r.params.animate || (n = 0));
            var i;
            if (i = t < r.currentYear ? new Date(t,e + 1,-1).getTime() : new Date(t,e).getTime(),
            r.params.maxDate && i > new Date(r.params.maxDate).getTime())
                return !1;
            if (r.params.minDate && i < new Date(r.params.minDate).getTime())
                return !1;
            var a = new Date(r.currentYear,r.currentMonth).getTime()
              , o = i > a ? "next" : "prev"
              , s = r.monthHTML(new Date(t,e));
            r.monthsTranslate = r.monthsTranslate || 0;
            var l, h, p = r.monthsTranslate, d = !r.animating;
            i > a ? (r.monthsTranslate--,
            r.animating || r.months.eq(r.months.length - 1).remove(),
            r.wrapper.append(s),
            r.months = r.wrapper.find(".picker-calendar-month"),
            l = 100 * -(p - 1) * c,
            r.months.eq(r.months.length - 1).transform("translate3d(" + (r.isH ? l : 0) + "%, " + (r.isH ? 0 : l) + "%, 0)").addClass("picker-calendar-month-next")) : (r.monthsTranslate++,
            r.animating || r.months.eq(0).remove(),
            r.wrapper.prepend(s),
            r.months = r.wrapper.find(".picker-calendar-month"),
            l = 100 * -(p + 1) * c,
            r.months.eq(0).transform("translate3d(" + (r.isH ? l : 0) + "%, " + (r.isH ? 0 : l) + "%, 0)").addClass("picker-calendar-month-prev")),
            r.params.onMonthAdd && r.params.onMonthAdd(r, "next" === o ? r.months.eq(r.months.length - 1)[0] : r.months.eq(0)[0]),
            r.animating = !0,
            r.onMonthChangeStart(o),
            h = 100 * r.monthsTranslate * c,
            r.wrapper.transition(n).transform("translate3d(" + (r.isH ? h : 0) + "%, " + (r.isH ? 0 : h) + "%, 0)"),
            d && r.wrapper.transitionEnd(function() {
                r.onMonthChangeEnd(o, !0)
            }),
            r.params.animate || r.onMonthChangeEnd(o)
        }
        ,
        r.nextYear = function() {
            r.setYearMonth(r.currentYear + 1)
        }
        ,
        r.prevYear = function() {
            r.setYearMonth(r.currentYear - 1)
        }
        ,
        r.layout = function() {
            var t, e = "", n = "", i = r.value && r.value.length ? r.value[0] : (new Date).setHours(0, 0, 0, 0), a = r.yearsGroupHTML(i, "prev"), o = r.yearsGroupHTML(i), s = r.yearsGroupHTML(i, "next"), l = '<div class="picker-calendar-years-picker"><div class="picker-calendar-years-picker-wrapper">' + (a + o + s) + "</div></div>", c = '<div class="picker-calendar-months-picker"><div class="picker-calendar-months-picker-wrapper">' + r.monthsGroupHTML(i) + "</div></div>", h = r.monthHTML(i, "prev"), p = r.monthHTML(i), d = r.monthHTML(i, "next"), u = '<div class="picker-calendar-months"><div class="picker-calendar-months-wrapper">' + (h + p + d) + "</div></div>", m = "";
            if (r.params.weekHeader) {
                for (t = 0; t < 7; t++) {
                    var f = t + r.params.firstDay > 6 ? t - 7 + r.params.firstDay : t + r.params.firstDay
                      , v = r.params.dayNamesShort[f];
                    m += '<div class="picker-calendar-week-day ' + (r.params.weekendDays.indexOf(f) >= 0 ? "picker-calendar-week-day-weekend" : "") + '"> ' + v + "</div>"
                }
                m = '<div class="picker-calendar-week-days">' + m + "</div>"
            }
            n = "picker-modal picker-calendar " + (r.params.cssClass || "");
            var g = r.params.toolbar ? r.params.toolbarTemplate.replace(/{{closeText}}/g, r.params.toolbarCloseText) : "";
            r.params.toolbar && (g = r.params.toolbarTemplate.replace(/{{closeText}}/g, r.params.toolbarCloseText).replace(/{{monthPicker}}/g, r.params.monthPicker ? r.params.monthPickerTemplate : "").replace(/{{yearPicker}}/g, r.params.yearPicker ? r.params.yearPickerTemplate : "")),
            e = '<div class="' + n + '">' + g + '<div class="picker-modal-inner">' + m + u + "</div>" + c + l + "</div>",
            r.pickerHTML = e
        }
        ,
        r.params.input && (r.input = $(r.params.input),
        r.input.length > 0 && (r.params.inputReadOnly && r.input.prop("readOnly", !0),
        r.inline || r.input.on("click", i),
        $(document).on("beforePageSwitch", function() {
            r.input.off("click", i),
            $(document).off("beforePageSwitch")
        }))),
        r.inline || $("html").on("click", a),
        r.opened = !1,
        r.open = function() {
            var t = !1;
            if (!r.opened) {
                if (!r.value)
                    if (r.params.value)
                        r.value = r.params.value,
                        t = !0;
                    else if (r.input && r.input.length > 0) {
                        var e = null;
                        e = r.params.hidenVal ? $(r.input).attr("date-value") : $(r.input).val(),
                        e && (r.value = [e],
                        t = !0)
                    }
                r.layout(),
                r.inline ? (r.container = $(r.pickerHTML),
                r.container.addClass("picker-modal-inline"),
                $(r.params.container).append(r.container)) : (r.container = $($.pickerModal(r.pickerHTML)),
                $(r.container).on("close", function() {
                    o()
                })),
                r.container[0].f7Calendar = r,
                r.wrapper = r.container.find(".picker-calendar-months-wrapper"),
                r.yearsPickerWrapper = r.container.find(".picker-calendar-years-picker-wrapper"),
                r.yearsGroups = r.yearsPickerWrapper.find(".picker-calendar-years-group"),
                r.monthsPickerWrapper = r.container.find(".picker-calendar-months-picker-wrapper"),
                r.months = r.wrapper.find(".picker-calendar-month"),
                r.updateCurrentMonthYear(),
                r.yearsTranslate = 0,
                r.setYearsTranslate(),
                r.monthsTranslate = 0,
                r.setMonthsTranslate(),
                r.initCalendarEvents(),
                t && r.updateValue()
            }
            r.opened = !0,
            r.initialized = !0,
            r.params.onMonthAdd && r.months.each(function() {
                r.params.onMonthAdd(r, this)
            }),
            r.params.onOpen && r.params.onOpen(r)
        }
        ,
        r.close = function() {
            r.opened && !r.inline && $.closeModal(r.container)
        }
        ,
        r.destroy = function() {
            r.close(),
            r.params.input && r.input.length > 0 && r.input.off("click", i),
            $("html").off("click", a)
        }
        ,
        r.inline && r.open(),
        r
    };
    $.fn.calendar = function(t) {
        return this.each(function() {
            var n = $(this);
            if (n[0]) {
                var i = {};
                "INPUT" === n[0].tagName.toUpperCase() ? i.input = n : i.container = n,
                new e($.extend(i, t))
            }
        })
    }
    ,
    $.initCalendar = function(t) {
        $(t ? t : document.body).find("[data-toggle='date']").each(function() {
            $(this).calendar()
        })
    }
}(Zepto),
function($) {
    "use strict";
    var t = function(t) {
        function e() {
            if (o.opened)
                for (var t = 0; t < o.cols.length; t++)
                    o.cols[t].divider || (o.cols[t].calcSize(),
                    o.cols[t].setValue(o.cols[t].value, 0, !1))
        }
        function n(t) {
            if (t.preventDefault(),
            $.device.isWeixin && $.device.android && o.params.inputReadOnly && (this.focus(),
            this.blur()),
            !o.opened) {
                if ($.closeModal($(".picker-modal")),
                o.open(),
                o.params.scrollToInput) {
                    var e = o.input.parents(".content");
                    if (0 === e.length)
                        return;
                    var n, i = parseInt(e.css("padding-top"), 10), a = parseInt(e.css("padding-bottom"), 10), r = e[0].offsetHeight - i - o.container.height(), s = e[0].scrollHeight - i - o.container.height(), l = o.input.offset().top - i + o.input[0].offsetHeight;
                    if (l > r) {
                        var c = e.scrollTop() + l - r;
                        c + r > s && (n = c + r - s + a,
                        r === s && (n = o.container.height()),
                        e.css({
                            "padding-bottom": n + "px"
                        })),
                        e.scrollTop(c, 300)
                    }
                }
                t.stopPropagation()
            }
        }
        function i(t) {
            o.opened && (o.input && o.input.length > 0 ? t.target !== o.input[0] && 0 === $(t.target).parents(".picker-modal").length && o.close() : 0 === $(t.target).parents(".picker-modal").length && o.close())
        }
        function a() {
            o.opened = !1,
            o.input && o.input.length > 0 && o.input.parents(".content").css({
                "padding-bottom": ""
            }),
            o.params.onClose && o.params.onClose(o),
            o.container.find(".picker-items-col").each(function() {
                o.destroyPickerCol(this)
            })
        }
        var o = this
          , r = {
            smooth: !0,
            updateValuesOnMomentum: !1,
            updateValuesOnTouchmove: !0,
            rotateEffect: !1,
            momentumRatio: 7,
            freeMode: !1,
            scrollToInput: !0,
            inputReadOnly: !0,
            toolbar: !0,
            toolbarCloseText: "确定",
            toolbarTemplate: '<header class="bar bar-nav">                <button class="button button-link pull-right close-picker picker-confirm">确定</button>                <h1 class="title">请选择</h1>                </header>'
        };
        t = t || {};
        for (var s in r)
            void 0 === t[s] && (t[s] = r[s]);
        o.params = t,
        o.cols = [],
        o.initialized = !1,
        o.inline = !!o.params.container;
        var l = $.device.ios || navigator.userAgent.toLowerCase().indexOf("safari") >= 0 && navigator.userAgent.toLowerCase().indexOf("chrome") < 0 && !$.device.android;
        return o.setValue = function(t, e) {
            for (var n = 0, i = 0; i < o.cols.length; i++)
                o.cols[i] && !o.cols[i].divider && (o.cols[i].setValue(t[n], e),
                n++)
        }
        ,
        o.confirmValue = function() {
            for (var t = [], e = [], n = 0; n < o.cols.length; n++)
                o.cols[n].divider || (t.push(o.cols[n].value),
                e.push(o.cols[n].displayValue));
            t.indexOf(void 0) >= 0 || (o.value = t,
            o.displayValue = e,
            o.params.onConfirm && o.params.onConfirm(o, o.value, o.displayValue),
            o.input && o.input.length > 0 && ($(o.input).val(o.params.formatValue ? o.params.formatValue(o, o.value, o.displayValue) : o.value.join(" ")),
            $(o.input).trigger("change")))
        }
        ,
        o.updateValue = function() {
            for (var t = [], e = [], n = 0; n < o.cols.length; n++)
                o.cols[n].divider || (t.push(o.cols[n].value),
                e.push(o.cols[n].displayValue));
            if (!(t.indexOf(void 0) >= 0))
                return o.params.smooth ? (o.value = t,
                o.displayValue = e,
                o.params.onChange && o.params.onChange(o, o.value, o.displayValue),
                o.input && o.input.length > 0 && ($(o.input).val(o.params.formatValue ? o.params.formatValue(o, o.value, o.displayValue) : o.value.join(" ")),
                $(o.input).trigger("change")),
                void 0) : void (o.params.onChange && o.params.onChange(o, o.value, o.displayValue))
        }
        ,
        o.initPickerCol = function(t, e) {
            function n() {
                g = $.requestAnimationFrame(function() {
                    p.updateItems(void 0, void 0, 0),
                    n()
                })
            }
            function i(t) {
                w || y || (t.preventDefault(),
                y = !0,
                k = x = "touchstart" === t.type ? t.targetTouches[0].pageY : t.pageY,
                T = (new Date).getTime(),
                D = !0,
                b = M = $.getTranslate(p.wrapper[0], "y"))
            }
            function a(t) {
                if (y) {
                    t.preventDefault(),
                    D = !1,
                    x = "touchmove" === t.type ? t.targetTouches[0].pageY : t.pageY,
                    w || ($.cancelAnimationFrame(g),
                    w = !0,
                    b = M = $.getTranslate(p.wrapper[0], "y"),
                    p.wrapper.transition(0)),
                    t.preventDefault();
                    M = b + (x - k),
                    S = void 0,
                    M < f && (M = f - Math.pow(f - M, .8),
                    S = "min"),
                    M > v && (M = v + Math.pow(M - v, .8),
                    S = "max"),
                    p.wrapper.transform("translate3d(0," + M + "px,0)"),
                    p.updateItems(void 0, M, 0, o.params.updateValuesOnTouchmove),
                    P = M - E || M,
                    Y = (new Date).getTime(),
                    E = M
                }
            }
            function r(t) {
                if (!y || !w)
                    return void (y = w = !1);
                y = w = !1,
                p.wrapper.transition(""),
                S && ("min" === S ? p.wrapper.transform("translate3d(0," + f + "px,0)") : p.wrapper.transform("translate3d(0," + v + "px,0)")),
                C = (new Date).getTime();
                var e;
                C - T > 300 ? e = M : (Math.abs(P / (C - Y)),
                e = M + P * o.params.momentumRatio),
                e = Math.max(Math.min(e, v), f);
                var i = -Math.floor((e - v) / u);
                o.params.freeMode || (e = -i * u + v),
                p.wrapper.transform("translate3d(0," + parseInt(e, 10) + "px,0)"),
                p.updateItems(i, e, "", !0),
                o.params.updateValuesOnMomentum && (n(),
                p.wrapper.transitionEnd(function() {
                    $.cancelAnimationFrame(g)
                })),
                setTimeout(function() {
                    D = !0
                }, 100)
            }
            function s(t) {
                if (D) {
                    $.cancelAnimationFrame(g);
                    var e = $(this).attr("data-picker-value");
                    p.setValue(e)
                }
            }
            var c = $(t)
              , h = c.index()
              , p = o.cols[h];
            if (!p.divider) {
                p.container = c,
                p.wrapper = p.container.find(".picker-items-col-wrapper"),
                p.items = p.wrapper.find(".picker-item");
                var d, u, m, f, v;
                p.replaceValues = function(t, e) {
                    p.destroyEvents(),
                    p.values = t,
                    p.displayValues = e;
                    var n = o.columnHTML(p, !0);
                    p.wrapper.html(n),
                    p.items = p.wrapper.find(".picker-item"),
                    p.calcSize(),
                    p.setValue(p.values[0], 0, !0),
                    p.initEvents()
                }
                ,
                p.calcSize = function() {
                    o.params.rotateEffect && (p.container.removeClass("picker-items-col-absolute"),
                    p.width || p.container.css({
                        width: ""
                    }));
                    var t, e;
                    t = 0,
                    e = p.container[0].offsetHeight,
                    d = p.wrapper[0].offsetHeight,
                    u = p.items[0].offsetHeight,
                    m = u * p.items.length,
                    f = e / 2 - m + u / 2,
                    v = e / 2 - u / 2,
                    p.width && (t = p.width,
                    parseInt(t, 10) === t && (t += "px"),
                    p.container.css({
                        width: t
                    })),
                    o.params.rotateEffect && (p.width || (p.items.each(function() {
                        var e = $(this);
                        e.css({
                            width: "auto"
                        }),
                        t = Math.max(t, e[0].offsetWidth),
                        e.css({
                            width: ""
                        })
                    }),
                    p.container.css({
                        width: t + 2 + "px"
                    })),
                    p.container.addClass("picker-items-col-absolute"))
                }
                ,
                p.calcSize(),
                p.wrapper.transform("translate3d(0," + v + "px,0)").transition(0);
                var g;
                p.setValue = function(t, e, i) {
                    void 0 === e && (e = "");
                    var a = p.wrapper.find('.picker-item[data-picker-value="' + t + '"]').index();
                    if (void 0 !== a && -1 !== a) {
                        var r = -a * u + v;
                        p.wrapper.transition(e),
                        p.wrapper.transform("translate3d(0," + r + "px,0)"),
                        o.params.updateValuesOnMomentum && p.activeIndex && p.activeIndex !== a && ($.cancelAnimationFrame(g),
                        p.wrapper.transitionEnd(function() {
                            $.cancelAnimationFrame(g)
                        }),
                        n()),
                        p.updateItems(a, r, e, i)
                    }
                }
                ,
                p.updateItems = function(t, e, n, i) {
                    void 0 === e && (e = $.getTranslate(p.wrapper[0], "y")),
                    void 0 === t && (t = -Math.round((e - v) / u)),
                    t < 0 && (t = 0),
                    t >= p.items.length && (t = p.items.length - 1);
                    var a = p.activeIndex;
                    p.activeIndex = t,
                    p.wrapper.find(".picker-selected").removeClass("picker-selected"),
                    o.params.rotateEffect && p.items.transition(n);
                    var r = p.items.eq(t).addClass("picker-selected").transform("");
                    if ((i || void 0 === i) && (p.value = r.attr("data-picker-value"),
                    p.displayValue = p.displayValues ? p.displayValues[t] : p.value,
                    a !== t && (p.onChange && p.onChange(o, p.value, p.displayValue),
                    o.updateValue())),
                    o.params.rotateEffect) {
                        Math.floor((e - v) / u);
                        p.items.each(function() {
                            var t = $(this)
                              , n = t.index() * u
                              , i = v - e
                              , a = n - i
                              , o = a / u
                              , r = Math.ceil(p.height / u / 2) + 1
                              , s = -18 * o;
                            s > 180 && (s = 180),
                            s < -180 && (s = -180),
                            Math.abs(o) > r ? t.addClass("picker-item-far") : t.removeClass("picker-item-far"),
                            t.transform("translate3d(0, " + (-e + v) + "px, " + (l ? -110 : 0) + "px) rotateX(" + s + "deg)")
                        })
                    }
                }
                ,
                e && p.updateItems(0, v, 0);
                var y, w, k, x, T, C, b, S, M, E, P, Y, D = !0;
                p.initEvents = function(t) {
                    var e = t ? "off" : "on";
                    p.container[e]($.touchEvents.start, i),
                    p.container[e]($.touchEvents.move, a),
                    p.container[e]($.touchEvents.end, r),
                    p.items[e]("click", s)
                }
                ,
                p.destroyEvents = function() {
                    p.initEvents(!0)
                }
                ,
                p.container[0].f7DestroyPickerCol = function() {
                    p.destroyEvents()
                }
                ,
                p.initEvents()
            }
        }
        ,
        o.destroyPickerCol = function(t) {
            t = $(t),
            "f7DestroyPickerCol"in t[0] && t[0].f7DestroyPickerCol()
        }
        ,
        $(window).on("resize", e),
        o.columnHTML = function(t, e) {
            var n = ""
              , i = "";
            if (t.divider)
                i += '<div class="picker-items-col picker-items-col-divider ' + (t.textAlign ? "picker-items-col-" + t.textAlign : "") + " " + (t.cssClass || "") + '">' + t.content + "</div>";
            else {
                for (var a = 0; a < t.values.length; a++)
                    n += '<div class="picker-item" data-picker-value="' + t.values[a] + '">' + (t.displayValues ? t.displayValues[a] : t.values[a]) + "</div>";
                i += '<div class="picker-items-col ' + (t.textAlign ? "picker-items-col-" + t.textAlign : "") + " " + (t.cssClass || "") + '"><div class="picker-items-col-wrapper">' + n + "</div></div>"
            }
            return e ? n : i
        }
        ,
        o.layout = function() {
            var t, e = "", n = "";
            o.cols = [];
            var i = "";
            for (t = 0; t < o.params.cols.length; t++) {
                var a = o.params.cols[t];
                i += o.columnHTML(o.params.cols[t]),
                o.cols.push(a)
            }
            n = "picker-modal picker-columns " + (o.params.cssClass || "") + (o.params.rotateEffect ? " picker-3d" : ""),
            e = '<div class="' + n + '">' + (o.params.toolbar ? o.params.toolbarTemplate.replace(/{{closeText}}/g, o.params.toolbarCloseText) : "") + '<div class="picker-modal-inner picker-items">' + i + '<div class="picker-center-highlight"></div></div></div>',
            o.pickerHTML = e
        }
        ,
        o.params.input && (o.input = $(o.params.input),
        o.input.length > 0 && (o.params.inputReadOnly && o.input.prop("readOnly", !0),
        o.inline || o.input.on("click", n))),
        o.inline || $("html").on("click", i),
        o.opened = !1,
        o.open = function() {
            o.opened || (o.layout(),
            o.opened = !0,
            o.inline ? (o.container = $(o.pickerHTML),
            o.container.addClass("picker-modal-inline"),
            $(o.params.container).append(o.container)) : (o.container = $($.pickerModal(o.pickerHTML)),
            $(o.container).on("close", function() {
                a()
            })),
            o.container[0].f7Picker = o,
            o.container.find(".picker-items-col").each(function() {
                var t = !0;
                (!o.initialized && o.params.value || o.initialized && o.value) && (t = !1),
                o.initPickerCol(this, t)
            }),
            o.params.smooth || o.container.find(".picker-confirm").on("click", function() {
                o.confirmValue()
            }),
            o.initialized ? o.value && o.setValue(o.value, 0) : o.params.value && (o.setValue(o.params.value, 0),
            o.params.smooth || (o.value = o.params.value))),
            o.initialized = !0,
            o.params.onOpen && o.params.onOpen(o)
        }
        ,
        o.close = function() {
            o.opened && !o.inline && $.closeModal(o.container)
        }
        ,
        o.destroy = function() {
            o.close(),
            o.params.input && o.input.length > 0 && o.input.off("click", n),
            $("html").off("click", i),
            $(window).off("resize", e)
        }
        ,
        o.inline && o.open(),
        o
    };
    $(document).on("click", ".close-picker", function() {
        var t = $(".picker-modal.modal-in");
        $.closeModal(t)
    }),
    $.fn.picker = function(e) {
        var n = arguments;
        return this.each(function() {
            if (this) {
                var i = $(this)
                  , a = i.data("picker");
                if (!a) {
                    var o = $.extend({
                        input: this,
                        value: i.val() ? i.val().split(" ") : ""
                    }, e);
                    a = new t(o),
                    i.data("picker", a)
                }
                "string" == typeof e && a[e].apply(a, Array.prototype.slice.call(n, 1))
            }
        })
    }
}(Zepto),
function($) {
    "use strict";
    var t = new Date
      , e = function(t) {
        for (var e = [], n = 1; n <= (t || 31); n++)
            e.push(n < 10 ? "0" + n : n);
        return e
    }
      , n = function(t, n) {
        var i = new Date(n,parseInt(t) + 1 - 1,1)
          , a = new Date(i - 1);
        return e(a.getDate())
    }
      , i = function(t) {
        return t < 10 ? "0" + t : t
    }
      , a = "01 02 03 04 05 06 07 08 09 10 11 12".split(" ")
      , o = function() {
        for (var t = [], e = 1950; e <= 2030; e++)
            t.push(e);
        return t
    }()
      , r = {
        rotateEffect: !1,
        value: [t.getFullYear(), i(t.getMonth() + 1), i(t.getDate()), t.getHours(), i(t.getMinutes())],
        onChange: function(t, e, i) {
            var a = n(t.cols[1].value, t.cols[0].value)
              , o = t.cols[2].value;
            o > a.length && (o = a.length),
            t.cols[2].setValue(o)
        },
        formatValue: function(t, e, n) {
            return n[0] + "-" + e[1] + "-" + e[2] + " " + e[3] + ":" + e[4]
        },
        cols: [{
            values: o
        }, {
            values: a
        }, {
            values: e()
        }, {
            divider: !0,
            content: "  "
        }, {
            values: function() {
                for (var t = [], e = 0; e <= 23; e++)
                    t.push(e);
                return t
            }()
        }, {
            divider: !0,
            content: ":"
        }, {
            values: function() {
                for (var t = [], e = 0; e <= 59; e++)
                    t.push(e < 10 ? "0" + e : e);
                return t
            }()
        }]
    };
    $.fn.datetimePicker = function(t) {
        return this.each(function() {
            if (this) {
                var e = $.extend(r, t);
                $(this).picker(e),
                t.value && $(this).val(e.formatValue(e, e.value, e.value))
            }
        })
    }
}(Zepto),
function(t) {
    "use strict";
    function e(t, e) {
        this.wrapper = "string" == typeof t ? document.querySelector(t) : t,
        this.scroller = $(this.wrapper).find(".content-inner")[0],
        this.scrollerStyle = this.scroller && this.scroller.style,
        this.options = {
            resizeScrollbars: !0,
            mouseWheelSpeed: 20,
            snapThreshold: .334,
            startX: 0,
            startY: 0,
            scrollY: !0,
            directionLockThreshold: 5,
            momentum: !0,
            bounce: !0,
            bounceTime: 600,
            bounceEasing: "",
            preventDefault: !0,
            preventDefaultException: {
                tagName: /^(INPUT|TEXTAREA|BUTTON|SELECT)$/
            },
            HWCompositing: !0,
            useTransition: !0,
            useTransform: !0,
            eventPassthrough: void 0
        };
        for (var n in e)
            this.options[n] = e[n];
        this.translateZ = this.options.HWCompositing && o.hasPerspective ? " translateZ(0)" : "",
        this.options.useTransition = o.hasTransition && this.options.useTransition,
        this.options.useTransform = o.hasTransform && this.options.useTransform,
        this.options.eventPassthrough = !0 === this.options.eventPassthrough ? "vertical" : this.options.eventPassthrough,
        this.options.preventDefault = !this.options.eventPassthrough && this.options.preventDefault,
        this.options.scrollY = "vertical" !== this.options.eventPassthrough && this.options.scrollY,
        this.options.scrollX = "horizontal" !== this.options.eventPassthrough && this.options.scrollX,
        this.options.freeScroll = this.options.freeScroll && !this.options.eventPassthrough,
        this.options.directionLockThreshold = this.options.eventPassthrough ? 0 : this.options.directionLockThreshold,
        this.options.bounceEasing = "string" == typeof this.options.bounceEasing ? o.ease[this.options.bounceEasing] || o.ease.circular : this.options.bounceEasing,
        this.options.resizePolling = void 0 === this.options.resizePolling ? 60 : this.options.resizePolling,
        !0 === this.options.tap && (this.options.tap = "tap"),
        "scale" === this.options.shrinkScrollbars && (this.options.useTransition = !1),
        this.options.invertWheelDirection = this.options.invertWheelDirection ? -1 : 1,
        3 === this.options.probeType && (this.options.useTransition = !1),
        this.x = 0,
        this.y = 0,
        this.directionX = 0,
        this.directionY = 0,
        this._events = {},
        this._init(),
        this.refresh(),
        this.scrollTo(this.options.startX, this.options.startY),
        this.enable()
    }
    function n(t, e, n) {
        var i = document.createElement("div")
          , a = document.createElement("div");
        return !0 === n && (i.style.cssText = "position:absolute;z-index:9999",
        a.style.cssText = "-webkit-box-sizing:border-box;-moz-box-sizing:border-box;box-sizing:border-box;position:absolute;background:rgba(0,0,0,0.5);border:1px solid rgba(255,255,255,0.9);border-radius:3px"),
        a.className = "iScrollIndicator",
        "h" === t ? (!0 === n && (i.style.cssText += ";height:5px;left:2px;right:2px;bottom:0",
        a.style.height = "100%"),
        i.className = "iScrollHorizontalScrollbar") : (!0 === n && (i.style.cssText += ";width:5px;bottom:2px;top:2px;right:1px",
        a.style.width = "100%"),
        i.className = "iScrollVerticalScrollbar"),
        i.style.cssText += ";overflow:hidden",
        e || (i.style.pointerEvents = "none"),
        i.appendChild(a),
        i
    }
    function i(e, n) {
        this.wrapper = "string" == typeof n.el ? document.querySelector(n.el) : n.el,
        this.wrapperStyle = this.wrapper.style,
        this.indicator = this.wrapper.children[0],
        this.indicatorStyle = this.indicator.style,
        this.scroller = e,
        this.options = {
            listenX: !0,
            listenY: !0,
            interactive: !1,
            resize: !0,
            defaultScrollbars: !1,
            shrink: !1,
            fade: !1,
            speedRatioX: 0,
            speedRatioY: 0
        };
        for (var i in n)
            this.options[i] = n[i];
        this.sizeRatioX = 1,
        this.sizeRatioY = 1,
        this.maxPosX = 0,
        this.maxPosY = 0,
        this.options.interactive && (this.options.disableTouch || (o.addEvent(this.indicator, "touchstart", this),
        o.addEvent(t, "touchend", this)),
        this.options.disablePointer || (o.addEvent(this.indicator, o.prefixPointerEvent("pointerdown"), this),
        o.addEvent(t, o.prefixPointerEvent("pointerup"), this)),
        this.options.disableMouse || (o.addEvent(this.indicator, "mousedown", this),
        o.addEvent(t, "mouseup", this))),
        this.options.fade && (this.wrapperStyle[o.style.transform] = this.scroller.translateZ,
        this.wrapperStyle[o.style.transitionDuration] = o.isBadAndroid ? "0.001s" : "0ms",
        this.wrapperStyle.opacity = "0")
    }
    var a = t.requestAnimationFrame || t.webkitRequestAnimationFrame || t.mozRequestAnimationFrame || t.oRequestAnimationFrame || t.msRequestAnimationFrame || function(e) {
        t.setTimeout(e, 1e3 / 60)
    }
      , o = function() {
        function e(t) {
            return !1 !== o && ("" === o ? t : o + t.charAt(0).toUpperCase() + t.substr(1))
        }
        var n = {}
          , i = document.createElement("div").style
          , o = function() {
            for (var t = ["t", "webkitT", "MozT", "msT", "OT"], e = 0, n = t.length; e < n; e++)
                if (t[e] + "ransform"in i)
                    return t[e].substr(0, t[e].length - 1);
            return !1
        }();
        n.getTime = Date.now || function() {
            return (new Date).getTime()
        }
        ,
        n.extend = function(t, e) {
            for (var n in e)
                t[n] = e[n]
        }
        ,
        n.addEvent = function(t, e, n, i) {
            t.addEventListener(e, n, !!i)
        }
        ,
        n.removeEvent = function(t, e, n, i) {
            t.removeEventListener(e, n, !!i)
        }
        ,
        n.prefixPointerEvent = function(e) {
            return t.MSPointerEvent ? "MSPointer" + e.charAt(9).toUpperCase() + e.substr(10) : e
        }
        ,
        n.momentum = function(t, e, n, i, o, r, s) {
            function l() {
                +new Date - m > 50 && (s._execEvent("scroll"),
                m = +new Date),
                +new Date - u < h && a(l)
            }
            var c, h, p = t - e, d = Math.abs(p) / n;
            d /= 2,
            d = d > 1.5 ? 1.5 : d,
            r = void 0 === r ? 6e-4 : r,
            c = t + d * d / (2 * r) * (p < 0 ? -1 : 1),
            h = d / r,
            c < i ? (c = o ? i - o / 2.5 * (d / 8) : i,
            p = Math.abs(c - t),
            h = p / d) : c > 0 && (c = o ? o / 2.5 * (d / 8) : 0,
            p = Math.abs(t) + c,
            h = p / d);
            var u = +new Date
              , m = u;
            return a(l),
            {
                destination: Math.round(c),
                duration: h
            }
        }
        ;
        var r = e("transform");
        return n.extend(n, {
            hasTransform: !1 !== r,
            hasPerspective: e("perspective")in i,
            hasTouch: "ontouchstart"in t,
            hasPointer: t.PointerEvent || t.MSPointerEvent,
            hasTransition: e("transition")in i
        }),
        n.isBadAndroid = /Android /.test(t.navigator.appVersion) && !/Chrome\/\d/.test(t.navigator.appVersion) && !1,
        n.extend(n.style = {}, {
            transform: r,
            transitionTimingFunction: e("transitionTimingFunction"),
            transitionDuration: e("transitionDuration"),
            transitionDelay: e("transitionDelay"),
            transformOrigin: e("transformOrigin")
        }),
        n.hasClass = function(t, e) {
            return new RegExp("(^|\\s)" + e + "(\\s|$)").test(t.className)
        }
        ,
        n.addClass = function(t, e) {
            if (!n.hasClass(t, e)) {
                var i = t.className.split(" ");
                i.push(e),
                t.className = i.join(" ")
            }
        }
        ,
        n.removeClass = function(t, e) {
            if (n.hasClass(t, e)) {
                var i = new RegExp("(^|\\s)" + e + "(\\s|$)","g");
                t.className = t.className.replace(i, " ")
            }
        }
        ,
        n.offset = function(t) {
            for (var e = -t.offsetLeft, n = -t.offsetTop; t = t.offsetParent; )
                e -= t.offsetLeft,
                n -= t.offsetTop;
            return {
                left: e,
                top: n
            }
        }
        ,
        n.preventDefaultException = function(t, e) {
            for (var n in e)
                if (e[n].test(t[n]))
                    return !0;
            return !1
        }
        ,
        n.extend(n.eventType = {}, {
            touchstart: 1,
            touchmove: 1,
            touchend: 1,
            mousedown: 2,
            mousemove: 2,
            mouseup: 2,
            pointerdown: 3,
            pointermove: 3,
            pointerup: 3,
            MSPointerDown: 3,
            MSPointerMove: 3,
            MSPointerUp: 3
        }),
        n.extend(n.ease = {}, {
            quadratic: {
                style: "cubic-bezier(0.25, 0.46, 0.45, 0.94)",
                fn: function(t) {
                    return t * (2 - t)
                }
            },
            circular: {
                style: "cubic-bezier(0.1, 0.57, 0.1, 1)",
                fn: function(t) {
                    return Math.sqrt(1 - --t * t)
                }
            },
            back: {
                style: "cubic-bezier(0.175, 0.885, 0.32, 1.275)",
                fn: function(t) {
                    return (t -= 1) * t * (5 * t + 4) + 1
                }
            },
            bounce: {
                style: "",
                fn: function(t) {
                    return (t /= 1) < 1 / 2.75 ? 7.5625 * t * t : t < 2 / 2.75 ? 7.5625 * (t -= 1.5 / 2.75) * t + .75 : t < 2.5 / 2.75 ? 7.5625 * (t -= 2.25 / 2.75) * t + .9375 : 7.5625 * (t -= 2.625 / 2.75) * t + .984375
                }
            },
            elastic: {
                style: "",
                fn: function(t) {
                    return 0 === t ? 0 : 1 === t ? 1 : .4 * Math.pow(2, -10 * t) * Math.sin((t - .055) * (2 * Math.PI) / .22) + 1
                }
            }
        }),
        n.tap = function(t, e) {
            var n = document.createEvent("Event");
            n.initEvent(e, !0, !0),
            n.pageX = t.pageX,
            n.pageY = t.pageY,
            t.target.dispatchEvent(n)
        }
        ,
        n.click = function(t) {
            var e, n = t.target;
            /(SELECT|INPUT|TEXTAREA)/i.test(n.tagName) || (e = document.createEvent("MouseEvents"),
            e.initMouseEvent("click", !0, !0, t.view, 1, n.screenX, n.screenY, n.clientX, n.clientY, t.ctrlKey, t.altKey, t.shiftKey, t.metaKey, 0, null),
            e._constructed = !0,
            n.dispatchEvent(e))
        }
        ,
        n
    }();
    e.prototype = {
        version: "5.1.3",
        _init: function() {
            this._initEvents(),
            (this.options.scrollbars || this.options.indicators) && this._initIndicators(),
            this.options.mouseWheel && this._initWheel(),
            this.options.snap && this._initSnap(),
            this.options.keyBindings && this._initKeys()
        },
        destroy: function() {
            this._initEvents(!0),
            this._execEvent("destroy")
        },
        _transitionEnd: function(t) {
            t.target === this.scroller && this.isInTransition && (this._transitionTime(),
            this.resetPosition(this.options.bounceTime) || (this.isInTransition = !1,
            this._execEvent("scrollEnd")))
        },
        _start: function(t) {
            if ((1 === o.eventType[t.type] || 0 === t.button) && this.enabled && (!this.initiated || o.eventType[t.type] === this.initiated)) {
                !this.options.preventDefault || o.isBadAndroid || o.preventDefaultException(t.target, this.options.preventDefaultException) || t.preventDefault();
                var e, n = t.touches ? t.touches[0] : t;
                this.initiated = o.eventType[t.type],
                this.moved = !1,
                this.distX = 0,
                this.distY = 0,
                this.directionX = 0,
                this.directionY = 0,
                this.directionLocked = 0,
                this._transitionTime(),
                this.startTime = o.getTime(),
                this.options.useTransition && this.isInTransition ? (this.isInTransition = !1,
                e = this.getComputedPosition(),
                this._translate(Math.round(e.x), Math.round(e.y)),
                this._execEvent("scrollEnd")) : !this.options.useTransition && this.isAnimating && (this.isAnimating = !1,
                this._execEvent("scrollEnd")),
                this.startX = this.x,
                this.startY = this.y,
                this.absStartX = this.x,
                this.absStartY = this.y,
                this.pointX = n.pageX,
                this.pointY = n.pageY,
                this._execEvent("beforeScrollStart")
            }
        },
        _move: function(t) {
            if (this.enabled && o.eventType[t.type] === this.initiated) {
                this.options.preventDefault && t.preventDefault();
                var e, n, i, a, r = t.touches ? t.touches[0] : t, s = r.pageX - this.pointX, l = r.pageY - this.pointY, c = o.getTime();
                if (this.pointX = r.pageX,
                this.pointY = r.pageY,
                this.distX += s,
                this.distY += l,
                i = Math.abs(this.distX),
                a = Math.abs(this.distY),
                !(c - this.endTime > 300 && i < 10 && a < 10)) {
                    if (this.directionLocked || this.options.freeScroll || (i > a + this.options.directionLockThreshold ? this.directionLocked = "h" : a >= i + this.options.directionLockThreshold ? this.directionLocked = "v" : this.directionLocked = "n"),
                    "h" === this.directionLocked) {
                        if ("vertical" === this.options.eventPassthrough)
                            t.preventDefault();
                        else if ("horizontal" === this.options.eventPassthrough)
                            return void (this.initiated = !1);
                        l = 0
                    } else if ("v" === this.directionLocked) {
                        if ("horizontal" === this.options.eventPassthrough)
                            t.preventDefault();
                        else if ("vertical" === this.options.eventPassthrough)
                            return void (this.initiated = !1);
                        s = 0
                    }
                    s = this.hasHorizontalScroll ? s : 0,
                    l = this.hasVerticalScroll ? l : 0,
                    e = this.x + s,
                    n = this.y + l,
                    (e > 0 || e < this.maxScrollX) && (e = this.options.bounce ? this.x + s / 3 : e > 0 ? 0 : this.maxScrollX),
                    (n > 0 || n < this.maxScrollY) && (n = this.options.bounce ? this.y + l / 3 : n > 0 ? 0 : this.maxScrollY),
                    this.directionX = s > 0 ? -1 : s < 0 ? 1 : 0,
                    this.directionY = l > 0 ? -1 : l < 0 ? 1 : 0,
                    this.moved || this._execEvent("scrollStart"),
                    this.moved = !0,
                    this._translate(e, n),
                    c - this.startTime > 300 && (this.startTime = c,
                    this.startX = this.x,
                    this.startY = this.y,
                    1 === this.options.probeType && this._execEvent("scroll")),
                    this.options.probeType > 1 && this._execEvent("scroll")
                }
            }
        },
        _end: function(t) {
            if (this.enabled && o.eventType[t.type] === this.initiated) {
                this.options.preventDefault && !o.preventDefaultException(t.target, this.options.preventDefaultException) && t.preventDefault();
                var e, n, i = o.getTime() - this.startTime, a = Math.round(this.x), r = Math.round(this.y), s = Math.abs(a - this.startX), l = Math.abs(r - this.startY), c = 0, h = "";
                if (this.isInTransition = 0,
                this.initiated = 0,
                this.endTime = o.getTime(),
                !this.resetPosition(this.options.bounceTime)) {
                    if (this.scrollTo(a, r),
                    !this.moved)
                        return this.options.tap && o.tap(t, this.options.tap),
                        this.options.click && o.click(t),
                        void this._execEvent("scrollCancel");
                    if (this._events.flick && i < 200 && s < 100 && l < 100)
                        return void this._execEvent("flick");
                    if (this.options.momentum && i < 300 && (e = this.hasHorizontalScroll ? o.momentum(this.x, this.startX, i, this.maxScrollX, this.options.bounce ? this.wrapperWidth : 0, this.options.deceleration, this) : {
                        destination: a,
                        duration: 0
                    },
                    n = this.hasVerticalScroll ? o.momentum(this.y, this.startY, i, this.maxScrollY, this.options.bounce ? this.wrapperHeight : 0, this.options.deceleration, this) : {
                        destination: r,
                        duration: 0
                    },
                    a = e.destination,
                    r = n.destination,
                    c = Math.max(e.duration, n.duration),
                    this.isInTransition = 1),
                    this.options.snap) {
                        var p = this._nearestSnap(a, r);
                        this.currentPage = p,
                        c = this.options.snapSpeed || Math.max(Math.max(Math.min(Math.abs(a - p.x), 1e3), Math.min(Math.abs(r - p.y), 1e3)), 300),
                        a = p.x,
                        r = p.y,
                        this.directionX = 0,
                        this.directionY = 0,
                        h = this.options.bounceEasing
                    }
                    if (a !== this.x || r !== this.y)
                        return (a > 0 || a < this.maxScrollX || r > 0 || r < this.maxScrollY) && (h = o.ease.quadratic),
                        void this.scrollTo(a, r, c, h);
                    this._execEvent("scrollEnd")
                }
            }
        },
        _resize: function() {
            var t = this;
            clearTimeout(this.resizeTimeout),
            this.resizeTimeout = setTimeout(function() {
                t.refresh()
            }, this.options.resizePolling)
        },
        resetPosition: function(e) {
            var n = this.x
              , i = this.y;
            if (e = e || 0,
            !this.hasHorizontalScroll || this.x > 0 ? n = 0 : this.x < this.maxScrollX && (n = this.maxScrollX),
            !this.hasVerticalScroll || this.y > 0 ? i = 0 : this.y < this.maxScrollY && (i = this.maxScrollY),
            n === this.x && i === this.y)
                return !1;
            if (this.options.ptr && this.y > 44 && -1 * this.startY < $(t).height() && !this.ptrLock) {
                i = this.options.ptrOffset || 44,
                this._execEvent("ptr"),
                this.ptrLock = !0;
                var a = this;
                setTimeout(function() {
                    a.ptrLock = !1
                }, 500)
            }
            return this.scrollTo(n, i, e, this.options.bounceEasing),
            !0
        },
        disable: function() {
            this.enabled = !1
        },
        enable: function() {
            this.enabled = !0
        },
        refresh: function() {
            this.wrapperWidth = this.wrapper.clientWidth,
            this.wrapperHeight = this.wrapper.clientHeight,
            this.scrollerWidth = this.scroller.offsetWidth,
            this.scrollerHeight = this.scroller.offsetHeight,
            this.maxScrollX = this.wrapperWidth - this.scrollerWidth,
            this.maxScrollY = this.wrapperHeight - this.scrollerHeight,
            this.hasHorizontalScroll = this.options.scrollX && this.maxScrollX < 0,
            this.hasVerticalScroll = this.options.scrollY && this.maxScrollY < 0,
            this.hasHorizontalScroll || (this.maxScrollX = 0,
            this.scrollerWidth = this.wrapperWidth),
            this.hasVerticalScroll || (this.maxScrollY = 0,
            this.scrollerHeight = this.wrapperHeight),
            this.endTime = 0,
            this.directionX = 0,
            this.directionY = 0,
            this.wrapperOffset = o.offset(this.wrapper),
            this._execEvent("refresh"),
            this.resetPosition()
        },
        on: function(t, e) {
            this._events[t] || (this._events[t] = []),
            this._events[t].push(e)
        },
        off: function(t, e) {
            if (this._events[t]) {
                var n = this._events[t].indexOf(e);
                n > -1 && this._events[t].splice(n, 1)
            }
        },
        _execEvent: function(t) {
            if (this._events[t]) {
                var e = 0
                  , n = this._events[t].length;
                if (n)
                    for (; e < n; e++)
                        this._events[t][e].apply(this, [].slice.call(arguments, 1))
            }
        },
        scrollBy: function(t, e, n, i) {
            t = this.x + t,
            e = this.y + e,
            n = n || 0,
            this.scrollTo(t, e, n, i)
        },
        scrollTo: function(t, e, n, i) {
            i = i || o.ease.circular,
            this.isInTransition = this.options.useTransition && n > 0,
            !n || this.options.useTransition && i.style ? (this._transitionTimingFunction(i.style),
            this._transitionTime(n),
            this._translate(t, e)) : this._animate(t, e, n, i.fn)
        },
        scrollToElement: function(t, e, n, i, a) {
            if (t = t.nodeType ? t : this.scroller.querySelector(t)) {
                var r = o.offset(t);
                r.left -= this.wrapperOffset.left,
                r.top -= this.wrapperOffset.top,
                !0 === n && (n = Math.round(t.offsetWidth / 2 - this.wrapper.offsetWidth / 2)),
                !0 === i && (i = Math.round(t.offsetHeight / 2 - this.wrapper.offsetHeight / 2)),
                r.left -= n || 0,
                r.top -= i || 0,
                r.left = r.left > 0 ? 0 : r.left < this.maxScrollX ? this.maxScrollX : r.left,
                r.top = r.top > 0 ? 0 : r.top < this.maxScrollY ? this.maxScrollY : r.top,
                e = void 0 === e || null === e || "auto" === e ? Math.max(Math.abs(this.x - r.left), Math.abs(this.y - r.top)) : e,
                this.scrollTo(r.left, r.top, e, a)
            }
        },
        _transitionTime: function(t) {
            if (t = t || 0,
            this.scrollerStyle[o.style.transitionDuration] = t + "ms",
            !t && o.isBadAndroid && (this.scrollerStyle[o.style.transitionDuration] = "0.001s"),
            this.indicators)
                for (var e = this.indicators.length; e--; )
                    this.indicators[e].transitionTime(t)
        },
        _transitionTimingFunction: function(t) {
            if (this.scrollerStyle[o.style.transitionTimingFunction] = t,
            this.indicators)
                for (var e = this.indicators.length; e--; )
                    this.indicators[e].transitionTimingFunction(t)
        },
        _translate: function(t, e) {
            if (this.options.useTransform ? this.scrollerStyle[o.style.transform] = "translate(" + t + "px," + e + "px)" + this.translateZ : (t = Math.round(t),
            e = Math.round(e),
            this.scrollerStyle.left = t + "px",
            this.scrollerStyle.top = e + "px"),
            this.x = t,
            this.y = e,
            this.indicators)
                for (var n = this.indicators.length; n--; )
                    this.indicators[n].updatePosition()
        },
        _initEvents: function(e) {
            var n = e ? o.removeEvent : o.addEvent
              , i = this.options.bindToWrapper ? this.wrapper : t;
            n(t, "orientationchange", this),
            n(t, "resize", this),
            this.options.click && n(this.wrapper, "click", this, !0),
            this.options.disableMouse || (n(this.wrapper, "mousedown", this),
            n(i, "mousemove", this),
            n(i, "mousecancel", this),
            n(i, "mouseup", this)),
            o.hasPointer && !this.options.disablePointer && (n(this.wrapper, o.prefixPointerEvent("pointerdown"), this),
            n(i, o.prefixPointerEvent("pointermove"), this),
            n(i, o.prefixPointerEvent("pointercancel"), this),
            n(i, o.prefixPointerEvent("pointerup"), this)),
            o.hasTouch && !this.options.disableTouch && (n(this.wrapper, "touchstart", this),
            n(i, "touchmove", this),
            n(i, "touchcancel", this),
            n(i, "touchend", this)),
            n(this.scroller, "transitionend", this),
            n(this.scroller, "webkitTransitionEnd", this),
            n(this.scroller, "oTransitionEnd", this),
            n(this.scroller, "MSTransitionEnd", this)
        },
        getComputedPosition: function() {
            var e, n, i = t.getComputedStyle(this.scroller, null);
            return this.options.useTransform ? (i = i[o.style.transform].split(")")[0].split(", "),
            e = +(i[12] || i[4]),
            n = +(i[13] || i[5])) : (e = +i.left.replace(/[^-\d.]/g, ""),
            n = +i.top.replace(/[^-\d.]/g, "")),
            {
                x: e,
                y: n
            }
        },
        _initIndicators: function() {
            function t(t) {
                for (var e = s.indicators.length; e--; )
                    t.call(s.indicators[e])
            }
            var e, a = this.options.interactiveScrollbars, o = "string" != typeof this.options.scrollbars, r = [], s = this;
            this.indicators = [],
            this.options.scrollbars && (this.options.scrollY && (e = {
                el: n("v", a, this.options.scrollbars),
                interactive: a,
                defaultScrollbars: !0,
                customStyle: o,
                resize: this.options.resizeScrollbars,
                shrink: this.options.shrinkScrollbars,
                fade: this.options.fadeScrollbars,
                listenX: !1
            },
            this.wrapper.appendChild(e.el),
            r.push(e)),
            this.options.scrollX && (e = {
                el: n("h", a, this.options.scrollbars),
                interactive: a,
                defaultScrollbars: !0,
                customStyle: o,
                resize: this.options.resizeScrollbars,
                shrink: this.options.shrinkScrollbars,
                fade: this.options.fadeScrollbars,
                listenY: !1
            },
            this.wrapper.appendChild(e.el),
            r.push(e))),
            this.options.indicators && (r = r.concat(this.options.indicators));
            for (var l = r.length; l--; )
                this.indicators.push(new i(this,r[l]));
            this.options.fadeScrollbars && (this.on("scrollEnd", function() {
                t(function() {
                    this.fade()
                })
            }),
            this.on("scrollCancel", function() {
                t(function() {
                    this.fade()
                })
            }),
            this.on("scrollStart", function() {
                t(function() {
                    this.fade(1)
                })
            }),
            this.on("beforeScrollStart", function() {
                t(function() {
                    this.fade(1, !0)
                })
            })),
            this.on("refresh", function() {
                t(function() {
                    this.refresh()
                })
            }),
            this.on("destroy", function() {
                t(function() {
                    this.destroy()
                }),
                delete this.indicators
            })
        },
        _initWheel: function() {
            o.addEvent(this.wrapper, "wheel", this),
            o.addEvent(this.wrapper, "mousewheel", this),
            o.addEvent(this.wrapper, "DOMMouseScroll", this),
            this.on("destroy", function() {
                o.removeEvent(this.wrapper, "wheel", this),
                o.removeEvent(this.wrapper, "mousewheel", this),
                o.removeEvent(this.wrapper, "DOMMouseScroll", this)
            })
        },
        _wheel: function(t) {
            if (this.enabled) {
                t.preventDefault(),
                t.stopPropagation();
                var e, n, i, a, o = this;
                if (void 0 === this.wheelTimeout && o._execEvent("scrollStart"),
                clearTimeout(this.wheelTimeout),
                this.wheelTimeout = setTimeout(function() {
                    o._execEvent("scrollEnd"),
                    o.wheelTimeout = void 0
                }, 400),
                "deltaX"in t)
                    1 === t.deltaMode ? (e = -t.deltaX * this.options.mouseWheelSpeed,
                    n = -t.deltaY * this.options.mouseWheelSpeed) : (e = -t.deltaX,
                    n = -t.deltaY);
                else if ("wheelDeltaX"in t)
                    e = t.wheelDeltaX / 120 * this.options.mouseWheelSpeed,
                    n = t.wheelDeltaY / 120 * this.options.mouseWheelSpeed;
                else if ("wheelDelta"in t)
                    e = n = t.wheelDelta / 120 * this.options.mouseWheelSpeed;
                else {
                    if (!("detail"in t))
                        return;
                    e = n = -t.detail / 3 * this.options.mouseWheelSpeed
                }
                if (e *= this.options.invertWheelDirection,
                n *= this.options.invertWheelDirection,
                this.hasVerticalScroll || (e = n,
                n = 0),
                this.options.snap)
                    return i = this.currentPage.pageX,
                    a = this.currentPage.pageY,
                    e > 0 ? i-- : e < 0 && i++,
                    n > 0 ? a-- : n < 0 && a++,
                    void this.goToPage(i, a);
                i = this.x + Math.round(this.hasHorizontalScroll ? e : 0),
                a = this.y + Math.round(this.hasVerticalScroll ? n : 0),
                i > 0 ? i = 0 : i < this.maxScrollX && (i = this.maxScrollX),
                a > 0 ? a = 0 : a < this.maxScrollY && (a = this.maxScrollY),
                this.scrollTo(i, a, 0),
                this._execEvent("scroll")
            }
        },
        _initSnap: function() {
            this.currentPage = {},
            "string" == typeof this.options.snap && (this.options.snap = this.scroller.querySelectorAll(this.options.snap)),
            this.on("refresh", function() {
                var t, e, n, i, a, o, r = 0, s = 0, l = 0, c = this.options.snapStepX || this.wrapperWidth, h = this.options.snapStepY || this.wrapperHeight;
                if (this.pages = [],
                this.wrapperWidth && this.wrapperHeight && this.scrollerWidth && this.scrollerHeight) {
                    if (!0 === this.options.snap)
                        for (n = Math.round(c / 2),
                        i = Math.round(h / 2); l > -this.scrollerWidth; ) {
                            for (this.pages[r] = [],
                            t = 0,
                            a = 0; a > -this.scrollerHeight; )
                                this.pages[r][t] = {
                                    x: Math.max(l, this.maxScrollX),
                                    y: Math.max(a, this.maxScrollY),
                                    width: c,
                                    height: h,
                                    cx: l - n,
                                    cy: a - i
                                },
                                a -= h,
                                t++;
                            l -= c,
                            r++
                        }
                    else
                        for (o = this.options.snap,
                        t = o.length,
                        e = -1; r < t; r++)
                            (0 === r || o[r].offsetLeft <= o[r - 1].offsetLeft) && (s = 0,
                            e++),
                            this.pages[s] || (this.pages[s] = []),
                            l = Math.max(-o[r].offsetLeft, this.maxScrollX),
                            a = Math.max(-o[r].offsetTop, this.maxScrollY),
                            n = l - Math.round(o[r].offsetWidth / 2),
                            i = a - Math.round(o[r].offsetHeight / 2),
                            this.pages[s][e] = {
                                x: l,
                                y: a,
                                width: o[r].offsetWidth,
                                height: o[r].offsetHeight,
                                cx: n,
                                cy: i
                            },
                            l > this.maxScrollX && s++;
                    this.goToPage(this.currentPage.pageX || 0, this.currentPage.pageY || 0, 0),
                    this.options.snapThreshold % 1 == 0 ? (this.snapThresholdX = this.options.snapThreshold,
                    this.snapThresholdY = this.options.snapThreshold) : (this.snapThresholdX = Math.round(this.pages[this.currentPage.pageX][this.currentPage.pageY].width * this.options.snapThreshold),
                    this.snapThresholdY = Math.round(this.pages[this.currentPage.pageX][this.currentPage.pageY].height * this.options.snapThreshold))
                }
            }),
            this.on("flick", function() {
                var t = this.options.snapSpeed || Math.max(Math.max(Math.min(Math.abs(this.x - this.startX), 1e3), Math.min(Math.abs(this.y - this.startY), 1e3)), 300);
                this.goToPage(this.currentPage.pageX + this.directionX, this.currentPage.pageY + this.directionY, t)
            })
        },
        _nearestSnap: function(t, e) {
            if (!this.pages.length)
                return {
                    x: 0,
                    y: 0,
                    pageX: 0,
                    pageY: 0
                };
            var n = 0
              , i = this.pages.length
              , a = 0;
            if (Math.abs(t - this.absStartX) < this.snapThresholdX && Math.abs(e - this.absStartY) < this.snapThresholdY)
                return this.currentPage;
            for (t > 0 ? t = 0 : t < this.maxScrollX && (t = this.maxScrollX),
            e > 0 ? e = 0 : e < this.maxScrollY && (e = this.maxScrollY); n < i; n++)
                if (t >= this.pages[n][0].cx) {
                    t = this.pages[n][0].x;
                    break
                }
            for (i = this.pages[n].length; a < i; a++)
                if (e >= this.pages[0][a].cy) {
                    e = this.pages[0][a].y;
                    break
                }
            return n === this.currentPage.pageX && (n += this.directionX,
            n < 0 ? n = 0 : n >= this.pages.length && (n = this.pages.length - 1),
            t = this.pages[n][0].x),
            a === this.currentPage.pageY && (a += this.directionY,
            a < 0 ? a = 0 : a >= this.pages[0].length && (a = this.pages[0].length - 1),
            e = this.pages[0][a].y),
            {
                x: t,
                y: e,
                pageX: n,
                pageY: a
            }
        },
        goToPage: function(t, e, n, i) {
            i = i || this.options.bounceEasing,
            t >= this.pages.length ? t = this.pages.length - 1 : t < 0 && (t = 0),
            e >= this.pages[t].length ? e = this.pages[t].length - 1 : e < 0 && (e = 0);
            var a = this.pages[t][e].x
              , o = this.pages[t][e].y;
            n = void 0 === n ? this.options.snapSpeed || Math.max(Math.max(Math.min(Math.abs(a - this.x), 1e3), Math.min(Math.abs(o - this.y), 1e3)), 300) : n,
            this.currentPage = {
                x: a,
                y: o,
                pageX: t,
                pageY: e
            },
            this.scrollTo(a, o, n, i)
        },
        next: function(t, e) {
            var n = this.currentPage.pageX
              , i = this.currentPage.pageY;
            n++,
            n >= this.pages.length && this.hasVerticalScroll && (n = 0,
            i++),
            this.goToPage(n, i, t, e)
        },
        prev: function(t, e) {
            var n = this.currentPage.pageX
              , i = this.currentPage.pageY;
            n--,
            n < 0 && this.hasVerticalScroll && (n = 0,
            i--),
            this.goToPage(n, i, t, e)
        },
        _initKeys: function() {
            var e, n = {
                pageUp: 33,
                pageDown: 34,
                end: 35,
                home: 36,
                left: 37,
                up: 38,
                right: 39,
                down: 40
            };
            if ("object" == typeof this.options.keyBindings)
                for (e in this.options.keyBindings)
                    "string" == typeof this.options.keyBindings[e] && (this.options.keyBindings[e] = this.options.keyBindings[e].toUpperCase().charCodeAt(0));
            else
                this.options.keyBindings = {};
            for (e in n)
                this.options.keyBindings[e] = this.options.keyBindings[e] || n[e];
            o.addEvent(t, "keydown", this),
            this.on("destroy", function() {
                o.removeEvent(t, "keydown", this)
            })
        },
        _key: function(t) {
            if (this.enabled) {
                var e, n = this.options.snap, i = n ? this.currentPage.pageX : this.x, a = n ? this.currentPage.pageY : this.y, r = o.getTime(), s = this.keyTime || 0;
                switch (this.options.useTransition && this.isInTransition && (e = this.getComputedPosition(),
                this._translate(Math.round(e.x), Math.round(e.y)),
                this.isInTransition = !1),
                this.keyAcceleration = r - s < 200 ? Math.min(this.keyAcceleration + .25, 50) : 0,
                t.keyCode) {
                case this.options.keyBindings.pageUp:
                    this.hasHorizontalScroll && !this.hasVerticalScroll ? i += n ? 1 : this.wrapperWidth : a += n ? 1 : this.wrapperHeight;
                    break;
                case this.options.keyBindings.pageDown:
                    this.hasHorizontalScroll && !this.hasVerticalScroll ? i -= n ? 1 : this.wrapperWidth : a -= n ? 1 : this.wrapperHeight;
                    break;
                case this.options.keyBindings.end:
                    i = n ? this.pages.length - 1 : this.maxScrollX,
                    a = n ? this.pages[0].length - 1 : this.maxScrollY;
                    break;
                case this.options.keyBindings.home:
                    i = 0,
                    a = 0;
                    break;
                case this.options.keyBindings.left:
                    i += n ? -1 : 5 + this.keyAcceleration >> 0;
                    break;
                case this.options.keyBindings.up:
                    a += n ? 1 : 5 + this.keyAcceleration >> 0;
                    break;
                case this.options.keyBindings.right:
                    i -= n ? -1 : 5 + this.keyAcceleration >> 0;
                    break;
                case this.options.keyBindings.down:
                    a -= n ? 1 : 5 + this.keyAcceleration >> 0;
                    break;
                default:
                    return
                }
                if (n)
                    return void this.goToPage(i, a);
                i > 0 ? (i = 0,
                this.keyAcceleration = 0) : i < this.maxScrollX && (i = this.maxScrollX,
                this.keyAcceleration = 0),
                a > 0 ? (a = 0,
                this.keyAcceleration = 0) : a < this.maxScrollY && (a = this.maxScrollY,
                this.keyAcceleration = 0),
                this.scrollTo(i, a, 0),
                this.keyTime = r
            }
        },
        _animate: function(t, e, n, i) {
            function r() {
                var d, u, m, f = o.getTime();
                if (f >= p)
                    return s.isAnimating = !1,
                    s._translate(t, e),
                    void (s.resetPosition(s.options.bounceTime) || s._execEvent("scrollEnd"));
                f = (f - h) / n,
                m = i(f),
                d = (t - l) * m + l,
                u = (e - c) * m + c,
                s._translate(d, u),
                s.isAnimating && a(r),
                3 === s.options.probeType && s._execEvent("scroll")
            }
            var s = this
              , l = this.x
              , c = this.y
              , h = o.getTime()
              , p = h + n;
            this.isAnimating = !0,
            r()
        },
        handleEvent: function(t) {
            switch (t.type) {
            case "touchstart":
            case "pointerdown":
            case "MSPointerDown":
            case "mousedown":
                this._start(t);
                break;
            case "touchmove":
            case "pointermove":
            case "MSPointerMove":
            case "mousemove":
                this._move(t);
                break;
            case "touchend":
            case "pointerup":
            case "MSPointerUp":
            case "mouseup":
            case "touchcancel":
            case "pointercancel":
            case "MSPointerCancel":
            case "mousecancel":
                this._end(t);
                break;
            case "orientationchange":
            case "resize":
                this._resize();
                break;
            case "transitionend":
            case "webkitTransitionEnd":
            case "oTransitionEnd":
            case "MSTransitionEnd":
                this._transitionEnd(t);
                break;
            case "wheel":
            case "DOMMouseScroll":
            case "mousewheel":
                this._wheel(t);
                break;
            case "keydown":
                this._key(t);
                break;
            case "click":
                t._constructed || (t.preventDefault(),
                t.stopPropagation())
            }
        }
    },
    i.prototype = {
        handleEvent: function(t) {
            switch (t.type) {
            case "touchstart":
            case "pointerdown":
            case "MSPointerDown":
            case "mousedown":
                this._start(t);
                break;
            case "touchmove":
            case "pointermove":
            case "MSPointerMove":
            case "mousemove":
                this._move(t);
                break;
            case "touchend":
            case "pointerup":
            case "MSPointerUp":
            case "mouseup":
            case "touchcancel":
            case "pointercancel":
            case "MSPointerCancel":
            case "mousecancel":
                this._end(t)
            }
        },
        destroy: function() {
            this.options.interactive && (o.removeEvent(this.indicator, "touchstart", this),
            o.removeEvent(this.indicator, o.prefixPointerEvent("pointerdown"), this),
            o.removeEvent(this.indicator, "mousedown", this),
            o.removeEvent(t, "touchmove", this),
            o.removeEvent(t, o.prefixPointerEvent("pointermove"), this),
            o.removeEvent(t, "mousemove", this),
            o.removeEvent(t, "touchend", this),
            o.removeEvent(t, o.prefixPointerEvent("pointerup"), this),
            o.removeEvent(t, "mouseup", this)),
            this.options.defaultScrollbars && this.wrapper.parentNode.removeChild(this.wrapper)
        },
        _start: function(e) {
            var n = e.touches ? e.touches[0] : e;
            e.preventDefault(),
            e.stopPropagation(),
            this.transitionTime(),
            this.initiated = !0,
            this.moved = !1,
            this.lastPointX = n.pageX,
            this.lastPointY = n.pageY,
            this.startTime = o.getTime(),
            this.options.disableTouch || o.addEvent(t, "touchmove", this),
            this.options.disablePointer || o.addEvent(t, o.prefixPointerEvent("pointermove"), this),
            this.options.disableMouse || o.addEvent(t, "mousemove", this),
            this.scroller._execEvent("beforeScrollStart")
        },
        _move: function(t) {
            var e, n, i, a, r = t.touches ? t.touches[0] : t, s = o.getTime();
            this.moved || this.scroller._execEvent("scrollStart"),
            this.moved = !0,
            e = r.pageX - this.lastPointX,
            this.lastPointX = r.pageX,
            n = r.pageY - this.lastPointY,
            this.lastPointY = r.pageY,
            i = this.x + e,
            a = this.y + n,
            this._pos(i, a),
            1 === this.scroller.options.probeType && s - this.startTime > 300 ? (this.startTime = s,
            this.scroller._execEvent("scroll")) : this.scroller.options.probeType > 1 && this.scroller._execEvent("scroll"),
            t.preventDefault(),
            t.stopPropagation()
        },
        _end: function(e) {
            if (this.initiated) {
                if (this.initiated = !1,
                e.preventDefault(),
                e.stopPropagation(),
                o.removeEvent(t, "touchmove", this),
                o.removeEvent(t, o.prefixPointerEvent("pointermove"), this),
                o.removeEvent(t, "mousemove", this),
                this.scroller.options.snap) {
                    var n = this.scroller._nearestSnap(this.scroller.x, this.scroller.y)
                      , i = this.options.snapSpeed || Math.max(Math.max(Math.min(Math.abs(this.scroller.x - n.x), 1e3), Math.min(Math.abs(this.scroller.y - n.y), 1e3)), 300);
                    this.scroller.x === n.x && this.scroller.y === n.y || (this.scroller.directionX = 0,
                    this.scroller.directionY = 0,
                    this.scroller.currentPage = n,
                    this.scroller.scrollTo(n.x, n.y, i, this.scroller.options.bounceEasing))
                }
                this.moved && this.scroller._execEvent("scrollEnd")
            }
        },
        transitionTime: function(t) {
            t = t || 0,
            this.indicatorStyle[o.style.transitionDuration] = t + "ms",
            !t && o.isBadAndroid && (this.indicatorStyle[o.style.transitionDuration] = "0.001s")
        },
        transitionTimingFunction: function(t) {
            this.indicatorStyle[o.style.transitionTimingFunction] = t
        },
        refresh: function() {
            this.transitionTime(),
            this.options.listenX && !this.options.listenY ? this.indicatorStyle.display = this.scroller.hasHorizontalScroll ? "block" : "none" : this.options.listenY && !this.options.listenX ? this.indicatorStyle.display = this.scroller.hasVerticalScroll ? "block" : "none" : this.indicatorStyle.display = this.scroller.hasHorizontalScroll || this.scroller.hasVerticalScroll ? "block" : "none",
            this.scroller.hasHorizontalScroll && this.scroller.hasVerticalScroll ? (o.addClass(this.wrapper, "iScrollBothScrollbars"),
            o.removeClass(this.wrapper, "iScrollLoneScrollbar"),
            this.options.defaultScrollbars && this.options.customStyle && (this.options.listenX ? this.wrapper.style.right = "8px" : this.wrapper.style.bottom = "8px")) : (o.removeClass(this.wrapper, "iScrollBothScrollbars"),
            o.addClass(this.wrapper, "iScrollLoneScrollbar"),
            this.options.defaultScrollbars && this.options.customStyle && (this.options.listenX ? this.wrapper.style.right = "2px" : this.wrapper.style.bottom = "2px")),
            this.options.listenX && (this.wrapperWidth = this.wrapper.clientWidth,
            this.options.resize ? (this.indicatorWidth = Math.max(Math.round(this.wrapperWidth * this.wrapperWidth / (this.scroller.scrollerWidth || this.wrapperWidth || 1)), 8),
            this.indicatorStyle.width = this.indicatorWidth + "px") : this.indicatorWidth = this.indicator.clientWidth,
            this.maxPosX = this.wrapperWidth - this.indicatorWidth,
            "clip" === this.options.shrink ? (this.minBoundaryX = 8 - this.indicatorWidth,
            this.maxBoundaryX = this.wrapperWidth - 8) : (this.minBoundaryX = 0,
            this.maxBoundaryX = this.maxPosX),
            this.sizeRatioX = this.options.speedRatioX || this.scroller.maxScrollX && this.maxPosX / this.scroller.maxScrollX),
            this.options.listenY && (this.wrapperHeight = this.wrapper.clientHeight,
            this.options.resize ? (this.indicatorHeight = Math.max(Math.round(this.wrapperHeight * this.wrapperHeight / (this.scroller.scrollerHeight || this.wrapperHeight || 1)), 8),
            this.indicatorStyle.height = this.indicatorHeight + "px") : this.indicatorHeight = this.indicator.clientHeight,
            this.maxPosY = this.wrapperHeight - this.indicatorHeight,
            "clip" === this.options.shrink ? (this.minBoundaryY = 8 - this.indicatorHeight,
            this.maxBoundaryY = this.wrapperHeight - 8) : (this.minBoundaryY = 0,
            this.maxBoundaryY = this.maxPosY),
            this.maxPosY = this.wrapperHeight - this.indicatorHeight,
            this.sizeRatioY = this.options.speedRatioY || this.scroller.maxScrollY && this.maxPosY / this.scroller.maxScrollY),
            this.updatePosition()
        },
        updatePosition: function() {
            var t = this.options.listenX && Math.round(this.sizeRatioX * this.scroller.x) || 0
              , e = this.options.listenY && Math.round(this.sizeRatioY * this.scroller.y) || 0;
            this.options.ignoreBoundaries || (t < this.minBoundaryX ? ("scale" === this.options.shrink && (this.width = Math.max(this.indicatorWidth + t, 8),
            this.indicatorStyle.width = this.width + "px"),
            t = this.minBoundaryX) : t > this.maxBoundaryX ? "scale" === this.options.shrink ? (this.width = Math.max(this.indicatorWidth - (t - this.maxPosX), 8),
            this.indicatorStyle.width = this.width + "px",
            t = this.maxPosX + this.indicatorWidth - this.width) : t = this.maxBoundaryX : "scale" === this.options.shrink && this.width !== this.indicatorWidth && (this.width = this.indicatorWidth,
            this.indicatorStyle.width = this.width + "px"),
            e < this.minBoundaryY ? ("scale" === this.options.shrink && (this.height = Math.max(this.indicatorHeight + 3 * e, 8),
            this.indicatorStyle.height = this.height + "px"),
            e = this.minBoundaryY) : e > this.maxBoundaryY ? "scale" === this.options.shrink ? (this.height = Math.max(this.indicatorHeight - 3 * (e - this.maxPosY), 8),
            this.indicatorStyle.height = this.height + "px",
            e = this.maxPosY + this.indicatorHeight - this.height) : e = this.maxBoundaryY : "scale" === this.options.shrink && this.height !== this.indicatorHeight && (this.height = this.indicatorHeight,
            this.indicatorStyle.height = this.height + "px")),
            this.x = t,
            this.y = e,
            this.scroller.options.useTransform ? this.indicatorStyle[o.style.transform] = "translate(" + t + "px," + e + "px)" + this.scroller.translateZ : (this.indicatorStyle.left = t + "px",
            this.indicatorStyle.top = e + "px")
        },
        _pos: function(t, e) {
            t < 0 ? t = 0 : t > this.maxPosX && (t = this.maxPosX),
            e < 0 ? e = 0 : e > this.maxPosY && (e = this.maxPosY),
            t = this.options.listenX ? Math.round(t / this.sizeRatioX) : this.scroller.x,
            e = this.options.listenY ? Math.round(e / this.sizeRatioY) : this.scroller.y,
            this.scroller.scrollTo(t, e)
        },
        fade: function(t, e) {
            if (!e || this.visible) {
                clearTimeout(this.fadeTimeout),
                this.fadeTimeout = null;
                var n = t ? 250 : 500
                  , i = t ? 0 : 300;
                t = t ? "1" : "0",
                this.wrapperStyle[o.style.transitionDuration] = n + "ms",
                this.fadeTimeout = setTimeout(function(t) {
                    this.wrapperStyle.opacity = t,
                    this.visible = +t
                }
                .bind(this, t), i)
            }
        }
    },
    e.utils = o,
    t.IScroll = e
}(window),
function($) {
    "use strict";
    function t(t) {
        var e = Array.apply(null, arguments);
        e.shift();
        var i;
        return this.each(function() {
            var a = $(this)
              , o = $.extend({}, a.dataset(), "object" == typeof t && t)
              , r = a.data("scroller");
            if (r || a.data("scroller", r = new n(this,o)),
            "string" == typeof t && "function" == typeof r[t] && void 0 !== (i = r[t].apply(r, e)))
                return !1
        }),
        void 0 !== i ? i : this
    }
    var e = {
        scrollTop: $.fn.scrollTop,
        scrollLeft: $.fn.scrollLeft
    };
    !function() {
        $.extend($.fn, {
            scrollTop: function(t, n) {
                if (this.length) {
                    var i = this.data("scroller");
                    return i && i.scroller ? i.scrollTop(t, n) : e.scrollTop.apply(this, arguments)
                }
            }
        }),
        $.extend($.fn, {
            scrollLeft: function(t, n) {
                if (this.length) {
                    var i = this.data("scroller");
                    return i && i.scroller ? i.scrollLeft(t, n) : e.scrollLeft.apply(this, arguments)
                }
            }
        })
    }();
    var n = function(t, e) {
        var n = this.$pageContent = $(t);
        this.options = $.extend({}, this._defaults, e);
        var i = this.options.type;
        if ("js" === i || "auto" === i && $.device.android && $.compareVersion("4.4.0", $.device.osVersion) > -1 || "auto" === i && $.device.ios && $.compareVersion("6.0.0", $.device.osVersion) > -1) {
            if (n.find(".content-inner")[0] || (n.children().length < 1 ? n.children().wrapAll('<div class="content-inner"></div>') : n.html('<div class="content-inner">' + n.html() + "</div>")),
            n.hasClass("pull-to-refresh-content")) {
                var a = $(window).height() + (n.prev().hasClass(".bar") ? 1 : 61);
                n.find(".content-inner").css("min-height", a + "px")
            }
            var o = $(t).hasClass("pull-to-refresh-content")
              , r = 0 === n.find(".fixed-tab").length
              , s = {
                probeType: 1,
                mouseWheel: !0,
                click: $.device.androidChrome,
                useTransform: r,
                scrollX: !0
            };
            o && (s.ptr = !0,
            s.ptrOffset = 44),
            this.scroller = new IScroll(t,s),
            this._bindEventToDomWhenJs(),
            $.initPullToRefresh = $._pullToRefreshJSScroll.initPullToRefresh,
            $.pullToRefreshDone = $._pullToRefreshJSScroll.pullToRefreshDone,
            $.pullToRefreshTrigger = $._pullToRefreshJSScroll.pullToRefreshTrigger,
            $.destroyToRefresh = $._pullToRefreshJSScroll.destroyToRefresh,
            n.addClass("javascript-scroll"),
            r || n.find(".content-inner").css({
                width: "100%",
                position: "absolute"
            });
            var l = this.$pageContent[0].scrollTop;
            l && (this.$pageContent[0].scrollTop = 0,
            this.scrollTop(l))
        } else
            n.addClass("native-scroll")
    };
    n.prototype = {
        _defaults: {
            type: "native"
        },
        _bindEventToDomWhenJs: function() {
            if (this.scroller) {
                var t = this;
                this.scroller.on("scrollStart", function() {
                    t.$pageContent.trigger("scrollstart")
                }),
                this.scroller.on("scroll", function() {
                    t.$pageContent.trigger("scroll")
                }),
                this.scroller.on("scrollEnd", function() {
                    t.$pageContent.trigger("scrollend")
                })
            }
        },
        scrollTop: function(t, e) {
            return this.scroller ? void 0 === t ? -1 * this.scroller.getComputedPosition().y : (this.scroller.scrollTo(0, -1 * t, e),
            this) : this.$pageContent.scrollTop(t, e)
        },
        scrollLeft: function(t, e) {
            return this.scroller ? void 0 === t ? -1 * this.scroller.getComputedPosition().x : (this.scroller.scrollTo(-1 * t, 0),
            this) : this.$pageContent.scrollTop(t, e)
        },
        on: function(t, e) {
            return this.scroller ? this.scroller.on(t, function() {
                e.call(this.wrapper)
            }) : this.$pageContent.on(t, e),
            this
        },
        off: function(t, e) {
            return this.scroller ? this.scroller.off(t, e) : this.$pageContent.off(t, e),
            this
        },
        refresh: function() {
            return this.scroller && this.scroller.refresh(),
            this
        },
        scrollHeight: function() {
            return this.scroller ? this.scroller.scrollerHeight : this.$pageContent[0].scrollHeight
        }
    };
    var i = $.fn.scroller;
    $.fn.scroller = t,
    $.fn.scroller.Constructor = n,
    $.fn.scroller.noConflict = function() {
        return $.fn.scroller = i,
        this
    }
    ,
    $(function() {
        $('[data-toggle="scroller"]').scroller()
    }),
    $.refreshScroller = function(t) {
        t ? $(t).scroller("refresh") : $(".javascript-scroll").each(function() {
            $(this).scroller("refresh")
        })
    }
    ,
    $.initScroller = function(t) {
        this.options = $.extend({}, "object" == typeof t && t),
        $('[data-toggle="scroller"],.content').scroller(t)
    }
    ,
    $.getScroller = function(t) {
        return t = t.hasClass("content") ? t : t.parents(".content"),
        t ? $(t).data("scroller") : $(".content.javascript-scroll").data("scroller")
    }
    ,
    $.detectScrollerType = function(t) {
        if (t)
            return $(t).data("scroller") && $(t).data("scroller").scroller ? "js" : "native"
    }
}(Zepto),
function($) {
    "use strict";
    var t = function(t, e, n) {
        var i = $(t);
        if (2 === arguments.length && "boolean" == typeof e && (n = e),
        0 === i.length)
            return !1;
        if (i.hasClass("active"))
            return n && i.trigger("show"),
            !1;
        var a = i.parent(".tabs");
        if (0 === a.length)
            return !1;
        var o = a.children(".tab.active").removeClass("active");
        if (i.addClass("active"),
        i.trigger("show"),
        e ? e = $(e) : (!(e = $("string" == typeof t ? '.tab-link[href="' + t + '"]' : '.tab-link[href="#' + i.attr("id") + '"]')) || e && 0 === e.length) && $("[data-tab]").each(function() {
            i.is($(this).attr("data-tab")) && (e = $(this))
        }),
        0 !== e.length) {
            var r;
            if (o && o.length > 0) {
                var s = o.attr("id");
                s && (r = $('.tab-link[href="#' + s + '"]')),
                (!r || r && 0 === r.length) && $("[data-tab]").each(function() {
                    o.is($(this).attr("data-tab")) && (r = $(this))
                })
            }
            return e && e.length > 0 && e.addClass("active"),
            r && r.length > 0 && r.removeClass("active"),
            e.trigger("active"),
            !0
        }
    }
      , e = $.showTab;
    $.showTab = t,
    $.showTab.noConflict = function() {
        return $.showTab = e,
        this
    }
    ,
    $(document).on("click", ".tab-link", function(e) {
        e.preventDefault();
        var n = $(this);
        t(n.data("tab") || n.attr("href"), n)
    })
}(Zepto),
function($) {
    "use strict";
    function t(t) {
        Array.apply(null, arguments).shift(),
        this.each(function() {
            var n = $(this)
              , i = $.extend({}, n.dataset(), "object" == typeof t && t)
              , a = n.data("fixedtab");
            a || n.data("fixedtab", a = new e(this,i))
        })
    }
    $.initFixedTab = function() {
        0 !== $(".fixed-tab").length && $(".fixed-tab").fixedTab()
    }
    ;
    var e = function(t, e) {
        var n = this.$pageContent = $(t)
          , i = n.clone()
          , a = n[0].getBoundingClientRect().top;
        i.css("visibility", "hidden"),
        this.options = $.extend({}, this._defaults, {
            fixedTop: a,
            shadow: i,
            offset: 0
        }, e),
        this._bindEvents()
    };
    e.prototype = {
        _defaults: {
            offset: 0
        },
        _bindEvents: function() {
            this.$pageContent.parents(".content").on("scroll", this._scrollHandler.bind(this)),
            this.$pageContent.on("active", ".tab-link", this._tabLinkHandler.bind(this))
        },
        _tabLinkHandler: function(t) {
            var e = $(t.target).parents(".buttons-fixed").length > 0
              , n = this.options.fixedTop
              , i = this.options.offset;
            $.refreshScroller(),
            e && this.$pageContent.parents(".content").scrollTop(n - i)
        },
        _scrollHandler: function(t) {
            var e = $(t.target)
              , n = this.$pageContent
              , i = this.options.shadow
              , a = this.options.offset
              , o = this.options.fixedTop;
            e.scrollTop() >= o - a ? (i.insertAfter(n),
            n.addClass("buttons-fixed").css("top", a)) : (i.remove(),
            n.removeClass("buttons-fixed").css("top", 0))
        }
    },
    $.fn.fixedTab = t,
    $.fn.fixedTab.Constructor = e,
    $(document).on("pageInit", function() {
        $.initFixedTab()
    })
}(Zepto),
function($) {
    "use strict";
    var t = 0
      , e = function(e) {
        function n() {
            l.hasClass("refreshing") || (-1 * s.scrollTop() >= 44 ? l.removeClass("pull-down").addClass("pull-up") : l.removeClass("pull-up").addClass("pull-down"))
        }
        function i() {
            l.hasClass("refreshing") || (l.removeClass("pull-down pull-up"),
            l.addClass("refreshing transitioning"),
            l.trigger("refresh"),
            t = +new Date)
        }
        function a() {
            s.off("scroll", n),
            s.scroller.off("ptr", i)
        }
        var o = $(e);
        if (o.hasClass("pull-to-refresh-content") || (o = o.find(".pull-to-refresh-content")),
        o && 0 !== o.length) {
            var r = o.hasClass("content") ? o : o.parents(".content")
              , s = $.getScroller(r[0]);
            if (s) {
                var l = o;
                s.on("scroll", n),
                s.scroller.on("ptr", i),
                o[0].destroyPullToRefresh = a
            }
        }
    }
      , n = function(e) {
        if (e = $(e),
        0 === e.length && (e = $(".pull-to-refresh-content.refreshing")),
        0 !== e.length) {
            var n = +new Date - t
              , i = n > 1e3 ? 0 : 1e3 - n
              , a = $.getScroller(e);
            setTimeout(function() {
                a.refresh(),
                e.removeClass("refreshing"),
                e.transitionEnd(function() {
                    e.removeClass("transitioning")
                })
            }, i)
        }
    }
      , i = function(t) {
        if (t = $(t),
        0 === t.length && (t = $(".pull-to-refresh-content")),
        !t.hasClass("refreshing")) {
            t.addClass("refreshing");
            $.getScroller(t).scrollTop(45, 200),
            t.trigger("refresh")
        }
    }
      , a = function(t) {
        t = $(t);
        var e = t.hasClass("pull-to-refresh-content") ? t : t.find(".pull-to-refresh-content");
        0 !== e.length && e[0].destroyPullToRefresh && e[0].destroyPullToRefresh()
    };
    $._pullToRefreshJSScroll = {
        initPullToRefresh: e,
        pullToRefreshDone: n,
        pullToRefreshTrigger: i,
        destroyPullToRefresh: a
    }
}(Zepto),
function($) {
    "use strict";
    $.initPullToRefresh = function(t) {
        function e(t) {
            if (r) {
                if (!$.device.android)
                    return;
                if ("targetTouches"in t && t.targetTouches.length > 1)
                    return
            }
            s = !1,
            r = !0,
            l = void 0,
            m = void 0,
            g.x = "touchstart" === t.type ? t.targetTouches[0].pageX : t.pageX,
            g.y = "touchstart" === t.type ? t.targetTouches[0].pageY : t.pageY,
            h = (new Date).getTime(),
            p = $(this)
        }
        function n(t) {
            if (r) {
                var e = "touchmove" === t.type ? t.targetTouches[0].pageX : t.pageX
                  , n = "touchmove" === t.type ? t.targetTouches[0].pageY : t.pageY;
                if (void 0 === l && (l = !!(l || Math.abs(n - g.y) > Math.abs(e - g.x))),
                !l)
                    return void (r = !1);
                if (u = p[0].scrollTop,
                void 0 === m && 0 !== u && (m = !0),
                !s) {
                    if (p.removeClass("transitioning"),
                    u > p[0].offsetHeight)
                        return void (r = !1);
                    v && (f = p.attr("data-ptr-distance"),
                    f.indexOf("%") >= 0 && (f = p[0].offsetHeight * parseInt(f, 10) / 100)),
                    k = p.hasClass("refreshing") ? f : 0,
                    w = p[0].scrollHeight === p[0].offsetHeight || !$.device.ios,
                    w = !0
                }
                if (s = !0,
                !((c = n - g.y) > 0 && u <= 0 || u < 0))
                    return p.removeClass("pull-up pull-down"),
                    void (y = !1);
                $.device.ios && parseInt($.device.osVersion.split(".")[0], 10) > 7 && 0 === u && !m && (w = !0),
                w && (t.preventDefault(),
                d = Math.pow(c, .85) + k,
                p.transform("translate3d(0," + d + "px,0)")),
                w && Math.pow(c, .85) > f || !w && c >= 2 * f ? (y = !0,
                p.addClass("pull-up").removeClass("pull-down")) : (y = !1,
                p.removeClass("pull-up").addClass("pull-down"))
            }
        }
        function i() {
            if (!r || !s)
                return r = !1,
                void (s = !1);
            if (d && (p.addClass("transitioning"),
            d = 0),
            p.transform(""),
            y) {
                if (p.hasClass("refreshing"))
                    return;
                p.addClass("refreshing"),
                p.trigger("refresh")
            } else
                p.removeClass("pull-down");
            r = !1,
            s = !1
        }
        function a() {
            o.off($.touchEvents.start, e),
            o.off($.touchEvents.move, n),
            o.off($.touchEvents.end, i)
        }
        var o = $(t);
        if (o.hasClass("pull-to-refresh-content") || (o = o.find(".pull-to-refresh-content")),
        o && 0 !== o.length) {
            var r, s, l, c, h, p, d, u, m, f, v, g = {}, y = !1, w = !1, k = 0;
            p = o,
            p.attr("data-ptr-distance") ? v = !0 : f = 44,
            o.on($.touchEvents.start, e),
            o.on($.touchEvents.move, n),
            o.on($.touchEvents.end, i),
            o[0].destroyPullToRefresh = a
        }
    }
    ,
    $.pullToRefreshDone = function(t) {
        $(window).scrollTop(0),
        t = $(t),
        0 === t.length && (t = $(".pull-to-refresh-content.refreshing")),
        t.removeClass("refreshing").addClass("transitioning"),
        t.transitionEnd(function() {
            t.removeClass("transitioning pull-up pull-down")
        })
    }
    ,
    $.pullToRefreshTrigger = function(t) {
        t = $(t),
        0 === t.length && (t = $(".pull-to-refresh-content")),
        t.hasClass("refreshing") || (t.addClass("transitioning refreshing"),
        t.trigger("refresh"))
    }
    ,
    $.destroyPullToRefresh = function(t) {
        t = $(t);
        var e = t.hasClass("pull-to-refresh-content") ? t : t.find(".pull-to-refresh-content");
        0 !== e.length && e[0].destroyPullToRefresh && e[0].destroyPullToRefresh()
    }
}(Zepto),
function($) {
    "use strict";
    function t() {
        var t, e = $(this), n = $.getScroller(e), i = n.scrollTop(), a = n.scrollHeight(), o = e[0].offsetHeight, r = e[0].getAttribute("data-distance"), s = e.find(".virtual-list"), l = e.hasClass("infinite-scroll-top");
        if (r || (r = 50),
        "string" == typeof r && r.indexOf("%") >= 0 && (r = parseInt(r, 10) / 100 * o),
        r > o && (r = o),
        l)
            i < r && e.trigger("infinite");
        else if (i + o >= a - r) {
            if (s.length > 0 && (t = s[0].f7VirtualList) && !t.reachEnd)
                return;
            e.trigger("infinite")
        }
    }
    $.attachInfiniteScroll = function(e) {
        $.getScroller(e).on("scroll", t)
    }
    ,
    $.detachInfiniteScroll = function(e) {
        $.getScroller(e).off("scroll", t)
    }
    ,
    $.initInfiniteScroll = function(t) {
        function e() {
            $.detachInfiniteScroll(n),
            t.off("pageBeforeRemove", e)
        }
        t = $(t);
        var n = t.hasClass("infinite-scroll") ? t : t.find(".infinite-scroll");
        0 !== n.length && ($.attachInfiniteScroll(n),
        t.forEach(function(t) {
            if ($(t).hasClass("infinite-scroll-top")) {
                var e = t.scrollHeight - t.clientHeight;
                $(t).scrollTop(e)
            }
        }),
        t.on("pageBeforeRemove", e))
    }
}(Zepto),
function($) {
    "use strict";
    $(function() {
        $(document).on("focus", ".searchbar input", function(t) {
            $(t.target).parents(".searchbar").addClass("searchbar-active")
        }),
        $(document).on("click", ".searchbar-cancel", function(t) {
            $(t.target).parents(".searchbar").removeClass("searchbar-active")
        }),
        $(document).on("blur", ".searchbar input", function(t) {
            $(t.target).parents(".searchbar").removeClass("searchbar-active")
        })
    })
}(Zepto),
function($) {
    "use strict";
    $.allowPanelOpen = !0,
    $.openPanel = function(t) {
        function e() {
            a.transitionEnd(function(n) {
                n.target === a[0] ? (t.hasClass("active") ? t.trigger("opened") : t.trigger("closed"),
                $.allowPanelOpen = !0) : e()
            })
        }
        if (!$.allowPanelOpen)
            return !1;
        "left" !== t && "right" !== t || (t = ".panel-" + t),
        t = t ? $(t) : $(".panel").eq(0);
        var n = t.hasClass("panel-right") ? "right" : "left";
        if (0 === t.length || t.hasClass("active"))
            return !1;
        $.closePanel(),
        $.allowPanelOpen = !1;
        var i = t.hasClass("panel-reveal") ? "reveal" : "cover";
        t.css({
            display: "block"
        }).addClass("active"),
        t.trigger("open");
        var a = (t[0].clientLeft,
        "reveal" === i ? $($.getCurrentPage()) : t);
        return e(),
        $(document.body).addClass("with-panel-" + n + "-" + i),
        !0
    }
    ,
    $.closePanel = function() {
        var t = $(".panel.active");
        if (0 === t.length)
            return !1;
        var e = t.hasClass("panel-reveal") ? "reveal" : "cover"
          , n = t.hasClass("panel-left") ? "left" : "right";
        t.removeClass("active");
        var i = "reveal" === e ? $(".page") : t;
        t.trigger("close"),
        $.allowPanelOpen = !1,
        i.transitionEnd(function() {
            t.hasClass("active") || (t.css({
                display: ""
            }),
            t.trigger("closed"),
            $("body").removeClass("panel-closing"),
            $.allowPanelOpen = !0)
        }),
        $("body").addClass("panel-closing").removeClass("with-panel-" + n + "-" + e)
    }
    ,
    $(document).on("click", ".open-panel", function(t) {
        var e = $(t.target).data("panel");
        $.openPanel(e)
    }),
    $(document).on("click", ".close-panel, .panel-overlay", function(t) {
        $.closePanel()
    }),
    $.initSwipePanels = function() {
        function t(t) {
            if ($.allowPanelOpen && (o || r) && !p && !($(".modal-in, .photo-browser-in").length > 0) && (s || r || !($(".panel.active").length > 0) || i.hasClass("active"))) {
                if (T.x = "touchstart" === t.type ? t.targetTouches[0].pageX : t.pageX,
                T.y = "touchstart" === t.type ? t.targetTouches[0].pageY : t.pageY,
                s || r) {
                    if ($(".panel.active").length > 0)
                        a = $(".panel.active").hasClass("panel-left") ? "left" : "right";
                    else {
                        if (r)
                            return;
                        a = o
                    }
                    if (!a)
                        return
                }
                if (i = $(".panel.panel-" + a),
                i[0]) {
                    if (g = i.hasClass("active"),
                    l && !g) {
                        if ("left" === a && T.x > l)
                            return;
                        if ("right" === a && T.x < window.innerWidth - l)
                            return
                    }
                    d = !1,
                    p = !0,
                    u = void 0,
                    m = (new Date).getTime(),
                    k = void 0
                }
            }
        }
        function e(t) {
            if (p && i[0] && !t.f7PreventPanelSwipe) {
                var e = "touchmove" === t.type ? t.targetTouches[0].pageX : t.pageX
                  , n = "touchmove" === t.type ? t.targetTouches[0].pageY : t.pageY;
                if (void 0 === u && (u = !!(u || Math.abs(n - T.y) > Math.abs(e - T.x))),
                u)
                    return void (p = !1);
                if (!k && (k = e > T.x ? "to-right" : "to-left",
                "left" === a && "to-left" === k && !i.hasClass("active") || "right" === a && "to-right" === k && !i.hasClass("active")))
                    return void (p = !1);
                if (h) {
                    return (new Date).getTime() - m < 300 && ("to-left" === k && ("right" === a && $.openPanel(a),
                    "left" === a && i.hasClass("active") && $.closePanel()),
                    "to-right" === k && ("left" === a && $.openPanel(a),
                    "right" === a && i.hasClass("active") && $.closePanel())),
                    p = !1,
                    void (d = !1)
                }
                d || (w = i.hasClass("panel-cover") ? "cover" : "reveal",
                g || (i.show(),
                x.show()),
                y = i[0].offsetWidth,
                i.transition(0)),
                d = !0,
                t.preventDefault();
                var o = g ? 0 : -c;
                "right" === a && (o = -o),
                f = e - T.x + o,
                "right" === a ? (v = f - (g ? y : 0),
                v > 0 && (v = 0),
                v < -y && (v = -y)) : (v = f + (g ? y : 0),
                v < 0 && (v = 0),
                v > y && (v = y)),
                "reveal" === w ? (C.transform("translate3d(" + v + "px,0,0)").transition(0),
                x.transform("translate3d(" + v + "px,0,0)")) : i.transform("translate3d(" + v + "px,0,0)").transition(0)
            }
        }
        function n(t) {
            if (!p || !d)
                return p = !1,
                void (d = !1);
            p = !1,
            d = !1;
            var e, n = (new Date).getTime() - m, o = 0 === v || Math.abs(v) === y;
            if (e = g ? v === -y ? "reset" : n < 300 && Math.abs(v) >= 0 || n >= 300 && Math.abs(v) <= y / 2 ? "left" === a && v === y ? "reset" : "swap" : "reset" : 0 === v ? "reset" : n < 300 && Math.abs(v) > 0 || n >= 300 && Math.abs(v) >= y / 2 ? "swap" : "reset",
            "swap" === e && ($.allowPanelOpen = !0,
            g ? ($.closePanel(),
            o && (i.css({
                display: ""
            }),
            $("body").removeClass("panel-closing"))) : $.openPanel(a),
            o && ($.allowPanelOpen = !0)),
            "reset" === e)
                if (g)
                    $.allowPanelOpen = !0,
                    $.openPanel(a);
                else if ($.closePanel(),
                o)
                    $.allowPanelOpen = !0,
                    i.css({
                        display: ""
                    });
                else {
                    var r = "reveal" === w ? C : i;
                    $("body").addClass("panel-closing"),
                    r.transitionEnd(function() {
                        $.allowPanelOpen = !0,
                        i.css({
                            display: ""
                        }),
                        $("body").removeClass("panel-closing")
                    })
                }
            "reveal" === w && (C.transition(""),
            C.transform("")),
            i.transition("").transform(""),
            x.css({
                display: ""
            }).transform("")
        }
        var i, a, o = $.smConfig.swipePanel, r = $.smConfig.swipePanelOnlyClose, s = !0, l = !1, c = 2, h = !1;
        if (o || r) {
            var p, d, u, m, f, v, g, y, w, k, x = $(".panel-overlay"), T = {}, C = $(".page");
            $(document).on($.touchEvents.start, t),
            $(document).on($.touchEvents.move, e),
            $(document).on($.touchEvents.end, n)
        }
    }
    ,
    $.initSwipePanels()
}(Zepto),
function($) {
    "use strict";
    $.lastPosition = function(t) {
        function e(t, e) {
            i.forEach(function(n, i) {
                if (0 !== $(n).length) {
                    var a = t
                      , o = $.fn.session_get(a);
                    e.find(n).scrollTop(parseInt(o))
                }
            })
        }
        function n(t, e) {
            var n = t;
            i.forEach(function(t, i) {
                0 !== $(t).length && $.fn.session_set(n, e.find(t).scrollTop())
            })
        }
        if ($.fn.support_storage()) {
            var i = t.needMemoryClass || [];
            $(window).off("beforePageSwitch").on("beforePageSwitch", function(t, e, i) {
                n(e, i)
            }),
            $(window).off("pageAnimationStart").on("pageAnimationStart", function(t, n, i) {
                e(n, i)
            })
        }
    }
}(Zepto),
function($) {
    "use strict";
    var t = function() {
        var t = $(".page-current");
        return t[0] || (t = $(".page").addClass("page-current")),
        t
    };
    $.initPage = function(e) {
        var n = t();
        n[0] || (n = $(document.body));
        var i = n.hasClass("content") ? n : n.find(".content");
        i.scroller(),
        $.initPullToRefresh(i),
        $.initInfiniteScroll(i),
        $.initCalendar(i),
        $.initSwiper && $.initSwiper(i)
    }
    ,
    $.smConfig.showPageLoadingIndicator && ($(window).on("pageSwitchStart", function() {
        $.smConfig.showPageLoadingCustomer ? $.showSwitchLoader() : $.showIndicator()
    }),
    $(window).on("pageLoadStart", function() {
        $.smConfig.showPageLoadingCustomer
    }),
    $(window).on("pageAnimationStart", function() {
        $.smConfig.showPageLoadingCustomer
    }),
    $(window).on("pageLoadCancel", function() {
        $.smConfig.showPageLoadingCustomer ? $.hideSwitchLoader() : $.hideIndicator()
    }),
    $(window).on("pageLoadComplete", function() {
        $.smConfig.showPageLoadingCustomer
    }),
    $(window).on("pageLoadError", function() {
        $.smConfig.showPageLoadingCustomer ? $.hideSwitchLoader() : $.hideIndicator(),
        $.toast("加载失败")
    })),
    $(window).on("pageAnimationStart", function(t, e, n) {
        $.closeModal(),
        $.closePanel(),
        $("body").removeClass("panel-closing"),
        $.allowPanelOpen = !0
    }),
    $(window).on("pageInit", function() {
        $.smConfig.showPageLoadingCustomer,
        $.lastPosition({
            needMemoryClass: [".content"]
        })
    }),
    window.addEventListener("pageshow", function(t) {
        t.persisted && location.reload()
    }),
    $.init = function() {
        var e = t()
          , n = e[0].id;
        $.initPage(),
        e.trigger("pageInit", [n, e])
    }
    ,
    $(function() {
        FastClick.attach(document.body),
        $.smConfig.autoInit && $.init(),
        $(document).on("pageInitInternal", function(t, e, n) {
            $.init()
        })
    })
}(Zepto),
function($) {
    "use strict";
    if ($.device.ios) {
        var t = function(t) {
            var e, n;
            (t = t || document.querySelector(t)) && t.addEventListener("touchstart", function(i) {
                e = i.touches[0].pageY,
                n = t.scrollTop,
                n <= 0 && (t.scrollTop = 1),
                n + t.offsetHeight >= t.scrollHeight && (t.scrollTop = t.scrollHeight - t.offsetHeight - 1)
            }, !1)
        }
          , e = function() {
            var e = $(".page-current").length > 0 ? ".page-current " : ""
              , n = $(e + ".content");
            new t(n[0])
        };
        $(document).on($.touchEvents.move, ".page-current .bar", function() {
            event.preventDefault()
        }),
        $(document).on("pageLoadComplete", function() {
            e()
        }),
        $(document).on("pageAnimationEnd", function() {
            e()
        }),
        e()
    }
}(Zepto);
