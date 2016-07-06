package com.kaishengit.controller;

import com.kaishengit.pojo.Book;
import com.kaishengit.pojo.BookType;
import com.kaishengit.pojo.Publisher;
import com.kaishengit.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 */
@Controller
@RequestMapping("/books")
public class BookController {
    @Inject
    private BookService bookService;


//    查询所有
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        List<Book> bookList = bookService.findAllBook();
        model.addAttribute("bookList", bookList);
        return "books/list";
    }



//新增（带成功提示）
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String saveBook(Model model) {

        List<BookType> bookTypeList=bookService.findAllBookType();
        List<Publisher> publisherList=bookService.findAllPublisher();

        model.addAttribute("bookTypeList",bookTypeList);
        model.addAttribute("publisherList",publisherList);
        return "books/new";
    }
    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public String saveBook(Book book, RedirectAttributes msg){
        bookService.saveBook(book);

       msg.addFlashAttribute("message","新增成功！");

        return "redirect:/books";
    }

//    删除
    @RequestMapping(value = "/{id:\\d+}/del",method = RequestMethod.GET)
    public String deleteBook(@PathVariable Integer id,RedirectAttributes msg){

        msg.addFlashAttribute("message","删除成功");
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.GET)
    public String editBook(@PathVariable Integer id,Model model){


        Book book=bookService.findById(id);
        if(book==null){
//            TODO
        }


        List<BookType> bookTypeList=bookService.findAllBookType();
        List<Publisher> publisherList=bookService.findAllPublisher();

        model.addAttribute("book",book);
        model.addAttribute("bookTypeList",bookTypeList);
        model.addAttribute("publisherList",publisherList);
        return "books/edit";
    }

    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.POST)
    public String editBook(Book book,RedirectAttributes msg){

        bookService.updateBook(book);
        msg.addFlashAttribute("message","修改成功");
        return "redirect:/books";
    }



}
