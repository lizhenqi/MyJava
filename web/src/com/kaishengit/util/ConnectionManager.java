package com.kaishengit.util;


import com.kaishengit.exception.DataAccessException;
import com.mysql.fabric.xmlrpc.base.Data;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {


    //第二次测试(自测)
    private static BasicDataSource dataSource=new BasicDataSource();
    static {

        dataSource.setDriverClassName(Config.get("jdbc.driver"));
        dataSource.setUrl(Config.get("jdbc.url"));
        dataSource.setUsername(Config.get("jdbc.username"));
        dataSource.setPassword(Config.get("jdbc.password"));

        dataSource.setInitialSize(new Integer(Config.get("jdbc.initsize","5")));
        dataSource.setMaxTotal(new Integer(Config.get("jdbc.maxtotal","20")));
        dataSource.setMaxWaitMillis(new Integer(Config.get("jdbc.maxwait","5000")));
        dataSource.setMinIdle(new Integer(Config.get("jdbc.maxidle","10")));
        dataSource.setMaxIdle(new Integer(Config.get("jdbc.minidle","5")));
    }

    public static Connection getConnection() {
        try {
            Connection connection=dataSource.getConnection();
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection = DriverManager.getConnection("jdbc:mysql:///db_21","root","root");
            return connection;
        }  catch (SQLException e) {
            throw new DataAccessException("连接数据库异常",e);
        }
    }
    public static void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DataAccessException("数据库关闭异常",e);
        }
    }














    //第一次测试(课堂)
//    private static BasicDataSource dataSource=new BasicDataSource();
//    static {
//        Properties proper=new Properties();
//        try {
//            proper.load(ConnectionManager.class.getClassLoader().getResourceAsStream("config.properties"));
//        } catch (IOException e) {
//            throw new RuntimeException("读取config.properties失败",e);
//        }
//        dataSource.setDriverClassName(proper.getProperty("jdbc.driver"));
//        dataSource.setUrl(proper.getProperty("jdbc.url"));
//        dataSource.setUsername(proper.getProperty("jdbc.username"));
//        dataSource.setPassword(proper.getProperty("jdbc.password"));
//        dataSource.setInitialSize(new Integer(proper.getProperty("jdbc.initasize","5")));
//        dataSource.setMaxTotal(new Integer(proper.getProperty("jdbc.maxsize","20")));
//        dataSource.setMaxWaitMillis(new Integer(proper.getProperty("jdbc.maxwait","5000")));
//        dataSource.setMaxIdle(new Integer(proper.getProperty("maxidle","10")));
//        dataSource.setMinIdle(new Integer(proper.getProperty("minidle","5")));
//    }
//
//    public static Connection getConnection(){
//        try {
//            Connection connection=dataSource.getConnection();
////            Class.forName("com.mysql.jdbc.Driver");
////            Connection connection=DriverManager.getConnection("jdbc:mysql:///db_21","root","root");
//            return connection;
//        }  catch (SQLException e) {
//            throw new DataAccessException("获取数据库连接异常",e);
//        }
//    }
//public static void closeConnection(Connection connection){
//    try {
//        connection.close();
//    } catch (SQLException e) {
//        throw new DataAccessException("关闭数据库连接异常",e);
//    }
//}

























//    public static Connection getConnection(){
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection connection= DriverManager.getConnection("jdbc:mysql:///db_21","root","root");
//            return connection;
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}
