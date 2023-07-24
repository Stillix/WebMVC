<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Notices</title>
</head>
<body>
<h1>Notices</h1>

${message}<br>
<c:forEach items="${noticeList}" var="noticeList">
    ${noticeList.title}
    ${noticeList.personName}
    ${noticeList.personSurname}
    ${noticeList.personAge}
    ${noticeList.description}
    ${noticeList.executionTime}
    ${noticeList.reward}
    ${noticeList.publicationDateTime}
    <form action="/WebMVC_war_exploded/controller" method="post">
        <input type="hidden" name="noticeId" value="${noticeList.noticeId}">
        <input type="hidden" name="command" value="delete_notice">
        <input type="submit" value="delete notice" style="font-weight: bold; font-size: 10px; padding: 10px;">
    </form>
    <form action="/WebMVC_war_exploded/controller" method="post">
        <input type="hidden" name="noticeId" value="${noticeList.noticeId}">
        <input type="hidden" name="command" value="edit_notice">
        <input type="submit" value="update notice" style="font-weight: bold; font-size: 10px; padding: 10px;">
    </form>
</c:forEach>
</body>
</body>
</html>
