+function($) {
    "use strict";
    var e = new Date
      , a = "01 02 03 04 05 06 07 08 09 10 11 12".split(" ")
      , t = function(e, a) {
        e = e.replace(new RegExp("-","gm"), "/") + "/01",
        a = a.replace(new RegExp("-","gm"), "/") + "/01";
        for (var t = new Date(e).getFullYear(), n = new Date(a).getFullYear(), l = [], s = t; s <= n; s++)
            l.push(s);
        return l
    }
      , n = function(e) {
        var a = e.cols[0].value + "-" + e.cols[1].value
          , t = new Date((e.cols[0].value + "-" + e.cols[1].value + "-01").replace(new RegExp("-","gm"), "/")).getTime()
          , n = new Date((e.params.minDate + "-01").replace(new RegExp("-","gm"), "/")).getTime()
          , l = new Date((e.params.maxDate + "-01").replace(new RegExp("-","gm"), "/")).getTime();
        return t < n && (e.cols[0].setValue(e.params.minDate.split("-")[0]),
        e.cols[1].setValue(e.params.minDate.split("-")[1]),
        a = e.params.minDate),
        t > l && (e.cols[0].setValue(e.params.maxDate.split("-")[0]),
        e.cols[1].setValue(e.params.maxDate.split("-")[1]),
        a = e.params.maxDate),
        a
    }
      , l = {
        change: null,
        confirm: null,
        minDate: "1950-01",
        maxDate: "2099-12",
        rotateEffect: !1,
        value: [e.getFullYear(), function(e) {
            return e < 10 ? "0" + e : e
        }(e.getMonth() + 1)],
        onChange: function(e, a, t) {
            var l = n(e);
            e.params.change && e.params.change(l)
        },
        onConfirm: function(e, a, t) {
            var l = n(e);
            e.params.confirm && e.params.confirm(l)
        },
        formatValue: function(e, a, t) {
            return t[0] + "-" + a[1]
        },
        cols: []
    };
    $.fn.dateMonthPicker = function(e) {
        return this.each(function() {
            if (this) {
                var n = $.extend(!0, {}, l)
                  , s = $.extend(n, e);
                s.cssClass = "customized-datePicker",
                s.cols = [{
                    values: t(s.minDate, s.maxDate)
                }, {
                    values: a
                }],
                e.value && (s.value = [e.value.split("-")[0], e.value.split("-")[1]]),
                $(this).picker(s),
                e.value && $(this).val(s.formatValue(s, s.value, s.value))
            }
        })
    }
}(Zepto),
function($) {
    "use strict";
    var e = new Date
      , a = function(e) {
        for (var a = [], t = 1; t <= (e || 31); t++)
            a.push(t < 10 ? "0" + t : t);
        return a
    }
      , t = function(e, t) {
        var n = new Date(t,parseInt(e) + 1 - 1,1)
          , l = new Date(n - 1);
        return a(l.getDate())
    }
      , n = function(e) {
        return e < 10 ? "0" + e : e
    }
      , l = "01 02 03 04 05 06 07 08 09 10 11 12".split(" ")
      , s = function(e, a) {
        for (var t = new Date(e.replace(new RegExp("-","gm"), "/")).getFullYear(), n = new Date(a.replace(new RegExp("-","gm"), "/")).getFullYear(), l = [], s = t; s <= n; s++)
            l.push(s);
        return l
    }
      , i = function(e) {
        var a = t(e.cols[1].value, e.cols[0].value)
          , n = e.cols[2].value;
        n > a.length && (n = a.length),
        e.cols[2].setValue(n);
        var l = e.cols[0].value + "-" + e.cols[1].value + "-" + n
          , s = new Date(l.replace(new RegExp("-","gm"), "/")).getTime()
          , i = new Date(e.params.minDate.replace(new RegExp("-","gm"), "/")).getTime()
          , r = new Date(e.params.maxDate.replace(new RegExp("-","gm"), "/")).getTime();
        return s < i && (e.cols[0].setValue(e.params.minDate.split("-")[0]),
        e.cols[1].setValue(e.params.minDate.split("-")[1]),
        e.cols[2].setValue(e.params.minDate.split("-")[2]),
        l = e.params.minDate),
        s > r && (e.cols[0].setValue(e.params.maxDate.split("-")[0]),
        e.cols[1].setValue(e.params.maxDate.split("-")[1]),
        e.cols[2].setValue(e.params.maxDate.split("-")[2]),
        l = e.params.maxDate),
        l
    }
      , r = {
        change: null,
        confirm: null,
        minDate: "1950-01-01",
        maxDate: "2099-12-31",
        rotateEffect: !1,
        value: [e.getFullYear(), n(e.getMonth() + 1), n(e.getDate())],
        onChange: function(e, a, t) {
            var n = i(e);
            e.params.change && e.params.change(n)
        },
        onConfirm: function(e, a, t) {
            var n = i(e);
            e.params.confirm && e.params.confirm(n)
        },
        formatValue: function(e, a, t) {
            return t[0] + "-" + a[1] + "-" + a[2]
        },
        cols: []
    };
    $.fn.datePicker = function(e) {
        return this.each(function() {
            if (this) {
                var t = $.extend(!0, {}, r)
                  , n = $.extend(t, e);
                n.cssClass = "customized-datePicker",
                n.cols = [{
                    values: s(n.minDate, n.maxDate)
                }, {
                    values: l
                }, {
                    values: a()
                }],
                e.value && (n.value = [e.value.split("-")[0], e.value.split("-")[1], e.value.split("-")[2]]),
                $(this).picker(n),
                e.value && $(this).val(n.formatValue(n, n.value, n.value))
            }
        })
    }
}(Zepto),
function($) {
    "use strict";
    var e = new Date
      , a = function(e) {
        return e < 10 ? "0" + e : e
    }
      , t = function(e) {
        var a = e.cols[0].value + ":" + e.cols[2].value
          , t = new Date("1900/01/01 " + a + ":00").getTime()
          , n = new Date("1900/01/01 " + e.params.minTime + ":00").getTime()
          , l = new Date("1900/01/01 " + e.params.maxTime + ":00").getTime();
        return t < n && (e.cols[0].setValue(e.params.minTime.split(":")[0]),
        e.cols[2].setValue(e.params.minTime.split(":")[1]),
        a = e.params.minTime),
        t > l && (e.cols[0].setValue(e.params.maxTime.split(":")[0]),
        e.cols[2].setValue(e.params.maxTime.split(":")[1]),
        a = e.params.maxTime),
        a
    }
      , n = {
        change: null,
        confirm: null,
        minTime: "00:00",
        maxTime: "23:59",
        rotateEffect: !1,
        value: [a(e.getHours()), a(e.getMinutes())],
        onChange: function(e, a, n) {
            var l = t(e);
            e.params.change && e.params.change(l)
        },
        onConfirm: function(e, a, n) {
            var l = t(e);
            e.params.confirm && e.params.confirm(l)
        },
        formatValue: function(e, a, t) {
            return a[0] + ":" + a[1]
        },
        cols: []
    };
    $.fn.timePicker = function(e) {
        return this.each(function() {
            if (this) {
                var a = $.extend(!0, {}, n)
                  , t = $.extend(a, e);
                t.cssClass = "customized-timePicker",
                t.cols = [{
                    values: function() {
                        for (var e = [], a = 0; a <= 23; a++)
                            e.push(a < 10 ? "0" + a : a);
                        return e
                    }()
                }, {
                    divider: !0,
                    content: ":"
                }, {
                    values: function() {
                        for (var e = [], a = 0; a <= 59; a++)
                            e.push(a < 10 ? "0" + a : a);
                        return e
                    }()
                }],
                e.value && (t.value = [e.value.split(":")[0], e.value.split(":")[1]]),
                $(this).picker(t),
                e.value && $(this).val(t.formatValue(t, t.value, t.value))
            }
        })
    }
}(Zepto),
function($) {
    "use strict";
    var e = new Date
      , a = function(e) {
        for (var a = [], t = 1; t <= (e || 31); t++)
            a.push(t < 10 ? "0" + t : t);
        return a
    }
      , t = function(e, t) {
        var n = new Date(t,parseInt(e) + 1 - 1,1)
          , l = new Date(n - 1);
        return a(l.getDate())
    }
      , n = function(e) {
        return e < 10 ? "0" + e : e
    }
      , l = "01 02 03 04 05 06 07 08 09 10 11 12".split(" ")
      , s = function(e, a) {
        for (var t = new Date(e.replace(new RegExp("-","gm"), "/")).getFullYear(), n = new Date(a.replace(new RegExp("-","gm"), "/")).getFullYear(), l = [], s = t; s <= n; s++)
            l.push(s);
        return l
    }
      , i = function(e) {
        var a = t(e.cols[1].value, e.cols[0].value)
          , n = e.cols[2].value;
        n > a.length && (n = a.length),
        e.cols[2].setValue(n);
        var l = e.cols[0].value + "-" + e.cols[1].value + "-" + n + " " + e.cols[4].value + ":" + e.cols[6].value
          , s = new Date(l.replace(new RegExp("-","gm"), "/")).getTime()
          , i = new Date(e.params.minDateTime.replace(new RegExp("-","gm"), "/")).getTime()
          , r = new Date(e.params.maxDateTime.replace(new RegExp("-","gm"), "/")).getTime();
        return s < i && (e.cols[0].setValue(e.params.minDateTime.split(" ")[0].split("-")[0]),
        e.cols[1].setValue(e.params.minDateTime.split(" ")[0].split("-")[1]),
        e.cols[2].setValue(e.params.minDateTime.split(" ")[0].split("-")[2]),
        e.cols[4].setValue(e.params.minDateTime.split(" ")[1].split(":")[0]),
        e.cols[6].setValue(e.params.minDateTime.split(" ")[1].split(":")[1]),
        l = e.params.minDateTime),
        s > r && (e.cols[0].setValue(e.params.maxDateTime.split(" ")[0].split("-")[0]),
        e.cols[1].setValue(e.params.maxDateTime.split(" ")[0].split("-")[1]),
        e.cols[2].setValue(e.params.maxDateTime.split(" ")[0].split("-")[2]),
        e.cols[4].setValue(e.params.maxDateTime.split(" ")[1].split(":")[0]),
        e.cols[6].setValue(e.params.maxDateTime.split(" ")[1].split(":")[1]),
        l = e.params.maxDateTime),
        l
    }
      , r = {
        rotateEffect: !1,
        change: null,
        confirm: null,
        minDateTime: "1950-01-01 00:00",
        maxDateTime: "2099-12-31 23:59",
        value: [e.getFullYear(), n(e.getMonth() + 1), n(e.getDate()), n(e.getHours()), n(e.getMinutes())],
        onChange: function(e, a, t) {
            var n = i(e);
            e.params.change && e.params.change(n)
        },
        onConfirm: function(e, a, t) {
            var n = i(e);
            e.params.confirm && e.params.confirm(n)
        },
        formatValue: function(e, a, t) {
            return t[0] + "-" + a[1] + "-" + a[2] + " " + a[3] + ":" + a[4]
        },
        cols: []
    };
    $.fn.dateAndTimePicker = function(e) {
        return this.each(function() {
            if (this) {
                var t = $.extend(!0, {}, r)
                  , n = $.extend(t, e);
                if (n.cssClass = "customized-dateAndTimePicker",
                n.cols = [{
                    values: s(n.minDateTime, n.maxDateTime)
                }, {
                    values: l
                }, {
                    values: a()
                }, {
                    divider: !0,
                    content: "  "
                }, {
                    values: function() {
                        for (var e = [], a = 0; a <= 23; a++)
                            e.push(a < 10 ? "0" + a : a);
                        return e
                    }()
                }, {
                    divider: !0,
                    content: ":"
                }, {
                    values: function() {
                        for (var e = [], a = 0; a <= 59; a++)
                            e.push(a < 10 ? "0" + a : a);
                        return e
                    }()
                }],
                e.value) {
                    var i = e.value.split(" ");
                    n.value = [i[0].split("-")[0], i[0].split("-")[1], i[0].split("-")[2], i[1].split(":")[0], i[1].split(":")[1]]
                }
                $(this).picker(n),
                e.value && $(this).val(n.formatValue(n, n.value, n.value))
            }
        })
    }
}(Zepto);
