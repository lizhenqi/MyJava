package com.kaishengit.dao;

import com.kaishengit.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Administrator on 2016/7/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class UserDaoTest {
    @Inject
    private UserDao userDao;

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("jdbcTemplate测试");
        user.setPassword("密码");
        user.setAddress("地址");
        userDao.save(user);
        System.out.println("新增成功");
    }


    @Test
    public void testDelete() {
        userDao.delete(37);
        System.out.println("删除成功");
    }


    @Test
    public void testUpdate() {
        User user =userDao.findId(42);
        user.setUsername("测试");
        userDao.update(user);
        System.out.println("修改成功");

    }

    @Test
    public void testFindId() {
        User user = userDao.findId(35);
        System.out.println(user);
    }


    @Test
    public void testFindUsername() {
        User user = userDao.findByUsername("罗贯中");
        System.out.println(user);
    }

    @Test
    public void testFindAll() {
        List<User> list = userDao.findAll();

        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void testCount(){
        Long count=userDao.count();
        System.out.println("总数："+count);
    }

}
