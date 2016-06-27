package kaishengit.test;

import com.kaishengit.entity.Message;
import com.kaishengit.service.MessageService;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Administrator on 2016/6/24.
 */
public class MessageTest {
    private Logger logger = LoggerFactory.getLogger(MessageTest.class);

    private MessageService messageService = new MessageService();

    @Test
    public void testMessage() {

        List<Message> messageList = messageService.findAll();
        for (Message message : messageList) {
            logger.info("{}", message);
        }
    }

    @Test
    public void testMessageCache() {
        Message message = messageService.findById(1);
        Message message1 = messageService.findById(1);
        Message message2 = messageService.findById(1);
        System.out.println(message);
        Assert.assertNotNull(message);
    }

    @Test
    public void testSystem() {
        System.out.println(System.getProperty("java.io.tmpdir"));
    }

    @Test
    public void testEnCache() {
        CacheManager cacheManager = new CacheManager();
        Ehcache ehcache = cacheManager.getEhcache("myCache");//这个里面用ehcache.xml里面的cache的name=myCache
//存
        Element element = new Element("message:1", "java");
        ehcache.put(element);
//取
        Element out = ehcache.get("message:1");
        if (out != null) {
            String msg = (String) out.getObjectValue();
            System.out.println(msg);
        }
//删
        ehcache.remove("message:1");

        //System.out.println(ehcache.get("message:1").getObjectValue());


    }

    @Test
    public void testFindAll() {
        List<Message> messageList = messageService.findAll();//从DB
        messageList = messageService.findAll();//从cache

//        Message message = new Message();
//        message.setAuthor("lee2");
//        message.setMessage("Cache测试");

        messageService.saveMessage("测试1","测试2");//remove cache

        messageList = messageService.findAll();//从DB
        messageList = messageService.findAll();//从cache

        Assert.assertEquals(17, messageList.size());
    }
}
