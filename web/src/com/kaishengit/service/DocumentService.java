package com.kaishengit.service;

import com.kaishengit.dao.DocumentDao;
import com.kaishengit.entity.Document;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

import javax.servlet.http.Part;
import java.io.*;
import java.util.UUID;

/**
 * Created by Administrator on 2016/6/16.
 */
public class DocumentService {

    private DocumentDao documentDao=new DocumentDao();

    public void updateFile(String filename, Long size, InputStream inputStream) throws IOException {
        ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(IOUtils.toByteArray(inputStream));

        String md5= DigestUtils.md5Hex(byteArrayInputStream);

        Document document=documentDao.finByMd5(md5);

        if (document==null) {

            String savename=saveFile(filename, byteArrayInputStream);//不是inputStream

            document=new Document();

            document.setFilename(filename);
            document.setSavename(savename);
            document.setExtname(filename.substring(filename.indexOf(".")));
            document.setMd5(md5);
            document.setSize(size);
            document.setDispsize(FileUtils.byteCountToDisplaySize(size));

            documentDao.insertFile(document);

        }

    }
    public String  saveFile(String filename,InputStream inputStream) throws IOException {

        inputStream.reset();

        File file=new File("D:/test");
        if(!file.exists()){
            file.mkdir();
        }
        //改名

        String exname=filename.substring(filename.indexOf("."));
        String uuid= UUID.randomUUID().toString();
        filename=uuid+exname;

        FileOutputStream outputStream=new FileOutputStream(new File(file,filename));

        IOUtils.copy(inputStream,outputStream);//它不提供关闭需要手动关闭

        inputStream.close();
        outputStream.flush();
        outputStream.close();
        return filename;

//        BufferedInputStream input=new BufferedInputStream(inputStream);
//        BufferedOutputStream output=new BufferedOutputStream(outputStream);
//
//        byte[] buffer=new byte[1024];
//        int len=-1;
//        while ((len=input.read(buffer))!=-1) {
//            output.write(buffer,0,len);
//        }
//        input.close();
//        output.flush();
//        output.close();
    }
}
