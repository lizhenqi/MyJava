package kaishengit.test;

import com.kaishengit.util.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Administrator on 2016/6/22.
 */
public class JsoupTest {
    @Test
    public void jsoupTest() throws IOException {
                                                                    //cookie("is_click","1")是网站确定是否浏览登录网站
        Document document= Jsoup.connect("http://www.topit.me/pop").cookie("is_click","1").get();
        Elements elements=document.select("#content .catalog .e>a");

        for(Element element:elements){

            String href=element.attr("href");
            System.out.println("href:"+href);

            Document bigImage=Jsoup.connect(href).cookie("is_click","1").get();
            Element imageElement=bigImage.select("#content>a").first();

            String imageSrc=imageElement.attr("href");
            System.out.println(imageSrc);

            String imgName=imageSrc.substring(imageSrc.lastIndexOf("/"));//定义下载图片的名字
            HttpUtil.getRequestStream(imageSrc,"d:/test/"+imgName);


        }
    }
}
