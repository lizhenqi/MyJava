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
    <title>用户列表测试</title>
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
                <small>员工列表</small>
            </h1>
        </section>
        <!-- Main content -->
        <section class="content">

            <div class="box box-primary">
                <div class="box box-header">
                    <div class="box-title " style="margin-left: 40%;color:red">员工管理</div>
                    <a href="javascript:;" class="btn btn-xs btn-success pull-right" id="newBtn"><i class="icon-plus-sign-alt ">新增</i></a>
                </div>

                <div class="box-body">
                    <table class="table" id="userTable">
                        <thead>
                            <tr>
                               <th>ID</th>
                               <th>账号</th>
                               <th>真实姓名</th>
                               <th>微信号</th>
                               <th>角色</th>
                               <th>状态</th>
                               <th>创建时间</th>
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

<!-- Modal -->
<div class="modal fade" id="newModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">新增员工</h4>
            </div>
            <div class="modal-body">
                <form id="newForm">
                    <div class="form-group">
                        <label>账号</label>
                        <input class="form-control" type="text" name="username">
                    </div><div class="form-group">
                        <label>真实姓名</label>
                        <input class="form-control" type="text" name="realname">
                    </div><div class="form-group">
                        <label>密码</label>
                        <input class="form-control" type="password" name="password">
                    </div><div class="form-group">
                        <label>微信号</label>
                        <input class="form-control" type="text" name="weixin">
                    </div><div class="form-group">
                        <label>角色</label>
                        <input class="form-control" type="text" name="roleid">
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
<script src="/static/plugins/datatables/js/dataTables.bootstrap.js"></script>
<script src="/static/plugins/moment/moment.js"></script>
<script src="/static/plugins/validata/jquery.validate.min.js"></script>

<script>
    $(function(){

//        显示列表
        $("#userTable").DataTable({
            "ajax":"/admin/user/list",
            "lengthMenu":[5,10,15],
            "serverSide":true,
            "autowidth":false,

            "columns":[
                {"data":"id"},
                {"data":"username"},
                {"data":"realname"},
                {"data":"weixin"},
                {"data":"role.rolename"},
                {"data":function(row){
                    if(row.enable){
                        return"<span class='label label-success'>正常</span>"
                    }else{
                        return"<span class='label label-danger'>禁用</span>"
                    }
                }},
                {"data":function(row){
                    var timestamp=row.createtime;
                    var day=moment(timestamp);
                    return day.format("YYYY-MM-DD HH:mm:ss")
                }},
                {"data":function(row){
                    return "";
                }}
            ],
            "language":{
                "search": "搜索",//
                searchPlaceholder: "请输入账号或真实姓名...",
                "zeroRecords": "没有查询到记录！",
                "infoEmpty": "0条记录！",//这个和上面的是在一个界面显示

                "loadingRecords": "加载中...",
                "processing": "处理中...",

                "info": "显示从 _START_ 到 _END_ 总共 _TOTAL_ 条记录",
                "lengthMenu": "选择显示 _MENU_ 条记录",//右上角选择显示记录数
                "infoFiltered": "(从 _MAX_ 条数据过滤)",//查询时候显示从多少数据中查询的

                "paginate":{
                    "first":"首页",
                    "last":"末页",
                    "next":"下一页",
                    "previous":"上一页"
                }
            }
        });

//        新增员工
        $("#newBtn").click(function(){
            $("#newForm")[0].reset();
            $("#newModal").modal({
                show:true,
                backdrop:"static",
                keyboard:false
            });
        });
        $("#newForm").validate({
            errorClass:"text-danger",
            errorElement:"span",
            rules:{
                username:{
                    required:true,
                    rangelength:[2,18]
                },realname:{
                    required:true
                },password:{
                    required:true,
                    rangelength:[6,18]
                },weixin:{
                    required:true
                }
            },
            messages:{
                username:{
                    required:"请输入用户名",
                    rangelength:"用户名长度2~18位"
                },realname:{
                    required:"请输入真实姓名"
                },password:{
                    required:"请输入密码",
                    rangelength:"密码长度6~18位"
                },weixin:{
                    required:"请输入微信"
                }
            },
            submitHandler:function(form){
                $.post("/admin/user/new",$(form).serialize())
                        .done(function(data){
                            if(data=="success"){
                                alert("新增成功");
                                window.location.href="/admin/user";
                            }
                        })
                        .fail(function(){
                            alert("新增异常！")
                        });
            }
        });
        $("#saveBtn").click(function(){
            $("#newForm").submit();
        });
//

    });
</script>


</body>
</html>
