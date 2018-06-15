layui.use(['element', 'layer', 'table', 'form'], function () {
    var $ = layui.$;
    // var $ = layui.jquery;
    var element = layui.element;
    var layer = layui.layer;
    var table = layui.table;
    var form = layui.form;

    layer.msg('hello');
    var selectBuilding = 1;
    //方法级渲染
    table.render({
        elem: '#lay_table_room'//指定原始表格元素选择器（
        , url: requestBaseUrl + '/room/list'//数据接口
        , method: 'post'
        , headers: {tokenId: tokenId}
        , request: {
            pageName: 'page' //页码的参数名称，默认：page
            , limitName: 'rows' //每页数据量的参数名，默认：limit
        } //如果无需自定义请求参数，可不加该参数
        , where: {
            buildingId : selectBuilding
        }
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
            {checkbox: true, fixed: true}
            , {field: 'roomId', title: 'ID'}
            , {field: 'roomNo', title: '房间号', sort: true, width: 100}
            , {field: 'style', title: '房型', sort: true, width: 100}
            , {field: 'orientation', title: '房间朝向', sort: true, width: 100}
            , {field: 'quaranty', title: '押金月份', width: 100}
            , {field: 'pay', title: '租金月份', sort: true, width: 100}
            , {field: 'rentFee', title: '租金[元/月]', sort: true, width: 100}
            , {field: 'description', title: '描述', width: 100}
            , {field: 'rentTs', title: '是否出租', width: 80}
            , {field: '', title: '操作', align: 'center', toolbar: '#barDemo'}
        ]]
        , done: function (res, curr, count) {
            //如果是异步请求数据方式，res即为你接口返回的信息。
            //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
            console.log(res);
            //得到当前页码
            console.log(curr);
            //得到数据总量
            console.log(count);
            $("[data-field='roomId']").css('display', 'none');//隐藏不需要显示的id字段
        }
    });

    //监听表格复选框选择
    table.on('checkbox(room)', function (obj) {
        console.log(obj);
        layer.alert("this is check all");
    });

    //监听工具条
    table.on('tool(room)', function (obj) {
        var data = obj.data;
        if (obj.event === 'detail') {
            layer.msg('ID：' + data.roomId + ' 的查看操作');
        } else if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {

                var jhxhr = $.ajax({url: requestBaseUrl + "/room/delete", data:{"ids": data.roomId}, headers: header, type: "POST"});
                jhxhr.done(function (res) {
                    var dat =$.parseJSON(res);
                    if(dat.code == 1){
                        obj.del();
                    }else{
                        layer.alert(dat.msg)
                    }
                });
                layer.close(index);
            });
        } else if (obj.event === 'edit') {
            form.val("formBuilding", {
                "roomId": data.roomId
                ,"name": data.name
                ,"address": data.address
                ,"description": data.description
            });
            var facility = data.facilities.split(",");
            $.each($('input[type=checkbox][name=dicItermIds]'),function(){
                for(j = 0, len=facility.length; j < len; j++) {
                    if(facility[j] == $(this).val()){
                        $(this).attr("checked",true);
                        $(this).next().addClass("layui-form-checked");
                    }
                }
            });
            var extra = data.extraFee.split(",");
            $.each($('input[type=checkbox][name=extraIds]'),function(){
                for(j = 0, len=extra.length; j < len; j++) {
                    if(extra[j] == $(this).val()){
                        $(this).attr("checked",true);
                        $(this).next().addClass("layui-form-checked");
                    }
                }
            });
            active.roomEdit();
        }
    });

    var active = {
        reload: function () {
            var demoReload = $('#demoReload');
            //执行重载
            table.reload('lay_table_room', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    key: {
                        id: demoReload.val()
                        ,buildingId: 1
                    }
                }//传参*/
            });
        },
        roomEdit: function () {
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "编辑楼盘"
                , area: '800px'
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

    //绑定click点击事件
    $('.demoTable .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

});