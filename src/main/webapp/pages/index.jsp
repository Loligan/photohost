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
    <link rel="script" href="components/dropdown.js">
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    <title>Фотохостинг:: Главная страница</title>
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

<div class="ui container center aligned marg-top" }>
    <sec:authorize access="isAuthenticated()">
        <div class="ui segment">
            <h1 class="ui header">Загрузить файл</h1><br>

            <form method="POST" action="uploadFile/Общие" enctype="multipart/form-data">
                <div>
                    <input type="file" name="file" multiple/>
                    <button class="ui simple button" id="subFile" type="submit">Загрузить</button>

                </div>
            </form>

            <h5>Максимальный размер: <b>10мб</b><br> Раширение файла: <b>JPG</b></h5>
            <br>


        </div>
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
        <div class="ui segment">
            <h1 class="ui header">Загрузить файл</h1><br>


            <form method="POST" action="uploadAnonimousFile" enctype="multipart/form-data">
                <div>
                   <input  required="required" type="file" name="file" id="file" multiple>
                    <button class="ui simple button" id="subFile" type="submit" disabled="disabled">Загрузить</button>

                </div>
            </form>

            <h5>Максимальный размер: <b>10мб</b> <br> Раширение файла: <b>JPG</b></h5>
            <br>



        </div>
    </sec:authorize>

</div>


<script type="text/javascript">

    document.getElementsByName('file')[0].onchange = function (e) {
        fileobject = e.target.files;
        for (var i = 0; i < fileobject.length; i++) {
            if ((fileobject[i].size > 1000000) || (innerHTML = fileobject[i].type != "image/jpeg")) {
                document.getElementById("subFile").disabled = true;
                alert("Неверное расширение либо большой размер");
            } else {
                document.getElementById("subFile").disabled = false;
            }
        }
    }
</script>
</body>

</html>
