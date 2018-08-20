layui.use(['layer', 'form'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var form = layui.form;

    var userInfo = {
        realCertification: function () {
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
        changePhone: function () {
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
        changePwd: function () {
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
        changeAvatar: function () {
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
    };

    //绑定click点击事件
    $('.childrenBody .layui-btn').on('click', function () {
        var othis = $(this), method = othis.data('method');
        userInfo[method] ? userInfo[method].call(this, othis) : '';
    });

    //监听提交
    form.on('submit(userPersonalInfoFormSubmitFilter)', function(data){
        var params = {};
        var fromParams = $(data.form).serializeObject();
        params.body = fromParams;
        console.log(params);
        var jhxhr = $.ajax({url: requestBaseUrl + "/user/personalInfo", data: JSON.stringify(params), headers: header, contentType: 'application/json', type: "POST"});
        jhxhr.done(function (res) {
            if(res.code == 1){
                layer.close(1);
                layer.msg("修改成功");
            }else{
                layer.alert(res.msg)
            }
        });

        return false;//阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

});