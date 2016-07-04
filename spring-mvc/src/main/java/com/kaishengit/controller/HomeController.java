package com.kaishengit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2016/7/4.
 */
@Controller
public class HomeController {

    private Logger logger= LoggerFactory.getLogger(HomeController.class);

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home(){
        logger.info("测试");
        return "home";
    }


//    SpringMVC使用步骤：
//    1. 添加pom依赖
//    2. 修改web.xml的schema
//    3. 在web.xml中添加中央控制器的配置
//    4. 在/WEB-INF/{servlet-name}-servlet.xml，该文件为SpringMVC的配置文件
//    4.1 扫描控制器
//    4.2 基于注解的控制器
//    4.3 视图解析器
//    5. 写自己的控制器


}
