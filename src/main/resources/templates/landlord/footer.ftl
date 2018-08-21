<div class="layui-footer">
    <!-- 底部固定区域 -->
    © layui.com - 底部固定区域
</div>
</div>
</body>
<script src="${base}/static/js/extends/jquery-1.11.3.min.js" charset="utf-8"></script>
<script src="${base}/static/js/extends/jquery.cookie.js" charset="utf-8"></script>
<script src="${base}/static/layui/layui.js" charset="utf-8"></script>
<script src="${base}/static/js/public.js"></script>
<script src="${base}/static/js/landlord/common.js"></script>


<div id="userPwdCheckDiv" style="padding-top: 20px; padding-right: 40px; display: none" >
    <form class="layui-form" lay-filter="userPwdCheckFormFilter" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block">
                <input type="password" class="layui-input" required  lay-verify="required" id="checkPwd" name="pwd" placeholder="请输入您的密码">
            </div>
        </div>
    </form>
</div>