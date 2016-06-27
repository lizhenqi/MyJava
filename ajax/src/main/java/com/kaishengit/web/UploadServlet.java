package com.kaishengit.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * Created by Administrator on 2016/6/24.
 */
@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Part part=req.getPart("file");
        String fileName=getFileName(part);
        System.out.println(fileName);


    }
    public String getFileName(Part part){
        String heaer=part.getHeader("Content-Disposition");
        String  name=heaer.substring(heaer.indexOf("filename=\""),heaer.length()-1);
        name=name.substring(name.indexOf("\"")+1);
        return name;
    }
}
