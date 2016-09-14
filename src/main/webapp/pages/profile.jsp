<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <link rel="stylesheet" href="/semantic/semantic.min.css">
  <link rel="stylesheet" href="components/custom/other.css">
  <link rel="script" href="components/dropdown.js">
  <title>Фотохостинг::Настройка профиля</title>
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

<c:if test="${err_msg != null}">
<div class="ui negative message">
  <div class="header">
    Ошибка
  </div>
  <p>${err_msg}
  </p></div>
</c:if>


<div class="ui container left aligned" >
  <div class="ui segment">
    <h4>Ваш логин: ${login}     <br><br>Ваша электронная почта: ${email}</h4>
    <label>Новая электронная почта:</label><br>

    <form action="/profile/changeEmail" method="POST">
      <div class="ui left corner labeled input">
        <input maxlength="15" required="required"  placeholder="Электронная почта" type="email" name="email">
      </div>
      <input class="ui orange button" type="submit" value="Изменить почту">
    </form>

    <br>
    <label>Новый пароль:</label>
    <form action="/profile/changePassword" method="POST">
    <div class="ui left corner labeled input">
      <input maxlength="15" required="required"  placeholder="Пароль" type="password" name="password">
    </div>
      <input class="ui orange button" type="submit" value="Изменить пароль">
    </form>


  </div>
</div>


</html>
