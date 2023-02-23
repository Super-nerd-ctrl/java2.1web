package com.yt.javaweb.controller.student;

import com.yt.javaweb.service.StudentService;
import com.yt.javaweb.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "delStudentServlet", urlPatterns = "/delStudent")
public class delStudentServlet extends HttpServlet {

    private StudentService studentService = new StudentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        studentService.deleteStudent(Integer.parseInt(id));

        resp.sendRedirect("/views/index.jsp");
    }
}
