package com.kaishengit.mapper;

import com.kaishengit.pojo.User;
import com.kaishengit.pojo.UserLog;

/**
 * Created by Administrator on 2016/7/8.
 */
public interface UserMapper  {

    User findByUsername(String username);

    void updatePassword(User user);
}
