<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/WebMVC_war_exploded/controller" method="post">
    <input type="hidden" name="command" value="show_all_notices">

    <h1>Notices</h1>
    ${message}
    <c:forEach items="${noticeList}" var="noticeList">
        ${noticeList.noticeId}
        ${noticeList.title}
        ${noticeList.personName}
        ${noticeList.personSurname}
        ${noticeList.personAge}
        ${noticeList.description}
        ${noticeList.executionTime}
        ${noticeList.reward}
        ${noticeList.publicationDateTime}
        <br>
    </c:forEach>


</form>
</body>
</html>
