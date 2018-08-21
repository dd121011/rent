
<div id="userPwdEditDiv" style="padding: 20px 0; padding-right: 40px; display: none" >
    <form class="layui-form" lay-filter="userPwdEditFormFilter" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">新密码</label>
            <div class="layui-input-block">
                <input type="password" class="layui-input" required  lay-verify="required" name="pwd" placeholder="请输入新密码">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">确认新密码</label>
            <div class="layui-input-block">
                <input type="password" class="layui-input" required  lay-verify="required" name="rpwd" placeholder="请确认新密码">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="userPwdEditFormSubmitFilter">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script src="${base}/static/js/landlord/user_pwd_edit.js" charset="utf-8"></script>