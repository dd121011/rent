var extraTableData;
var depositItermTableData;
layui.use(['layer', 'table', 'form', 'laytpl'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var table = layui.table;
    var form = layui.form;
    var laytpl = layui.laytpl;

    //生成房间二维码
    new QRCode('qrcode', {
        text: 'https://scrats.cn/rent/qr?method=bindRoom&data=' + $('#roomDetailRoomId').val(),
        width: 256,
        height: 256,
        colorDark : '#000000',
        colorLight : '#ffffff',
        correctLevel : QRCode.CorrectLevel.H
    });

    //租客Table
    table.render({
        elem: '#lay_table_room_renter'//指定原始表格元素选择器（
        , url: requestBaseUrl + '/room/renterAll/' + $('#roomDetailRoomId').val()
        , method: 'get'
        , headers: header
        , response: {
            statusName: 'code' //数据状态的字段名称，默认：code
            , statusCode: 1 //成功的状态码，默认：0
            , msgName: 'msg' //状态信息的字段名称，默认：msg
            , countName: 'count' //数据总数的字段名称，默认：count
            , dataName: 'data' //数据列表的字段名称，默认：data
        } //如果无需自定义数据响应名称，可不加该参数
        , id: 'lay_table_room_renter'
        , cols: [[//表头
            {field: 'name', title: '租客姓名', templet: function(d){
                    return d.user.name;
                }}
            , {field: 'sex', title: '租客性别', templet: function(d){
                    return d.user.sexName;
                }}
            , {field: 'phone', title: '手机号', templet: function(d){
                    return d.user.hidePhone;
                }}
            , {field: 'idCard', title: '身份证号', templet: function(d){
                    return d.user.hideIdCard;
                }}
            , {field: 'wechat', title: '微信号', templet: function(d){
                    return d.user.wechat;
                }}
            , {field: 'qq', title: 'QQ号', templet: function(d){
                    return d.user.qq;
                }}
            , {field: 'check', title: '核验', templet: function(d){
                    if(d.checkTs == 0){
                        if(d.deleteTs > 0){
                            return "离开待核验";

                        }else{
                            return "入住待核验";
                        }

                    }
                    return "已核验入住";
                }}
            , {field: '', title: '操作', align: 'center', toolbar: '#renterListBar'}
        ]]
        , done: function (res, curr, count) {
        }
    });

    //租赁历史Table
    table.render({
        elem: '#lay_table_room_history'//指定原始表格元素选择器（
        , url: requestBaseUrl + '/bargin/list'
        , method: 'post'
        , contentType: 'application/json'
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
            body: {
                roomId: $('#roomDetailRoomId').val(),
                deleteTs: 1
            }
        }//传参*/
        , id: 'lay_table_room_history'
        , page: true//开启分页
        , cols: [[//表头
            {field: 'name', title: '姓名', templet: function(d){
                return d.name;
            }}
            , {field: 'phone', title: '手机号', templet: function(d){
                    return d.phone;
                }}
            , {field: 'idCard', title: '身份证号', width: 180, templet: function(d){
                    return d.idCard == undefined ? "" : d.idCard;
                }}
            , {field: 'rentFee', title: '租金', width: 90, templet: function(d){
                    return d.rentFee/100;
                }}
            , {field: 'guarantyAndPay', title: '押付方式', width: 100, templet: function(d){
                    return "押" + d.guaranty + "付" + d.pay;
                }}
            , {field: 'liveTs', title: '入住时间', templet: function(d){
                    return new Date(d.liveTs).Format('yyyy-MM-dd');
                }}
            , {field: 'leaveTs', title: '退租时间', templet: function(d){
                    return new Date(d.deleteTs).Format('yyyy-MM-dd');
                }}
            , {field: '', title: '操作', align: 'center', toolbar: '#barginRoomListBar'}
        ]]
        , done: function (res, curr, count) {

        }
    });

    var active = {
        rentAdd: function () {
            form.val("rentEditFormFilter", {
                "name": ""
                ,"sex": "0"
                ,"idCard": ""
                ,"phone": ""
                ,"smsCode": ""
                ,"liveTs": ""
                ,"leaveTs": ""
                ,"remark": ""
            });
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "办理入住"
                , area: '600px'
                , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerRentEdit'  //防止重复弹出
                , content: $('#addRentDiv')
                , btn: '关闭全部'
                , btnAlign: 'c' //按钮居中
                // , shade: 0 //不显示遮罩
                , yes: function () {
                    layer.closeAll();
                }
                ,success: function(layero, index){
                    smsCodeGenerateClick($('#rentEditFormPhoneInput'), $('#rentFormSmsCodeGenerate'));
                    console.log(layero, index);
                }
            });
            //额外收费项table
            table.render({
                elem: '#extraTableEdit'//指定原始表格元素选择器（
                , url: requestBaseUrl + '/room/extra/' + $('#roomDetailRoomId').val()
                , method: 'get'
                , headers: header
                , response: {
                    statusName: 'code' //数据状态的字段名称，默认：code
                    , statusCode: 1 //成功的状态码，默认：0
                    , msgName: 'msg' //状态信息的字段名称，默认：msg
                    , countName: 'count' //数据总数的字段名称，默认：count
                    , dataName: 'data' //数据列表的字段名称，默认：data
                } //如果无需自定义数据响应名称，可不加该参数
                , id: 'extraTableEdit'
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
                elem: '#depositTableEdit'//指定原始表格元素选择器（
                , url: requestBaseUrl + '/room/depositIterm/' + $('#roomDetailRoomId').val()
                , method: 'get'
                , headers: header
                , response: {
                    statusName: 'code' //数据状态的字段名称，默认：code
                    , statusCode: 1 //成功的状态码，默认：0
                    , msgName: 'msg' //状态信息的字段名称，默认：msg
                    , countName: 'count' //数据总数的字段名称，默认：count
                    , dataName: 'data' //数据列表的字段名称，默认：data
                } //如果无需自定义数据响应名称，可不加该参数
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
        renterAdd: function () {
            form.val("renterEditFormFilter", {
                "name": ""
                ,"idCard": ""
                ,"phone": ""
                ,"smsCode": ""
                ,"remark": ""
            });

            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "添加租客"
                , area: '500px'
                , offset: 'auto' //
                , id: 'layerRenterEdit'  //防止重复弹出
                , content: $('#addRenterDiv')
                , btn: '关闭全部'
                , btnAlign: 'c' //按钮居中
                // , shade: 0 //不显示遮罩
                , yes: function () {
                    layer.closeAll();
                }
                , success: function(layero, index){
                    smsCodeGenerateClick($('#renterEditFormPhoneInput'), $('#renterFormSmsCodeGenerate'));
                    console.log(layero, index);
                }
            });
        },
        renterEdit: function () {
            $("input[name='name']").attr("readonly","readonly");
            $("input[name='sex']").attr("disabled","disabled");
            $("input[name='idCard']").attr("readonly",true);
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "修改租客"
                , area: '500px'
                , offset: 'auto' //
                , id: 'layerRenterEdit'  //防止重复弹出
                , content: $('#addRenterDiv')
                , btn: '关闭全部'
                , btnAlign: 'c' //按钮居中
                // , shade: 0 //不显示遮罩
                , yes: function () {
                    layer.closeAll();
                }
            });
        },
        bindRoom: function () {
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "房间二维码"
                , area: '300px'
                , offset: 'auto' //
                , id: 'layerQrcodeRoom'  //防止重复弹出
                , content: $('#qrcode')
                , btn: '关闭全部'
                , btnAlign: 'c' //按钮居中
                // , shade: 0 //不显示遮罩
                , yes: function () {
                    layer.closeAll();
                }
            });
        },
        rentLeave: function () {
            layer.confirm('真的要办理退房吗, 请先退还押金', function (index) {
                var jhxhr = $.ajax({url: requestBaseUrl + "/room/rentLeave/" + $('#roomDetailRoomId').val(), headers: header, type: "GET"});
                jhxhr.done(function (res) {
                    if(res.code == 1){
                        //执行重载
                        table.reload('lay_table_room_renter', { });
                    }else{
                        layer.alert(res.message);
                    }
                });
                layer.close(index);
            });
        },
        roomBargin: function () {
            var jhxhr = $.ajax({url: requestBaseUrl + "/room/bargin/" + $('#roomDetailRoomId').val(), headers: header, type: "GET"});
            jhxhr.done(function (res) {
                if(res.code == 1){
                    active.showBargin(res.data);
                }else{
                    layer.alert(res.message);
                }
            });
        },
        showBargin: function (data) {
            var facilitiesName = '';
            for(i=0;i<data.facilities.length;i++){
                facilitiesName += data.facilities[i].value + ',';
            }
            data.facilitiesName = facilitiesName;
            data.liveTs = new Date(data.bargin.liveTs).Format('yyyy-MM-dd');
            data.leaveTs = new Date(data.bargin.leaveTs).Format('yyyy-MM-dd');
            data.signTs = new Date(data.bargin.createTs).Format('yyyy-MM-dd');
            //方法级渲染
            var getTpl = roomBarginTemplete.innerHTML;
            var view = document.getElementById('roomBarginTableTbody');
            laytpl(getTpl).render(data, function(html){
                view.innerHTML = html;
            });

            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "合同详情"
                , area: '800px'
                , offset: '100px' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerRoomBargin'  //防止重复弹出
                , content: $('#roomBarginDiv')
//                    ,shade: 0 //不显示遮罩
                , yes: function () {
                    layer.closeAll();
                }
            });
            table.render({
                elem: '#roomBarginItermTable'//指定原始表格元素选择器（
                , data: data.extras
                , id: 'roomBarginItermTable'
                // , width: 550
                , cols: [[//表头
                    {field: 'value', title: '项目', templet: function(d){
                            return d.value;
                        }}
                    , {field: 'unit', title: '单位', templet: function(d){
                            return d.unit;
                        }}
                    , {field: 'price', title: '单价', templet: function(d){
                            return d.price/100;
                        }}
                    , {field: 'number', title: '初始数量', templet: function(d){
                            return undefined == d.number || d.number < 0 ? "" : d.number;
                        }}
                ]]
                , done: function (res, curr, count) {
                    console.log(res.data)
                }
            });
        },
        roomDeposit: function () {
            var jhxhr = $.ajax({url: requestBaseUrl + "/room/deposit/" + $('#roomDetailRoomId').val(), headers: header, type: "GET"});
            jhxhr.done(function (res) {
                if(res.code == 1){
                    active.showDeposit(res.data);
                }else{
                    layer.alert(res.message);
                }
            });
        },
        showDeposit: function (data) {
            data.signTs = new Date(data.deposit.createTs).Format('yyyy-MM-dd');
            data.payTs = data.deposit.payTs == 0 ? '' :new Date(data.deposit.payTs).Format('yyyy-MM-dd');
            //方法级渲染
            var getTpl = roomDepositTemplete.innerHTML;
            var view = document.getElementById('roomDepositTableTbody');
            laytpl(getTpl).render(data, function(html){
                view.innerHTML = html;
            });

            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "押金详情"
                , area: '800px'
                , offset: '100px' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerRoomDeposit'  //防止重复弹出
                , content: $('#roomDepositDiv')
//                    ,shade: 0 //不显示遮罩
                , yes: function () {
                    layer.closeAll();
                }
            });
            table.render({
                elem: '#roomDepositItermTable'//指定原始表格元素选择器（
                , data: data.deposit.depositItermList
                , id: 'roomDepositItermTable'
                // , width: 550
                , cols: [[//表头
                    {field: 'value', title: '项目', templet: function(d){
                            return d.value;
                        }}
                    , {field: 'unit', title: '单位', templet: function(d){
                            return d.unit;
                        }}
                    , {field: 'price', title: '单价', templet: function(d){
                            return d.price/100;
                        }}
                    , {field: 'number', title: '数量', templet: function(d){
                            return undefined == d.number || d.number < 0 ? "" : d.number;
                        }}
                    , {field: 'money', title: '金额', templet: function(d){
                            return undefined == d.money || d.money < 0 ? "" : d.money/100;
                        }}
                ]]
                , done: function (res, curr, count) {
                    console.log(res.data)
                }
            });
        },
    };

    //绑定搜索点击事件
    $('.childrenBody .layui-btn').on('click', function () {
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });

    //监听工具条
    table.on('tool(roomRenterTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'renterCheck') {
            var checkType = "入住";
            if(data.deleteTs > 0){
                checkType = "离开";
            }
            layer.confirm('请确认是否已经办理完' + checkType + '手续', function (index) {
                var jhxhr = $.ajax({url: requestBaseUrl + "/room/renterCheck/" + $('#roomDetailRoomId').val() + "/" + data.roomRenterId, headers: header, type: "GET"});
                jhxhr.done(function (res) {
                    if(res.code == 1){
                        layer.msg(checkType + "核验成功");
                        table.reload('lay_table_room_renter', { });
                    }else{
                        layer.alert(res.message);
                    }
                });
                layer.close(index);
            });
        } else if (obj.event === 'del') {
            layer.confirm('真的删除当前租客么', function (index) {
                var jhxhr = $.ajax({url: requestBaseUrl + "/room/renterDelete/" + $('#roomDetailRoomId').val() + "/" + data.roomRenterId, headers: header, type: "GET"});
                jhxhr.done(function (res) {
                    if(res.code == 1){
                        obj.del();
                    }else{
                        layer.alert(res.message);
                    }
                });
                layer.close(index);
            });
        }
    });

    //监听工具条
    table.on('tool(roomHistoryTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'barginDetail') {
            var jhxhr = $.ajax({url: requestBaseUrl + "/bargin/bargin/" + data.barginId, headers: header, type: "GET"});
            jhxhr.done(function (res) {
                if(res.code == 1){
                    active.showBargin(res.data);
                }else{
                    layer.alert(res.message);
                }
            });
        }else if(obj.event === 'depositDetail') {
            var jhxhr = $.ajax({url: requestBaseUrl + "/bargin/deposit/" + data.barginId, headers: header, type: "GET"});
            jhxhr.done(function (res) {
                if(res.code == 1){
                    active.showDeposit(res.data);
                }else{
                    layer.alert(res.message);
                }
            });
        }
    });

    table.on('edit(extraTableEdit)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
        console.log(obj.data);
    });

    table.on('edit(depositTableEditFilter)', function(obj){ //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
        console.log(obj.data);
        if(obj.field == "number" || obj.field == "price"){
            if(undefined != obj.data.number && undefined != obj.data.price){
                obj.data.money = Number(obj.data.number) * Number(obj.data.price)/1;
                obj.update(obj.data);
                console.log(depositItermTableData);
                for(i=0, len=depositItermTableData.length; i< len; i++){
                    if(depositItermTableData[i].dicItermCode == obj.data.dicItermCode){
                        depositItermTableData[i].money = obj.data.money;
                        break;
                    }
                }
                console.log(depositItermTableData.length);
                table.reload('depositTableEdit', {
                    data : depositItermTableData
                });
            }
        }
    });
});