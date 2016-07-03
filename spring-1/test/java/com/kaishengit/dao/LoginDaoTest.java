package com.kaishengit.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

/**
 * Created by Administrator on 2016/7/2.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class LoginDaoTest {


    private Logger logger= LoggerFactory.getLogger(LoginDaoTest.class);
    @Inject
    private LoginDao loginDao;

    @Test
    public void testSave(){
        loginDao.save("127.0.0.1",2);
        logger.info("成功");
    }

}
