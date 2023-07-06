<%--
  Created by IntelliJ IDEA.
  User: tdoro
  Date: 27.06.2023
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error page 500</title>
</head>
<body>
The requested URI was: ${pageContext.errorData.requestURI}<br/><br/>
The status of the error is : ${pageContext.errorData.statusCode}<br/><br/>
Servlet name : ${pageContext.errorData.getServletName} <br>
Exception : ${pageContext.exception} <br> <br> <br><br>
Message from exception : ${pageContext.exception.message}
</body>
</html>
