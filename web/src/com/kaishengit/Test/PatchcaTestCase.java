package com.kaishengit.Test;

import com.kaishengit.exception.DataAccessException;
import org.junit.Test;
import org.patchca.color.RandomColorFactory;
import org.patchca.color.SingleColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.font.FontFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/6/15.
 */
public class PatchcaTestCase {
    @Test
    public void testPatchca(){
        ConfigurableCaptchaService service=new ConfigurableCaptchaService();
        service.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        service.setFilterFactory(new CurvesRippleFilterFactory(service.getColorFactory()));
        RandomWordFactory factory=new RandomWordFactory();
        factory.setMinLength(4);
        factory.setMaxLength(6);
        factory.setCharacters("1234567测试卡罗拉");
        service.setWordFactory(factory);
        service.setFontFactory(new FontFactory() {
            @Override
            public Font getFont(int i) {
                return new Font("微软雅黑",Font.ITALIC,25);
            }
        });

        try {
            FileOutputStream outputStream=new FileOutputStream("D:/patchca.jpg");
            EncoderHelper.getChallangeAndWriteImage(service,"jpg",outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            throw new DataAccessException("找不到文件",e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
