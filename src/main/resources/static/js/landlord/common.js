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

    };

    //绑定click点击事件
    $('.scratMenu').on('click', function () {
        var othis = $(this), method = othis.data('method');
        scratMenu[method] ? scratMenu[method].call(this, othis) : '';
    });

});

