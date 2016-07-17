package com.kaishengit.util;

import com.google.common.collect.Maps;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/17.
 */
public class TestToQRcode {

    /**
     * 二维码测试
     * @param args
     * @throws WriterException
     * @throws IOException
     */
    public static void main(String[] args) throws WriterException, IOException {

        String me = "MECARD:N:李振起;ORG:河南理工大学;TEL:18336880379;ADR:焦作;NOTE:个人信息;;";
        Map<EncodeHintType, Object> encode = Maps.newHashMap();
        encode.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        BitMatrix bitMatrix = new
                MultiFormatWriter().encode(me, BarcodeFormat.QR_CODE, 400, 400, encode);//最后得传一个Map
        MatrixToImageWriter.writeToStream(bitMatrix, "jpg", new FileOutputStream("D:/test/22222.jpg"));
        //文本，二维码格式，输出流
    }
}
