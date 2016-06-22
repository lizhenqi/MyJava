package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;
import com.kaishengit.util.EmailUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
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

        final User user=userDao.testFindName(username);
       // password = DigestUtils.md5Hex(password+SALT);

        if(user!=null&&user.getPassword().equals(password)){
            logger.info("{}登录成功",username);


            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    //登录成功发邮件提示：
                    EmailUtil.sendTestEmail("登录提示","您的账号："+"《"+user.getUsername()+"》在"+ DateTime.now().toString("yyyy-MM-dd HH:mm:ss")+"登录了系统！",user.getAddress());

                }
            });
            thread.start();

            return user;
        }
        return null;
    }
}
