layui.use(['layer', 'form'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var form = layui.form;

    //监听提交
    form.on('submit(userPwdEditFormSubmitFilter)', function(data){
        var params = {};
        var fromParams = $(data.form).serializeObject();
        if(fromParams.pwd != fromParams.rpwd){
            layer.alert('两次输入的密码不一样!!!');
            return false;
        }
        params.body = fromParams;
        console.log(params);
        var jhxhr = $.ajax({url: requestBaseUrl + "/user/updatePwd", data: JSON.stringify(params), headers: header, contentType: 'application/json', type: "POST"});
        jhxhr.done(function (res) {
            if(res.code == 1){
                layer.closeAll();
                layer.msg("修改成功");
            }else{
                layer.alert(res.message);
            }
        });
        return false;//阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

});