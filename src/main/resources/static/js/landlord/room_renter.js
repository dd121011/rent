layui.use(['layer', 'form'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var form = layui.form;

    //监听提交
    form.on('submit(renterEditFormSubmitFilter)', function(data){
        var params = $(data.form).serializeObject();
        var jhxhr = $.ajax({url: requestBaseUrl + "/room/renterEdit/" + $('#roomId').val(), data: params, headers: header, type: "POST"});
        jhxhr.done(function (res) {
            if(res.code == 1){
                layer.close(1);
                if(params.roomRenterId){
                    layer.msg("修改成功");

                }else{
                    layer.msg("添加成功");
                }
                location.href= requestBaseUrl + "/room/goRoomDetail/" + $('#roomId').val() + "?tokenId=" + tokenId;
            }else{
                layer.alert(res.msg)
            }
        });
        return false;//阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

});