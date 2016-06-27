package com.kaishengit.util;

import com.kaishengit.exception.DataAccessException;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Administrator on 2016/6/22.
 */

/**
 * 读取config文件
 * @author Administrator
 */
public class Config {

    private static Properties properties=new Properties();
    static {
        try {
            properties.load(Config.class.getClassLoader().getResourceAsStream("config.properties"));

        } catch (IOException e) {
            throw new DataAccessException("读取config.properties文件异常！",e);
        }
    }
    public static String get(String msg){
        return properties.getProperty(msg);
    }
    public static String get(String msg,String defaultValue){
        return properties.getProperty(msg,defaultValue);
    }

}
