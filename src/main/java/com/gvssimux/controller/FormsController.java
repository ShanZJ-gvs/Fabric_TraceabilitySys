package com.gvssimux.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gvssimux.pojo.TeaArea;
import com.gvssimux.pojo.TeaGarden;
import com.gvssimux.service.TeaAreaServiceImpl;
import com.gvssimux.service.TeaGardenServiceImpl;
import com.gvssimux.util.JsonUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class FormsController {


    /**
     * form-wizards.html 页面的控制逻辑
     * 给 “茶区” 添加数据
     */
    @ResponseBody
    @RequestMapping("/setteaarea")
    public int setteaarea(HttpServletRequest request, HttpServletResponse response)throws JsonProcessingException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaAreaServiceImpl mapper = context.getBean("TeaAreaServiceImpl", TeaAreaServiceImpl.class);

        // 创建json工具
        JsonUtil jsonUtil = new JsonUtil();

        // 创建接收对象
        TeaArea teaArea = new TeaArea();

        teaArea.setTeaAreaId(request.getParameter("sum"));

        // mysql 部分插入
        int i = mapper.insertSelective(teaArea);


        return i;
    }




    /**
     * form-wizards.html 页面的控制逻辑
     * 给 “茶园” 添加数据
     */
    @ResponseBody
    @RequestMapping("/formssetgarden")
    public int toFormWizards(HttpServletRequest request, HttpServletResponse response)throws JsonProcessingException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaGardenServiceImpl mapper = context.getBean("TeaGardenServiceImpl", TeaGardenServiceImpl.class);

        // 创建json工具
        JsonUtil jsonUtil = new JsonUtil();

        // 创建接收对象
        TeaGarden teaGarden = new TeaGarden("3b9cbf4c3699462a9e8c9151b6fddf6d","安徽合肥","13000公顷","132-332");

        int i = mapper.insertSelective(teaGarden);


        return i;
    }


    /**
     * 对增加TeaGarden的数据进行测试
     * */
    @Test
    public void testSetTeaGarden(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaGardenServiceImpl mapper = context.getBean("TeaGardenServiceImpl", TeaGardenServiceImpl.class);

        // 创建json工具
        JsonUtil jsonUtil = new JsonUtil();

        // 创建接收对象
        TeaGarden teaGarden = new TeaGarden("3b9cbf4c3699462a9e8c9151b6fddf6d","安徽合肥","13000公顷","132-332");

        int i = mapper.insertSelective(teaGarden);

        System.out.println(i);
    }




}
