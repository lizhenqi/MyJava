package com.kaishengit.util;


import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2016/7/6.
 */
public class Strings {


    /**
     * 转为UTF-8
     * @param str
     * @return
     */
    public static String toUTF8(String str){
        if(StringUtils.isNotEmpty(str)){
            try {
                return new String(str.getBytes("ISO8859-1"),"utf-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("字符串转码异常！");
            }
        }
        return "";
    }


    /**
     * 测试汉语转拼音
     * @param str
     * @return
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public static String toPinYin(String str){
        HanyuPinyinOutputFormat format=new HanyuPinyinOutputFormat();
        format.setVCharType(HanyuPinyinVCharType.WITH_V);//让v代替u
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);//不要音调
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);//大小写
        try {
            return PinyinHelper.toHanYuPinyinString(str,format,"",true);
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
            throw new RuntimeException("汉语转为拼音异常");
        }
    }
}
