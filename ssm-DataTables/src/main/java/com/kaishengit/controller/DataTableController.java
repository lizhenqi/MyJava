package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.pojo.Book;
import com.kaishengit.service.BookService;
import com.kaishengit.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/6.
 */
@Controller
@RequestMapping("/datatable")
public class DataTableController {


    @Inject
    private BookService bookService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {

        model.addAttribute("bookTypeList", bookService.findAllBookType());
        model.addAttribute("publisherList", bookService.findAllPublisher());

        return "datatables/list";
    }

    @RequestMapping(value = "/data.json", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> load(HttpServletRequest request) {

        //        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        String draw = request.getParameter("draw");
        String start = request.getParameter("start");//当前页偏移量
        String length = request.getParameter("length");//每页显示数据

//        第二种
        String bookName = request.getParameter("bookNmae");
        String typeid = request.getParameter("typeid");
        String pubid = request.getParameter("pubid");

        bookName = Strings.toUTF8(bookName);

//       下面这两句是ssm项目中的搜索，本datatable项目用的是第二种
//        String keyWord = request.getParameter("search[value]");//搜索框中的值
//        keyWord = Strings.toUTF8(keyWord);//得转码，否则不能搜中文

        String sortCountIndex = request.getParameter("order[0][column]");//获取排序列的索引
        String sortCountName = request.getParameter("columns[" + sortCountIndex + "][name]");//根据索引获取列的名字
        String sortCountType = request.getParameter("order[0][dir]");//排序的方式(desc|asc)


        Map<String, Object> param = Maps.newHashMap();
        param.put("start", start);
        param.put("length", length);
//        param.put("keyWord", keyWord);
        param.put("sortCountName", sortCountName);
        param.put("sortCountType", sortCountType);

        param.put("bookName",bookName);
        param.put("type",typeid);
        param.put("pub",pubid);

        Map<String, Object> result = Maps.newHashMap();

        result.put("draw", draw);
        result.put("recordsTotal", bookService.count());
        result.put("recordsFiltered", bookService.countByParam(param));
//        result.put("data",bookService.findDataTable(start,length,keyWord));
//        都封装在param中，上面就可以直接传param
        result.put("data", bookService.findDataTable(param));
        return result;
    }

    //    (传进来的)新增
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public String saveBook(Book book) {
        bookService.saveBook(book);
        return "success";
    }


    //    (传进来的)删除
    @RequestMapping(value = "/{id:\\d+}/del", method = RequestMethod.GET)
    @ResponseBody
    public String delBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return "success";
    }

    //    (传进来的 id 查找对象，写进去)修改
    @RequestMapping(value = "/{id:\\d+}.json", method = RequestMethod.GET)
    @ResponseBody
    public Book editBook(@PathVariable Integer id) {
        Book book = bookService.findById(id);
        return book;
    }

    //    (传进来的)修改
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public String editBook(Book book) {
        bookService.updateBook(book);
        return "success";
    }


}
