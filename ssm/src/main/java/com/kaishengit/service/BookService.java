package com.kaishengit.service;

import com.kaishengit.mapper.BookMapper;
import com.kaishengit.mapper.BookTypeMapper;
import com.kaishengit.mapper.PublisherMapper;
import com.kaishengit.pojo.Book;
import com.kaishengit.pojo.BookType;
import com.kaishengit.pojo.Publisher;
import org.springframework.transaction.annotation.Transactional;
import sun.dc.pr.PRError;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 */
@Named
@Transactional
public class BookService {

    @Inject
    private BookMapper bookMapper;

    @Inject
    private BookTypeMapper bookTypeMapper;

    @Inject
    private PublisherMapper publisherMapper;

    public void saveBook(Book book){
        bookMapper.saveBook(book);
    }
    public void deleteBook(Integer id){
        bookMapper.deleteBook(id);
    }
    public void updateBook(Book book){
        bookMapper.updateBook(book);
    }
    public Book findById(Integer id){
        return bookMapper.findById(id);
    }
    public List<Book> findAllBook(){
        return bookMapper.findAll();
    }

    public List<BookType> findAllBookType(){
        return bookTypeMapper.findAll();
    }


    public List<Publisher> findAllPublisher(){
        return publisherMapper.findAll();
    }
}
