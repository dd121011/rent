layui.use(['layer', 'form', 'laydate', 'table'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;

    var now = new Date();
    var nowEnd = new Date();
    nowEnd.setFullYear(nowEnd.getFullYear()+1);
    nowEnd.setDate(nowEnd.getDate()-1);
    //日期
    laydate.render({
        elem: '#liveTs'
        ,value: now
        ,isInitValue: true
        ,done: function(value, date, endDate){
            var leaveTs = new Date(value);
            leaveTs.setFullYear(leaveTs.getFullYear()+1);
            leaveTs.setDate(leaveTs.getDate()-1);
            laydate.render({
                elem: '#leaveTs'
                ,value: leaveTs
                ,isInitValue: true
            });
        }
    });

    laydate.render({
        elem: '#leaveTs'
        ,value: nowEnd
        ,isInitValue: true
    });

    //监听提交
    form.on('submit(rentEditFormSubmitFilter)', function(data){
        var barginExtraList = [];
        var depositItermList = [];
        var guarantyFee = 0;
        var params = {};
        var formParams = $(data.form).serializeObject();
        for(i=0, len=extraTableData.length; i< len; i++){
            if(isEmpty(extraTableData[i].price) || Number(extraTableData[i].price) < 0){
                layer.alert("额外收费项-" + extraTableData[i].value + "-的单价填写不正确, 请填写一个不小于0的数据!!!");
                return false;
            }
            var extra = {};
            extra.value = extraTableData[i].value;
            extra.unit = extraTableData[i].unit;
            extra.dicCode = extraTableData[i].dicCode;
            extra.dicItermCode = extraTableData[i].dicItermCode;
            extra.price = Number(extraTableData[i].price)*100;
            extra.number = undefined == extraTableData[i].number ? -1 : extraTableData[i].number;
            barginExtraList.push(extra);
        }
        for(i=0, len=depositItermTableData.length; i< len; i++){
            if(isEmpty(depositItermTableData[i].price) || Number(depositItermTableData[i].price) < 0){
                layer.alert("押金项项-" + depositItermTableData[i].value + "-的单价填写不正确, 请填写一个不小于0的数据!!!");
                return false;
            }
            var depositIterm = {};
            depositIterm.value = depositItermTableData[i].value;
            depositIterm.unit = depositItermTableData[i].unit;
            depositIterm.dicCode = depositItermTableData[i].dicCode;
            depositIterm.dicItermCode = depositItermTableData[i].dicItermCode;
            depositIterm.price = Number(depositItermTableData[i].price)*100;
            depositIterm.number = depositItermTableData[i].number;
            depositIterm.money = Number(depositItermTableData[i].money)*100/1;
            depositItermList.push(depositIterm);
            guarantyFee += depositIterm.money;
        }

        formParams.roomId = $('#roomDetailRoomId').val();
        formParams.rentFee = formParams.rentFee * 100;
        formParams.liveTs = (new Date(formParams.liveTs)).getTime();
        formParams.leaveTs = (new Date(formParams.leaveTs)).getTime();
        formParams.facilities = isEmptyArray(formParams.facilities) ? '' : formParams.facilities.join(",");
        formParams.barginExtraList = barginExtraList;
        formParams.depositItermList = depositItermList;
        formParams.guarantyFee = guarantyFee;
        formParams.total = Number(formParams.rentFee) + Number(formParams.guarantyFee);
        params.body = formParams;

        var jhxhr = $.ajax({url: requestBaseUrl + "/room/rent", data: JSON.stringify(params), headers: header, contentType: 'application/json', type: "POST"});
        jhxhr.done(function (res) {
            if(res.code == 1){
                layer.close(1);
                layer.msg("房间出租成功");
                // location.href= requestBaseUrl + "/room/goRoomDetail/" + $('#roomId').val() + "?tokenId=" + tokenId;
                location.reload();
            }else{
                layer.alert(res.message);
            }
        });
        return false;//阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

});