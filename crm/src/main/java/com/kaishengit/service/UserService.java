package com.kaishengit.service;

import com.google.common.collect.Maps;
import com.kaishengit.mapper.RoleMapper;
import com.kaishengit.mapper.UserLogMapper;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;
import com.kaishengit.pojo.UserLog;
import com.kaishengit.util.ShiroUtil;
import com.kaishengit.util.Strings;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/8.
 */
@Named
public class UserService {

    @Inject
    private UserMapper userMapper;
    @Inject
    private UserLogMapper userLogMapper;
    @Inject
    private RoleMapper roleMapper;


    public void saveLog(String ip) {
        UserLog userlog=new UserLog();
        userlog.setLoginip(ip);
        userlog.setLogintime(DateTime.now().toString("yyyy:MM:dd HH:mm"));
        userlog.setUserid(ShiroUtil.getCurrentUserId());
        userLogMapper.save(userlog);

    }

    public Role findRoleById(Integer roleid) {
        return roleMapper.findById(roleid);
    }

    public User findUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }


    /**
     * 修改密码
     * @param password
     */
    public void changePassword(String password) {

        User user=ShiroUtil.getCurrentUser();
        user.setPassword(DigestUtils.md5Hex(password));
//       要加密
        userMapper.updatePassword(user);

    }

    /**
     * 获取当前用户的登录日志
     * @param start
     * @param length
     * @return
     */
    public List<UserLog> findCurrentUsreLog(String start, String length) {
        Map<String,Object> param= Maps.newHashMap();
        param.put("userid",ShiroUtil.getCurrentUserId());
        param.put("start",start);
        param.put("length",length);

        return userLogMapper.findByParam(param);
    }


    /**
     * 获取当前用户的日志数量
     * @return
     */
    public Long findCurrentUsreLogCont() {
        Map<String,Object> param= Maps.newHashMap();
        param.put("userid",ShiroUtil.getCurrentUserId());
        return userLogMapper.countByParam(param);
    }


    /**
     * 根据查询参数获取用户
     * @return
     * @param param
     */
    public List<User> findAllUserByParam(Map<String, Object> param) {
        return userMapper.findAllUserByParam(param);
    }

    /**
     * 获取user的总数
     * @return
     */
    public Long findAllCount() {
        return userMapper.findAllCount();
    }

    /**
     * 根据查询参数获取user的数量
     * @param param
     * @return
     */
    public Long findFilterCount(Map<String, Object> param) {
        return userMapper.findFilterCount(param);
    }

    /**
     * 新增用户
     * @param user
     */

    @Transactional
    public void saveUser(User user) {

        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        userMapper.saveUser(user);
    }


    /**
     * 查询所有Role
     * @return
     */
    public List<Role> findAllRole() {
        return roleMapper.findAllRole();
    }


    /**
     * 重置用户密码
     * @param id
     */
    public void updateUserPwd(Integer id) {
        User user=userMapper.findUserById(id);
        if(user!=null){
           user.setPassword(DigestUtils.md5Hex("666666"));
        }
        userMapper.updateUserPwd(user);
    }

    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }


    /**
     * 修改用户信息
     * @param user
     */
    public void userEdit(User user) {
        userMapper.userEdit(user);

    }

    /**
     * 查询所有user
     * @return
     */
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }
}
