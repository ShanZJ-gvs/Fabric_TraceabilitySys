package com.gvssimux.controller;

import com.alibaba.fastjson.JSON;
import com.gvssimux.pojo.fabquery.QueryResultList;
import com.gvssimux.service.TeaAreaServiceImpl;
import com.gvssimux.service.TeaGardenServiceImpl;
import com.gvssimux.service.TeaKindServiceImpl;

import com.gvssimux.service.TeaTreeServiceImpl;
import com.gvssimux.util.FabricUtil;
import com.gvssimux.util.JsonUtil;
import lombok.extern.java.Log;
import org.apache.ibatis.annotations.Param;
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
    public String  kindsum(@Param("companyName") String companyName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaKindServiceImpl mapper = context.getBean("TeaKindServiceImpl", TeaKindServiceImpl.class);

        Contract contract = FabricUtil.getContract();
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        companyName = request.getParameter("companyName");

        System.out.println("--------------------------------------"+request.getParameter("companyName")+"--------------------------------------");

        list1.add(mapper.getSum(contract));
        list1.add(areasum(contract,companyName));// 按公司名查茶区数
        list1.add(gardensum(contract,companyName));// 按公司名查茶园数
        list1.add(treesum(contract,companyName));// 按公司名查茶树数
        System.out.println("list11111111  "+list1);
        return JsonUtil.getJson(list1);
    }



    public static int areasum(Contract contract,String companyName) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaAreaServiceImpl mapper = context.getBean("TeaAreaServiceImpl", TeaAreaServiceImpl.class);
        return mapper.getAreaSumByCompany(contract,companyName);
    }


    public static int gardensum(Contract contract,String companyName) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaGardenServiceImpl mapper = context.getBean("TeaGardenServiceImpl", TeaGardenServiceImpl.class);
        return mapper.getGardenSumByCompany(contract,companyName);
    }

    public static int treesum(Contract contract,String companyName) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaTreeServiceImpl mapper = context.getBean("TeaTreeServiceImpl", TeaTreeServiceImpl.class);
        return mapper.getTreeSumByCompany(contract,companyName);
    }





}
