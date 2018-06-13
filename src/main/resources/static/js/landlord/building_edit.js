layui.use(['element', 'layer', 'form'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var form = layui.form;

    //监听提交
    form.on('submit(formDemo)', function(data){
        var facility=[];
        $.each($('input:checkbox[name=dicItermIds]:checked'),function(){
            facility.push($(this).val());
        });
        var extra=[];
        $.each($('input[type=checkbox][name=extraIds]:checked'),function(){
            extra.push($(this).val());
        });
        console.log("baseURI=" + base.baseURI);
        console.log("base=" + requestBaseUrl);
        console.log("tokenId=" + tokenId);
        var ff = $(data.form).serialize();
        console.log(ff);
        console.log(JSON.stringify(ff));
        var jhxhr = $.ajax({url: requestBaseUrl + "/building/edit", data: ff, headers: header, type: "POST"});
        jhxhr.done(function (res) {
            if(res.code == 1){
                // location.href=base + "building/goBuilding?tokenId=" + tokenId;
                layer.close(1);
            }else{
                layer.alert(res.msg)
            }
        });

        return false;//阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });


    var active = {
        offset: function (othis) {
            var type = othis.data('type');
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "编辑楼盘"
                , area: '800px'
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