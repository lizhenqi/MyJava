<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
<div class="container">

    <div class="col-xs-4">
        <div class="page-header">
            <h2 style="color: greenyellow;text-align: center">新增书籍</h2>
        </div>
        <form action="/books/new" method="post">
            <div class="form-group">
                <label>书籍名称</label>
                <input type="text" name="bookname" class="form-control"/>
            </div>

            <div class="form-group">
                <label>作者</label>
                <input type="text" name="bookauthor" class="form-control"/>
            </div>

            <div class="form-group">
                <label>价格</label>
                <input type="text" name="bookprice" class="form-control"/>
            </div>

            <div class="form-group">
                <label>数量</label>
                <input type="text" name="" class="form-control"/>
            </div>

            <div class="form-group">
                <label>类型</label>
                <select class="form-control" name="typeid">
                    <c:forEach items="${bookTypeList}" var="type">
                        <option value="${type.id}">${type.booktype}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label>出版商</label>
                <select class="form-control" name="pubid">
                    <c:forEach items="${publisherList}" var="pub">
                        <option value="${pub.id}">${pub.pubname}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <button class="btn btn-primary" style="float: right">新增</button>
            </div>

        </form>
    </div>
</div>
</body>
</html>
