package com.kaishengit.service;

import com.kaishengit.mapper.LoginMapper;
import com.kaishengit.pojo.Login;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Administrator on 2016/7/3.
 */
@Named
@Transactional
public class LoginService {

    @Inject
    private LoginMapper loginMapper;

    public List<Login> findLoginUserid(Integer id){
        return loginMapper.findLoginUserid(id);
    }
}
