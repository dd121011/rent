layui.use(['layer', 'table', 'form'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var table = layui.table;
    var form = layui.form;

    //方法级渲染

    table.render({
        elem: '#lay_table_room_renter'//指定原始表格元素选择器（
        , url: requestBaseUrl + '/room/roomRenter/' + $('#roomId').val()
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
        // , page: false//开启分页
//            ,height: 315//容器高度
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
        add: function () {
            // $("input[type!=checkbox][type!=select]").val("");
            // // $("select").val("");
            $("[name='description']").val("");
            $.each($('input[type=checkbox]'),function(){
                $(this).attr("checked",false);
                $(this).next().removeClass("layui-form-checked");
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
        rent: function () {
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
            });
        },
    };

    //绑定搜索点击事件
    $('.childrenBody .layui-btn').on('click', function () {
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });
});