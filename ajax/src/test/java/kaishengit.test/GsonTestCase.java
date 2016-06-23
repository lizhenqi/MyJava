package kaishengit.test;

import com.google.gson.Gson;
import com.kaishengit.entity.User;
import netscape.security.UserTarget;
import org.junit.Test;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/23.
 */
public class GsonTestCase {

    private Logger logger= LoggerFactory.getLogger(GsonTestCase.class);

    @Test
    public void tesToJson(){
        User user=new User(12,"tom","中国",99.9f);
        String gson=new Gson().toJson(user);
        logger.info(gson);

    }

    @Test
    public void testMapToJson(){
        Map<String ,Object> map=new HashMap<>();
        map.put("键1","值1");
        map.put("键2","值2");

        String gson=new Gson().toJson(map);
        logger.info(gson);
    }

    @Test
    public void testArrayToJson(){
        String []arr={"name","address"};

        String gson=new Gson().toJson(arr);
        logger.info(gson);
    }

    @Test
    public void testObjectArrayToJson(){
        User []arr=new User[2];
        arr[0]=new User(11,"Lee","USA",99.6f);
        arr[1]=new User(10,"jim","中国",99.7f);
        String gson=new Gson().toJson(arr);
        logger.info(gson);
    }

    @Test
    public void testListToJson(){
        List<User> list=new ArrayList<>();
        list.add(new User(11,"Lee","USA",99.6f));
        list.add(new User(11,"Lee","USA",99.6f));

        String gson=new Gson().toJson(list);

        logger.info(gson);

    }
    @Test
    public void testOtherToJson(){
        List<User> list=new ArrayList<>();
        list.add(new User(11,"Lee","USA",99.6f));
        list.add(new User(11,"Lee","USA",99.6f));

        Map<String,Object> map=new HashMap<>();
        map.put("name","测试");
        map.put("de",list);
        String gson=new Gson().toJson(map);
        logger.info(gson);
    }
}
