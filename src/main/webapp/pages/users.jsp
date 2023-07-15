<%@ page import="com.example.webmvc.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<form action="/WebMVC_war_exploded/controller" method="post">
<input type="hidden" name="command" value="show_users">
<h1>User List</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Login</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Role</th>
        </tr>
        <% for (User user : (List<User>) request.getAttribute("userList")) { %>
        <tr>
            <td><%= user.getUserId() %></td>
            <td><%= user.getUserLogin() %></td>
            <td><%= user.getUserName() %></td>
            <td><%= user.getUserSurname() %></td>
            <td><%= user.getUserPhone() %></td>
            <td><%= user.getUserEmail() %></td>
            <td><%= user.getUserRole() %></td>
        </tr>
        <% } %>
    </table>

</form>
</body>
</html>
