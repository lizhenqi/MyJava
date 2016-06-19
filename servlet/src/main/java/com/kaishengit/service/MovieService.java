package com.kaishengit.service;

import com.kaishengit.dao.MovieDao;
import com.kaishengit.entity.Movie;
import com.kaishengit.util.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/6/19.
 */
public class MovieService {
    private MovieDao movieDao=new MovieDao();

    public List<Movie> findAllMovie(){
        return movieDao.findAll();
    }

    public Page<Movie> findMoviePage(int pageNo){
        int size=10;
        int totalSize=movieDao.findCount().intValue();

        Page<Movie> page=new Page<>(size,totalSize,pageNo);

        List<Movie> movieList=movieDao.findPage(page.getStart(),size);

        page.setItems(movieList);

        return page;


//        int size=10;
//        int totalSize=movieDao.findCount().intValue();
//        int pageSize=totalSize/size;
//
//        if(totalSize%10!=0){
//            pageSize=pageSize+1;
//        }
//        if(pageNo>pageSize){
//            pageNo=pageSize;
//        }
//        if (pageNo<=0){
//            pageNo=1;
//        }
//
//        int start=(pageNo-1)*size;
//
//        return movieDao.findPage(start,size);
    }
}
