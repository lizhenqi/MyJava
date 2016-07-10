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
    <title>密码修改测试</title>
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

    <%@include file="../include/header.jsp"%>
    <%@include file="../include/leftSide.jsp"%>


    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                客户关系系统管理
                <small>修改密码</small>
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">

            <div class="col-xs-3" style="margin-left: 30%">
                <form method="post" id="changePasswordForm">
                    <div class="box-body">
                        <div class="form-group">
                            <label >旧密码</label>
                            <input type="password" class="form-control"  placeholder="请输入旧密码" name="oldPassword">
                        </div>
                        <div class="form-group">
                            <label >新密码</label>
                            <input type="password" class="form-control"  placeholder="请输入新密码" name="newPassword" id="idnewPassword">
                        </div>
                        <div class="form-group">
                            <label >确认密码</label>
                            <input type="password" class="form-control"  placeholder="请确认密码" name="replyPassword">
                        </div>
                    </div>
                    <!-- /.box-body -->

                    <div class="box-footer">
                        <button id="saveBtn" class="btn btn-primary pull-right">Submit</button>
                    </div>
                </form>
            </div>

        </section>

    </div>
</div>
<script src="/static/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<script src="/static/dist/js/app.min.js"></script>
<script src="/static/plugins/validata/jquery.validate.min.js"></script>


<script>


    $("#saveBtn").click(function(){
        $("#changePasswordForm").submit();
    });

//    验证
    $("#changePasswordForm").validate({
        errorClass:"text-danger",
        errorElement:"span",
        rules:{
            oldPassword:{
                required:true,
                remote:"/user/validate/password"
//                验证旧密码
            },newPassword:{
                required:true,
                rangelength:[6,18]
            },replyPassword:{
                required:true,
                rangelength:[6,18],
                equalTo:"#idnewPassword"
//                注意是：#id
            }
        },
        messages:{
            oldPassword:{
                required:"请输入旧密码",
                remote:"旧密码错误"
            },newPassword:{
                required:"请输入新密码",
                rangelength:"密码长度6~18位"
            },replyPassword:{
                required:"请确认密码",
                rangelength:"密码长度6~18位",
                equalTo:"两次密码不一致"
            }
        },
        submitHandler:function(form){
            var password=$("#idnewPassword").val();
            $.post("/user/password",{"password":password})
                    .done(function(data){
                        if(data=="success"){
                            alert("密码修改成功，请重新登录！");
                            window.location.href="/";
                        }
                    })
                    .fail(function(){
                        alert("修改密码异常！")
                    });
        }
    });


</script>

</body>
</html>
