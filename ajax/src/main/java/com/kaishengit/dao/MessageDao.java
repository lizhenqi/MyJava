package com.kaishengit.dao;

import com.kaishengit.entity.Message;
import com.kaishengit.util.Dbhelp;
import com.kaishengit.util.cache.EhCacheUtil;
import com.kaishengit.util.cache.SimpleCache;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Administrator on 2016/6/24.
 */
public class MessageDao {

    public List<Message> findAll() {

        List<Message> messageList = (List<Message>) EhCacheUtil.get("messageList");
        if (messageList == null) {
            String sql = "select *from t_message order by id desc";
            messageList= Dbhelp.userFind(sql, new BeanListHandler<Message>(Message.class));
            EhCacheUtil.set("messageList",messageList);
        }
        return messageList;
    }
    public void saveMessage(Message message){
        String sql="insert into t_message(author,message)values(?,?)";
        Dbhelp.userUpdate(sql,message.getAuthor(),message.getMessage());
        EhCacheUtil.remove("messageList");
    }

    public List<Message> findMaxId(int maxId) {
        String sql = "select *from t_message where id>? order by id desc";
        return Dbhelp.userFind(sql, new BeanListHandler<Message>(Message.class), maxId);
    }


//    private Logger logger= LoggerFactory.getLogger(MessageDao.class);
//    //添加cache测试
//    public Message findById(Integer id) {
//
//        Message message = (Message) SimpleCache.getCache("message:" + id);
//        if (message == null) {
//            String sql = "select *from t_message where id=?";
//            message= Dbhelp.userFind(sql, new BeanHandler<Message>(Message.class), id);
//            SimpleCache.setCache("message:"+id,message);
//        }else{
//            logger.info("数据来源于cache！");
//        }
//        return message;
//    }


    private Logger logger = LoggerFactory.getLogger(MessageDao.class);

    //添加cache测试
    public Message findById(Integer id) {

        Message message = (Message) EhCacheUtil.get("message:" + id);
        if (message == null) {
            String sql = "select *from t_message where id=?";
            message = Dbhelp.userFind(sql, new BeanHandler<Message>(Message.class), id);
            EhCacheUtil.set("message:" + id, message);
        } else {
            logger.info("数据来源于cache！");
        }
        return message;
    }
}
