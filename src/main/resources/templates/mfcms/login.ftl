<#include "common.ftl"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="${base}/static/favicon.ico">
    <!-- load css -->
    <link rel="stylesheet" type="text/css" href="${base}/static/layui/css/layui.css" media="all" />
    <link rel="stylesheet" type="text/css" href="${base}/static/css/login.css" media="all" />
</head>
<body>
<div class="layui-canvs"></div>
<div class="layui-layout layui-layout-login layui-form">
    <h1>
        <strong>管理系统</strong>
        <em>Management System</em>
    </h1>
    <div class="layui-user-icon larry-login">
        <input type="text" name="username" placeholder="账号" lay-verify="required" class="login_txtbx" value="admin"/>
    </div>
    <div class="layui-pwd-icon larry-login">
        <input type="password" name="password" lay-verify="required" placeholder="密码" class="login_txtbx" value="root@admin"/>
    </div>
    <!--<div class="layui-val-icon larry-login">
        <div class="layui-code-box">
            <input type="text" id="code" name="code" placeholder="验证码" maxlength="4" class="login_txtbx">
            <img src="./images/verifyimg.png" alt="" class="verifyImg" id="verifyImg" onclick="javascript:this.src='xxx'+Math.random();">
        </div>
    </div>-->
    <div class="layui-submit larry-login">
        <input type="button" value="立即登陆" lay-submit lay-filter="login" class="submit_btn"/>
    </div>
    <div class="layui-login-text">
        <p>© 2017-2018 DingHao 版权所有</p>
        <p>沪ICP备16001600号 <a href="https://www.hiekn.site:1443" target="_blank">www.hiekn.site</a></p>
    </div>
</div>

<script type="text/javascript" src="${base}/static/js/libs/jquery.min.js"></script>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/libs/jsplug/jparticle.jquery.js"></script>
<script type="text/javascript" src="${base}/static/js/login.js"></script>

</body>
</html>