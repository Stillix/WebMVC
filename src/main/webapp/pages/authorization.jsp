<%--
  Created by IntelliJ IDEA.
  User: tdoro
  Date: 10.07.2023
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
    <link rel="stylesheet" type="text/css" href="css/authorization.css">

</head>
<body>
<div class="container">
    <h1>Authorization</h1>
    <form action="/WebMVC_war_exploded/controller" method="post">
        <input type="hidden" name="command" value="login"/>
        <label for="login">Login</label>
        <input type="text" id="login" name="login" value=""/>
        <label for="password">Password</label>
        <input type="password" id="password" name="password" value=""/>
        <input type="submit" name="submit" value="Ok"/>
        ${login_failed}<br>
        Еще нет аккаунта? <a href="/WebMVC_war_exploded/pages/registration.jsp">Зарегистрируйтесь</a>
    </form>
</div>
</body>
</html>
