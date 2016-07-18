package com.kaishengit.service;

import com.google.common.collect.Maps;
import com.kaishengit.mapper.SalesFileMapper;
import com.kaishengit.mapper.SalesLogMapper;
import com.kaishengit.mapper.SalesMapper;
import com.kaishengit.pojo.Sales;
import com.kaishengit.pojo.Sales_file;
import com.kaishengit.pojo.Sales_log;
import com.kaishengit.util.ShiroUtil;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/18.
 */
@Named
public class SalesService {
    @Inject
    private SalesMapper salesMapper;
    @Inject
    private CustomerService customerService;
    @Inject
    private SalesLogMapper salesLogMapper;
    @Inject
    private SalesFileMapper  salesFileMapper;

    /**
     * 查询总机会数量
     * @return
     */
    public Long findCount() {
        Map<String,Object> param= Maps.newHashMap();
        if(ShiroUtil.isEmployee()){
            param.put("userid",ShiroUtil.getCurrentUserId());
        }
        return salesMapper.findFilterCount(param);//如果不是员工相当于传值为空，查询的是所有总数
    }

    /**
     * 查询
     * @param param
     * @return
     */
    public List<Sales> findSalesByParam(Map<String, Object> param) {
//       如果你是员工，就获取你的userid意思你只能看自己的
        if(ShiroUtil.isEmployee()){
            param.put("userid",ShiroUtil.getCurrentUserId());
        }

        return salesMapper.findParam(param);
    }

    /**
     * 新增
     * @param sales
     */
    @Transactional
    public void save(Sales sales) {
        sales.setUserid(ShiroUtil.getCurrentUserId());
        sales.setUsername(ShiroUtil.getCurrentRealname());
        sales.setCustomername(customerService.findCustomerById(sales.getCustomerid()).getName());

        salesMapper.save(sales);

        //新建的同时自动保存记录
        Sales_log salesLog=new Sales_log();
        salesLog.setType(Sales_log.LOG_TYPE_AUTO);
        salesLog.setSalesid(sales.getId());
        //如果不在SalesMapper中设置（useGeneratedKeys="true" keyProperty="id"）这里获取不到sales.getId()
        salesLog.setContext(sales.getUsername()+"创建了该机会！");
        salesLogMapper.save(salesLog);
    }

    /**
     * 通过ID查询机会
     * @param id
     * @return
     */
    public Sales findById(Integer id) {

        return salesMapper.findById(id);
    }

    /**
     * 通过salesid查询机会创建记录
     * @param salesid
     * @return
     */
    public List<Sales_log> findSalesLogBySalesId(Integer salesid) {
        return salesLogMapper.findBySalesid(salesid);
    }

    /**
     * 通过参数查询数量
     * @param param
     * @return
     */
    public Long findFilterCount(Map<String, Object> param) {
        return salesMapper.findFilterCount(param);
    }

    /**
     * 查询对应salesid的文件列表
     * @param salesid
     * @return
     */
    public List<Sales_file> findSalesFileBySalesId(Integer salesid) {
        return salesFileMapper.findBySalesId(salesid);
    }


}
