layui.use(['layer', 'form', 'laydate'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var form = layui.form;
    var laydate = layui.laydate;

    laydate.render({
        elem: '#month'
        ,type: 'month'
        ,format: 'yyyyMM'
    });

    //监听提交
    form.on('submit(rentChargeFormSubmitFilter)', function(data){
        var params = {};
        var bodyParams = {};
        var barginExtraList = [];
        for(i=0, len=chargeExtra.extraList.length; i< len; i++){
            var barginExtra = {};
            barginExtra.barginExtraId = chargeExtra.extraList[i].barginExtraId;
            barginExtra.dicItermCode = chargeExtra.extraList[i].dicItermCode;
            barginExtra.count = Number($('#addChargeDiv .barginExtraId' + chargeExtra.extraList[i].barginExtraId).val());
            barginExtraList.push(barginExtra);
        }
        bodyParams.barginExtraList=barginExtraList;
        bodyParams.roomId = chargeExtra.roomId;
        bodyParams.barginId=chargeExtra.extraList[0].barginId;
        bodyParams.month=$('#addChargeDiv input[name=month]').val();
        bodyParams.remark=$('#addChargeDiv textarea[name=remark]').val();
        params.body = bodyParams;

        var jhxhr = $.ajax({url: requestBaseUrl + "/room/charge", data: JSON.stringify(params), headers: header, contentType: 'application/json', type: "POST"});
        jhxhr.done(function (res) {
            if(res.code == 1){
                layer.close(1);
                layer.msg("录入成功");
                // location.href= requestBaseUrl + "/rent/goRent/" + userId + "/" + params.roomId + "?tokenId=" + tokenId;
                location.reload();
            }else{
                layer.alert(res.msg)
            }
        });

        return false;//阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

});