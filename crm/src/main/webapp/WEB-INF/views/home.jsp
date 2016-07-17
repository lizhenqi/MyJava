<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>测试</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/plugins/Font-Awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">

    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">


</head>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@include file="include/header.jsp"%>
    <%--<%@include file="include/leftSide.jsp"%>--%>
    <jsp:include page="include/leftSide.jsp">
        <jsp:param name="menu" value="home"/>
    </jsp:include>
    
    
    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                首页
                <small>客户关系管理</small>
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">

        </section>

    </div>
</div>
<script src="/static/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/dist/js/app.min.js"></script>

</body>
</html>
