package com.kaishengit.mapper;

import com.kaishengit.pojo.Sales;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/18.
 */
public interface SalesMapper {

    void save(Sales sales);

    Sales findById(Integer id);

    List<Sales> findParam(Map<String, Object> param);

    Long findFilterCount(Map<String, Object> param);

    void update(Sales sales);

    List<Sales> findByCustomerid(Integer customerid);

    void delSalesById(Integer id);
}
