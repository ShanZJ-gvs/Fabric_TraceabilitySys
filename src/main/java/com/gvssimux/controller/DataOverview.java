package com.gvssimux.controller;

import com.alibaba.fastjson.JSON;
import com.gvssimux.pojo.fabquery.QueryResultList;
import com.gvssimux.service.TeaAreaServiceImpl;
import com.gvssimux.service.TeaGardenServiceImpl;
import com.gvssimux.service.TeaKindServiceImpl;

import com.gvssimux.util.FabricUtil;
import com.gvssimux.util.JsonUtil;
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

import java.util.ArrayList;

@Controller
@Log
public class DataOverview {


    @ResponseBody
    @GetMapping("/areas")
    public String  teaarea(@RequestParam("limit") int limit, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaAreaServiceImpl mapper = context.getBean("TeaAreaServiceImpl", TeaAreaServiceImpl.class);
        Contract contract = FabricUtil.getContract();
        QueryResultList resultList = mapper.selectOffsetLimit(contract,0,10);

        return JSON.toJSONString(resultList);
    }


    @ResponseBody
    @GetMapping("/kindsum")
    public String  kindsum(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaKindServiceImpl mapper = context.getBean("TeaKindServiceImpl", TeaKindServiceImpl.class);

        Contract contract = FabricUtil.getContract();
        ArrayList<Integer> list1 = new ArrayList<Integer>();

        list1.add(mapper.getSum(contract));
        list1.add(areasum(contract));
        list1.add(gardensum(contract));
        return JsonUtil.getJson(list1);
    }



    public static int areasum(Contract contract) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaAreaServiceImpl mapper = context.getBean("TeaAreaServiceImpl", TeaAreaServiceImpl.class);
        return mapper.getSum(contract);
    }


    public static int gardensum(Contract contract) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaGardenServiceImpl mapper = context.getBean("TeaGardenServiceImpl", TeaGardenServiceImpl.class);
        return mapper.getSum(contract);
    }





}
