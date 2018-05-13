var form, $,areaData;
layui.config({
    base: '../../static/js/modules/'
}).use(['form','layer','upload','laydate',"address",'config','utils','ajax','common'],function(){
    form = layui.form;
    $ = layui.jquery;
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        upload = layui.upload,
        laydate = layui.laydate,
        address = layui.address;
    var config = layui.config;
    var utils = layui.utils;
    var ajax = layui.ajax;
    var mj = layui.common;


    ajax.sendGet({
        "url":config.RESTFUL_URL+"/page/admin/get",
        "urlData":{id:mj.getUserId()},
        "success":function (data) {
            if(mj.haveData(data)){
                utils.setData($("form"),data);
                if(data.skill){
                    var inputs = $("form").find("input[name='skill[]']");
                    var skill = utils.fromJson(data.skill);
                    $.each(inputs,function(i,v){
                        for(var k in skill){
                            if($(v).val() == skill[k]) {
                                $(v).prop("checked","checked");
                            }
                        }
                    });
                }
                if(data.headImg){
                    $('#userFace').attr('src',data.headImg);
                }
            }
        }
    });


    //上传头像
    upload.render({
        elem: '.userFaceBtn',
        accept: 'images',
        acceptMime:  'image/*',
        url: config.RESTFUL_URL+"/upload",
        method : "post",
        done: function(res, index, upload){
            if(res && res.ActionStatus == "OK"){
                $('#userFace').attr('src',res.data);
                window.sessionStorage.setItem('userFace',res.data);
            }
        }
    });

    //添加验证规则
    form.verify({
        userBirthday : function(value){
            if(!/^(\d{4})[\u4e00-\u9fa5]|[-\/](\d{1}|0\d{1}|1[0-2])([\u4e00-\u9fa5]|[-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/.test(value)){
                return "出生日期格式不正确！";
            }
        }
    })
    //选择出生日期
    laydate.render({
        elem: '.userBirthday',
        format: 'yyyy-MM-dd',
        trigger: 'click',
        max : 0,
        mark : {"0-10-17":"生日"},
        done: function(value, date){
            if(date.month === 10 && date.date === 17){ //点击每年10月17日，弹出提示语
                layer.msg('今天是你的生日');
            }
        }
    });

    //获取省信息
    address.provinces();

    // form.on('checkbox', function(data){
    //     console.log(data.elem); //得到checkbox原始DOM对象
    //     console.log(data.elem.checked); //是否被选中，true或者false
    //     console.log(data.value); //复选框value值，也可以通过data.elem.value得到
    //     console.log(data.othis); //得到美化后的DOM对象
    // });

    //提交个人资料
    form.on("submit(changeUser)",function(data){
        delete data.field.file;

        var key,userInfo = {};
        var skill = [];
        for(k in data.field){
            var v = data.field[k];
            if(k.indexOf("skill[") == 0){
                skill.push(v);
            }else{
                userInfo[k] = v;
            }
        }
        if(!$.isEmptyObject(skill)){
            userInfo.skill = utils.toJson(skill);
        }
        var headImg = $('#userFace').attr('src');
        if(headImg){
            userInfo.headImg = headImg;
        }
        mj.setDataToStorage("userInfo",JSON.stringify(userInfo));
        ajax.sendPost({
            "url":config.RESTFUL_URL+"/page/admin/update",
            "postData":userInfo,
            "success":function (data) {
                if(data){
                    layer.msg("提交成功！");
                }
            }
        });

        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })

})