package com.kaishengit.util;

import com.kaishengit.entity.User;
import com.kaishengit.exception.DataAccessException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Dbhelp {

    private static Logger logger= LoggerFactory.getLogger(Dbhelp.class);

    //第二次测试(自测)
    public static void userUpdate(String sql,Object...params){
        Connection connection=ConnectionManager.getConnection();
        QueryRunner queryRunner=new QueryRunner();
        try {
            queryRunner.update(connection,sql,params);
            logger.debug("SQL:{}",sql);
        } catch (SQLException e) {
            logger.error("执行:{}异常",sql);
            throw new DataAccessException("执行"+sql+"异常",e);
        }
    }
    public  static <T>T userFind(String sql,ResultSetHandler<T> handler,Object...params){
        Connection connection=ConnectionManager.getConnection();
        QueryRunner queryRunner=new QueryRunner();
        try {
           T result= queryRunner.query(connection,sql,handler,params);
            logger.debug("SQL:{}",sql);
            return result;
        } catch (SQLException e) {
            logger.error("执行:{}异常",sql);
            throw new DataAccessException("执行"+sql+"异常",e);
        }
    }













    //第一次测试(课堂)
//    public static void userUpdate(String sql,Object...params){
//        QueryRunner queryRunner=new QueryRunner();
//        Connection connection= ConnectionManager.getConnection();
//
//        try {
//            queryRunner.update(connection,sql,params);
//        } catch (SQLException e) {
//            throw new DataAccessException("执行"+sql+"异常",e);
//        }finally {
//            ConnectionManager.closeConnection(connection);
//        }
//    }
//    public static <T> T queryAll(String sql,ResultSetHandler<T> handler,Object...params){
//        Connection connection=ConnectionManager.getConnection();
//        QueryRunner queryRunner=new QueryRunner();
//        try {
//            return queryRunner.query(connection,sql,handler,params);
//        } catch (SQLException e) {
//            throw new DataAccessException("执行"+sql+"异常",e);
//        }finally {
//            ConnectionManager.closeConnection(connection);
//        }
//    }
}
