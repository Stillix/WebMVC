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
    Заголовок: <input type="password" name="title" required><br>
    Персон : <input type="text" name="id_person" required><br>
    Время выполнения : <input type="text" name="execution_time" required><br>
    Награда : <input type="text" name="reward" required><br>
    Статус: <select id="status" name="status" required>
    <option value="missing">Пропавший</option>
    <option value="criminal">Преступник</option>
</select>
    Описание: <input type="text" name="description" required><br>
    <br>
    <input type="submit" value="Подать заявку"><br>

</form>
</body>
</html>
