package com.kaishengit.mapper;

import com.kaishengit.pojo.User;

/**
 * Created by Administrator on 2016/7/3.
 */
public interface UserMapper {
    void save(User user);
    User findId(Integer id);
    void delete(Integer id);
    void updata(User user);
}
