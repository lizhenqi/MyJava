
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>


<input type="text" id="username">
<span id="help"></span>
<hr>

<button id="btn">Send Post Ajax</button>
<hr>


<button id="jsonBtn">get Json Data</button>
<ul id="jsonList"></ul>
<hr>

<button id="xmlBtn">get xml Data</button>
<div id="userBox">

</div>
<hr>
<script src="/static/js/jquery-2.2.3.min.js"></script>
<script>

//    $(document).ready(function(){});//另一种写法
    $(function(){

        $("#username").change(function() {
            var name = $(this).val();

            $.get("/checkUsername",{"username":name},function(result){
                if(result=="yes"){
                    $("#help").text("√")
                }else{
                    $("#help").text("该账号已被注册！");
                }
            });
        } );

        $("#btn").click(function(){
            $.post("/ajax", {"name":"测试"},function(result){
                alert(result);
            });
        });
        $("#jsonBtn").click(function(){
            var $ul=$("#jsonList");
            $.get("/user.json",function(data){
               for(var i=0;i<data.length;i++){
                   var msg=data[i];
                   var name=msg["name"];
                   var address=msg.address;
                   $ul.append("<li>"+name+"->"+address+"</li>")
               }

            })
        });

        $("#xmlBtn").click(function(){
            $("#userBox").html("");
            $.get("/user.xml",function(data){
                $(data).find("user").each(function(){
                    var id=$(this).attr("id");
                    var name=$(this).find("name").text();
                    var address=$(this).find("address").text();
                    $("#userBox").append("<div><h4>"+name+"<small>"+id+"</small></h4><P>"+address+"</P></div>");


                });
            })


        });



    });




</script>
</body>
</html>
