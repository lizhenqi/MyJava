package com.kaishengit.service;

import com.kaishengit.dao.MessageDao;
import com.kaishengit.entity.Message;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;

/**
 * Created by Administrator on 2016/6/24.
 */
public class MessageService {
    private MessageDao messageDao=new MessageDao();
    public List<Message> findAll(){
        return messageDao.findAll();
    }

    public List<Message> findMaxId(int maxId) {

        return messageDao.findMaxId(maxId);

    }
}
