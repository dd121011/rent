layui.define(['layer','config','utils'], function(exports){
    'use strict';
    var $ = layui.$;
    var layer = parent.layer === undefined ? layui.layer : top.layer;
    var config = layui.config;
    var utils = layui.utils;

    var loading;

    var ajax = {
        "ajaxLoader": function (options) {
            var defaults = {
                type:"GET",
                url:'',
                postData:'',
                urlData:'',
                success:'',
                beforeSend:null,
                showLoading:true,
                error:null,
                complete:null,
                dataFilter:null,
                dataType:null,
                processData:true,
                contentType:'application/x-www-form-urlencoded;charset=UTF-8',
                timeout:60000
            };

            var configs = $.extend({},defaults,options);
            var type = configs.type;
            var urlData = configs.urlData;
            var param = getCommonParam();
            if(typeof(urlData) == "object" && !$.isEmptyObject(urlData)){
                for(var k in urlData){
                    param[k] = urlData[k];
                }
            }
            var params = configs.postData;
            if(params && params.length){
                for(var k in params){
                    if(typeof params[k] == "object"){
                        params[k] = JSON.stringify(params[k]);
                    }
                }
            }

            var url = configs.url;
            if(url && url.indexOf("?") > -1){
                var u = url.split("?");
                url = u[0];
                var urlParams = $.unParam(u[1]);
                if(!$.isEmptyObject(urlParams)){
                    $.extend(param,urlParams);
                }
            }
            var success = configs.success;
            var beforeSend = configs.beforeSend;
            var showLoading = configs.showLoading;
            var error = configs.error;
            var complete = configs.complete;
            var dataFilter = configs.dataFilter;
            var dataType = configs.dataType;
            var processData = configs.processData;
            var contentType = configs.contentType;
            var timeout = configs.timeout;

            $.ajax({
                type: type,
                data:params,
                beforeSend:function (XHR) {
                    beforeSend?beforeSend(XHR):'';
                    showLoading?showOrHideLoading(true):"";
                },
                ifModified: true,
                dataType: dataType,
                crossDomain: true,
                statusCode: {
                    404: function() {
                        showAlert("请求地址不存在");
                    },
                    405: function() {
                        showAlert("请求方法出错");
                    },
                    504: function() {
                        showAlert("网关超时");
                    }
                },
                url: $.isEmptyObject(param)?url:url+"?"+$.param(param),
                cache: false,
                timeout:timeout,
                processData : processData,
                contentType : contentType,
                dataFilter :function(data, type){
                    if(dataFilter){
                        return dataFilter(data, type);
                    }else{
                        data = dealJsonCallback(data);
                        return  JSON.stringify(data);
                    }
                },
                success : function(data,state,XHR){
                    var sendData = window.decodeURIComponent(this.data);
                    var jsonSendData = {};
                    if(sendData){
                        $.extend(jsonSendData,utils.unParam(sendData));
                    }
                    sendData = "url="+this.url;
                    if(sendData.indexOf('?') > -1 ){
                        var pre = sendData.substring(0,sendData.indexOf('?'));
                        sendData = sendData.substring(sendData.indexOf('?') + 1);
                        $.extend(jsonSendData,utils.unParam(pre),utils.unParam(sendData));
                    }
                    success?success(data,state,XHR,jsonSendData):'';
                },
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    if(error){
                        error(XMLHttpRequest,textStatus);
                    }else{
                        if(textStatus == "timeout"){
                            showAlert("与服务器连接超时，请稍后重试!");
                        }else if(textStatus == "parsererror"){
                            showAlert("数据解析错误!");
                        }else if(textStatus == "error"){
                            showAlert("网络错误，请重试!");
                        }
                    }
                },
                complete:function(XMLHttpRequest, textStatus){
                    showLoading?showOrHideLoading(false):"";
                    complete?complete(XMLHttpRequest):"";
                }
            });
        },
        "sendPost": function (options) {
            options.type = "POST";
            this.ajaxLoader(options)
        },
        "sendGet": function (options) {
            this.ajaxLoader(options)
        }
    };

    function showOrHideLoading(flag) {
        if(flag){
            loading = layer.msg('努力加载中......',{icon: 16,time:false,shade:0.8});
        }else{
            layer.close(loading);
        }
    }

    function dealJsonCallback(data){
        if(data){
            if(typeof(data) == "string"){
                data = JSON.parse(data);
            }
            var code = data.ActionStatus;
            if(code == "OK"){
                data = data.data?data.data:true;
            }else{
                showAlert(data.ErrorInfo);
                data = false;
            }
            return data;
        }
    }

    function getCommonParam() {
        var user =  window.localStorage.getItem(config.PREFIX+"user");
        user = user?JSON.parse(user):"";
        return {
            // userId:user?user.id:"",
            // token:user?user.token:"",
        };
    }

    function showAlert(content) {
        layer.open({title:"提示",content:content,icon: 5,btnAlign: 'c',time:0});
    }

    exports('ajax', ajax);

});
