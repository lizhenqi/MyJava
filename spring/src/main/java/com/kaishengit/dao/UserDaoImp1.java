package com.kaishengit.dao;

import org.springframework.stereotype.Component;

import javax.inject.Named;

/**
 * Created by Administrator on 2016/7/1.
 */
@Named("userDao")
public class UserDaoImp1 implements UserDao{

    @Override
    public Integer save() {
        System.out.println("测试：save...");

//        if(1==1){
//            throw new RuntimeException("测试");
//        }
        return 110;
    }
}
