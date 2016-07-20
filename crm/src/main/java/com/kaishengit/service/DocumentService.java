package com.kaishengit.service;

import com.kaishengit.mapper.DocumentMapper;
import com.kaishengit.pojo.Document;
import com.kaishengit.util.ShiroUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2016/7/14.
 */
@Named
public class DocumentService {

    @Inject
    private DocumentMapper documentMapper;


    @Value("${imagePath}")
    private String docSavePath;

    public List<Document> findDocumentFid(int fid) {

        return documentMapper.findByFid(fid);
    }

    /**
     * 新建文件夹
     *
     * @param name
     * @param fid
     */
    public void saveDir(String name, Integer fid) {
        Document document = new Document();

        document.setName(name);
        document.setFid(fid);
        document.setCreateuser(ShiroUtil.getCurrentRealname());
        document.setType(Document.TYPE_DIR);

        documentMapper.save(document);

    }

    /**
     * 保存文件
     *
     * @param inputStream      文件输入流
     * @param originalFilename 文件真实名称
     * @param size             文件大小(字节)
     * @param contentType      文件MIME类型
     * @param fid              父ID
     */
    //应该加上事务，要挂全挂
    @Transactional
    public void saveFile(InputStream inputStream, String originalFilename, long size, String contentType, Integer fid) {


//        String md5 = null;
        String exName="";
        if(originalFilename.lastIndexOf(".")!=-1){
//            因为有的文件可能没有后缀
            exName = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String newName = UUID.randomUUID().toString() + exName;
        try {
//            md5 = DigestUtils.md5Hex(inputStream);

            FileOutputStream outputStream=new FileOutputStream(new File(docSavePath,newName));
            IOUtils.copy(inputStream,outputStream);

            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
//            为了让事务有效要抛出RuntimeException
        }


        Document document = new Document();
        document.setType(Document.TYPE_DOC);
        document.setCreateuser(ShiroUtil.getCurrentRealname());
        document.setFid(fid);
        document.setName(originalFilename);
        document.setContexttype(contentType);
        document.setSize(FileUtils.byteCountToDisplaySize(size));
//        document.setMd5(md5);
        document.setFilename(newName);
        documentMapper.save(document);
    }


    /**
     * 根据ID获取文件
     * @param id
     */
    public Document findDocumentById(Integer id) {
        return documentMapper.findById(id);
    }
}
