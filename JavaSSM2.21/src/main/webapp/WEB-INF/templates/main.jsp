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
<div>
    <h2 style="text-align: center">用户管理</h2>
    <div>
        <form class="form-inline">
            <div class="form-group mx-sm-3 mb-2">
                ID：<input type="text" class="form-control" id="userId" placeholder="ID">
                用户名：<input type="text" class="form-control" id="username" placeholder="用户名（支持模糊查询）">
                电话：<input type="text" class="form-control" id="phone" placeholder="电话">
            </div>
            <button type="button" class="btn btn-primary mb-2" onclick="conditionQuery()">查询</button>
        </form>
    </div>
    <table class="table table-light" id="studentTable">
    </table>
    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
            <li class="page-item"><a class="page-link" href="#" onclick="initTable(1)">1</a></li>
            <li class="page-item"><a class="page-link" href="#" onclick="initTable(2)">2</a></li>
            <li class="page-item"><a class="page-link" href="#" onclick="initTable(3)">3</a></li>
            <li class="page-item"><a class="page-link" href="#">Next</a></li>
        </ul>
    </nav>
    <button type="button" class="btn btn-primary" onclick="register()">添加用户信息</button>
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
</div>

<script src="<%=basePath%>/static/lib/js/jquery-3.6.3.min.js"></script>
<script src="<%=basePath%>/static/lib/js/bootstrap.bundle.min.js"></script>

