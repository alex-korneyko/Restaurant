<%--
  Created by IntelliJ IDEA.
  Date: 25.10.2016
  Time: 16:58
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="<c:url value="/resources/css/bootstrap.css" /> " rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" /> " rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/restaurant_script.js" /> "></script>
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />"></script>

    <title>$Title$</title>
</head>
<body>
<h2>Регистрация нового пользователя</h2>
<p><c:out value="${regError}"/></p>
<form action="${pageContext.request.contextPath}/registrationResult" method="post" class="form-horizontal">
    <div class="form-group">
        <label for="userName" class="control-label col-sm-2">Имя:</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="userName" name="userName">
        </div>
    </div>
    <div class="form-group">
        <label for="userSurName" class="control-label col-sm-2">Фамилия:</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="userSurName" name="userSurName">
        </div>
    </div>
    <div class="form-group">
        <label for="userLogin" class="control-label col-sm-2">Логин:</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="userLogin" name="userLogin">
        </div>
    </div>
    <div class="form-group">
        <label for="userPass1" class="control-label col-sm-2">Пароль:</label>
        <div class="col-sm-4">
            <input type="password" class="form-control" id="userPass1" name="userPass1">
        </div>
    </div>
    <div class="form-group">
        <label for="userPass2" class="control-label col-sm-2">Повторите пароль:</label>
        <div class="col-sm-4">
            <input type="password" class="form-control" id="userPass2" name="userPass2">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">Регистрация</button>
        </div>
    </div>
</form>
</body>
</html>
