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
    form.on('submit(roomChargeFormSubmitFilter)', function(data){
        var params = {};
        var bodyParams = {};
        var barginExtraList = [];
        params.roomId = chageExtra.roomId;
        for(i=0, len=chageExtra.length; i< len; i++){
            var barginExtra = {};
            barginExtra.barginExtraId = chageExtra[i].barginExtraId;
            barginExtra.dicItermCode = chageExtra[i].dicItermCode;
            barginExtra.count = Number($('#addChargeDiv .barginExtraId' + chageExtra[i].barginExtraId).val());
            barginExtraList.push(barginExtra);
        }
        bodyParams.barginExtraList=barginExtraList;
        bodyParams.barginId=chageExtra[0].barginId;
        bodyParams.month=$('#addChargeDiv input[name=month]').val();
        bodyParams.remark=$('#addChargeDiv textarea[name=remark]').val();
        params.body = bodyParams;

        var jhxhr = $.ajax({url: requestBaseUrl + "/room/charge", data: JSON.stringify(params), headers: header, contentType: 'application/json', type: "POST"});
        jhxhr.done(function (res) {
            if(res.code == 1){
                layer.alert("录入成功");
                layer.close(1);
                // var buildingId = $("select[name=buildingId]").val();
                // location.href= requestBaseUrl + "/room/goRoom/" + buildingId + "?tokenId=" + tokenId;
            }else{
                layer.alert(res.msg)
            }
        });

        return false;//阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

});