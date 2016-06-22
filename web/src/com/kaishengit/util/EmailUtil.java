package com.kaishengit.util;

import com.kaishengit.exception.DataAccessException;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Administrator on 2016/6/22.
 */
public class EmailUtil {

    private static Logger logger= LoggerFactory.getLogger(EmailUtil.class);

    public static void sendTestEmail(String hostName,String name,String password,String from,String subject,String msg,String to){
        Email email=new SimpleEmail();
        email.setHostName(hostName);
        email.setAuthentication(name,password);
        email.setCharset("utf-8");

        try {
            email.setFrom(from);
            email.setSubject(subject);
            email.setMsg(msg);
            email.addTo(to);
            email.send();
            logger.debug("发送成功");

        } catch (EmailException e) {
            throw new DataAccessException("发送失败",e);
        }


    }
}
