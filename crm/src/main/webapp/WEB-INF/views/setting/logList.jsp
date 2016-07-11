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
    <title>日志测试</title>
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
    <link rel="stylesheet" href="/static/plugins/datatables/css/dataTables.bootstrap.css">

</head>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@include file="../include/header.jsp"%>
    <%@include file="../include/leftSide.jsp"%>


    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                客户关系管理
                <small >日志列表</small>
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="box box-primary">
                <div class="box-header">
                    <div class="box-title">
                        <h4 style="text-align: center">日志列表</h4>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="logTable">
                        <thead>
                        <tr>
                            <td>ID</td>
                            <td>登录时间</td>
                            <td>登录IP</td>
                            <td>账号</td>
                            <td>真实姓名</td>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
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
<script src="/static/plugins/datatables/js/dataTables.bootstrap.js"></script>

<script>
    $(function(){
       var dataTable=$("#logTable").DataTable({


           "lengthMenu": [5, 10, 15],//定义每页显示条数（可选）
           "serverSide": true,//表示所有的操作都在服务端进行
           "searching": false,
           "ajax":"/user/log/list",
           "autowidth":false,

           "columns": [//配置控制器返回的JSON中data属性中数据key和表格列的对应关系
               {"data": "id"},
               {"data": "logintime"},
               {"data": "loginip"},
               {"data": "user.username"},
               {"data": "user.realname"}
           ],
           "language": {

               "loadingRecords": "加载中...",
               "processing": "处理中...",
               "lengthMenu": "选择显示 _MENU_ 条记录",

               "info": "显示从 _START_ 到 _END_ 总共 _TOTAL_ 条记录",

               "paginate": {
                   "first": "首页",
                   "last": "末页",
                   "next": "下一页",
                   "previous": "上一页"
               }

           }
       });
    });
</script>


</body>
</html>
