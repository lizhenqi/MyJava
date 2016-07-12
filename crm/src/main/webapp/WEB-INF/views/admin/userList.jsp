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

    <%@include file="../include/header.jsp" %>
    <%@include file="../include/leftSide.jsp" %>


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
                    <a href="javascript:;" class="btn btn-xs btn-success pull-right" id="newBtn"><i
                            class=" icon-plus ">新增</i></a>
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
                            <th style="text-align: center">操作</th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
            </div>
        </section>
    </div>
</div>

<!-- Modal(新增) -->
<div class="modal fade" id="newModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="text-align: center;color: red">新增员工</h4>
            </div>
            <div class="modal-body">
                <form id="newForm">
                    <div class="form-group">
                        <label>账号</label>
                        <input class="form-control" type="text" name="username">
                    </div>
                    <div class="form-group">
                        <label>真实姓名</label>
                        <input class="form-control" type="text" name="realname">
                    </div>
                    <div class="form-group">
                        <label>密码(默认"666666")</label>
                        <input class="form-control" type="password" name="password" value="666666">
                    </div>
                    <div class="form-group">
                        <label>微信号</label>
                        <input class="form-control" type="text" name="weixin">
                    </div>
                    <div class="form-group">
                        <label>角色</label>
                        <select class="form-control" name="roleid">
                            <c:forEach items="${roleList}" var="role">
                                <option value="${role.id}">${role.rolename}</option>
                                <%--上面的value就相当于输入的值--%>
                            </c:forEach>
                        </select>
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

<!-- Modal(编辑) -->
<div class="modal fade" id="editModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="text-align: center;color: red">编辑员工信息</h4>
            </div>
            <div class="modal-body">
                <form id="editForm">
                    <input class="hide" name="id" id="edit_id">
                    <%--账号disabled（一生都不提供更改）--%>
                    <div class="form-group">
                        <label>账号(不能更改)</label>
                        <input class="form-control" type="text" disabled name="username" id="edit_username">
                    </div>
                    <div class="form-group">
                        <label>真实姓名</label>
                        <input class="form-control" type="text" name="realname" id="edit_realname">
                    </div>

                    <%--密码删了，不要和重置密码有功能相似--%>
                    <%--<div class="form-group">--%>
                    <%--<label>密码(默认"666666")</label>--%>
                    <%--<input class="form-control" type="password" name="password" value="666666">--%>
                    <%--</div>--%>
                    <div class="form-group">
                        <label>微信号</label>
                        <input class="form-control" type="text" name="weixin" id="edit_weixin">
                    </div>
                    <div class="form-group">
                        <label>角色</label>
                        <select class="form-control" name="roleid" id="edit_roleid">
                            <c:forEach items="${roleList}" var="role">
                                <option value="${role.id}">${role.rolename}</option>
                                <%--上面的value就相当于输入的值--%>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>状态</label>
                        <select class="form-control" name="enable" id="edit_enable">
                            <option value="true">正常</option>
                            <option value="false">禁用</option>
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
<script src="/static/plugins/datatables/js/jquery.dataTables.min.js"></script>
<script src="/static/plugins/datatables/js/dataTables.bootstrap.js"></script>
<script src="/static/plugins/moment/moment.js"></script>
<script src="/static/plugins/validata/jquery.validate.min.js"></script>

