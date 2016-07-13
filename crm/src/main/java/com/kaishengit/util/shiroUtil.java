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
}
