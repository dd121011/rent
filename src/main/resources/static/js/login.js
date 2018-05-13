layui.config({
    base: 'static/js/modules/'
}).use(['jquery','form','config','utils','ajax','common'],function () {
    'use strict';
    var $ = layui.$;
    var form = layui.form;
    var config = layui.config;
    var utils = layui.utils;
    var ajax = layui.ajax;
    var mj = layui.common;

    $(".layui-canvs").width($(window).width());
    $(".layui-canvs").height($(window).height());
    $(".layui-canvs").jParticle({
        background: "#141414",
        color: "#E6E6E6"
    });


    form.on('submit(login)', function(data){
        var postData = data.field;
        postData.password = utils.md5(postData.password);
        ajax.sendPost({
            "url":config.RESTFUL_URL+"/login",
            "postData":postData,
            "success":function (data) {
                if(mj.haveData(data)){
                    mj.setLoginInfo(data);
                    mj.goHome();
                }
            }
        });
        return false;
    });

    $(window).resize(function() {
        $(".layui-canvs").resizeCanvas();
    });





});