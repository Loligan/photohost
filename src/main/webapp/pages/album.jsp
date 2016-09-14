<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ru">
<head>
    <meta charset="utf-8">

    <title>Фотохостинг::Просмотр альбомов</title>
    <link rel="stylesheet" href="/semantic/semantic.min.css">
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>

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

<div class="ui right menu">

    <a class="active red item">${album_name}</a>

    <!--Переимнование альбома-->
    <a class="item">
        <form action="/rename/${album_name}" method="post">
        <div class="ui input">
            <input placeholder="Новое имя" name="new_album_name" maxlength="15" required="required" type="text">

            <!--<a class="item">-->
            <input type="submit" class="ui  basic button"  value="Переимновать"/>
            <!--</a>-->
        </div>
            </form>
    </a>

    <!--Создание нового альбома-->
    <a class="item">
        <form action="/create_album/" method="POST" enctype="multipart/form-data"    >
            <div class="ui input">
                <input placeholder="Имя альбома" name="name" maxlength="15" required="required"  type="text">
                <input type="submit" class="ui  basic button" value="Создать альбом"/>
            </div>
        </form>
    </a>

    <%--</a>--%>

    <!--Загрузка изображения-->
    <div class="right menu">
        <a class="item">
            <form method="POST" action="/uploadFile/${album_name}" enctype="multipart/form-data">
            <div class="ui input">
                <input type="file" required="required"  name="file" id="file" class="ui basic ">
                <input type="submit" class="ui  basic button" id="subFile" value="Загрузить изображение"/>
            </div>
                </form>
        </a>
        <!--Удаление альбома-->
        <a class="item">
            <form action="/drop_album/${album_name}" method="POST">
                <div class="ui input">
                    <input type="submit" class="ui red  button" value="Удалить альбом"/>
                </div>
            </form>
        </a>
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

<c:if test="${ok_msg != null}">
    <div class="ui positive message">
        <div class="header">
            Успешно!
        </div>
        <p>${ok_msg}
        </p></div>
</c:if>

<div class="ui grid">
    <div class="two wide column">
        <div class="ui vertical menu">
            <div class="item">
                <div class="header">Альбомы</div>
                <div class="menu">
                    <c:forEach items="${albumsNameList}" var="item">
                        <a class="item" href="/album/${item}"> ${item}</a>
                    </c:forEach>
                </div>

            </div>
        </div>
    </div>
    <div class="ten wide stretched column">

        <!---->
        <div class="ui cards">
            <!--item1-->
            <c:forEach items="${images_album}" var="item">
            <div class="card">
                <div class="blurring dimmable image">
                    <img  src="/img/${item}">
                </div>
                <div class="extra content ui center aligned  grid">

                    <div class="ui basic buttons">
                        <a class="ui button" href="/image/${item}">Просмотреть</a>

                        <form name="supportform" method="POST" action="/drop/${album_name}/${item}">
                            <input type="submit" class="ui button" value="Удалить"/>
                            </form>
                  </div>


                        <div class="view-source">
                            <a href="#">Переместить альбом</a>
                            <c:forEach items="${albumsNameList}" var="album_item">
                            <form method="POST" action="/moving/${item}/${album_item}">
                                <div class="hide">${album_item}<input type="submit" value="Переместить"></div>
                            </form>
                            </c:forEach>

                        </div>
                </div>
            </div>
            </c:forEach>


            <!---->
        </div>
    </div>
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
    };

</script>


<script>
    $(document).ready(function () {
        $('.view-source .hide').hide();
        $('.view-source a').on('click', function () {
            $('.view-source .hide').slideUp(500);
            $(this).parent().find('.hide').slideDown(500);
        });
    });
</script>


</body>
</html>