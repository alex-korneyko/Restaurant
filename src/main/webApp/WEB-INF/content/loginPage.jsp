<%--
  Created by IntelliJ IDEA.
  Date: 25.10.2016
  Time: 16:57
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="<c:url value="/resources/css/bootstrap.css" /> " rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" /> " rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/restaurant_script.js" /> "></script>
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />"></script>
    <title>Login page</title>
</head>
<body>
<c:url value="/loginPage" var="loginUrl"/>

<H2>Enter your login and password, please</H2>

<form name="form_login" method="post" action="${loginUrl}" class="form-horizontal">
    <c:if test="${param.error != null}">
        <p>
            Invalid username or password.
        </p>
    </c:if>

    <c:if test="${param.logout != null}">
        <p>
            You have been logged out.
        </p>
    </c:if>

    <div class="form-group">
        <label for="username" class="control-label col-sm-2">Логин</label>
        <div class="col-sm-4">
            <input id="username" type="text" name="username" class="form-control" value=''>
        </div>
    </div>
    <div class="form-group">
        <label for="password" class="control-label col-sm-2">Пароль</label>
        <div class="col-sm-4">
            <input id="password" type="password" name="password" class="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <div class="checkbox">
                <label>
                    <input name="rememberMe" type="checkbox" id="checkBoxRememberMe"> Запомнить
                </label>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-primary">Вход</button>
        </div>
    </div>
</form>

<a href="${pageContext.request.contextPath}/mainPage">На главную</a>
</body>
</html>
