
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">

</head>
<body>

    <div class="container">
        <h3 style="margin-left: 400px">电子词典</h3>
    </div>
    <div class="form-group" style="margin-left: 300px">
        <input type="text" id="word" class="col-lg-5" placeholder="请输入要查询的词...">
        <button id="btn">翻译</button>

        <p id="result1"></p>
        <p id="result2"></p>
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

//        enter获取点击翻译都能翻译
        document.querySelector("#word").onkeyup=function(event){
           if(event.keyCode==13){
               var word=this.value;
               var xmlHttp=creaXmlHttp();

               xmlHttp.open("get","/dict?q="+encodeURIComponent(word),true);

                xmlHttp.onreadystatechange=function(){
                    if(xmlHttp.readyState==4){
                        if(xmlHttp.status==200){
                            var xmlDoc=xmlHttp.responseXML;

//                               if(xmlDoc.contains(basic)){
//                                   var basic=xmlDoc.getElementsByTagName("basic")[0];
//                                   var explain=basic.getElementsByTagName("explain")[0];
//                                   var ex=explain.getElementsByTagName("ex")[0].childNodes[0].nodeValue;
//                                   document.querySelector("#result1").innerText="基本释义："+ex;
//                               }

                            var translation=xmlDoc.getElementsByTagName("translation")[0];
                            var paragraph = translation.getElementsByTagName("paragraph")[0].childNodes[0].nodeValue;

                            document.querySelector("#result2").innerText="网络释义："+paragraph;


                        }else{
                            alert("服务器请求异常："+xmlHttp.status);
                        }
                    }

                };
               xmlHttp.send();
           }
        }




        document.querySelector("#btn").onclick=function(){

            var word=document.querySelector("#word").value;
            var xmlHttp=creaXmlHttp();

            xmlHttp.open("get","/dict?q="+encodeURIComponent(word),true);

            xmlHttp.onreadystatechange=function(){
                if(xmlHttp.readyState==4){
                    if(xmlHttp.status==200){
                        var xmlDoc=xmlHttp.responseXML;

//                               if(xmlDoc.contains(basic)){
//                                   var basic=xmlDoc.getElementsByTagName("basic")[0];
//                                   var explain=basic.getElementsByTagName("explain")[0];
//                                   var ex=explain.getElementsByTagName("ex")[0].childNodes[0].nodeValue;
//                                   document.querySelector("#result1").innerText="基本释义："+ex;
//                               }

                        var translation=xmlDoc.getElementsByTagName("translation")[0];
                        var paragraph = translation.getElementsByTagName("paragraph")[0].childNodes[0].nodeValue;

                        document.querySelector("#result2").innerText="网络释义："+paragraph;


                    }else{
//                            alert("服务器请求异常："+xmlHttp.status);
                        document.querySelector("#result2").innerText="网络释义：不好意思，没查询到！";
                    }
                }

            };
            xmlHttp.send();
        }
    })();


</script>
</body>
</html>
