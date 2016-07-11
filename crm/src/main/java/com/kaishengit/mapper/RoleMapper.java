package com.kaishengit.mapper;

import com.kaishengit.pojo.Role;

import java.util.List;

/**
 * Created by Administrator on 2016/7/8.
 */
public interface RoleMapper {
    Role findById(Integer roleid);

    List<Role> findAllRole();
}
