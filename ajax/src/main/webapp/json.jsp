
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button id="btn">GET提交测试</button>
<ol id="tab">

</ol>


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

        document.querySelector("#btn").onclick=function(){
            var xmlHttp=creaXmlHttp();
            xmlHttp.open("get","/user.json");

            xmlHttp.onreadystatechange=function(){
                if(xmlHttp.readyState==4){
                    if(xmlHttp.status==200){
                       var msg=xmlHttp.responseText;
                        var json=JSON.parse(msg);
//                        console.log(json.length);
                    for(var i=0;i<json.length;i++){
                        var msg=json[i];
                        var name=msg.name;
                        var address=msg["address"];

                        var li=document.createElement("li");
                        var text=document.createTextNode(name+"->>>"+address);
                        li.appendChild(text);
                        document.querySelector("#tab").appendChild(li);

                    }



                    }else{
                        alert("服务器请求异常",xmlHttp.status);
                    }
                }
            };

            xmlHttp.send();
        };









//        var arr=[
//            {
//                "username":"tom",
//                "address":"China"
//            },
//            {
//                "username":"lee",
//                "address":"USA"
//            }
//        ];
//        for(var i=0;i<arr.length;i++){
//            var msg=arr[i];
//            alert(msg.username+"-->>"+msg.address);
//        }
//
//
//
//        var object={"name":"java","gender":"man","age":10};
//        alert(object.name+":"+object.gender+":"+object.age);
//        alert(object["name"]);






    })();

</script>
</body>
</html>
