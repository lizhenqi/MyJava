package com.kaishengit.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2016/7/12.
 */
public class Notice implements Serializable{

    private Integer id;
    private String title;
    private String context;
    private Timestamp createtime;
    private String realname;
    private Integer userid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
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

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {

        this.userid = userid;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", context='" + context + '\'' +
                ", createtime=" + createtime +
                ", realname='" + realname + '\'' +
                ", userid=" + userid +
                '}';
    }
}
