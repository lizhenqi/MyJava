package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.service.BookService;
import com.kaishengit.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(){
//        model.addAttribute("bookList",bookService.findAllBook());

        return "datatables/list";
    }

    @RequestMapping(value = "/data.json",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> load(HttpServletRequest request){

        //        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        String draw=request.getParameter("draw");
        String start=request.getParameter("start");//当前页偏移量
        String length=request.getParameter("length");//每页显示数据
        String keyWord=request.getParameter("search[value]");//搜索框中的值

        keyWord= Strings.toUTF8(keyWord);//得转码，否则不能搜中文

        String sortCountIndex=request.getParameter("order[0][column]");//获取排序列的索引
        String sortCountName=request.getParameter("columns["+sortCountIndex+"][name]");//根据索引获取列的名字
        String sortCountType=request.getParameter("order[0][dir]");//排序的方式(desc|asc)


        Map<String ,Object> param=Maps.newHashMap();
        param.put("start",start);
        param.put("length",length);
        param.put("keyWord",keyWord);
        param.put("sortCountName",sortCountName);
        param.put("sortCountType",sortCountType);





        Map<String ,Object> result= Maps.newHashMap();

        result.put("draw",draw);
        result.put("recordsTotal",bookService.count());
        result.put("recordsFiltered",bookService.countByKeyWord(keyWord));
//        result.put("data",bookService.findDataTable(start,length,keyWord));
//        都封装在param中，上面就可以直接传param
        result.put("data",bookService.findDataTable(param));
        return result;
    }
}
