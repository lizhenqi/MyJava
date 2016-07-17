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
    <title>客户管理测试</title>
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
    <link rel="stylesheet" href="/static/plugins/webuploader/webuploader.css">

</head>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@include file="../include/header.jsp" %>
    <%--<%@include file="../include/leftSide.jsp" %>--%>
    <jsp:include page="../include/leftSide.jsp">
        <jsp:param name="menu" value="customer"/>
    </jsp:include>

    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                客户关系管理
                <small>客户列表</small>
            </h1>
        </section>
        <!-- Main content -->
        <section class="content">

            <div class="box box-primary">
                <div class="box-header" style="text-align: center;color: mediumvioletred">
                    <h3 class="box-title " >客户管理</h3>
                    <div class="box-tools">
                        <%--这个"box-tools"使其靠右边--%>
                        <button class="btn btn-xs btn-success " id="newCustomer"><i class="icon-user"></i>新建客户</button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="customerTable">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>类型</th>
                            <th>客户名称</th>
                            <th>联系电话</th>
                            <th>电子邮件</th>
                            <th>等级</th>
                            <th>创建时间</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="box box-info">
                        <div class="box-header with-border" style="text-align: center;color: mediumvioletred">
                            <div class="box-title" ><i class="icon-th-list"></i>项目列表</div>
                        </div>
                        <div class="box-body">
                            <h6>暂无项目</h6>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="box box-info">
                        <div class="box-header with-border" style="text-align: center;color: mediumvioletred">
                            <div class="box-title" ><i class="icon-bell"></i>待办事项</div>
                        </div>
                        <div class="box-body">
                            <h6>暂无项目</h6>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </div>
</div>

<!-- Modal(新增客户) -->
<div class="modal fade" id="customerModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="text-align: center;color: red">新增客户</h4>
            </div>
            <div class="modal-body">
                <form id="newForm">
                    <div class="form-group">
                        <label>类型</label>
                        <div>
                            <label class="radio-inline">
                                <input type="radio" name="type" value="person" checked id="radioPerson">个人
                            </label><label class="radio-inline">
                            <input type="radio" name="type" value="company" id="radioCompany">公司
                        </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label>客户名称</label>
                        <input class="form-control" type="text" name="name">
                    </div>
                    <div class="form-group">
                        <label>联系电话</label>
                        <input class="form-control" type="text" name="tel">
                    </div>
                    <div class="form-group">
                        <label>客户等级</label>
                        <select name="level" class="form-control">
                            <option value="★">★</option>
                            <option value="★★">★★</option>
                            <option value="★★★">★★★</option>
                            <option value="★★★★">★★★★</option>
                            <option value="★★★★★">★★★★★</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>微信号</label>
                        <input class="form-control" type="text" name="weixin">
                    </div>
                    <div class="form-group">
                        <label>电子邮件</label>
                        <input class="form-control" type="text" name="email">
                    </div>
                    <div class="form-group">
                        <label>地址</label>
                        <input class="form-control" type="text" name="address">
                    </div>
                    <div class="form-group" id="companyList">
                        <label>所属公司</label>
                        <select class="form-control" name="companyID">
                            <%--customerList：所有为公司的客户--%>
                            <%--有的客户不想关联他的公司，要有一个空选项--%>
                            <option></option>
                            <c:forEach items="${customerList}" var="company">
                                <option value="${company.id}">${company.name}</option>
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

