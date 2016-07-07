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
            <table class="table" id="testData">
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


                <%--<c:forEach items="${bookList}" var="book">--%>
                    <%--<tr>--%>
                        <%--<td>${book.id}</td>--%>
                        <%--<td>${book.bookname}</td>--%>
                        <%--<td>${book.bookprice}</td>--%>
                        <%--<td>${book.bookauthor}</td>--%>
                        <%--<td>${book.booknum}</td>--%>
                        <%--<td>${book.bookType.booktype}</td>--%>
                        <%--<td>${book.publisher.pubname}</td>--%>

                        <%--<td>--%>
                            <%--<a href="/books/${book.id}" class="">修改</a>--%>
                            <%--<a href="javascript:;" data="${book.id}" class="del">删除</a>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                <%--</c:forEach>--%>
                </tbody>

            </table>
        </div>


<script src="/static/js/jquery-2.2.3.min.js"></script>
<script src="/static/js/datatables/js/jquery.dataTables.min.js"></script>
<script src="/static/js/datatables/js/dataTables.bootstrap.min.js"></script>

<script>
    $(function(){
        //       DataTable()： 注意是大写
        $('#testData').DataTable({
            "lengthMenu": [5, 10, 15],//定义每页显示条数（可选）
            "serverSide": true,//表示所有的操作都在服务端进行
            "ajax": "/datatable/data.json",//服务端地址url

            "columns": [//配置控制器返回的JSON中data属性中数据key和表格列的对应关系
                { "data": "id" },
                { "data": "bookname" },
                { "data": "bookprice" ,"name":"bookprice"},
                { "data": "bookauthor" },
                { "data": "booknum" ,"name":"booknum"},
                { "data": "bookType.booktype" ,"name":"typeid" },
                { "data": "publisher.pubname" },
                { "data": function(row){
                    return "操作";
                } }
            ],

            columnDefs: [//定义列的特征
                { targets: [0], visible: true},
                { targets: [1,3,5,6,7], orderable:false}//代表这几列不能被排序。
            ],


            "language": {
                "search": "搜索",//
                searchPlaceholder: "请输入书籍...",

                "zeroRecords": "没有查询到记录！",
                "infoEmpty":      "0条记录！",//这个和上面的是在一个界面显示

                "loadingRecords": "加载中...",
                "processing":     "处理中...",

                "info":           "显示从 _START_ 到 _END_ 总共 _TOTAL_ 条记录",
                "lengthMenu":     "选择显示 _MENU_ 条记录",//右上角选择显示记录数
                "infoFiltered":   "(从 _MAX_ 条数据过滤)",//查询时候显示从多少数据中查询的


                "paginate": {
                    "first":      "首页",
                    "last":       "末页",
                    "next":       "下一页",
                    "previous":   "上一页"
                }
            }

        });

    });
</script>

</body>
</html>
