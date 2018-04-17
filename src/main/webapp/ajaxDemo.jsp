<%@page contentType="text/html"%>
<%@ page language="java"  pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <script type="text/javascript" src="../js/jquery-3.3.1.min.js" ></script>

    <style>
        #center {
            border: 1px solid red;
            margin: 0 auto;
            height: 50px;
            width: 80px; }

    </style>
</head>
<body>
<h1>hello world ,this is my first page</h1>
<!-- 居中效果-->
<div id="center"><font>hello is me</font>  </div>
<h2><font>你好，</font><font id="userName">游客 请点击登陆按钮</font> </h2>
<button id="bu">按钮</button>
</body>

<script>
    var i='js is here';
    var base_url='http://localhost:8181/MyProject';
    console.log(i);
    //ajax 测试
    $("#bu").click(function(){
        var param={id:1};
        var real_url=base_url+'/restUser/findUserById';
        window.alert("ajax 即将执行");
        $.ajax({
            type: 'POST',
            //data:JSON.stringify(param),
            data:{"id":"1"},
            dataType:"json",
//		            headers:{
//		            	'Access-Control-Allow-Headers':'Content-Type',
//		                'Content-Type':'application/json;charset=UTF-8'
//		            },
            url:real_url,
            async:true,
            success:function(result){
                window.alert("后台响应成功");
                var data=result.data;
                var userName=data.user.real_name;
                console.log(userName);
                if(result.responseCode=='0'){
                    console.log("响应成功");
                    document.getElementById("userName").innerHTML=userName;
                    //window.location.assign("http://localhost:8080/test/jsp/index.jsp") // 改变路由地址
                }
                console.log(JSON.stringify(result));

            },
            error:function(){
                window.alert("请求失败");
            }
        });
    })
</script>

</html>