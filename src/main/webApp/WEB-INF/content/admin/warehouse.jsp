<%@ page contentType="text/html;charset=utf-8" language="java" %>
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

    <title>posts</title>
</head>
<body>
<h3>Склад</h3>
<form method="post" action="${pageContext.request.contextPath}/admin/warehouse">
    <div class="container">
        <div class="row">
            <button class="btn btn-success" name="create" disabled="disabled">Создать</button>
            <button class="btn btn-warning" name="edit" disabled="disabled">Изменить</button>
            <button class="btn btn-danger" name="delete" disabled="disabled">Удалить</button>
            <button class="btn btn-primary" name="find">Поиск</button>
            <button class="btn btn-info" name="showAll">Показать всё</button>
        </div>
    </div>
    <br>
    <div class="container">
        <table class="table table-bordered table-condensed table-hover">

            <tr>
                <th>Id</th>
                <th>Название</th>
                <th>Количество</th>
                <th>Единицы</th>
            </tr>

            <c:forEach items="${wholeWarehouse}" var="warehousePsition">
                <tr>
                    <td>${warehousePsition.id}</td>
                    <td>${warehousePsition.ingredient.ingredientName}</td>
                    <td>${warehousePsition.ingredientAmount}</td>
                    <td>${warehousePsition.ingredient.unit.unitName}</td>
                </tr>
            </c:forEach>

        </table>
    </div>
</form>

</body>
</html>
