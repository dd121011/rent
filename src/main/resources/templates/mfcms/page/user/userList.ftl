<#include "../../common.ftl"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>用户中心</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="${base}/static/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${base}/static/css/public.css" media="all" />
</head>
<body class="childrenBody">
<form class="layui-form">
	<blockquote class="layui-elem-quote quoteBox">
		<form class="layui-form">
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" class="layui-input searchVal" placeholder="请输入用户名" />
				</div>
                <div class="layui-input-inline status">
                    <select name="status">
                        <option value="-1">状态</option>
                        <option value="0">待审核</option>
                        <option value="1">正常</option>
                        <option value="2">已禁用</option>
                        <option value="3">已删除</option>
                    </select>
                </div>
				<a class="layui-btn search_btn" data-type="reload">搜索</a>
			</div>
			<div class="layui-inline">
				<a class="layui-btn layui-btn-normal addNews_btn">添加</a>
			</div>
			<#--<div class="layui-inline">
				<a class="layui-btn layui-btn-danger layui-btn-normal delAll_btn">批量删除</a>
			</div>-->
		</form>
	</blockquote>
	<table id="userList" lay-filter="userList"></table>

	<!--操作-->
	<script type="text/html" id="userListBar">
		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        {{#  if(d.status === '1'){ }}
        <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="usable">禁用</a>
        {{#  } else { }}
        <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="usable">启用</a>
        {{#  } }}
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
	</script>
    <script type="text/html" id="statusTpl">
        {{#  if(d.status === '0'){ }}
        <span class="layui-blue">待审核</span>
        {{#  } else if(d.status === '1'){ }}
        <span style="color: #009688">正常</span>
        {{#  } else if(d.status === '2'){ }}
        <span class="layui-red">已禁用</span>
        {{#  } else if(d.status === '3'){ }}
        已删除
        {{#  } }}
    </script>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/page/user/userList.js"></script>
</body>
</html>