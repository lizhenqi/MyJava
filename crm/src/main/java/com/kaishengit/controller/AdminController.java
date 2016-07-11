package com.kaishengit.controller;


import com.google.common.collect.Maps;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import com.kaishengit.util.ShiroUtil;
import com.kaishengit.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/11.
 */
@Controller
public class AdminController {

    @Inject
    private UserService userService;


    @RequestMapping(value = "/admin/user",method = RequestMethod.GET)
    public String userList(){

        return "/admin/userList";
    }

    @RequestMapping(value = "/admin/user/list",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult userTestList(HttpServletRequest request){

        String start=request.getParameter("start");
        String draw=request.getParameter("draw");
        String length=request.getParameter("length");
        String keyword=request.getParameter("search[value]");
        keyword= Strings.toUTF8(keyword);

        Map<String ,Object> param= Maps.newHashMap();
        param.put("start",start);
        param.put("length",length);
        param.put("keyword",keyword);

        List<User> userList=userService.findAllUserByParam(param);

        Long count=userService.findAllCount();

        Long filterCount=userService.findFilterCount(param);

        return new DataTablesResult<>(draw,filterCount,userList,count);
    }



}
