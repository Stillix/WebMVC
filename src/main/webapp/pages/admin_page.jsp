<%--
  Created by IntelliJ IDEA.
  User: tdoro
  Date: 15.07.2023
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="controller" method="post">
    <input type="hidden" name="command" value="show_users">
    <input type="submit" value="show users" style="font-weight: bold; font-size: 18px; padding: 10px;">
</form>
<form action="controller" method="post">
    <input type="hidden" name="command" value="find_user_by_name">
    User name <input type="text" name="name" required><br>
    <input type="submit" value="find user" style="font-weight: bold; font-size: 18px; padding: 10px;">
</form>
<br><br><br>
<form action="controller" method="post">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="Logout" style="font-weight: bold; font-size: 18px; padding: 10px;">
</form>
</body>
</html>
