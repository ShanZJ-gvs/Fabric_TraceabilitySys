package com.gvssimux.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.gvssimux.fabric.RegisterUser;
import com.gvssimux.fabric.gateway;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageJumpController {






    /**
     * 跳转到home页面
     */
    @RequestMapping("/tohome")
    public String tohome() throws Exception {
        return "home";
    }



    /**
     * 跳转到home页面
     */
    @RequestMapping("/toregister")
    public String toregister()throws JsonProcessingException{

        return "home";
    }

    /**
     * 跳转到people.html页面
     */
    @RequestMapping("/topeople")
    public String topeople()throws JsonProcessingException{

        return "people";
    }

    /**
     * 跳转到datas.html页面
     */
    @RequestMapping("/todata")
    public String toData()throws JsonProcessingException{

        return "datas";
    }

    /**
     * 跳转到search.html页面
     */
    @RequestMapping("/tosearch")
    public String toSearch()throws JsonProcessingException{

        return "search";
    }

    /**
     * 跳转到form-wizard.html页面
     */
    @RequestMapping("/toform")
    public String toFormWizard()throws JsonProcessingException{

        return "form-wizard";
    }

    /**
     * 跳转到form-wizards.html页面
     */
    @RequestMapping("/toforms")
    public String toFormWizards()throws JsonProcessingException{

        return "form-wizards";
    }

    /**
     * 跳转到auth-login.html页面
     */
    @RequestMapping("/toauthlogin")
    public String toAuthLogin()throws JsonProcessingException{

        return "auth-login";
    }

    /**
     * 跳转到auth-forgot-password.html页面
     */
    @RequestMapping("/toauthforgotpwd")
    public String toAuthForgotPWD()throws JsonProcessingException{

        return "auth-forgot-password.html";
    }







}
