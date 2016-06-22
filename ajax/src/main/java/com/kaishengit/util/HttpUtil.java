package com.kaishengit.util;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/6/21.
 */
public class HttpUtil {

    //用于获取html
    public static String getRequestText(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);
        HttpResponse resp = null;
        try {
            resp = httpClient.execute(httpGet);
            //http请求码
            int httpCode = resp.getStatusLine().getStatusCode();
            if (httpCode == 200) {
                InputStream inputStream = resp.getEntity().getContent();
                String html = IOUtils.toString(inputStream);
                return html;

            } else {
                System.out.println("请求异常：" + resp);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    //用于下载
    public static void getRequestStream(String url,String savePath){
        CloseableHttpClient httpClient= HttpClients.createDefault();

        try {
            HttpGet httpGet=new HttpGet(url);
            HttpResponse resp=httpClient.execute(httpGet);

            //http请求码
            int httpCode=resp.getStatusLine().getStatusCode();
            if(httpCode==200){
                InputStream inputStream= resp.getEntity().getContent();
                FileOutputStream out=new FileOutputStream(savePath);
                IOUtils.copy(inputStream,out);

                out.flush();
                out.close();
                inputStream.close();

            }else{
                System.out.println("请求异常："+resp);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
