package com.kaishengit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2016/7/4.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    private Logger logger= LoggerFactory.getLogger(UserController.class);


//    可以把公共前缀value ="/users",提取出来写在类上
    @RequestMapping(method = RequestMethod.GET)
    public String userList(){

        return "users/list";
    }


//    只写value = "/{id}"如果输入不匹配就会报400 bad request 加上value = "/{id:\\d+}"就会报404找不到而不是错误请求
//    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.GET)
//    public String userShow(@PathVariable Integer id){
//       logger.info("ID为 ：{}",id);
//        return "users/userOne";
//    }

//    如果id和传的参数不一致如下，为userId而不是id，这样必须在变量后面加上@PathVariable("id")
    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.GET)
    public String showOneUser(@PathVariable("id") Integer userId){
        logger.info("ID为 ：{}",userId);
        return "users/show";
    }

    @RequestMapping(value = "/{userid:\\d+}/{id:\\d+}",method = RequestMethod.GET)
    public String showOnePhoto(@PathVariable Integer userid,@PathVariable Integer id){
        logger.info("userid ：{},id :{}",userid,id);
        return "users/photos";
    }
}
