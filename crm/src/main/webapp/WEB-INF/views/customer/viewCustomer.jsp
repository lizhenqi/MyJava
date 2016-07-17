<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>客户信息测试</title>
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


</head>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@include file="../include/header.jsp" %>
    <%@include file="../include/leftSide.jsp" %>
    <jsp:include page="../include/leftSide.jsp">
        <jsp:param name="menu" value="customer"/>
    </jsp:include>


    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                客户关系管理
                <small>客户详细信息</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/customer" style="color: red"><i class="icon-arrow-left"></i>回到列表</a></li>
                <li class="active">${customer.name}</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box box-primary">
                <div class="box-header" style="text-align: center">
                    <h4 class="box-title">
                        <c:choose>
                            <c:when test="${customer.type=='person'}">
                                <i class="icon-user"></i>
                            </c:when>
                            <c:otherwise>
                                <i class="icon-group"></i>
                            </c:otherwise>
                        </c:choose>
                        ${customer.name}
                    </h4>
                    <%--不是公开的才有公开选项--%>
                    <c:if test="${not empty customer.userid}">
                        <div class="box-tools">
                            <button class="btn btn-danger btn-xs" id="openBtn">公开改客户</button>
                            <button class="btn btn-danger btn-xs" id="moveBtn">转移改客户</button>
                        </div>
                    </c:if>

                </div>
                <div class="box-body">
                    <table class="table">
                        <tr>
                            <th style="width: 160px;color:blue">姓名:</th>
                            <th style="color: red">${customer.name}</th>
                        </tr>
                        <tr>
                            <th>联系电话:</th>
                            <th style="color: red">${customer.tel}</th>
                        </tr>
                        </tr>
                        <tr>
                            <th>地址:</th>
                            <th style="color: red">${customer.address}</th>
                        </tr>
                        <tr>
                            <th>等级:</th>
                            <th>${customer.level}</th>
                        </tr>
                        <tr>
                            <th>微信:</th>
                            <th>${customer.weixin}</th>
                        </tr>
                        <tr>
                            <th>电子邮件：</th>
                            <th>${customer.email}</th>
                        </tr>
                        <c:if test="${ not empty customer.companyname}">
                            <tr>
                                <th>所属公司：</th>
                                <th style="color: red"><a
                                        href="/customer/view/${customer.companyID}">${customer.companyname}</a></th>
                            </tr>
                        </c:if>
                        <c:if test="${not empty customerList}">
                            <tr>
                                <th>公司成员列表:</th>
                                <td >
                                    <c:forEach items="${customerList}" var="msg">
                                        <a href="/customer/view/${msg.id}">${msg.name};</a>
                                    </c:forEach>
                                </td>
                            </tr>
                        </c:if>

                    </table>
                </div>

            </div>

            <div class="row">
                <div class="col-md-4">
                    <div class="box box-info">
                        <div class="box-header with-border" style="text-align: center;color: mediumvioletred">
                            <div class="box-title" ><i class="icon-th-list"></i>项目列表</div>
                        </div>
                        <div class="box-body">
                            <h6>暂无项目</h6>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="box box-info">
                        <div class="box-header with-border" style="text-align: center;color: mediumvioletred">
                            <div class="box-title" ><i class="icon-bell"></i>待办事项</div>
                        </div>
                        <div class="box-body">
                            <h6>暂无项目</h6>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="box box-info">
                        <div class="box-header with-border" style="text-align: center;color: mediumvioletred">
                            <div class="box-title" ><i class=" icon-zoom-in"></i>生成电子名片</div>
                        </div>
                        <div class="box-body" style="text-align: center">
                            <img src="/customer/QRcode/${customer.id}.jpg">
                            <%--必须有后缀--%>
                        </div>
                    </div>
                </div>
            </div>
        </section>

    </div>
</div>

<!-- Modal(转移) -->
<div class="modal fade" id="moveModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="text-align: center;color: red">请选择转移人员</h4>
            </div>
            <div class="modal-body">
                <form id="moveForm" method="post" action="/customer/move/">

                    <div class="form-group">
                        <input type="hidden" name="id" value="${customer.id}">
                        <label>请选择转入员工</label>
                        <select class="form-control" name="userid" id="move_name">
                            <c:forEach items="${userList}" var="user">
                                <option value="${user.id}">${user.username}——>>${user.realname}</option>
                            </c:forEach>
                        </select>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="editBtn">确认更改</button>
            </div>
        </div>
    </div>
</div>

<script src="/static/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/dist/js/app.min.js"></script>

<script>
    $(function(){

        $("#openBtn").click(function(){
            if(confirm("确认公开？")){
                var id=${customer.id};
                window.location.href = "/customer/open/"+id;
            }
        });
        $("#moveBtn").click(function(){
            if(confirm("确认转移？")){
                var id=${customer.id};

                $("#moveModal").modal({
                    show:true,
                    keyboard:false,
                    backdrop:"static"
                });
            }
        });
        $("#editBtn").click(function () {
            $("#moveForm").submit();
        });



    });
</script>
</body>
</html>
