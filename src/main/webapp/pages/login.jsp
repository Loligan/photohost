<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<head>
    <title>Фотохостинг::Вход</title>
    <link rel="stylesheet" href="/semantic/semantic.min.css">
    <link rel="stylesheet" href="components/custom/other.css">

    <link rel="script" href="components/dropdown.js">
</head>


<body>
<div class="ui secondary pointing menu">
    <a class="active item" href="/">photohost </a>
    <a class="item" href="/">На главную </a>

    <div class="right menu">
        <sec:authorize access="isAuthenticated()">
            <a href="/album/Общие" class="item">Мои альбомы </a>


            <a href="/profile" class="ui item">Профиль</a>

        </sec:authorize>
        <sec:authorize access="hasRole('ADMIN')">
            <a class="ui item" href="/admin">Статистика </a>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
            <a class="ui item" href="/logout">Выход </a>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
            <a class="ui item" href="/login">Вход</a>
            <a class="ui item" href="/register">Регистрация</a>
        </sec:authorize>
    </div>
</div>
<!---->
<c:if test="${err_msg != null}">
    <div class="ui negative message">
        <div class="header">
            Ошибка
        </div>
        <p>${err_msg}
        </p></div>
</c:if>
<c:if test="${ok_msg != null}">
    <div class="ui positive message">
        <div class="header">
           Успешно!
        </div>
        <p>${ok_msg}
        </p></div>
</c:if>
<div class="ui container  aligned marg-top">
    <div class="ui segment">
        <div class="ui center aligned basic segment">

            <div class="ui left icon action input">
                <div class="ui form">
                    <c:url value="/j_spring_security_check" var="loginUrl"/>
                    <form action="${loginUrl}" method="post">
                        <div class="field">

                        </div>
                        <div class="field">
                            <label>Логин</label>

                            <div class="ui left icon input">
                                <input maxlength="15" type="text" name="j_username" placeholder="Электронная почта"
                                       required autofocus
                                       value="user">
                            </div>
                        </div>
                        <div class="field">
                            <label>Пароль</label>
                            <div class="ui left icon input">
                                <input maxlength="15" type="password" name="j_password" placeholder="Пароль"
                                       required value="user">
                            </div>
                        </div>
                        <button class="ui blue button" type="submit">Войти</button>
                    </form>
                </div>
            </div>
        </div>

    </div>
</div>

</body>
</html>
