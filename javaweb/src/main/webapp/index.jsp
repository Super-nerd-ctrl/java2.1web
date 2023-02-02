<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="helloServlet">Hello Servlet</a>
<form action="/login" method="post">
    用户名<input type="text" name="userName">
    密码<input type="password" name="password">
    <button type="submit">登录</button>
</form>
</body>
</html>