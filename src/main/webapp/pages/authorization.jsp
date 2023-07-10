<%--
  Created by IntelliJ IDEA.
  User: tdoro
  Date: 10.07.2023
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
</head>
<body>
<form action="controller">
  <input type="hidden" name="command" value="login"/>
  Login <input type="text" name="login" value=""/> <br>
  Password <input type="password" name="password" value=""/><br>
  <input type="submit" name="submit" value="Ok"/>
  ${login_failed}

</form>
</body>
</html>
