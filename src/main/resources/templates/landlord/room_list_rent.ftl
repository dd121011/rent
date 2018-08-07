<div id="addRentDiv" style="padding: 20px 0; padding-right: 40px; display: none" >
    <form class="layui-form" lay-filter="rentEditFormFilter" action="">
        <input type="hidden" name="roomId" required lay-verify="required" autocomplete="off" class="layui-input">
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="name" required lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-inline">
                <select name="sex" lay-filter="sex">
                    <option value="0" selected>保密</option>
                    <option value="1">男</option>
                    <option value="2">女</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">租金</label>
                <div class="layui-input-inline">
                    <input type="text" name="rentFee" required  lay-verify="number" placeholder="请输入租金" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid">元/月</div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">收租日</label>
            <div class="layui-input-inline">
                <select name="rentDay" lay-filter="rentDay">
                    <#list 1..31 as t>
                            <option value="${t}">${t}</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">押付方式</label>
                <div class="layui-form-mid">押</div>
                <div class="layui-input-inline" style="width: 30%;">
                    <select name="guaranty" lay-filter="guaranty">
                        <option value="0">0</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </div>
                <div class="layui-form-mid">付</div>
                <div class="layui-input-inline" style="width: 30%;">
                    <select name="pay" lay-filter="pay">
                        <option value="0">0</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号码</label>
            <div class="layui-input-block">
                <input type="text" name="phone" required  lay-verify="phone" placeholder="请输入手机号码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">身份证</label>
            <div class="layui-input-block">
                <input type="text" name="idCard" required  lay-verify="identity" placeholder="请输入身份证号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">入住时间</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="liveTs" required  lay-verify="required" id="liveTs" placeholder="请选择入住时间">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">退租时间</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="leaveTs" required  lay-verify="required" id="leaveTs" placeholder="请选择退租时间">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">配套设施</label>
            <div class="layui-input-block" id="roomRentFacilities"></div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">额外收费项</label>
            <div class="layui-input-block">
                <table class="layui-table" id="extraTable" lay-filter="extraTableFilter"></table>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">押金项</label>
            <div class="layui-input-block">
                <table class="layui-table" id="depositTable" lay-filter="depositTableFilter"></table>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block layui-form-text">
                <textarea name="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="rentEditFormSubmitFilter">提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

<script type="text/html" id="roomRentFacilitiesTemplete">
    {{#  layui.each(d, function(index, item){ }}
    <input type="checkbox" lay-filter="testt" name="facilities" value="{{ item.dicItermCode }}" title="{{ item.value }}" checked>
    {{#  }); }}
</script>

<script src="${base}/static/js/landlord/room_list_rent.js" charset="utf-8"></script>