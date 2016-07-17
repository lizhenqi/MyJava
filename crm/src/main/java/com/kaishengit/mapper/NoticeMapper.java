package com.kaishengit.mapper;

import com.kaishengit.pojo.Notice;

import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2016/7/12.
 */
public interface NoticeMapper {

    void save(Notice notice);

    List<Notice> findNoticeByParam(Map<String, Object> param);

    Long findAllCount();

    Long findFilterCount(Map<String, Object> param);

    Notice findNoticeById(Integer id);

    void del(Integer id);
}
