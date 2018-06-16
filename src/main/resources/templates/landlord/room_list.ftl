<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>房屋出租 - by scrats</title>
    <#include "landlord/menu.ftl" />

    <div class="layui-body childrenBody">
        内容主体区域
        <br><br>
        <blockquote class="layui-elem-quote">
            这个是room_list.ftl
        </blockquote>
        <hr class="layui-bg-green">
        <div class="roomTable">
            搜索ID：
            <div class="layui-inline">
                <input class="layui-input" name="id" id="demoReload" autocomplete="off">
            </div>
            <button class="layui-btn" data-type="reload">搜索</button>
        </div>

        <div class="site-demo-button" id="layerRoom" style="padding: 15px;">
            <button data-method="add" data-type="auto" class="layui-btn layui-btn-normal">添加</button>
        </div>
        <table class="layui-hide" id="lay_table_room" lay-filter="room"></table>
    </div>

<#include "landlord/footer.ftl"/>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">出租记录</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script type="text/html" id="roomStyleTpl">
    {{d.bedroom}}房{{d.living}}厅{{d.toilet}}卫
</script>
<script type="text/html" id="roomGuarantyAndPayTpl">
    押{{d.guaranty}}付{{d.pay}}
</script>
<script type="text/html" id="roomRentTpl">
    {{#  if(d.rentTs > 0){ }}
    <div style="background-color: #5FB878;"><i class="layui-icon layui-icon-ok"></i></div>
    {{#  } else { }}
    <div style="background-color: #FF5722;"><i class="layui-icon layui-icon-close"></i></div>
    {{#  } }}
</script>
<script src="${base}/static/js/landlord/room.js" charset="utf-8"></script>

<#include "landlord/room_edit.ftl"/>

</html>