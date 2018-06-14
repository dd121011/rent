layui.use(['element', 'layer', 'table'], function () {
    var $ = layui.$;
    // var $ = layui.jquery;
    var element = layui.element;
    var layer = layui.layer;
    var table = layui.table;

    layer.msg('hello');

    //方法级渲染
    table.render({
        elem: '#lay_table_building'//指定原始表格元素选择器（
        , url: '/rent/building/list'//数据接口
        , method: 'post'
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
        , id: 'lay_table_building'
        , page: true//开启分页
//            ,height: 315//容器高度
        , cols: [[//表头
            {checkbox: true, fixed: true}
            , {field: 'buildingId', title: 'ID'}
            , {field: 'name', title: '楼盘', width: 200}
            , {field: 'rooms', title: '总的房间数', sort: true, width: 110}
            , {field: 'roomAble', title: '可用房间数', sort: true, width: 120}
            , {field: 'facilities', title: '配套设施', width: 100}
            , {field: 'extraFee', title: '额外收费项', width: 100}
            , {field: 'desc', title: '描述', width: 80}
            , {field: 'address', title: '地址', width: 80}
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
            $("[data-field='buildingId']").css('display', 'none');//隐藏不需要显示的id字段
        }
    });

    //监听表格复选框选择
    table.on('checkbox(building)', function (obj) {
        console.log(obj);
        layer.alert("this is check all");
    });

    //监听工具条
    table.on('tool(building)', function (obj) {
        var data = obj.data;
        if (obj.event === 'detail') {
            layer.msg('ID：' + data.buildingId + ' 的查看操作');
        } else if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {

                var jhxhr = $.ajax({url: requestBaseUrl + "/building/delete", data:{"ids": data.buildingId}, headers: header, type: "POST"});
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
            layer.alert('编辑行：<br>' + JSON.stringify(data))
        }
    });

    var active = {
        reload: function () {
            var demoReload = $('#demoReload');
            //执行重载
            table.reload('lay_table_building', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    key: {
                        id: demoReload.val()
                    }
                }//传参*/
            });
        }
    };

    //绑定click点击事件
    $('.demoTable .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

});