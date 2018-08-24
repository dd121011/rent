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
                layer.alert(res.msg)
            }
        });
    });

    var active = {
        search: function () {
            if(isEmpty($('#searchRoomId option:selected').val())){
                return layer.alert("请先选择一个房间!");
            }
            var jhxhr = $.ajax({url: requestBaseUrl + "/room/barginExtra/" + $('#searchRoomId option:selected').val(), headers: header, type: "GET"});
            jhxhr.done(function (res) {
                if(res.code == 1){
                    if(res.data.length < 1){
                        return layer.alert("该房间尚未出租,无法计算房租!");
                    }
                    chargeExtra = {};
                    chargeExtra.extraList = res.data;
                    chargeExtra.roomId = $('#searchRoomId option:selected').val();
                    $('#chargeRoomNo').val($('#searchRoomId option:selected').text());
                    $('#chargeBuilding').val($('#searchBuildingId option:selected').text());
                    var getTpl = rentChargeTemplete.innerHTML;
                    var view = document.getElementById('chargeView');
                    laytpl(getTpl).render(res.data, function(html){
                        view.innerHTML = html;
                    });
                    layer.open({
                        type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                        ,title: "录入房租"
                        , area: '500px'
                        , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                        , id: 'layerRentCharge'  //防止重复弹出
                        , content: $('#addChargeDiv')
                        , btn: '关闭全部'
                        , btnAlign: 'c' //按钮居中
//                    ,shade: 0 //不显示遮罩
                        , yes: function () {
                            layer.closeAll();
                        }
                    });
                }else{
                    layer.alert(res.msg)
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