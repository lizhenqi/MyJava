package com.kaishengit.mapper;

import com.kaishengit.pojo.User;
import com.kaishengit.pojo.UserLog;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/8.
 */
public interface UserMapper  {

    User findByUsername(String username);

    void updatePassword(User user);

    List<User> findAllUserByParam(Map<String ,Object> param);

    Long findAllCount();

    Long findFilterCount(Map<String, Object> param);

    void saveUser(User user);

    User findUserById(Integer id);

    void updateUserPwd(User user);
}
