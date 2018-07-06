<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>房屋出租 - by scrats</title>
    <#include "landlord/menu.ftl" />

    <div class="layui-body childrenBody">
        首页内容
        <br><br>
        <blockquote class="layui-elem-quote">
            <form class="layui-form" lay-filter="roomSearchFormFilter" action="">
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <input type="text" id="searchRoomNo" placeholder="请输入房间号" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-input-inline">
                        <select id="searchBuildingId" lay-verify="required" lay-search>
                        <#list buildingList as item>
                            <option value="${item.buildingId}"<#if item.buildingId == buildingId>selected</#if>>${item.name}</option>
                        </#list>
                        </select>
                    </div>
                    <div class="layui-input-inline">
                        <select id="searchRoomRentTs"  lay-search="">
                            <option value="-1" selected>全部</option>
                            <option value="1">已出租</option>
                            <option value="0">未出租</option>
                        </select>
                    </div>
                    <a class="layui-btn search_btn" data-method="search" data-type="search">搜索</a>
                </div>
                <div class="layui-inline">
                    <a data-method="add" data-type="add" class="layui-btn layui-btn-normal">添加</a>
                </div>
            </form>
        </blockquote>
        <hr class="layui-bg-green">
        <table class="layui-hide" id="lay_table_building" lay-filter="buildingTableFilter"></table>
    </div>

<#include "landlord/footer.ftl"/>

</html>