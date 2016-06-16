package com.kaishengit.web;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/6/16.
 */
public class UploadServlet extends HttpServlet{
    private Logger logger= LoggerFactory.getLogger(UploadServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/upload.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
//        String filedes=req.getParameter("filedes");使用servlet2.5以后不能用注解，也不能这样获取了，要想判断
//        logger.debug("文件描述：{}",filedes);
        if(ServletFileUpload.isMultipartContent(req)){


        }
    }
}
