layui.use(['element', 'layer', 'table', 'form'], function () {
    var $ = layui.$;
    var element = layui.element;

    //绑定click点击事件
    $('#goBuilding').on('click', function () {
        window.location.href = requestBaseUrl +  "/building/goBuilding?tokenId=" + tokenId;
    });
});

