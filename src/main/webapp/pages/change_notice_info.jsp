<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Change notice</title>
    <link rel="stylesheet" type="text/css" href="pages/css/change_notice_info.css">
</head>
<body>
<div class="container">
    <h1>Изменение заявки</h1>
    ${message}
    <form action="/WebMVC_war_exploded/controller" method="post">
        <input type="hidden" name="command" value="save_notice">
        <input type="hidden" name="noticeId" value="${notice.noticeId}">
        <label for="title">Заголовок:</label>
        <input type="text" id="title" name="title" maxlength="45" value="${notice.title}" placeholder="Введите заголовок" required><br>
        <label for="name">Имя:</label>
        <input type="text" id="name" name="name" value="${notice.personName}" placeholder="Введите имя" required><br>
        <label for="surname">Фамилия:</label>
        <input type="text" id="surname" name="surname" value="${notice.personSurname}" placeholder="Введите фамилию" required><br>
        <label for="age">Возраст:</label>
        <input type="text" id="age" name="age" maxlength="13" value="${notice.personAge}" placeholder="Введите возраст" required><br>
        <label for="description">Описание:</label>
        <textarea id="description" name="description" placeholder="Введите описание" required>${notice.description}</textarea><br>
        <label for="execution_time">Время выполнения:</label>
        <input type="text" id="execution_time" name="execution_time" value="${notice.executionTime}" placeholder="Введите время выполнения заявки" required><br>
        <label for="reward">Вознаграждение:</label>
        <input type="text" id="reward" name="reward" value="${notice.reward}" placeholder="Введите вознаграждение" required><br>
        <label for="status">Статус:</label>
        <select id="status" name="person_status" required>
            <option value="missing" ${notice.personStatus == 'missing' ? 'selected' : ''}>missing</option>
            <option value="criminal" ${notice.personStatus == 'criminal' ? 'selected' : ''}>criminal</option>
        </select><br>
        <input type="submit" value="Save notice"><br>
    </form>

</div>
</body>
</html>
