var chargeExtra;
layui.use(['layer', 'table', 'form', 'laytpl', 'laydate'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var table = layui.table;
    var form = layui.form;
    var laytpl = layui.laytpl;
    var laydate = layui.laydate;

    laydate.render({
        elem: '#searchMonth'
        ,type: 'month'
        ,format: 'yyyyMM'
    });

    //方法级渲染
    table.render({
        elem: '#lay_table_rent'//指定原始表格元素选择器（
        , url: requestBaseUrl + '/rent/list/'
        , method: 'post'
        , headers: header
        , request: {
            pageName: 'page' //页码的参数名称，默认：page
            , limitName: 'rows' //每页数据量的参数名，默认：limit
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            statusName: 'code' //数据状态的字段名称，默认：code
            , statusCode: 1 //成功的状态码，默认：0
            , msgName: 'msg' //状态信息的字段名称，默认：msg
            , countName: 'count' //数据总数的字段名称，默认：count
            , dataName: 'data' //数据列表的字段名称，默认：data
        } //如果无需自定义数据响应名称，可不加该参数
        , where: {
            roomId: $('#searchRoomId').val(),
            buildingId: $('#searchBuildingId').val(),
            rentMonth: $('#searchMonth').val(),
            payTs: $('#searchRoomPayTs').val()
        }//传参*/
        , id: 'lay_table_rent'
        , page: true//开启分页
//            ,height: 315//容器高度
        , cols: [[//表头
            {checkbox: true, fixed: true}
            , {field: 'roomNo', title: '房间号', sort: true}
            , {field: 'rentMonth', title: '月份', sort: true}
            , {field: 'fee', title: '总费用', sort: true, templet: function(d){
                    return d.fee/100;
                }}
            , {field: 'count', title: '折扣费用', sort: true, templet: function(d){
                    return d.count/100;
                }}
            , {field: 'realFee', title: '实际费用', sort: true, templet: function(d){
                    return d.realFee/100;
                }}
            , {field: 'payTs', title: '缴费', sort: true, templet: function(d){
                    if(d.payTs > 0){
                        return '<a class="layui-btn layui-btn-xs">已缴费</a>';
                    }
                    return '<a class="layui-btn layui-btn-danger layui-btn-xs">未缴费</a>'
                }}
            , {field: '', title: '操作', align: 'center', toolbar: '#rentListBar'}
        ]]
        , done: function (res, curr, count) {
            //如果是异步请求数据方式，res即为你接口返回的信息。
            //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
            console.log(res);
            //得到当前页码
            console.log(curr);
            //得到数据总量
            console.log(count);
        }
    });

    //监听表格复选框选择
    table.on('checkbox(rentTableFilter)', function (obj) {
        console.log(obj);
        layer.alert("this is check all");
    });

    //监听工具条
    table.on('tool(rentTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'pay') {
            console.log(data);
            layer.confirm('请确认是否已缴费' + data.realFee/100 + '元', function (index) {
                var jhxhr = $.ajax({url: requestBaseUrl + "/rent/pay/" + data.rentId, headers: header, type: "GET"});
                jhxhr.done(function (res) {
                    if(res.code == 1){
                        active.search();
                        layer.msg("缴费成功");
                    }else{
                        layer.alert(res.msg);
                    }
                });
                layer.close(index);

            });
        } else if(obj.event === 'detail'){
            console.log(data);
            var jhxhr = $.ajax({url: requestBaseUrl + "/rent/detail/" + data.rentId, headers: header, type: "GET"});
            jhxhr.done(function (res) {
                if(res.code == 1){
                    active.detail(res.data);
                }else{
                    layer.alert(res.msg);
                }
            });
        } else if(obj.event === 'edit'){
            var jhxhr = $.ajax({url: requestBaseUrl + "/rent/editExtra/" + data.rentId, headers: header, type: "GET"});
            jhxhr.done(function (res) {
                if(res.code == 1){
                    var rent = data;
                    rent.extraList = res.data;
                    active.edit(rent);
                }else{
                    layer.alert(res.msg);
                }
            });
        }
    });

    form.on('select(rentSearchFormSelectBuildingFilter)', function (data) {
        var jhxhr = $.ajax({url: requestBaseUrl + "/room/roomAll/" + data.value, headers: header, type: "GET"});
        jhxhr.done(function (res) {
            if(res.code == 1){
                $('#searchRoomId').html('');
                if(res.data.length > 0){
                    var option = $('<option>').val('').text('全部');
                    $('#searchRoomId').append(option)
                    $.each(res.data, function (index, val) {
                        option = $('<option>').val(val.roomId).text(val.roomNo);
                        $('#searchRoomId').append(option)
                    });
                }
                //重新渲染
                form.render('select', 'rentSearchFormFilter');
                $('#searchRoomId').get(0).selectedIndex = 0;
            }else{
                layer.alert(res.msg)
            }
        });
    });

    var active = {
        search: function () {
            //执行重载
            table.reload('lay_table_rent', {
                // url: requestBaseUrl + '/rent/list'
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    roomId: $('#searchRoomId').val(),
                    buildingId: $('#searchBuildingId').val(),
                    rentMonth: $('#searchMonth').val(),
                    payTs: $('#searchRoomPayTs').val()
                }
            });
        },
        add: function () {
            if(isEmpty($('#searchRoomId option:selected').val())){
                layer.alert("请先选择一个房间!")
            }
            var jhxhr = $.ajax({url: requestBaseUrl + "/room/barginExtra/" + $('#searchRoomId option:selected').val(), headers: header, type: "GET"});
            jhxhr.done(function (res) {
                if(res.code == 1){
                    if(res.data.length < 1){
                        return layer.alert("该房间尚未出租,无法计算房租!");
                    }
                    chargeExtra = {};
                    chargeExtra.extraList = res.data;
                    chargeExtra.roomId = $('#searchRoomId option:selected').val();
                    $('#chargeRoomNo').val($('#searchRoomId option:selected').text());
                    $('#chargeBuilding').val($('#searchBuildingId option:selected').text());
                    var getTpl = rentChargeTemplete.innerHTML;
                    var view = document.getElementById('chargeView');
                    laytpl(getTpl).render(res.data, function(html){
                        view.innerHTML = html;
                    });
                    layer.open({
                        type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                        ,title: "录入房租"
                        , area: '500px'
                        , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                        , id: 'layerRentCharge'  //防止重复弹出
                        , content: $('#addChargeDiv')
                        , btn: '关闭全部'
                        , btnAlign: 'c' //按钮居中
//                    ,shade: 0 //不显示遮罩
                        , yes: function () {
                            layer.closeAll();
                        }
                    });
                }else{
                    layer.alert(res.msg)
                }
            });
        },
        detail: function (rent) {
            //初始化详情页面
            //方法级渲染
            var getTpl = rentDetailTemplete.innerHTML;
            var view = document.getElementById('rentDetailTableTbody');
            laytpl(getTpl).render(rent, function(html){
                view.innerHTML = html;
            });

            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "房租详情"
                , area: '800px'
                , offset: '100px' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerRentDetail'  //防止重复弹出
                , content: $('#rentDetailDiv')
//                    ,shade: 0 //不显示遮罩
                , yes: function () {
                    layer.closeAll();
                }
            });
            table.render({
                elem: '#rentDetailItermTable'//指定原始表格元素选择器（
                , data: rent.rentItermList
                , id: 'rentDetailItermTable'
                // , width: 550
                , cols: [[//表头
                    {field: 'value', title: '项目', templet: function(d){
                            return d.value;
                        }}
                    , {field: 'unit', title: '单位', width:60, templet: function(d){
                            return d.unit;
                        }}
                    , {field: 'price', title: '单价', width:100, templet: function(d){
                            return d.price/100;
                        }}
                    , {field: 'number', title: '数量', width:100, templet: function(d){
                            return undefined == d.number ? "" : d.number;
                        }}
                    , {field: 'money', title: '金额', width:120, templet: function(d){
                            return d.money/100;
                        }}
                    , {field: 'description', title: '描述', templet: function(d){
                            return d.description;
                        }}
                ]]
                , done: function (res, curr, count) {
                    console.log(res.data)
                }
            });
        },
        edit: function (rent) {
            chargeExtra = rent;
            $('#chargeEditRoomNo').val($('#searchRoomId option:selected').text());
            $('#chargeEditBuilding').val($('#searchBuildingId option:selected').text());
            $('#chargeEditMonth').val(rent.rentMonth);
            $('#chargeEditMonth').attr("disabled",true);
            var getTpl = rentChargeEditTemplete.innerHTML;
            var view = document.getElementById('chargeEditView');
            laytpl(getTpl).render(rent.extraList, function(html){
                view.innerHTML = html;
            });
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "编辑房租"
                , area: '500px'
                , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerRentChargeEdit'  //防止重复弹出
                , content: $('#rentChargeEditDiv')
                , btn: '关闭全部'
                , btnAlign: 'c' //按钮居中
//                    ,shade: 0 //不显示遮罩
                , yes: function () {
                    layer.closeAll();
                }
            });
        },
    };

    //绑定搜索点击事件
    $('.childrenBody .layui-btn').on('click', function () {
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });
});