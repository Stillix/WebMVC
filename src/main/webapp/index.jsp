<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Главная страница</title>
    <link rel="stylesheet" type="text/css" href="pages/css/home.css">
</head>
<body>

<header>
    <h1>Главная страница</h1>
</header>

<div>
    <form action="pages/authorization.jsp">
        <button type="submit">Перейти к авторизации</button>
    </form>
    <br><br>
    <form action="pages/registration.jsp">
        <button type="submit">Перейти к регистрации</button>
    </form>
    <br><br>
    <form action="/WebMVC_war_exploded/controller" method="post">
        <input type="hidden" name="command" value="show_all_notices"/>
        <button type="submit">Посмотреть заявки</button>
    </form>
</div>
</body>
</html>
