package com.kaishengit.web.Test;

import com.kaishengit.web.dao.UserDao;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2016/6/8.
 */
public class MyTestCase {

    private UserDao userDao=new UserDao();

    @Test
    public void testCase(){
        Assert.assertEquals(11,userDao.test());
    }

}
