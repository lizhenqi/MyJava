package com.kaishengit.util;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Administrator on 2016/7/10.
 */
public class TestToMd5 {

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5Hex("123123"));
    }
}