<!-- Modal(修改客户) -->
<div class="modal fade" id="editModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="text-align: center;color: red">修改客户</h4>
            </div>
            <div class="modal-body">
                <form id="editForm">
                    <input type="hidden" name="id" id="edit_id">
                    <input type="hidden" name="userid" id="edit_userid">
                    <input type="hidden" name="type" id="edit_type">
                    <div class="form-group">
                        <label>客户名称</label>
                        <input class="form-control" type="text" name="name" id="edit_name">
                    </div>
                    <div class="form-group">
                        <label>联系电话</label>
                        <input class="form-control" type="text" name="tel" id="edit_tel">
                    </div>
                    <div class="form-group">
                        <label>客户等级</label>
                        <select name="level" class="form-control" id="edit_level">
                            <option value="★">★</option>
                            <option value="★★">★★</option>
                            <option value="★★★">★★★</option>
                            <option value="★★★★">★★★★</option>
                            <option value="★★★★★">★★★★★</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>微信号</label>
                        <input class="form-control" type="text" name="weixin" id="edit_weixin">
                    </div>
                    <div class="form-group">
                        <label>电子邮件</label>
                        <input class="form-control" type="text" name="email" id="edit_email">
                    </div>
                    <div class="form-group">
                        <label>地址</label>
                        <input class="form-control" type="text" name="address" id="edit_address">
                    </div>
                    <div class="form-group" id="editcompanyList">
                        <label>所属公司</label>
                        <select class="form-control" name="companyID" id="edit_companyID">

                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="editBtn">保存</button>
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

