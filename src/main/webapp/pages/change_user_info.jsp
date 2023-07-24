<%--
  Created by IntelliJ IDEA.
  User: tdoro
  Date: 23.07.2023
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change user info</title>
</head>
<body>
<form action="/WebMVC_war_exploded/controller" method="post">
    <input type="hidden" name="command" value="update_user">
    Имя: <input type="text" name="name" value="${user.userName}" required><br>
    Фамилия: <input type="text" name="surname" value="${user.userSurname}" required><br>
    Телефон: <input type="text" name="phone" value="${user.userPhone}" required><br>
    Email: <input type="email" name="email" value="${user.userEmail}" required><br>
    <input type="submit" value="Зарегистрироваться"><br>
</form>
${message}
</body>
</html>
