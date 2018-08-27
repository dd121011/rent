layui.use(['layer', 'form'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var form = layui.form;

    //监听提交
    form.on('submit(buildingEditFormSubmitFilter)', function(data){
        var params = {};
        var fromParams = $(data.form).serializeObject();
        params.body = fromParams;
        console.log(params);
        var jhxhr = $.ajax({url: requestBaseUrl + "/building/edit", data: JSON.stringify(params), headers: header, contentType: 'application/json', type: "POST"});
        jhxhr.done(function (res) {
            if(res.code == 1){
                layer.close(1);
                if(params.buildingId){
                    layer.msg("修改成功");

                }else{
                    layer.msg("添加成功");
                }
                // window.location.href = requestBaseUrl +  "/building/goBuilding/" + userId + "?tokenId=" + tokenId;
                location.reload();
            }else{
                layer.alert(res.msg || res.message);
            }
        });

        return false;//阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

});