package com.kaishengit.controller;


import com.google.common.collect.Maps;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.Document;
import com.kaishengit.service.DocumentService;
import com.kaishengit.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/14.
 */
@Controller
public class DocumentController {

    @Inject
    private DocumentService documentService;


    @Value("${imagePath}")
    private String docSavePath;

    /**
     * 文档列表
     *
     * @return
     */
    @RequestMapping(value = "/doc", method = RequestMethod.GET)
    public String list(Model model,
                       @RequestParam(required = false, defaultValue = "0") Integer fid) {

        List<Document> documentList = documentService.findDocumentFid(fid);
        model.addAttribute("documentList", documentList);
        model.addAttribute("fid", fid);

        return "document/list";
    }


    /**
     * 保存新的文件夹
     *
     * @param name
     * @param fid
     * @return
     */
    @RequestMapping(value = "/doc/dir/new", method = RequestMethod.POST)
    public String saveDir(String name, Integer fid) {

        documentService.saveDir(name, fid);

        return "redirect:/doc?fid=" + fid;
    }

    /**
     * 文件上传
     * @param file
     * @param fid
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/doc/file/upload", method = RequestMethod.POST)
    @ResponseBody
    public String saveFile(MultipartFile file, Integer fid) throws IOException {

        if (file.isEmpty()) {
            throw new NotFoundException();
        } else {
            documentService.saveFile(file.getInputStream(), file.getOriginalFilename(), file.getSize(), file.getContentType(), fid);
        }
        return "success";
    }

    /**
     * 文件下载
     * @param id
     * @return
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/doc/download/{id:\\d+}",method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> download(@PathVariable Integer id) throws FileNotFoundException, UnsupportedEncodingException {
       Document document= documentService.findDocumentById(id);
        if(document==null){
            throw new NotFoundException();
        }
        File file=new File(docSavePath,document.getFilename());
        if(!file.exists()){
            throw new NotFoundException();
        }

        FileInputStream inputStream=new FileInputStream(file);

        String name=document.getName();
        name=new String(name.getBytes("UTF-8"),"ISO8859-1");

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(document.getContexttype()))//可以让浏览器弹出另存为对话框
                .contentLength(file.length())//假如有进度条下载时候会显示多大
                .header("Content-Disposition","attachment;filename="+name)//filename这个是固定的（默认表示就是文件的真名）
                .body(new InputStreamResource(inputStream));


    }


}
