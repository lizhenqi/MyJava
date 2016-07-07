<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/js/datatables/css/dataTables.bootstrap.min.css">
    <%--<link rel="stylesheet" href="/static/js/datatables/css/jquery.dataTables.css">--%>
</head>
<body>

<div class="container">
    <div class="page-header">
        <h3 style="text-align: center; color: red">DataTables的应用</h3>
    </div>

    <div class="well well-sm">
        <form method="get" class="form-inline">
            <div class="form-group">
                <input type="text" placeholder="书籍名称" class="form-control" id="search_BookName" value="${bookname}" name="bookname">
            </div>

            <div class="form-group">
                <select id="" class="form-control" name="type" id="search_BookType">
                    <option value="">请选择类型</option>
                    <c:forEach items="${bookTypeList}" var="type">
                        <option value="${type.id}" ${typeid==type.id? 'selected':''} >${type.booktype}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <select class="form-control" name="pub" id="search_BookPub">
                    <option value="">请选择出版社</option>
                    <c:forEach items="${publisherList}" var="pub">
                        <option value="${pub.id}" ${pubid==pub.id?'selected':''}>${pub.pubname}</option>
                    </c:forEach>
                </select>
            </div>
            <button type="button" id="searchBtn" class="btn btn-default">搜索</button>
        </form>
    </div>


    <a href="javascript:;" class="btn btn-primary" id="newBookBtn" style="margin-bottom: 15px">新增书籍</a>
    <table class="table" id="datatable">
        <thead>
        <tr>
            <th>ID</th>
            <th>书籍名称</th>
            <th>价格</th>
            <th>作者</th>
            <th>数量</th>
            <th>类型</th>
            <th>出版商</th>
            <th>操作</th>
        </tr>
        </thead>

        <tbody>

        </tbody>

    </table>
</div>

<!-- Modal（新增用） -->
<div class="modal fade" id="newBookModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color:#34ee0d; text-align: center">添加新书</h4>
            </div>
            <div class="modal-body">
                <%--表单（用于新增）--%>
                <form id="saveForm">
                    <div class="form-group">
                        <label>书籍名称</label>
                        <input type="text" name="bookname" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label>作者</label>
                        <input type="text" name="bookauthor" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label>价格</label>
                        <input type="text" name="bookprice" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label>数量</label>
                        <input type="text" name="booknum" class="form-control"/>
                    </div>

                    <div class="form-group">
                        <label>类型</label>
                        <select class="form-control" name="typeid">
                            <c:forEach items="${bookTypeList}" var="type">
                                <option value="${type.id}">${type.booktype}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>出版商</label>
                        <select class="form-control" name="pubid">
                            <c:forEach items="${publisherList}" var="pub">
                                <option value="${pub.id}">${pub.pubname}</option>
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


<!-- Modal（修改用） -->
<div class="modal fade" id="editBookModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style="color:#34ee0d; text-align: center">修改书籍</h4>
            </div>
            <div class="modal-body">
                <%--表单（用于新增）--%>
                <form id="editForm">
                    <input class="hidden" name="id" id="edit_bookid">
                    <div class="form-group">
                        <label>书籍名称</label>
                        <input type="text" name="bookname" class="form-control" id="edit_bookname"/>
                    </div>

                    <div class="form-group">
                        <label>作者</label>
                        <input type="text" name="bookauthor" class="form-control" id="edit_bookauthor"/>
                    </div>

                    <div class="form-group">
                        <label>价格</label>
                        <input type="text" name="bookprice" class="form-control" id="edit_bookprice"/>
                    </div>

                    <div class="form-group">
                        <label>数量</label>
                        <input type="text" name="booknum" class="form-control" id="edit_booknum"/>
                    </div>

                    <div class="form-group">
                        <label>类型</label>
                        <select class="form-control" name="typeid" id="edit_typeid">
                            <c:forEach items="${bookTypeList}" var="type">
                                <option value="${type.id}">${type.booktype}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>出版商</label>
                        <select class="form-control" name="pubid" id="edit_pubid">
                            <c:forEach items="${publisherList}" var="pub">
                                <option value="${pub.id}">${pub.pubname}</option>
                            </c:forEach>
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


<script src="/static/js/jquery-2.2.3.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/datatables/js/jquery.dataTables.min.js"></script>
<script src="/static/js/datatables/js/dataTables.bootstrap.min.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>

