package com.kaishengit.test;

import com.kaishengit.pojo.User;
import com.kaishengit.util.MyBatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by Administrator on 2016/6/27.
 */
public class MyBatisTest {
    private Logger logger = LoggerFactory.getLogger(MyBatisTest.class);

    @Test
    public void testBatisXml() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

            SqlSession sqlSession = sqlSessionFactory.openSession();


            User user = sqlSession.selectOne("com.kaishengit.mapper.UserMapper.findId", 35);
            logger.info("{}", user);

            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSave() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//            SqlSession sqlSession=sqlSessionFactory.openSession(true);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            User user = new User();
            user.setUsername("测试");
            user.setPassword("测试");
            user.setAddress("测试");

            sqlSession.insert("com.kaishengit.mapper.UserMapper.save", user);
            sqlSession.commit();
            logger.info("成功");

            sqlSession.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpDate() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        User user = sqlSession.selectOne("com.kaishengit.mapper.UserMapper.findId", 39);

        user.setUsername("再次测试");

        sqlSession.update("com.kaishengit.mapper.UserMapper.update", user);
        sqlSession.commit();//老忘记提交
        sqlSession.close();
        logger.info("成功");

    }

    @Test
    public void testDel() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        sqlSession.delete("com.kaishengit.mapper.UserMapper.delete", 39);

        sqlSession.commit();
        sqlSession.close();
        logger.info("成功");
    }


    @Test
    public void testFindAll(){
        SqlSession sqlSession=MyBatisUtil.getSqlSession();
        List<User> userList=sqlSession.selectList("com.kaishengit.mapper.UserMapper.findAll");

        for(User user:userList){
            logger.info("{}",user);
        }

sqlSession.close();
    }
}
