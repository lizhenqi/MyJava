package com.kaishengit.mapper;

import com.kaishengit.pojo.Customer;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/15.
 */
public interface CustomerMapper {


    List<Customer> findCustomerByParam(Map<String, Object> param);

    Long findCount();

    Long findFilterCount(Map<String, Object> param);

    List<Customer> findType(String customerTypeCompany);

    Customer findById(Integer companyID);

    void saveCustomer(Customer customer);
}
