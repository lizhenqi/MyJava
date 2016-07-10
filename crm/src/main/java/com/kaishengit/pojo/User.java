package com.kaishengit.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2016/7/8.
 */
public class User implements Serializable {

    private Integer id;
    private Integer roleid;
    private String username;
    private String password;
    private String realname;
    private String weixin;
    private Timestamp createtime;
    private Role role;
    private Boolean enable;
    

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", roleid=" + roleid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", weixin='" + weixin + '\'' +
                ", createtime=" + createtime +
                ", role=" + role +
                ", enable=" + enable +
                '}';
    }
}
