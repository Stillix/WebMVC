<%@ page import="com.example.webmvc.entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: tdoro
  Date: 12.07.2023
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <th>User ID</th>
        <th>User Name</th>
        <th>User Login</th>
    </tr>
    <%
        List<User> userList = (List<User>) request.getAttribute("userList");
        for (User user : userList) {
    %>
    <tr>
        <td><%= user.getUserId() %></td>
        <td><%= user.getUserName() %></td>
        <td><%= user.getUserLogin() %></td>
    </tr>
    <% } %>
</table>
</body>
</html>
