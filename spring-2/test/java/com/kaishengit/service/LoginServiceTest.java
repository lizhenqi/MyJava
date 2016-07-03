package com.kaishengit.service;

import com.kaishengit.pojo.Login;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Administrator on 2016/7/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ApplicationContext.xml")
public class LoginServiceTest {

    @Inject
    private LoginService loginService;

    @Test
    public void testLogin(){
        List<Login> loginList=loginService.findLoginUserid(35);
        for(Login login:loginList){
            System.out.println(login);
        }
    }
}
