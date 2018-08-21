layui.use(['layer', 'form'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var form = layui.form;

    //监听提交
    form.on('submit(userEmailEditFormSubmitFilter)', function(data){
        var params = {};
        var fromParams = $(data.form).serializeObject();
        params.body = fromParams;
        console.log(params);
        var jhxhr = $.ajax({url: requestBaseUrl + "/user/updatePersonalInfo", data: JSON.stringify(params), headers: header, contentType: 'application/json', type: "POST"});
        jhxhr.done(function (res) {
            if(res.code == 1){
                layer.closeAll();
                layer.msg("添加成功");
                location.reload();
            }else{
                layer.alert(res.msg)
            }
        });
        return false;//阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

});