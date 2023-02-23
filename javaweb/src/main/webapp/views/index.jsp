<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <script src="../lib/jquery-3.6.3.min.js"></script>
    <style>
        #studentTable {
            text-align: center;
            color: #333333;
            border-width: 1px;
            border-color: #666666;
            border-collapse: collapse;
        }
        #studentTable th {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #dedede;
        }

        #studentTable td {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #ffffff;
        }

        .addAndUpdate {
            display: none;
            position: absolute;
            left: 50%;
            top: 50%;
        }
    </style>
</head>
<body>
<h1>欢迎：<%= session.getAttribute("userName") %>
</h1>
<br/>
<a href="Http://localhost:8080/logout">退出登录</a>
<button onclick=formShow()>添加学生信息</button>
<form class="addAndUpdate">
    <input id="id" type="hidden" name="id">
    姓名：<input id="name" type="text" name="name"><br>
    年龄：<input id="age" type="text" name="age"><br>
    性别：<input id="sex" type="text" name="sex"><br>
    班级：<input id="classname" type="text" name="classname"><br>
    成绩：<input id="score" type="text" name="score"><br>
    成绩等级：<input id="grade" type="text" name="grade">
    <button type="button" onclick="choice()">保存</button>
</form>
<table id="studentTable">
</table>

</body>
<script>

    $(function(){

        //初始化查询表格数据
        initTable();

    });

    function initTable(){
        $("#studentTable").html("");
        var htmls = "<tr>"+
            "<th>编号</th>"+
            "<th>姓名</th>"+
            "<th>性别</th>"+
            "<th>年龄</th>"+
            "<th>班级</th>"+
            "<th>分数</th>"+
            "<th>分数等级</th>"+
            "<th>操作</th>"+
            "</tr>";

        $.ajax({
            url:"Http://localhost:8080/student",
            type:"GET",
            async:false,
            success:function(data){
                if(data.code=="0000"){
                    for(var i in data.data){
                        let num = data.data[i].id
                        htmls += "<tr>"+
                            "<td class='studentId'>"+data.data[i].id+"</td>"+
                            "<td>"+data.data[i].name+"</td>"+
                            "<td>"+data.data[i].sex+"</td>"+
                            "<td>"+data.data[i].age+"</td>"+
                            "<td>"+data.data[i].classname+"</td>"+
                            "<td>"+data.data[i].score+"</td>"+
                            "<td>"+data.data[i].grade+"</td>"+
                            "<td>"+
                            "<a class='update' href='' onclick='updateShow(\""+data.data[i].id+"\");return false'>"+" 修改"+"</a>"+
                            "<a href='Http://localhost:8080/delStudent?id="+data.data[i].id+"' onclick='show_confirm()'>"+" 删除"+"</a>"+
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

    function test(data) {
        console.dir(data.stuid)
        console.dir($(".update").data("stuid"))
    }

    function formShow() {
        $(".addAndUpdate").show()

    }

    function choice() {
        if ($("#id").val() !== '') {
            updateStudent(id)
        }else {
            addStudent()
        }


        // updateStudent()
    }
    function updateStudent() {
        $.ajax({
            type: "POST",        // 请求方式
            url: "Http://localhost:8080/updateStudent", // 请求路径
            dataType: "json",   // 预期返回一个 json 类型数据
            data: {
                "id" : $("#id").val(),
                "name" : $("#name").val(),
                "sex" : $("#sex").val(),
                "age" : $('#age').val(),
                "classname" : $('#classname').val(),
                "score" : $('#score').val(),
                "grade" : $('#grade').val(),
            },
            success: function (data) {   // data是形参名，代表返回的数据
                if (data.code == "9999"){
                    $(".error").show().delay(2000).fadeOut();
                }
                if (data.code == "0000") {
                    location.href = "index.jsp"
                    $(".right").show().delay(2000).fadeOut();
                }
            }
        });
    }
    function addStudent() {
        $.ajax({
            type: "POST",        // 请求方式
            url: "Http://localhost:8080/addStudent", // 请求路径
            dataType: "json",   // 预期返回一个 json 类型数据
            data: {
                "name" : $("#name").val(),
                "sex" : $("#sex").val(),
                "age" : $('#age').val(),
                "classname" : $('#classname').val(),
                "score" : $('#score').val(),
                "grade" : $('#grade').val(),
            },
            success: function (data) {   // data是形参名，代表返回的数据
                if (data.code == "9999"){
                    $(".error").show().delay(2000).fadeOut();
                }
                if (data.code == "0000") {
                    location.href = "index.jsp"
                    $(".right").show().delay(2000).fadeOut();
                }
            }
        });
    }

    function delStudent(studentId) {
        $.ajax({
            type: "GET",        // 请求方式
            url: "Http://localhost:8080/delStudent", // 请求路径
            dataType: "json",   // 预期返回一个 json 类型数据
            data: {
                "id" : studentId
            },
            success: function (data) {   // data是形参名，代表返回的数据
                if (data.code == "9999"){
                    $(".error").show().delay(2000).fadeOut();
                }
                if (data.code == "0000") {
                    location.href = "index.jsp"
                    $(".right").show().delay(2000).fadeOut();
                }
            }
        });
    }

    function show_confirm()
    {
        var r=confirm("确定要删除学生信息吗");
        if (r==true)
        {
            alert("成功删除");
        }
        else
        {
            alert("删除失败");
        }
    }

    function updateShow(id) {
        $.ajax({
            url:"Http://localhost:8080/updateStudent?id="+ id,
            type:"GET",
            async:false,
            success:function(data){
                console.log(data)
                $("#name").text(data.data.name)
                if(data.code=="0000"){
                    $(".addAndUpdate").show()
                    for(var i in data.data){
                        $("#id").val(data.data[i].id)
                        $("#name").val(data.data[i].name)
                        $("#age").val(data.data[i].age)
                        $("#sex").val(data.data[i].sex)
                        $("#classname").val(data.data[i].classname)
                        $("#score").val(data.data[i].score)
                        $("#grade").val(data.data[i].grade)
                    }

                    // location.href = "/index.jsp"
                }else{
                    alert(data.msg);
                }
            },
            error:function(){
                alert("您输入的学号不存在");
            }
        });



    }
</script>
</html>