package com.kaishengit.service;

import com.google.common.collect.Maps;
import com.kaishengit.mapper.CustomerMapper;
import com.kaishengit.pojo.Customer;
import com.kaishengit.util.ShiroUtil;
import com.kaishengit.util.Strings;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/15.
 */
@Named
public class CustomerService {
    @Inject
    private CustomerMapper customerMapper;


    /**
     * 查询Customer（本人只能看自己的客户和公共客户）
     * @param param
     * @return
     */
    public List<Customer> findCustomerByParam(Map<String, Object> param) {
        if(ShiroUtil.isEmployee()){
            param.put("userid",ShiroUtil.getCurrentUserId());
        }
        return customerMapper.findCustomerByParam(param);
    }

    /**
     * 总数(包括根据role查询的)
     * @return
     */
    public Long findCount() {
        if(ShiroUtil.isEmployee()){
            Map<String,Object> param= Maps.newHashMap();
            param.put("userid",ShiroUtil.getCurrentUserId());
            return customerMapper.findFilterCount(param);
        }
        return customerMapper.findCount();
    }

    public Long findFilterCount(Map<String, Object> param) {

        if(ShiroUtil.isEmployee()){
            param.put("userid",ShiroUtil.getCurrentUserId());
        }
        return customerMapper.findFilterCount(param);
    }

    /**
     * 查询所有为公司的客户
     * @return
     */
    public List<Customer> findAllCompany() {
        return customerMapper.findType(Customer.CUSTOMER_TYPE_COMPANY);
    }

    public void saveCustomer(Customer customer) {

        if(customer.getCompanyID() !=null){
            Customer company=customerMapper.findById(customer.getCompanyID());

            customer.setCompanyname(company.getName());
        }
        customer.setUserid(ShiroUtil.getCurrentUserId());
        customer.setPinyin(Strings.toPinYin(customer.getName()));
        customerMapper.saveCustomer(customer);
    }

    /**
     * 根据ID删除客户
     * @param id
     */
    @Transactional
    public void delCustomerById(Integer id) {

        Customer customer=customerMapper.findById(id);
        if(customer !=null){
            //查找被删除用户是公司是否有关联客户，如果有就将客户的ID和名称设置为空
            if(customer.getType().equals(Customer.CUSTOMER_TYPE_COMPANY)){
                List<Customer> customerList=customerMapper.findByCompanyId(id);
                for(Customer msg:customerList){
                    msg.setCompanyname(null);
                    msg.setCompanyID(null);
                    customerMapper.update(msg);
                }
            }
        }
        customerMapper.delCustomer(id);

        //TODO 删除关联的项目
       //TODO 删除关联的代办事项
    }
}



