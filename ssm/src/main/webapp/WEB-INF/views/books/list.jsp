<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">

</head>
<body>
<div CLASS="container">
    <table class="table">
        <div class="page-header">
            <h2 style="text-align: center;color:red">图书管理</h2>
        </div>
        <c:if test="${not empty message}">
            <div class="alert alert-success alert-dismissible" role="alert">
                <button id="xx" type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>提示：</strong><span>${message}</span>
            </div>
        </c:if>
        <a href="/books/new" class="btn btn-primary">新增图书</a>

        <thead>
        <tr>
            <th>ID</th>
            <th>书籍名称</th>
            <th>价格</th>
            <th>作者</th>
            <th>数量</th>
            <th>类型</th>
            <th>出版商</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${bookList}" var="book">
            <tr>
                <td>${book.id}</td>
                <td>${book.bookname}</td>
                <td>${book.bookprice}</td>
                <td>${book.bookauthor}</td>
                <td>${book.booknum}</td>
                <td>${book.bookType.booktype}</td>
                <td>${book.publisher.pubname}</td>
            </tr>
        </c:forEach>


        </tbody>


    </table>
</div>

</body>
</html>
