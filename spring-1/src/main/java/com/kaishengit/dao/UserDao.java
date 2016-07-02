package com.kaishengit.dao;

import com.kaishengit.pojo.User;

import java.util.List;

/**
 * Created by Administrator on 2016/7/1.
 */
public interface UserDao {

    void save(User user);
    void delete(Integer id);
    void update(User user);
    User findId(Integer integer);

    List<User> findAll();

    User findByUsername(String username);

    Long count();

}
