package com.kaishengit.entity;

/**
 * Created by Administrator on 2016/6/23.
 */
public class User {
    private Integer id;
    private String name;
    private String address;
    private Float score;

    public User() {}

    public User(Integer id, String name, String address, Float score) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.score = score;
    }

    public void setId(Integer id){
        this.id=id;
    }
    public Integer getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
