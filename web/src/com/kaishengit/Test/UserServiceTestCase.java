package com.kaishengit.Test;

import com.kaishengit.entity.User;
import com.kaishengit.service.UserService;
import com.sun.org.glassfish.external.arc.Taxonomy;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2016/6/15.
 */
public class UserServiceTestCase {
    private UserService userService=new UserService();

    @Test
    public void testLogin(){
        User user=userService.login("焦作","1111");
        Assert.assertNotNull(user);
        System.out.println(user);
    }
}
