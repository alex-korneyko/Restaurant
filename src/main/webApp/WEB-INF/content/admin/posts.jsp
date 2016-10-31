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
<h3>Должности и права</h3>

<c:if test="${openEditWindow == true}">
        <script type="text/javascript">
            $(window).load(function () {
                $('#modal-1').modal('show');
            })
        </script>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/admin/posts">
    <div class="container">
        <div class="row">
            <button class="btn btn-success" name="create">Создать</button>
            <button class="btn btn-warning" name="edit">Изменить</button>
            <button class="btn btn-danger" name="delete">Удалить</button>
            <button class="btn btn-primary" name="find">Поиск</button>
            <button class="btn btn-info" name="showAll">Показать всё</button>
        </div>
    </div>
    <br>
    <div class="container">

        <c:if test="${wrongCount == true}">
            <div class="alert alert-danger alert-dismissable">
                <button type="button" class="close" data-dismiss="alert">
                    <i class="fa fa-times fa-fw"></i>
                </button>
                <h4>Ошибка</h4>
                <p>Нужно выбрать один элемент</p>
                <p><button type="button" class="btn btn-danger" data-dismiss="alert">Отмена</button></p>
            </div>
        </c:if>

        <table class="table table-bordered table-condensed table-hover">

            <tr>
                <th>Id</th>
                <th>Название</th>
                <th>Выбор</th>
            </tr>

            <c:forEach items="${posts}" var="post">
                <tr>
                    <td>${post.id}</td>
                    <td>${post.postName}</td>
                    <td><input type="checkbox" name="selected${post.id}" value="${post.id}"></td>
                </tr>
            </c:forEach>

        </table>
    </div>
</form>

<div class="modal fade" id="modal-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Должность</h4>
            </div>
            <form action="${pageContext.request.contextPath}/admin/posts" method="post">
                <div class="modal-body">
                    <input type="text" name="newPost" value="${postNameForEditing}">
                    <input type="hidden" name="postId" value="${postIdForEditing}">
                </div>
                <div class="modal-footer">
                    <button class="btn-danger" type="button" data-dismiss="modal">Cancel</button>
                    <button class="btn-default" type="submit">Ok</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>