package com.kaishengit.controller;


import com.google.common.collect.Maps;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.dto.JsonResult;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import com.kaishengit.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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


    /**
     * 用户列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin/user",method = RequestMethod.GET)
    public String userList(Model model){
//      Model向视图里面写入，而@ResponseBody向js里面写入

        List<Role> roleList=userService.findAllRole();
        model.addAttribute("roleList",roleList);
        return "/admin/userList";
    }

    /**
     * ajax回调用户
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/user/list",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult userTestList(HttpServletRequest request){

        String start=request.getParameter("start");
        String draw=request.getParameter("draw");
        String length=request.getParameter("length");
        String keyword=request.getParameter("search[value]");

//        下面要转码，否则中文搜索就是乱码
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


    /**
     * 新增用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/admin/user/new",method = RequestMethod.POST)
    @ResponseBody
    public String userNew(User user){
        userService.saveUser(user);
        return "success";
    }

    /**
     * 验证账号是否可注册（就是查询是否存在）
     * @param username
     * @return
     */
    @RequestMapping(value = "/admin/user/checkusername",method = RequestMethod.GET)
    @ResponseBody
    public String userCheckUsername(String username){
        username=Strings.toUTF8(username);
        User user=userService.findUserByUsername(username);
        if(user==null){
            return "true";
        }
        return "false";
    }

    /**
     * 重置密码
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/user/setPwd",method = RequestMethod.POST)
    @ResponseBody
    public String userSetPwd(Integer id){
        userService.updateUserPwd(id);
        return "success";
    }


    /**
     * 根据用户id显示json
     * @param id
     * @return
     */
    @RequestMapping(value = "/admin/user/{id:\\d+}.json",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult userShow(@PathVariable Integer id){
        User user=userService.findUserById(id);
        if(user!=null){
//            return new JsonResult(JsonResult.SUCCESS,user);
            //像这种判断型的返回的。最好都封装了，调用时候成功的返回对应数据（Object）,失败的返回错误信息

            return new JsonResult(user);//是上面的简写形式（在JsonResult里面优化了）
        }else {
//            return new JsonResult(JsonResult.ERROR,"找不到"+id+"对应的用户");
            return new JsonResult("找不到"+id+"对应的用户");
        }
    }


    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/admin/user/edit",method = RequestMethod.POST)
    @ResponseBody
    public String userEdit(User user){
        userService.userEdit(user);
        return "success";
    }



}
