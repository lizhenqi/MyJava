package com.kaishengit.test;

import com.kaishengit.service.BookService;
import com.kaishengit.service.UserService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/6/30.
 */
public class SpringTest {

    private Logger logger = LoggerFactory.getLogger(SpringTest.class);

    @Test
    public void testUserDao() {

        //注意：默认情况下spring中的类会变成单例类,容器启动就会创建类的对象（可以通过在类中写个构造方法测试）,相当于工厂模式的饿汉式。
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");

//        BookService bookService= (BookService) applicationContext.getBean("bookService");
//
//        bookService.showBook();


        UserService userService= (UserService) applicationContext.getBean("userService");
        userService.sayHi();


//        UserDao userDao = (UserDao) applicationContext.getBean("userDao");
//        userDao.sayHello();

//        UserDao userDao1 = (UserDao) applicationContext.getBean("userDao");
//
//        System.out.println(userDao == userDao1);
// 比较内存地址是否一样：测试这两个对象是否一样
    }
}
