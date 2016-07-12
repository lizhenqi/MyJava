package com.kaishengit.dto;

/**
 * Created by Administrator on 2016/7/12.
 */
public class JsonResult {

    public static final String SUCCESS="success";
    public static final String ERROR="error";

    private String state;
    private Object data;
    private String message;


    public JsonResult(Object data){
        this(SUCCESS,data);
    }public JsonResult(String message){
        this(ERROR,message);
    }
    //上面这两个其实就是下面两个的简写形式



    //这两个构造方法用于返回结果状态：错和错误信息。对及对的数据
    public JsonResult(String state, Object data) {
        this.state = state;
        this.data = data;
    }
    public JsonResult(String state, String message) {
        this.state = state;
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
