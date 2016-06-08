package com.kaishengit.web.dao;

import java.util.Objects;

/**
 * Created by Administrator on 2016/6/8.
 */
public class UserDao {
    public int test(){
        return 11;
    }
    public Object findId(Integer n){
        if (n.equals(1)){
            return new Object();
        }
        return null;
    }
    public void save(int i){
        if (i==1){
            throw new RuntimeException();
        }
    }
}
