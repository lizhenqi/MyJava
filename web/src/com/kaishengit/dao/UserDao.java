package com.kaishengit.dao;

import com.kaishengit.entity.User;
import com.kaishengit.exception.DataAccessException;
import com.kaishengit.util.ConnectionManager;
import com.kaishengit.util.Dbhelp;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/6/8.
 */
public class UserDao {




    //第二次测试(自测)
    public void testInsert(User user){
        String sql="insert into t_user(username,password,address)values(?,?,?)";
        Dbhelp.userUpdate(sql,user.getUsername(),user.getPassword(),user.getAddress());
    }
    public void testDelete(int id){
        String sql="delete from t_user where id=?";
       Dbhelp.userUpdate(sql,id);
    }
    public void testModify(User user,int id){
        String sql="update t_user set username=?,password=?,address=? where id=?";
        Dbhelp.userUpdate(sql,user.getUsername(),user.getPassword(),user.getAddress(),id);
    }



    public User testFindId(int id){
        String sql="select *from t_user where id=?";
        return Dbhelp.userFind(sql,new BeanHandler<>(User.class),id);
    }
    public List<User> testFindList(){
        String sql="select *from t_user";
       return Dbhelp.userFind(sql,new BeanListHandler<>(User.class));
    }






















    //第一次测试(课堂)
//   public void userInsert(User user){
//       String sql="insert into t_user(username,password,address)values(?,?,?)";
//       Dbhelp.userUpdate(sql,user.getUsername(),user.getPassword(),user.getAddress());
//   }
//    public void userDelete(int id){
//        String sql="delete from t_user where id=?";
//       Dbhelp.userUpdate(sql,id);
//    }
//    public User userFindId(int id){
//        String sql="select *from t_user where ID=?";
//        return Dbhelp.queryAll(sql,new BeanHandler<User>(User.class),id);
//    }
//    public List<User> userFindAll(){
//        String sql="select *from t_user";
//        return Dbhelp.queryAll(sql,new BeanListHandler<>(User.class));
//    }
//
//
//    public void userModify(User user,int id){
//        String sql="update t_user set username=?,password=?,address=? where ID=?";
//        Dbhelp.userUpdate(sql,user.getUsername(),user.getPassword(),user.getAddress(),id);
//    }
}
