package com.kaishengit.dao;

/**
 * Created by Administrator on 2016/6/30.
 */
public class UserDao {

    public UserDao() {
        System.out.println("测试：启动容器是否创建对象？");
    }


    public void init() {
        System.out.println("测试：初始化...");
    }

    public void destroy() {
        System.out.println("测试：摧毁...");
    }

    public void sayHello() {
        System.out.println("测试：你好...");
    }
}
