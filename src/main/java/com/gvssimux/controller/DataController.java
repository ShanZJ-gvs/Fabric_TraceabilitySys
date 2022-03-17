package com.gvssimux.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.gvssimux.fabric.ClientApp;
import com.gvssimux.pojo.TeaArea;
import com.gvssimux.pojo.TeaGarden;
import com.gvssimux.service.TeaGardenServiceImpl;
import com.gvssimux.util.JsonUtil;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.java.Log;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Log
public class DataController {



    /**
     * 数据总览data
     * 测试 茶区TeaArea
     */
    @ResponseBody
    @GetMapping("/teaarea")
    public String  teaarea(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("controller开始执行");
        // 创建接收对象
        String s = new ClientApp().get("getTeaArea", "茶区");
        log.info("controller执行完毕");
        return s;
    }




    /**
     * 数据总览data
     * 测试 菜园TeaGarden
     */
    @ResponseBody
    @GetMapping("/teagarden")
    public String  teagarden(HttpServletRequest request, HttpServletResponse response)throws JsonProcessingException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaGardenServiceImpl mapper = context.getBean("TeaGardenServiceImpl", TeaGardenServiceImpl.class);

        // 创建 返回对象
        //TeaGarden teaGarden = new TeaGarden("3b9cbf4c3699462a9e8c9151b6fddf6d","安徽合肥","13000公顷","132-332");
        TeaGarden teaGarden = new TeaGarden();

        return JsonUtil.getJson(teaGarden);
    }


}
