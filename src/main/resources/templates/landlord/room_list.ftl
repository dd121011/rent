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
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="name" required  lay-verify="required" placeholder="请输入房间号" autocomplete="off" class="layui-input">
                </div>
                <a class="layui-btn search_btn" data-type="reload">搜索</a>
            </div>
            <div class="layui-inline" id="layerRoom">
                <button data-method="add" data-type="auto" class="layui-btn layui-btn-normal">添加</button>
            </div>
        </blockquote>
        <hr class="layui-bg-green">
        <table class="layui-hide" id="lay_table_room" lay-filter="roomTableFilter"></table>
    </div>

<#include "landlord/footer.ftl"/>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">出租记录</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script type="text/html" id="roomRentTpl">
    {{#  if(d.rentTs > 0){ }}
    <a class="layui-btn layui-btn-xs">已出租</a>
    {{#  } else { }}
    <a class="layui-btn layui-btn-danger layui-btn-xs">未出租</a>
    {{#  } }}
</script>
<script src="${base}/static/js/landlord/room.js" charset="utf-8"></script>

<#include "landlord/room_edit.ftl"/>

</html>