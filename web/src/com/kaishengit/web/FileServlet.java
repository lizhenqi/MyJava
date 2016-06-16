package com.kaishengit.web;

import com.kaishengit.service.DocumentService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.UUID;

/**
 * Created by Administrator on 2016/6/16.
 */
@WebServlet("/fileload")
@MultipartConfig
public class FileServlet extends HttpServlet {
    private Logger logger= LoggerFactory.getLogger(FileServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/fileLogin.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String filedes=req.getParameter("filedes");
        logger.debug("文件说明：{}上传。",filedes);

        Part part=req.getPart("filename");

        InputStream input=part.getInputStream();


        DocumentService documentService=new DocumentService();

        documentService.updateFile(getFileName(part),part.getSize(),input);


//        String fileType=part.getContentType();
//        logger.debug("文件类型：{}",fileType);
//
//        Long filesize=part.getSize();
//        logger.debug("文件大小：{}",filesize);
//
//        String header=part.getHeader("Content-Disposition");
//        logger.debug("头：{}",header);
//
//        String filename=getFileName(part);
//        logger.debug("文件名：{}",filename);
//        //form-data; name="filename"; filename="1e30e924b899a901c32ae3c21c950a7b0208f523.jpg"
//
//        saveFile(part);

    }
//    public void saveFile(Part part) throws IOException {
//        File file=new File("D:/test");
//        if(!file.exists()){
//            file.mkdir();
//        }
//        //改名
//        String pname=getFileName(part);
//        String exname=pname.substring(pname.indexOf("."));
//        String uuid=UUID.randomUUID().toString();
//        pname=uuid+exname;
//
//        InputStream inputStream= part.getInputStream();
////        FileOutputStream outputStream=new FileOutputStream(new File(file,getFileName(part)));
//        FileOutputStream outputStream=new FileOutputStream(new File(file,pname));
//
//        IOUtils.copy(inputStream,outputStream);//它不提供关闭需要手动关闭
//
//        inputStream.close();
//        outputStream.flush();
//        outputStream.close();
//
//
////        BufferedInputStream input=new BufferedInputStream(inputStream);
////        BufferedOutputStream output=new BufferedOutputStream(outputStream);
////
////        byte[] buffer=new byte[1024];
////        int len=-1;
////        while ((len=input.read(buffer))!=-1) {
////            output.write(buffer,0,len);
////        }
////        input.close();
////        output.flush();
////        output.close();
//    }


    public String getFileName(Part part){
        String heaer=part.getHeader("Content-Disposition");
        String  name=heaer.substring(heaer.indexOf("filename=\""),heaer.length()-1);
        name=name.substring(name.indexOf("\"")+1);
        return name;
    }
}
