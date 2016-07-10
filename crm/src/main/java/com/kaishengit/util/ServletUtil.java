package com.kaishengit.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/7/10.
 */
public class ServletUtil {
    /**
     * 获取客户端IP
     * @param request
     * @return
     */
    public static String  getRemoteIp(HttpServletRequest request){
        String ip=request.getRemoteAddr();
        if("0:0:0:0:0:0:0:1".equals(ip)){
            ip="127.0.0.1";
        }
        return ip;
    }
}
