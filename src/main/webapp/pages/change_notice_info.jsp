<%--
  Created by IntelliJ IDEA.
  User: tdoro
  Date: 23.07.2023
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/WebMVC_war_exploded/controller" method="post">
    <input type="hidden" name="command" value="save_notice">
    <input type="hidden" name="noticeId" value="${notice.noticeId}">
    Заголовок: <input type="text" name="title" maxlength="45"  value="${notice.title}"  placeholder="Введите заголовок" required><br>
    Имя : <input type="text" name="name" placeholder="Введите имя"  value="${notice.personName}" required><br>
    Фамилия : <input type="text" name="surname" placeholder="Введите фамилию"  value="${notice.personSurname}" required><br>
    Возраст : <input type="text" name="age" maxlength="13" placeholder="Введите возраст "  value="${notice.personAge}" required><br>
    Описание : <textarea name="description" placeholder="Введите описание"  value="${notice.description}" required></textarea><br>
    Время выполнения : <input type="text" name="execution_time" placeholder="Введите время выполнения заявки" value="${notice.executionTime}" required><br>
    Вознаграждение : <input type="text" name="reward" placeholder="Введите вознаграждение"  value="${notice.reward}" required><br>
    Статус: <select id="status" name="person_status" required>
    <option value="missing">missing</option>
    <option value="criminal">criminal</option>
</select>
    <br>
    <input type="submit" value="Save notice"><br>
</form>
${message}
</body>
</html>