<script>
    $(function () {


//        显示列表
        var dataTable = $("#customerTable").DataTable({
            "ajax": "/customer/list",
            "serverSide": true,
            "lengthMenu": [5, 10, 15],
            "autowidth": false,
            "columns": [

                {"data": "id"},
                {
                    "data": function (row) {
                        if (row.type == "company") {
                            return "<i class='icon-group'></i>";
                        }
                        return "<i class='icon-user'></i>";
                    }
                },
                {
                    "data": function (row) {
                        if (row.companyname) {
                            return "<a href='/customer/view/"+row.id+"'>"+row.name+"</a>"
                                    +"<a href='/customer/view/"+row.companyID+"'><label style='color: red'>∈</label>" + row.companyname+"</a>";
                        }
                        return "<a href='/customer/view/"+row.id+"'>"+row.name+"</a>";
                    }
                },
                {"data": "tel"},
                {"data": "email"},
                {
                    "data": function (row) {
                        return "<i style='color:green'>" + row.level + "</i>";
                    }
                },

                {
                    "data": function (row) {
                        var time = moment(row.creeeeatetime);
                        return time.format("YYYY-MM-DD HH:mm")
                    }
                },

                {
                    "data": function (row) {
                        //注意

                        return <shiro:hasRole name="管理员">"<a href='javascript:;' rel='" + row.id + "' class='del'>删除</a>" + </shiro:hasRole>"<a href='javascript:;' rel='" + row.id + "' class='edit'>编辑</a>";


                    }
                }
            ],

            "language": {
                "search": "搜索",//
                searchPlaceholder: "请输入客户/电话...",
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
                    "previous": "上一页",
                    "next": "下一页"
                }
            }
        });


        /**
         * 新建customer
         */
//        （点击新建模态框显示）
        $("#newCustomer").click(function () {
            $("#newForm")[0].reset();//清空框内记忆（重置表单）

            $.get("/customer/company.json").done(function (data) {
                var $select = $("#companyList select").html("");//先把原有的select内的清空

                $select.append("<option></option>");//添加空的选项

                if (data && data.length) {
                    for (var i = 0; i < data.length; i++) {
                        var company = data[i];//代表每一个类型为company的客户
                        var option = "<option value=" + company.id + ">" + company.name + "</option>";
                        $select.append(option);
                    }
                }
            }).fail(function () {
                alert("服务器异常。")
            });

            $("#companyList").show();//默认显示，否则操作后切换还得点击


            $("#customerModal").modal({
                show: true,
                backdrop: "static",
                keyboard: false
            })
        });
//        点击个人所属公司显示
        $("#radioPerson").click(function () {
            if ($(this)[0].checked) {
                $("#companyList").show();
            }
        });
//        点击公司，所属公司隐藏
        $("#radioCompany").click(function () {
            if ($(this)[0].checked) {
                $("#companyList").hide();
            }
        });
//        验证输入
        $("#newForm").validate({
            errorClass: "text-danger",
            errorElement: "span",
            rules: {
                name: {
                    required: true
                },
                tel: {
                    required: true
                },
                address: {
                    required: true
                }
            },
            messages: {
                name: {
                    required: "请输入客户名称"
                },
                tel: {
                    required: "请输入联系方式"
                },
                address: {
                    required: "请输入地址"
                }
            },
            submitHandler: function (form) {
                $.post("/customer/new", $(form).serialize())
                        .done(function (data) {
                            if (data == "success") {
                                $("#customerModal").modal("hide");
                                alert("新增成功");
                                dataTable.ajax.reload();
//                              window.location.href="/customer";//(重新登录界面)这个也可以解决新增时候解决添加客户时无法实时加载最新公司数据的bug，但是会有页面刷新的卡顿感。
                            }
                        })
                        .fail(function () {
                            alert("新增失败！");
                        });
            }
        });
//提交
        $("#saveBtn").click(function () {
            $("#newForm").submit();
        });

        <shiro:hasRole name="管理员">
        //删除(只有管理员可以)，
        $(document).delegate(".del", "click", function () {
            if (confirm("确认删除?")) {
                var id = $(this).attr("rel");
                $.get("/customer/del/" + id).done(function (data) {
                    if (data == "success") {
                        alert("删除成功");
                        dataTable.ajax.reload();
                    }
                }).fail(function () {
                    alert("删除失败");
                });
            }
        });
        </shiro:hasRole>


        /**
         * 修改(任何角色都能改)
         */
        $(document).delegate(".edit", "click", function () {
            var id = $(this).attr("rel");

            var $select=$("#editcompanyList select").html("");//清空公司内
            $select.append("<option></option>");

            //ajax请求服务端获取id对应的customer对象和公司列表
            $.get("/customer/edit/company/" + id + ".json").done(function (result) {

                if (result.state = "success") {

                    //获取公司选项列表
                    if(result.companylist && result.companylist.length){
                        for(var i= 0;i<result.companylist.length;i++){
                            var company=result.companylist[i];
                            var option="<option value='"+company.id+"'>"+company.name+"</option>";
                            $select.append(option);
                        }
                    }


                    //判别是公司还是个人
                    var customer=result.customer;

                    if(customer.type=="company"){
                        $("#editcompanyList").hide();
                    }else{
                        $("#editcompanyList").show();
                    }

                    //框内填充
                    $("#edit_id").val(customer.id);
                    $("#edit_userid").val(customer.userid);
                    $("#edit_name").val(customer.name);
                    $("#edit_tel").val(customer.tel);
                    $("#edit_address").val(customer.address);
                    $("#edit_weixin").val(customer.weixin);
                    $("#edit_email").val(customer.email);
                    $("#edit_level").val(customer.level);
                    $("#edit_type").val(customer.type);
//                    $("edit_companyID").val(customer.companyID);//这个也可以
                    $select.val(customer.companyID);

                } else {
                    alert(result.message);
                }

                $("#editModal").modal({
                    show: true,
                    backdrop: "static",
                    keyboard: false
                })

            }).fail(function () {
                alert("服务器异常");
            });
        });
        //        验证输入
        $("#editForm").validate({
            errorClass: "text-danger",
            errorElement: "span",
            rules: {
                name: {
                    required: true
                },
                tel: {
                    required: true
                },
                address: {
                    required: true
                }
            },
            messages: {
                name: {
                    required: "请输入客户名称"
                },
                tel: {
                    required: "请输入联系方式"
                },
                address: {
                    required: "请输入地址"
                }
            },
            submitHandler: function (form) {
                $.post("/customer/update", $(form).serialize())
                        .done(function (data) {
                            if (data == "success") {
                                $("#editModal").modal("hide");
                                alert("修改成功");
                                dataTable.ajax.reload();
//                              window.location.href="/customer";//(重新登录界面)这个也可以解决新增时候解决添加客户时无法实时加载最新公司数据的bug，但是会有页面刷新的卡顿感。
                            }
                        })
                        .fail(function () {
                            alert("新增失败！");
                        });
            }
        });
//提交
        $("#editBtn").click(function () {
            $("#editForm").submit();
        });


    });
</script>
</body>
</html>
