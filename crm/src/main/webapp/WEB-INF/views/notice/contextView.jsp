<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--注意导入的是jsp/jstl.不是直接jstl下的fmt--%>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>公告查看</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/plugins/Font-Awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">

    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
    <link rel="stylesheet" href="/static/plugins/datatables/css/dataTables.bootstrap.min.css">


</head>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@include file="../include/header.jsp" %>
    <%--<%@include file="../include/leftSide.jsp"%>--%>
    <%--可以把上面换成jsp动作。（）--%>
    <jsp:include page="../include/leftSide.jsp">
        <jsp:param name="menu" value="notice"/>
    </jsp:include>


    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                客户关系管理
                <small>公告列表</small>
            </h1>

        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box box-primary">
                <div class="box box-header" style="text-align: center">
                    <%--pattern="yy-MM-dd HH:mm"--%>
                    <div class="box-title">公告:${notice.title}</div><small style="color: red ;margin-left: 100px">作者：${notice.realname}&nbsp;&nbsp;日期：<fmt:formatDate pattern="y-M-d H:m" value="${notice.createtime}" ></fmt:formatDate></small>
                    <%--员工能看公告，管理员和经理才能发公告--%>
                    <shiro:hasAnyRoles name="管理员,经理">
                        <a href="/notice/new" class="btn btn-xs btn-success pull-right"><i
                                class="icon-edit">发布公告</i></a>
                    </shiro:hasAnyRoles>
                </div>
                <div class="box-body">
                    ${notice.context}
                </div>

            </div>
        </section>

    </div>
</div>
<script src="/static/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/plugins/datatables/js/jquery.dataTables.min.js"></script>
<script src="/static/plugins/datatables/js/dataTables.bootstrap.min.js"></script>
<script src="/static/plugins/moment/moment.js"></script>


</body>
</html>
