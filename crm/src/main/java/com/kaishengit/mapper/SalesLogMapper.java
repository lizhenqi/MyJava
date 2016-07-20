package com.kaishengit.mapper;

import com.kaishengit.pojo.Sales_log;

import java.util.List;

/**
 * Created by Administrator on 2016/7/18.
 */
public interface SalesLogMapper {
    //自动保存创建记录
    void save(Sales_log salesLog);


    List<Sales_log> findBySalesid(Integer salesid);

    void delBySalesId(Integer salesid);
}
