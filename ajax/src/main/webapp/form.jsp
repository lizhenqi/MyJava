
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>


<div class="container">
    <div class="row">
        <div class="col-xs-3">
            <form id="regForm">
                <div class="form-group">
                    <label>账号</label>
                    <input class="form-control" type="text" name="username">
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <input class="form-control" type="password" name="password">
                </div>
                <div class="form-group">
                    <label>个人信息</label>
                    <input class="form-control" type="text" name="msg">
                </div>
                <div class="form-group">
                    <button type="button" id="subBtn" class="btn btn-primary" >注册</button>
                    <img  id="img" src="/static/img/send.gif" style="display:none "/>
                </div>

            </form>
        </div>


    </div>

</div>
<script src="/static/js/jquery-2.2.3.min.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
<script>
    $(function(){
        $("#regForm").validate({
            errorElement:"span",
            errorClass:"text-danger",
            rules:{

                username:{
                    required:true,
//                    minlength:5,
                    remote:"/checkUsername"//实际就是远程验证的url
                },
                password:{
                    required:true,
                    rangelength:[6,18]
                },
                msg:{
                    required:true
                }

            },

            messages:{
                username:{
                    required:"请输入账号",
//                    minlength:"最少5位",
                    remote:"改账号已经注册，请登陆"
                },
                password:{
                    required:"请输入密码",
                    rangelength:"请输入6-18位密码"
                },
                msg:{
                    required:"请输入个人信息"
                }
            },
            submitHandler:function(form){
                var $subBtn=$("#subBtn");
                $.ajax({
                    url:"/reg",
                    type:"post",
                    data:$(form).serialize(),
                    beforeSend:function(){
                        $("#subBtn").text("注册中...").attr("disabled","disabled");
                        $("#img").show();
                    },
                    success:function(){
                        alert("注册成功");
                    },
                    error:function(){
                        alert("注册失败");
                    },
                    complete:function(){
                        $("#subBtn").text("注册").removeAttr("disabled");
                        alert("服务结束！");
                    }
                });
            }
        });
        $("#subBtn").click(function () {
            $("#regForm").submit();
        });
    });
</script>
</body>
</html>
