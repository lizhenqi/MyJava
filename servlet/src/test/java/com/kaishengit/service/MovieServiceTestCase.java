package com.kaishengit.service;

import com.kaishengit.entity.Movie;
import com.kaishengit.util.Page;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Administrator on 2016/6/17.
 */
public class MovieServiceTestCase {

    private Logger logger= LoggerFactory.getLogger(MovieServiceTestCase.class);

    @Test
    public void testFindMoviePageNo(){
       Page<Movie> movieList= movieService.findMoviePage(2);
        Assert.assertNotNull(movieList);
        logger.debug("{}",65/10);


    }
    MovieService movieService=new MovieService();
    @Test
    public void testMovieService(){


        List<Movie> movieList=movieService.findAllMovie();


        Assert.assertNotNull(movieList);
        for(Movie movie:movieList){
            logger.debug("{}",movie);
        }

//        Assert.assertEquals(movieList.size(),682);

//        for(Movie movie:movieList){
//            System.out.println(movie);
//            break;
//        }
    }
}
