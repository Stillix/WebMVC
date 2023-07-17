<%--
  Created by IntelliJ IDEA.
  User: tdoro
  Date: 17.07.2023
  Time: 1:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create notice</title>
</head>
<body>
<form action="/WebMVC_war_exploded/controller" method="post">
    <input type="hidden" name="command" value="create_notice">
    Заголовок: <input type="text" name="title"  placeholder="Введите заголовок" required><br>
    Имя : <input type="text" name="name" placeholder="Введите имя" required><br>
    Фамилия : <input type="text" name="surname" placeholder="Введите фамилию" required><br>
    Возраст : <input type="text" name="age" placeholder="Введите возраст " required><br>
    Описание : <textarea name="description" placeholder="Введите описание" required></textarea><br>
    Время выполнения : <input type="text" name="execution_time" placeholder="Введите время выполнения заявки" required><br>
    Вознаграждение : <input type="text" name="reward" placeholder="Введите вознаграждение" required><br>
    Статус: <select id="status" name="person_status" required>
    <option value="missing">missing</option>
    <option value="criminal">criminal</option>
</select>
    <br>
    <input type="submit" value="Подать заявку"><br>
</form>
</body>
</html>
