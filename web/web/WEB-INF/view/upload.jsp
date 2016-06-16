
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <form action="/upload" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label>文件描述</label>
                <input type="text" name="filedes" class="form-control">
            </div>
            <div class="form-group">
                <label>文件选择</label>
                <input type="file" name="filedec" class="form-control">
            </div>
            <button class="btn btn-primary">上传</button>
        </form>
    </div>

</body>
</html>
