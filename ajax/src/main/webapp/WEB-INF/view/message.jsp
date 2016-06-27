<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">

</head>
<body>
<div class="container">
    <div class="page-header">轮询演示</div>
    <a href="javascript:;" id="js">
        <div class="alert alert-info" STYLE="display: none"></div>
    </a>
    <c:forEach items="${messageList}" var="msg">
        <div class="panel-default">
            <div class="panel-heading">
                    ${msg.id}-->${msg.author}-->${msg.message}
            </div>
            <%--<div class="panel-body">--%>
                    <%--${msg.message}--%>
            <%--</div>--%>
        </div>
    </c:forEach>

</div>

<script src="/static/js/jquery-2.2.3.min.js"></script>


<script>
    $(function(){


        //当前id
        var maxId=${maxId};

        var newData=null;
        $("#js").onclick(function(){
            if(newData){
<c:forEach items="${messageList}" var="msg">
        <div class="panel-default">
            <div class="panel-heading">
                    ${msg.id}-->${msg.author}-->${msg.message}
            </div>
            <%--<div class="panel-body">--%>
                    <%--${msg.message}--%>
            <%--</div>--%>
        </div>
    </c:forEach>
            }

        });

        //从服务器获取数据
        setInterval(function(){
            $.post("/message",{"maxId":maxId},function(data){
                if(data.length>0){
                    newData=data;
                    console.log("测试");
                    $(".alert").text("有"+data.length+"条数据").fadeIn();
                }else{
                    console.log("没有数据");
                }
            });

        },2000);


    });


</script>
</body>
</html>
