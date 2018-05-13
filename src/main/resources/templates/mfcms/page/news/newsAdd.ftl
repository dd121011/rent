<#include "../../common.ftl"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>文章列表--layui后台管理模板 2.0</title>
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
<form class="layui-form layui-row layui-col-space10">
	<div class="layui-col-md9 layui-col-xs12">
		<div class="layui-row layui-col-space10">
			<div class="layui-col-md9 layui-col-xs7">
				<div class="layui-form-item magt3">
					<label class="layui-form-label">标题</label>
					<div class="layui-input-block">
                        <input type="hidden" name="id">
                        <input type="text" class="layui-input newsName" name="title" lay-verify="required" placeholder="请输入标题">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">摘要</label>
					<div class="layui-input-block">
						<textarea placeholder="请输入摘要256字以内" name="abs" lay-verify="abs-len" class="layui-textarea abstract"></textarea>
					</div>
				</div>
			</div>
			<div class="layui-col-md3 layui-col-xs5">
				<div class="layui-upload-list thumbBox mag0 magt3">
					<img class="layui-upload-img thumbImg">
                    <input type="hidden" name="thumb">
                </div>
			</div>
		</div>
		<div class="layui-form-item magb0">
			<label class="layui-form-label">内容</label>
			<div class="layui-input-block">
				<textarea class="layui-textarea layui-hide" name="content" lay-verify="required" id="news_content"></textarea>
			</div>
		</div>
	</div>
	<div class="layui-col-md3 layui-col-xs12">
		<blockquote class="layui-elem-quote title"><i class="seraph icon-caidan"></i> 分类目录</blockquote>
		<div class="border category">
			<div class="">
				<p><input type="checkbox" name="news" title="新闻" lay-skin="primary" /></p>
				<p><input type="checkbox" name="goods" title="商品" lay-skin="primary" /></p>
				<p><input type="checkbox" name="notice" title="公告" lay-skin="primary" /></p>
				<p><input type="checkbox" name="images" title="图片" lay-skin="primary" /></p>
			</div>
		</div>
		<blockquote class="layui-elem-quote title magt10"><i class="layui-icon">&#xe609;</i> 发布</blockquote>
		<div class="border">
			<div class="layui-form-item">
				<label class="layui-form-label"><i class="layui-icon">&#xe60e;</i> 状　态</label>
				<div class="layui-input-block">
					<select name="status" lay-verify="required">
						<option value="0">待审核</option>
						<option value="1">审核通过</option>
						<option value="2">禁用</option>
					</select>
				</div>
			</div>
			<#--<div class="layui-form-item">
				<label class="layui-form-label"><i class="layui-icon">&#xe609;</i> 发　布</label>
				<div class="layui-input-block">
					<input type="radio" name="release" title="立即发布" lay-skin="primary" lay-filter="release" checked />
					<input type="radio" name="release" title="定时发布" lay-skin="primary" lay-filter="release" />
				</div>
			</div>-->
            <div class="layui-form-item">
                <label class="layui-form-label"><i class="seraph icon-look"></i>发布者</label>
                <div class="layui-input-block">
                    <input type="text" name="publisher" class="layui-input" lay-verify="required" placeholder="请输入发布者" />
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"><i class="seraph icon-look"></i>发布时间</label>
                <div class="layui-input-block">
                    <input type="text" class="layui-input" name="publishTime" lay-verify="required"  id="release" placeholder="请选择日期和时间" readonly />
                </div>
            </div>
			<div class="layui-form-item">
				<label class="layui-form-label"><i class="seraph icon-look"></i>阅读量</label>
				<div class="layui-input-block">
					<input type="text" class="layui-input" name="readNum" value="0" lay-verify="number" placeholder="请输入阅读量" />
				</div>
			</div>
			<#--<div class="layui-form-item openness">
				<label class="layui-form-label"><i class="seraph icon-look"></i> 公开度</label>
				<div class="layui-input-block">
					<input type="radio" name="openness" title="开放浏览" lay-skin="primary" checked />
					<input type="radio" name="openness" title="私密浏览" lay-skin="primary" />
				</div>
			</div>-->
			<div class="layui-form-item top">
				<label class="layui-form-label"><i class="seraph icon-zhiding"></i> 置　顶</label>
				<div class="layui-input-block">
					<input type="checkbox" name="top" lay-skin="switch" lay-filter="top" lay-text="是|否">
				</div>
			</div>
			<hr class="layui-bg-gray" />
			<div class="layui-right">
				<a class="layui-btn layui-btn-sm" lay-filter="addNews" lay-submit><i class="layui-icon">&#xe609;</i>发布</a>
				<#--<a class="layui-btn layui-btn-primary layui-btn-sm" lay-filter="look" lay-submit>预览</a>-->
			</div>
		</div>
	</div>
</form>
<script type="text/javascript" src="${base}/static/layui/layui.js"></script>
<script type="text/javascript" src="${base}/static/js/page/news/newsAdd.js"></script>
</body>
</html>