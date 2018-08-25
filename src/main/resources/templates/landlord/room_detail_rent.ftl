<div id="addRentDiv" style="padding: 20px 0; padding-right: 40px; display: none" >
    <form class="layui-form" lay-filter="rentEditFormFilter" action="">
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
                    <input type="text" name="rentFee" required  lay-verify="number" placeholder="请输入租金" autocomplete="off" class="layui-input" value="${room.rentFee/100}">
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
                        <option value="0" <#if room.guaranty == 0>selected</#if>>0</option>
                        <option value="1" <#if room.guaranty == 1>selected</#if>>1</option>
                        <option value="2" <#if room.guaranty == 2>selected</#if>>2</option>
                        <option value="3" <#if room.guaranty == 3>selected</#if>>3</option>
                        <option value="4" <#if room.guaranty == 4>selected</#if>>4</option>
                        <option value="5" <#if room.guaranty == 5>selected</#if>>5</option>
                    </select>
                </div>
                <div class="layui-form-mid">付</div>
                <div class="layui-input-inline" style="width: 30%;">
                    <select name="pay" lay-filter="pay">
                        <option value="0" <#if room.pay == 0>selected</#if>>0</option>
                        <option value="1" <#if room.pay == 1>selected</#if>>1</option>
                        <option value="2" <#if room.pay == 2>selected</#if>>2</option>
                        <option value="3" <#if room.pay == 3>selected</#if>>3</option>
                        <option value="4" <#if room.pay == 4>selected</#if>>4</option>
                        <option value="5" <#if room.pay == 5>selected</#if>>5</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">身份证</label>
            <div class="layui-input-block">
                <input type="text" name="idCard" required  lay-verify="identity" placeholder="请输入身份证号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号码</label>
            <div class="layui-input-block">
                <input type="text" name="phone" id="rentEditFormPhoneInput" required  lay-verify="phone" placeholder="请输入手机号码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">验证码</label>
            <div class="layui-input-inline">
                <input type="text" class="layui-input" name="smsCode" required  lay-verify="required" placeholder="请输入验证码">
            </div>
            <div class="layui-input-inline" style="width:30%;">
                <button type="button" class="layui-btn layui-btn-primary layui-btn-fluid" id="rentFormSmsCodeGenerate" >获取验证码</button>
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
            <div class="layui-input-block">
                <#list room.facilitiesIterm as item>
                    <input type="checkbox" lay-filter="testt" name="facilities" value="${item.dicItermCode}" title="${item.value}" checked>
                </#list>
            </div>
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

<script src="${base}/static/js/landlord/room_detail_rent.js" charset="utf-8"></script>