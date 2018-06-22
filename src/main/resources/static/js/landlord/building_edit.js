layui.use(['layer', 'form'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var form = layui.form;

    //监听提交
    form.on('submit(buildingEditFormSubmitFilter)', function(data){
        // var facility=[];
        // $.each($('input:checkbox[name=dicItermIds]:checked'),function(){
        //     facility.push($(this).val());
        // });
        // var extra=[];
        // $.each($('input[type=checkbox][name=extraIds]:checked'),function(){
        //     extra.push($(this).val());
        // });
        var params = $(data.form).serializeObject();
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

});