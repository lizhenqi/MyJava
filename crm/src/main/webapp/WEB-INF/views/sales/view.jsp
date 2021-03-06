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
    <link rel="stylesheet" href="/static/plugins/simditor/styles/simditor.css">
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
                            <button class="Btn btn-danger" id="delSales">删除</button>
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
                    <div class="box box-info ">
                        <div class="box-header with-border" style="text-align: center;color: mediumvioletred">
                            <div class="box-title" ><i class=" icon-zoom-in"></i>相关资料</div>
                            <%--收缩框--%>
                            <div class="box-tools pull-right" >
                                <button class="btn btn-box-tool" data-widget="collapse"><i class="icon-zoom-in"></i></button>
                                <span class="btn-xs " id="uploadBtn"><span class="text"><i class="icon-cloud-upload">上传文档</i></span></span>
                            </div>
                        </div>
                        <div class="box-body">
                                <%--文档部分--%>
                                <div class="box-body">
                                    <table class="table" id="salesFileTable">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>名称</th>
                                            <th>大小</th>
                                            <th>机会ID</th>
                                            <th>创建时间</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${salesFiles}" var="file">
                                            <tr>
                                                <td>${file.id}</td>
                                                <td><a href="/file/download/${file.id}">${file.name}</a></td>
                                                <td>${file.size}</td>
                                                <td>${file.salesid}</td>
                                                <td><fmt:formatDate value="${file.createtime}"
                                                 pattern="y-MM-dd HH:mm "></fmt:formatDate></td>
                                            </tr>
                                        </c:forEach>

                                        </tbody>
                                    </table>
                            </div>

                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <%--collapsed-box加则是默认收缩状态--%>
                    <div class="box box-info collapsed-box" >
                        <div class="box-header with-border" style="text-align: center;color: mediumvioletred">
                            <div class="box-title" ><i class="icon-bell"></i>待办任务</div>
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
                <form id="newLogForm" action="/sales/log/new" method="post">
                    <input type="hidden" name="salesid" value="${sales.id}">
                    <div class="form-group">
                        <textarea name="context" id="context"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="saveLogBtn">保存</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal(更改进度记录) -->
<div class="modal fade" id="progressModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="text-align: center;color: red">修改进度</h4>
            </div>
            <div class="modal-body">
                <form id="progressForm" action="/sales/progress/edit" method="post">
                    <input type="hidden" name="id" value="${sales.id}">
                    <div class="form-group">
                        <label>当前进度</label>
                        <select name="progress" class="form-control">
                            <option value="初次接触">初次接触</option>
                            <option value="确认意向">确认意向</option>
                            <option value="提供合同">提供合同</option>
                            <option value="完成交易">完成交易</option>
                            <option value="交易搁置">交易搁置</option>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="saveProgressBtn">保存</button>
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
<script src="/static/plugins/timeago/timeago.js"></script>
<script src="/static/plugins/timeago/timeago_zh_cn.js"></script>
<script src="/static/plugins/simditor/scripts/module.min.js"></script>
<script src="/static/plugins/simditor/scripts/hotkeys.min.js"></script>
<script src="/static/plugins/simditor/scripts/uploader.min.js"></script>
<script src="/static/plugins/simditor/scripts/simditor.min.js"></script>


<script>
    $(function(){

        //相对时间
        $(".timeago").timeago();


        //在线编辑器
        var edit = new Simditor({
            textarea:$("#context"),
            placeholder: '请新增跟进记录...',
            toolbar:false
        });

//新增跟进记录
        $("#newLogBtn").click(function(){
            $("#newLogModal").modal({
                show:true,
                backdrop:'static',
                keyboard:false
            });
        });
//提交
        $("#saveLogBtn").click(function(){
            if(edit.getValue()) {
                $("#newLogForm").submit();
            } else {
                edit.focus();
            }
        });

        //修改进度
        $("#editProgress").click(function(){
            $("#progressModal").modal({
                show:true,
                backdrop:'static',
                keyboard:false
            });
        });
        $("#saveProgressBtn").click(function(){
            $("#progressForm").submit();
        });

//        删除机会

        $(document).delegate("#delSales","click",function(){
            if(confirm("确认删除该机会？")){
                var id=${sales.id};
                $.get("/sales/del/"+id).done(function(data){
                    if(data=="success"){
                        alert("删除成功");
                        window.location.href="/sales";
                    }
                }).fail(function(){
                    alert("删除异常！")
                });
            }
        });


//        文件上传
        var upload=WebUploader.create({
            swf:"/static/plugins/webuploader/Upload.swf",
            pick:"#uploadBtn",
            server:"/sales/file/upload",
            fileVal:"file",
            formData:{'salesid':"${sales.id}"},
            auto:true
        });
//        开始
        upload.on("startUpload",function(){
            $("#uploadBtn .text").html("<i class='icon-spinner icon-spin'>上传中...</i>");
        });
        upload.on("uploadSuccess",function(file,data){
            if(data._raw=="success"){
                window.history.go(0);
            }
        });
        upload.on("uploadError",function(file){
            alert("文件上传失败");
        });
        upload.on("uploadComplete",function(file){
            $("#uploadBtn .text").html("<i class='icon-cloud-upload'>上传文档</i>");
        })







    });


</script>
</body>
</html>
