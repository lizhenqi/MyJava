package com.kaishengit.mapper;

import com.kaishengit.pojo.Tag;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Administrator on 2016/6/29.
 */
public interface TagMapper {
//上面报错没事，现在不用xml，而是用注解
    @Select("select *from t_tag where userid=#{userid}")
    List<Tag> findId(Integer userid);
}
