package com.kaishengit.pojo;

import javax.inject.Inject;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2016/7/2.
 */
public class Login implements Serializable {
    private static final long serialVersionUID = -1942761188151437004L;
    private Integer id;
    private Timestamp createTime;
    private String ip;
    private Integer userid;

    public Login() {
    }

    public Login(String ip, Integer userid) {
        this.ip = ip;
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", ip='" + ip + '\'' +
                ", userid=" + userid +
                '}';
    }
}
