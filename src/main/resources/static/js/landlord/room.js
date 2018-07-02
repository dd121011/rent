var chageExtra;
layui.use(['layer', 'table', 'form', 'laytpl'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var table = layui.table;
    var form = layui.form;
    var laytpl = layui.laytpl;

    //方法级渲染
    table.render({
        elem: '#lay_table_room'//指定原始表格元素选择器（
        , url: requestBaseUrl + '/room/list/' + $('#searchBuildingId').val()//数据接口
        , method: 'post'
        , headers: header
        , request: {
            pageName: 'page' //页码的参数名称，默认：page
            , limitName: 'rows' //每页数据量的参数名，默认：limit
        } //如果无需自定义请求参数，可不加该参数
        , response: {
            statusName: 'code' //数据状态的字段名称，默认：code
            , statusCode: 1 //成功的状态码，默认：0
            , msgName: 'msg' //状态信息的字段名称，默认：msg
            , countName: 'count' //数据总数的字段名称，默认：count
            , dataName: 'data' //数据列表的字段名称，默认：data
        } //如果无需自定义数据响应名称，可不加该参数
        , id: 'lay_table_room'
        , page: true//开启分页
//            ,height: 315//容器高度
        , cols: [[//表头
            {checkbox: true, fixed: true}
            , {field: 'roomNo', title: '房间号', sort: true, width: 100}
            , {field: 'roomStyle', title: '房型', sort: true, width: 100, templet: function(d){
                    return d.bedroom + '房' + d.living + '厅' + d.toilet + '卫';
                }}
            , {field: 'orientationName', title: '房间朝向', sort: true, width: 100}
            , {field: 'guarantyAndPay', title: '押付情况', width: 100, templet: function(d){
                    return '押' + d.guaranty + '付' + d.pay;
                }}
            , {field: 'rentFee', title: '租金[元/月]', sort: true, width: 150, templet: function(d){
                    return d.rentFee/100;
                }}
            , {field: 'description', title: '描述', width: 100}
            , {field: 'rentTs', title: '是否出租', width: 100, templet: function(d){
                    if(d.rentTs > 0){
                        return '<a class="layui-btn layui-btn-xs">已出租</a>';
                    }
                    return '<a class="layui-btn layui-btn-danger layui-btn-xs">未出租</a>'
                }}
            , {field: '', title: '操作', align: 'center', toolbar: '#roomListBar'}
        ]]
        , done: function (res, curr, count) {
            //如果是异步请求数据方式，res即为你接口返回的信息。
            //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
            console.log(res);
            //得到当前页码
            console.log(curr);
            //得到数据总量
            console.log(count);
        }
    });

    //监听表格复选框选择
    table.on('checkbox(roomTableFilter)', function (obj) {
        console.log(obj);
        layer.alert("this is check all");
    });

    //监听工具条
    table.on('tool(roomTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {

                var jhxhr = $.ajax({url: requestBaseUrl + "/room/delete", data:{"ids": data.roomId}, headers: header, type: "POST"});
                jhxhr.done(function (res) {
                    if(res.code == 1){
                        obj.del();
                    }else{
                        layer.alert(res.msg)
                    }
                });
                layer.close(index);
            });
        } else if (obj.event === 'edit') {
            form.val("roomEditFormFilter", {
                "buildingId": data.buildingId
                ,"roomId": data.roomId
                ,"roomNo": data.roomNo
                ,"orientation": data.orientation
                ,"decoration": data.decoration
                ,"bedroom": data.bedroom
                ,"living": data.living
                ,"toilet": data.toilet
                ,"guaranty": data.guaranty
                ,"pay": data.pay
                ,"rentFee": data.rentFee/100
                ,"area": data.area/10000
                ,"description": data.description
            });
            var facility = data.facilities.split(",");
            $.each($('input[type=checkbox][name=facilityIds]'),function(){
                for(j = 0, len=facility.length; j < len; j++) {
                    if(facility[j] == $(this).val()){
                        $(this).attr("checked",true);
                        $(this).next().addClass("layui-form-checked");
                    }
                }
            });
            var extra = data.extraFee.split(",");
            $.each($('input[type=checkbox][name=extraIds]'),function(){
                for(j = 0, len=extra.length; j < len; j++) {
                    if(extra[j] == $(this).val()){
                        $(this).attr("checked",true);
                        $(this).next().addClass("layui-form-checked");
                    }
                }
            });
            var deposit = data.deposits.split(",");
            $.each($('input[type=checkbox][name=depositIds]'),function(){
                for(j = 0, len=extra.length; j < len; j++) {
                    if(deposit[j] == $(this).val()){
                        $(this).attr("checked",true);
                        $(this).next().addClass("layui-form-checked");
                    }
                }
            });
            active.edit();
        }else if (obj.event === 'rentHistoty') {
            //出租记录
            layer.msg('ID：' + data.roomId + ' 的查看操作');
        } else if (obj.event === 'rent'){
            // //出租
            // form.val("renterFormFilter", {
            //     "buildingId": data.buildingId
            //     ,"roomId": data.roomId
            //     ,"roomNo": data.roomNo
            //     ,"orientation": data.orientation
            //     ,"decoration": data.decoration
            //     ,"bedroom": data.bedroom
            //     ,"living": data.living
            //     ,"toilet": data.toilet
            //     ,"guaranty": data.guaranty
            //     ,"pay": data.pay
            //     ,"rentFee": data.rentFee
            //     ,"area": data.area
            //     ,"description": data.description
            // });
            // var facility = data.facilities.split(",");
            // $.each($('input[type=checkbox][name=dicItermIds]'),function(){
            //     for(j = 0, len=facility.length; j < len; j++) {
            //         if(facility[j] == $(this).val()){
            //             $(this).attr("checked",true);
            //             $(this).next().addClass("layui-form-checked");
            //         }
            //     }
            // });
            // var extra = data.extraFee.split(",");
            // $.each($('input[type=checkbox][name=extraIds]'),function(){
            //     for(j = 0, len=extra.length; j < len; j++) {
            //         if(extra[j] == $(this).val()){
            //             $(this).attr("checked",true);
            //             $(this).next().addClass("layui-form-checked");
            //         }
            //     }
            // });
            active.rent();
            // active.edit();
        } else if (obj.event === 'continue'){
            //续约
        } else if (obj.event === 'detail'){
            location.href= requestBaseUrl + "/room/goRoomDetail/" + data.roomId + "?tokenId=" + tokenId;
        } else if (obj.event === 'charge'){
            var jhxhr = $.ajax({url: requestBaseUrl + "/room/barginExtra/" + data.roomId, headers: header, type: "GET"});
            jhxhr.done(function (res) {
                if(res.code == 1){
                    chageExtra = res.data;
                    chageExtra.roomId = data.roomId;
                    var getTpl = roomChargeTemplete.innerHTML;
                    var view = document.getElementById('chargeView');
                    laytpl(getTpl).render(res.data, function(html){
                        view.innerHTML = html;
                    });
                    active.charge();
                }else{
                    layer.alert(res.msg)
                }
            });

        }
    });

    var active = {
        search: function () {
            //执行重载
            table.reload('lay_table_room', {
                url: requestBaseUrl + '/room/list/' + $('#searchBuildingId').val()//数据接口
                ,page: {
                    curr: 1 //重新从第 1 页开始
                }
                ,where: {
                    roomNo: $('#searchRoomNo').val(),
                    rentTs: $('#searchRoomRentTs').val()
                }//传参*/
            });
        },
        add: function () {
            // $("input[type!=checkbox][type!=select]").val("");
            // // $("select").val("");
            $("[name='description']").val("");
            $.each($('input[type=checkbox]'),function(){
                $(this).attr("checked",false);
                $(this).next().removeClass("layui-form-checked");
            });
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "新增房间"
                , area: '500px'
                , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerRoomAdd' //防止重复弹出
                , content: $('#addDiv')
                , btn: '关闭全部'
                , btnAlign: 'c' //按钮居中
//                    ,shade: 0 //不显示遮罩
                , yes: function () {
                    layer.closeAll();
                }
            });
        },
        edit: function () {
            $("input[name='roomNo']").attr("readonly","readonly");
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "编辑房间"
                , area: '500px'
                , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerRoomEdit'  //防止重复弹出
                , content: $('#addDiv')
                , btn: '关闭全部'
                , btnAlign: 'c' //按钮居中
//                    ,shade: 0 //不显示遮罩
                , yes: function () {
                    layer.closeAll();
                }
            });
        },
        rent: function () {
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "添加租客"
                , area: '500px'
                , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerRentEdit'  //防止重复弹出
                , content: $('#addRenterDiv')
                , btn: '关闭全部'
                , btnAlign: 'c' //按钮居中
                // , shade: 0 //不显示遮罩
                , yes: function () {
                    layer.closeAll();
                }
            });
        },
        charge: function () {
            $("input[name='roomNo']").attr("readonly","readonly");
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "录入房租"
                , area: '500px'
                , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerRoomCharge'  //防止重复弹出
                , content: $('#addChargeDiv')
                , btn: '关闭全部'
                , btnAlign: 'c' //按钮居中
//                    ,shade: 0 //不显示遮罩
                , yes: function () {
                    layer.closeAll();
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