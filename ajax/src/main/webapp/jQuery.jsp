<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>


<div class="container" style="font-size: 20px">
    <button id="btn1" style="margin-top: 20px">get Json</button>
    <button id="btn2" style="margin-top: 20px">Ajax method</button>
    <img src="/static/img/send.gif" style="display:none" id="img"/>
</div>
<script src="/static/js/jquery-2.2.3.min.js"></script>

<script>
    $(function () {
        $("#btn1").click(function () {
            $.getJSON("/user.json")
                    .done(function (result) {
                        console.log("成功");
                    })//这句也可以放在上面括号内。
                    .fail(function (result) {
                        console.log("错误");
                    })
                    .always(function () {
                        console.log("无论成功否都执行");
                    })

            ;
        });
        $("#btn2").click(function(){
            var $this=$(this);
            $.ajax({
                url:"/user.json",
                type:"get",
                data:{"name":"test Ajax"},

                timeout:3000,

                success:function(result){
                    console.log(result);

                },
                error:function(result){console.log("失败");},
                complete:function(result){
                    console.log("成功否都执行");
                    $this.text("Ajax method").removeAttr("disabled");
                },
//问：文档不是说推荐用done fail always吗怎么不能用？但可以用上面的写法。
                beforeSend:function(result){
                    console.log("测试发送前！");
                    $this.text("发送中...").attr("disabled","disabled");
                    $("#img").show();
                }

            });
        });
    });
</script>
</body>
</html>
