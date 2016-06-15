package com.kaishengit.Test;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Administrator on 2016/6/14.
 */
public class Md5TestCase {
    @Test
    public void md5Test(){
        String password="1111";
        String salt="35@*$^$@)!)";
        password= DigestUtils.md5Hex(password);
        System.out.println(password.length());
    }
    @Test
    public void shaTest(){
        String password="110";
        password=DigestUtils.sha1Hex(password);
        System.out.println(password.length());
    }
    @Test
    public void fileTest(){
        try {
            //b0baee9d279d34fa1dfd71aadb908c3f

            FileInputStream inputStream=new FileInputStream("D:/2.txt");
            String md5=DigestUtils.md5Hex(inputStream);
            System.out.println(md5);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
