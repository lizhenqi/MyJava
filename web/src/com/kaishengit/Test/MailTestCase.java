package com.kaishengit.Test;

import com.kaishengit.exception.DataAccessException;
import com.kaishengit.util.EmailUtil;
import org.apache.commons.mail.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2016/6/19.
 */
public class MailTestCase {
    private Logger logger= LoggerFactory.getLogger(MailTestCase.class);

    @Test
    public void testSendMail(){
        //发送文本


        //法一
//        SimpleEmail simpleEmail=new SimpleEmail();
//        simpleEmail.setHostName("smtp.163.com");
//        simpleEmail.setAuthentication("用户名","授权码");
//        simpleEmail.setCharset("utf-8");
//
//        try {
//            simpleEmail.setFrom("发送者@163.com");
//            simpleEmail.setSubject("邮件发送测试");
//            simpleEmail.setMsg("收到回复吗？");
//            simpleEmail.addTo("接收者@qq.com");
//            simpleEmail.send();
//            logger.info("发送成功！");
//        } catch (EmailException e) {
//            throw new DataAccessException("发送失败",e);
//        }

        //法二调用已经下好的方法：EmailUtil
        EmailUtil emailUtil=new EmailUtil();
        emailUtil.sendTestEmail("ceshi","ceshi","接收者@qq.com");


    }
    @Test
    public void testSendHtml(){

        //方法一
        //发送Html文件
//        HtmlEmail simpleEmail=new HtmlEmail();//注意这个
//        simpleEmail.setHostName("smtp.163.com");
//        simpleEmail.setAuthentication("用户名","授权码");
//        simpleEmail.setCharset("utf-8");
//        try {
//            simpleEmail.setFrom("发送者@163.com");
//            simpleEmail.setSubject("HTML测试");
//            simpleEmail.setHtmlMsg("<div style='color:blue'>测试</div><img src='http://ww1.sinaimg.cn/mw690/6b6e567cgw1f4x4aks870j20c80c8jrn.jpg'/>");
//            simpleEmail.addTo("接收者@qq.com");
//            simpleEmail.send();
//            logger.info("发送成功！");
//        } catch (EmailException e) {
//            throw new DataAccessException("发送失败",e);
//        }

        //方法二
        EmailUtil emailUtil=new EmailUtil();
        String html="<div style='color:red'>是红色吗？</div>";
        emailUtil.sendHtmlEmail("测试html",html,"接收者@qq.com");
    }


    @Test
    public void testSendAttachMail(){


        //方法一
        //发送带附件的邮件
//        EmailAttachment attach=new EmailAttachment();//注意
//        attach.setPath("D:/test/1.jpg");
//
//        MultiPartEmail multiPartEmail=new MultiPartEmail();//注意
//        multiPartEmail.setHostName("smtp.163.com");
//        multiPartEmail.setAuthentication("用户名","授权码");
//        multiPartEmail.setCharset("utf-8");
//
//        try {
//            multiPartEmail.setFrom("发送者@163.com");
//            multiPartEmail.setSubject("附件发送");
//            multiPartEmail.setMsg("附件是一张图片，收到了吗？");
//            multiPartEmail.addTo("接收者@qq.com");
//            multiPartEmail.attach(attach);
//            multiPartEmail.send();
//            logger.info("发送成功！");
//
//
//        } catch (EmailException e) {
//           throw new DataAccessException("发送失败！",e);
//        }

        //方法二
        EmailUtil emailUtil=new EmailUtil();
        String path="D:/test/1.jpg";
        emailUtil.sendAttachEmail(path,"测试附件","附件接收到吗？","接收者@qq.com");
    }

}
