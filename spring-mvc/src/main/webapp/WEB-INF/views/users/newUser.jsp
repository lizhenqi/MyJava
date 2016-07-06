<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/4
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>newUser测试</h1>
    <form method="post" action="/users/newUser">
        <input type="text" placeholder="请输入username" name="username">
        <input type="text" placeholder="请输入possword" name="password">
        <input type="text" placeholder="请输入age" name="age">
        <button>newUser</button>
    </form>
</body>
</html>
