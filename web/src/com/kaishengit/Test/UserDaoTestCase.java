package com.kaishengit.Test;

import com.kaishengit.entity.User;
import com.kaishengit.dao.UserDao;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2016/6/10.
 */
public class UserDaoTestCase {

    private UserDao userDao=new UserDao();
    @Test
    public void testUserInsert(){
//        User user=new User();
//        user.setUsername("李四");
//        user.setPassword("测试");
//        user.setAddress("测试");
        User user=new User("施耐庵","测试1","测试2");
        userDao.testInsert(user);
    }
    @Test
    public void testUserDelete(){
        userDao.testDelete(31);
    }@Test
    public void testUsermodify(){
        User user=new User("施耐庵","俺乃是","测试3");
        userDao.testModify(user,36);
    }
    @Test
    public void testUserFindBean(){
        User user=userDao.testFindId(36);
        System.out.println(user);
    }
    @Test
    public void testUserFindList(){
        List<User> userList=userDao.testFindList();
        for (User user:userList
             ) {
            System.out.println(user);
        }
    }














//第一次测试
//    private UserDao userDao=new UserDao();
//    @Test
//    public void testUserInsert(){
//        User user=new User("cdde","测试","永城");
//        userDao.userInsert(user);
//    }
//
//    @Test
//    public void testUserDelete(){
//        userDao.userDelete(26);
//    }
//    @Test
//    public void testModify(){
//        User user=new User("测试","修改测试","修改测试");
//        userDao.userModify(user,31);
//    }
//    @Test
//    public void testUserFindId(){
//        User user=userDao.userFindId(1);
//        Assert.assertNotNull(user);
//        System.out.println(user);
//    }
//    @Test
//    public void testUserFindAll(){
//        List<User> userList=userDao.userFindAll();
//        Assert.assertEquals(userList.size(),2);
//
//        for (User user:userList) {
//            System.out.println(user);
//            System.out.println("-----");
//        }
//    }

}
