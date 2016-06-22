
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button id="btn">Ajax请求</button>

<script>
    (function(){

        function creXmlHttp(){
            var xmlHttp=null;
            if(window.ActiveXObject){
                xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
            }else{
                xmlHttp=new XMLHttpRequest();
            }
            return xmlHttp;
        }

        document.querySelector("#btn").onclick=function(){
            var xmlHttp=creXmlHttp();
            xmlHttp.open("GET","/ajax",true);
            xmlHttp.send();
        }




    })();




</script>
</body>
</html>
