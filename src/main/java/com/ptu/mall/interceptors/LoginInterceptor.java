package com.ptu.mall.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 拦截未登录用户，跳转页面到登录页
        System.out.println("判断是否登录");
        HttpSession session = request.getSession();
        if (session.getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/register.html");
            return false;
        }
        System.out.println("已经登录" + session.getAttribute("userId"));
        return true;
    }
}