<script>
    $(function () {
        //       DataTable()： 注意是大写
        var dataTable = $('#datatable').DataTable({
            "lengthMenu": [5, 10, 15],//定义每页显示条数（可选）
            "serverSide": true,//表示所有的操作都在服务端进行
//            "ajax": "/datatable/data.json",//服务端地址url

            "ajax":{
                url:"/datatable/data.json",
                data:function(dataSource){
                    dataSource.bookname=$("#search_BookName").val();
                    dataSource.typeid=$("#search_BookType").val();
                    dataSource.pubid=$("#search_BookPub").val();
                }

            },
            "order": [0, 'desc'],
            "searching":false,//为了使用另一种搜索，这种就不用了，把它隐藏起来
            "columns": [//配置控制器返回的JSON中data属性中数据key和表格列的对应关系
                {"data": "id", "name": "id"},
                {"data": "bookname"},
                {"data": "bookprice", "name": "bookprice"},
                {"data": "bookauthor"},
                {"data": "booknum", "name": "booknum"},
                {"data": "bookType.booktype", "name": "typeid"},
                {"data": "publisher.pubname"},
                {
                    "data": function (row) {
//                        row就是获取到的一条对象
                        return "<a href='javascript:;' class='editBookBtn' rel=" + row.id + " style='color: b'>修改</a> <a href='javascript:;' class='delBook' rel=" + row.id + " style='color: red'>删除</a>";
                    }
                }
            ],

            columnDefs: [//定义列的特征
                {targets: [0], visible: true},
                {targets: [1, 3, 5, 6, 7], orderable: false}//代表这几列不能被排序。
            ],


            "language": {
                "search": "搜索",//
                searchPlaceholder: "请输入书籍...",

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


//        模态框的显示也可以手写（自带的输入简单，但是单一）
        $("#newBookBtn").click(function () {
            $("#saveForm")[0].reset();//让表单进行重置（就是不让框内再有上次输入的值了，清空）

            $('#newBookModal').modal({
                show: true,
                backdrop: 'static',
                keyboard: false//代表esc键盘也不会退出，除非点击x，或是close
            });
        });

//        保存按钮(保存之前要验证如下)
        $("#saveBtn").click(function () {
            $("#saveForm").submit();

        });

//        保存之前的验证
        $("#saveForm").validate({
            errorElement: "span",
            errorClass: "text-danger",
            rules: {
                bookname: {
                    required: true
                },
                bookauthor: {
                    required: true
                },
                bookprice: {
                    required: true,
                    number: true

                },
                booknum: {
                    required: true,
                    digits: true
                }
            },
            messages: {
                bookname: {
                    required: "请输入书籍名称"
                },
                bookauthor: {
                    required: "请输入作者"
                },
                bookprice: {
                    required: "请输入价格",
                    number: "请输入合法的价格"

                },
                booknum: {
                    required: "请输入数量",
                    digits: "请输入合法的数量"
                }
            },
            submitHandler: function (form) {
                $.post("/datatable/new", $(form).serialize())
                        .done(function (data) {
                            if (data == "success") {
                                $('#newBookModal').modal("hide");
                                dataTable.ajax.reload();//让DataTable组件自动刷新

                            }
                        })
                        .fail(function () {
                            alert("新增异常");
                        })
            }
        });

//        删除（不能直接写点击）
        //删除书籍(事件委托)可以给当前或未来出现的符合选择器的元素绑定事件
        $(document).delegate(".delBook", "click", function () {
            var id = $(this).attr("rel");
            if (confirm("确认删除？")) {
                $.get("/datatable/" + id + "/del")
                        .done(function (data) {
                            if (data == "success") {
                                dataTable.ajax.reload();//让DataTable组件自动刷新
                            }
                        })
                        .fail(function () {
                            alert("删除异常");
                        });
            }


        });


        //修改(再复制一份modal，反正界面上也是看不到的)
        $(document).delegate(".editBookBtn", "click", function () {
            $("#saveForm")[0].reset();//让表单进行重置（就是不让框内再有上次输入的值了，清空）

            var id = $(this).attr("rel");

            $.get("/datatable/" + id + ".json")
                    .done(function (data) {

                        //将JSON数据填充在表单上
                        $("#edit_bookid").val(data.id);
                        $("#edit_bookauthor").val(data.bookauthor);
                        $("#edit_bookname").val(data.bookname);
                        $("#edit_bookprice").val(data.bookprice);
                        $("#edit_booknum").val(data.booknum);
                        $("#edit_typeid").val(data.typeid);
                        $("#edit_pubid").val(data.pubid);


//                            点击就显示modal框
                        $("#editBookModal").modal({
                            show: true,
                            backdrop: 'static',
                            keyboard: false
                        });

                    })
                    .fail(function () {
                        alert("修改异常");
                    });
        });

//        保存按钮(保存之前要验证如下)
        $("#editBtn").click(function () {
            $("#editForm").submit();

        });
//        保存之前的验证
        $("#editForm").validate({
            errorElement: "span",
            errorClass: "text-danger",
            rules: {
                bookname: {
                    required: true
                },
                bookauthor: {
                    required: true
                },
                bookprice: {
                    required: true,
                    number: true

                },
                booknum: {
                    required: true,
                    digits: true
                }
            },
            messages: {
                bookname: {
                    required: "请输入书籍名称"
                },
                bookauthor: {
                    required: "请输入作者"
                },
                bookprice: {
                    required: "请输入价格",
                    number: "请输入合法的价格"

                },
                booknum: {
                    required: "请输入数量",
                    digits: "请输入合法的数量"
                }
            },
            submitHandler: function (form) {
                $.post("/datatable/edit", $(form).serialize())
                        .done(function (data) {
                            if (data == "success") {
                                $('#editBookModal').modal("hide");
                                dataTable.ajax.reload();//让DataTable组件自动刷新
                            }
                        })
                        .fail(function () {
                            alert("新增异常");
                        })
            }
        });

//        搜索
        $("#searchBtn").click(function() {
            dataTable.ajax.reset();
        });

    });
</script>

</body>
</html>
