package com.kaishengit.web;

import com.kaishengit.util.HttpUtil;

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
@WebServlet("/load")
public class LoadServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String url="http://blog.sina.com.cn/rss/1220218113.xml";
        String url=req.getParameter("url");
        String xml= HttpUtil.getRequestText(url);

        resp.setContentType("text/xml;charset=utf-8");
        PrintWriter out=resp.getWriter();
        out.print(xml);
        out.flush();
        out.close();
//        http://fanyi.youdao.com/openapi.do?keyfrom=kaishengit&key=1587754017&type=data&doctype=xml&version=1.1&q=blog
    }
}
