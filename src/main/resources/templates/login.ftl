<#include "common.ftl" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <title>登录</title>
    <meta name="author" content="DeathGhost" />
    <link rel="icon" href="${base}/static/favicon.ico">
    <link rel="stylesheet" type="text/css" href="${base}/static/plugins/login/css/style.css" tppabs="css/style.css"/>
    <style>
        body{height:100%;background:#16a085;overflow:hidden;}
        canvas{z-index:-1;position:absolute;}
    </style>
    <script src="${base}/static/plugins/login/js/jquery.js"></script>
    <script src="${base}/static/plugins/login/js/verificationNumbers.js"></script>
    <script src="${base}/static/plugins/login/js/Particleground.js"></script>
    <script src="${base}/static/js/extends/jquery.cookie.js"></script>
    <script src="${base}/static/js/common.js"></script>
</head>

<body>
<dl class="admin_login">
 <dt>
  <strong>房屋出租</strong>
  <em>Management System</em>
 </dt>
 <dd class="user_icon">
  <input type="text" placeholder="账号" class="login_txtbx"/>
 </dd>
 <dd class="pwd_icon">
  <input type="password" placeholder="密码" class="login_txtbx"/>
 </dd>
 <dd class="val_icon">
  <div class="checkcode">
    <input type="text" id="J_codetext" placeholder="验证码" maxlength="4" class="login_txtbx">
    <canvas class="J_codeimg" id="myCanvas" onclick="createCode()">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>
  </div>
  <input type="button" value="验证码核验" class="ver_btn" onClick="validate();">
 </dd>
 <dd>
  <input type="button" value="立即登陆" class="submit_btn"/>
 </dd>
</dl>
</body>

<script>
    if(undefined != tokenId){
        window.location.href = "${base}/building/goBuilding?tokenId=" + tokenId;
    }

    $(document).ready(function () {
        //粒子背景特效
        $('body').particleground({
            dotColor: '#5cbdaa',
            lineColor: '#5cbdaa'
        });
        //验证码
        createCode();
        //测试提交，对接程序删除即可
        $(".submit_btn").click(function () {
            login();
        });
    });

    var login = function () {
        var username = $("input[type='text']").val();
        var pwd = $("input[type='password']").val();

        $.post("${base}/login",{username:username,pwd:pwd},function(result){
            if(result.code == 1){
                tokenId = result.data.tokenId;
                header["tokenId"] = tokenId;
                $.cookie("rent_tokenId",tokenId,{expires: 7, path: '/rent'})
                window.location.href = "${base}/building/goBuilding?tokenId=" + tokenId;
            }else{
                alert(result.msg);
            }
        }).error(function (result) {
            alert("系统错误");
        });
    }
</script>
</html>
