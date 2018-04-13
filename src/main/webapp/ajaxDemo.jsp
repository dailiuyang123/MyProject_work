<%@ page language="java"  pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<script type="text/javascript">
   console.log('hello world');

</script>
<script src="resource/js/jquery-3.3.1.min.js">

</script>

<body>
<div><h2> AJAX 可以修改文本内容</h2></div>
<button id="ff">修改内容</button>

</body>
<script>

    $("#ff").click(function(){
        console.log($("button"));
        var param={id:1};
        $.ajax({
            type: 'POST',
            data:JSON.stringify(param),
            dataType:"json",
            headers: {
                'Content-Type': 'application/json;charset=UTF-8'
            },
            url:"${pageContext.request.contextPath}/user/findUserById",
            async:true,
            success:succFunction
//            success:function(result){
//            $("div").html(result);
//                                 }
        });
        //成功页面渲染
        function succFunction(tt) {
            var jsData = JSON.parse(tt);
            console.log(jsData);
            console.log('123');
            if (jsData.status == 0){
                var data = jsData.data;

                var html = '';
                for(var i in data){
//                    console.log(data[i]);
//                    console.log(data[i].answer_content);
                    // 处理，将数据渲染到页面——————纯JS渲染\JQ\vue

                    html +='<li>' + '<p class="m0">' + 'NO.'+(i+1)+ '</p>' +
                        '提问人：' + data[i].name + '&nbsp;' + data[i].ask_time + '<br>'+
                        '内容：' + data[i].ask_content + '</li>';
                }
                var oContent = $('.content');
                oContent.html(html);
            }else {
                alert('报错');
            }
        }
    });



</script>
</html>
