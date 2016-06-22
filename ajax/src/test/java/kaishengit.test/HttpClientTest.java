package kaishengit.test;


import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.*;

/**
 * Created by Administrator on 2016/6/21.
 */
public class HttpClientTest {

    @Test
    public void testRequest() throws IOException {

        CloseableHttpClient httpClient= HttpClients.createDefault();

        HttpGet httpGet=new HttpGet("http://ww2.sinaimg.cn/mw690/a2bc75a2gw1f4uo9e80n1j20lc0sgq8g.jpg");
        HttpResponse resp=httpClient.execute(httpGet);

        //http请求码
        int httpCode=resp.getStatusLine().getStatusCode();
        if(httpCode==200){
           InputStream inputStream= resp.getEntity().getContent();
            String result= IOUtils.toString(inputStream);
            System.out.println(result);

        }else{
            System.out.println("请求异常："+resp);
        }

        httpClient.close();

    }



    @Test
    public void testDowload() throws IOException {

        CloseableHttpClient httpClient= HttpClients.createDefault();

        HttpGet httpGet=new HttpGet("http://ww2.sinaimg.cn/mw690/a2bc75a2gw1f4uo9e80n1j20lc0sgq8g.jpg");
        HttpResponse resp=httpClient.execute(httpGet);

        //http请求码
        int httpCode=resp.getStatusLine().getStatusCode();
        if(httpCode==200){
            InputStream inputStream= resp.getEntity().getContent();
            FileOutputStream out=new FileOutputStream("D:/test/测试.jpg");
            IOUtils.copy(inputStream,out);

            out.flush();
            out.close();
            inputStream.close();

        }else{
            System.out.println("请求异常："+resp);
        }

        httpClient.close();

    }

}
