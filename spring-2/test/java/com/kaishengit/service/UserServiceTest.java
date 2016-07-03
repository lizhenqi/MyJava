package com.kaishengit.service;

import com.kaishengit.pojo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Administrator on 2016/7/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class UserServiceTest {
    @Inject
    private UserService userService;

    @Test
    public void testSave(){
        User user=new User("测试3","1","1");
        userService.save(user);
        System.out.println(user);
    }

    @Test
    public void testFindId(){
       User user=userService.findId(53);
        System.out.println(user);
    }

    @Test
    public void testDelete(){
        userService.delete(41);
        System.out.println("删除成功！");
    }

    @Test
    public void testUpdate(){
        User user=userService.findId(54);
        user.setUsername("修改测试");
        userService.update(user);
        System.out.println("修改测试！");
    }
}
