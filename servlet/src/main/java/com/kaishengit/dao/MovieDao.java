package com.kaishengit.dao;

import com.kaishengit.entity.Movie;
import com.kaishengit.util.Dbhelp;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

/**
 * Created by Administrator on 2016/6/17.
 */
public class MovieDao {

    public List<Movie> findAll(){
        String sql="select *from movie ";
        return Dbhelp.userFind(sql,new BeanListHandler<>(Movie.class));
    }
    public List<Movie> findPage(int start,int size){
        String sql="select *from movie limit ?,?";
        return Dbhelp.userFind(sql,new BeanListHandler<Movie>(Movie.class),start,size);
    }
    public Long findCount(){
        String sql="select count(*) from movie";
        return Dbhelp.userFind(sql,new ScalarHandler<Long>());
    }
}
