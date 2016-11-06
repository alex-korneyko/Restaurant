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

    <title>employees</title>
</head>

<body>
<h3>Персонал</h3>

<c:if test="${openEditWindow == true}">
    <script type="text/javascript">
        $(window).load(function () {
            $('#modal-1').modal('show');
        })
    </script>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/admin/employees">
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
    <div class="container" style="width: 100%">

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

        <c:if test="${error != null}">
            <div class="alert alert-danger alert-dismissable">
                <button type="button" class="close" data-dismiss="alert">
                    <i class="fa fa-times fa-fw"></i>
                </button>
                <h4>Ошибка</h4>
                <p>${error}</p>
                <p><button type="button" class="btn btn-danger" data-dismiss="alert">Отмена</button></p>
            </div>
        </c:if>

        <table class="table table-bordered table-condensed table-hover">

            <tr>
                <th>Id</th>
                <th>Логин</th>
                <th>Фамилия</th>
                <th>Имя</th>
                <th>Телефон</th>
                <th>Дата рожд.</th>
                <th>Должность</th>
                <th>Зарплата</th>
                <th>Выбрать</th>
            </tr>

            <c:forEach items="${employees}" var="employee">
                <tr>
                    <td>${employee.id}</td>
                    <td><a href="${pageContext.request.contextPath}/user/userProfile?login=${employee.user.userLogin}"> ${employee.user.userLogin}</a></td>
                    <td>${employee.lastName}</td>
                    <td>${employee.firstName}</td>
                    <td>${employee.phoneNumber}</td>
                    <td>${employee.dateOfBirth}</td>
                    <td>${employee.employeePost}</td>
                    <td>${employee.salary}</td>
                    <td><input type="checkbox" name="selected${employee.id}" value="${employee.id}"></td>
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
                <h4 class="modal-title">Работник</h4>
            </div>
            <form action="${pageContext.request.contextPath}/admin/employees" method="post" class="form-horizontal">
                <div class="modal-body">

                    <div class="form-group">
                        <label for="newEmployeeNameTextBox" class="control-label col-sm-4">Имя:</label>
                        <div class="col-sm-6">
                            <input id="newEmployeeNameTextBox" type="text" class="form-control" name="userName" value="${employeeNameForEditing}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="newEmployeeSurNameTextBox" class="control-label col-sm-4">Фамилия:</label>
                        <div class="col-sm-6">
                            <input id="newEmployeeSurNameTextBox" type="text" class="form-control" name="userSurName" value="${employeeSurNameForEditing}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="newEmployeeLoginTextBox" class="control-label col-sm-4">Логин:</label>
                        <div class="col-sm-6">
                            <input id="newEmployeeLoginTextBox" type="text" class="form-control" name="userLogin" value="${employeeLoginForEditing}"
                            <%--<c:if test="${passEditDisable == true}"> disabled="disabled" </c:if>--%>
                            >
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="newEmployeePass1TextBox" class="control-label col-sm-4">Пароль:</label>
                        <div class="col-sm-6">
                            <input id="newEmployeePass1TextBox" type="password" class="form-control" name="userPass1"
                            <%--<c:if test="${passEditDisable == true}"> disabled="disabled" </c:if> --%>
                            >
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="newEmployeePass2TextBox" class="control-label col-sm-4">Повторить пароль:</label>
                        <div class="col-sm-6">
                            <input id="newEmployeePass2TextBox" type="password" class="form-control" name="userPass2"
                            <%--<c:if test="${passEditDisable == true}"> disabled="disabled" </c:if> --%>
                            >
                        </div>
                    </div>

                    <div id="accordion" style="margin-bottom: 5px" class="col-sm-offset-2 col-sm-8 panel-group panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a href="#groups" data-parent="#accordion" data-toggle="collapse">Членство в группах</a>
                            </h4>
                        </div>
                        <div class="collapse panel-collapse" id="groups">
                            <div class="panel-body">
                                <table class="table table-bordered table-condensed table-hover">
                                    <tr>
                                        <th>Id</th>
                                        <th>Группа</th>
                                        <th>Выбрать</th>
                                    </tr>

                                    <c:forEach items="${allGroups}" var="group">
                                        <tr>
                                            <td>${group.id}</td>
                                            <td>${group.groupName}</td>
                                            <td>
                                                <input type="checkbox" name="selected${group.id}" value="${group.id}"
                                                <c:if test="${employeeGroups.contains(group)}"> checked="checked" </c:if>
                                                >
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="dropDownComboBox" class="control-label col-sm-4">Должность:</label>
                        <div class="dropdown col-sm-6">
                            <c:if test="${post != null}">
                                <script type="text/javascript">
                                    $(function (){
                                        dropDownComboBoxScript('${post.postName}')
                                    });
                                </script>
                            </c:if>
                            <input style="width: 200px" id="dropDownComboBox" type="button" data-toggle="dropdown"
                                   aria-haspopup="true" aria-expanded="false" class="btn btn-default"
                                   <c:if test="${post == null}"> value="Выберите..." </c:if>
                                   <c:if test="${post != null}"> value="${post.postName}" </c:if>
                            />

                            <ul class="dropdown-menu" aria-labelledby="newEmployeePostDropDown">
                                <c:forEach items="${allPosts}" var="post">
                                    <li><a href="#" onclick="dropDownComboBoxScript('${post.postName}')">${post.postName}</a> </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>

                    <input type="hidden" name="employeePost" id="dropDownComboBoxSelectedValue">

                    <div class="form-group">
                        <label for="newEmployeePhoneTextBox" class="control-label col-sm-4">Телефон:</label>
                        <div class="col-sm-6">
                            <input id="newEmployeePhoneTextBox" type="text" class="form-control" name="newEmployeePhone" value="${employeePhoneForEditing}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="newEmployeeDateBirth" class="control-label col-sm-4">Дата рождения:</label>
                        <div class="col-sm-6">
                            <input id="newEmployeeDateBirth" type='date' class="form-control" name="newEmployeeDateOfBirth" value="${employeeBirthForEditing}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="newEmployeeSalaryTextBox" class="control-label col-sm-4">Оклад:</label>
                        <div class="col-sm-6">
                            <input id="newEmployeeSalaryTextBox" type="text" class="form-control" name="newEmployeeSalary" value="${employeeSalaryForEditing}">
                        </div>
                    </div>


                    <input type="hidden" name="employeeId" value="${employeeIdForEditing}">

                </div>
                <div class="modal-footer">
                    <button class="btn btn-default" type="submit" name="saveEmployeeForm">Ok</button>
                    <button class="btn btn-danger" type="button" data-dismiss="modal">Cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>