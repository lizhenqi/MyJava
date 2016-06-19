package com.kaishengit.web;

import com.kaishengit.entity.Movie;
import com.kaishengit.service.MovieService;
import com.kaishengit.util.Page;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/6/17.
 */
@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pageNo=req.getParameter("p");
        int p=1;

        if (StringUtils.isNumeric(pageNo)) {
            p=new Integer(pageNo);
//            if(p<=0){//这都在MovieService里面判断
//                p=1;
//            }
//            if(p>69){
//                p=69;
//            }
        }



        MovieService movieService=new MovieService();
//        List<Movie> movieList=movieService.findAllMovie();

        Page<Movie> page=movieService.findMoviePage(p);
        req.setAttribute("page",page);

        req.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(req,resp);
    }
}
