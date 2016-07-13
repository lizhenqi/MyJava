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
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">

    <link rel="stylesheet" href="/static/dist/css/skins/skin-blue.min.css">
    <link rel="stylesheet" href="/static/plugins/datatables/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="/static/plugins/simditor/styles/simditor.css">


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
                <div class="box box-header" style="text-align: center;color: red">
                    <div class="box-title">发布公告</div>
                </div>
                <div class="box-body">
                    <form method="POST" id="newForm">
                        <div class="form-group">
                            <label>标题</label>
                            <input type="text" class="form-control" name="title" id="title" placeholder="输入标题...">
                        </div>
                        <div class="form-group">
                            <label>公告正文</label>
                            <textarea rows="10" class="form-control" name="context" id="context"
                                      placeholder="请输入公告..."></textarea>
                            <%--textarea这个框体是可以自动调整的。这个标签题中间不能有空格和换行等等--%>
                        </div>
                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn btn-primary pull-right" id="saveBtn">发布</button>
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
<script src="/static/plugins/simditor/scripts/module.min.js"></script>
<script src="/static/plugins/simditor/scripts/hotkeys.min.js"></script>
<script src="/static/plugins/simditor/scripts/uploader.min.js"></script>
<script src="/static/plugins/simditor/scripts/simditor.min.js"></script>

<script>
    $(function () {

//       simditor编辑器
        var editor = new Simditor({
            textarea: $('#context'),
            placeholder:"请输入公告",
//           uplpad要求有一下内容，前两个是必须的
            upload:{
                url:"/notice/img/upload",
                fileKey:"file"//(K大写)注意file这个名字要和接收的：MultipartFile uploadFile名字一样。
//                connectionCount:10,
//                leaveConfirm:"正在上传，确定离开？"
            }

        });


        $("#saveBtn").click(function () {
            if (!$("#title").val()) {
                $("#title").focus();
                return;
//               必须输入标题否则光标就一直定位在那。
            }

            if (!$("#context").val()) {
                $("#context").focus();
                return;
//               必须输入正文否则光标就一直定位在那。
            }
            $("#newForm").submit();
        });
    });
</script>

</body>
</html>
