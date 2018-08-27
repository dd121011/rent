layui.use(['layer', 'form'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var form = layui.form;
    //监听提交
    form.on('submit(renterEditFormSubmitFilter)', function(data){
        var params = {};
        var fromParams = $(data.form).serializeObject();
        params.body = fromParams;
        console.log(params);
        var jhxhr = $.ajax({url: requestBaseUrl + "/room/renterAdd/" + $('#roomDetailRoomId').val(), data: JSON.stringify(params), headers: header, contentType: 'application/json', type: "POST"});
        jhxhr.done(function (res) {
            if(res.code == 1){
                layer.close(1);
                layer.msg("添加成功");
                // location.href= requestBaseUrl + "/room/goRoomDetail/" + renterRoomId + "?tokenId=" + tokenId;
                location.reload();
            }else{
                layer.alert(res.message);
            }
        });
        return false;//阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

});