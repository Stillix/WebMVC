<%--
  Created by IntelliJ IDEA.
  User: tdoro
  Date: 01.07.2023
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Profile</title>
</head>
<body>
Hello, (forward) = ${user}
<br><br><br>
Hi (redirect/forward) = ${user_name}
<br>
<form action="controller"  method="post">
  <input type="hidden" name="command" value="logout">
  <input type="submit" value="Logout" style="font-weight: bold; font-size: 18px; padding: 10px;">
</form>
<form action="controller"  method="post">
  <input type="hidden" name="command" value="show_users">
  <input type="submit" value="show users" style="font-weight: bold; font-size: 18px; padding: 10px;">
</form>
</body>
</html>
