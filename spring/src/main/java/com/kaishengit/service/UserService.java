package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.dao.UserDaoImp1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Administrator on 2016/6/30.
 */


/*注意注解和注入最好来源一致，不要混着来
例如：用spring：(@Service、@Component、@Repository)和@Autowired
例如:同Inject:@Named和@Inject
 */


//@Named
    @Service
public class UserService {

//        @Inject
    @Autowired
//    @Resource
//   当注入写在被注入属性的头上，set方法就可以不写了。例如现在下面的set方法就不用写了
    private UserDao userDao;




    //优先根据传入参数的名字，获取。UserDaoImp1和和UserDaoImp2虽然是同一个类型，
    // 但是UserDaoImp1的@Named("userDao")和UserDaoImp2的@Named("xx")的名字
    // 不一样故优先选和传参userDao一样的userDao
//    public void setUserDaoXX(UserDao userDao) {
//        this.userDao = userDao;
//    }


//    构造方法注入
//    private UserService(UserDao userDao){
//        this.userDao = userDao;
//    }

    public void sayHi() {
        System.out.println("say hi");
        userDao.save();
    }
}
