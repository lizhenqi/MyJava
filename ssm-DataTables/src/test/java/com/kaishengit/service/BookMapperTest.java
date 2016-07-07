package com.kaishengit.service;

import com.kaishengit.pojo.Book;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class BookMapperTest {
    private Logger logger= LoggerFactory.getLogger(BookMapperTest.class);


    @Inject
    private BookService bookService;


    @Test
    public void testSaveBook(){
        Book book=new Book();
        book.setBookname("大话数据库");
        bookService.saveBook(book);
        logger.info("测试成功！");
    }

    @Test
    public void testFindAll(){
        List<Book> bookList=bookService.findAllBook();
        Assert.assertEquals(bookList.size(),29);
    }

    @Test
    public void testFindById(){
        Book book=bookService.findById(36);
        System.out.println(book);
    }
    @Test
    public void testDel(){
        bookService.deleteBook(36);
        logger.info("删除成功");
    }
    @Test
    public void testUpdate(){
        Book book=bookService.findById(35);
        book.setBooknum(100);
        bookService.updateBook(book);
        logger.info("修改成功");
    }
}
