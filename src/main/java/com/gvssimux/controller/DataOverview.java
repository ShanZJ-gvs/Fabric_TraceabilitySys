package com.gvssimux.controller;

import com.alibaba.fastjson.JSON;
import com.gvssimux.pojo.fabquery.TeaAreaQueryResultList;
import com.gvssimux.service.TeaAreaServiceImpl;
import com.gvssimux.service.UserLoginServiceImpl;
import com.gvssimux.util.FabricUtil;
import lombok.extern.java.Log;
import org.hyperledger.fabric.gateway.Contract;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@Log
public class DataOverview {


    @ResponseBody
    @GetMapping("/areas")
    public String  teaarea(@RequestParam("limit") int limit, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaAreaServiceImpl mapper = context.getBean("TeaAreaServiceImpl", TeaAreaServiceImpl.class);
        TeaAreaQueryResultList resultList = mapper.selectOffsetLimit(0,10);


        return JSON.toJSONString(resultList);
    }




}
