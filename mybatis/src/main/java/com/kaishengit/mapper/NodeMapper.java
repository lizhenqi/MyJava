package com.kaishengit.mapper;

import com.kaishengit.pojo.Node;
import org.apache.ibatis.annotations.Select;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public interface NodeMapper {

    public void batchSave(List<Node> nodeList);

    public List<Node> findIdList(List<Integer> idList);


    @Select("select *from t_node where id=#{id}")
    Node findById(Integer id);

}

