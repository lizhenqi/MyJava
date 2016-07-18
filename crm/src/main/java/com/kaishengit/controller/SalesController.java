package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.exception.ForbiddenException;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.Customer;
import com.kaishengit.pojo.Sales;
import com.kaishengit.pojo.Sales_file;
import com.kaishengit.pojo.Sales_log;
import com.kaishengit.service.CustomerService;
import com.kaishengit.service.SalesService;
import com.kaishengit.util.ShiroUtil;
import com.kaishengit.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/18.
 */
@Controller
public class SalesController {
    @Inject
    private SalesService salesService;

    @Inject
    private CustomerService customerService;


    /**
     * 显示列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/sales",method = RequestMethod.GET)
    public String saleList(Model model){
        List<Customer> customerList=customerService.findAll();
        model.addAttribute("customerList",customerList);
        return "sales/list";
    }

    /**
     * ajax回调
     * @param request
     * @return
     */
    @RequestMapping(value = "/sales/list",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult<Sales> saleListShow(HttpServletRequest request){
        String draw = request.getParameter("draw");
        String length = request.getParameter("length");
        String start = request.getParameter("start");

        //查询参数
        String name = request.getParameter("name");
        name = Strings.toUTF8(name);
        String progress = request.getParameter("progress");
        progress = Strings.toUTF8(progress);
        String startDate = request.getParameter("startdate");
        String endDate = request.getParameter("enddate");


        Map<String, Object> param = Maps.newHashMap();
        param.put("length", length);
        param.put("start", start);
        param.put("name",name);
        param.put("progress",progress);
        param.put("startdate",startDate);
        param.put("enddate",endDate);

        List<Sales> salesList = salesService.findSalesByParam(param);
        Long count = salesService.findCount();
        Long filterCount = salesService.findFilterCount(param);

        return new DataTablesResult<>(draw,filterCount,salesList,count);

    }


    /**
     * 新建机会
     * @param sales
     * @return
     */
    @RequestMapping(value = "/sales/new",method = RequestMethod.POST)
    @ResponseBody
    public String newSales(Sales sales){

        salesService.save(sales);
        return "success";
    }

    /**
     * 查看机会信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/sales/view/{id:\\d+}",method = RequestMethod.GET)
    public String view(@PathVariable Integer id,Model model){
        Sales sales=salesService.findById(id);

       if(sales==null){
           throw new NotFoundException();
       }if(!sales.getUserid().equals(ShiroUtil.getCurrentUserId()) && !ShiroUtil.isAdmin()){
            throw new ForbiddenException();
        }
//        把创建的机会传值进去
        model.addAttribute("sales",sales);


//        把跟进记录传值进去
        List<Sales_log> salesLogList=salesService.findSalesLogBySalesId(id);
        model.addAttribute("salesLogList",salesLogList);

        //查找当前机会的文件列表
        List<Sales_file> salesFiles = salesService.findSalesFileBySalesId(id);
        model.addAttribute(salesFiles);

        return "sales/view";
    }
}