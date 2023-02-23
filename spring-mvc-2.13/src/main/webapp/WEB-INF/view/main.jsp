<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/2/13
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() +"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
%>
<html>
<head>
    <title>Title</title>
    <script src="<%=basePath%>/static/lib/js/jquery-3.6.3.min.js"></script>
</head>
<body>
<form action="/test/register" method="post">
    用户名：<input id="userName" type="text" name="userName"><br>
    密码：<input id="password" type="password" name="password"><br>
    验证码：<input id="reqCodeText" class="input-item" type="text" name="reqCodeText">
    <div id="msg"></div>
    <img id="reqCode" src="" onclick="initReqCode()">
    <button type="button" onclick="register()">注册</button>
</form>
</body>
<script>
    function register() {
        // 复杂json参数格式时，要使用json字符串方式提交
        var param = {
            userName:$("#userName").val(),
            password:$("#password").val(),
            reqCodeText:$("#reqCodeText").val()
        };

        $.ajax({
            url:"<%=basePath%>/test/register",
            dataType:"json",//预计后端返回json数据
            type:"POST",
            async:true,
            data:JSON.stringify(param),// 发送的是一个json字符串
            contentType:"application/json;charset=UTF-8",// 设置请求头描述数据内容是一个json
            success:function(data){
                //data 后端响应的数据报文
                if(data.success==false){
                    $("#msg").html(data.errorMsg);
                }else{
                    $("#msg").html('');
                    location.href = "xxx";
                }
            },
            error:function(){
                alert("请求错误");
            }
        });
    }

    $(function(){

        initReqCode();
    });

    function initReqCode(){
        $.ajax({
            url:"<%=basePath%>/test/code",
            dataType:"json",//预计后端返回json数据
            type:"GET",
            async:true,
            success:function(data){
                //data 后端响应的数据报文
                if(data.success== true){
                    $("#reqCode").attr("src",data.data);
                }else{
                    $("#msg").html('');
                }
            },
            error:function(){
                alert("请求错误");
            }
        });
    }
</script>
</html>
