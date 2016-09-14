<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Фотохостинг::Регистрация</title>
    <link rel="stylesheet" href="/semantic/semantic.min.css">
    <link rel="stylesheet" href="components/custom/other.css">

    <link rel="script" href="components/dropdown.js">
</head>


<body>
<div class="ui secondary pointing menu">
    <a class="active item" href="/">photohost </a>
    <a class="item" href="/">На главную </a>

    <div class="right menu">

            <a class="ui item" href="/login">Вход</a>
            <a class="ui item" href="/register">Регистрация</a>

    </div>
</div>

<!---->


<body>
<c:if test="${err_msg != null}">
    <div class="ui negative message">
        <div class="header">
            Ошибка
        </div>
        <p>${err_msg}
        </p></div>
</c:if>

<div class="ui container aligned marg-top">
    <div class="ui segment">
        <div class="ui center aligned basic segment">
            <div class="ui left icon action input">
                <div class="ui form">
                    <form:form method="POST" action="/register">
                        <div class="field">
                            <label>Логин</label>

                            <div class="ui left icon input">
                                <form:input required="required" path="login"  placeholder="Логин"/>
                            </div>
                        </div>
                        <div class="field">
                            <label>Почта</label>

                            <div class="ui left icon input">
                                <form:input  required="required" maxlength="15"  path="email"  type="email" placeholder="Электронная почта"/>

                            </div>
                        </div>
                        <div class="field">
                            <label>Пароль</label>

                            <div class="ui left icon input">
                                <form:input required="required" maxlength="15" path="password" type="password" placeholder="Пароль"/>
                            </div>
                        </div>

                        <input class="ui teal basic button" type="submit" value="Регистрация"/>
                    </form:form>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>
