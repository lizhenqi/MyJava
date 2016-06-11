package com.kaishengit.exception;

/**
 * Created by Administrator on 2016/6/10.
 */
public class DataAccessException extends RuntimeException{


    //第二次测试(自测)
    public DataAccessException(){}
    public DataAccessException(String msg){
        super(msg);
    }
    public DataAccessException(String msg,Exception e){
        super(msg ,e);
    }























    //第一次测试(课堂)
//    public DataAccessException(){}
//    public DataAccessException(String msg){
//        super(msg);
//    }
//    public DataAccessException(String msg,Exception e){
//        super(msg,e);
//    }
}
