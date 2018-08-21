layui.use(['layer', 'form'], function () {
    var $ = layui.jquery;
    var layer = layui.layer;
    var form = layui.form;

    var userInfo = {
        realCertification: function () {
            console.log("realCertification")
        },
        changePhone: function () {
            checkPwd(userInfo.openChangePhone);
        },
        openChangePhone: function () {
            form.val("userPhoneEditFormFilter", {
                "phone": ""
                ,"smsCode": ""
            });
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "修改手机号码"
                , area: '500px'
                , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layerUserPhoneEdit' //防止重复弹出
                , content: $('#userPhoneEditDiv')
                , btn: '关闭全部'
                , btnAlign: 'c' //按钮居中
//                    ,shade: 0 //不显示遮罩
                , yes: function () {
                    layer.closeAll();
                }
                , success: function(layero, index){
                    smsCodeGenerateClick($('#userPhoneEditNewPhoneInput'));
                    console.log(layero, index);
                }
            });
        },
        changePwd: function () {
            checkPwd(userInfo.openChangePwd);
        },
        openChangePwd: function () {
            form.val("userPwdEditFormFilter", {
                "pwd": ""
                ,"rpwd": ""
            });
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "修改密码"
                , area: '500px'
                , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layeruserPwdEdit' //防止重复弹出
                , content: $('#userPwdEditDiv')
                , btn: '关闭全部'
                , btnAlign: 'c' //按钮居中
//                    ,shade: 0 //不显示遮罩
                , yes: function () {
                    layer.closeAll();
                }
            });
        },
        changeEmail: function () {
            checkPwd(userInfo.openChangeEmail);
        },
        openChangeEmail: function () {
            form.val("userEmailEditFormFilter", {
                "email": ""
            });
            layer.open({
                type: 1//0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,title: "修改邮箱"
                , area: '500px'
                , offset: 'auto' //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                , id: 'layeruserEmailEdit' //防止重复弹出
                , content: $('#userEmailEditDiv')
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
        var jhxhr = $.ajax({url: requestBaseUrl + "/user/updatePersonalInfo", data: JSON.stringify(params), headers: header, contentType: 'application/json', type: "POST"});
        jhxhr.done(function (res) {
            if(res.code == 1){
                layer.close(1);
                layer.msg("修改成功");
            }else{
                layer.alert(res.msg);
            }
        });

        return false;//阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

});