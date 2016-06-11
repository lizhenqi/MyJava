package com.kaishengit.entity;

public class User {

    //第二次测试(自测)
    private int ID;
    private String username;
    private String password;
    private String address;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public User() {}
    public User(String username, String password, String address) {
        this.username = username;
        this.password = password;
        this.address = address;
    }











    //第一次测试(课堂)
//    private int ID;
//    private String username;
//    private String password;
//    private String address;
//
//    public User() {}
//
//    public User(String username, String password, String address) {
//        this.username = username;
//        this.password = password;
//        this.address = address;
//    }
//
//    public int getID() {
//        return ID;
//    }
//
//    public void setID(int ID) {
//        this.ID = ID;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "ID=" + ID +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", address='" + address + '\'' +
//                '}';
//    }







    //    private int id;
//    private String username;
//    private String address;
//    private String password;
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
////
////    @Override
////    public String toString() {
////        return "User[" +
////                "id=" + id +
////                ", username='" + username + '\'' +
////                ", address='" + address + '\'' +
////                ", password='" + password + '\'' +
////                ']';
////    }
}
