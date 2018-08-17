var tokenId = $.cookie('rent_tokenId');
var userId = $.cookie('rent_userId');

var header = {}; //或者 var obj=new Object();
header["tokenId"] = tokenId;
header["userId"] = userId;

layui.use(['element', 'layer', 'table', 'form'], function () {
    var $ = layui.$;
    var element = layui.element;

    var scratMenu = {
        userLogout: function () {
            $.cookie("rent_tokenId", null, {path: '/rent'})
            $.cookie("rent_userId", null, {path: '/rent'})
            tokenId = null;
            window.location.href = requestBaseUrl +  "/";
        },
        dashboardManage: function () {
            window.location.href = requestBaseUrl +  "/landlord/goHome/" + userId + "?tokenId=" + tokenId;
        },
        buildingManage: function () {
            window.location.href = requestBaseUrl +  "/building/goBuilding/" + userId + "?tokenId=" + tokenId;
        },
        roomManage: function () {
            window.location.href = requestBaseUrl +  "/room/goRoom/" + userId + "?tokenId=" + tokenId;
        },
        rentManage: function () {
            window.location.href = requestBaseUrl +  "/rent/goRent/" + userId + "?tokenId=" + tokenId;
        },
        userDetail: function () {
            window.location.href = requestBaseUrl +  "/user/goUserDetail/" + userId + "?tokenId=" + tokenId;
        },

    };

    //绑定click点击事件
    $('.scratMenu').on('click', function () {
        var othis = $(this), method = othis.data('method');
        scratMenu[method] ? scratMenu[method].call(this, othis) : '';
    });

});

