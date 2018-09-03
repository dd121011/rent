var chargeExtra;
layui.use(['layer', 'table', 'form', 'laytpl', 'laydate'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var table = layui.table;
    var form = layui.form;
    var laytpl = layui.laytpl;
    var laydate = layui.laydate;

    laydate.render({
        elem: '#searchMonth'
        ,type: 'month'
        ,format: 'yyyyMM'
    });


    form.on('select(rentMultiSearchFormSelectBuildingFilter)', function (data) {
        var jhxhr = $.ajax({url: requestBaseUrl + "/rent/extraType/" + data.value, headers: header, type: "GET"});
        jhxhr.done(function (res) {
            if(res.code == 1){
                $('#searchExtraCode').html('');
                $.each(res.data, function (index, val) {
                    var option = $('<option>').val(val.dicItermCode).text(val.value);
                    $('#searchExtraCode').append(option)
                });
                //重新渲染
                form.render('select', 'rentMultiSearchFormFilter');
                $('#searchExtraCode').get(0).selectedIndex = 0;
            }else{
                layer.alert(res.msg);
            }
        });
    });

    var active = {
        search: function () {
            if(isEmpty($('#searchExtraCode option:selected').val())){
                return layer.alert("请先选择一个输入类型!");
            }
            if(isEmpty($('#searchMonth').val())){
                return layer.alert("请输入一个抄表月份!");
            }
            var jhxhr = $.ajax({url: requestBaseUrl + "/rent/multi/" + $('#searchBuildingId option:selected').val() + "/" + $('#searchExtraCode option:selected').val() + "/" + $('#searchMonth').val(), headers: header, type: "GET"});
            jhxhr.done(function (res) {
                if(res.code == 1){
                    layer.alert(JSON.stringify(res.data));
                }else{
                    layer.alert(res.msg);
                }
            });
        },
    };

    //绑定搜索点击事件
    $('.childrenBody .layui-btn').on('click', function () {
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
    });
});