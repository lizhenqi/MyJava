
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="page-header">
            <h2>Ajax读取xml测试</h2>
        </div>
        <button id="btn">Read</button>
        <div id="userBox">


        </div>
    </div>
<script>
    (function(){
        function creaXmlHttp(){
            var xmlHttp=null;
            if(window.ActiveXObject){
                xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
            }else{
                xmlHttp=new XMLHttpRequest();
            }
            return xmlHttp;
        }

        function  creaDocDiv(id,name,address){
            var div=document.createElement("div");
            var h4=document.createElement("h4");
            var small=document.createElement("small");
            var p=document.createElement("p");

            var idText=document.createTextNode(id);
            var nameText=document.createTextNode(name);
            var addressText=document.createTextNode(address);

            p.appendChild(addressText);
            small.appendChild(idText);
            h4.appendChild(nameText);
            h4.appendChild(small);

            div.appendChild(h4);
            div.appendChild(p);

            document.getElementById("userBox").appendChild(div);
        }


        document.getElementById("btn").onclick=function(){
            var xmlHttp=creaXmlHttp();
            xmlHttp.open("get","/user.xml",true);

            xmlHttp.onreadystatechange=function(){
                var readyState=xmlHttp.readyState;
                if(readyState==4){
                    var state=xmlHttp.status;
                    if(state==200){
                        document.getElementById("userBox").innerHTML="";

                        var xmlDoc=xmlHttp.responseXML;
                        var users=xmlDoc.getElementsByTagName("user");
                        for(var i=0;i<users.length;i++){
                            var user=users[i];
                            var id=user.getAttribute("id");
                            var name=user.getElementsByTagName("name")[0].childNodes[0].nodeValue;
                            var address=user.getElementsByTagName("address")[0].childNodes[0].nodeValue;
                            console.log("id:"+id+"name:"+name+"address:"+address);
                            creaDocDiv(id,name,address);
                        }
                    }else{
                        alert("服务器请求异常："+state);
                    }
                }

            };
            xmlHttp.send();
        }


    })();


</script>
</body>
</html>
