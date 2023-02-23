<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() +"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="<%=basePath%>/static/lib/css/bootstrap.min.css">
</head>
<body>

<div class="container" style="margin-top: 200px;">
    <h1>修改用户</h1>
    <form action="" novalidate id="form-demo">
        <input id="userId" type="hidden">
        <div class="form-group">
            <input id="username" type="text" class="form-control" required="required" placeholder="请输入用户名">
            <div class="invalid-feedback"><small>请输入用户名6-30位字母或数字！</small></div>
        </div>
        <div class="form-group">
            <input id="password" type="password" class="form-control" required="required"placeholder="请输入密码">
            <div class="invalid-feedback">密码长度至少为六位,只能是大小写字母或数字！</div>
        </div>
        <div class="form-group">
            <input id="requirePwd" type="password" class="form-control" required="required"placeholder="确认密码">
            <div class="invalid-feedback">密码长度至少为六位,只能是大小写字母或数字！</div>
        </div>
        <div class="form-group">
            <input id="age" type="text" class="form-control" required="required" placeholder="请输入年龄">
            <div class="invalid-feedback">必须是中文，且长度不小于2位！</div>
        </div>
        <div class="form-group">
            <input id="gender" type="text" class="form-control" required="required" placeholder="请输入性别 0-女 1-男">
            <div class="invalid-feedback">必须是中文，且长度不小于2位！</div>
        </div>
        <div class="form-group">
            <input id="phone" type="text" class="form-control" required="required" pattern="1[34578]\d{9}$" placeholder="请输入手机号码">
            <div class="invalid-feedback">手机号只能是11位的数字！</div>
        </div>
        <div class="form-group">
            <input id="email" type="email" class="form-control" required="required"  placeholder="输入邮箱">
            <div class="invalid-feedback">邮箱地址格式不正确！</div>
        </div>
        <button class="btn btn-primary" type="button" onclick="update()">提交</button>
        <div class="position-fixed bottom-0 right-0 p-3" style="z-index: 5; right: 50%; bottom: 50%;">
            <div id="liveToast" class="toast hide" role="alert" aria-live="assertive" aria-atomic="true" data-delay="2000">
                <div class="toast-header">
                    <div class="rounded mr-2" style="background-color: #005cbf"> </div>
                    <strong class="mr-auto">Bootstrap</strong>
                    <small>现在</small>
                    <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="toast-body">
                    Hello, world! This is a toast message.
                </div>
            </div>
        </div>
    </form>
</div>
<script src="<%=basePath%>/static/lib/js/jquery-3.6.3.min.js"></script>
<script src="<%=basePath%>/static/lib/js/bootstrap.bundle.min.js"></script>
</body>
<script>

    $(function(){
        show()
    });

    function show() {
        let user = ${user}
        console.log(user.id)
        $("#userId").val(user.id)
        $("#username").val(user.username)
        $("#password").val(user.password)
        $("#requirePwd").val(user.password)
        $("#age").val(user.age)
        $("#gender").val(user.gender)
        $("#phone").val(user.phone)
        $("#email").val(user.email)
    }


    function update() {
        // 复杂json参数格式时，要使用json字符串方式提交
        var param = {
            id:$("#userId").val(),
            username:$("#username").val(),
            password:$("#password").val(),
            requirePwd:$("#requirePwd").val(),
            age:$("#age").val(),
            gender:$("#gender").val(),
            phone:$("#phone").val(),
            email:$("#email").val(),
        };

        $.ajax({
            url:"<%=basePath%>/user",
            dataType:"json",//预计后端返回json数据
            type:"PUT",
            async:true,
            data:JSON.stringify(param),// 发送的是一个json字符串
            contentType:"application/json;charset=UTF-8",// 设置请求头描述数据内容是一个json
            success:function(data){
                //data 后端响应的数据报文
                if(data.code===9999){
                    $(".toast-body").html(data.errorMsg);
                    $('#liveToast').toast('show')
                }else{
                    location.href = "main";
                }
            },
            error:function(){
                alert("请求错误");
            }
        });
    }
</script>
</html>