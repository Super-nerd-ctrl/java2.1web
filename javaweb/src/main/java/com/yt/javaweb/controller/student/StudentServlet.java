package com.yt.javaweb.controller.student;

import com.alibaba.fastjson.JSON;
import com.yt.javaweb.domin.Student;
import com.yt.javaweb.service.StudentService;
import com.yt.javaweb.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "StudentServlet", urlPatterns = "/student")
public class StudentServlet extends HttpServlet {

    private StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        MyResult res = new MyResult();

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        resp.setContentType("text/json");

        List<Student> students = studentService.getAllStudent();

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", "0000");
        result.put("msg", "查询成功");
        result.put("data", students);

        String s = JSON.toJSONString(result);
        PrintWriter writer = resp.getWriter();
        writer.println(s);
        writer.flush();
        writer.close();
    }

    
}
