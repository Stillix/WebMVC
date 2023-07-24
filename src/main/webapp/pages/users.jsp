<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
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
        <p>${message}</p>
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
            <form action="/WebMVC_war_exploded/controller" method="post">
                <input type="hidden" name="userId" value="${userList.userId}">
                <input type="hidden" name="command" value="delete_user">
                <input type="submit" value="delete user" style="font-weight: bold; font-size: 10px; padding: 10px;">
            </form>
        </c:forEach>
    </table>
</form>
</body>
</html>
