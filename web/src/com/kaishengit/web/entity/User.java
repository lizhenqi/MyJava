package com.kaishengit.web.entity;

public class User {
    private int id;
    private String username;
    private String address;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//
//    @Override
//    public String toString() {
//        return "User[" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", address='" + address + '\'' +
//                ", password='" + password + '\'' +
//                ']';
//    }
}