<%@ page language="java"  pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
    console.log("hello world");
</script>
<body style="margin:auto">


<form name= "myform" method = 'post'  action =  "${pageContext.request.contextPath }/user/findAll.do"  >

    <div  style="width: 500px; margin: 0px auto">
        <h2>用户登录页</h2>
<table >
    <tr>
        <td> 用户名</td>
        <td><input type="text" name="user.name"></td>
    </tr>

    <tr>
        <td>密码</td>
        <td><input type="password" name="user.password"></td>
    </tr>
    <tr>

        <td><input type="submit" value="登录"></td>
    </tr>
</table>
    </div>
</form>
<h1>Excell导出</h1>
<hr>
<form name="excellexport" method="post" action="${pageContext.request.contextPath}/Excell/exportExcell">
    <input type="submit" value="导出">
</form>

<h1>多sheet页导出Excell</h1>
<hr>
<form name="excellexporta" method="post" action="${pageContext.request.contextPath}/Excell/exportReport">
    <input type="submit" value="导出">
</form>

<h1>world 文档导出</h1>
<hr>
<form name="excellworld" method="post" action="${pageContext.request.contextPath}/word/exportWorld">
    <input type="submit" value="导出">
</form>

<h1>文件上传</h1>
<hr>
<form method="post" name="filedowload" action="${pageContext.request.contextPath}/files/upload" enctype="multipart/form-data">
    <input type="file" name="file" value="上传">
    <input type="submit" value="确定">
</form>

<h1>图片上传</h1>
<hr>
<form method="post" name="filedowload" action="${pageContext.request.contextPath}/files/uploadImage" enctype="multipart/form-data">
    <input type="file" name="file" value="上传图片">
    <input type="submit" value="确定">
</form>
</body>
</html>
