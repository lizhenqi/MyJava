package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.dto.JsonResult;
import com.kaishengit.exception.ForbiddenException;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.Customer;
import com.kaishengit.pojo.User;
import com.kaishengit.service.CustomerService;
import com.kaishengit.service.UserService;
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
 * Created by Administrator on 2016/7/15.
 */

@Controller
public class CustomerController {
    @Inject
    private CustomerService customerService;
    @Inject
    private UserService userService;

    /**
     * 列表
     *
     * @return
     */
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String customerList(Model model) {
        List<Customer> customerList = customerService.findAllCompany();
//      //查询所有为公司的客户
        model.addAttribute("customerList", customerList);
        return "customer/list";
    }

    /**
     * 异步回调
     *
     * @return
     */
    @RequestMapping(value = "/customer/list", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult<Customer> customerShowList(HttpServletRequest request) {

        String draw = request.getParameter("draw");
        String length = request.getParameter("length");
        String start = request.getParameter("start");
        String keyword = request.getParameter("search[value]");

        keyword = Strings.toUTF8(keyword);
        Map<String, Object> param = Maps.newHashMap();
        param.put("length", length);
        param.put("start", start);
        param.put("keyword", keyword);

        List<Customer> customerList = customerService.findCustomerByParam(param);
        Long count = customerService.findCount();
        Long filterCount = customerService.findFilterCount(param);

        return new DataTablesResult<>(draw, filterCount, customerList, count);
    }

    /**
     * 新增客户
     *
     * @param customer
     * @return
     */
    @RequestMapping(value = "/customer/new", method = RequestMethod.POST)
    @ResponseBody
    public String saveCustomer(Customer customer) {

        customerService.saveCustomer(customer);
        return "success";
    }


    /**
     * 删除客户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/customer/del/{id:\\d+}", method = RequestMethod.GET)
    @ResponseBody
    public String del(@PathVariable Integer id) {
        customerService.delCustomerById(id);
        return "success";
    }


    //用于解决新增后重新获取company列表（没有实时更新）
    @RequestMapping(value = "/customer/company.json", method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> companyUpdate() {
        List<Customer> customerList = customerService.findAllCompany();
        return customerList;
    }




    //修改
    /**
     * 根据ID获取客户信息和所有的company（用于下拉框）
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/customer/edit/company/{id:\\d+}.json", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> editCompany(@PathVariable Integer id) {

        Customer customer = customerService.findCustomerById(id);
        Map<String, Object> result = Maps.newHashMap();

        if(customer==null){
            result.put("state", "error");
            result.put("message", "找不到id为："+id+"的客户！");
        }else{
            List<Customer> customerList = customerService.findAllCompany();
            result.put("state", "success");
            result.put("customer", customer);
            result.put("companylist", customerList);
        }
        return result;
    }
    @RequestMapping(value = "/customer/update")
    @ResponseBody
    public String edit(Customer customer) {

        customerService.updateCompany(customer);
        return "success";

    }

    /**
     * 查看客户信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/customer/view/{id:\\d+}",method = RequestMethod.GET)
    public String lookCustomer(@PathVariable Integer id,Model  model){
        Customer customer=customerService.findCustomerById(id);
        if(customer==null){
            throw new NotFoundException();
        }
        if(customer.getUserid()!=null && !customer.getUserid().equals(ShiroUtil.getCurrentUserId()) && !ShiroUtil.isAdmin()){
//            如果不是公共客户，不是本人的客户，不是管理员则禁止访问
            throw new ForbiddenException();
        }
        //如果是公司把其成员传给信息列表
        if(customer.getType().equals(Customer.CUSTOMER_TYPE_COMPANY)){
            List<Customer> customerList=customerService.findCustomerBycompantId(customer.getId());
            model.addAttribute("customerList",customerList);
        }
        model.addAttribute("customer",customer);


        //下面这两句用于转移客户时候传入的人员
        List<User> userList=userService.findAllUser();
        model.addAttribute("userList",userList);

        return "/customer/viewCustomer";
    }


    /**
     * 公开客户
     * @param id
     * @return
     */
    @RequestMapping(value = "/customer/open/{id:\\d+}",method = RequestMethod.GET)
    public String open(@PathVariable Integer id){
       Customer customer=customerService.findCustomerById(id);
        if(customer==null){
            throw new NotFoundException();
        }
        if(customer.getUserid()!=null && !customer.getUserid().equals(ShiroUtil.getCurrentUserId()) && !ShiroUtil.isAdmin()){
            throw new ForbiddenException();
        }

        customerService.openUpdate(customer);
        return "redirect:/customer/view/"+id;
    }

    /**
     * 转移客户
     * @param id
     * @param userid
     * @return
     */
    @RequestMapping(value = "/customer/move/",method = RequestMethod.POST)
    public String move(Integer id,Integer userid){
        //都得先验证是否是自己
        Customer customer=customerService.findCustomerById(id);
        if(customer==null){
            throw new NotFoundException();
        }
        if(customer.getUserid()!=null && !customer.getUserid().equals(ShiroUtil.getCurrentUserId()) && !ShiroUtil.isAdmin()){
            throw new ForbiddenException();
        }

        customerService.moveCustomer(customer,userid);
        return "redirect:/customer";
    }





}
