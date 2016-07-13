package com.kaishengit.service;

import com.kaishengit.mapper.NoticeMapper;
import com.kaishengit.pojo.Notice;
import com.kaishengit.util.ShiroUtil;


import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/12.
 */

@Named
public class NoticeService {

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
}