<script>
    $(function () {

//        显示列表
        var dataTable = $("#userTable").DataTable({
            "ajax": "/admin/user/list",
            "lengthMenu": [5, 10, 15],
            "serverSide": true,
            "autowidth": false,

            "columns": [
                {"data": "id"},
                {"data": "username"},
                {"data": "realname"},
                {"data": "weixin"},
                {"data": "role.rolename"},
                {
                    "data": function (row) {
                        if (row.enable) {
                            return "<span class='label label-success'>正常</span>"
                        } else {
                            return "<span class='label label-danger'>禁用</span>"
                        }
                    }
                },
                {
                    "data": function (row) {
                        var timestamp = row.createtime;
                        var day = moment(timestamp);
                        return day.format("YYYY-MM-DD HH:mm:ss")
                    }
                },
                {
                    "data": function (row) {

                        if (row.username == "admin") {
                            return '';//避免超级用户被操作
                        } else {
                            return "<a href='javascript:;' class='setPwd' rel='" + row.id + "'>重置密码</a>&nbsp;&nbsp;&nbsp;&nbsp;" +
                                    "<a href='javascript:;' class='edit'  rel='" + row.id + "'>编辑</a> ";
                        }
                    }
                }
            ],
            "language": {
                "search": "搜索",//
                searchPlaceholder: "请输入账号或真实姓名...",
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

//        新增员工
        $("#newBtn").click(function () {
            $("#newForm")[0].reset();//弹出来的时候清空框内记忆
            $("#newModal").modal({
                show: true,//显示
                backdrop: "static",//一直显示，如果不加，点击旁边就会隐藏
                keyboard: false//表示esc键将不能用于退出
            });
        });
        $("#newForm").validate({
            errorClass: "text-danger",
            errorElement: "span",
            rules: {
                username: {
                    required: true,
                    rangelength: [2, 18],
                    remote: "/admin/user/checkusername"
                }, realname: {
                    required: true
                }, password: {
                    required: true,
                    rangelength: [6, 18]
                }, weixin: {
                    required: true
                }
            },
            messages: {
                username: {
                    required: "请输入用户名",
                    rangelength: "用户名长度2~18位",
                    remote: "该账号已经注册,请重新输入！"
                }, realname: {
                    required: "请输入真实姓名"
                }, password: {
                    required: "请输入密码",
                    rangelength: "密码长度6~18位"
                }, weixin: {
                    required: "请输入微信"
                }
            },
            submitHandler: function (form) {
                $.post("/admin/user/new", $(form).serialize())
                        .done(function (data) {
                            if (data == "success") {
                                alert("新增成功");

                                $("#newModal").modal("hide");
                                dataTable.ajax.reload();//刷新

//                              window.location.href = "/admin/user";//用这一句就相当于上面两句刷新加隐藏（间接）,不过没有动画效果
                            }
                        })
                        .fail(function () {
                            alert("新增异常！");
                            window.location.href = "/admin/user";
                        });
            }
        });
        $("#saveBtn").click(function () {
            $("#newForm").submit();
        });
//  重置密码
        $(document).delegate(".setPwd", "click", function () {
            var id = $(this).attr("rel");
            if (confirm("确定把密码重置为“666666”")) {
                $.post("/admin/user/setPwd", {"id": id})
                        .done(function (data) {
                            if (data == "success") {
                                alert("重置密码成功！");
                                dataTable.ajax.reload();
                            }
                        })
                        .fail(function () {
                            alert("重置密码异常！");
                            dataTable.ajax.reload();
                        })
            }
        });

        //     编辑(编辑时候应该是账号(一生不变)和密码不能编辑。否则会和重置密码功能一样，密码修改就在个人设置里面一个地方有就行了)
        $(document).delegate(".edit", "click", function () {
            var id = $(this).attr("rel");
            $.get("/admin/user/" + id + ".json")
                    .done(function (result) {
                        if (result.state == "success") {
//                            在模态框出来之前进行填充
                            $("#edit_id").val(result.data.id);
                            $("#edit_username").val(result.data.username);
                            $("#edit_realname").val(result.data.realname);
                            $("#edit_weixin").val(result.data.weixin);
                            $("#edit_roleid").val(result.data.roleid);
                            $("#edit_enable").val(result.data.enable.toString());
//              这个要和上面value=“1”或是“0”一致，如果要是写true 或false。这里就要result.data.enable.toString()


                            $("#editModal").modal({
                                show: true,
                                backdrop: "static",
                                keyboard: false
                            });

                        } else {
                            alert(result.message);
                        }
                    })
                    .fail(function () {
                        alert("编辑异常！")
                    })
        });
        $("#editForm").validate({
            errorClass: "text-danger",
            errorElement: "span",
            rules: {
                realname: {
                    required: true
                }, weixin: {
                    required: true
                }
            },
            messages: {
                realname: {
                    required: "请输入真实姓名"
                }, weixin: {
                    required: "请输入微信"
                }
            },
            submitHandler: function (form) {
                $.post("/admin/user/edit", $(form).serialize())
                        .done(function (data) {
                            if (data == "success") {
                                alert("修改成功");

                                $("#editModal").modal("hide");
                                dataTable.ajax.reload();//刷新

//                              window.location.href = "/admin/user";//用这一句就相当于上面两句刷新加隐藏（间接）,不过没有动画效果
                            }
                        })
                        .fail(function () {
                            alert("修改异常！");
                            window.location.href = "/admin/user";
                        });
            }
        });
        $("#editBtn").click(function () {
            $("#editForm").submit();
        });


    });
</script>


</body>
</html>
