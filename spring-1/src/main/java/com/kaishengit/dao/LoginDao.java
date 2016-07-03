package com.kaishengit.dao;

import javax.inject.Named;

/**
 * Created by Administrator on 2016/7/2.
 */
public interface LoginDao {
    void save(String ip,Integer userid);
}
