package com.kaishengit.mapper;

import com.kaishengit.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/28.
 */
public interface UserMapper {



    @Select("select * from t_user where id = #{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password"),
            @Result(column = "avatar",property = "avatar"),
            @Result(column = "createtime",property = "createtime"),
            @Result(column = "loginip",property = "loginip"),
            @Result(column = "logintime",property = "logintime"),
            @Result(column = "state",property = "state"),
            @Result(property = "tagList",javaType = List.class,column = "id",many = @Many(
                    select = "com.kaishengit.mapper.TagMapper.findId"
            ))
    })
    public User findId(Integer id);



    @Insert(" INSERT INTO t_user(username, password, email, avatar, createtime, loginip, logintime, state)\n" +
            "VALUES (#{username},#{password},#{email},#{avatar},#{createtime},#{loginip},#{logintime},#{state})")
    public void save(User user);


@Delete("DELETE from t_user WHERE id=#{id}")
    public void delete(Integer id);


@Update(" UPDATE t_user\n" +
        "         set\n" +"            username = #{username},\n" +
        "            password = #{password},\n" +
        "             email = #{email},\n" +
        "             avatar=#{avatar},\n" +
        "             loginip=#{loginip},\n" +
        "             logintime=#{logintime},\n" +
        "             state=#{state}\n" +
        "        where id = #{id}")
    public void update(User user);



    public List<User> findAll();

    //封装成map时候
    public User findMap(Map<String, Object> param);

    //多个参数的时候
    public User findParam(@Param("username") String username, @Param("pwd") String password);

    public List<User> findByParam(Map<String, Object> map);


    @Select("select * from t_user limit ${start},${size} ")
    public List<User> findByPage(@Param("start") int start, @Param("size") int size);
}
