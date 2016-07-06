package com.kaishengit.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2016/7/5.
 */
@RestController
public class RestfulController {

@RequestMapping(value = "/api",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String restfulTest(){
        return "测试";
    }
}
