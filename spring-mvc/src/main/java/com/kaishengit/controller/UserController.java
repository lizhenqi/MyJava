package com.kaishengit.controller;

import com.kaishengit.pojo.User;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/7/4.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);


    //    可以把公共前缀value ="/users",提取出来写在类上
//   1：传值测试（Model）
    @RequestMapping(method = RequestMethod.GET)
    public String userList(Model model,
                           @RequestParam(required = false, defaultValue = "vip测试") String vip,
                           @RequestParam(required = false, defaultValue = "age测试") String age,
                           @RequestHeader(value = "User-Agent", required = false, defaultValue = "请求头") String userAgent,
                           @CookieValue(value = "JSESSIONID") String sessionId) {
//        @RequestHeader(values="")分别为获取请求头，cookies的值。不是必须的，取不着默认为“请求头”

//        String msg="传值测试！";

        model.addAttribute("attr1", userAgent);
        model.addAttribute("attr2", sessionId);
        model.addAttribute("attr3", vip);
        model.addAttribute("attr4", age);
        return "users/list";
    }


//    只写value = "/{id}"如果输入不匹配就会报400 bad request 加上value = "/{id:\\d+}"就会报404找不到而不是错误请求
//    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.GET)
//    public String userShow(@PathVariable Integer id){
//       logger.info("ID为 ：{}",id);
//        return "users/show";
//    }

//    如果id和传的参数不一致如下，为userId而不是id，这样必须在变量后面加上@PathVariable("id")
//    2：这种传值方式比较麻烦知道即可
//    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.GET)
//    public ModelAndView showOneUser(@PathVariable("id") Integer userId){
//        logger.info("ID为 ：{}",userId);
//       ModelAndView modelAndView=new ModelAndView();
//        modelAndView.setViewName("users/show");
//        modelAndView.addObject("userId",userId);
//
//        return modelAndView;
//    }


    //    如果id和传的参数不一致如下，为userId而不是id，这样必须在变量后面加上@PathVariable("id")
//    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.GET)
//    public String showOneUser(@PathVariable("id") Integer userId){
//        logger.info("ID为 ：{}",userId);
//        return "users/show";
//    }

    @RequestMapping(value = "/{userid:\\d+}/{id:\\d+}", method = RequestMethod.GET)
    public String showOnePhoto(@PathVariable Integer userid, @PathVariable Integer id) {
        logger.info("userid ：{},id :{}", userid, id);
        return "users/photos";
    }


    //    获取
    @RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public String newUser() {

        return "users/newUser";
    }
//新增->从定向到users
//    @RequestMapping(value = "/newUser",method = RequestMethod.POST)
//    public String saveUser(String username,String password,Integer age){
//        logger.info("username：{},password：{},age:{}",username,password,age);
//        return "redirect:/users";
//    }

    //新增->从定向到users
    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String saveUser(User user) {
//        可以把对象装进User，但是必须对应User内有的。
        logger.info("username：{},password：{},age:{}", user.getUsername(), user.getPassword(), user.getAge());
        return "redirect:/users";
    }


    //    删除然后重定向到users。
    @RequestMapping(value = "/{id:\\d+}/del", method = RequestMethod.GET)
    public String delUser(@PathVariable Integer id) {
        return "redirect:/users";
    }


    @RequestMapping(value = "/check.json",
            method = RequestMethod.GET,
            produces = "text/html;charset=UTF-8"
    )
    @ResponseBody//代表返回的就是响应的内容了，不是view了。（会直接在界面上显示）
    public String showUserJson(@PathVariable Integer id) {
        return "showUserJson测试";
    }


    @RequestMapping(value = "/{id:\\d+}.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public User showUser() {

        User user = new User();
        user.setUsername("测试");
        user.setPassword("测2");
        user.setAge(12);
        return user;
    }


    //    ----------------------native Servlet
    @RequestMapping("/native")
    public String nativeHttpServlet(HttpServletRequest request,
                                    HttpServletResponse response,
                                    HttpSession session
    ) {
//    ServletContext context=session.getServletContext();

        session.setAttribute("msg", "jim");
        return "home";
    }


    //    ----------------------fileUpload
    @RequestMapping(value = "/avatar/upload", method = RequestMethod.GET)
    public String uploadAvatar() {

        return "users/upload";
    }

    @RequestMapping(value = "/avatar/upload", method = RequestMethod.POST)
    public String uploadAvatar(String picName, MultipartFile file){

        logger.info("```````````````````````````");
        logger.info("测试{}", picName);
        logger.info("测试{}", file.getContentType());
        logger.info("测试{}", file.getName());
        logger.info("测试{}", file.getSize());

//        注意：判断是否选择了文件用isEmpty，而不是用null
        logger.info("测试isEmpty：{}", file.isEmpty());
        logger.info("```````````````````````````");

        try {
//            文件保存位置
            IOUtils.copy(file.getInputStream(),new FileOutputStream("D:/test/"+file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/users";
    }


}
