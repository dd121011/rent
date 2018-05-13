layui.config({
    base: '../../static/js/modules/'
}).use(['form','layer','config','utils','ajax','common'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    var config = layui.config;
    var utils = layui.utils;
    var ajax = layui.ajax;
    var mj = layui.common;

    form.on("submit(addUser)",function(data){
        if(data.field.password){
            data.field.password = utils.md5(data.field.password);
        }else{
            delete data.field.password;
        }
        if(data.field.id){//编辑
            ajax.sendPost({
                "url":config.RESTFUL_URL+"/page/user/update",
                "postData":data.field,
                "success":function (data) {
                    if(data){
                        top.layer.msg("修改成功！");
                        setTimeout(function(){
                            parent.location.reload();
                        },1000);
                    }
                }
            });
        }else{
            ajax.sendPost({
                "url":config.RESTFUL_URL+"/page/user/add",
                "postData":data.field,
                "success":function (data) {
                    if(data){
                        top.layer.msg("添加成功！");
                        setTimeout(function(){
                            parent.location.reload();
                        },1000);
                    }
                }
            });
        }
        // setTimeout(function(){
        //     top.layer.msg("用户添加成功！");
        //     layer.closeAll("iframe");
        //     //刷新父页面
        //     parent.location.reload();
        // },2000);
        return false;
    })

    $(".cancel_btn").on("click",function () {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });

    //格式化时间
    function filterTime(val){
        if(val < 10){
            return "0" + val;
        }else{
            return val;
        }
    }
    //定时发布
    var time = new Date();
    var submitTime = time.getFullYear()+'-'+filterTime(time.getMonth()+1)+'-'+filterTime(time.getDate())+' '+filterTime(time.getHours())+':'+filterTime(time.getMinutes())+':'+filterTime(time.getSeconds());

})