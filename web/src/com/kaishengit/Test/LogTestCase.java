package com.kaishengit.Test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2016/6/13.
 */
public class LogTestCase {
    @Test
    public void testLog4j(){

        String name="lee";
        String book="《测试》";
        Logger logger= LoggerFactory.getLogger(LogTestCase.class);   //Logger.getLogger(LogTestCase.class);

        logger.trace("trace");
        logger.debug("debug");
        logger.info("{}借阅{}",name,book);
        logger.warn("warn");
        logger.error("error");
        //logger.fatal("fatal");
    }
}
