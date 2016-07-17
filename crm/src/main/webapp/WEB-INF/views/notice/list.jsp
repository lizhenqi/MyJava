<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>发布公告测试</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/plugins/Font-Awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.css">

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

            <c:if test="${not empty message}">
                <div class="alert alert-success  " style="text-align: center">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong>${message}</strong>
                </div>
            </c:if>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box box-primary">
                <div class="box box-header">

                    <div class="box-title">公告管理</div>
                    <%--员工能看公告，管理员和经理才能发公告--%>
                    <shiro:hasAnyRoles name="管理员,经理">
                        <a href="/notice/new" class="btn btn-xs btn-success pull-right"><i
                                class="icon-edit">发布公告</i></a>
                    </shiro:hasAnyRoles>
                </div>
                <div class="box-body">
                    <table class="table" id="noticeTable">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>标题</th>
                            <th>发布者</th>
                            <th>发布时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
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

<script>
    $(function () {
        var dataTable = $("#noticeTable").DataTable({
            "ajax": "/notice/list/show",
            "lengthMenu": [5, 10, 15],
            "serverSide": true,
            "autowidth": false,

            "columns": [
                {"data": "id"},
                {"data": function(row){
                    return "<a href='/notice/context/"+row.id+"'>"+row.title+"</a>";
                }},



                {"data": "realname"},
                {"data":function(row){
                    var time=moment(row.createtime);
                    return time.format("YYYY-MM-DD HH:mm")
                }},
                {"data": function(row){
                    return "<a javascript:; class='del' rel='"+row.id+"'>删除</a>"
                }}
            ],

            "language": {
                "search": "搜索",//
                searchPlaceholder: "标题或发布者...",
                "zeroRecords": "没有查询到记录！",
                "infoEmpty": "0条记录！",//这个和上面的是在一个界面显示

                "loadingRecords": "加载中...",
                "processing": "处理中...",

                "info": "显示从 _START_ 到 _END_ 总共 _TOTAL_ 条记录",
                "lengthMenu": "选择显示 _MENU_ 条记录",//右上角选择显示记录数
                "infoFiltered": "(从 _MAX_ 条数据过滤)",//查询时候显示从多少数据中查询的

                "paginate": {
                    "first": "首页",
                    "last": "末页",
                    "next": "下一页",
                    "previous": "上一页"
                }
            }
        });

        $(document).delegate(".del","click",function(){
            if(confirm("确认删除？")){
                var id=$(this).attr("rel");
                $.get("/notice/del/"+id).done(function(data){
                    if(data=="success"){
                        alert("删除成功！");
                        dataTable.ajax.reload();
                    }
                }).fail(function(){
                    alert("删除异常！");
                });
            }
        })
    });
</script>
</body>
</html>
