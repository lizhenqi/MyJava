package com.kaishengit.mapper;

import com.kaishengit.pojo.Document;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/14.
 */
public interface DocumentMapper {
    List<Document> findByFid(int fid);

    void save(Document document);

    Document findById(Integer id);
} 
