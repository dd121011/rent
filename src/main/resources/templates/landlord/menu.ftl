<#include "common.ftl" />
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="icon" href="${base}/static/favicon.ico">
<link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all"/>
<link rel="stylesheet" href="${base}/static/css/public.css">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">房屋出租</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a data-method="dashboardManage" class="scratMenu" href="javascript:;">首页</a></li>
            <li class="layui-nav-item"><a data-method="buildingManage" class="scratMenu" href="javascript:;">楼盘管理</a></li>
            <li class="layui-nav-item"><a data-method="roomManage" class="scratMenu"href="javascript:;">房间管理</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a data-method="emailManage" class="scratMenu" href="javascript:;">邮件管理</a></dd>
                    <dd><a data-method="msgManage" class="scratMenu" href="javascript:;">消息管理</a></dd>
                    <dd><a data-method="rightManage" class="scratMenu" href="javascript:;">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    ${user.name}
                </a>
                <dl class="layui-nav-child">
                    <dd><a data-method="userDetail" class="scratMenu" href="javascript:;">基本资料</a></dd>
                    <dd><a data-method="userSecurity" class="scratMenu" href="javascript:;">安全设置</a></dd>
                    <dd><a data-method="userLogout" class="scratMenu" href="javascript:;">退出</a></dd>
                </dl>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="user-photo">
            <a class="img" title="我的头像"><img src="<#if user.avatar!=''>${user.avatar!}<#else>${base}/static/images/face.jpg</#if>" class="userAvatar"></a>
        </div>
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;" data-method="buildingManage" class="scratMenu"><i class="layui-icon layui-icon-home"></i> <cite>楼盘管理</cite></a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">列表一</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="javascript:;">列表三</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;" data-method="roomManage" class="scratMenu"><i class="layui-icon layui-icon-home"></i> <cite>房间管理</cite></a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">出租</a></dd>
                        <dd><a href="javascript:;">收租</a></dd>
                        <dd><a href="">押金</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">云市场</a></li>
                <li class="layui-nav-item"><a href="">发布商品</a></li>
            </ul>
        </div>
    </div>