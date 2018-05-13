<#include "../../common.ftl"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>添加用户</title>
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
<form class="layui-form" style="width:80%;">
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">手机号</label>
		<div class="layui-input-block">
            <input type="hidden" name="id">
            <input type="text" name="mobile" class="layui-input" lay-verify="phone" placeholder="请输入手机号">
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">昵称</label>
		<div class="layui-input-block">
			<input type="text" name="nickname" class="layui-input" placeholder="请输入昵称">
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12 password">
		<label class="layui-form-label">密码</label>
		<div class="layui-input-block">
			<input type="text" name="password" class="layui-input" lay-verify="required"  placeholder="请输入密码">
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">邮箱</label>
		<div class="layui-input-block">
			<input type="text" name="email" class="layui-input" lay-verify="email" placeholder="请输入邮箱">
		</div>
	</div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">公司</label>
        <div class="layui-input-block">
            <input type="text" name="company" class="layui-input" placeholder="请输入公司名称">
        </div>
    </div>
	<div class="layui-row">
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">性别</label>
			<div class="layui-input-block userSex">
				<input type="radio" name="gender" value="1" title="男">
				<input type="radio" name="gender" value="2" title="女">
			</div>
		</div>
		<#--<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">会员等级</label>
			<div class="layui-input-block">
				<select name="userGrade" class="userGrade" lay-filter="userGrade">
					<option value="0">注册会员</option>
					<option value="1">中级会员</option>
					<option value="2">高级会员</option>
					<option value="3">钻石会员</option>
					<option value="4">超级会员</option>
				</select>
			</div>
		</div>-->
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">会员状态</label>
			<div class="layui-input-block">
				<select name="status" class="userStatus" lay-filter="userStatus">
					<option value="0">待审核</option>
					<option value="1">正常</option>
					<option value="2">禁用</option>
					<option value="3">删除</option>
				</select>
			</div>
		</div>
	</div>
	<#--<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">用户简介</label>
		<div class="layui-input-block">
			<textarea placeholder="请输入用户简介" class="layui-textarea userDesc"></textarea>
		</div>
	</div>-->
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="layui-input-block">
			<button class="layui-btn layui-btn-sm" lay-submit="" lay-filter="addUser">确定</button>
			<button type="reset" class="layui-btn layui-btn-sm layui-btn-primary cancel_btn">取消</button>
		</div>
	</div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/page/user/userAdd.js"></script>
</body>
</html>