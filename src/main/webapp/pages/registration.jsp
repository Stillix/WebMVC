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
  <input type="hidden" name="command" value="register">
    Логин: <input type="text" name="login" required>${errorLoginMessage}${errorLoginExistMessage}<br>
  Пароль: <input type="password" name="password" required>${errorPasswordMessage}<br>
  Имя: <input type="text" name="name" required>${errorNameMessage}<br>
  Фамилия: <input type="text" name="surname" required>${errorSurnameMessage}<br>
  Телефон: <input type="text" name="phone" required>${errorPhoneMessage}<br>
  Email: <input type="email" name="email" required>${errorEmailMessage}<br>
 <br>
  <input type="submit" value="Зарегистрироваться"><br>

</form>
</body>
</html>
