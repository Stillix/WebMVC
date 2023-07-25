<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
    <link rel="stylesheet" type="text/css" href="css/registration.css">
</head>
<body>
<div class="container">
    <h1>Регистрация</h1>
    <form action="/WebMVC_war_exploded/controller" method="post">
        <input type="hidden" name="command" value="register">
        <label for="login">Логин:</label>
        <input type="text" id="login" name="login" required>
        ${errorLoginMessage}${errorLoginExistMessage}<br>
        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password" required>
        ${errorPasswordMessage}<br>
        <label for="name">Имя:</label>
        <input type="text" id="name" name="name" required>
        ${errorNameMessage}<br>
        <label for="surname">Фамилия:</label>
        <input type="text" id="surname" name="surname" required>
        ${errorSurnameMessage}<br>
        <label for="phone">Телефон:</label>
        <input type="text" id="phone" name="phone" required>
        ${errorPhoneMessage}<br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
        ${errorEmailMessage}<br>
        <br>
        <input type="submit" value="Зарегистрироваться"><br>
    </form>
</div>
</body>
</html>
