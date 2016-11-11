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

    <title>Sales invoices</title>
</head>

<body>
<h3>Расходные накладные</h3>

<c:if test="${openEditWindow == true}">
    <script type="text/javascript">
        $(window).load(function () {
            $('#invoiceModalWindow').modal('show');
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

<form method="post" action="${pageContext.request.contextPath}/admin/salesInvoices">
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

        <c:if test="${error == true}">
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
                <th>Дата</th>
                <th>Контрагент</th>
                <th>Ордер</th>
                <th>Сумма</th>
                <th>Выбор</th>
            </tr>

            <c:forEach items="${allInvoices}" var="invoice">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/admin/salesInvoices?invoice=${invoice.id}">${invoice.id}</a></td>
                    <td>${invoice.invoiceDate}</td>
                    <td>${invoice.contractor.nameWithHtmlQuot}</td>
                    <td>${invoice.order.id}</td>
                    <td>${invoice.amountOfInvoice}</td>
                    <td><input type="checkbox" name="selected${invoice.id}" value="${invoice.id}"></td>
                </tr>
            </c:forEach>

        </table>
    </div>
</form>

<div class="modal fade" id="invoiceModalWindow">
    <div class="modal-dialog" style="width: 700px">
        <div class="modal-content">
            <div class="modal-header">
                <button class="close" type="button" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Расходная накладная</h4>
            </div>
            <form action="${pageContext.request.contextPath}/admin/salesInvoices" method="post" class="form-horizontal">
                <div class="modal-body">

                    <div class="form-group">
                        <label for="dropDownComboBox" class="control-label col-sm-4">Контрагент:</label>
                        <div class="dropdown col-sm-6">
                        <c:if test="${invoiceForEditing.contractor != null}">
                            <script type="text/javascript">
                                $(function (){
                                    dropDownComboBoxScript('${invoiceForEditing.contractor.contractorName}')
                                });
                            </script>
                        </c:if>
                        <input style="width: 300px" id="dropDownComboBox" type="button" data-toggle="dropdown"
                               aria-haspopup="true" aria-expanded="false" class="btn btn-default"
                        <c:if test="${invoiceForEditing == null || invoiceForEditing.contractor == null}"> value="Выберите..." </c:if>
                        <c:if test="${invoiceForEditing != null}"> value="${invoiceForEditing.contractor.nameWithHtmlQuot}" </c:if>
                        >
                        <ul class="dropdown-menu" aria-labelledby="newInvoiceDropDown">
                            <c:forEach items="${allContractors}" var="contractor">
                                <li><a href="#" onclick="dropDownComboBoxScript('${contractor.nameWithHtmlQuot}')">${contractor.nameWithHtmlQuot}</a> </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                <input type="hidden" name="selectedContractor" id="dropDownComboBoxSelectedValue" value="0">

                <div class="form-group">
                    <label for="invoiceDate" class="control-label col-sm-4">Дата накладной</label>
                    <div class="col-sm-6">
                        <input id="invoiceDate" type="date" class="form-control" name="invoiceDate" value="${invoiceForEditing.invoiceDate}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="invoicePrice" class="control-label col-sm-4">Сумма:</label>
                    <div class="col-sm-4">
                        <input id="invoicePrice" type="text" class="form-control" name="invoicePrice" value="${invoiceForEditing.amountOfInvoice}">
                    </div>
                    <div class="checkbox col-sm-2">
                        <label>
                            <input id="invoiceAutoPrice" type="checkbox" name="invoiceAutoPriceCheckBox"
                                   <c:if test="${invoiceForEditing.autoPrice == true}">checked="checked"</c:if>/>Авто
                        </label>
                    </div>
                </div>

                <div class="form-group">
                    <label for="sourceOrderId" class="control-label col-sm-4">Ордер-основание:</label>
                    <div class="col-sm-6">
                        <input id="sourceOrderId" type="text" class="form-control" name="sourceOrderId" contenteditable="false" value="${invoiceForEditing.order.id}">
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
                    <c:forEach items="${invoiceForEditing.ingredients}" var="ingredient">
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
                    <button class="btn btn-default" type="submit" name="saveInvoiceForm"
                            <c:if test="${disableOk == true}">disabled="disabled"</c:if>>Ok
                    </button>
                    <button class="btn btn-danger" type="button" data-dismiss="modal">Cancel</button>
                </div>
            </form>
        </div>
    </div>
    <input type="hidden" name="invoiceId" value="${invoiceForEditing.id}">
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
                            <td><a href="${pageContext.request.contextPath}/admin/salesInvoices?clickIngredient=${ingredient.id}">${ingredient.ingredientName}</a></td>
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
            <form action="${pageContext.request.contextPath}/admin/salesInvoices" method="post" class="form-horizontal">
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
                    <input type="hidden" name="selectedIngredientForInvoice" value="${paramsForIngredient.id}">
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>