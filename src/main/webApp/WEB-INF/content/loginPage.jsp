<%--
  Created by IntelliJ IDEA.
  Date: 25.10.2016
  Time: 16:57
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<c:url value="/loginPage" var="loginUrl"/>

<H2>Enter your login and password, please</H2>

<form name="form_login" method="post" action="${loginUrl}">
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

    <table>
        <tr>
            <td><label for="username">Username </label></td>
            <td><input id="username" type="text" name="username" value=''></td>
        </tr>
        <tr>
            <td><label for="password">Password </label></td>
            <td><input id="password" type="password" name="password"/></td>
        </tr>
    </table>
    <label for="checkBoxRememberMe">Remember me </label>
    <input name="rememberMe" type="checkbox" id="checkBoxRememberMe">
    <br>
    <button type="submit" class="button">Submit</button>
</form>
<br>
<a href="${pageContext.request.contextPath}/">Main</a>
</body>
</html>
