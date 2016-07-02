package com.kaishengit.dao;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.inject.Named;

/**
 * Created by Administrator on 2016/7/1.
 */
//@Service
//@Component
//@Repository
//获取的时候默认就是类名（首字母小写），当然也可以名义别名：@Repository("别名")

    @Named("xx")
//    @Lazy(true)
//    @Scope("prototype")
public class UserDaoImp2  implements UserDao{
    @Override
    public Integer save() {
        System.out.println("测试：add...");
        return null;
    }

}
