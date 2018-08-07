layui.use(['layer', 'form'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var form = layui.form;

    //监听提交
    form.on('submit(roomEditFormSubmitFilter)', function(data){
        var params = {};
        var fromParams = $(data.form).serializeObject();
        fromParams.rentFee = fromParams.rentFee * 100;
        fromParams.area = fromParams.area * 10000;
        params.body = fromParams;
        console.log(params);
        var jhxhr = $.ajax({url: requestBaseUrl + "/room/edit", data: JSON.stringify(params), headers: header, contentType: 'application/json', type: "POST"});
        jhxhr.done(function (res) {
            if(res.code == 1){
                layer.close(1);
                if(params.roomId){
                    layer.msg("修改成功");

                }else{
                    layer.msg("添加成功");
                }
                var buildingId = $("select[name=buildingId]").val();
                location.href= requestBaseUrl + "/room/goRoom/" + userId + "/" + buildingId + "?tokenId=" + tokenId;
            }else{
                layer.alert(res.msg)
            }
        });

        return false;//阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

});