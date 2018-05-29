<#include "common.ftl" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here </title>
</head>
<body>
请看说明：${descrip} <br />
${tdate?string('yyyy-MM-dd hh:mm:ss')}<br />
${tdate?datetime}<br />
${tdate?date}<br />
${tdate?time}<br />
<#--${tdate?locale}-->
end

</body>
</html>