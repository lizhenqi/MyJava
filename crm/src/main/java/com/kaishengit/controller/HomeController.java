package com.kaishengit.controller;


import com.kaishengit.dto.FlashMessage;
import com.kaishengit.service.UserService;
import com.kaishengit.util.ServletUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/7/8.
 */
@Controller
public class HomeController {
    @Inject
    private UserService userService;


    /**
     * 登录（登录界面和提交验证）
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "login";
    }
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String login(String username, String password,
                        RedirectAttributes redirectAttributes,
                        HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
//        获取主题。即用户

        if(subject.isAuthenticated()){
            subject.logout();
//          如果用户已经登录过了，再次登录，就先退出之前的账号。
        }
        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, DigestUtils.md5Hex(password));
//            数据库里面存的是MD5加密后的，而获取的password是明文，故要加密后DigestUtils.md5Hex(password)再传入。
            subject.login(usernamePasswordToken);//login()这个方法在执行的时候会自动调用ShiroRealm实现类的验证方法。
//            这里需要传入进行登录验证：usernamePasswordToken（内传入username和password）

//          获取ip地址并保存登录日志
//           String ip=request.getRemoteAddr();//获取ip地址（固定的）
            String ip= ServletUtil.getRemoteIp(request);//对上面的改进使其ip为127.0.0.1
            userService.saveLog(ip);

            return "redirect:/home";
        }catch(LockedAccountException e){

            redirectAttributes.addFlashAttribute("message",new FlashMessage(FlashMessage.STATE_ERROR,"该账户已被禁用！"));

        }catch (AuthenticationException e){
//            AuthenticationException这个异常是所有验证异常的父类，故放在最后
//            e.printStackTrace();
//            System.out.println("登录异常");
            redirectAttributes.addFlashAttribute("message",new FlashMessage(FlashMessage.STATE_ERROR,"账号或是密码错误！"));
        }
        return "redirect:/";
    }

    /**
     * 安全退出
     * @return
     */
    @RequestMapping(value = "/logOut",method = RequestMethod.GET)
    public String logOut(RedirectAttributes redirectAttributes){
        SecurityUtils.getSubject().logout();
//        这个方法好，要是以前就是销毁session。

        redirectAttributes.addFlashAttribute("message",new FlashMessage("您已安全退出！"));
        return "redirect:/";
    }

    /**
     * home界面
     * @return
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    /**
     * 403错误页面
     * @return
     */
    @RequestMapping(value = "/403",method = RequestMethod.GET)
    public String error403(){
        return "error/403";
    }
}
