package com.kaishengit.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Administrator on 2016/7/2.
 */
@Named
public class LoginDaoImpl implements LoginDao {

    @Inject
    private JdbcTemplate jdbcTemplate;


    @Override
    public void save(String ip, Integer userid) {
        String sql="insert into t_login(ip,userid)values(?,?)";
        jdbcTemplate.update(sql,ip,userid);

    }
}
