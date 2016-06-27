package com.kaishengit.web;

import com.google.gson.Gson;
import com.kaishengit.entity.Message;
import com.kaishengit.service.MessageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Administrator on 2016/6/24.
 */
@WebServlet("/message")
public class MessageServlet extends HttpServlet {
    MessageService messageService=new MessageService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Message> messageList=messageService.findAll();
        int maxId=0;
        if(messageList!=null){
            maxId=messageList.get(0).getId();
        }
        req.setAttribute("maxId",maxId);

        req.setAttribute("messageList",messageList);
        req.getRequestDispatcher("/WEB-INF/view/message.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");


        int maxId=new Integer(req.getParameter("maxId"));

        List<Message> messageList=messageService.findMaxId(maxId);

        resp.setContentType("application/json;charset:utf-8");

        PrintWriter out= resp.getWriter();
        out.print(new Gson().toJson(messageList));
        out.flush();
        out.close();
    }
}
