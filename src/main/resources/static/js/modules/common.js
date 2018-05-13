layui.define(['jquery','config','utils'], function(exports){
    'use strict';
    var $ = layui.$;
    var config = layui.config;
    var utils = layui.utils;


    var init = function () {
        var user = getLoginInfo();
        var pathname = window.location.pathname;
        if(user){
            $(".admin").text(user.nickname);
            $(".userName").text(user.nickname);
            if(pathname=="/"+config.PROJECT_NAME+"/"){
                goHome();
            }
        }
    }();

    var mj = {
            haveData:haveData,
            setDataToStorage:setDataToStorage,
            getDataFromStorage:getDataFromStorage,
            setLoginInfo:setLoginInfo,
            clearLoginInfo:clearLoginInfo,
            getLoginInfo:getLoginInfo,
            goHome:goHome,
            goLogin:goLogin,
            getUserId:getUserId,
        };


    function getUserId() {
        var user = mj.getDataFromStorage("mj_user");
        return user?utils.fromJson(user).id:"";
    }
    function haveData(data) {
        return data && !$.isEmptyObject(data);
    }

    function setDataToStorage(key,val) {
        window.localStorage.setItem(key,val);
    }
    function getDataFromStorage(key) {
        return  window.localStorage.getItem(key);
    }
    function removeDataFromStorage(key) {
        window.localStorage.removeItem(key);
    }
    function clearLoginInfo() {
        removeDataFromStorage(config.PREFIX+"user");
    }
    function setLoginInfo(user) {
        setDataToStorage(config.PREFIX+"user",utils.toJson(user));
    }
    function getLoginInfo() {
        var user = getDataFromStorage(config.PREFIX+"user");
       return user?utils.fromJson(user):"";
    }
    function goHome() {
        locationTo("/mjcms_ws");
    }
    function goLogin() {
        parent.window.location.href = "/mjcms";
    }
    function locationTo(href) {
        window.location.href = href;
    }

    exports('common', mj);


});
