package com.yt.javaweb;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        System.out.println("这是一个post请求");
        String userName = req.getParameter("userName");
        System.out.println(userName);
        String password = req.getParameter("password");
        System.out.println(password);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String htmls = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>登陆成功</h1>\n" +
                "</body>\n" +
                "</html>";
        String html2 = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>登录失败</h1>\n" +
                "    <a href=\"http://localhost:8080/login\">返回登录页面</a>\n" +
                "</body>\n" +
                "</html>";
        PrintWriter writer = resp.getWriter();
        if ("admin".equals(userName) && "123456".equals(password)){
            writer.println(htmls);
        }else {
            writer.println(html2);
        }
        writer.flush();
        writer.close();
    }
}
