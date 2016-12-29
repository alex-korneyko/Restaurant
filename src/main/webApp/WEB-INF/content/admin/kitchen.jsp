<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="<c:url value="/resources/css/bootstrap.css" /> " rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" /> " rel="stylesheet">
    <link href="<c:url value="/resources/css/font-awesome.css" />" rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/restaurant_script.js" /> "></script>
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />"></script>

    <title>kitchen</title>
</head>
<body>
<h3>Кухня</h3>

<form method="post" action="${pageContext.request.contextPath}/admin/kitchen">
    <div class="container">
        <div class="row">
            <button class="btn btn-success" name="prepare">Готовить</button>
            <button class="btn btn-warning" name="prepared">Готово</button>
            <button class="btn btn-danger" name="backToQueue">Назад в очередь</button>
            <button class="btn btn-primary" name="find">Поиск</button>
            <button class="btn btn-info" name="showAll">Показать всё</button>
        </div>
    </div>
    <br>
    <div class="container">
        <c:if test="${errorMessage != null}">
            <div class="alert alert-danger alert-dismissable">
                <button type="button" class="close" data-dismiss="alert">
                    <i class="fa fa-times fa-fw"></i>
                </button>
                <h4>Ошибка</h4>
                <p>${errorMessage}</p>
                <p>
                    <button type="button" class="btn btn-danger" data-dismiss="alert">Ок</button>
                </p>
            </div>
        </c:if>

        <table class="table table-bordered table-condensed table-hover">
            <tr>
                <th>Id</th>
                <th><center>Принял</center></th>
                <th>Столик</th>
                <th>Статус</th>
                <th>Дата/время</th>
                <th>Клиент</th>
                <th title="Пометить"><i class="fa fa-check-square" aria-hidden="true"></i> </th>
                <th title="Действие"><i class="fa fa-bolt" aria-hidden="true"></i></th>
            </tr>
            <c:forEach items="${allOrders}" var="order">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/admin/orders?orderInfo=${order.id}">${order.id}</a></td>
                    <td><a href="${pageContext.request.contextPath}/admin/employees?employeeInfoWindow=${order.employee.user.userLogin}">
                            ${order.employee.firstName} ${order.employee.lastName}</a></td>
                    <td align="center">${order.desk}</td>
                    <td>${order.status.toString()}</td>
                    <td>${order.dateOfCreation}</td>
                    <td>${order.orderOwner.userName} ${order.orderOwner.userSurName}</td>
                    <td><input type="checkbox" name="selected${order.id}" value="${order.id}"></td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/kitchen?prepare=&selected${order.id}=${order.id}" title="Готовить">P</a>
                        <a href="${pageContext.request.contextPath}/admin/kitchen?prepared=&selected${order.id}=${order.id}" title="Готово">R</a>
                        <a href="${pageContext.request.contextPath}/admin/kitchen?backToQueue=&selected${order.id}=${order.id}" title="Назад в очередь">Q</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</form>

</body>
</html>
