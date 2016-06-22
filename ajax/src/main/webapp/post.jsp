
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <button id="btn1">post提交请求</button>
    <button id="btn2">get提交请求</button>
    <script src="/static/js/ajax.js"></script>
    <script>

        (function(){
            document.querySelector("#btn2").onclick= function () {
//                var msg={name:"jim",address:"中国"};
//                var inf="";
//                for(var index in msg){
//                    inf+=index+"="+msg[index]+"&";
//                }
//
//                inf+=new Date().getTime();
////                if(inf.lastIndexOf("&")==inf.length-1){
////                    inf=inf.substring(0,inf.length-1)
////                }
//
//                alert(inf);



                ajax.getText("/ajax",function(result){
                    alert("测试："+result);
                })
            };

            document.querySelector("#btn1").onclick= function () {
                ajax.postText("/ajax",{name:"测试",msg:"USA"},function(result){
                    alert("测试："+result);
                })

            };

//            document.querySelector("#btn").onclick=function(){
//                var xmlHttp=creXmlHttp();
//                xmlHttp.open("POST","/ajax",true);
//                xmlHttp.setRequestHeader("content-Type","application/x-www-form-urlencoded");
//
//
//                xmlHttp.send("name=回火&address=中国");
//
//            }




        })();





    </script>
</body>
</html>
