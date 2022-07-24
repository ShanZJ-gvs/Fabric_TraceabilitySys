package com.gvssimux.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gvssimux.pojo.fabquery.QueryResultList;
import com.gvssimux.service.*;

import com.gvssimux.util.FabricUtil;
import com.gvssimux.util.JsonUtil;
import lombok.extern.java.Log;
import org.apache.ibatis.annotations.Param;
import org.hyperledger.fabric.client.Contract;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@Log
public class DataOverview {



    @ResponseBody
    @GetMapping("/kindsum")
    public String  kindsum(@Param("companyName") String companyName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaTreeServiceImpl mapper = context.getBean("TeaTreeServiceImpl", TeaTreeServiceImpl.class);

        Contract contract = FabricUtil.getContract();
        companyName = request.getParameter("companyName");

        System.out.println("--------------------------------------"+request.getParameter("companyName")+"--------------------------------------");
        System.out.println();


        String json = "{\"瓜片1\":2,\"猴魁\":2,\"瓜片\":2}";
        HashMap<String,Integer> hashMap = mapper.getTreeSumByCompanyToKind(contract, companyName);


        System.out.println();
        System.out.println("hashMap====>"+hashMap);

        hashMap.put("茶区数量",areasum(contract,companyName));
        hashMap.put("茶园数量",gardensum(contract,companyName));
        hashMap.put("茶树数量",treesum(contract,companyName));

        System.out.println("\n hashMap to jsonStr====》 "+JsonUtil.getJson(hashMap));

        System.out.println("\n 前端拿到的json字符串====》 "+JsonUtil.getJson(hashMap));

        return JsonUtil.getJson(hashMap);
    }


    @ResponseBody
    @GetMapping("/kinds")
    // 获取有哪些种类
    public String  kinds(@Param("companyName") String companyName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaKindServiceImpl mapper = context.getBean("TeaKindServiceImpl", TeaKindServiceImpl.class);

        Contract contract = FabricUtil.getContract();
        companyName = request.getParameter("companyName");

        List list1 = mapper.getKindByCompany(contract, companyName);
        System.out.println();
        System.out.println("公司有哪些茶种 List====>"+list1);
        System.out.println();
        System.out.println("前端哪到的数据====>"+ JsonUtil.getJson(list1));

        return JsonUtil.getJson(list1);
    }




    /* 查询每月的采摘量*/
    @ResponseBody
    @GetMapping("/pickper")
    public String pickper(@Param("companyName") String companyName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaPickServiceImpl mapper = context.getBean("TeaPickServiceImpl", TeaPickServiceImpl.class);
        Contract contract = FabricUtil.getContract();

        HashMap map = mapper.getPickPerSumByCompany(contract, companyName);


        System.out.println("每月的采摘量====》"+map);
        System.out.println("前端接收的数据====》"+JsonUtil.getJson(map));

        return JsonUtil.getJson(map);
    }

    /* 查询每月的制茶量*/
    @ResponseBody
    @GetMapping("/makeper")
    public String makeper(@Param("companyName") String companyName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaMakeServiceImpl mapper = context.getBean("TeaMakeServiceImpl", TeaMakeServiceImpl.class);
        Contract contract = FabricUtil.getContract();

        HashMap map = mapper.getMakePerSumByCompany(contract, companyName);


        System.out.println("每月的制茶量====》"+map);
        System.out.println("前端接收的数据====》"+ JsonUtil.getJson(map));

        return JsonUtil.getJson(map);
    }


    /* 查询当前公司各级茶叶的量*/
    @ResponseBody
    @GetMapping("/rankper")
    public String rankper(@Param("companyName") String companyName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaRankServiceImpl mapper = context.getBean("TeaRankServiceImpl", TeaRankServiceImpl.class);
        Contract contract = FabricUtil.getContract();

        HashMap map = mapper.getRankPerSum(contract, companyName);


        System.out.println("各级茶叶的量====》"+map);
        System.out.println("各级茶叶的量--前端接收的数据====》"+ JsonUtil.getJson(map));

        return JsonUtil.getJson(map);
    }


    /*公司茶叶合格率--指质检通过率*/
    @ResponseBody
    @GetMapping("/testper")
    public String testper(@Param("companyName") String companyName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaRankServiceImpl mapper = context.getBean("TeaRankServiceImpl", TeaRankServiceImpl.class);
        Contract contract = FabricUtil.getContract();

        HashMap map = mapper.getRankPerSum(contract, companyName);


        System.out.println("各级茶叶的量====》"+map);
        System.out.println("各级茶叶的量--前端接收的数据====》"+ JsonUtil.getJson(map));

        return JsonUtil.getJson(map);
    }








    /*        被上面调用的方法                      */

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

    /*            被上面调用的方法                  */





}
