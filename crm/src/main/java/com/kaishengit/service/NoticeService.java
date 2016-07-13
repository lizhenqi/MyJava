package com.kaishengit.service;

import com.kaishengit.mapper.NoticeMapper;
import com.kaishengit.pojo.Notice;
import com.kaishengit.util.ShiroUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;


import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2016/7/12.
 */

@Named
public class NoticeService {


    //（地址不要写死，配置在config中）这是Simditor的图片路径配置在config中，这样可以取过来
    @Value("${imagePath}")
    private String imageSavePath;//即把上面的值赋值给这个变量


    @Inject
    private NoticeMapper noticeMapper;

    /**
     * 保存新公告
     * @param notice
     */
    public void saveNotice(Notice notice) {
        notice.setUserid(ShiroUtil.getCurrentUserId());
        notice.setRealname(ShiroUtil.getCurrentRealname());

        noticeMapper.save(notice);
    }

    /**
     * 查询或搜索的所有
     * @param param
     * @return
     */
    public List<Notice> findNoticeByParam(Map<String, Object> param) {
        return noticeMapper.findNoticeByParam(param);
    }

    /**
     * 查询公告总数
     * @return
     */
    public Long findAllCount() {
        return noticeMapper.findAllCount();
    }

    /**
     * 查询按参数搜索的数量
     * @param param
     * @return
     */
    public Long findFilterCount(Map<String, Object> param) {
        return noticeMapper.findFilterCount(param);
    }


    /**
     * 根据id查询公告
     * @param id
     * @return
     */
    public Notice findNoticeById(Integer id) {
        return noticeMapper.findNoticeById(id);
    }

    /**
     * 将Simditor在线编辑器上传的图片进行保存
     * @param inputStream
     * @param fileName
     */
    public String saveImage(InputStream inputStream, String fileName) throws IOException {
//        String exName=fileName.substring(fileName.lastIndexOf("."));
        String newFileName= UUID.randomUUID().toString();//+exName;

        FileOutputStream outputStream=new FileOutputStream(new File(imageSavePath,newFileName));
        IOUtils.copy(inputStream,outputStream);

        outputStream.flush();
        outputStream.close();
        inputStream.close();

        return "/preview/"+newFileName;
    }
}

