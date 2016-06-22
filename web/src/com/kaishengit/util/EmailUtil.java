package com.kaishengit.util;

import com.kaishengit.exception.DataAccessException;
import org.apache.commons.mail.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Administrator on 2016/6/22.
 */
public class EmailUtil {

    private static Logger logger= LoggerFactory.getLogger(EmailUtil.class);

    /**
     * 发送一封文本邮件
     * @param subject 主题
     * @param msg 正文
     * @param to 发给谁
     */
    public static void sendTestEmail(String subject,String msg,String to){
        Email email=new SimpleEmail();
        email.setHostName(Config.get("mail.hostname"));
        email.setAuthentication(Config.get("mail.username"),Config.get("mail.password"));
        email.setCharset(Config.get("mail.charset"));

        try {
            email.setFrom(Config.get("mail.from"));
            email.setSubject(subject);
            email.setMsg(msg);
            email.addTo(to);
            email.send();

            logger.info("给{}发送成功！",to);

        } catch (EmailException e) {
            throw new DataAccessException("发送失败",e);
        }


    }

    /**
     * 发送一邮件一封html邮件
     * @param to 发给谁
     * @param subject 主题
     * @param html 内容
     */
    public static void sendHtmlEmail(String subject,String html,String to){
        HtmlEmail email=new HtmlEmail();
        email.setHostName(Config.get("mail.hostname"));
        email.setAuthentication(Config.get("mail.username"),Config.get("mail.password"));
        email.setCharset(Config.get("mail.charset"));

        try {
            email.setFrom(Config.get("mail.from"));
            email.setSubject(subject);
            email.setHtmlMsg(html);
            email.addTo(to);
            email.send();
            logger.info("给{}发送成功！",to);
        } catch (EmailException e) {
            throw new DataAccessException("",e);
        }
    }
    public static void sendAttachEmail(String path,String subject,String msg,String to){
        EmailAttachment email=new EmailAttachment();
        email.setPath(path);

        MultiPartEmail multiPartEmail=new MultiPartEmail();
        multiPartEmail.setHostName(Config.get("mail.hostname"));
        multiPartEmail.setAuthentication(Config.get("mail.username"),Config.get("mail.password"));
        multiPartEmail.setCharset(Config.get("mail.charset"));

        try {
            multiPartEmail.setFrom(Config.get("mail.from"));
            multiPartEmail.setSubject(subject);
            multiPartEmail.setMsg(msg);
            multiPartEmail.addTo(to);
            multiPartEmail.attach(email);

            multiPartEmail.send();
            logger.info("给{}发送成功！",to);
        } catch (EmailException e) {
            throw new DataAccessException("发送失败！",e);
        }


    }



}
