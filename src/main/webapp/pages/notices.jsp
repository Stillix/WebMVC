<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Notices</title>
    <link rel="stylesheet" type="text/css" href="pages/css/notices.css">
</head>
<body>
<div class="container">
    <h1>Заявки</h1>

    ${message}<br>
    <c:forEach items="${noticeList}" var="notice">
        <div class="notice">
            <div class="notice-title">${notice.title}</div>
            <div class="notice-info">
                <p>Имя: ${notice.personName}</p>
                <p>Фамилия: ${notice.personSurname}</p>
                <p>Возраст: ${notice.personAge}</p>
                <p>Статус: ${notice.personStatus}</p>
                <p>Описание: ${notice.description}</p>
                <p>Срок выполнения: ${notice.executionTime}</p>
                <p>Вознаграждение: ${notice.reward}</p>
                <p>Дата-время публикации: ${notice.publicationDateTime}</p>
            </div>
            <div class="form-buttons">
                <form action="/WebMVC_war_exploded/controller" method="post">
                    <input type="hidden" name="noticeId" value="${notice.noticeId}">
                    <input type="hidden" name="command" value="delete_notice">
                    <input type="submit" value="Удалить" class="form-button">
                </form>
                <form action="/WebMVC_war_exploded/controller" method="post">
                    <input type="hidden" name="noticeId" value="${notice.noticeId}">
                    <input type="hidden" name="command" value="edit_notice">
                    <input type="submit" value="Изменить" class="form-button">
                </form>
            </div>
        </div>
        <br>
    </c:forEach>
</div>
</body>
</html>
