package com.kaishengit.web;

import com.kaishengit.entity.User;
import com.kaishengit.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/6/15.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static Logger logger= LoggerFactory.getLogger(LoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/view/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String patchca= req.getParameter("patchca");
        String sessionPatchca= (String) req.getSession().getAttribute("patchca");

        if(patchca!=null&&patchca.equalsIgnoreCase(sessionPatchca)){

            String username=req.getParameter("username");
            String password=req.getParameter("password");

//            logger.info("{}登录成功",name);

            UserService userService=new UserService();
            User user=userService.login(username,password);

            if (user==null) {
                resp.sendRedirect("/login?err="+111);
            }else{
                logger.info("显示home界面！");
            }

        }else{
            logger.info("{}验证码错误！",patchca);
            resp.sendRedirect("/login?err="+110);
        }
    }
}
