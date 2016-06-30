package com.kaishengit.mapper;

import com.kaishengit.pojo.Node;
import com.kaishengit.pojo.Topic;
import com.kaishengit.pojo.User;
import org.apache.ibatis.annotations.*;

/**
 * Created by Administrator on 2016/6/29.
 */

@CacheNamespace
public interface TopicMapper {

    @Select("select *from t_topic where id=#{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "title",property = "title"),
            @Result(column = "text",property = "text"),
            @Result(column = "createtime",property = "createtime"),
            @Result(column = "userid",property = "userid"),
            @Result(column = "nodeid",property = "nodeid"),
            @Result(column = "viewnum",property = "viewnum"),
            @Result(column = "favnum",property = "favnum"),
            @Result(column = "likenum",property = "likenum"),
            @Result(column = "replynum",property = "replynum"),
            @Result(column = "replytime",property = "replytime"),
            @Result(property = "user",column = "userid",javaType = User.class,one = @One(
                    select = "com.kaishengit.mapper.UserMapper.findId"
            )),
            @Result(property = "node",column = "nodeid",javaType = Node.class,one = @One(
                    select = "com.kaishengit.mapper.NodeMapper.findById"
            ))
    })

    @Options(useCache = false)//不使用缓存
    Topic findId(Integer id);
}
