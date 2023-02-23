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
import java.util.Map;

/**
 * 添加学生信息
 */
@WebServlet(name = "AddStudentServlet", urlPatterns = "/addStudent")
public class AddStudentServlet extends HttpServlet {

    private StudentService studentService= new StudentServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Integer sex = Integer.parseInt(req.getParameter("sex"));
        Integer age = Integer.parseInt(req.getParameter("age"));
        String classname = req.getParameter("classname");
        Integer score = Integer.parseInt(req.getParameter("score"));
        String grade = req.getParameter("grade");

        Student student = new Student();
        student.setName(name);
        student.setSex(sex);
        student.setAge(age);
        student.setClassname(classname);
        student.setScore(score);
        student.setGrade(grade);

        Boolean Boolean = studentService.addStudent(student);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", "0000");
        result.put("msg", "查询成功");
        String s = JSON.toJSONString(result);
        PrintWriter writer = resp.getWriter();
        writer.println(s);
        writer.flush();
        writer.close();
    }
}
