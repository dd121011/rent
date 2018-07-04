layui.use(['layer', 'table', 'form'], function () {
    var $ = layui.$;
    var layer = layui.layer;
    var table = layui.table;
    var form = layui.form;

    layer.msg('hello');

    var selectLoadFlag= true;

    //方法级渲染
    table.render({
        elem: '#lay_table_building'//指定原始表格元素选择器（
        , url: requestBaseUrl + '/building/list'//数据接口
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
        , id: 'lay_table_building'
        , page: true//开启分页
//            ,height: 315//容器高度
        , cols: [[//表头
            {checkbox: true, fixed: true}
            , {field: 'name', title: '楼盘', width: 200}
            , {field: 'rooms', title: '总的房间数', sort: true, width: 110}
            , {field: 'roomAble', title: '可用房间数', sort: true, width: 120}
            , {field: 'facilities', title: '配套设施', width: 100}
            , {field: 'extraFee', title: '额外收费项', width: 100}
            , {field: 'description', title: '描述', width: 80}
            , {field: 'address', title: '地址', width: 80}
            , {field: '', title: '操作', align: 'center', toolbar: '#buildingListBar'}
        ]]
        , done: function (res, curr, count) {
            if(selectLoadFlag){
                var selectData = res.data;
                $("#searchBuildingName").append("<option value='-1' selected>全部</option>");
                for(i = 0, len=selectData.length; i < len; i++) {
                    $("#searchBuildingName").append("<option value='"+selectData[i].buildingId+"'>"+selectData[i].name+"</option>");
                }
                form.render('select','buildingSearchFormFilter');
            }
        }
    });

    //监听表格复选框选择
    table.on('checkbox(buildingTableFilter)', function (obj) {
        console.log(obj);
        layer.alert("this is check all");
    });

    //监听工具条
    table.on('tool(buildingTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'detail') {
            location.href= requestBaseUrl + "/room/goRoom/" + data.buildingId + "?tokenId=" + tokenId;
        } else if (obj.event === 'del') {
            layer.confirm('真的删除行么', function (index) {
                var jhxhr = $.ajax({url: requestBaseUrl + "/building/delete", data:{"ids": data.buildingId}, headers: header, type: "POST"});
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
            form.val("buildingFormFilter", {
                "buildingId": data.buildingId
                ,"name": data.name
                ,"address": data.address
                ,"description": data.description
            });
            var facility = data.facilities.split(",");
            $.each($('input[type=checkbox][name=facilityIds]'),function(){
                for(j = 0, len=facility.length; j < len; j++) {
                    if(facility[j] == $(this).val()){
                        $(this).attr("checked",true);
                        $(this).next().addClass("layui-form-checked");
                        break;
                    }
                }
            });
            var extra = data.extraFee.split(",");
            $.each($('input[type=checkbox][name=extraIds]'),function(){
                for(j = 0, len=extra.length; j < len; j++) {
                    if(extra[j] == $(this).val()){
                        $(this).attr("checked",true);
                        $(this).next().addClass("layui-form-checked");
                        break;
                    }
                }
            });
            var deposit = data.deposits.split(",");
            $.each($('input[type=checkbox][name=depositIds]'),function(){
                for(j = 0, len=extra.length; j < len; j++) {
                    if(deposit[j] == $(this).val()){
                        $(this).attr("checked",true);
                        $(this).next().addClass("layui-form-checked");
                        break;
                    }
                }
            });
            active.edit();
        }
    });

    var active = {
        search: function () {
            var demoReload = $('#demoReload');
            //执行重载
            table.reload('lay_table_building', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    buildingId: $('#searchBuildingName').val()
                }//传参*/
            });
        },
        add: function (othis) {
            $("input[type!=checkbox]").val("");
            $("select").val("");
            $("[name='description']").val("");
            $.each($('input[type=checkbox]'),function(){
                $(this).attr("checked",false);
                $(this).next().removeClass("layui-form-checked");
            });
            // var type = othis.data('type');
            var type = "auto";
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "新增楼盘"
                , area: '800px'
                , offset: type //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerBuildingAdd' //防止重复弹出
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
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "编辑楼盘"
                , area: '800px'
                , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerBuildingEdit'  //防止重复弹出
                , content: $('#addDiv')
                , btn: '关闭全部'
                , btnAlign: 'c' //按钮居中
//                    ,shade: 0 //不显示遮罩
                , yes: function () {
                    layer.closeAll();
                }
            });
        },
    };

    //绑定click点击事件
    $('.childrenBody .layui-btn').on('click', function () {
        var othis = $(this), method = othis.data('method');
        // active.add();
        active[method] ? active[method].call(this, othis) : '';
    });

});