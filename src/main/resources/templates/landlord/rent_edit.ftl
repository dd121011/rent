
<div id="rentChargeEditDiv" style="padding: 20px 0; padding-right: 40px; display: none" >
    <form class="layui-form" lay-filter="roomChargeEditFormFilter" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">房源</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" required  lay-verify="required" id="chargeEditBuilding" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">房间号</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" required  lay-verify="required" id="chargeEditRoomNo" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">统计月</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="month" required  lay-verify="required" id="chargeEditMonth" placeholder="请选择统计月">
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
                <button class="layui-btn" lay-submit="" lay-filter="roomChargeEditFormSubmitFilter">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script type="text/html" id="rentChargeEditTemplete">
    {{#  layui.each(d, function(index, item){ }}
    <div class="layui-form-item">
        <label class="layui-form-label">{{ item.value }}</label>
        <div class="layui-input-block">
            <input type="text" required  lay-verify="required" placeholder="请输入数量" autocomplete="off" class="layui-input barginExtraId{{item.barginExtraId}}" value="{{# if(item.number == -1){ }}{{item.count/100}}{{#  }else { }}{{item.count}}{{#  } }}">
        </div>
    </div>
    {{#  }); }}
</script>
<script src="${base}/static/js/landlord/rent_edit.js" charset="utf-8"></script>