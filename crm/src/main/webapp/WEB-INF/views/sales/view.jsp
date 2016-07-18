<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>销售机会查看测试</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/plugins/Font-Awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.css">

    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
    <link rel="stylesheet" href="/static/plugins/datatables/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="/static/plugins/webuploader/webuploader.css">

</head>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@include file="../include/header.jsp" %>
    <%--<%@include file="../include/leftSide.jsp" %>--%>
    <jsp:include page="../include/leftSide.jsp">
        <jsp:param name="menu" value="sales"/>
    </jsp:include>

    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                客户关系管理
                <small>销售机会信息</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/sales" style="color: red"><i class="icon-arrow-left"></i>回到列表</a></li>
                <li class="active">${sales.name}</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">

            <div class="box box-primary">
                <div class="box-header" style="text-align: center;color: mediumvioletred">
                    <h3 class="box-title " >${sales.name}</h3>
                    <span class="box-tools ">
                         <button class="btn btn-box-tool" data-widget="collapse"><i class="icon-zoom-in"></i></button>
                    </span>
                    <shiro:hasRole name="管理员">
                        <div class="box-tools">
                            <button class="Btn btn-danger">删除</button>
                        </div>
                    </shiro:hasRole>
                </div>

                <div class="box-body">
                    <table class="table">
                        <tr>
                            <th style="width: 160px;color:blue">机会名称:</th>
                            <th style="color: red">${sales.name}</th>
                        </tr>
                        <tr>
                            <th>关联客户:</th>
                            <th>${sales.customername}</th>
                        </tr>
                        <tr>
                            <th>价值:</th>
                            <th>￥${sales.price}</th>
                        </tr>
                        <tr>
                            <th>当前进度:</th>
                            <th style="color: green">${sales.progress}&nbsp;&nbsp;&nbsp;<a href="javascript:;" id="editProgress">修改</a></th>
                        </tr>
                        <tr>
                            <th>最后跟进时间：</th>
                            <c:choose>
                                <c:when test="${not empty sales.lasttime}">
                                    <th>${sales.lasttime}</th>
                                </c:when>
                                <c:otherwise>
                                    <th>无</th>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </table>
                </div>
            </div>


            <div class="row">
                <div class="col-md-5">
                    <div class="box box-info">
                        <div class="box-header with-border" style="text-align: center;color: mediumvioletred">
                            <div class="box-title" ><i class="icon-th-list"></i>跟进记录</div>
                            <%--收缩框--%>
                            <div class="box-tools pull-right">
                                <button class="btn btn-box-tool" data-widget="collapse"><i class="icon-zoom-in"></i></button>
                                <button class="btn btn-success btn-xs" id="newLogBtn">新增记录</button>
                            </div>
                        </div>

                        <div class="box-body">
                            <ul class="timeline">
                                <c:forEach items="${salesLogList}" var="log">
                                    <li>
                                        <c:choose>
                                            <c:when test="${log.type == 'auto'}">
                                                <i class="fa icon-undo bg-red"></i>
                                            </c:when>
                                            <c:otherwise>
                                                <i class="fa icon-edit bg-green"></i>
                                            </c:otherwise>
                                        </c:choose>

                                        <div class="timeline-item">
                                            <span class="time"><i class="fa icon-time"></i> <span class="timeago" title="${log.createtime}"></span></span>
                                            <h3 class="timeline-header no-border">
                                                    ${log.context}
                                            </h3>
                                        </div>
                                    </li>
                                </c:forEach>
                                <li>
                                    <i class="fa fa-clock-o bg-gray"></i>
                                </li>
                            </ul>
                        </div>

                    </div>
                </div>
                <div class="col-md-4">
                    <div class="box box-info collapsed-box">
                        <div class="box-header with-border" style="text-align: center;color: mediumvioletred">
                            <div class="box-title" ><i class="icon-bell"></i>相关资料</div>
                            <%--收缩框--%>
                            <div class="box-tools pull-right">
                                <button class="btn btn-box-tool" data-widget="collapse"><i class="icon-zoom-in"></i></button>
                            </div>
                        </div>
                        <div class="box-body">
                            <h6>暂无项目</h6>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <%--collapsed-box加则是默认收缩状态--%>
                    <div class="box box-info collapsed-box" >
                        <div class="box-header with-border" style="text-align: center;color: mediumvioletred">
                            <div class="box-title" ><i class=" icon-zoom-in"></i>待办任务</div>
                            <div class="box-tools pull-right">
                                <button class="btn btn-box-tool" data-widget="collapse"><i class="icon-zoom-in"></i></button>
                            </div>
                        </div>
                        <div class="box-body" style="text-align: center">
                            <div>暂无</div>
                        </div>
                    </div>
                </div>


            </div>
        </section>
    </div>
</div>

<!-- Modal(新增跟进记录) -->
<div class="modal fade" id="newLogModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="text-align: center;color: red">新增跟进记录</h4>
            </div>
            <div class="modal-body">
                <form id="newLogForm">
                    <div class="form-group">
                        <label>账号</label>
                        <input class="form-control" type="text" name="username">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="saveBtn">保存</button>
            </div>
        </div>
    </div>
</div>



<script src="/static/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/plugins/datatables/js/jquery.dataTables.min.js"></script>
<script src="/static/plugins/datatables/js/dataTables.bootstrap.min.js"></script>
<script src="/static/plugins/moment/moment.js"></script>
<script src="/static/plugins/webuploader/webuploader.min.js"></script>
<script src="/static/plugins/validata/jquery.validate.min.js"></script>


</body>
</html>
