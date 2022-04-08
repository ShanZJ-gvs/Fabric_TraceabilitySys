package com.gvssimux.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    //return true;  执行下一个拦截器，放行
    //return false;  不执行执行下一个拦截器

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("=========处理前=========");
        HttpSession session = request.getSession();
        //放行：判断什么情况下登录
        if (request.getRequestURI().contains("checkUserPwd")){
            System.out.println("Loginintercepter===>url contian checkUserPwd");

            if(session.getAttribute("uuid")==null){
                System.out.println("LoginInterceptor===>session true");
                return true;
            }
            return true;
        }

        if (request.getRequestURI().contains("register")){
            System.out.println("Loginintercepter===>url contian register");
            return true;
        }

        if (request.getRequestURI().contains("setuser")){
            System.out.println("Loginintercepter===>url contian setuser");
            return true;
        }


        //有session时放行
        if(session.getAttribute("uuid")!=null){
            System.out.println("LoginInterceptor===>session true");
            return true;
        }
//        request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request,response);
        System.out.println("无验证信息");

        return false;
    }


    //无返回值 可以用于日志
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("=========处理后=========");




    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("=========清 理=========");
    }
}