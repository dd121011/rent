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
            <form class="layui-form" lay-filter="roomSearchFormFilter" action="">
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <select id="searchBuildingId" lay-verify="required" lay-filter="roomSearchFormSelectBuildingFilter" lay-search="">
                        <#list buildings as item>
                            <option value="${item.buildingId}"<#if item.buildingId == buildingId>selected</#if>>${item.name}</option>
                        </#list>
                        </select>
                    </div>
                    <div class="layui-input-inline">
                        <select id="searchRoomId" lay-verify="required" lay-search="">
                            <option value="-1">请选择</option>
                        <#list rooms as item>
                            <option value="${item.roomId}">${item.roomNo}</option>
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
        <table class="layui-hide" id="lay_table_room" lay-filter="roomTableFilter"></table>
    </div>

<#include "landlord/footer.ftl"/>

<script type="text/html" id="roomListBar">
    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="detail">详情</a>
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="charge">生成房租</a>
    {{#  if(d.rentTs > 0){ }}
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="continue">续约</a>
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="leave">退租</a>
    {{#  } else { }}
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="rent">出租</a>
    {{#  } }}
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="rentHistoty">出租记录</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script src="${base}/static/js/landlord/room.js" charset="utf-8"></script>

<#include "landlord/room_edit.ftl"/>
<#include "landlord/room_charge.ftl"/>
<#include "landlord/room_list_rent.ftl"/>

</html>