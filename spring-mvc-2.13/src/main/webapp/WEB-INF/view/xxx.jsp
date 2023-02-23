<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2023/2/16
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() +"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=basePath%>/static/lib/css/layui.css" media="all">

</head>
<body>
<form class="layui-form layui-form-pane" action="" method="post">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-inline">
            <input type="text" name="username" lay-verify="username" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-inline">
            <input type="password" name="password"lay-verify="pass" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">请务必填写用户名</div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">验证码</label>
        <div class="layui-input-inline">
            <input type="text" name="username" lay-verify="username" placeholder="请输入" autocomplete="off" class="layui-input">
            <img id="reqCode" src="" onclick="initReqCode()">
        </div>
    </div>
    <div id="msg" class="layui-form-item "></div>
    <div class="layui-form-item">
        <button type="button" onclick="register()" class="layui-btn" lay-submit="" lay-filter="demo2">跳转式提交</button>
    </div>
</form>
<script src="<%=basePath%>/static/lib/js/layui.js"></script>
<script src="<%=basePath%>/static/lib/js/jquery-3.6.3.min.js"></script>
<script>
    layui.use('form', function(){
        var form = layui.form;
        form.verify({
            username: function(value, item){ //value：表单的值、item：表单的DOM对象
                if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
                    return '用户名不能有特殊字符';
                }
                if(/(^\_)|(\__)|(\_+$)/.test(value)){
                    return '用户名首尾不能出现下划线\'_\'';
                }
                if(/^\d+\d+\d$/.test(value)){
                    return '用户名不能全为数字';
                }

                //如果不想自动弹出默认提示框，可以直接返回 true，这时你可以通过其他任意方式提示（v2.5.7 新增）
                if(value === 'xxx'){
                    alert('用户名不能为敏感词');
                    return true;
                }
            }

            //我们既支持上述函数式的方式，也支持下述数组的形式
            //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
            ,pass: [
                /^[\S]{6,12}$/
                ,'密码必须6到12位，且不能出现空格'
            ]
        });
        //各种基于事件的操作，下面会有进一步介绍
    });

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
</script>
</body>
</html>
