package com.kaishengit.exception;

/**
 * Created by Administrator on 2016/7/2.
 */
public class ServiceException extends RuntimeException {


    //自定义异常可以传参也可以不传。

    public ServiceException(){}

    public ServiceException(String msg){
        super(msg);
    }
}
