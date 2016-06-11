package com.kaishengit.exception;

/**
 * Created by Administrator on 2016/6/10.
 */
public class DataAccessException extends RuntimeException{
    public DataAccessException(){}
    public DataAccessException(String msg){
        super(msg);
    }
    public DataAccessException(String msg,Exception e){
        super(msg ,e);
    }























    //第一次测试
//    public DataAccessException(){}
//    public DataAccessException(String msg){
//        super(msg);
//    }
//    public DataAccessException(String msg,Exception e){
//        super(msg,e);
//    }
}
