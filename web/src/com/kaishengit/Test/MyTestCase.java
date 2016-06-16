package com.kaishengit.Test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Created by Administrator on 2016/6/15.
 */
public class MyTestCase {

    private static Logger logger= LoggerFactory.getLogger(MyTestCase.class);

    @Test
    public void testUUID(){
        UUID uuid=UUID.randomUUID();
        String msg=uuid.toString();
        logger.info(msg);
        System.out.println(msg);
    }
}
