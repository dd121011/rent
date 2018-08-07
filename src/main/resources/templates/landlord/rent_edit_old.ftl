
<div id="rentEditDiv" style="padding: 20px 0; padding-right: 40px; display: none" >
    <form class="layui-form" lay-filter="rentEditFormFilter" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">房源</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" required  lay-verify="required" name="rentChargeBuilding" id="rentChargeBuilding" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">房间号</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" required  lay-verify="required" name="rentNo" id="rentChargeRoomNo" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">统计月</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" required  lay-verify="required" name="rentMonth" id="editMonth" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">总费用</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" required  lay-verify="required" name="fee" id="rentFee" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">折扣</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" required  lay-verify="required" name="count" id="rentCount">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">实际费用</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" required  lay-verify="required" name="realFee" id="rentRealFee" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">房租项</label>
            <div class="layui-input-block">
                <table class="layui-table" id="rentEditItermTable" lay-filter="rentEditItermTableFilter"></table>
            </div>
        </div>
        <div id="chargeEditView"></div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea name="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="rentEditFormSubmitFilter">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script src="${base}/static/js/landlord/rent_edit_old.js" charset="utf-8"></script>