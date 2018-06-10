layui.use('element', function(){
    var element = layui.element;

});

layui.use('layer', function(){
    var $ = layui.$;
    var layer = layui.layer;

    //触发事件
    var active = {
        offset: function(othis){
            var type = othis.data('type');
            layer.open({
                type: 1//页面层,type=2--iframe层
                ,area: '500px'
                ,offset: type //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                ,id: 'layerDemo'+type //防止重复弹出
                ,content: $('#addDiv')
                ,btn: '关闭全部'
                ,btnAlign: 'c' //按钮居中
//                    ,shade: 0 //不显示遮罩
                ,yes: function(){
                    layer.closeAll();
                }
            });
        }
    };

    $('#layerDemo .layui-btn').on('click', function(){
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });

    layer.msg('hello');
});

layui.use('table', function(){
    var $ = layui.jquery;
    var table = layui.table;

    //方法级渲染
    table.render({
        elem: '#LAY_table_user'//指定原始表格元素选择器（
        ,url: '/rent/building/list'//数据接口
        ,headers: {tokenId: tokenId}
        ,request: {
            pageName: 'page' //页码的参数名称，默认：page
            ,limitName: 'rows' //每页数据量的参数名，默认：limit
        } //如果无需自定义请求参数，可不加该参数
        ,response: {
            statusName: 'code' //数据状态的字段名称，默认：code
            ,statusCode: 1 //成功的状态码，默认：0
            ,msgName: 'msg' //状态信息的字段名称，默认：msg
            ,countName: 'count' //数据总数的字段名称，默认：count
            ,dataName: 'data' //数据列表的字段名称，默认：data
        } //如果无需自定义数据响应名称，可不加该参数
        ,id: 'testReload'
        ,page: true//开启分页
//            ,height: 315//容器高度
        ,cols: [[//表头
            {checkbox: true, fixed: true}
            ,{field:'buildingId', title: '主键', width:80, sort: true, fixed: true}
            ,{field:'facilities', title: '配套设施', width:80}
            ,{field:'extraFee', title: '额外收费项', width:80, sort: true}
            ,{field:'rooms', title: '总的房间数', width:80}
            ,{field:'roomAble', title: '可用房间数', width:80}
            ,{field:'desc', title: '描述', sort: true, width:80}
            ,{field:'address', title: '地址', sort: true, width:80}
            ,{field:'createTs', title: '创建时间', width:80}
            ,{field:'updateTs', title: '更新时间', sort: true, width:135}
            ,{field:'', title: '操作', align:'center', toolbar: '#barDemo'}
        ]]
        ,done: function(res, curr, count){
            //如果是异步请求数据方式，res即为你接口返回的信息。
            //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
            console.log(res);

            //得到当前页码
            console.log(curr);

            //得到数据总量
            console.log(count);
        }
    });

    var active = {
        reload: function(){
            var demoReload = $('#demoReload');

            //执行重载
            table.reload('testReload', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    key: {
                        id: demoReload.val()
                    }
                }//传参*/
            });
        }
    };

    //绑定click点击事件
    $('.demoTable .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

});