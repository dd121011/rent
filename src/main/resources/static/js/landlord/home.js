layui.use(['layer', 'form'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;

    var landlordHome = {
        search: function () {
            var jhxhr = $.ajax({url: requestBaseUrl + "/landlord/homeData/" + $('#searchBuildingId').val(), headers: header, type: "GET"});
            jhxhr.done(function (res) {
                if(res.code == 1){
                    $('#rentTotal').html(res.data.income/100);
                    $('#rentExpired').html(res.data.expire/100);
                    $('#roomTotal').html(res.data.roomNum);
                    $('#roomAvaliable').html(res.data.avaliableNum);
                    $('#roomRenter').html(res.data.renterNum);
                    $('#roomRentExpire').html(res.data.noRentNum);
                    $('#renterExpired').html(res.data.noRentNum);
                }else{
                    layer.alert(res.message);
                }
            });
        },
    };

    //绑定click点击事件
    $('.childrenBody .layui-btn').on('click', function () {
        var othis = $(this), method = othis.data('method');
        landlordHome[method] ? landlordHome[method].call(this, othis) : '';
    });

    landlordHome.search();
    // // * 整合表单数据
    // var formData = data.field;
    // $.extend(formData, { Id: $("#hiddenId").val() });
    // console.info(formData);

});