requirejs.config({
    baseUrl: "/h5/public/js/lib",
    paths: {
        base: "..",
        module: "../module",
        config: "/h5/modules/insure",
    },
    shim: {
  		sm: {
  			deps: ["zepto"],
        exports: "$"
      },
  		'zepto': {
  			exports: '$'
  		},
      'app': {
        deps: ["zepto"],
  			exports: '$fn'
  		},
      "sm-date-picker": {
          deps: ["sm"],
          exports: "$"
      },
      juicer: {
          exports: "juicer"
      }
    }
}),
define(["zepto","sm","juicer","utils","app","sm-date-picker"],function(){

  if(apputils.isNull(sessionStorage.getItem("openId"))){
    $fn.setOpenId();
  }
});
