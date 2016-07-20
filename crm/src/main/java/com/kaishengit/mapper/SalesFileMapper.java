package com.kaishengit.mapper;

import com.kaishengit.pojo.Document;
import com.kaishengit.pojo.Sales_file;

import java.util.List;

/**
 * Created by Administrator on 2016/7/18.
 */
public interface SalesFileMapper {
    List<Sales_file> findBySalesId(Integer salesid);

    void delBySalesId(Integer salesid);

    void save(Sales_file salesFile);

    Sales_file findSalesFileById(Integer id);
}
