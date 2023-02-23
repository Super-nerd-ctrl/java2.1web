<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() +"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="<%=basePath%>/static/lib/css/bootstrap.min.css">
</head>
<body>

<div class="container" style="margin-top: 200px;">
    <h1>登录</h1>
    <form action="/user/login" novalidate id="form-demo" method="post">
        <div class="form-group">
            <input id="username" type="text" class="form-control" required="required" placeholder="请输入用户名" pattern="[A-Za-z0-9]{5,30}">
            <div class="invalid-feedback"><small>请输入用户名5-30位字母或数字！</small></div>
        </div>
        <div class="form-group">
            <input id="password" type="password" class="form-control" required="required" pattern="[A-Za-z0-9]{6,30}" placeholder="请输入密码">
            <div class="invalid-feedback">密码长度至少为六位,只能是大小写字母或数字！</div>
        </div>
        <div class="form-group">
            <input id="reqCodeText" type="text" class="form-control" placeholder="请输入验证码" >
            <img id="reqCode" onclick="initReqCode()" src="" alt="">
<!--            <div class="invalid-feedback"><small>请输入用户名5-30位字母或数字！</small></div>-->
        </div>
        <button class="btn btn-primary" type="button" onclick="login()">提交</button>
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
    $(function () {
        $("#form-demo").submit(function(event){
            const f=$(this)
            if(f[0].checkValidity()===false){
                event.preventDefault()
                event.stopPropagation()
            }
            f.addClass("was-validated")
        })

    })

    $(function(){
        initReqCode();
    });

    function initReqCode(){
        $.ajax({
            url:"<%=basePath%>/user/code",
            dataType:"json",//预计后端返回json数据
            type:"GET",
            async:true,
            success:function(data){
                //data 后端响应的数据报文
                if(data.code=== 0){
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

    function login() {
        // 复杂json参数格式时，要使用json字符串方式提交
        var param = {
            username:$("#username").val(),
            password:$("#password").val(),
            reqCodeText:$("#reqCodeText").val()
        };

        $.ajax({
            url:"<%=basePath%>/user/login",
            dataType:"json",//预计后端返回json数据
            type:"POST",
            async:true,
            data:JSON.stringify(param),// 发送的是一个json字符串
            contentType:"application/json;charset=UTF-8",// 设置请求头描述数据内容是一个json
            success:function(data){
                //data 后端响应的数据报文
                if(data.code===9999){
                    $(".toast-body").html(data.errorMsg);
                    $('#liveToast').toast('show')
                }else{
                    $("#msg").html('登录成功');
                    location.href = "user/main";
                }
            },
            error:function(){
                alert("请求错误");
            }
        });
    }
</script>
</html>