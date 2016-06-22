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
 */@WebServlet("/ajax")
public class AjaxServlet extends HttpServlet {
    private Logger logger= LoggerFactory.getLogger(AjaxServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Ajax get......");

        PrintWriter printWriter=resp.getWriter();
        printWriter.print("rose");
        printWriter.flush();
        printWriter.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       logger.debug("Ajax post.....");
        PrintWriter printWriter=resp.getWriter();
        printWriter.print("PHP");
        printWriter.flush();
        printWriter.close();
    }
}
