package com.kaishengit.test;

import com.google.common.collect.Maps;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.Tag;
import com.kaishengit.pojo.User;
import com.kaishengit.util.MyBatisUtil;
import netscape.security.UserTarget;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/28.
 */
public class MyBatisInterfaceTest {

    private Logger logger = LoggerFactory.getLogger(MyBatisInterfaceTest.class);

    @Test
    public void testFindId() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();

        //getMapper()方法使用了【动态代理模式】-> 自动产生一个接口的实现类
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.findId(10);


        List<Tag> tagList=user.getTagList();
        for(Tag tag:tagList){
            logger.info("测试{}",tag);
        }



//        logger.info("测试{}",user);
        sqlSession.close();

    }

    @Test
    public void testSave() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setUsername("Interface测试2");
        user.setPassword("1");
        userMapper.save(user);

        sqlSession.commit();
        sqlSession.close();

        logger.info("新增成功:{}",user.getId());
    }

    @Test
    public void testDelete() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        userMapper.delete(16);
        logger.info("删除成功");

//每次都忘了提交和释放资源
        sqlSession.commit();
        sqlSession.close();
        logger.info("删除成功");
    }

    @Test
    public void testUpdate() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.findId(16);
        user.setUsername("测试1");

        userMapper.update(user);

        sqlSession.commit();
        sqlSession.close();
        logger.info("修改成功");
    }

    @Test
    public void testFindAll() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> userList = userMapper.findAll();
        for (User user : userList) {
            logger.info("{}", user);
        }
        sqlSession.commit();
        sqlSession.close();
        logger.info("查询全部");
    }

    //根据用户名和密码查询
    @Test
    public void testFindMap() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        Map<String, Object> map = Maps.newHashMap();
//                new HashMap<>();也可以用这个
//       上面要用钻石操作符就得加编译插件jdk7以上或是dependency google/guava

        map.put("username", "Interface测试1");
        map.put("password", "1");

        User user = userMapper.findMap(map);

        sqlSession.commit();
        sqlSession.close();

        logger.info("{}", user);
    }

    @Test
    public void testFindParam(){
        SqlSession sqlSession=MyBatisUtil.getSqlSession();
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

        User user=userMapper.findParam("罗贯中","测试1");
        logger.info("成功");
        sqlSession.close();
    }
    @Test
    public void testFindByParam(){
        SqlSession sqlSession=MyBatisUtil.getSqlSession();
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

        Map<String ,Object> userMap=Maps.newHashMap();

//        userMap.put("username","罗贯中");
        userMap.put("password","测试1");

        List<User> userList=userMapper.findByParam(userMap);
        for(User user:userList){
            logger.info("{}",user);
        }
//        logger.info("{}",user);
        sqlSession.close();
    }
    @Test
    public void testPage(){
        SqlSession sqlSession=MyBatisUtil.getSqlSession();
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);

        List<User> userList=userMapper.findByPage(0,2);
        for(User user:userList){
            logger.info("{}",user);
        }
        sqlSession.close();


    }


}
