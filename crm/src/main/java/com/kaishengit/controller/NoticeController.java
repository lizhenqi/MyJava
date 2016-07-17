package com.kaishengit.controller;


import com.google.common.collect.Maps;
import com.kaishengit.dto.DataTablesResult;
import com.kaishengit.pojo.Notice;
import com.kaishengit.service.NoticeService;
import com.kaishengit.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2016/7/12.
 */
@Controller
public class NoticeController {

    @Inject
    private NoticeService noticeService;


    /**
     * 查看公告列表
     *
     * @return
     */
    @RequestMapping(value = "/notice", method = RequestMethod.GET)
    public String noticeList() {
        return "notice/list";
    }

    @RequestMapping(value = "/notice/list/show", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult noticeListShow(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        String keyword = request.getParameter("search[value]");

        keyword = Strings.toUTF8(keyword);

        Map<String, Object> param = Maps.newHashMap();
        param.put("start", start);
        param.put("length", length);
        param.put("keyword", keyword);

        List<Notice> noticeList = noticeService.findNoticeByParam(param);
        Long count = noticeService.findAllCount();
        Long filterCount = noticeService.findFilterCount(param);

        return new DataTablesResult<>(draw, filterCount, noticeList, count);
    }


    /**
     * 发布公告
     *
     * @return
     */
    @RequestMapping(value = "/notice/new", method = RequestMethod.GET)
    public String newNotice() {

        return "notice/new";
    }

    @RequestMapping(value = "/notice/new", method = RequestMethod.POST)
    public String newNotice(Notice notice, RedirectAttributes redirectAttributes) {

        noticeService.saveNotice(notice);
        redirectAttributes.addFlashAttribute("message", "发布成功！");

        return "redirect:/notice";
    }


    /**
     * 查看公告正文
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/notice/context/{id:\\d+}", method = RequestMethod.GET)
    public String showContext(@PathVariable Integer id, Model model) {
        Notice notice = noticeService.findNoticeById(id);
        model.addAttribute("notice", notice);

        return "/notice/contextView";
    }

    /**
     * 将Simditor在线编辑器的图片进行上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/notice/img/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(MultipartFile file) throws IOException {

            /*
              Simditor编辑器要求要返回一下(成功的返回true和真实地址，失败的返回false和错误信息)：{
               "success": true/false,
                "msg": "error message", # optional
               "file_path": "[real file path]"

             */

        Map<String, Object> result = Maps.newHashMap();
        if (!file.isEmpty()) {

            String path=noticeService.saveImage(file.getInputStream(),file.getOriginalFilename());

            result.put("success", true);
            result.put("file_path",path);
        } else {
            result.put("success", false);
            result.put("msg", "请选择文件");
        }
        return result;
    }

    /**
     * 删除公告
     * @param id
     * @return
     */
    @RequestMapping(value = "/notice/del/{id:\\d+}",method = RequestMethod.GET)
    @ResponseBody
    public String del(@PathVariable Integer id){
        noticeService.del(id);
        return "success";
    }

}
