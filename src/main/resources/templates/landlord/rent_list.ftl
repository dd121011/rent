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
            <form class="layui-form" lay-filter="rentSearchFormFilter" action="">
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <select id="searchBuildingId" lay-verify="required" lay-filter="rentSearchFormSelectBuildingFilter" lay-search="">
                        <#list buildings as item>
                            <option value="${item.buildingId}"<#if item.buildingId == buildingId>selected</#if>>${item.name}</option>
                        </#list>
                        </select>
                    </div>
                    <div class="layui-input-inline">
                        <select id="searchRoomId" lay-verify="required" lay-search="">
                        <#list rooms as item>
                            <option value="${item.roomId}"<#if item.roomId == roomId>selected</#if>>${item.roomNo}</option>
                        </#list>
                        </select>
                    </div>
                    <div class="layui-input-inline">
                        <select id="searchRoomPayTs"  lay-search="">
                            <option value="-1" selected>全部</option>
                            <option value="1">已缴费</option>
                            <option value="0">未缴费</option>
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
        <table class="layui-hide" id="lay_table_rent" lay-filter="rentTableFilter"></table>
    </div>

<#include "footer.ftl"/>

<script type="text/html" id="rentListBar">
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="pay">缴费</a>
    <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="detail">详情</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
</script>

<script src="${base}/static/js/landlord/rent.js" charset="utf-8"></script>

<#include "landlord/rent_charge.ftl"/>
<#include "landlord/rent_detail.ftl"/>
<#include "landlord/rent_edit.ftl"/>
</html>