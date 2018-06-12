layui.use(['element', 'layer', 'table'], function () {
    var $ = layui.$;
    // var $ = layui.jquery;
    var element = layui.element;
    var layer = layui.layer;

    var active = {
        offset: function (othis) {
            var type = othis.data('type');
            layer.open({
                type: 1//页面层,type=2--iframe层
                , area: '500px'
                , offset: type //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerDemo' + type //防止重复弹出
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


    $('#layerDemo .layui-btn').on('click', function () {
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });

});