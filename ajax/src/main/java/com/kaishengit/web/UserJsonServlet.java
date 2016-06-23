package com.kaishengit.web;

import com.google.gson.Gson;
import com.kaishengit.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/23.
 */
@WebServlet("/user.json")
public class UserJsonServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Thread.sleep(4000);//测试延时（$.ajax的 timeout）
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<User> list=new ArrayList<>();
        list.add(new User(11,"Lee","USA",99.6f));
        list.add(new User(11,"jim","China",99.6f));

        String name=req.getParameter("name");

        resp.setContentType("application/json;charset=utf-8");

        PrintWriter out=resp.getWriter();
        out.print(new Gson().toJson(name));
        out.flush();
        out.close();


    }
}
