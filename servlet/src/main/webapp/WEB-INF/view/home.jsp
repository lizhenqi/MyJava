<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
<div class="container">

    <div class="panel panel-default" style="text-align: center">

        <div class="panel-heading" >
            <h1>分页测试！！！</h1>
        </div>
        <%--<div class="page-header">--%>
            <%--<h4 style="margin-top: 0px;margin-bottom: 0px"></h4>--%>
        <%--</div>--%>
        <div class="panel-body">
            <table class="table">
                <thead>
                    <tr>
                        <th>影片名称</th>
                        <th>评分</th>
                        <th>导演</th>
                        <th>发行时间</th>
                        <th>上映时间</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${page.items}" var="movie">
                    <tr>
                        <td style="width: 500px">${movie.title}</td>
                        <td>${movie.rate}</td>
                        <td>${movie.daoyan}</td>
                        <td>${movie.releaseyear}</td>
                        <td>${movie.sendtime}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>


        </div>
        <div class="panel-footer" style="text-align:center">

            <%--插件：这一行代替下面所有注释部分--%>
            <ul class="pagination" id="page"></ul>




               <%-- <ul class="pagination" style="margin: 0px">
                    <c:choose>
                        <c:when test="${page.pageNo==1}">
                            <li class="disabled"><a href="javascript:;" >首页</a></li>
                            <li class="disabled"><a href="javascript:;" >上一页</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="/home?p=1" >首页</a></li>
                            <li><a href="/home?p=${page.pageNo-1}" >上一页</a></li>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                        <c:when test="${page.pageNo==page.pageSize}">
                            <li class="disabled"><a href="javascript:;" >下一页</a></li>
                            <li class="disabled"><a href="javascript:;" >末页</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="/home?p=${page.pageNo+1}" >下一页</a></li>
                            <li><a href="/home?p=${page.pageSize}" >末页</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
                --%>
        </div>

    </div>

</div>

<script src="/static/js/jquery-2.2.3.min.js"></script>
<script src="/static/js/jquery.twbsPagination.min.js"></script>
<script>
    $(function(){

        $("#page").twbsPagination({
            totalPages:${page.pageSize},
            visiblePages:10,
            first:"首页",
            prev:"上一页",
            next:"下一页",
            last:"末页",
            href:"?p={{number}}"
        });
    });

</script>


</body>
</html>
