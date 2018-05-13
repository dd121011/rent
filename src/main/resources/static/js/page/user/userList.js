layui.config({
    base: '../../static/js/modules/'
}).use(['form','layer','table','laytpl','config','utils','ajax','common'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table;
        var config = layui.config;
        var utils = layui.utils;
        var ajax = layui.ajax;
        var mj = layui.common;

    //用户列表
    var tableIns = table.render({
        elem: '#userList',
        url : config.RESTFUL_URL+"/page/user/list",
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
        id : "userListTable",
        cols : [[
            // {type: "checkbox", fixed:"left", width:50},
            {field: 'mobile', title: '用户名', minWidth:100, align:"center"},
            {field: 'nickname', title: '昵称', minWidth:100, align:"center"},
            {field: 'email', title: '邮箱', minWidth:200, align:'center',templet:function(d){
                return '<a class="layui-blue" href="mailto:'+d.email+'">'+d.email+'</a>';
            }},
            {field: 'gender', title: '性别', align:'center',templet:function(d){
                return d.gender?(d.gender == 1 ? "男" : "女"):"";
            }},
            {field: 'company', title: '公司', align:'center'},
            {field: 'status', title: '状态',  align:'center',templet:'#statusTpl'},
            // {field: 'grade', title: '用户等级', align:'center',templet:function(d){
            //     if(d.userGrade == "0"){
            //         return "注册会员";
            //     }else if(d.userGrade == "1"){
            //         return "中级会员";
            //     }else if(d.userGrade == "2"){
            //         return "高级会员";
            //     }else if(d.userGrade == "3"){
            //         return "钻石会员";
            //     }else if(d.userGrade == "4"){
            //         return "超级会员";
            //     }
            // }},
            {field: 'createTime', title: '注册时间', align:'center',minWidth:150,sort: true},
            {field: 'status',title: '操作', minWidth:175, templet:'#userListBar',fixed:"right",align:"center"}
        ]]
    });
    
    function renderStatus(s) {
        if(s == "0"){
            return "待审核";
        }else if(s == "1"){
            return "正常";
        }else if(s == "2"){
            return "禁用";
        }else if(s == "3"){
            return "删除";
        }
    }

    table.on('sort', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        tableIns.reload({
            initSort: obj //记录初始排序，如果不设的话，将无法标记表头的排序状态。 layui 2.1.1 新增参数
            ,where: { //请求参数（注意：这里面的参数可任意定义，并非下面固定的格式）
                sort: obj.field //排序字段
                ,order: obj.type //排序方式
            }
        });
    });

    $(".search_btn").on("click",function(){
        var where = {};
        if($(".searchVal").val() != ''){
            where.mobile = $(".searchVal").val();
        }
        if($(".status select").val() != -1){
            where.status = $(".status select").val();
        }
        tableIns.reload({
            page: {
                curr: 1
            },
            where: where
        });
    });

    //添加/编辑用户
    function addUser(data){

        var index = layui.layer.open({
            title : data?"编辑用户":"添加用户",
            type : 2,
            content : "userAdd.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(data){
                    body.find(".password input").attr("placeholder","原密码不显示");
                    body.find(".password input").removeAttr("lay-verify");
                    utils.setData(body,data)
                    form.render();
                }
                // setTimeout(function(){
                //     layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                //         tips: 3
                //     });
                // },500)
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }

    $(".addNews_btn").click(function(){
        addUser();
    })

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('userListTable'),
            data = checkStatus.data,
            userId = [];
        if(data.length > 0) {
            for (var i in data) {
                userId.push(data[i].id);
            }
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
                // $.get("删除文章接口",{
                //     newsId : newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                tableIns.reload();
                layer.close(index);
                // })
            })
        }else{
            layer.msg("请选择需要删除的用户");
        }
    })

    //列表操作
    table.on('tool(userList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'edit'){ //编辑
            ajax.sendGet({
                "url":config.RESTFUL_URL+"/page/user/get",
                "urlData":{id:data.id},
                "success":function (data) {
                    if(data){
                        addUser(data);
                    }
                }
            });
        }else if(layEvent === 'usable'){ //启用禁用
            var _this = $(this),
                usableText = "",
                status = 1;
            if(_this.text()=="启用"){
                usableText = "是否确定启用此用户？";
            }else if(_this.text()=="禁用"){
                usableText = "是否确定禁用此用户？";
                status = 2;
            }
            layer.confirm(usableText,{
                icon: 3,
                title:'系统提示',
                cancel : function(index){
                    layer.close(index);
                }
            },function(index){
                ajax.sendPost({
                    "url":config.RESTFUL_URL+"/page/user/update",
                    "postData":{id:data.id,status:status},
                    "success":function (data) {
                        if(data){
                            tableIns.reload();
                            layer.close(index);
                        }
                    }
                });
            },function(index){
                layer.close(index);
            });
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此用户？',{icon:3, title:'提示信息'},function(index){
                ajax.sendPost({
                    "url":config.RESTFUL_URL+"/page/user/delete",
                    "postData":{id:data.id},
                    "success":function (data) {
                        if(data){
                            tableIns.reload();
                            layer.close(index);
                        }
                    }
                });
            });
        }
    });

})
