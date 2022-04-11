package com.gvssimux.controller;


import com.gvssimux.pojo.UserBasic;
import com.gvssimux.service.UserLoginServiceImpl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController {



    @RequestMapping("/login")
    public String loginUser01(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserLoginServiceImpl mapper = context.getBean("UserLoginServiceImpl", UserLoginServiceImpl.class);

        String username = request.getParameter("loginUser");
        String pwd = request.getParameter("loginPwd");

        UserBasic user = mapper.getUserIdByUserNamePwd(username, pwd);
        System.out.println("执行成功=====》:"+user);

        if (user!=null) {
            //把用户的信息存在session中,用于验证
            session.setAttribute("company",user.getUserUname());
            System.out.println("LoginController===>");
            model.addAttribute("company",user.getUserUname());
            return "home";
        }
        return "UserPwdError";
    }

    @ResponseBody
    @RequestMapping("/checkUserPwd")
    public String loginUser02(HttpSession session, String username, String pwd) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserLoginServiceImpl mapper = context.getBean("UserLoginServiceImpl", UserLoginServiceImpl.class);

        UserBasic user = mapper.getUserIdByUserNamePwd(username, pwd);
        System.out.println("执行成功=====》:"+user);

        if (user!=null) {
            //把用户的信息存在session中,用于验证
            session.setAttribute("uuid",user);
            System.out.println("LoginController===>"+user);
            return "UserPwdOK";
        }
        return "UserPwdError";

    }






}
