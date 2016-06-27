<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/js/webUploader/webuploader.css">
</head>
<body>
<div class="container">
    <div class="page-header">
        <h4>文件操作</h4>
    </div>
    <div id="picker">请选择文件</div>
    <h5 style="color: red">文件列表</h5>
    <ul id="list"></ul>

    <button id="btn" class="bg-success">开始上传</button>
</div>


<script src="/static/js/jquery-2.2.3.min.js"></script>
<script src="/static/js/webUploader/webuploader.min.js"></script>
<script type="myTem" id="myTest">
<div class="progress">
  <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">

  </div>
</div>
</script>
<script>
    $(function () {
        var upLoader = WebUploader.create({
            swf: "/static/js/webUploader/Uploader.swf",
            server: "/upload",
            pick: "#picker",
            fileVal: "file"

        });
        upLoader.on("fileQueued", function (data) {
            console.log(data.id + "->" + data.name);
            $("#list").append("<li id=" + data.id + ">" + data.name + "</li>");
        });
        $("#btn").click(function () {
            upLoader.upload();
        });
        upLoader.on("uploadProgress", function (data, percentage) {
            percentage = parseInt(percentage * 100);
            var $li = $("#" + data.id);

            if($li.find(".progress").length){
                $li.find(".progress .progress-bar").css("width",percentage+"%");
            }else{
                var html=$("#myTest").html();
                $li.append(html);
            }







//            percentage = parseInt(percentage * 100);
//
//            var $li = $("#" + data.id);
//
//            if ($li.find("span").length) {
//                $li.find("span").text("文件上传中..." + percentage + "%");
//            } else {
//                $("#" + data.id).append("<span>文件上传中..." + parseInt(percentage * 100) + "%</span>");
//            }
//            if (percentage == 100) {
//                $li.find("span").text("上传完成！").fadeOut(4000);
//            }
        });
        upLoader.on("uploadSuccess", function (data) {
//            $("#"+data.id).find("span").text("上传！");

            $("#" + data.id).css("color", "#ccc");
        });
        upLoader.on("uploadError", function (data) {
            $("#" + data.id).css("color", "red");
        });
        upLoader.on("uploadComplete", function (data) {
            $("#" + data.id).find("span").text("无论成功否都必须执行的步骤！");
        });

    });


</script>
</body>
</html>
