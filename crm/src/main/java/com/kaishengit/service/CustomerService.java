package com.kaishengit.service;

import com.google.common.collect.Maps;
import com.kaishengit.mapper.CustomerMapper;
import com.kaishengit.pojo.Customer;
import com.kaishengit.util.ShiroUtil;
import com.kaishengit.util.Strings;
import org.apache.commons.lang3.StringUtils;
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

    /**
     * 根据ID查询客户
     * @param id
     * @return
     */
    public Customer findCustomerById(Integer id) {
        return customerMapper.findById(id);
    }


    /**
     * 修改company
     * @param customer
     */
    @Transactional
    public void updateCompany(Customer customer) {

        //如果为公司，找到关联客户并修改其关联公司名称(要注意区分查找的id还是companyID)
        if(customer.getType().equals(Customer.CUSTOMER_TYPE_COMPANY)){
            List<Customer> customerList=findCustomerBycompantId(customer.getId());
            for(Customer msg:customerList){
                msg.setCompanyID(customer.getId());
                msg.setCompanyname(customer.getName());
                customerMapper.updateCompany(msg);
            }
        }else{
            //有可能个人没有关联公司
            if(customer.getCompanyID()!=null){
                Customer msg=findCustomerById(customer.getCompanyID());
                customer.setCompanyname(msg.getName());
            }
        }

        customer.setPinyin(Strings.toPinYin(customer.getName()));
        customerMapper.updateCompany(customer);
    }


    /**
     * 查找所有companyID为id的客户
     * @param id
     * @return
     */
    public List<Customer> findCustomerBycompantId(Integer id) {
        return customerMapper.findByCompanyId(id);
    }


    /**
     * 更新公开客户
     * @param customer
     */
    @Transactional
    public void openUpdate(Customer customer) {
        customer.setUserid(null);
        customerMapper.updateCompany(customer);
    }


    /**
     * 查询所有客户
     * @return
     */
    public List<Customer> findAll() {
        return customerMapper.findAll();
    }


    /**
     * 转移客户
     * @param customer
     * @param userid
     */
    public void moveCustomer(Customer customer, Integer userid) {
        customer.setUserid(userid);
        customerMapper.updateCompany(customer);
    }


    /**
     * 将用户信息生成meCard形式以备生成二维码之用
     * @param id
     * @return
     */
    public String meCard(Integer id){
        Customer customer=customerMapper.findById(id);
        StringBuilder meCard=new StringBuilder("MECARD:");
        if(StringUtils.isNotEmpty(customer.getName())){
            meCard.append("N:"+customer.getName()+";");
        }
        if(StringUtils.isNotEmpty(customer.getTel())){
            meCard.append("TEL:"+customer.getTel()+";");
        }
        if(StringUtils.isNotEmpty(customer.getAddress())){
            meCard.append("ADR:"+customer.getAddress()+";");
        }
        if(StringUtils.isNotEmpty(customer.getEmail())){
            meCard.append("EMAIL:"+customer.getEmail()+";");
        }if(StringUtils.isNotEmpty(customer.getCompanyname())){
            meCard.append("ORG:"+customer.getCompanyname()+";");
        }
        meCard.append(";");
        return meCard.toString();
    }




}



