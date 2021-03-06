package com.kaishengit.Test;

import com.kaishengit.entity.User;
import com.kaishengit.exception.DataAccessException;
import com.kaishengit.util.ConnectionManager;
import com.sun.corba.se.pept.transport.ConnectionCache;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DbUtilsTestCase {




    //第二次测试(自测)
    @Test
    public void testConnection() {
        Connection connection = ConnectionManager.getConnection();
    }

    @Test
    public void testInsert() {
        Connection connection = ConnectionManager.getConnection();
        String sql = "insert into t_user(username,password,address)values(?,?,?)";
        QueryRunner queryRunner = new QueryRunner();
        try {
            queryRunner.update(connection, sql, "张三", "测试1", "测试2");
        } catch (SQLException e) {
            throw new DataAccessException("新增异常", e);
        } finally {
            ConnectionManager.closeConnection(connection);
        }
    }

    @Test
    public void testDelete() {
        Connection connection = ConnectionManager.getConnection();
        String sql = "delete from t_user where id=?";
        QueryRunner queryRunner = new QueryRunner();
        try {
            queryRunner.update(connection, sql, 32);
        } catch (SQLException e) {
            throw new DataAccessException("删除异常", e);
        } finally {
            ConnectionManager.closeConnection(connection);
        }
    }

    @Test
    public void testModify() {
        Connection connection = ConnectionManager.getConnection();
        String sql = "update t_user set username=? where id=?";
        QueryRunner queryRunner = new QueryRunner();
        try {
            queryRunner.update(connection, sql, "张三", 31);
        } catch (SQLException e) {
            throw new DataAccessException("修改异常", e);
        }
    }

    @Test
    public void testFindIdBean() {
        Connection connection = ConnectionManager.getConnection();
        String sql = "select *from t_user where id=?";
        QueryRunner queryRunner = new QueryRunner();
        try {

            User user = queryRunner.query(connection, sql, new BeanHandler<>(User.class), 10);
            Assert.assertNotNull(user);
            System.out.println(user);
        } catch (SQLException e) {
            throw new DataAccessException("查询异常", e);
        }
    }

    @Test
    public void testFindAllBeanList() {
        Connection connection = ConnectionManager.getConnection();
        String sql = "select *from t_user";
        QueryRunner queryRunner = new QueryRunner();
        try {
            List<User> userList = queryRunner.query(connection, sql, new BeanListHandler<User>(User.class));
            Assert.assertEquals(userList.size(), 3);
            for (User user : userList) {
                System.out.println(user);
            }
        } catch (SQLException e) {
            throw new DataAccessException("查询BeanList异常", e);
        }
    }

    @Test
    public void testFindMap() {
        Connection connection = ConnectionManager.getConnection();
        String sql = "select *from t_user where id=?";
        QueryRunner queryRunner = new QueryRunner();
        try {
            Map<String, Object> usermap = queryRunner.query(connection, sql, new MapHandler(), 31);
            Assert.assertEquals(usermap.size(), 4);
            for (Map.Entry<String, Object> user : usermap.entrySet()) {
                System.out.println(user.getKey() + "->" + user.getValue());
            }
        } catch (SQLException e) {
            throw new DataAccessException("Map查询异常", e);
        }
    }

    @Test
    public void testFindMapList() {
        Connection connection = ConnectionManager.getConnection();
        String sql = "select *from t_user";
        QueryRunner queryRunner = new QueryRunner();
        try {
            List<Map<String, Object>> userlist = queryRunner.query(connection, sql, new MapListHandler());
            for (Map<String, Object> usermap : userlist) {
                for (Map.Entry<String, Object> user : usermap.entrySet()) {
                    System.out.println(user.getKey() + "->" + user.getValue());
                }
                System.out.println("----------");
            }
        } catch (SQLException e) {
            throw new DataAccessException("MapList查询异常", e);
        }
    }

    @Test
    public void testFindScalar() {
        Connection connection = ConnectionManager.getConnection();
        String sql = "select count(*) from t_user";
        QueryRunner queryRunner = new QueryRunner();
        try {
            Long msg = queryRunner.query(connection, sql, new ScalarHandler<Long>());
            Assert.assertEquals(msg, new Long(3));
            System.out.println(msg);
        } catch (SQLException e) {
            throw new DataAccessException("Scalar查询异常", e);
        }
    }

    @Test
    public void testFindColumn() {
        Connection connection = ConnectionManager.getConnection();
        String sql = "select username from t_user";
        QueryRunner queryRunner = new QueryRunner();
        try {
            List<String> names = queryRunner.query(connection, sql, new ColumnListHandler<String>());
            Assert.assertEquals(names.size(), 3);
            for (Object name : names) {
                System.out.println(name);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Column查询异常", e);
        }
    }



















//第一次测试(课堂)
//@Test
//    public void testInsert() throws SQLException{
//    QueryRunner queryRunner=new QueryRunner();
//    String sql="insert into t_user(username,password,address)values(?,?,?)";
//    Connection connection= ConnectionManager.getConnection();
//    try {
//        int msg=queryRunner.update(connection,sql,"jin","root","美国");
//        Assert.assertEquals(1,msg);
//    } catch (SQLException e) {
//        throw new DataAccessException("数据插入异常",e);
//    }finally {
//        ConnectionManager.closeConnection(connection);
//    }
//}
//@Test
//    public void testDelete() throws SQLException{
//    Connection connection=ConnectionManager.getConnection();
//    String sql="delete from t_user where ID=?";
//    QueryRunner queryRunner=new QueryRunner();
//    try {
//        queryRunner.update(connection,sql,9);
//    } catch (SQLException e) {
//       throw new DataAccessException("删除异常",e);
//    }finally {
//        ConnectionManager.closeConnection(connection);
//    }
//}
//@Test
//    public void testModify() throws SQLException{
//    Connection connection=ConnectionManager.getConnection();
//    String sql="update t_user set username=?,password=? where ID=?";
//    QueryRunner queryRunner=new QueryRunner();
//    try {
//        int msg=queryRunner.update(connection,sql,"河南","豫",10);
//        Assert.assertEquals(1,msg);
//    } catch (SQLException e) {
//        throw new DataAccessException("数据修改异常",e);
//    }finally {
//        ConnectionManager.closeConnection(connection);
//    }
//}
//@Test
//    public void testFindIdBean() throws SQLException{
//    Connection connection=ConnectionManager.getConnection();
//    String sql="select * from t_user where ID=?";
//    QueryRunner queryRunner=new QueryRunner();
//    try {
//        User user=queryRunner.query(connection,sql,new BeanHandler<>(User.class),10);
//        Assert.assertNotNull(user);
//        System.out.println(user);
//    } catch (SQLException e) {
//        throw new DataAccessException("查询异常",e);
//    }finally {
//        ConnectionManager.closeConnection(connection);
//    }
//}
//    @Test
//    public void testFindIdBeanList() throws SQLException{
//        Connection connection=ConnectionManager.getConnection();
//        String sql="select *from t_user";
//        QueryRunner queryRunner=new QueryRunner();
//        try {
//            List<User> users=queryRunner.query(connection,sql,new BeanListHandler<>(User.class));
//
//            Assert.assertEquals(users.size(),2);
//            for(User user:users ){
//                System.out.println(user);
//            }
//        } catch (SQLException e) {
//            throw new DataAccessException("数据查询异常",e);
//        }finally {
//            ConnectionManager.closeConnection(connection);
//        }
//    }
//    @Test
//    public void testFindIdMap()throws SQLException{
//        Connection connection=ConnectionManager.getConnection();
//        String sql="select *from t_user where ID=?";
//        QueryRunner queryRunner=new QueryRunner();
//        try {
//           Map<String ,Object> user= queryRunner.query(connection,sql,new MapHandler(),1);
//           Assert.assertNotNull(user);
//            for(Map.Entry<String ,Object> msg:user.entrySet()){
//                System.out.println(msg.getKey()+"->"+msg.getValue());
//            }
//        } catch (SQLException e) {
//            throw new DataAccessException("数据查询异常",e);
//        }finally {
//            ConnectionManager.closeConnection(connection);
//        }
//    }
//    @Test
//    public void testFindIdMapList() throws SQLException{
//        Connection connection=ConnectionManager.getConnection();
//        String sql="select *from t_user";
//        QueryRunner queryRunner=new QueryRunner();
//        try {
//            List<Map<String ,Object>> users=queryRunner.query(connection,sql,new MapListHandler());
//            Assert.assertEquals(users.size(),2);
//            for (Map<String ,Object> user:users) {
//                for(Map.Entry<String ,Object> msg:user.entrySet()){
//                    System.out.println(msg.getKey()+"->"+msg.getValue());
//                }
//            }
//        } catch (SQLException e) {
//            throw new DataAccessException("数据查询异常",e);
//        }finally {
//            ConnectionManager.closeConnection(connection);
//        }
//
//    }
//@Test
//    public void testFindScalar() throws Exception{
//    Connection connection=ConnectionManager.getConnection();
//    String sql="select count(*)from t_user";
//    QueryRunner queryRunner=new QueryRunner();
//    try {
//
//        Long msg=queryRunner.query(connection,sql,new ScalarHandler<Long>());
//       Assert.assertEquals(msg,new Long(2));
//        System.out.println(msg);
//    } catch (SQLException e) {
//        throw new DataAccessException("Scalar数据查询异常",e);
//    }finally {
//        ConnectionManager.closeConnection(connection);
//    }
//}
//
//    @Test
//    public void testFindColumn()throws SQLException{
//        Connection connection=ConnectionManager.getConnection();
//        String sql="Select username from t_user";
//        QueryRunner queryRunner=new QueryRunner();
//        try {
//            List names=queryRunner.query(connection,sql,new ColumnListHandler<>());
//            for (Object msg:names) {
//                System.out.println(msg);
//            }
//        } catch (SQLException e) {
//            throw new DataAccessException("Column数据查询异常",e);
//        }finally {
//            ConnectionManager.closeConnection(connection);
//        }
//    }


//    @Test
//    public void testInsert(){
//        QueryRunner queryrunner=new QueryRunner();
//        String sql="insert into t_user(username,address,password)values(?,?,?)";
//        Connection connection=ConnectionManager.getConnection();
//        try {
//            queryrunner.update(connection,sql,"jim","焦作","454000");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally{
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Test
//    public void testDelete(){
//        QueryRunner queryrunner=new QueryRunner();
//        String sql="delete from t_user where id=?";
//        Connection connection=ConnectionManager.getConnection();
//        try {
//            queryrunner.update(connection,sql,1);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//   @Test
//    public void testUpdae(){
//       QueryRunner queryRunner=new QueryRunner();
//       String sql="update t_user set username=? where id=?";
//       Connection connection=ConnectionManager.getConnection();
//       try {
//           queryRunner.update(connection,sql,"jim",1);
//       } catch (SQLException e) {
//           e.printStackTrace();
//       }finally {
//           try {
//               connection.close();
//           } catch (SQLException e) {
//               e.printStackTrace();
//           }
//       }
//   }
//
//    @Test
//    public void testFindOne(){
//
//        QueryRunner queryrunner=new QueryRunner();
//        String sql="select *from t_user where id=?";
//        Connection connection=ConnectionManager.getConnection();
//        try {
//            User user = queryrunner.query(connection, sql, new BeanHandler<>(User.class), 3);
//            System.out.println(user);
//            Assert.assertNotNull(user);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    @Test
//    public void testFindAll(){
//        QueryRunner queryRunner=new QueryRunner();
//        String sql="select *from t_user";
//        Connection connection=ConnectionManager.getConnection();
//        try {
//            List list=queryRunner.query(connection,sql,new BeanListHandler<>(User.class));
//
//            for(Object user:list){
//                System.out.println(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    @Test
//    public void testFindIdToMap(){
//        QueryRunner queryRunner=new QueryRunner();
//        String sql="select *from t_user where id=?";
//        Connection connection=ConnectionManager.getConnection();
//        try {
//           Map<String,Object> usermap= queryRunner.query(connection,sql,new MapHandler(),4);
//            Assert.assertNotNull(usermap);
//            for(Map.Entry<String ,Object> entry:usermap.entrySet()){
//                System.out.println(entry.getKey()+"->>"+entry.getValue());
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    @Test
//    public void testFindAllToMap(){
//        QueryRunner queryRunner=new QueryRunner();
//        String sql="select *from t_user";
//        Connection connection=ConnectionManager.getConnection();
//        try {
//            List<Map<String ,Object>> list=queryRunner.query(connection,sql,new MapListHandler());
//            Assert.assertEquals(list.size(),2);
//
//            for(Map<String ,Object> users:list){
//                for(Map.Entry<String ,Object> user:users.entrySet()){
//                    System.out.println(user.getKey()+"->"+user.getValue());
//                }
//                System.out.println("-----------------------");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    @Test
//    public void testFindScalar(){
//        Connection connection=ConnectionManager.getConnection();
//        String sql="select count(*) from t_user";
//        QueryRunner queryRunner=new QueryRunner();
//        try {
//            Long count=queryRunner.query(connection,sql,new ScalarHandler<Long>());
//            Assert.assertEquals(new Long(2),count);
//            System.out.println(count);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//
//
//        }
//    }
//    @Test
//    public void testFindColumn(){
//        Connection connection=ConnectionManager.getConnection();
//        String sql="select username from t_user";
//        QueryRunner queryRunner=new QueryRunner();
//
//        try {
//           List names= queryRunner.query(connection,sql,new ColumnListHandler<String>());
//            Assert.assertEquals(2,names.size());
//
//            for(Object us:names){
//                System.out.print(names);
//            }
////            for (Object name:names
////                 ) {
////                System.out.println(name);
////            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}
