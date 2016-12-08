<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 25.10.2016
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login success page</title>
</head>
<body>
<h2>You are logged successful!</h2>
<script>
    top.location.reload();
//    window.parent.document.getElementById("loginFrame").setAttribute('src', 'loginStatusFrame');
</script>
<a href="${pageContext.request.contextPath}/mainPage">Main page</a>
</body>
</html>
