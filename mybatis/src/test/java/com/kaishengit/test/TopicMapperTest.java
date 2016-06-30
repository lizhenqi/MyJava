package com.kaishengit.test;

import com.kaishengit.mapper.TopicMapper;
import com.kaishengit.pojo.Topic;
import com.kaishengit.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2016/6/29.
 */
public class TopicMapperTest {
    private Logger logger= LoggerFactory.getLogger(TopicMapper.class);
    @Test
    public void testFindId(){
        SqlSession sqlSession= MyBatisUtil.getSqlSession();
        TopicMapper topicMapper=sqlSession.getMapper(TopicMapper.class);

        Topic topic=topicMapper.findId(2);
        System.out.println(topic);

        logger.debug("测试UserName:{}",topic.getUser().getUsername());
        logger.debug("测试NodeName:{}",topic.getNode().getNodename());
        sqlSession.close();

    }
}
