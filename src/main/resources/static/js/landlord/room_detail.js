var extraTableData;
var depositItermTableData;
layui.use(['layer', 'table', 'form'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var table = layui.table;
    var form = layui.form;

    //生成房间二维码
    var qrcode = new QRCode('qrcode', {
        text: 'https://scrats.cn/rent/qr?method=bindUser&data=' + $('#roomId').val(),
        width: 256,
        height: 256,
        colorDark : '#000000',
        colorLight : '#ffffff',
        correctLevel : QRCode.CorrectLevel.H
    });

    //租客Table
    table.render({
        elem: '#lay_table_room_renter'//指定原始表格元素选择器（
        , url: requestBaseUrl + '/room/renterAll/' + $('#roomId').val()
        , method: 'get'
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
        , id: 'lay_table_room_renter'
        , cols: [[//表头
            {field: 'name', title: '租客姓名', templet: function(d){
                    return d.user.name;
                }}
            , {field: 'sex', title: '租客性别', templet: function(d){
                    return d.user.sexName;
                }}
            , {field: 'phone', title: '手机号', templet: function(d){
                    return d.phone;
                }}
            , {field: 'idCard', title: '身份证号', templet: function(d){
                    return d.idCard == undefined ? "" : d.idCard;
                }}
            , {field: 'wechat', title: '微信号', templet: function(d){
                    return d.user.wechat;
                }}
            , {field: 'qq', title: 'QQ号', templet: function(d){
                    return d.user.qq;
                }}
            , {field: '', title: '操作', align: 'center', toolbar: '#renterListBar'}
        ]]
        , done: function (res, curr, count) {
        }
    });

    //租客Table
    table.render({
        elem: '#lay_table_bargin_room'//指定原始表格元素选择器（
        , url: requestBaseUrl + '/bargin/list'
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
            roomId: $('#roomId').val(),
            deleteTs: -1
        }//传参*/
        , id: 'lay_table_bargin_room'
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
                    return new Date(d.leaveTs).Format('yyyy-MM-dd');
                }}
            , {field: '', title: '操作', align: 'center', toolbar: '#barginRoomListBar'}
        ]]
        , done: function (res, curr, count) {

        }
    });

    var active = {
        rentAdd: function () {
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "添加租客"
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
                    console.log(layero, index);
                    //额外收费项table
                    table.render({
                        elem: '#extraTable'//指定原始表格元素选择器（
                        , url: requestBaseUrl + '/room/extra/' + $('#roomId').val()
                        , method: 'get'
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
                        , url: requestBaseUrl + '/room/depositIterm/' + $('#roomId').val()
                        , method: 'get'
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
                }
            });
        },
        renterAdd: function () {
            $("input[type!=checkbox]").val("");
            $("select").val("");
            $("[name='remark']").val("");
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
        qrcodeRenter: function () {
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
                var jhxhr = $.ajax({url: requestBaseUrl + "/room/rentLeave/" + $('#roomId').val(), headers: header, type: "GET"});
                jhxhr.done(function (res) {
                    if(res.code == 1){
                        //执行重载
                        table.reload('lay_table_room_renter', { });
                    }else{
                        layer.alert(res.msg)
                    }
                });
                layer.close(index);
            });
        },
    };

    //绑定搜索点击事件
    $('.childrenBody .layui-btn').on('click', function () {
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });

    //监听工具条
    table.on('tool(renterRoomTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'detail') {
            location.href= requestBaseUrl + "/room/goRoom/" + data.buildingId + "?tokenId=" + tokenId;
        } else if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                var jhxhr = $.ajax({url: requestBaseUrl + "/room/renterDelete/" + $('#roomId').val() + "/" + data.roomRenterId, headers: header, type: "GET"});
                jhxhr.done(function (res) {
                    if(res.code == 1){
                        obj.del();
                    }else{
                        layer.alert(res.msg)
                    }
                });
                layer.close(index);
            });
        } else if (obj.event === 'edit') {
            form.val("renterEditFormFilter", {
                "roomRenterId": data.roomRenterId
                ,"name": data.user.name
                ,"sex": data.user.sex
                ,"phone": data.phone
                ,"idCard": data.idCard
                ,"remark": data.user.remark
            });
            active.renterEdit();
        }
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