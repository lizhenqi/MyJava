package com.kaishengit.test;

import com.kaishengit.service.UserService;
import com.sun.org.glassfish.external.arc.Taxonomy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

/**
 * Created by Administrator on 2016/7/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class SpringTestCase {

//    @Inject
    @Autowired
    private UserService userService;

    @Test
    public void testSayHi(){
        userService.sayHi();
    }


}
