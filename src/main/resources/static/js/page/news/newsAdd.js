layui.config({
    base: '../../static/js/modules/'
}).use(['form','layer','layedit','laydate','upload','config','utils','ajax','common'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        laypage = layui.laypage,
        upload = layui.upload,
        layedit = layui.layedit,
        laydate = layui.laydate,
        $ = layui.jquery;
        var config = layui.config;
        var utils = layui.utils;
        var ajax = layui.ajax;
        var mj = layui.common;

    //用于同步编辑器内容到textarea
    layedit.sync(editIndex);

    //上传缩略图
    upload.render({
        elem: '.thumbBox',
        accept: 'images',
        acceptMime:  'image/*',
        url: config.RESTFUL_URL+"/upload",
        method : "post",
        done: function(res, index, upload){
            if(res && res.ActionStatus == "OK"){
                $('.thumbImg').attr('src',res.data+"?_t="+new Date().getTime());
                $("input[name='thumb']").val(res.data);
                $('.thumbBox').css("background","#fff");
            }
            //对上传失败的单个文件重新上传，一般在某个事件中使用
            //obj.upload(index, file);
        }
    });

    //格式化时间
    // function filterTime(val){
    //     if(val < 10){
    //         return "0" + val;
    //     }else{
    //         return val;
    //     }
    // }
    //定时发布
    // var time = new Date();
    // var submitTime = time.getFullYear()+'-'+filterTime(time.getMonth()+1)+'-'+filterTime(time.getDate())+' '+filterTime(time.getHours())+':'+filterTime(time.getMinutes())+':'+filterTime(time.getSeconds());
    laydate.render({
        elem: '#release',
        type: 'datetime',
        trigger : "click",
        done : function(value, date, endDate){

        }
    });
    //
    // form.on("radio(release)",function(data){
    //     if(data.elem.title == "定时发布"){
    //         $(".releaseDate").removeClass("layui-hide");
    //         $(".releaseDate #release").attr("lay-verify","required");
    //     }else{
    //         $(".releaseDate").addClass("layui-hide");
    //         $(".releaseDate #release").removeAttr("lay-verify");
    //         submitTime = time.getFullYear()+'-'+(time.getMonth()+1)+'-'+time.getDate()+' '+time.getHours()+':'+time.getMinutes()+':'+time.getSeconds();
    //     }
    // });

    form.verify({
        "abs-len" : function(val){
            if(val && val.length > 256){
                return "只能256字以内";
            }
        },
        content : function(val){
            if(val == ''){
                return "文章内容不能为空";
            }
        }
    })
    form.on("submit(addNews)",function(data){
        delete data.field.file;
        if(data.field.id){//编辑
            data.field.content = layedit.getContent(editIndex);
            ajax.sendPost({
                "url":config.RESTFUL_URL+"/page/news/update",
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
                "url":config.RESTFUL_URL+"/page/news/add",
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
        return false;
    });

    form.on('switch(top)', function(data){
        if(data.elem.checked){
            data.elem.value = new Date().getTime();
        }
    });

    //预览
    form.on("submit(look)",function(){
        layer.alert("此功能需要前台展示");
        return false;
    })

    //创建一个编辑器
    var editIndex = layedit.build('news_content',{
        height : 535,
        uploadImage : {
            url : config.RESTFUL_URL+"/upload/editor"
        }
    });

})