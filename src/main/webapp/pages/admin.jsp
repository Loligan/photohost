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
  <title>Фотохостинг::Просмотр статистики</title>
</head>
<body>

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
<%----%>

<div class="ui container center aligned marg-top" }>
  <div class="ui segment">
    <div class="ui statistic">
      <div class="label">Пользователей </div>
      <div class="value">
        ${numerUser}
      </div>
    </div>

    <div class="ui statistic">
      <div class="label">Альбомов </div>
      <div class="value">
        ${numerAlbum}
      </div>
    </div>

    <div class="ui statistic">
      <div class="label">Изображений </div>
      <div class="value">
        ${numerImage}
      </div>
    </div>

  </div>
</div>
</body>
</html>
