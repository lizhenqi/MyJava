package com.kaishengit.Test;

import com.kaishengit.web.FileServlet;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2016/6/16.
 */
public class FileServletTestCase {
    private static Logger logger= LoggerFactory.getLogger(FileServlet.class);
    @Test
    public void testFile(){
        String header="form-data; name=\"filename\"; filename=\"1e30e924b899a901c32ae3c21c950a7b0208f523.jpg\"";
        String name=header.substring(header.indexOf("filename=\""),header.length()-1);
        name=name.substring(name.indexOf("\"")+1);
        logger.debug("name:{}",name);
    }

}
