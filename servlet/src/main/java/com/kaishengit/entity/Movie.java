package com.kaishengit.entity;

/**
 * Created by Administrator on 2016/6/17.
 */
public class Movie {

    private Integer id;
    private String title;
    private Float rate;
    private String releaseyear;
    private String sendtime;
    private String daoyan;
    private String jianjie;


    public void setId(Integer id) {
        this.id = id;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }

    public void setDaoyan(String daoyan) {
        this.daoyan = daoyan;
    }

    public void setReleaseyear(String releaseyear) {
        this.releaseyear = releaseyear;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Float getRate() {
        return rate;
    }

    public String getReleaseyear() {
        return releaseyear;
    }

    public String getSendtime() {
        return sendtime;
    }

    public String getDaoyan() {
        return daoyan;
    }

    public String getJianjie() {
        return jianjie;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", rate=" + rate +
                ", releaseyear='" + releaseyear + '\'' +
                ", sendtime='" + sendtime + '\'' +
                ", daoyan='" + daoyan + '\'' +
                ", jianjie='" + jianjie + '\'' +
                '}';
    }
}
