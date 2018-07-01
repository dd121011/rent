layui.use(['layer', 'form', 'laydate', 'table'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;

    //日期
    laydate.render({
        elem: '#liveTs'
        ,done: function(value, date, endDate){
            console.log(value); //得到日期生成的值，如：2017-08-18
            console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
            console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
            var d2 = new Date(value);
            d2.setFullYear(d2.getFullYear()+1);
            d2.setDate(d2.getDate()-1);
            laydate.render({
                elem: '#leaveTs'
                , value: d2.getTime()
            });
        }
    });
    laydate.render({
        elem: '#leaveTs'
    });

    //监听提交
    form.on('submit(rentEditFormSubmitFilter)', function(data){
        var barginExtraList = [];
        var depositItermList = [];
        var guarantyFee = 0;
        var params = {};
        params.roomId = $('#roomId').val();
        var formParams = $(data.form).serializeObject();
        for(i=0, len=extraTableData.length; i< len; i++){
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

        formParams.rentFee = formParams.rentFee * 100;
        formParams.liveTs = (new Date(formParams.liveTs)).getTime();
        formParams.leaveTs = (new Date(formParams.leaveTs)).getTime();
        formParams.facilities = formParams.facilities.join(",");
        formParams.barginExtraList = barginExtraList;
        formParams.depositItermList = depositItermList;
        formParams.guarantyFee = guarantyFee;
        formParams.total = Number(formParams.rentFee) + Number(formParams.guarantyFee);
        params.body = formParams;

        var jhxhr = $.ajax({url: requestBaseUrl + "/room/rent", data: JSON.stringify(params), headers: header, contentType: 'application/json', type: "POST"});
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