package com.ztt.mt.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.ztt.mt.utils.BaseContext;
import com.ztt.mt.utils.R;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long id=Thread.currentThread().getId();
        log.info("LoginInterceptor >> 线程Id: {}",id);
        Object empobj= request.getSession().getAttribute("employee");
        if(empobj!=null){
            log.info("用户:{}已登录",request.getSession().getAttribute("employee"));
            Long userId=(long) empobj;
            BaseContext.setCurrentId(userId);
            return true;
        }
        Object userobj= request.getSession().getAttribute("user");
        if(userobj!=null){
            log.info("用户:{}已登录",request.getSession().getAttribute("user"));
            Long userId=(long) userobj;
            BaseContext.setCurrentId(userId);
            return true;
        }
        response.getWriter().write(JSONObject.toJSONString(R.error("NOTLOGIN")));
        return false;
    }
}
