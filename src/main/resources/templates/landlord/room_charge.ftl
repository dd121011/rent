
<div id="addChargeDiv" style="padding: 20px 0; padding-right: 40px; display: none" >
    <form class="layui-form" lay-filter="roomChargeFormFilter" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">统计月</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="month" required  lay-verify="required" id="month" placeholder="请选择统计月">
            </div>
        </div>
        <div id="chargeView"></div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <textarea name="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="roomChargeFormSubmitFilter">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script type="text/html" id="roomChargeTemplete">
    {{#  layui.each(d, function(index, item){ }}
    <div class="layui-form-item">
        <label class="layui-form-label">{{ item.value }}</label>
        <div class="layui-input-block">
            <input type="text" required  lay-verify="required" placeholder="请输入数量" autocomplete="off" class="layui-input barginExtraId{{ item.barginExtraId }}" value="{{#  if(item.number == -1){ }} {{ item.price/100 }}{{#  } }}">
        </div>
    </div>
    {{#  }); }}
</script>
<script src="${base}/static/js/landlord/room_charge.js" charset="utf-8"></script>