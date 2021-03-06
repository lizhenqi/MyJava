package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.exception.ForbiddenException;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.Customer;
import com.kaishengit.pojo.Sales;
import com.kaishengit.pojo.Sales_file;
import com.kaishengit.pojo.Sales_log;
import com.kaishengit.service.CustomerService;
import com.kaishengit.service.SalesService;
import com.kaishengit.util.ShiroUtil;
import com.kaishengit.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/18.
 */
@Controller
public class SalesController {
    @Inject
    private SalesService salesService;

    @Inject
    private CustomerService customerService;

    @Value("${imagePath}")
    private String savePath;

    /**
     * 显示列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/sales",method = RequestMethod.GET)
    public String saleList(Model model){
        List<Customer> customerList=customerService.findAll();
        model.addAttribute("customerList",customerList);
        return "sales/list";
    }

    /**
     * ajax回调
     * @param request
     * @return
     */
    @RequestMapping(value = "/sales/list",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult<Sales> saleListShow(HttpServletRequest request){
        String draw = request.getParameter("draw");
        String length = request.getParameter("length");
        String start = request.getParameter("start");

        //查询参数
        String name = request.getParameter("name");
        name = Strings.toUTF8(name);
        String progress = request.getParameter("progress");
        progress = Strings.toUTF8(progress);
        String startDate = request.getParameter("startdate");
        String endDate = request.getParameter("enddate");


        Map<String, Object> param = Maps.newHashMap();
        param.put("length", length);
        param.put("start", start);
        param.put("name",name);
        param.put("progress",progress);
        param.put("startdate",startDate);
        param.put("enddate",endDate);

        List<Sales> salesList = salesService.findSalesByParam(param);
        Long count = salesService.findCount();
        Long filterCount = salesService.findFilterCount(param);

        return new DataTablesResult<>(draw,filterCount,salesList,count);

    }


    /**
     * 新建销售机会
     * @param sales
     * @return
     */
    @RequestMapping(value = "/sales/new",method = RequestMethod.POST)
    @ResponseBody
    public String newSales(Sales sales){

        salesService.save(sales);
        return "success";
    }

    /**
     * 查看机会信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/sales/view/{id:\\d+}",method = RequestMethod.GET)
    public String view(@PathVariable Integer id,Model model){
        Sales sales=salesService.findById(id);

       if(sales==null){
           throw new NotFoundException();
       }if(!sales.getUserid().equals(ShiroUtil.getCurrentUserId()) && !ShiroUtil.isAdmin()){
            throw new ForbiddenException();
        }
//        把创建的机会传值进去
        model.addAttribute("sales",sales);


//        把跟进记录传值进去
        List<Sales_log> salesLogList=salesService.findSalesLogBySalesId(id);
        model.addAttribute("salesLogList",salesLogList);

        //查找当前机会的文件列表
        List<Sales_file> salesFiles = salesService.findSalesFileBySalesId(id);
        model.addAttribute("salesFiles",salesFiles);

        return "sales/view";
    }

    /**
     * 新增跟进日志
     * @param salesLog
     * @return
     */
    @RequestMapping(value = "/sales/log/new",method = RequestMethod.POST)
    public String saveLog(Sales_log salesLog) {
        salesService.saveLog(salesLog);
        return "redirect:/sales/view/"+salesLog.getSalesid();
    }

    /**
     * 修改机会的进度
     */
    @RequestMapping(value = "/sales/progress/edit",method = RequestMethod.POST)
    public String editProgress(Integer id,String progress) {

        salesService.editSalesProgress(id,progress);
        return "redirect:/sales/view/"+id;
    }

    /**
     * 通过id删除机会
     * @param id
     * @return
     */
    @RequestMapping(value = "/sales/del/{id:\\d+}",method = RequestMethod.GET)
    @ResponseBody
    public String del(@PathVariable Integer id){
//        它下面关联有文件和记录，删除它之前要先把其他两个删除否则，报异常删除不了
        salesService.delSalesById(id);
        return "success";
    }

    /**
     * 上传文件
     * @param file
     * @param salesid
     * @return
     * @throws IOException
     */
    @RequestMapping(value ="/sales/file/upload",method = RequestMethod.POST)
    @ResponseBody
    public String uploadSales(MultipartFile file,Integer salesid) throws IOException {
        salesService.uploadFile(file.getInputStream(),file.getSize(),file.getOriginalFilename(),file.getContentType(),salesid);
        return "success";
    }

    @RequestMapping(value = "/file/download/{id:\\d+}",method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> download(@PathVariable Integer id) throws FileNotFoundException, UnsupportedEncodingException {

        Sales_file salesFile=salesService.findSalesFileById(id);
        if(salesFile==null){
            throw new NotFoundException();
        }

        File file=new File(savePath,salesFile.getFilename());
        if(!file.exists()){
            throw new NotFoundException();
        }

        FileInputStream fileInputStream=new FileInputStream(file);

        String name=salesFile.getName();
        name=new String(name.getBytes("UTF-8"),"ISO8859-1");

      return ResponseEntity
              .ok()
              .contentLength(file.length())//假如有进度条下载时候会显示多大
              .contentType(MediaType.parseMediaType(salesFile.getContenttype()))//可以让浏览器弹出另存为对话框
              .header("Content-Disposition","attachment;filename="+name)//filename这个是固定的（默认表示就是文件的真名）
              .body(new InputStreamResource(fileInputStream));
    }




}
