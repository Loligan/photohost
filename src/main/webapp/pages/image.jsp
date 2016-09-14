<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html lang="en">
<head>
    <title>Фотохостинг::Просмотр изображения</title>
    <link rel="stylesheet" href="/semantic/semantic.min.css">
    <link rel="stylesheet" href="/components/custom/other.css">

    <link rel="script" href="/components/dropdown.js">
    <script src="https://cdn.rawgit.com/zenorocha/clipboard.js/master/dist/clipboard.min.js"></script>
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

<div class="ui container center aligned marg-top-result">
    <div class="ui segment ">

        <div class="ui sizer vertical segment">
            <div class="ui large header">Изображение успешно загружено</div>
        </div>
        <img class="ui fluid image large top aligned" src="${img_link}">
        <br>
        <br>


        <div class="ui action input small">
        <input type="text" id="textarea-example_1" value="localhost:8083${img_link}"/>
        <button class=" ui teal right labeled icon button btn-clipboard" data-clipboard-target="#textarea-example_1">Прямая ссылка</button>
</div>
        <div class="ui action input small">
            <input type="text" id="textarea-example_2" value="localhost:8083${img_view}"/>
            <button class=" ui teal right labeled icon button btn-clipboard" data-clipboard-target="#textarea-example_2">Ссылка на просмотр</button>
        </div>

        <br>
        <br>

        <div class="ui action input small">
            <input type="text" id="textarea-example_3" value="[img]localhost:8083${img_link}[/img]"/>
            <button class=" ui teal right labeled icon button btn-clipboard" data-clipboard-target="#textarea-example_3">Вставка в форум</button>
        </div>

        <div class="ui action input small">
            <input type="text" id="textarea-example_4" value="<img src='localhost:localhost:8083${img_link}'/>"/>
            <button class=" ui teal right labeled icon button btn-clipboard" data-clipboard-target="#textarea-example_4">Вставка в HTML</button>
        </div>

    </div>

    <script>
        new Clipboard('.btn-clipboard'); // Не забываем инициализировать библиотеку на нашей кнопке
    </script>
</div>

</body>
</html>
