var chageExtra;
layui.use(['layer', 'table', 'form', 'laytpl'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var table = layui.table;
    var form = layui.form;
    var laytpl = layui.laytpl;

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
        if (obj.event === 'edit') {
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
            active.edit();
        } else if(obj.event === 'detail'){
            active.detail();
        }
    });

    form.on('select(rentSearchFormSelectBuildingFilter)', function (data) {
        var jhxhr = $.ajax({url: requestBaseUrl + "/room/roomAll/" + data.value, headers: header, type: "GET"});
        jhxhr.done(function (res) {
            if(res.code == 1){
                $('#searchRoomId').html('');
                $.each(res.data, function (index, val) {
                    var option = $('<option>').val(val.roomId).text(val.roomNo);
                    $('#searchRoomId').append(option)
                });
                //重新渲染
                form.render('select', 'rentSearchFormFilter');
                $('#searchRoomId').get(0).selectedIndex = 0;
            }else{
                layer.alert(res.msg)
            }
        });
        console.log(data.elem);
        console.log(data.value);
        console.log(data.othis);
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
                    payTs: $('#searchRoomPayTs').val()
                }
            });
        },
        detail: function () {
            //执行重载
            table.reload('lay_table_rent', {
                // url: requestBaseUrl + '/rent/list'
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    roomId: $('#searchRoomId').val(),
                    buildingId: $('#searchBuildingId').val(),
                    payTs: $('#searchRoomPayTs').val()
                }
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
    };

    //绑定搜索点击事件
    $('.childrenBody .layui-btn').on('click', function () {
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });
});