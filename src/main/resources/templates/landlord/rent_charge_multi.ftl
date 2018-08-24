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
                        <select id="searchExtraCode" lay-verify="required" lay-search="">
                        <#list extraType as item>
                            <option value="${item.dicItermCode}">${item.value}</option>
                        </#list>
                        </select>
                    </div>
                    <div class="layui-input-inline">
                        <input type="text" class="layui-input" id="searchMonth" required  lay-verify="required" placeholder="请选择统计月">
                    </div>
                    <a class="layui-btn search_btn" data-method="search" data-type="search">搜索</a>
                </div>
            </form>
        </blockquote>
        <hr class="layui-bg-green">
        <table class="layui-hide" id="lay_table_rent" lay-filter="rentTableFilter"></table>
    </div>

<#include "footer.ftl"/>


<script src="${base}/static/js/landlord/rent_charge_multi.js" charset="utf-8"></script>

</html>