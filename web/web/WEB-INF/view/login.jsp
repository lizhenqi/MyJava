<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">

</head>
<body>
    <div class="container">
        <c:choose>
            <c:when test="${param.err==110}">
                <div class="alert alert-danger">
                    验证码有误！
                </div>
            </c:when>
            <c:when test="${param.err==111}">
                <div class="alert alert-danger">
                    用户名或是密码错误！
                </div>
            </c:when>
        </c:choose>
        <form action="/login" method="post" id="submitForm">
            <div class="form-group">
                <label>账号</label>
                <input type="text" class="form-control" name="username">
            </div><br/>

            <div class="form-group">
                <label>密码</label>
                <input type="password" class="form-control" name="password" id="password">
            </div><br/>

            <div class="form-group">
                <label>验证码</label>
                <input type="text" class="form-control" name="patchca">
                <a id="pic" href="javascript:;"><img id="patch" src="/patchca.jpg" alt="验证码"></a>
            </div>

            <button class="btn btn-primary" type="button" id="submitBtn">登录</button>
        </form>
    </div>
    <script src="/static/js/jquery-2.2.3.min.js"></script>
    <script src="/static/js/core-min.js"></script>
    <script src="/static/js/md5-min.js"></script>
 <script>
     $(function(){
         $("#submitBtn").click(function(){
            var pwd=$("#password").val();
             pwd=CryptoJS.MD5(pwd);
             $("#password").val(pwd);
             $("#submitForm").submit();
             console.log(pwd.toString())

         });
         $("#pic").click(function(){
             console.log("测试");
             $("#patch").removeAttr("src").attr("src","/patchca.jpg?t="+new Date().getTime());
         });
     });
 </script>
</body>
</html>
