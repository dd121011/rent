layui.use('element', function(){
    var element = layui.element;

});

layui.use('layer', function(){
    var $ = layui.$
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
        ,url: '/rent/testPage'//数据接口
        ,id: 'testReload'
        ,page: true//开启分页
//            ,height: 315//容器高度
        ,cols: [[//表头
            {checkbox: true, fixed: true}
            ,{field:'id', title: 'ID', width:80, sort: true, fixed: true}
            ,{field:'username', title: '用户名', width:80}
            ,{field:'sex', title: '性别', width:80, sort: true}
            ,{field:'city', title: '城市', width:80}
            ,{field:'sign', title: '签名', width:80}
            ,{field:'experience', title: '积分', sort: true, width:80}
            ,{field:'score', title: '评分', sort: true, width:80}
            ,{field:'classify', title: '职业', width:80}
            ,{field:'wealth', title: '财富', sort: true, width:135}
            ,{field:'', title: '操作', align:'center', toolbar: '#barDemo'}
        ]]
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
                }
            });
        }
    };

    $('.demoTable .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

});