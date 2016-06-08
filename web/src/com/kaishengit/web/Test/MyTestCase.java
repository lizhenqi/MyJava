package com.kaishengit.web.Test;


import com.kaishengit.web.dao.UserDao;

import static java.lang.Math.abs;
import static org.junit.Assert.*;
import org.junit.Test;
import static java.lang.Math.*;

/**
 * Created by Administrator on 2016/6/8.
 */
public class MyTestCase {

  private UserDao userDao=new UserDao();
   @Test
    public void testCase(){
        assertNotEquals(11,userDao.test());
       abs(1);
       //Math.abs(1);
    }
    @Test
    public void testfindId(){
        assertNotNull(userDao.findId(1));
    }
    @Test
    public void testSave(){
        userDao.save(Integer.parseInt("2"));
    }

}
