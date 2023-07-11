<%--
  Created by IntelliJ IDEA.
  User: tdoro
  Date: 09.07.2023
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Регистрация</title>
</head>
<body>
<h1>Регистрация</h1>
<form action="/WebMVC_war_exploded/controller" method="post">
  <input type="hidden" name="command" value="add_user">
  Логин: <input type="text" name="login" required><br>
  Пароль: <input type="password" name="password" required><br>
  Имя: <input type="text" name="name" required><br>
  Фамилия: <input type="text" name="surname" required><br>
  Телефон: <input type="text" name="phone" required><br>
  Email: <input type="email" name="email" required><br>
 <br>
  <input type="submit" value="Зарегистрироваться"><br>
    ${errorMessage}
</form>
</body>
</html>
