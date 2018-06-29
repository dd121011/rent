layui.use(['layer', 'form', 'laydate', 'table'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;

    //日期
    laydate.render({
        elem: '#liveTs'
    });
    laydate.render({
        elem: '#leaveTs'
    });

    //监听提交
    form.on('submit(rentEditFormSubmitFilter)', function(data){
        var params = $(data.form).serializeObject();
        params.roomId = $('#roomId').val();
        params.rentFee = params.rentFee * 100;
        params.liveTs = (new Date(params.liveTs)).getTime();
        params.leaveTs = (new Date(params.leaveTs)).getTime();
        params.extras = JSON.stringify(extraTableData);
        params.depositIterms = JSON.stringify(depositItermTableData);
        var jhxhr = $.ajax({url: requestBaseUrl + "/room/rent", data: params, headers: header, type: "POST"});
        jhxhr.done(function (res) {
            if(res.code == 1){
                layer.close(1);
                location.href= requestBaseUrl + "/room/goRoomDetail/" + $('#roomId').val() + "?tokenId=" + tokenId;
            }else{
                layer.alert(res.msg)
            }
        });
        return false;//阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

});