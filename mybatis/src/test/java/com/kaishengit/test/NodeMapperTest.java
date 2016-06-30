package com.kaishengit.test;

import com.google.common.collect.Lists;
import com.kaishengit.mapper.NodeMapper;
import com.kaishengit.pojo.Node;
import com.kaishengit.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Administrator on 2016/6/28.
 */
public class NodeMapperTest {
    private Logger logger= LoggerFactory.getLogger(NodeMapperTest.class);

    @Test
    public void testBatchSave(){
        SqlSession sqlSession=MyBatisUtil.getSqlSession();
        NodeMapper nodeMapper=sqlSession.getMapper(NodeMapper.class);

        List<Node> nodeList= Lists.newArrayList();

        nodeList.add(new Node("测试6"));
        nodeList.add(new Node("测试7"));
        nodeList.add(new Node("测试8"));

        nodeMapper.batchSave(nodeList);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testFindIdList(){
        SqlSession sqlSession=MyBatisUtil.getSqlSession();
        NodeMapper nodeMapper=sqlSession.getMapper(NodeMapper.class);

        List<Integer> idList=Lists.newArrayList();
        idList.add(1);
        idList.add(3);

        List<Node> nodeList= nodeMapper.findIdList(idList);

        for(Node node:nodeList){
            logger.info("测试{}",node);
        }
        sqlSession.close();

    }

    @Test
    public void testFindById(){

        //一级缓存。（同时满足：对同一个sqlSession,同一个对象的多次查处）

        //二级缓存。（同一个SqlSessionFactory产生的sqlSession，对同一个对象多次查询）

        //一级缓存无需配置，二级缓存需要配置才会生效：1.放入的对象需要序列化。2.mapper.xml中添加<cache/>
        SqlSession sqlSession=MyBatisUtil.getSqlSession();
        NodeMapper nodeMapper=sqlSession.getMapper(NodeMapper.class);

        Node node=nodeMapper.findById(1);
         node=nodeMapper.findById(1);
         node=nodeMapper.findById(1);
        logger.info("测试1{}",node);
        sqlSession.close();
//----------------------------------------------------------------
        SqlSession sqlSession2=MyBatisUtil.getSqlSession();
        NodeMapper nodeMapper2=sqlSession2.getMapper(NodeMapper.class);

        Node node2=nodeMapper2.findById(1);
        node2=nodeMapper2.findById(1);
        logger.info("测试2{}",node2);
        sqlSession.close();







    }

}
