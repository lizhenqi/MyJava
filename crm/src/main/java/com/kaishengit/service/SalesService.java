package com.kaishengit.service;

import com.google.common.collect.Maps;
import com.kaishengit.mapper.SalesFileMapper;
import com.kaishengit.mapper.SalesLogMapper;
import com.kaishengit.mapper.SalesMapper;
import com.kaishengit.pojo.Document;
import com.kaishengit.pojo.Sales;
import com.kaishengit.pojo.Sales_file;
import com.kaishengit.pojo.Sales_log;
import com.kaishengit.util.ShiroUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @Value("${imagePath}")
    private String savePath;

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


    /**
     * 保存新的跟进日志
     * @param salesLog
     */
    @Transactional
    public void saveLog(Sales_log salesLog) {
        salesLog.setType(Sales_log.LOG_TYPE_INPUT);
        salesLogMapper.save(salesLog);

        //增加最后跟进时间
        Sales sales = salesMapper.findById(salesLog.getSalesid());
        sales.setLasttime(DateTime.now().toString("yyyy-MM-dd"));
        salesMapper.update(sales);
    }



    /**
     * 修改机会的进度
     * @param id
     * @param progress
     */
    @Transactional
    public void editSalesProgress(Integer id, String progress) {
        Sales sales = salesMapper.findById(id);
        sales.setProgress(progress);

        //修改最后跟进时间
        sales.setLasttime(DateTime.now().toString("yyyy-MM-dd"));

        //判断进度是否是『完成交易』
        if("完成交易".equals(progress)) {
            sales.setSuccesstime(DateTime.now().toString("yyyy-MM-dd"));
        }
        salesMapper.update(sales);

        //添加跟进日志
        Sales_log salesLog = new Sales_log();
        salesLog.setType(Sales_log.LOG_TYPE_AUTO);
        salesLog.setContext(ShiroUtil.getCurrentRealname()+ " 更改进度为：" + progress);
        salesLog.setSalesid(sales.getId());
        salesLogMapper.save(salesLog);
    }

    /**
     * 通过customerid查询机会列表
     * @param id
     * @return
     */
    public List<Sales> findByCustomerid(Integer id) {
        return salesMapper.findByCustomerid(id);
    }

    /**
     * 通过id删除机会
     * @param id
     */
    @Transactional
    public void delSalesById(Integer id) {

        //删除关联文件
        salesFileMapper.delBySalesId(id);

        //删除关联记录
        salesLogMapper.delBySalesId(id);

//它下面关联有文件和记录，删除它之前要先把其他两个删除否则，报异常删除不了
        salesMapper.delSalesById(id);
    }



    /**
     * 保存文件
     * @param inputStream
     * @param size
     * @param originalFilename
     * @param contentType
     * @param salesid
     */
    public void uploadFile(InputStream inputStream, long size, String originalFilename, String contentType, Integer salesid) {
//名字
        String exName="";
        if(originalFilename.lastIndexOf(".")!=-1){
            exName=originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String newName=UUID.randomUUID().toString()+ exName;
//写入
        try {
            FileOutputStream outPutStream=new FileOutputStream(new File(savePath,newName));
            try {
                IOUtils.copy(inputStream,outPutStream);
                outPutStream.flush();
                outPutStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
                //为了让事务有效要抛出RuntimeException
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        Sales_file salesFile=new Sales_file();
        salesFile.setSalesid(salesid);
        salesFile.setFilename(newName);
        salesFile.setName(originalFilename);
        salesFile.setContenttype(contentType);
        salesFile.setSize(FileUtils.byteCountToDisplaySize(size));

        salesFileMapper.save(salesFile);
    }

    /**
     * 按id查询salesFiles
     * @param id
     * @return
     */
    public Sales_file findSalesFileById(Integer id) {
        return salesFileMapper.findSalesFileById(id);
    }
}
