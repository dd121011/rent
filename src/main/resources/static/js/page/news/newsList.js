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

    //新闻列表
    var tableIns = table.render({
        elem: '#newsList',
        url : config.RESTFUL_URL+"/page/news/list",
        cellMinWidth : 95,
        page : true,
        request: {
            pageName: 'pageNo'
            ,limitName: 'pageSize'
        },
        response: {
            statusName: 'ErrorCode' //数据状态的字段名称，默认：code
            ,statusCode: 0 //成功的状态码，默认：0
            ,msgName: 'ErrorInfo' //状态信息的字段名称，默认：msg
            ,countName: 'count' //数据总数的字段名称，默认：count
            ,dataName: 'data' //数据列表的字段名称，默认：data
        },
        height : "full-125",
        limits : [10,20,30],
        limit : 20,
        id : "newsListTable",
        cols : [[
            // {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: 'ID', width:60, align:"center"},
            {field: 'title', title: '标题', width:350},
            {field: 'publisher', title: '发布者', align:'center'},
            {field: 'status', title: '发布状态',  align:'center',templet:"#newsStatus"},
            {field: 'readNum', title: '阅读数',sort: true, align:'center'},
            {field: 'top', title: '是否置顶', align:'center',sort: true, templet:function(d){
                d.top = d.top > 0 ? "checked" : "";
                return '<input type="checkbox" newsId="'+d.id+'" name="top" lay-filter="newsTop" lay-skin="switch" lay-text="是|否" '+d.top+'>'
            }},
            {field: 'publishTime', title: '发布时间', align:'center',sort: true, minWidth:110},
            {field: 'createTime', title: '创建时间', align:'center',sort: true,minWidth:110,sort: true},
            {title: '操作', width:170, templet:'#newsListBar',fixed:"right",align:"center"}
        ]]
    });

    table.on('sort', function(obj){
        tableIns.reload({
            initSort: obj
            ,where: {
                sort: obj.field
                ,order: obj.type
            }
        });
    });

    //是否置顶
    form.on('switch(newsTop)', function(data){
        var top = 0;
        if(data.elem.checked){
            top = new Date().getTime()
        }
        var postData = {"id":$(data.elem).attr("newsId"),"top":top};
        update(postData,function (data) {
            if(data){
                if(top > 0){
                    layer.msg("置顶成功！");
                }else{
                    layer.msg("取消置顶成功！");
                }
            }
        });
    });

    $(".search_btn").on("click",function(){
        var where = {};
        if($(".searchVal").val() != ''){
            where.title = $(".searchVal").val();
        }
        if($(".news-status select").val() != -1){
            where.status = $(".news-status select").val();
        }
        tableIns.reload({
            page: {
                curr: 1
            },
            where: where
        });
    });

    //添加文章
    function addNews(data){
        var index = layui.layer.open({
            title : data?"编辑新闻":"添加新闻",
            type : 2,
            content : "newsAdd.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(data){
                    utils.setData(body,data);
                    body.find(".thumbImg").attr("src",data.thumb);
                    $("input[name='thumb']").val(data.thumb);
                    if(data.top > 0){
                        body.find("input[name='top']").prop("checked","checked");
                        body.find("input[name='top']").val(data.top);
                    }
                    form.render();
                }
                setTimeout(function(){
                    layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                },500)
            }
        })
        layui.layer.full(index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(index);
        })
    }
    $(".addNews_btn").click(function(){
        addNews();
    })

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('newsListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].newsId);
            }
            layer.confirm('确定删除选中的文章？', {icon: 3, title: '提示信息'}, function (index) {
                // $.get("删除文章接口",{
                //     newsId : newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                tableIns.reload();
                layer.close(index);
                // })
            })
        }else{
            layer.msg("请选择需要删除的文章");
        }
    })

    //列表操作
    table.on('tool(newsList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            ajax.sendGet({
                "url":config.RESTFUL_URL+"/page/news/get",
                "urlData":{id:data.id},
                "success":function (data) {
                    if(data){
                        addNews(data);
                    }
                }
            });
        } else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除？',{icon:3, title:'提示信息'},function(index){
                ajax.sendPost({
                    "url":config.RESTFUL_URL+"/page/news/delete",
                    "postData":{id:data.id},
                    "success":function (data) {
                        if(data){
                            tableIns.reload();
                            layer.close(index);
                        }
                    }
                });
            });
        } else if(layEvent === 'look'){ //预览
            layer.alert("此功能需要前台展示，实际开发中传入对应的必要参数进行文章内容页面访问")
        }
    });
    
    function update(data,callback) {
        ajax.sendPost({
            "url":config.RESTFUL_URL+"/page/news/update",
            "postData":data,
            "success":function (data) {
                callback(data)
            }
        })
    }

})