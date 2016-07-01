package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.dao.UserDaoImp1;

/**
 * Created by Administrator on 2016/6/30.
 */
public class UserService {

    private UserDao userDao;

//    public void setUserDaoXX(UserDao userDao) {
//        this.userDao = userDao;
//    }


//    构造方法注入
    private UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public void sayHi(){
        System.out.println("say hi");
        userDao.save();
    }
}
