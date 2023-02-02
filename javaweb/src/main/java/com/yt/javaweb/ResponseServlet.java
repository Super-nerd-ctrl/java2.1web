package com.yt.javaweb;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String userName = req.getParameter("userName");
        System.out.println("userName = " + userName);
        String password = req.getParameter("password");
        System.out.println("password = " + password);

        resp.setContentType("text/json;charset=UTF-8");
        User user = new User();
        user.setAge(18);
        user.setUsername("哈哈哈");
        user.setPassword("123456");

        String email = (String)req.getAttribute("email");
        user.setEmail(email);
        String s = JSON.toJSONString(user);
        PrintWriter writer = resp.getWriter();
        writer.println(s);
    }
}
