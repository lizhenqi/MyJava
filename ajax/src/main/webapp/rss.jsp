
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <div class="page-header">
            <h1>RSS</h1>
        </div>
        <input type="text" class="col-xs-3" placeholder="请输入url..." id="url">
        <button id="btn" type="submit">登录</button>
        <ul class="list-unstyled" id="list">

        </ul>


    </div>    


<script>
    (function () {
        function creaXmlHttp(){
            var xmlHttp=null;
            if(window.ActiveXObject){
                xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
            }else{
                xmlHttp=new XMLHttpRequest();
            }
            return xmlHttp;
        }
        function createLi(link,title){
            var li=document.createElement("li");
            var a=document.createElement("a");

            var liText=document.createTextNode(title);


            a.setAttribute("href",link);
            a.setAttribute("target","_blank");//另起一页。
            a.appendChild(liText);
            li.appendChild(a);

            document.getElementById("list").appendChild(li);


        }
        document.querySelector("#btn").onclick=function(){
            var xmlHttp=creaXmlHttp();
            document.querySelector("#list").innerHTML="";
            var url=document.querySelector("#url").value;

            xmlHttp.open("get","/load?url="+url,true);
            xmlHttp.onreadystatechange= function () {
              if(xmlHttp.readyState==4){
                  if(xmlHttp.status=200){
                     var xmlDoc=xmlHttp.responseXML;
                     var itemArray=xmlDoc.getElementsByTagName("item");

                      for(var i=0;i<itemArray.length;i++){
                        var item=itemArray[i];
                        var title=item.getElementsByTagName("title")[0].childNodes[0].nodeValue;
                        var link=item.getElementsByTagName("link")[0].childNodes[0].nodeValue;

//                      alert(title+":"+link);

                        createLi(link,title);
                     }
                  }else{
                      alert("服务器请求异常："+xmlHttp.status);
                  }
              }
            };
            xmlHttp.send();
        }




    })();

</script>
</body>
</html>
