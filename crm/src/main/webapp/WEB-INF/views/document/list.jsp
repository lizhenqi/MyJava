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
    <title>文档管理测试</title>
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
        <jsp:param name="menu" value="doc"/>
    </jsp:include>

    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                客户关系管理
                <small>文档列表</small>
            </h1>

        </section>
        <!-- Main content -->
        <section class="content">

            <div class="box box-primary">
                <div class="box box-header">
                    <h3 class="box-title ">文档管理-${fid}</h3>
                    <div class="box-tools">
                        <%--这个"box-tools"使其靠右边--%>
                        <button class="btn btn-xs btn-success " id="newDir"><i class=" icon-folder-open-alt"></i>新建文件夹</button>
                        <span id="uploadBtn"><span class="text"><i class="icon-cloud-upload">上传文档</i></span></span>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="userTable">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>名称</th>
                            <th>大小</th>
                            <th>创建人</th>
                            <th>创建时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:if test="${empty documentList}">
                            <tr>
                                <td>没有数据</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${documentList}" var="doc">
                            <tr>
                                <c:choose>
                                    <c:when test="${doc.type=='dir'}">
                                        <td><i class="icon-folder-open-alt"></i></td>
                                        <td><a href="/doc?fid=${doc.id}">${doc.name}</a></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><i class=" icon-file"></i></td>
                                        <td><a href="/doc/download/${doc.id}">${doc.name}</a></td>
                                    </c:otherwise>
                                </c:choose>

                                <td>${doc.size}</td>
                                <td>${doc.createuser}</td>
                                <td><fmt:formatDate value="${doc.createtime}"
                                                    pattern="y-MM-dd HH:mm "></fmt:formatDate></td>
                                <td>操作</td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </section>
    </div>
</div>

<!-- Modal(新增文件夹) -->
<div class="modal fade" id="dirModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="text-align: center;color: red">新增文件夹</h4>
            </div>
            <div class="modal-body">
                <form id="newDirForm" action="/doc/dir/new" method="post">
                    <input type="hidden" value="${fid}" name="fid">
                    <div class="form-group">
                        <label>请输入文件夹名称</label>
                        <input class="form-control" type="text" name="name" id="dirname" autofocus>
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

<script>
    $(function () {

        //文件上传
        var uploader = WebUploader.create({
            // swf文件路径
            swf: "/static/plugins/webuploader/Uploader.swf",

            // 文件接收服务端。
            server: '/doc/file/upload',

            //文件上传时候上传的参数
            formData: {"fid": "${fid}"},

            //  [默认值：'file'] 设置文件上传域的name。
            fileVal:"file",

            //[默认值：false] 设置为 true 后，不需要手动调用上传，有文件选择即开始上传。
            auto: true,

            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick:"#uploadBtn",

            // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
//            resize: false
        });

        //文件上传时的状态
//开始上传时的动作
        uploader.on("startUpload",function(){
            $("#uploadBtn .text").html("<i class='icon-spinner icon-spin'></i>上传中...");
//            上面是个选择器空格点text（这样可以留着式样仅仅更改文字）
        });

        uploader.on('uploadSuccess', function (file,data) {
            //上传成功会有返回值data._raw(固定response)

            if(data._raw=="success"){

                window.history.go(0);//表示后退和前进一次
//                window.location.href="/doc";这个当刷新用会有页面刷新的感觉。
            }
        });

        uploader.on('uploadError', function (file) {
            alert("上传失败！");
        });

        uploader.on('uploadComplete', function (file) {
            $("#uploadBtn .text").html("<i class='icon-cloud-upload'>上传文档</i>");
        });


//       新建文件夹
        $("#newDir").click(function () {

            $("#dirModal").modal({
                show: true,
                backdrop: "static",
                keyboard: false
            });
        });
        $("#saveBtn").click(function () {
            if (!$("#dirname").val()) {
                $("#dirname").focus();
                return;
            }
            $("#newDirForm").submit();
        });

    });


</script>


</body>
</html>
