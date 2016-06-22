<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<input type="text" id="username"><span id="help"></span>

<script>

    (function () {

        function creXmlHttp() {
            var xmlHttp = null;
            if (window.ActiveXObject) {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            } else {
                xmlHttp = new XMLHttpRequest();
            }
            return xmlHttp;
        }

        document.querySelector("#username").onblur = function () {
            //console.log("...........");


            var username = this.value;

            var xmlHttp = creXmlHttp();

//            xmlHttp.open("GET", "/checkUsername?username=" + username, true);
            xmlHttp.open("GET","/checkUsername?username="+encodeURIComponent(username));

            xmlHttp.onreadystatechange = function () {
                var readyState = xmlHttp.readyState;

                if (readyState == 4) {
                    var httpState = xmlHttp.status;

                    if (httpState == 200) {
                        var result = xmlHttp.responseText;
                        if (result == "yes") {
                            document.querySelector("#help").innerText = "√"
                        } else {
                            document.querySelector("#help").innerText = "X"
                        }
                    } else {
                        alert("服务器请求异常："+ httpState);
                    }

                }
            };

            xmlHttp.send();
        }


    })();


</script>

</body>
</html>
