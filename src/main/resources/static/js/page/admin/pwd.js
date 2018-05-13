layui.config({
    base: '../../static/js/modules/'
}).use(['form','layer','laydate','table','laytpl','config','utils','ajax','common'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laydate = layui.laydate,
        laytpl = layui.laytpl,
        table = layui.table;
    var config = layui.config;
    var utils = layui.utils;
    var ajax = layui.ajax;
    var mj = layui.common;

    //添加验证规则
    form.verify({
        newPwd : function(value, item){
            if(value.length < 6){
                return "密码长度不能小于6位";
            }
        },
        confirmPwd : function(value, item){
            if($("#newPwd").val() != value){
                return "两次输入密码不一致，请重新输入！";
            }
        }
    })

    //修改密码
    form.on("submit(changePwd)",function(data){
        var oldPwd = utils.md5(data.field.oldPwd);
        var newPwd = utils.md5(data.field.confirmPwd);
        ajax.sendPost({
            "url":config.RESTFUL_URL+"/page/admin/update/pwd",
            "postData":{id:mj.getUserId(),oldPassword:oldPwd,password:newPwd},
            "success":function (data) {
                if(data){
                    layer.msg("密码修改成功！");
                    $(".pwd").val('');
                }
            }
        });
        return false;
    });

});