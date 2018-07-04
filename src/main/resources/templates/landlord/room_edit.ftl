<div id="addDiv" style="padding: 20px 0; padding-right: 40px; display: none" >
    <form class="layui-form" lay-filter="roomEditFormFilter" action="">
        <div class="layui-form-item" style="display: none">
            <label class="layui-form-label">id</label>
            <div class="layui-input-block">
                <input type="hidden" name="roomId" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">楼盘</label>
            <div class="layui-input-block">
                <select name="buildingId" lay-filter="buildingId">
                    <#list buildings as item>
                    <option value="${item.buildingId}" <#if item.buildingId == buildingId>selected</#if>>${item.name}</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">房间号</label>
            <div class="layui-input-block">
                <input type="text" name="roomNo" required  lay-verify="required" placeholder="请输入房间号" autocomplete="off" class="layui-input rmcl">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">房间朝向</label>
            <div class="layui-input-block">
                <select name="orientation" lay-filter="orientation">
                <#list orientations as item>
                    <option value="${item.dicItermCode}">${item.value}</option>
                </#list>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">装修情况</label>
            <div class="layui-input-block">
                <select name="decoration" lay-filter="decoration">
                <#list decorations as item>
                    <option value="${item.dicItermCode}">${item.value}</option>
                </#list>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">房间情况</label>
                <div class="layui-input-inline" style="width: 17%;">
                    <select name="bedroom" lay-filter="bedroom">
                        <option value="1" selected>1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </div>
                <div class="layui-form-mid">房</div>
                <div class="layui-input-inline" style="width: 17%;">
                    <select name="living" lay-filter="living">
                        <option value="0" selected>0</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </div>
                <div class="layui-form-mid">厅</div>
                <div class="layui-input-inline" style="width: 17%;">
                    <select name="toilet" lay-filter="toilet">
                        <option value="0">0</option>
                        <option value="1" selected>1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </div>
                <div class="layui-form-mid">卫</div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">押付方式</label>
                <div class="layui-form-mid">押</div>
                <div class="layui-input-inline" style="width: 30%;">
                    <select name="guaranty" lay-filter="guaranty">
                        <option value="0">0</option>
                        <option value="1" selected>1</option>
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
                        <option value="1" selected>1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">租金</label>
                <div class="layui-input-inline">
                    <input type="text" name="rentFee" required  lay-verify="required" placeholder="请输入租金" autocomplete="off" class="layui-input rmcl">
                </div>
                <div class="layui-form-mid">元/月</div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">使用面积</label>
                <div class="layui-input-inline">
                    <input type="text" name="area" required  lay-verify="required" placeholder="请输入使用面积" autocomplete="off" class="layui-input rmcl">
                </div>
                <div class="layui-form-mid">平方米</div>
            </div>
        </div>
        <#if facilityIds?? && (facilityIds?size > 0) >
        <div class="layui-form-item">
            <label class="layui-form-label">配套设施</label>
            <div class="layui-input-block">
                        <#list facilityList as item>
                            <input type="checkbox" lay-filter="testt" name="facilityIds" value="${item.dicItermCode}" title="${item.value}" checked>
                        </#list>
            </div>
        </div>
        </#if>
        <#if extraIds?? && (extraIds?size > 0) >
        <div class="layui-form-item">
            <label class="layui-form-label">额外收费项</label>
            <div class="layui-input-block">
                        <#list extraList as item>
                            <input type="checkbox" lay-filter="testt" name="extraIds" value="${item.dicItermCode}" title="${item.value}" checked>
                        </#list>
            </div>
        </div>
        </#if>
        <#if depositList?? && (depositList?size > 0) >
        <div class="layui-form-item">
            <label class="layui-form-label">押金项</label>
            <div class="layui-input-block">
                        <#list depositList as item>
                            <input type="checkbox" lay-filter="testt" name="depositIds" value="${item.dicItermCode}" title="${item.value}" checked>
                        </#list>
            </div>
        </div>
        </#if>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">描述</label>
            <div class="layui-input-block">
                <textarea name="description" placeholder="请输入内容" class="layui-textarea rmcl"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="roomEditFormSubmitFilter">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>

<script src="${base}/static/js/landlord/room_edit.js" charset="utf-8"></script>