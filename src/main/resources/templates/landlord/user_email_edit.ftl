
<div id="userEmailEditDiv" style="padding: 20px 0; padding-right: 40px; display: none" >
    <form class="layui-form" lay-filter="userEmailEditFormFilter" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">新Email</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" required  lay-verify="email" name="email" placeholder="请输入新Email">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="userEmailEditFormSubmitFilter">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script src="${base}/static/js/landlord/user_email_edit.js" charset="utf-8"></script>