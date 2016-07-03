package com.kaishengit.service;

import com.kaishengit.dao.LoginDao;
import com.kaishengit.dao.UserDao;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.pojo.User;
import com.kaishengit.util.EmailUtil;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Administrator on 2016/7/1.
 */
@Named

public class UserService {

    @Inject
    private UserDao userDao;
    @Inject
    private LoginDao loginDao;
    @Inject
    private EmailUtil emailUtil;


//  1： @Transactional(rollbackFor = RuntimeException.class)默认碰见运行时异常回滚
// 2：@Transactional(noRollbackFor = RuntimeException.class)例外的情况
// 3： @Transactional(rollbackFor = Exception.class)碰见什么回滚
//4：@Transactional(readOnly = true)事务会降低性能，查询推荐只读就行
//5：@Transactional可以写在类上表示所有通用，但是个别方法上又可以注解自己独有的，这是不冲突的
//6:@Transactional(isolation = Isolation.SERIALIZABLE)修改事务的隔离级别，一般不会改这些！！！
// 7:@Transactional(propagation = Propagation.REQUIRED)修改事务的传播属性。




//   事务会降低性能，查询-推荐只读就行
    @Transactional(readOnly = true)
    public User findId(Integer id){
        return userDao.findId(id);
    }


    //登录成功User最好要返回，因为别的层可能需要。
    @Transactional(propagation = Propagation.REQUIRED)//默认为运行时异常
    public User login(String username,String password,String ip){
        User user=userDao.findByUsername(username);

//        if(username.equals("罗贯")){
//            throw new ServiceException("该账号已被冻结");
//        }
        if(user!=null && user.getPassword().equals(password)){

//          感觉这个放在外层会更优化。
//           if(username.equals("xx")){
//               throw new ServiceException("该账号已被冻结");
//           }


            loginDao.save(ip,user.getId());
            emailUtil.sendEmail("给用户发一封登录邮件！");
//            System.out.println("给用户发一封登录邮件！");

//            loginDao.save(ip,user.getId());
            return user;
        }else{
            throw new ServiceException("用户名或密码错误");
        }
    }
}
