<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script src="layer/layer.js"></script>
    <script type="text/javascript">

        $(function () {
            $("#btn3").click(function () {
                //alert("ddd")
                var array = [5, 8, 12];
                //将JSON数组转换为JSON字符串
                var requestBody = JSON.stringify(array);
                $.ajax({
                    "url": "send/array/three.json",
                    "type": "post",
                    "data": requestBody,
                    "contentType": 'application/json;charset=utf-8',//设置请求体的内容类型，告诉服务器端本次请求的请求体是JSON数据
                    "dataType": "json",//如何对待服务器返回的数据
                    "success": function (response) {
                        console.log(response);
                        //alert("成功"+ response);
                    },
                    "error": function (response) {
                        console.log(response);
                        alert("失败"+ response);
                    }
                });
            })
            $("#btn5").click(function () {
                layer.msg("layer的效果");
            });
        })

    </script>

</head>
<body>
<h2>测试环境页面</h2>
<a href="test/ssm.html">测试SSM环境整合</a><br>
<a href="admin/to/login/page.html">到登录界面-admin/to/login/page.html->admin-login.jsp</a><br>
<button id="btn3">Send [5,8,12] three</button><br>
<button id="btn5">点我弹框</button><br>
</body>
</html>
