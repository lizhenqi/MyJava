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
    <title>销售机会管理测试</title>
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
    <link rel="stylesheet" href="/static/plugins/daterangepicker/daterangepicker-bs3.css">

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
                <small>销售机会列表</small>
            </h1>
        </section>

        <section class="content">
            <%--搜索之用--%>
            <div class="box box-default collapsed-box">
                <div class="box-header with-border">
                    <h3 class="box-title">搜索</h3>
                    <div class="box-tools">
                        <button class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"><i class="icon-zoom-in"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <form class="form-inline">
                        <input type="hidden" id="search_start_time">
                        <input type="hidden" id="search_end_time">
                        <input type="text" class="form-control" id="search_name" placeholder="机会名称">
                        <select class="form-control"id="search_progress">
                            <option value="">当前进度</option>
                            <option value="初次接触">初次接触</option>
                            <option value="确认意向">确认意向</option>
                            <option value="提供合同">提供合同</option>
                            <option value="完成交易">完成交易</option>
                            <option value="交易搁置">交易搁置</option>
                        </select>
                        <input type="text" id="rangepicker" class="form-control" placeholder="跟进时间">
                        <button type="button" id="search_Btn" class="btn btn-default"><i class="icon-zoom-in"></i> 搜索</button>
                    </form>
                </div>
            </div>



            <div class="box box-primary">
                <div class="box-header" style="text-align: center;color: mediumvioletred">
                    <h3 class="box-title " >销售机会管理</h3>
                    <div class="box-tools">
                        <%--这个"box-tools"使其靠右边--%>
                        <button class="btn btn-xs btn-success " id="newSales"><i class="icon-user"></i>新建机会</button>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table" id="salesTable">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>机会名称</th>
                            <th>关联客户</th>
                            <th>价值</th>
                            <th>当前进度</th>
                            <th>最后跟进时间</th>
                            <th>所属员工</th>
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

<!-- Modal(新增客户) -->
<div class="modal fade" id="salesModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="text-align: center;color: red">新增机会</h4>
            </div>
            <div class="modal-body">
                <form id="salesForm">
                    <div class="form-group">
                         <label>机会名称</label>
                         <input class="form-control" type="text" name="name">
                    </div>
                    <div class="form-group">
                        <label>关联客户</label>
                        <select class="form-control" name="customerid">
                            <c:forEach items="${customerList}" var="customer">
                                <option value="${customer.id}">${customer.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>价值</label>
                        <input class="form-control" type="text" name="price">
                    </div>
                    <div class="form-group">
                        <label>当前进度</label>
                        <select class="form-control" name="progress">
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
                <button type="button" class="btn btn-primary" id="salesBtn">保存</button>
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
<script src="/static/plugins/daterangepicker/daterangepicker.js"></script>

<script>
    $(function () {
//        显示列表
        var dataTable = $("#salesTable").DataTable({

            "ajax":{
                url: "/sales/list",
                data:function(dataSouce){
                    dataSouce.name=$("#search_name").val();
                    dataSouce.progress = $("#search_progress").val();
                    dataSouce.startdate = $("#search_start_time").val();
                    dataSouce.enddate = $("#search_end_time").val();
                }
            },
            "lengthMenu": [5, 10, 15],
            "serverSide": true,
            "autowidth": false,
            "searching":false,
            "columns": [
                {"data":"id"},
                {"data":function(row){
                    return"<a href='/sales/view/"+row.id+"'>"+row.name+"</a>";
                }},
                {"data":function(row){
                    return "<a href='/customer/view/"+row.customerid+"'>" + row.customername+"</a>";
                }},
                {"data":function(row){
                    return "￥"+row.price;
                }},
                {"data":function(row){

                    if(row.progress=="交易搁置"){
                        return "<span class='label label-danger'>"+row.progress+"</span>";
                    }if(row.progress == "完成交易"){
                        return "<span class='label btn-success' style='color: red'>"+row.progress+"</span>"
                    }
                    return row.progress;
                }},
                {"data":"lasttime"},
                {"data":"username"},
                {"data":function(){
                    return"";
                }}
            ],

            "language": {
//                "search": "搜索",//
//                searchPlaceholder: "请输入...",
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
        $("#newSales").click(function () {
            $("#salesForm")[0].reset();//清空框内记忆（重置表单）

            $("#salesModal").modal({
                show: true,
                backdrop: "static",
                keyboard: false
            })
        });

//        验证输入
        $("#salesForm").validate({
            errorClass: "text-danger",
            errorElement: "span",
            rules: {
                name: {
                    required: true
                },
                customername: {
                    required: true
                },
                price: {
                    required: true
                }
            },
            messages: {
                name: {
                    required: "请输入机会名称"
                },
                customername: {
                    required: "请输入关联客户"
                },
                price: {
                    required: "请输入价值"
                }
            },
            submitHandler: function (form) {
                $.post("/sales/new", $(form).serialize())
                        .done(function (data) {
                            if (data == "success") {
                                $("#salesModal").modal("hide");
                                alert("新增成功");
                                dataTable.ajax.reload();
                            }
                        })
                        .fail(function () {
                            alert("新增失败！");
                        });
            }
        });
//提交
        $("#salesBtn").click(function () {
            $("#salesForm").submit();
        });




        //daterangepicker时间范围选择器

        $("#rangepicker").daterangepicker({
            format: "YYYY-MM-DD",
            separator:"~",
            locale:{
                "applyLabel": "选择",
                "cancelLabel": "取消",
                "fromLabel": "从",
                "toLabel": "到",
                "customRangeLabel": "自定义",
                "weekLabel": "周",
                "daysOfWeek": [
                    "一",
                    "二",
                    "三",
                    "四",
                    "五",
                    "六",
                    "日"
                ],
                "monthNames": [
                    "一月",
                    "二月",
                    "三月",
                    "四月",
                    "五月",
                    "六月",
                    "七月",
                    "八月",
                    "九月",
                    "十月",
                    "十一月",
                    "十二月"
                ],
                "firstDay": 1
            },
            ranges: {
                'Today': [moment(), moment()],
                'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                'Last 7 Days': [moment().subtract(6, 'days'), moment()],
                'Last 30 Days': [moment().subtract(29, 'days'), moment()],
                'This Month': [moment().startOf('month'), moment().endOf('month')],
                'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
            }
        });
        $('#rangepicker').on('apply.daterangepicker', function(ev, picker) {

            $("#search_start_time").val(picker.startDate.format('YYYY-MM-DD'));
            $("#search_end_time").val(picker.endDate.format('YYYY-MM-DD'));
        });

        //搜索
        $("#search_Btn").click(function(){
            dataTable.ajax.reload();
        });



    });
</script>
</body>
</html>
