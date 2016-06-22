package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2016/6/15.
 */
public class UserService {
    private static Logger logger= LoggerFactory.getLogger(UserService.class);
    private final String SALT="35@*$^$@)!)";
    private UserDao userDao=new UserDao();

    /**
     * 用户登录
     * @param username 账号
     * @param password 密码
     * @return 如果登录成功返回User 否则null
     */
    public User login(String username, String password){

        User user=userDao.testFindName(username);
       // password = DigestUtils.md5Hex(password+SALT);

        if(user!=null&&user.getPassword().equals(password)){
            logger.info("{}登录成功",username);
            return user;
        }
        return null;
    }
}
