
<div id="userPhoneEditDiv" style="padding: 20px 0; padding-right: 40px; display: none" >
    <form class="layui-form" lay-filter="userPhoneEditFormFilter" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">新手机号</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" required  lay-verify="required" id="userPhoneEditNewPhoneInput" name="phone" placeholder="请输入新手机号">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">验证码</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" name="smsCode" required  lay-verify="required" id="chargeEditMonth" placeholder="请输入验证码">
            </div>
            <div class="layui-input-inline" style="width:30%;">
                <button type="button" class="layui-btn layui-btn-primary layui-btn-fluid" id="smsCodeGenerate" lay-filter="userPhoneEditFormSmsCodeGenerateSubmitFilter">获取验证码</button>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="userPhoneEditFormSubmitFilter">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script src="${base}/static/js/landlord/user_phone_edit.js" charset="utf-8"></script>