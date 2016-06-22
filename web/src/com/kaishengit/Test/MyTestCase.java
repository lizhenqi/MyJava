package com.kaishengit.Test;

import org.joda.time.DateTime;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Test
    public void testTime(){
        Date date=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now=simpleDateFormat.format(date);
        System.out.println(now);
    }
    //joda-time解决获取时间问题
    @Test
    public void testJodaTime(){
        DateTime dateTime=DateTime.now();
        dateTime=dateTime.plusHours(1);

        String now =dateTime.toString("yyyy-MM-dd HH:mm:ss");
        System.out.println(now);
    }
}
