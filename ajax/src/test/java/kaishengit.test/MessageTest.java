package kaishengit.test;

import com.kaishengit.entity.Message;
import com.kaishengit.service.MessageService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Administrator on 2016/6/24.
 */
public class MessageTest {
    private Logger logger= LoggerFactory.getLogger(MessageTest.class);
    @Test
    public void testMessage(){
        MessageService messageService=new MessageService();
        List<Message> messageList=messageService.findAll();
        for(Message message:messageList){
            logger.info("{}",message);
        }

    }

}
