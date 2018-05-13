layui.define('jquery', function(exports){
    'use strict';
    var $ = layui.$;

    $.support.cors = true;

    var BASE_URL = "http://localhost:8888";

    exports('config', {
            PROJECT_NAME:"mjcms",
            PREFIX:"mj_",
            BASE_URL:BASE_URL,
            RESTFUL_URL:BASE_URL+"/mjcms_ws"
    });
});
