package com.kaishengit.util;

import com.kaishengit.exception.ServiceException;

import javax.inject.Named;

/**
 * Created by Administrator on 2016/7/2.
 */
@Named
public class EmailUtil {

    public void sendEmail(String msg){
        throw new ServiceException("发送邮件异常！");
    }
}
