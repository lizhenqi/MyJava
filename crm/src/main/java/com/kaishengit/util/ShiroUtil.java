package com.kaishengit.util;

import com.kaishengit.pojo.User;
import org.apache.shiro.SecurityUtils;

/**
 * Created by Administrator on 2016/7/10.
 */
public class ShiroUtil {

    public static User getCurrentUser(){
        return (User) SecurityUtils.getSubject().getPrincipal();
    }
    public static Integer getCurrentUserId(){
        return getCurrentUser().getId();
    }
    public static String getCurrentUsername(){
        return getCurrentUser().getUsername();
    }

    public static String getCurrentRealname() {
        return getCurrentUser().getRealname();
    }


    /**
     * 这里面的User，来自ShiroRealm的return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
     * 而user是根据username查询的故要在UserMapper.xml中根据username的查询和role建立连接查询，否则下面获取roel时候是空。
     * @return
     */
//判断是否是某种jiaose
    public static boolean isAdmin(){

        return getCurrentUser().getRole().getRolename().equals("管理员");
    }
    public static boolean isEmployee(){
        return getCurrentUser().getRole().getRolename().equals("员工");
    }
    public static boolean isManager(){
        return getCurrentUser().getRole().getRolename().equals("经理");
    }
}
