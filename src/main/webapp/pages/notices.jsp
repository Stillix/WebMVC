<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/WebMVC_war_exploded/controller" method="post">
    <input type="hidden" name="command" value="show_users">

    <h1>Notices</h1>

    <c:forEach items="${noticeList}" var="noticeList">
        ${noticeList.noticeId}
        ${noticeList.title}
        ${noticeList.userId}
        ${noticeList.personId}
        ${noticeList.executionTime}
        ${noticeList.publicationDateTime}
        ${noticeList.reward}
        ${noticeList.statusId}
        ${noticeList.description}
    </c:forEach>

    <p>${message}</p>
</form>
</body>
</html>
