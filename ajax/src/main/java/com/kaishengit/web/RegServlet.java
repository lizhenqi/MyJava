package com.kaishengit.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/6/23.
 */
@WebServlet("/reg")
public class RegServlet extends HttpServlet {

private Logger logger= LoggerFactory.getLogger(RegServlet.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String name=req.getParameter("username");
        String password=req.getParameter("password");
        String msg=req.getParameter("msg");

        logger.info(name+"->"+password+"->"+msg);


    }
}
