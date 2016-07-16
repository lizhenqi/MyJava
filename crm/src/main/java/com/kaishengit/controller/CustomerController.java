package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.pojo.Customer;
import com.kaishengit.service.CustomerService;
import com.kaishengit.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/15.
 */

@Controller
public class CustomerController {
    @Inject
    private CustomerService customerService;

    /**
     * 列表
     * @return
     */
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String customerList(Model model) {
        List<Customer> customerList=customerService.findAllCompany();
//      //查询所有为公司的客户
        model.addAttribute("customerList",customerList);
        return "customer/list";
    }

    /**
     * 异步回调
     * @return
     */
    @RequestMapping(value = "/customer/list", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult<Customer> customerShowList(HttpServletRequest request) {

        String draw=request.getParameter("draw");
        String length=request.getParameter("length");
        String start=request.getParameter("start");
        String keyword=request.getParameter("search[value]");

        keyword= Strings.toUTF8(keyword);
        Map<String,Object> param= Maps.newHashMap();
        param.put("length",length);
        param.put("start",start);
        param.put("keyword",keyword);

        List<Customer> customerList=customerService.findCustomerByParam(param);
        Long count=customerService.findCount();
        Long filterCount=customerService.findFilterCount(param);

        return new DataTablesResult<>(draw,filterCount,customerList,count);
    }

    /**
     * 新增客户
     * @param customer
     * @return
     */
    @RequestMapping(value = "/customer/new",method = RequestMethod.POST)
    @ResponseBody
    public String saveCustomer(Customer customer){

        customerService.saveCustomer(customer);
        return "success";
    }


    /**
     * 删除客户
     * @param id
     * @return
     */
    @RequestMapping(value = "/customer/del/{id:\\d+}",method = RequestMethod.GET)
    @ResponseBody
    public String del(@PathVariable Integer id){
        customerService.delCustomerById(id);
        return "success";
    }

}