</body>
<script>
    $(function(){

        //初始化查询表格数据
        initTable(1);

    });

    function initTable(page){
        $("#studentTable").html("");
        var htmls = "<tr>"+
            "<th scope="+"col"+">ID</th>"+
            "<th scope="+"col"+">用户名</th>"+
            "<th scope="+"col"+">性别</th>"+
            "<th scope="+"col"+">年龄</th>"+
            "<th scope="+"col"+">邮箱</th>"+
            "<th scope="+"col"+">电话</th>"+
            "<th scope="+"col"+">角色</th>"+
            "<th class='toshow' scope="+"col"+">操作</th>"+
            "</tr>";

        $.ajax({
            url:"<%=basePath%>/user/allUsers/"+page+"/5",
            type:"GET",
            async:false,
            success:function(data){
                if(data.code===0){
                    console.log(data)
                    for(var i in data.data.list){
                        let num = data.data.list[i].id
                        htmls += "<tr>"+
                            "<th class='studentId' scope='row'>"+data.data.list[i].id+"</th>"+
                            "<td>"+data.data.list[i].username+"</td>"+
                            "<td>"+data.data.list[i].gender+"</td>"+
                            "<td>"+data.data.list[i].age+"</td>"+
                            "<td>"+data.data.list[i].email+"</td>"+
                            "<td>"+data.data.list[i].phone+"</td>"+
                            "<td>"+data.data.list[i].isAdmin+"</td>"+
                            "<td>"+
                            "<a href='' onclick='isUpdate(\""+data.data.list[i].id+"\");return false'>"+" 修改"+"</a>"+
                            "<a href='' onclick='isDelete(\""+data.data.list[i].id+"\");return false'>"+" 删除"+"</a>"+
                            "</td>"+
                            "</tr>";
                    }

                    $("#studentTable").html(htmls);

                }else{
                    alert(data.msg);
                }
            },
            error:function(){
                alert("请求错误");
            }
        });
    }

    function conditionQuery() {
        $("#studentTable").html('');
        var htmls = "<tr>"+
            "<th scope="+"col"+">ID</th>"+
            "<th scope="+"col"+">用户名</th>"+
            "<th scope="+"col"+">性别</th>"+
            "<th scope="+"col"+">年龄</th>"+
            "<th scope="+"col"+">邮箱</th>"+
            "<th scope="+"col"+">电话</th>"+
            "<th scope="+"col"+">角色</th>"+
            "<th scope="+"col"+">操作</th>"+
            "</tr>";
        // 复杂json参数格式时，要使用json字符串方式提交
        var param = {
            userIdStr:$("#userId").val(),
            username:$("#username").val(),
            phone:$("#phone").val()
        };
        $.ajax({
            url:"<%=basePath%>/user/conditionQuery",
            dataType:"json",//预计后端返回json数据
            type:"POST",
            async:true,
            data:JSON.stringify(param),// 发送的是一个json字符串
            contentType:"application/json;charset=UTF-8",// 设置请求头描述数据内容是一个json
            success:function(data){
                //data 后端响应的数据报文
                if(data.code===0){
                    console.log(data)
                    for(var i in data.data){
                        var num = data.data[i].id
                        htmls += "<tr>"+
                            "<th class='studentId' scope='row'>"+data.data[i].id+"</th>"+
                            "<td>"+data.data[i].username+"</td>"+
                            "<td>"+data.data[i].gender+"</td>"+
                            "<td>"+data.data[i].age+"</td>"+
                            "<td>"+data.data[i].email+"</td>"+
                            "<td>"+data.data[i].phone+"</td>"+
                            "<td>"+data.data[i].isAdmin+"</td>"+
                            "<td>"+
                            "<a class='update' onclick='isUpdate(\""+data.data[i].id+"\");return false' href='Http://localhost:8080/user/toUpdate/"+data.data[i].id+"' >"+" 修改"+"</a>"+
                            "<a href='' onclick='isDelete(\""+data.data[i].id+"\");return false'>"+" 删除"+"</a>"+
                            "</td>"+
                            "</tr>";
                    }
                    $("#studentTable").html(htmls);
                }else{
                    $(".toast-body").html(data.errorMsg)
                    $('#liveToast').toast('show')
                    // $("#msg").html('登录成功');
                    // location.href = "user/main";
                }
            },
            error:function(){
                alert("请求错误");
            }
        });
    }

    function register() {
        $.ajax({
            url:"<%=basePath%>/user/isAdmin",
            dataType:"json",//预计后端返回json数据
            type:"GET",
            async:true,
            success:function(data){
                //data 后端响应的数据报文
                if(data.code=== 0){
                    // $("#reqCode").attr("src",data.data);
                    console.log(data)
                    location.href = '/user/toAdd';
                }else{
                    $(".toast-body").html(data.errorMsg);
                    $('#liveToast').toast('show')
                }
            },
            error:function(){
                alert("请求错误");
            }
        });
    }

    function isDelete(id) {
        $.ajax({
            url:"<%=basePath%>/user/isAdmin",
            dataType:"json",//预计后端返回json数据
            type:"GET",
            async:true,
            success:function(data){
                //data 后端响应的数据报文
                if(data.code=== 0){
                    deleteUser(id)
                }else{
                    $(".toast-body").html(data.errorMsg)
                    $('#liveToast').toast('show')
                }
            },
            error:function(){
                alert("请求错误");
            }
        });
    }

    function deleteUser(id){
        $.ajax({
            url:"<%=basePath%>/user/deleteUser/" + id,
            dataType:"json",//预计后端返回json数据
            type:"DELETE",
            async:true,
            success:function(data){
                //data 后端响应的数据报文
                if(data.code=== 0){
                    // $("#reqCode").attr("src",data.data);
                    // console.log(data)
                    location.href = 'main';
                }else{
                    $(".toast-body").html(data.errorMsg)
                    $('#liveToast').toast('show')
                }
            },
            error:function(){
                alert("请求错误");
            }
        });
    }

    function isUpdate(id) {
        $.ajax({
            url:"<%=basePath%>/user/isUpdate/" + id,
            dataType:"json",//预计后端返回json数据
            type:"GET",
            async:true,
            success:function(data){
                //data 后端响应的数据报文
                if(data.code=== 0){
                    location.href = 'toUpdate/' + id
                }else{
                    $(".toast-body").html(data.errorMsg)
                    $('#liveToast').toast('show')
                }
            },
            error:function(){
                alert("请求错误");
            }
        });
    }

    <%--function updateUser(id) {--%>
    <%--    $.ajax({--%>
    <%--        url:"<%=basePath%>/user/toUpdate/" + id,--%>
    <%--        dataType:"json",//预计后端返回json数据--%>
    <%--        type:"GET",--%>
    <%--        async:true,--%>
    <%--        success:function(data){--%>
    <%--            //data 后端响应的数据报文--%>
    <%--            if(data.code=== 0){--%>

    <%--            }else{--%>
    <%--                $(".toast-body").html(data.errorMsg)--%>
    <%--                $('#liveToast').toast('show')--%>
    <%--            }--%>
    <%--        },--%>
    <%--        error:function(){--%>
    <%--            alert("请求错误");--%>
    <%--        }--%>
    <%--    });--%>
    <%--}--%>
</script>
</html>