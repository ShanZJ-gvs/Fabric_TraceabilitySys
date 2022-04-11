package com.gvssimux.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import java.util.HashMap;
import java.util.List;

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
