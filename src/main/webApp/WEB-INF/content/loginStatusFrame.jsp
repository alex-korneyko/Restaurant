<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 26.10.2016
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>loginFrame</title>

    <script type="text/javascript" src="<c:url value="/resources/js/restaurant_script.js" /> "></script>
</head>
<body>
<c:if test="${userLogin == null}">
    <div class="container">
        <a href="#" onclick="showInFrameParentWindow('loginPage', '')">Вход</a>
    </div>
    <div class="container">
        <a href="#" onclick="showInFrameParentWindow('registrationPage', '')">Регистрация</a>
    </div>
</c:if>
<c:if test="${userLogin != null}">
    <div class="container">
        <c:out value="Привет ${userLogin}"/>
    </div>
    <div class="container">
        <a href="#" onclick="showInFrameParentWindow('/user/userProfile?login=${userLogin}', '')">Профиль </a>
        <%--<a href="${pageContext.request.contextPath}/user/logout" onclick="showInFrameParentWindow('loginPage', '')">Выход </a>--%>
        <a href="${pageContext.request.contextPath}/user/logout" onclick="reloadMainPage()">Выход </a>
    </div>
</c:if>
</body>
</html>
