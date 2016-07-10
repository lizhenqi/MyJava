package com.kaishengit.controller;

import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.User;
import com.kaishengit.pojo.UserLog;
import com.kaishengit.service.UserService;
import com.kaishengit.util.ShiroUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/10.
 */
@Controller
public class UserController {
    @Inject
    private UserService userService;

    /**
     * 修改密码
     *
     * @return
     */
    @RequestMapping(value = "/user/password", method = RequestMethod.GET)
    public String setPassword() {
        return "setting/setPassword";
    }

    @RequestMapping(value = "/user/password", method = RequestMethod.POST)
    @ResponseBody
    public String savePassword(String password) {

        userService.changePassword(password);

        return "success";
    }


    /**
     * 验证旧密码（ajax调用）
     *
     * @return
     */
    @RequestMapping(value = "/user/validate/password", method = RequestMethod.GET)
    @ResponseBody
    public String validatePassword(@RequestHeader("X-Requested-With") String xRequestedwith, String oldPassword) {
//        通过ajax调用防止暴露，ajax调用时候，通过调请求头X-Requested-With的值保证
        if ("XMLHttpRequest".equals(xRequestedwith)) {
            User user = ShiroUtil.getCurrentUser();
            if (user.getPassword().equals(DigestUtils.md5Hex(oldPassword))) {
                return "true";
            }
            return "false";
        } else {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = "/user/log", method = RequestMethod.GET)
    public String lookLog() {
        return "setting/logList";
    }

    @RequestMapping(value = "/user/log/list", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult lookLogList(HttpServletRequest request) {

        String draw = request.getParameter("draw");
        String start = request.getParameter("start");//当前页偏移量
        String length = request.getParameter("length");//每页显示数据

        List<UserLog> userLogList=userService.findCurrentUsreLog(start,length);
        Long count=userService.findCurrentUsreLogCont();

        return new DataTablesResult<UserLog>(draw,count,userLogList,count);
    }
}
