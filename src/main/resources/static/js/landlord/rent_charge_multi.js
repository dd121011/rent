var chargeExtra;
layui.use(['layer', 'table', 'form', 'laytpl', 'laydate', 'flow'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var table = layui.table;
    var form = layui.form;
    var laytpl = layui.laytpl;
    var laydate = layui.laydate;
    var flow = layui.flow;

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
                layer.alert(res.message);
            }
        });
    });

    var active = {
        sflow: function () {
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
                    active.sflow();
                }else{
                    layer.alert(res.message);
                }
            });
        },
        search: function () {
            if(isEmpty($('#searchExtraCode option:selected').val())){
                return layer.alert("请先选择一个输入类型!");
            }
            if(isEmpty($('#searchMonth').val())){
                return layer.alert("请输入一个抄表月份!");
            }
            flow.load({
                elem: '#LAY_demo1' //流加载容器
                ,done: function(page, next){ //执行下一页的回调
                    var lis = [];
                    //以jQuery的Ajax请求为例，请求下一页数据（注意：page是从2开始返回）
                    var jhxhr = $.ajax({url: requestBaseUrl + "/rent/multi/" + $('#searchBuildingId option:selected').val() + "/" + $('#searchExtraCode option:selected').val() + "/" + $('#searchMonth').val() + '?page=' + page, headers: header, type: "GET"});
                    jhxhr.done(function (res) {
                        if(res.code == 1){
                            //假设你的列表返回在data集合中
                            layui.each(res.data, function(index, item){
                                lis.push('<li>'+ item.beforeCount +'</li>');
                            });

                            //执行下一页渲染，第二参数为：满足“加载更多”的条件，即后面仍有分页
                            //pages为Ajax返回的总页数，只有当前页小于总页数的情况下，才会继续出现加载更多
                            // next(lis.join(''), page < res.pages);
                            next(lis.join(''), page < 5);
                        }else{
                            layer.alert(res.message);
                        }
                    });
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