package com.kaishengit.web.Test;

import com.kaishengit.web.entity.User;
import com.kaishengit.web.util.ConnectionManager;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DbUtilsTestCase {

    @Test
    public void testInsert(){
        QueryRunner queryrunner=new QueryRunner();
        String sql="insert into t_user(username,address,password)values(?,?,?)";
        Connection connection=ConnectionManager.getConnection();
        try {
            queryrunner.update(connection,sql,"jim","焦作","454000");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testDelete(){
        QueryRunner queryrunner=new QueryRunner();
        String sql="delete from t_user where id=?";
        Connection connection=ConnectionManager.getConnection();
        try {
            queryrunner.update(connection,sql,1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

   @Test
    public void testUpdae(){
       QueryRunner queryRunner=new QueryRunner();
       String sql="update t_user set username=? where id=?";
       Connection connection=ConnectionManager.getConnection();
       try {
           queryRunner.update(connection,sql,"jim",1);
       } catch (SQLException e) {
           e.printStackTrace();
       }finally {
           try {
               connection.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
   }

    @Test
    public void testFindOne(){

        QueryRunner queryrunner=new QueryRunner();
        String sql="select *from t_user where id=?";
        Connection connection=ConnectionManager.getConnection();
        try {
            User user = queryrunner.query(connection, sql, new BeanHandler<>(User.class), 3);
            System.out.println(user);
            Assert.assertNotNull(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void testFindAll(){
        QueryRunner queryRunner=new QueryRunner();
        String sql="select *from t_user";
        Connection connection=ConnectionManager.getConnection();
        try {
            List list=queryRunner.query(connection,sql,new BeanListHandler<>(User.class));

            for(Object user:list){
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void testFindIdToMap(){
        QueryRunner queryRunner=new QueryRunner();
        String sql="select *from t_user where id=?";
        Connection connection=ConnectionManager.getConnection();
        try {
           Map<String,Object> usermap= queryRunner.query(connection,sql,new MapHandler(),4);
            Assert.assertNotNull(usermap);
            for(Map.Entry<String ,Object> entry:usermap.entrySet()){
                System.out.println(entry.getKey()+"->>"+entry.getValue());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void testFindAllToMap(){
        QueryRunner queryRunner=new QueryRunner();
        String sql="select *from t_user";
        Connection connection=ConnectionManager.getConnection();
        try {
            List<Map<String ,Object>> list=queryRunner.query(connection,sql,new MapListHandler());
            Assert.assertEquals(list.size(),2);

            for(Map<String ,Object> users:list){
                for(Map.Entry<String ,Object> user:users.entrySet()){
                    System.out.println(user.getKey()+"->"+user.getValue());
                }
                System.out.println("-----------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    public void testFindScalar(){
        Connection connection=ConnectionManager.getConnection();
        String sql="select count(*) from t_user";
        QueryRunner queryRunner=new QueryRunner();
        try {
            Long count=queryRunner.query(connection,sql,new ScalarHandler<Long>());
            Assert.assertEquals(new Long(2),count);
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }
    @Test
    public void testFindColumn(){
        Connection connection=ConnectionManager.getConnection();
        String sql="select username from t_user";
        QueryRunner queryRunner=new QueryRunner();

        try {
           List names= queryRunner.query(connection,sql,new ColumnListHandler<String>());
            Assert.assertEquals(2,names.size());

            for(Object us:names){
                System.out.print(names);
            }
//            for (Object name:names
//                 ) {
//                System.out.println(name);
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
