package com.kaishengit.mapper;

import com.kaishengit.pojo.UserLog;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/8.
 */
public interface UserLogMapper {

    void save(UserLog userlog);

    List<UserLog> findByParam(Map<String, Object> param);

    Long countByParam(Map<String, Object> param);
}
