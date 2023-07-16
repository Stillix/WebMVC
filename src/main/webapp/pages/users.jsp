<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

        <c:forEach items="${userList}" var="userList">
            <tr>
                <td>${userList.userId}</td>
                <td>${userList.userLogin}</td>
                <td>${userList.userName}</td>
                <td>${userList.userSurname}</td>
                <td>${userList.userPhone}</td>
                <td>${userList.userEmail}</td>
                <td>${userList.userRole}</td>
            </tr>
        </c:forEach>
    </table>
    <p>${message}</p>

</form>
</body>
</html>
