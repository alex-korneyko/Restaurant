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
<h3>Блюда</h3>
<c:if test="${openEditWindow == true}">
    <script type="text/javascript">
        $(window).load(function () {
            $('#dishModalWindow').modal('show');
        })
    </script>
</c:if>

<c:if test="${openIngredientsWindow == true}">
    <script type="text/javascript">
        $(window).load(function () {
            $('#ingredientsModalWindow').modal('show');
        })
    </script>
</c:if>

<c:if test="${openIngredientParamsWindow == true}">
    <script type="text/javascript">
        $(window).load(function () {
            $('#paramsModalWindow').modal('show');
        })
    </script>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/admin/dishes">
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
                <th>Название</th>
                <th>Категория</th>
                <th>Цена</th>
                <th>Вес</th>
                <th>Выбор</th>
            </tr>
            <c:forEach items="${dishes}" var="dish">
                <tr>
                    <td>${dish.id}</td>
                    <td>${dish.dishName}</td>
                    <td>${dish.dishCategory.toString()}</td>
                    <td>${dish.price}</td>
                    <td>${dish.weight}</td>
                    <td><input type="checkbox" name="selected${dish.id}" value="${dish.id}"></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</form>

<div class="modal fade" id="dishModalWindow">
    <div class="modal-dialog" style="width: 700px">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Блюдо</h4>
            </div>
            <form action="${pageContext.request.contextPath}/admin/dishes" method="post" class="form-horizontal">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="dishName" class="control-label col-sm-4">Название:</label>
                        <div class="col-sm-6">
                            <input id="dishName" type="text" class="form-control" name="dishName" value="${dish.dishName}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="dropDownComboBox" class="control-label col-sm-4">Категория:</label>
                        <div class="dropdown col-sm-6">
                            <c:if test="${dish.dishCategory != null}">
                                <script type="text/javascript">
                                    $(function (){
                                        dropDownComboBoxScript('${dish.dishCategory.toString()}')
                                    });
                                </script>
                            </c:if>
                            <input style="width: 300px" id="dropDownComboBox" type="button" data-toggle="dropdown"
                                   aria-haspopup="true" aria-expanded="false" class="btn btn-default"
                            <c:if test="${dish.dishCategory == null}"> value="Выберите..." </c:if>
                            <c:if test="${dish.dishCategory != null}"> value="${dish.dishCategory.toString()}" </c:if>
                            >
                            <ul class="dropdown-menu" aria-labelledby="newInvoiceDropDown">
                                <c:forEach items="${dishCategories}" var="category">
                                    <li><a href="#" onclick="dropDownComboBoxScript('${category.toString()}')">${category.toString()}</a> </li>
                                </c:forEach>
                            </ul>
                            <input type="hidden" name="selectedCategory" id="dropDownComboBoxSelectedValue" value="0">
                        </div>
                    </div>

                </div>

                <div class="form-group">
                    <label for="dishPrice" class="control-label col-sm-4">Цена:</label>
                    <div class="col-sm-4">
                        <input id="dishPrice" type="text" class="form-control" name="dishPrice" value="${dish.price}">
                    </div>
                    <div class="checkbox col-sm-2">
                        <label>
                            <input id="dishAutoPrice" type="checkbox" name="dishAutoPriceCheckBox"
                                   <c:if test="${dish.autoPrice == true}">checked="checked"</c:if>/>Авто
                        </label>
                    </div>
                </div>

                <div class="form-group">
                    <label for="dishWeight" class="control-label col-sm-4">Вес:</label>
                    <div class="col-sm-4">
                        <input id="dishWeight" type="text" class="form-control" name="dishWeight" value="${dish.weight}">
                    </div>
                    <div class="checkbox col-sm-2">
                        <label>
                            <input id="dishAutoWeight" type="checkbox" name="dishAutoWeightCheckBox"
                                   <c:if test="${dish.autoWeight == true}">checked="checked"</c:if>/>Авто
                        </label>
                    </div>
                </div>

                <table class="table table-bordered table-condensed table-hover">
                    <tr>
                        <th>Id</th>
                        <th>Ингредиент</th>
                        <th>Вес</th>
                        <th>Цена</th>
                        <th>Сумма</th>
                        <th>Выбор</th>
                    </tr>
                    <c:forEach items="${dish.ingredients}" var="ingredient">
                        <tr>
                            <td>${ingredient.id}</td>
                            <td>${ingredient.nameWithHtmlQuot}</td>
                            <td>${ingredient.ingredientWeight}</td>
                            <td>${ingredient.ingredientPrice}</td>
                            <td>${ingredient.ingredientPriceOfWeight}</td>
                            <td><input type="checkbox" name="selectedIngredient${ingredient.id}" value="${ingredient.id}"></td>
                        </tr>
                    </c:forEach>
                </table>
                <div class="container">
                    <div class="col-sm-8 col-sm-offset-2">
                        <div class="form-group">
                            <input type="submit" class="btn btn-default" name="addIngredient" value="Добавить">
                            <input type="submit" class="btn btn-default" name="editIngredient" value="Изменить">
                            <input type="submit" class="btn btn-default" name="removeIngredient" value="Удалить">
                            <input type="submit" class="btn btn-default" name="clearAllIngredients" value="Очистить">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" type="submit" name="saveDishForm">Ok</button>
                    <button class="btn btn-danger" type="button" data-dismiss="modal">Cancel</button>
                </div>
            </form>
        </div>
    </div>
    <input type="hidden" name="dishId" value="${dish.id}">
</div>

<div class="modal fade" id="ingredientsModalWindow">
    <div class="modal-dialog" style="margin-top: 50px">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Ингредиенты</h4>
            </div>

            <div class="modal-body">
                <table class="table table-bordered table-condensed table-hover">
                    <tr>
                        <th>Id</th>
                        <th>Название</th>
                        <th>Еденица</th>
                    </tr>
                    <c:forEach items="${allIngredients}" var="ingredient">
                        <tr>
                            <td>${ingredient.id}</td>
                            <td><a href="${pageContext.request.contextPath}/admin/dishes?clickIngredient=${ingredient.id}">${ingredient.ingredientName}</a></td>
                            <td>${ingredient.unit.unitName}</td>
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

<div class="modal fade" id="paramsModalWindow">
    <div class="modal-dialog" style="margin-top: 50px">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">${paramsForIngredient.ingredientName}(${paramsForIngredient.unit.unitName})</h4>
            </div>
            <form action="${pageContext.request.contextPath}/admin/dishes" method="post" class="form-horizontal">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="ingredientPrice" class="control-label col-sm-4">Цена/ед.</label>
                        <div class="col-sm-3">
                            <input id="ingredientPrice" type="text" class="form-control" name="ingredientParamPrice" value="${paramsForIngredient.ingredientPrice}">
                        </div>
                        <label class="control-label col-sm-1">${paramsForIngredient.currency.shortName}</label>
                    </div>
                    <div class="form-group">
                        <label for="ingredientWeight" class="control-label col-sm-4">Количество</label>
                        <div class="col-sm-3">
                            <input id="ingredientWeight" type="text" class="form-control" name="ingredientParamWeight" value="${paramsForIngredient.ingredientWeight}">
                        </div>
                        <label class="control-label col-sm-1">${paramsForIngredient.unit.unitName}</label>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" type="submit" name="paramsEntered">Ok</button>
                    <button class="btn btn-danger" type="button" data-dismiss="modal">Cancel</button>
                    <input type="hidden" name="selectedIngredientForDish" value="${paramsForIngredient.id}">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>