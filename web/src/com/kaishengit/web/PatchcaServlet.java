package com.kaishengit.web;

import org.patchca.background.SingleColorBackgroundFactory;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2016/6/15.
 */
@WebServlet( "/patchca.jpg")
public class PatchcaServlet extends HttpServlet {
    private static Logger logger= LoggerFactory.getLogger(PayServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConfigurableCaptchaService service=new ConfigurableCaptchaService();
        service.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        service.setBackgroundFactory(new SingleColorBackgroundFactory(new Color(255, 255, 255)));
        service.setFilterFactory(new CurvesRippleFilterFactory());

        OutputStream outputStream=response.getOutputStream();
        String msg=EncoderHelper.getChallangeAndWriteImage(service,"jpg",outputStream);
        //logger.info("验证码为：{}",msg);

        HttpSession session=request.getSession();
        session.setAttribute("patchca",msg);

        outputStream.flush();
        outputStream.close();
    }
}
