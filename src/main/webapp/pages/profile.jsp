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
<form action="/WebMVC_war_exploded/controller" method="post">
    <input type="hidden" name="command" value="update_user">
    <h1>Edit User</h1>
    <p>${message}</p>
    User ID:
    <input type="text" id="userId" name="user_id" value="">
    Name:
    <input type="text" id="name" name="name" value="" required>
    Surname:
    <input type="text" id="surname" name="surname" value="" required>
    Phone:
    <input type="text" id="phone" name="phone" value="" required>
    Email:
    <input type="email" id="email" name="email" value="" required>
    Role:
    <input type="text" id="role" name="role" value="" required>
    <button type="submit">Update User</button>
</form>
<br><br><br><br><br><br>

<form action="controller" method="post">
    <input type="hidden" name="command" value="find_user_by_name">
    User name <input type="text" name="name" required><br>
    <input type="submit" value="find user" style="font-weight: bold; font-size: 18px; padding: 10px;">
</form>
<form action="controller" method="post">
    <input type="hidden" name="command" value="show_all_notices">
    <input type="submit" value="show notices" style="font-weight: bold; font-size: 18px; padding: 10px;">
</form>
<br><br>
<form action="controller" method="post">
    <input type="hidden" name="command" value="delete_user_by_id">
    User id <input type="text" name="user_id" required><br>
    <input type="submit" value="delete user" style="font-weight: bold; font-size: 18px; padding: 10px;">
</form>
<br><br>
<form action="controller" method="post">
    <input type="hidden" name="command" value="show_users">
    <input type="submit" value="show users" style="font-weight: bold; font-size: 18px; padding: 10px;">
</form>
<br><br>
<form action="controller" method="post">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="Logout" style="font-weight: bold; font-size: 18px; padding: 10px;">
</form><br>

    Для заполнения формы заявки перейдите на <a href="pages/form_notice.jsp">страницу заявки</a>

</body>
</html>
