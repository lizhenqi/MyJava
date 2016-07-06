package com.kaishengit.mapper;

import com.kaishengit.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 */
public interface BookMapper {

    void saveBook(Book book);
    void deleteBook(Integer id);
    Book findById(Integer id);
    void updateBook(Book book);
    List<Book> findAll();
    Long count();

    List<Book> findByPage(@Param("start") Integer start,@Param("size") Integer size);
}
