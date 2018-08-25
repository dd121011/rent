var chargeExtra;
var extraTableData;
var depositItermTableData;
layui.use(['layer', 'table', 'form', 'laytpl'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var table = layui.table;
    var form = layui.form;
    var laytpl = layui.laytpl;

    //方法级渲染
    table.render({
        elem: '#lay_table_room'//指定原始表格元素选择器（
        , url: requestBaseUrl + '/room/list/' + $('#searchBuildingId').val()//数据接口
        , method: 'post'
        , contentType: 'application/json'
        , where: {
            body: {
                roomId: $('#searchRoomId').val(),
                rentTs: $('#searchRoomRentTs').val()
            }
        }
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
        , id: 'lay_table_room'
        , page: true//开启分页
//            ,height: 315//容器高度
        , cols: [[//表头
            {field: 'roomNo', title: '房间号', sort: true, width: 100}
            , {field: 'roomStyle', title: '房型', sort: true, width: 100, templet: function(d){
                    return d.bedroom + '房' + d.living + '厅' + d.toilet + '卫';
                }}
            , {field: 'guarantyAndPay', title: '押付情况', width: 100, templet: function(d){
                    return '押' + d.guaranty + '付' + d.pay;
                }}
            , {field: 'rentFee', title: '租金[元/月]', sort: true, width: 150, templet: function(d){
                    return d.rentFee/100;
                }}
            , {field: 'rentTs', title: '是否出租', width: 100, templet: function(d){
                    if(d.rentTs > 0){
                        return '<a class="layui-btn layui-btn-xs">已出租</a>';
                    }
                    return '<a class="layui-btn layui-btn-danger layui-btn-xs">未出租</a>'
                }}
            , {field: '', title: '操作', align: 'left', toolbar: '#roomListBar'}
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
    table.on('checkbox(roomTableFilter)', function (obj) {
        console.log(obj);
        layer.alert("this is check all");
    });

    //监听工具条
    table.on('tool(roomTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {

                var jhxhr = $.ajax({url: requestBaseUrl + "/room/delete", data:{"ids": data.roomId}, headers: header, type: "POST"});
                jhxhr.done(function (res) {
                    if(res.code == 1){
                        layer.msg("删除成功");
                        obj.del();
                    }else{
                        layer.alert(res.msg);
                    }
                });
                layer.close(index);
            });
        } else if (obj.event === 'edit') {
            form.val("roomEditFormFilter", {
                "buildingId": data.buildingId
                ,"roomId": data.roomId
                ,"roomNo": data.roomNo
                ,"orientation": data.orientation
                ,"decoration": data.decoration
                ,"bedroom": data.bedroom
                ,"living": data.living
                ,"toilet": data.toilet
                ,"guaranty": data.guaranty
                ,"pay": data.pay
                ,"rentFee": data.rentFee/100
                ,"area": data.area/10000
                ,"description": data.description
            });
            var facility = data.facilities.split(",");
            $.each($('input[type=checkbox][name=facilityIds]'),function(){
                var flag = 1;
                for(j = 0, len=facility.length; j < len; j++) {
                    if(facility[j] == $(this).val()){
                        flag = 0;
                        $(this).attr("checked",true);
                        $(this).next().addClass("layui-form-checked");
                        break;
                    }
                }
                if(flag){
                    $(this).attr("checked",false);
                    $(this).next().removeClass("layui-form-checked");
                }
            });
            var extra = data.extraFee.split(",");
            $.each($('input[type=checkbox][name=extraIds]'),function(){
                var flag = 1;
                for(j = 0, len=extra.length; j < len; j++) {
                    if(extra[j] == $(this).val()){
                        flag = 0;
                        $(this).attr("checked",true);
                        $(this).next().addClass("layui-form-checked");
                        break;
                    }
                }
                if(flag){
                    $(this).attr("checked",false);
                    $(this).next().removeClass("layui-form-checked");
                }
            });
            var deposit = data.deposits.split(",");
            $.each($('input[type=checkbox][name=depositIds]'),function(){
                var flag = 1;
                for(j = 0, len=extra.length; j < len; j++) {
                    if(deposit[j] == $(this).val()){
                        flag = 0;
                        $(this).attr("checked",true);
                        $(this).next().addClass("layui-form-checked");
                        break;
                    }
                }
                if(flag){
                    $(this).attr("checked",false);
                    $(this).next().removeClass("layui-form-checked");
                }
            });
            active.edit();
        }else if (obj.event === 'rentHistoty') {
            //出租记录
            layer.msg('ID：' + data.roomId + ' 的查看操作');
        } else if (obj.event === 'rent'){
            var jhxhr = $.ajax({url: requestBaseUrl + "/room/detail/" + data.roomId, headers: header, type: "GET"});
            jhxhr.done(function (res) {
                if(res.code == 1){
                    var getTpl = roomRentFacilitiesTemplete.innerHTML;
                    var view = document.getElementById('roomRentFacilities');
                    laytpl(getTpl).render(res.data.facilitiesIterm, function(html){
                        view.innerHTML = html;
                    });
                    form.val("rentEditFormFilter", {
                        "roomId": res.data.roomId
                        ,"rentFee": res.data.rentFee/100
                        ,"rentDay": res.data.rentDay
                        ,"guaranty": res.data.guaranty
                        ,"pay": res.data.pay
                    });
                    active.rent(res.data);
                }else{
                    layer.alert(res.msg)
                }
            });
        } else if (obj.event === 'continue'){
            //续约
        } else if (obj.event === 'detail'){
            location.href= requestBaseUrl + "/room/goRoomDetail/" + data.roomId + "?tokenId=" + tokenId;
        } else if (obj.event === 'charge'){
            var jhxhr = $.ajax({url: requestBaseUrl + "/room/barginExtra/" + data.roomId, headers: header, type: "GET"});
            jhxhr.done(function (res) {
                if(res.code == 1){
                    if(res.data < 1){
                        return layer.alert("该房间尚未出租,无法计算房租!");
                    }
                    chargeExtra = {};
                    chargeExtra.extraList = res.data;
                    chargeExtra.roomId = data.roomId;
                    $('#chargeRoomNo').val(data.roomNo);
                    $('#chargeBuilding').val($('#searchBuildingId option:selected').text());
                    var getTpl = roomChargeTemplete.innerHTML;
                    var view = document.getElementById('chargeView');
                    laytpl(getTpl).render(res.data, function(html){
                        view.innerHTML = html;
                    });
                    active.charge();
                }else{
                    layer.alert(res.msg)
                }
            });

        } else if (obj.event === 'leave'){
            active.rentLeave(data.roomId);
        }
    });

    var active = {
        search: function () {
            //执行重载
            table.reload('lay_table_room', {
                url: requestBaseUrl + '/room/list/' + $('#searchBuildingId').val()//数据接口
                ,page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    body: {
                        roomId: $('#searchRoomId').val(),
                        rentTs: $('#searchRoomRentTs').val()
                    }
                }//传参*/
            });
        },
        add: function () {
            $(".rmcl").val("");
            // // $("select").val("");
            $("[name='description']").val("");
            $.each($('input[type=checkbox]'),function(){
                $(this).attr("checked",true);
                $(this).next().addClass("layui-form-checked");
            });
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "新增房间"
                , area: '500px'
                , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerRoomAdd' //防止重复弹出
                , content: $('#addDiv')
                , btn: '关闭全部'
                , btnAlign: 'c' //按钮居中
//                    ,shade: 0 //不显示遮罩
                , yes: function () {
                    layer.closeAll();
                }
            });
        },
        edit: function () {
            $("input[name='roomNo']").attr("readonly","readonly");
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "编辑房间"
                , area: '500px'
                , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerRoomEdit'  //防止重复弹出
                , content: $('#addDiv')
                , btn: '关闭全部'
                , btnAlign: 'c' //按钮居中
//                    ,shade: 0 //不显示遮罩
                , yes: function () {
                    layer.closeAll();
                }
            });
        },
        charge: function () {
            $("input[name='roomNo']").attr("readonly","readonly");
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "录入房租"
                , area: '500px'
                , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerRoomCharge'  //防止重复弹出
                , content: $('#addChargeDiv')
                , btn: '关闭全部'
                , btnAlign: 'c' //按钮居中
//                    ,shade: 0 //不显示遮罩
                , yes: function () {
                    layer.closeAll();
                }
            });
        },
        rentLeave: function (roomId) {
            layer.confirm('真的要办理退房吗, 请先退还押金', function (index) {
                var jhxhr = $.ajax({url: requestBaseUrl + "/room/rentLeave/" + roomId, headers: header, type: "GET"});
                jhxhr.done(function (res) {
                    if(res.code == 1){
                        //执行重载
                        active.search();
                    }else{
                        layer.alert(res.msg)
                    }
                });
                layer.close(index);
            });
        },
        rent: function (room) {
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "办理入住"
                , area: '600px'
                , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerRentAdd'  //防止重复弹出
                , content: $('#addRentDiv')
                , btn: '关闭全部'
                , btnAlign: 'c' //按钮居中
                // , shade: 0 //不显示遮罩
                , yes: function () {
                    layer.closeAll();
                }
                ,success: function(layero, index){
                    console.log(layero, index);
                }
            });

            //额外收费项table
            table.render({
                elem: '#extraTable'//指定原始表格元素选择器（
                , data: room.extraFeeIterm
                , id: 'extraTable'
                // , width: 550
                , cols: [[//表头
                    {field: 'value', title: '项目', templet: function(d){
                            return d.value;
                        }}
                    , {field: 'unit', title: '单位', templet: function(d){
                            return d.unit;
                        }}
                    , {field: 'price', title: '单价', edit: 'text', templet: function(d){
                            return '';
                        }}
                    , {field: 'number', title: '初始数量', edit: 'text', templet: function(d){
                            return '';
                        }}
                ]]
                , done: function (res, curr, count) {
                    extraTableData = res.data;
                    console.log(extraTableData)
                }
            });

            //押金项Table
            table.render({
                elem: '#depositTable'//指定原始表格元素选择器（
                , data: room.depositIterm
                , id: 'depositTableEdit'
                // , width: 550
                , cols: [[//表头
                    {field: 'value', title: '项目', templet: function(d){
                            return d.value;
                        }}
                    , {field: 'unit', title: '单位', templet: function(d){
                            return d.unit;
                        }}
                    , {field: 'price', title: '单价', edit: 'text', templet: function(d){
                            return undefined == d.price ? "" : d.price;
                        }}
                    , {field: 'number', title: '数量', edit: 'text', templet: function(d){
                            return undefined == d.number ? "" : d.number;
                        }}
                    , {field: 'money', title: '金额', edit: 'text', templet: function(d){
                            return undefined == d.money ? "" : d.money;
                        }}
                ]]
                , done: function (res, curr, count) {
                    depositItermTableData = res.data;
                    console.log(depositItermTableData)
                }
            });
        },
    };

    //绑定搜索点击事件
    $('.childrenBody .layui-btn').on('click', function () {
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });

    form.on('select(roomSearchFormSelectBuildingFilter)', function (data) {
        var jhxhr = $.ajax({url: requestBaseUrl + "/room/roomAll/" + data.value, headers: header, type: "GET"});
        jhxhr.done(function (res) {
            if(res.code == 1){
                $('#searchRoomId').html('');
                if(res.data.length > 0){
                    var option = $('<option>').val('-1').text('全部');
                    $('#searchRoomId').append(option);
                    $.each(res.data, function (index, val) {
                        option = $('<option>').val(val.roomId).text(val.roomNo);
                        $('#searchRoomId').append(option);
                    });
                }
                //重新渲染
                form.render('select', 'roomSearchFormFilter');
                $('#searchRoomId').get(0).selectedIndex = 0;
            }else{
                layer.alert(res.msg)
            }
        });
    });

    table.on('edit(extraTableFilter)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
        console.log(extraTableData);

    });

    table.on('edit(depositTableFilter)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
        var othis = $(this);
        if(obj.field == "number" || obj.field == "price"){
            if(undefined != obj.data.number && undefined != obj.data.price){
                obj.data.money = Number(obj.data.number) * Number(obj.data.price)/1;
                console.log(depositItermTableData);
                console.log(depositItermTableData.length);

                table.render({
                    elem: '#depositTable'//指定原始表格元素选择器（
                    , data: depositItermTableData
                    , id: 'depositTableEdit'
                    , cols: [[//表头
                        {field: 'value', title: '项目', templet: function(d){
                                return d.value;
                            }}
                        , {field: 'unit', title: '单位', templet: function(d){
                                return d.unit;
                            }}
                        , {field: 'price', title: '单价', edit: 'text', templet: function(d){
                                return undefined == d.price ? "" : d.price;
                            }}
                        , {field: 'number', title: '数量', edit: 'text', templet: function(d){
                                return undefined == d.number ? "" : d.number;
                            }}
                        , {field: 'money', title: '金额', edit: 'text', templet: function(d){
                                return undefined == d.money ? "" : d.money;
                            }}
                    ]]
                    , done: function (res, curr, count) {
                        depositItermTableData = res.data;
                        console.log(depositItermTableData)
                    }
                });
            }
        }
    });
});