layui.use(['layer', 'form'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var form = layui.form;

    //监听提交
    form.on('submit(formDemo)', function(data){
        // var facility=[];
        // $.each($('input:checkbox[name=dicItermIds]:checked'),function(){
        //     facility.push($(this).val());
        // });
        // var extra=[];
        // $.each($('input[type=checkbox][name=extraIds]:checked'),function(){
        //     extra.push($(this).val());
        // });
        var params = $(data.form).serialize();
        var jhxhr = $.ajax({url: requestBaseUrl + "/building/edit", data: params, headers: header, type: "POST"});
        jhxhr.done(function (res) {
            if(res.code == 1){
                layer.close(1);
                location.href= requestBaseUrl + "/building/goBuilding?tokenId=" + tokenId;
            }else{
                layer.alert(res.msg)
            }
        });

        return false;//阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });


    var active = {
        add: function (othis) {
            $("input[type!=checkbox]").val("");
            $("select").val("");
            $("[name='description']").val("");
            $.each($('input[type=checkbox]'),function(){
                $(this).attr("checked",false);
                $(this).next().removeClass("layui-form-checked");
            });
            var type = othis.data('type');
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "新增楼盘"
                , area: '800px'
                , offset: type //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerBuildingAdd' //防止重复弹出
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

    $('#layerBuilding .layui-btn').on('click', function () {
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });

});