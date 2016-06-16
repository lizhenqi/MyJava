<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="col-xs-6">
            <form action="/fileload" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label>文件描述</label>
                    <input type="text" class="form-control" name="filedes">
                </div>
                <div class="form-group">
                    <label>选择文件</label>
                    <input type="file" class="form-control" name="filename">
                </div>
                <button class="btn btn-primary">上传</button>
            </form>
        </div>
    </div>

</body>
</html>
