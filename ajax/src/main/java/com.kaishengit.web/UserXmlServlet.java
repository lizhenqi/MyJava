package com.kaishengit.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/6/21.
 */
@WebServlet("/user.xml")
public class UserXmlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/xml;charset=utf-8");

        PrintWriter out=resp.getWriter();
        out.print("<?xml version=\"1.0\" encoding=\"utf-8\"?>");

        out.print("<users>");
        out.print("<user id=\"101\"><name>tom</name><address>USA</address></user>");
        out.print("<user id=\"102\"><name>jim</name><address>CAN</address></user>");
        out.print("<user id=\"103\"><name>lee</name><address>CHINA</address></user>");
        out.print("</users>");

        out.flush();
        out.close();

    }
}
