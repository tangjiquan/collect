!function(t) {
    "function" == typeof define && define.amd ? define(["zepto"], t) : t(window.jQuery || window.Zepto)
} (function($, t) {
    function e() {}
    function a(t, e) {
        return (e._$container == d ? ("innerHeight" in _ ? _.innerHeight: d.height()) + d.scrollTop() : e._$container.offset().top + e._$container.height()) <= t.offset().top - e.threshold
    }
    function r(t, e) {
        return (e._$container == d ? d.width() + ($.fn.scrollLeft ? d.scrollLeft() : _.pageXOffset) : e._$container.offset().left + e._$container.width()) <= t.offset().left - e.threshold
    }
    function n(t, e) {
        return (e._$container == d ? d.scrollTop() : e._$container.offset().top) >= t.offset().top + e.threshold + t.height()
    }
    function o(t, e) {
        return (e._$container == d ? $.fn.scrollLeft ? d.scrollLeft() : _.pageXOffset: e._$container.offset().left) >= t.offset().left + e.threshold + t.width()
    }
    function i(t, e) {
        var i = 0;
        t.each(function(l, c) {
            function f() {
                _.trigger("_lazyload_appear"),
                i = 0
            }
            var _ = t.eq(l);
            if (! (_.width() <= 0 && _.height() <= 0 || "none" === _.css("display"))) if (e.vertical_only) if (n(_, e));
            else if (a(_, e)) {
                if (++i > e.failure_limit) return ! 1
            } else f();
            else if (n(_, e) || o(_, e));
            else if (a(_, e) || r(_, e)) {
                if (++i > e.failure_limit) return ! 1
            } else f()
        })
    }
    function l(t) {
        return t.filter(function(e, a) {
            return ! t.eq(e)._lazyload_loadStarted
        })
    }
    function c(t, e) {
        function a() {
            i = 0,
            l = +new Date,
            o = t.apply(r, n),
            r = null,
            n = null
        }
        var r, n, o, i, l = 0;
        return function() {
            r = this,
            n = arguments;
            var t = new Date - l;
            return i || (t >= e ? a() : i = setTimeout(a, e - t)),
            o
        }
    }
    var f, _ = window,
    d = $(_),
    s = {
        threshold: 0,
        failure_limit: 0,
        event: "scroll",
        effect: "show",
        effect_params: null,
        container: _,
        data_attribute: "original",
        data_srcset_attribute: "original-srcset",
        skip_invisible: !0,
        appear: e,
        load: e,
        vertical_only: !1,
        check_appear_throttle_time: 300,
        url_rewriter_fn: e,
        no_fake_img_loader: !1,
        placeholder_data_img: "data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==",
        placeholder_real_img: "http://ditu.baidu.cn/yyfm/lazyload/0.0.1/img/placeholder.png"
    };
    f = function() {
        var t = Object.prototype.toString;
        return function(e) {
            return t.call(e).replace("[object ", "").replace("]", "")
        }
    } (),
    $.fn.hasOwnProperty("lazyload") || ($.fn.lazyload = function(t) {
        var a, r, n, o = this;
        return $.isPlainObject(t) || (t = {}),
        $.each(s,
        function(e, a) {
            var r = f(t[e]); - 1 != $.inArray(e, ["threshold", "failure_limit", "check_appear_throttle_time"]) ? "String" == r ? t[e] = parseInt(t[e], 10) : "Number" != r && (t[e] = a) : "container" == e ? (t.hasOwnProperty(e) ? t[e] == _ || t[e] == document ? t._$container = d: t._$container = $(t[e]) : t._$container = d, delete t.container) : !s.hasOwnProperty(e) || t.hasOwnProperty(e) && r == f(s[e]) || (t[e] = a)
        }),
        a = "scroll" == t.event,
        n = 0 == t.check_appear_throttle_time ? i: c(i, t.check_appear_throttle_time),
        r = a || "scrollstart" == t.event || "scrollstop" == t.event,
        o.each(function(a, n) {
            var i = this,
            c = o.eq(a),
            f = c.attr("src"),
            _ = c.attr("data-" + t.data_attribute),
            d = t.url_rewriter_fn == e ? _: t.url_rewriter_fn.call(i, c, _),
            s = c.attr("data-" + t.data_srcset_attribute),
            u = c.is("img");
            if (1 == c._lazyload_loadStarted || f == d) return c._lazyload_loadStarted = !0,
            void(o = l(o));
            c._lazyload_loadStarted = !1,
            u && !f && c.one("error",
            function() {
                c.attr("src", t.placeholder_real_img)
            }).attr("src", t.placeholder_data_img),
            c.one("_lazyload_appear",
            function() {
                function a() {
                    r && c.hide(),
                    u ? (s && c.attr("srcset", s), d && c.attr("src", d)) : c.css("background-image", 'url("' + d + '")'),
                    r && c[t.effect].apply(c, n ? t.effect_params: []),
                    o = l(o)
                }
                var r, n = $.isArray(t.effect_params);
                c._lazyload_loadStarted || (r = "show" != t.effect && $.fn[t.effect] && (!t.effect_params || n && 0 == t.effect_params.length), t.appear != e && t.appear.call(i, c, o.length, t), c._lazyload_loadStarted = !0, t.no_fake_img_loader || s ? (t.load != e && c.one("load",
                function() {
                    t.load.call(i, c, o.length, t)
                }), a()) : $("<img />").one("load",
                function() {
                    a(),
                    t.load != e && t.load.call(i, c, o.length, t)
                }).attr("src", d))
            }),
            r || c.on(t.event,
            function() {
                c._lazyload_loadStarted || c.trigger("_lazyload_appear")
            })
        }),
        r && t._$container.on(t.event,
        function() {
            n(o, t)
        }),
        d.on("resize load",
        function() {
            n(o, t)
        }),
        $(function() {
            n(o, t)
        }),
        this
    })
});