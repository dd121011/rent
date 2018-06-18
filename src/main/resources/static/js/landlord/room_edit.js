layui.use(['layer', 'form'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var form = layui.form;

    //监听提交
    form.on('submit(formDemo)', function(data){
        var params = $(data.form).serializeObject();
        params.rentFee = params.rentFee * 100;
        params.area = params.area * 10000;
        var jhxhr = $.ajax({url: requestBaseUrl + "/room/edit", data: params, headers: header, type: "POST"});
        jhxhr.done(function (res) {
            if(res.code == 1){
                layer.close(1);
                var buildingId = $("select[name=buildingId]").val();
                location.href= requestBaseUrl + "/room/goRoom/" + buildingId + "?tokenId=" + tokenId;
            }else{
                layer.alert(res.msg)
            }
        });

        return false;//阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

});