package com.kaishengit.Test;

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
        SimpleEmail simpleEmail=new SimpleEmail();
        simpleEmail.setHostName("smtp.163.com");
        simpleEmail.setAuthentication("18336880379","lzqqzl565400");
        simpleEmail.setCharset("utf-8");

        try {
            simpleEmail.setFrom("18336880379@163.com");
            simpleEmail.setSubject("测试 commons-email");
            simpleEmail.setMsg("测试2！");
            simpleEmail.addTo("565400895@qq.com");
            simpleEmail.send();

            logger.debug("成功");
        } catch (EmailException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void testSendAttachMail(){

        EmailAttachment emailAttachment=new EmailAttachment();
        emailAttachment.setPath("D:/test/1.jpg");

        MultiPartEmail simpleEmail=new MultiPartEmail();
        simpleEmail.setHostName("smtp.163.com");
        simpleEmail.setAuthentication("18336880379","lzqqzl565400");
        simpleEmail.setCharset("utf-8");

        try {
            simpleEmail.setFrom("18336880379@163.com");
            simpleEmail.setSubject("测试附件");
            simpleEmail.setMsg("<div style='color:red'>是红色吗？</div>");
            simpleEmail.addTo("565400895@qq.com");
            simpleEmail.attach(emailAttachment);
            simpleEmail.send();
            logger.debug("成功");
        } catch (EmailException e) {
            e.printStackTrace();
        }

    }

}
