layui.use(['layer', 'form'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;

    var landlordHome = {
        search: function () {
            var jhxhr = $.ajax({url: requestBaseUrl + "/building/homeData/" + $('#searchBuildingId').val(), headers: header, type: "GET"});
            jhxhr.done(function (res) {
                if(res.code == 1){
                    $('#roomNum').html(res.data.roomNum);
                    $('#avaliableNum').html(res.data.avaliableNum);
                    $('#renterNum').html(res.data.renterNum);
                    $('#noRentNum').html(res.data.noRentNum);
                }else{
                    layer.alert(res.msg)
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