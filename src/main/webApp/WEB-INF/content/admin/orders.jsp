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
<h3>Заказы</h3>
<c:if test="${openNewOrderWindow == true}">
    <script type="text/javascript">
        $(window).load(function () {
            $('#orderModalWindow').modal('show');
        })
    </script>
</c:if>

<c:if test="${openDishesSelectWindow == true}">
    <script type="text/javascript">
        $(window).load(function () {
            $('#dishSelectModalWindow').modal('show');
        })
    </script>
</c:if>

<c:if test="${openDishesCountWindow == true}">
    <script type="text/javascript">
        $(window).load(function () {
            $('#dishesCountModalWindow').modal('show');
        })
    </script>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/admin/orders">
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
                <th>Принял</th>
                <th>Столик</th>
                <th>Статус</th>
                <th>Дата/время</th>
                <th>Клиент</th>
                <th>Выбор</th>
            </tr>
            <c:forEach items="${allOrders}" var="order">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/admin/orders?orderInfo=${order.id}">${order.id}</a></td>
                    <td><a href="${pageContext.request.contextPath}/admin/employees/?employeeInfoWindow=${order.employee.user.userLogin}">
                    ${order.employee.firstName} ${order.employee.lastName}</a></td>
                    <td>${order.desk}</td>
                    <td>${order.status.toString()}</td>
                    <td>${order.dateOfCreation}</td>
                    <td>${order.orderOwner.userName} ${order.orderOwner.userSurName}</td>
                    <td><input type="checkbox" name="selected${order.id}" value="${order.id}"></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</form>

<div class="modal fade" id="orderModalWindow">
    <div class="modal-dialog" style="width: 700px">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Ордер</h4>
            </div>
            <form action="${pageContext.request.contextPath}/admin/orders" method="post" class="form-horizontal">
                <div class="modal-body">

                    <div class="form-group">
                        <label for="dropDownComboBox" class="control-label col-sm-3">Клиент:</label>
                        <div class="col-sm-6">
                            <div class="dropdown">
                                <c:if test="${order.orderOwner != null}">
                                    <script type="text/javascript">
                                        $(function (){
                                            dropDownComboBoxScr('${order.orderOwner.userFullName}', '${order.orderOwner.userLogin}')
                                        });
                                    </script>
                                </c:if>
                                <input style="width: 320px" id="dropDownComboBox" type="button" data-toggle="dropdown"
                                       aria-haspopup="true" aria-expanded="false" class="btn btn-default"
                                <c:if test="${order.orderOwner == null}"> value="Выберите..." </c:if>
                                <c:if test="${order.orderOwner != null}"> value="${order.orderOwner.userFullName}" </c:if>
                                >
                                <ul class="dropdown-menu" aria-labelledby="usersDropDown">
                                    <c:forEach items="${allUsers}" var="user">
                                        <li><a href="#" onclick="dropDownComboBoxScr('${user.userFullName}', '${user.userLogin}')">${user.userFullName}</a> </li>
                                    </c:forEach>
                                </ul>
                                <input type="hidden" name="selectedUserId" id="dropDownComboBoxSelectedValue">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="desk" class="control-label col-sm-3">Столик:</label>
                        <div class="col-sm-2">
                            <input id="desk" type="text" class="form-control" name="desk" value="${order.desk}">
                        </div>
                        <label for="deskOrder" class="control-label col-sm-2">№ заказа:</label>
                        <div class="col-sm-2">
                            <input id="deskOrder" type="text" class="form-control" name="deskOrder" value="0">
                        </div>
                    </div>

                    <h4 class="col-sm-6 title">Список блюд</h4><br>

                    <table class="table table-bordered table-condensed table-hover">
                        <tr>
                            <th>Id</th>
                            <th>Блюдо</th>
                            <th>Цена</th>
                            <th>Количество</th>
                            <th>Сумма</th>
                            <th>Выбор</th>
                        </tr>
                        <c:forEach items="${order.dishes}" var="dish">
                            <tr>
                                <td>${dish.id}</td>
                                <td>${dish.dishName}</td>
                                <td>${dish.price}</td>
                                <td>${order.dishesCount.get(dish)}</td>
                                <td>${dish.price * order.dishesCount.get(dish)}</td>
                                <td><input type="checkbox" name="selectedDish${dish.id}" value="${dish.id}"></td>
                            </tr>
                        </c:forEach>
                        <c:if test="${order.dishes.size() > 0}">
                            <tr>
                                <td colspan="4">И того</td>
                                <td colspan="2">${order.orderCost}</td>
                            </tr>
                        </c:if>
                    </table>

                    <div class="container">
                        <div class="col-sm-8 col-sm-offset-2">
                            <div class="form-group">
                                <input type="submit" class="btn btn-default" name="addDish" value="Добавить">
                                <input type="submit" class="btn btn-default" name="editDish" value="Изменить">
                                <input type="submit" class="btn btn-default" name="removeDish" value="Удалить">
                                <input type="submit" class="btn btn-default" name="clearAllDish" value="Очистить">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" type="submit" name="saveOrderForm" <c:if test="${hideOk == true}">disabled="disabled"</c:if> >Ok</button>
                    <button class="btn btn-danger" type="button" data-dismiss="modal">Cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="dishSelectModalWindow">
    <div class="modal-dialog" style="margin-top: 50px">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Блюда</h4>
            </div>

            <div class="modal-body">
                <table class="table table-bordered table-condensed table-hover">
                    <tr>
                        <th>Id</th>
                        <th>Название</th>
                        <th>Категория</th>
                        <th>Цена</th>
                        <th>Вес</th>
                    </tr>
                    <c:forEach items="${allDishes}" var="dish">
                        <tr>
                            <td>${dish.id}</td>
                            <td><a href="${pageContext.request.contextPath}/admin/orders?clickDish=${dish.id}">${dish.dishName}</a></td>
                            <td>${dish.dishCategory.toString()}</td>
                            <td>${dish.price}</td>
                            <td>${dish.weight}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <div class="modal-footer">
                <button class="btn btn-danger" type="button" data-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="dishesCountModalWindow">
    <div class="modal-dialog" style="margin-top: 50px">
         <div class="modal-content">
             <div class="modal-header">
                 <button class="close" type="button" data-dismiss="modal">&times;</button>
                 <h4 class="modal-title">${paramsForDish.dishName}</h4>
             </div>
             <form action="${pageContext.request.contextPath}/admin/orders" method="post" class="form-horizontal">
                 <div class="modal-body">
                     <div class="form-group">
                         <label for="dishCount" class="control-label col-sm-3">Количество:</label>
                         <div class="col-sm-2">
                             <input id="dishCount" type="text" class="form-control" name="dishCount" value="${dishCount}">
                         </div>
                     </div>
                 </div>
                 <div class="modal-footer">
                     <button class="btn btn-default" type="submit" name="paramsEntered">Ok</button>
                     <button class="btn btn-danger" type="button" data-dismiss="modal">Cancel</button>
                     <input type="hidden" name="selectedDishForOrder" value="${paramsForDish.id}">
                 </div>
             </form>
         </div>
    </div>
</div>

</body>
</html>