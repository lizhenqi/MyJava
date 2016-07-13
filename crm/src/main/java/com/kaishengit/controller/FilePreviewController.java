package com.kaishengit.controller;

import com.kaishengit.exception.NotFoundException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Administrator on 2016/7/13.
 */
@Controller
public class FilePreviewController {


    /**
     * 获取读取图片的地址
     */
    @Value("${imagePath}")
    private String path;
//    由于在applicationContext.xml中把Conteoller注解排除了，而这又需要自由再次在mvc中配置（读取config文件）

    /**
     * 读取图片（给一个名字就能读取给出的图片名字应该是：/preview/+名字）
     * @param fileName
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/preview/{fileName}")//可以如：value = "/preview/{fileName：.+}"一样获取后缀，也可以在mvc中配置直接带.的path变量（本次都测试了，现在才有第二种）
    public ResponseEntity<InputStreamResource> previewFile(@PathVariable String fileName) throws IOException {

        File file=new File(path,fileName);
        if(!file.exists()){
            throw new NotFoundException();
        }

        FileInputStream inputStream=new FileInputStream(file);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)//表示是图片
                .body(new InputStreamResource(inputStream));






//方法一(上面是方法二)
//        , HttpServletResponse response（参数）
//        OutputStream outputStream=response.getOutputStream();
//        IOUtils.copy(inputStream,outputStream);
//        outputStream.flush();
//        outputStream.close();
//        inputStream.close();

    }
}
