<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>房屋出租 - by scrats</title>
    <#include "landlord/menu.ftl" />

    <div class="layui-body childrenBody">
        个人中心
        <br><br>
        <div class="layui-tab layui-tab-brief" lay-filter="userTabBrief">
            <input type="hidden" id="userId" autocomplete="off" class="layui-input" value="${user.userId}">
            <ul class="layui-tab-title">
                <li class="layui-this">个人中心</li>
                <li>安全设置</li>
                <li>操作日志</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <h6>实名认证信息</h6>
                    <table class="layui-table" lay-even lay-skin="nob">
                        <tbody>
                        <tr>
                            <th class="table-two-first-fex">认证状态：</th>
                            <td><#if user.checkTs &gt; 0>已<#else>未</#if>认证</td>
                        </tr>
                        <tr>
                            <th class="table-two-first-fex">姓名：</th>
                            <td>${user.name}</td>
                        </tr>
                        <tr>
                            <th class="table-two-first-fex">身份证号：</th>
                            <td>${user.hideIdCard}</td>
                        </tr>
                        </tbody>
                    </table>
                    <hr class="layui-bg-green">
                    <h6>个人信息</h6>

                    <form class="layui-form" lay-filter="userPersonalInfoFormFilter" action="">
                        <table class="layui-table" lay-even lay-skin="nob">
                            <tbody>
                            <tr style="background-color: #ffffff;">
                                <th class="table-two-first-fex">QQ：</th>
                                <td><input type="text" name="qq"  lay-verify="number" placeholder="请输入QQ" autocomplete="off" class="layui-input"  value="${user.qq}"></td>
                            </tr>
                            <tr style="background-color: #ffffff;">
                                <th class="table-two-first-fex">微信：</th>
                                <td><input type="text" name="wechat"  lay-verify="" placeholder="请输入微信" autocomplete="off" class="layui-input"  value="${user.wechat}"></td>
                            </tr>
                            <tr style="background-color: #ffffff;">
                                <th class="table-two-first-fex">职业：</th>
                                <td><input type="text" name="profession"  lay-verify="" placeholder="请输入职业" autocomplete="off" class="layui-input"  value="${user.profession}"></td>
                            </tr>
                            <tr style="background-color: #ffffff;">
                                <th class="table-two-first-fex">籍贯：</th>
                                <td><input type="text" name="hometown"  lay-verify="" placeholder="请输入籍贯" autocomplete="off" class="layui-input"  value="${user.hometown}"></td>
                            </tr>
                            <tr style="background-color: #ffffff;">
                                <th class="table-two-first-fex">地址：</th>
                                <td><input type="text" name="address"  lay-verify="" placeholder="请输入地址" autocomplete="off" class="layui-input"  value="${user.hometown}"></td>
                            </tr>
                            <tr style="background-color: #ffffff;">
                                <td></td>
                                <td style="float: right">
                                    <button class="layui-btn" lay-submit="" lay-filter="userPersonalInfoFormSubmitFilter">保存</button>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
                <div class="layui-tab-item">
                    <table class="layui-table" lay-even lay-skin="nob">
                        <tbody>
                        <tr style="background-color: #ffffff;">
                            <th class="table-two-first-fex">换绑手机：</th>
                            <td>您当前的手机号码是${user.hidePhone}</td>
                            <td><a data-method="changePhone" data-type="changePhone" class="layui-btn layui-btn-normal">更改</a></td>
                        </tr>
                        <tr style="background-color: #ffffff;">
                            <th class="table-two-first-fex">更改邮箱：</th>
                            <td>您当前的邮箱是${user.email}</td>
                            <td><a data-method="changeEmail" data-type="changeEmail" class="layui-btn layui-btn-normal">更改</a></td>
                        </tr>
                        <tr style="background-color: #ffffff;">
                            <th class="table-two-first-fex">更改密码：</th>
                            <td>建议您90天更改一次密码</td>
                            <td><a data-method="changePwd" data-type="changePwd" class="layui-btn layui-btn-normal">更改</a></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="layui-tab-item">
                    <table class="layui-hide" id="lay_table_login_log" lay-filter="loginLogTableFilter"></table>
                </div>
            </div>
        </div>
    </div>

<#include "landlord/footer.ftl"/>
<script src="${base}/static/js/landlord/user.js" charset="utf-8"></script>
<#include "landlord/user_pwd_edit.ftl"/>
<#include "landlord/user_phone_edit.ftl"/>
</html>