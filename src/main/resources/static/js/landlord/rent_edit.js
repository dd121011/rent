layui.use(['layer', 'form', 'laydate'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var form = layui.form;
    var laydate = layui.laydate;

    //监听提交
    form.on('submit(roomChargeEditFormSubmitFilter)', function(data){
        var params = {};
        for(i=0, len=chargeExtra.extraList.length; i< len; i++){
            if(chargeExtra.extraList[i].number == -1){
                chargeExtra.extraList[i].count = Number($('#rentChargeEditDiv .barginExtraId' + chargeExtra.extraList[i].barginExtraId).val()) * 100;
            }else{
                chargeExtra.extraList[i].count = Number($('#rentChargeEditDiv .barginExtraId' + chargeExtra.extraList[i].barginExtraId).val());
            }

        }
        chargeExtra.remark = $('#rentChargeEditDiv textarea[name=remark]').val();
        params.body = chargeExtra;

        var jhxhr = $.ajax({url: requestBaseUrl + "/rent/edit/" + chargeExtra.rentId, data: JSON.stringify(params), headers: header, contentType: 'application/json', type: "POST"});
        jhxhr.done(function (res) {
            if(res.code == 1){
                layer.close(1);
                layer.msg("修改成功");
                // location.href= requestBaseUrl + "/rent/goRent/" + userId + "/" + chargeExtra.roomId + "?tokenId=" + tokenId;
                location.reload();
            }else{
                layer.alert(res.message);
            }
        });

        return false;//阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

});