<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/2/3
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h1><%= session.getAttribute("userName")%></h1>
  <h1><%= session.getId()%></h1>
</body>
</html>
