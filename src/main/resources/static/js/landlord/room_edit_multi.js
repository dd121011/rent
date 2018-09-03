layui.use(['layer', 'form'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var form = layui.form;

    //监听提交
    form.on('submit(roomEditMultiSecondFormSubmitFilter)', function(data){
        var fromParams = $(data.form).serializeObject();
        roomAddMulti.body.roomNoMulity = fromParams.roomNo;
        console.log(roomAddMulti);
        var jhxhr = $.ajax({url: requestBaseUrl + "/room/addMulity", data: JSON.stringify(roomAddMulti), headers: header, contentType: 'application/json', type: "POST"});
        jhxhr.done(function (res) {
            if(res.code == 1){
                layer.close(1);
                layer.msg("添加成功");
                location.reload();
            }else{
                layer.alert(res.message);
            }
        });
        return false;//阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
});