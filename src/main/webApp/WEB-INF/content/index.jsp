<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>Restaurant</title>

    <link href="<c:url value="/resources/css/bootstrap.css" /> " rel="stylesheet">
    <link href="<c:url value="/resources/css/style.css" /> " rel="stylesheet">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/restaurant_script.js" /> "></script>
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />"></script>

</head>

<body>

<!--Status bar-->
<div class="container">
    <div class="row status">
        <div class="col-md-10 hidden-xs hidden-sm">
            <div class="container">
                <div class="row">
                    <div class="col-md-2"><p>Столиков свободно:</p></div>
                    <div class="col-md-1"><p>15</p></div>
                    <div class="col-md-2"><p>Заказов в очереди:</p></div>
                    <div class="col-md-1"><p>5</p></div>
                    <div class="col-md-4"></div>
                    <%--<div class="col-md-2"><a href="#" onclick="showInFrame('loginPage', '')">Вход</a></div>--%>
                </div>
            </div>
            <div class="container">
                <div class="row">
                    <div class="col-md-2"><p>Столиков зарезерв.:</p></div>
                    <div class="col-md-1"><p>3</p></div>
                    <div class="col-md-2"><p>Блюд готовится:</p></div>
                    <div class="col-md-1"><p>14</p></div>
                    <div class="col-md-4"></div>
                    <%--<div class="col-md-2"><a href="#" onclick="showInFrame('registrationPage', '')">Регистрация</a>--%>
                </div>
            </div>
        </div>
        <div class="col-md-2">
            <iframe id="loginFrame" class="loginFrame" src="${pageContext.request.contextPath}/loginStatusFrame"></iframe>
        </div>
    </div>

</div>
</div>

<!--Menu bar-->
<div class="container">
    <div class="row">
        <div class="navbar navbar-inverse">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#responsive-menu">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href="#" onclick="showInFrame('mainPage', '')" class="navbar-brand">Блинчики тёти Глаши</a>
                </div>
                <div class="collapse navbar-collapse" id="responsive-menu">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Ресторан<b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#" onclick="showInFrame('diagram', '')">Схема зала</a></li>
                                <li><a href="#" onclick="showInFrame('menu', '')">Меню</a></li>
                                <li><a href="#" onclick="showInFrame('reserveTable', 'user/')">Заказать столик</a></li>
                                <li><a href="#" onclick="showInFrame('orderDelivery', 'user/')">Заказать доставку</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Ассортимент<b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Блюда</a></li>
                                <li><a href="#">Готовые наборы</a></li>
                                <li><a href="#">Ингредиенты</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Программы<b
                                    class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Выступления</a></li>
                                <li><a href="#">Авторские вечера</a></li>
                                <li><a href="#">Караоке</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Приват<b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Комнаты переговоров</a></li>
                                <li><a href="#">Комнаты отдыха</a></li>
                                <li><a href="#">Индивидуальные заказы</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">О нас<b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Наши сотрудники</a></li>
                                <li><a href="#">Схема проезда</a></li>
                                <li><a href="#">Контакты</a></li>
                                <li><a href="#">Отзывы</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<!--Admin panel & content-->
<div class="container">
    <div class="row">

        <!--Admin panel-->
        <div class="col-md-2 left-side-panel">
            <div class="btn-group">
                <button type="button" class="btn btn-default dropdown-toggle s100x100" data-toggle="dropdown"
                        aria-haspopup="true"
                        aria-expanded="false">
                    Люди <span class="caret"></span>
                </button>
                <ul class="dropdown-menu dropdown-to-right">
                    <li><a href="#" onclick="showInFrame('posts', 'admin/')">Должности</a></li>
                    <li><a href="#" onclick="showInFrame('employees', 'admin/')">Сотрудники</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="#" onclick="showInFrame('users', 'admin/')">Клиенты</a></li>
                </ul>
            </div>
            <div class="btn-group">
                <button type="button" class="btn btn-default dropdown-toggle s100x100" data-toggle="dropdown"
                        aria-haspopup="true"
                        aria-expanded="false">
                    Обслужи-<br>вание <span class="caret"></span>
                </button>
                <ul class="dropdown-menu dropdown-to-right">
                    <li><a href="#" onclick="showInFrame('orders', 'admin/')">Заказы</a></li>
                    <li><a href="#">Кухня</a></li>
                </ul>
            </div>
            <div class="btn-group">
                <button type="button" class="btn btn-default dropdown-toggle s100x100" data-toggle="dropdown"
                        aria-haspopup="true"
                        aria-expanded="false">
                    Инсту-<br>менты <span class="caret"></span>
                </button>
                <ul class="dropdown-menu dropdown-to-right">
                    <li><a href="#" onclick="showInFrame('ingredients', 'admin/')">Ингредиенты</a></li>
                    <li><a href="#" onclick="showInFrame('dishes', 'admin/')">Блюда</a></li>
                    <li><a href="#" onclick="showInFrame('menus', 'admin/')">Меню</a></li>
                </ul>
            </div>
            <div class="btn-group">
                <button type="button" class="btn btn-default dropdown-toggle s100x100" data-toggle="dropdown"
                        aria-haspopup="true"
                        aria-expanded="false">
                    Менедж-<br>мент <span class="caret"></span>
                </button>
                <ul class="dropdown-menu dropdown-to-right">
                    <li><a href="#" onclick="showInFrame('purchaseInvoices', 'admin/')">Приход</a></li>
                    <li><a href="#" onclick="showInFrame('salesInvoices', 'admin/')">Расход</a></li>
                    <li><a href="#" onclick="showInFrame('contractors', 'admin/')">Контрагенты</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="#" onclick="showInFrame('warehouse', 'admin/')">Склад</a></li>
                </ul>
            </div>
            <div class="btn-group">
                <button type="button" class="btn btn-default dropdown-toggle s100x100" data-toggle="dropdown"
                        aria-haspopup="true"
                        aria-expanded="false">
                    Отчёты <span class="caret"></span>
                </button>
                <ul class="dropdown-menu dropdown-to-right">
                    <li><a href="#">Доходы/расходы</a></li>
                    <li><a href="#">Приход/Расход</a></li>
                    <li><a href="#">Сотрудники</a></li>
                    <li role="separator" class="divider"></li>
                    <li><a href="#">Отзывы</a></li>
                </ul>
            </div>
            <div class="btn-group">
                <button type="button" class="btn btn-default dropdown-toggle s100x100" data-toggle="dropdown"
                        aria-haspopup="true"
                        aria-expanded="false">
                    Сервис <span class="caret"></span>
                </button>
                <ul class="dropdown-menu dropdown-to-right">
                    <li><a href="#">Настройки</a></li>
                    <li><a href="#">База данных</a></li>
                </ul>
            </div>
        </div>

        <!--Content-->
        <div class="col-md-9 center-panel">
            <iframe class="main-frame" id="mainFrame" src="${pageContext.request.contextPath}/mainPage"></iframe>
        </div>

        <!--Left side for banner-->
        <div class="col-md-1 hidden-xs hidden-sm hidden-md right-side-panel">
            <img src="http://placehold.it/130x600" alt="">
        </div>
    </div>
</div>

</body>