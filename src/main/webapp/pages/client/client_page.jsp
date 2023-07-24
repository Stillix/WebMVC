<%--
  Created by IntelliJ IDEA.
  User: tdoro
  Date: 20.07.2023
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client</title>
</head>
<body>

<form action="controller" method="post">
    <input type="hidden" name="command" value="show_all_notices">
    <input type="submit" value="show notices" style="font-weight: bold; font-size: 18px; padding: 10px;">
</form>
<br><br>
<form action="controller" method="post">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="Logout" style="font-weight: bold; font-size: 18px; padding: 10px;">
</form><br>
Для заполнения формы заявки перейдите на <a href="pages/form_notice.jsp">страницу заполнения заявки</a>

</body>
</html>
