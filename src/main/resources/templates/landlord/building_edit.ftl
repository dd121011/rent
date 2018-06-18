<div id="addDiv" style="padding: 20px 0; padding-right: 40px; display: none" >
    <form class="layui-form" id="testBuildingForm" lay-filter="buildingFormFilter" action="">
        <div class="layui-form-item" style="display: none">
            <label class="layui-form-label">id</label>
            <div class="layui-input-block">
                <input type="hidden" name="buildingId" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">楼盘名称</label>
            <div class="layui-input-block">
                <input type="text" name="name" required  lay-verify="required" placeholder="请输入楼盘名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">配套设施</label>
            <div class="layui-input-block">
                <#list facilityList as item>
                    <input type="checkbox" lay-filter="testt" name="facilityIds" value="${item.dicItermId}" title="${item.value}">
                </#list>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">额外收费项</label>
            <div class="layui-input-block">
                <#list extraList as item>
                    <input type="checkbox" lay-filter="testt" name="extraIds" value="${item.extraId}" title="${item.name}">
                </#list>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">地址</label>
            <div class="layui-input-block">
                <input type="text" name="address"  lay-verify="required" placeholder="请输入地址" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">描述</label>
            <div class="layui-input-block">
                <textarea name="description" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

<script src="${base}/static/js/landlord/building_edit.js" charset="utf-8"></script>