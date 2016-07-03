package com.kaishengit.mapper;

import com.kaishengit.pojo.Login;

import java.util.List;

/**
 * Created by Administrator on 2016/7/3.
 */
public interface LoginMapper {
    List<Login> findLoginUserid(Integer id);
}
