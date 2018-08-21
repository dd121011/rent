<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>房屋出租 - by scrats</title>
    <#include "landlord/menu.ftl" />

    <blockquote class="layui-body childrenBody">
        房东首页内容
        <br><br>
        <blockquote class="layui-elem-quote">
            <form class="layui-form" lay-filter="landlordHomeSearchFormFilter"  action="">
                <div class="layui-inline">
                    <div class="layui-input-inline">
                        <select id="searchBuildingId" lay-verify="required" lay-search="">
                        <#list buildingList as item>
                            <option value="${item.buildingId}"<#if item.buildingId == buildingId>selected</#if>>${item.name}</option>
                        </#list>
                        </select>
                    </div>
                    <a class="layui-btn search_btn" data-method="search" data-type="search">搜索</a>
                </div>
            </form>
        </blockquote>
        <div class="layui-row layui-col-space10 panel_box">
            <div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4">
                <a href="javascript:;" data-url="https://github.com/kse-music/mcn-projects" target="_blank">
                    <input type="hidden" value="http://fly.layui.com/case/u/3198216">
                    <div class="panel_icon layui-bg-green">
                        <i class="layui-anim seraph icon-good"></i>
                    </div>
                    <div class="panel_word">
                        <span id="roomNum">0</span>
                        <cite>客房数量</cite>
                    </div>
                </a>
            </div>
            <div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4">
                <a href="javascript:;" data-url="https://github.com/kse-music/mjcms_ws" target="_blank">
                    <div class="panel_icon layui-bg-black">
                        <i class="layui-anim seraph icon-github"></i>
                    </div>
                    <div class="panel_word">
                        <span id="avaliableNum">0</span>
                        <cite>可租客房</cite>
                    </div>
                </a>
            </div>
            <div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4">
                <a href="javascript:;" data-url="${base}/page/user/userList.html">
                    <div class="panel_icon layui-bg-orange">
                        <i class="layui-anim seraph icon-icon10" data-icon="icon-icon10"></i>
                    </div>
                    <div class="panel_word userAll">
                        <span id="renterNum">0</span>
                        <em>租客总数</em>
                        <cite class="layui-hide">用户中心</cite>
                    </div>
                </a>
            </div>
            <div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4">
                <a href="javascript:;" data-url="https://blog.csdn.net/dh798417147" target="_blank">
                    <div class="panel_icon layui-bg-red">
                        <i class="layui-anim seraph icon-oschina"></i>
                    </div>
                    <div class="panel_word">
                        <span id="noRentNum">0</span>
                        <cite>欠租房间数量</cite>
                    </div>
                </a>
            </div>
            <div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4">
                <a href="javascript:;" data-url="page/systemSetting/icons.html">
                    <div class="panel_icon layui-bg-cyan">
                        <i class="layui-anim layui-icon" data-icon="&#xe857;">&#xe857;</i>
                    </div>
                    <div class="panel_word outIcons">
                        <span></span>
                        <em>待定</em>
                        <cite class="layui-hide">图标管理</cite>
                    </div>
                </a>
            </div>
            <div class="panel layui-col-xs12 layui-col-sm6 layui-col-md4">
                <a href="javascript:;">
                    <div class="panel_icon layui-bg-blue">
                        <i class="layui-anim seraph icon-clock"></i>
                    </div>
                    <div class="panel_word">
                        <span class="loginTime"></span>
                        <cite>上次登录时间</cite>
                    </div>
                </a>
            </div>
        </div>
        <blockquote class="layui-elem-quote">
            <div class="layui-card">
                <div class="layui-card-header">入住率</div>
                <div class="layui-card-body">
                    统计图表
                </div>
            </div>
        </blockquote>
        <blockquote class="layui-elem-quote">
            <div class="layui-card">
                <div class="layui-card-header">租金收入</div>
                <div class="layui-card-body">
                    统计图表
                </div>
            </div>
        </blockquote>
    </blockquote>

<#include "footer.ftl"/>

<script src="${base}/static/js/landlord/home.js" charset="utf-8"></script>

</html>