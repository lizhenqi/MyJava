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
 * Created by Administrator on 2016/6/14.
 */
@WebServlet("/pay")
public class PayServlet extends HttpServlet{
    private static Logger logger= LoggerFactory.getLogger(PayServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/view/pay.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String money=req.getParameter("money");
        logger.info("成功支付{}",money);
//        req.getRequestDispatcher("WEB-INF/view/paysuc.jsp").forward(req,resp);
        resp.sendRedirect("/suc");

    }
}
