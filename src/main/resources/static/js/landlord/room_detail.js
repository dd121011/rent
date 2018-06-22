var extraTableData;
var depositItermTableData;
layui.use(['layer', 'table', 'form'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var table = layui.table;
    var form = layui.form;

    //租客Table
    table.render({
        elem: '#lay_table_room_renter'//指定原始表格元素选择器（
        , url: requestBaseUrl + '/room/renterAll/' + $('#roomId').val()
        , method: 'get'
        , headers: {tokenId: tokenId}
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
                    return d.user.name;
                }}
            , {field: 'idCard', title: '身份证号', templet: function(d){
                    return d.renter.idCard == undefined ? "" : d.renter.idCard;
                }}
            , {field: 'wechat', title: '微信号', templet: function(d){
                    return d.user.wechat;
                }}
            , {field: 'qq', title: 'QQ号', templet: function(d){
                    return d.user.qq;
                }}
        ]]
        , done: function (res, curr, count) {
        }
    });

    var active = {
        addBargin: function () {
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "添加租客"
                , area: '500px'
                , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerRentEdit'  //防止重复弹出
                , content: $('#addRenterDiv')
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
                        , headers: {tokenId: tokenId}
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
                            {field: 'name', title: '项目', edit: 'text', templet: function(d){
                                    return d.value;
                                }}
                            , {field: 'unit', title: '单位', edit: 'text', templet: function(d){
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
                        , headers: {tokenId: tokenId}
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
                        , id: 'depositTable'
                        // , width: 550
                        , cols: [[//表头
                            {field: 'name', title: '项目', edit: 'text', templet: function(d){
                                    return d.name;
                                }}
                            , {field: 'unit', title: '单位', edit: 'text', templet: function(d){
                                    return d.unit;
                                }}
                            , {field: 'price', title: '单价', edit: 'text', templet: function(d){
                                    return d.price/100;
                                }}
                            , {field: 'number', title: '数量', edit: 'text', templet: function(d){
                                    return d.number;
                                }}
                            , {field: 'money', title: '金额', edit: 'text', templet: function(d){
                                    return d.money/100;
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
        qrcodeRenter: function () {
            new QRCode($("#qrcode"), "http://www.baidu.com");
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "房间二维码"
                , area: '300px'
                , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerQrcodeRoom'  //防止重复弹出
                , content: $('#qrcodeShow')
                , btn: '关闭全部'
                , btnAlign: 'c' //按钮居中
                // , shade: 0 //不显示遮罩
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