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
            <form class="layui-form" lay-filter="buildingSearchFormFilter" action="">
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <select id="searchBuildingName"  lay-search>
                        </select>
                    </div>
                    <a class="layui-btn search_btn" data-method="search" data-type="auto">搜索</a>
                </div>
                <div class="layui-inline">
                    <a data-method="add" data-type="auto" class="layui-btn layui-btn-normal">添加</a>
                </div>
            </form>
        </blockquote>
        <hr class="layui-bg-green">
        <table class="layui-hide" id="lay_table_building" lay-filter="buildingTableFilter"></table>
    </div>

<#include "footer.ftl"/>

<script type="text/html" id="buildingListBar">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">出租情况</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script src="${base}/static/js/landlord/building.js" charset="utf-8"></script>

<#include "landlord/building_edit.ftl"/>

</html>