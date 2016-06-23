package com.kaishengit.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/6/20.
 */
@WebServlet("/checkUsername")
public class CheckUsernameServlet extends HttpServlet {
    private Logger logger= LoggerFactory.getLogger(CheckUsernameServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //服务器端处理缓存(告诉浏览器不要缓存结果)
        resp.setContentType("text/html;charset=UTF-8");
        resp.setHeader("pragma","no-cache");
        resp.setHeader("cache-control","no-cache");
        resp.setHeader("expires","0");



       String username=req.getParameter("username");
       username=new String(username.getBytes("ISO-8859-1"),"UTF-8");


        logger.debug("{}",username);
        PrintWriter printWriter=resp.getWriter();

        if("tom".equals(username)){
            printWriter.print("false");
        }else{
            printWriter.print("true");
        }
        printWriter.flush();
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String name=req.getParameter("name");
        String address=req.getParameter("address");
        logger.debug("post........{}{}",name,address);
    }
}
