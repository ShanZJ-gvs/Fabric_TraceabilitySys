package com.gvssimux.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gvssimux.pojo.*;
import com.gvssimux.pojo.fabquery.QueryResult;
import com.gvssimux.pojo.fabquery.QueryResultList;
import com.gvssimux.service.TeaAreaServiceImpl;
import com.gvssimux.service.TeaGardenServiceImpl;
import com.gvssimux.service.TeaLeafServiceImpl;
import com.gvssimux.service.TeaPickServiceImpl;
import com.gvssimux.util.FabricUtil;
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
import java.util.List;

import static com.gvssimux.service.CreateUserCode.createUserCodeByKey;


@Controller
@Log
public class DataController {


    /**
     * 用户溯源码查询
     * 2022-3-24 21:59:53
     * */
    @ResponseBody
    @GetMapping("/userCode/key")
    public String userCode(@Param("userCode") String key, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("===>请求:userCode:用户溯源码===>");
        TeaPack pack;
        String json;
        key = request.getParameter("userCode");
        if (key.equals("")){
            return "溯源码为空";
        }

        Contract contract = FabricUtil.getContract();


        log.info("key===> "+key);
        try {
            /*直接提交溯源码，看是否次溯源码已被使用*/
            byte[] bytes = contract.submitTransaction("getAllPojo", key);
            String s = new String(bytes);
            System.out.println(s);
            return s;

        } catch(Throwable e) {
            /*溯源码没有被查询过*/
            try {
               return createUserCodeByKey(key);
            }
            catch (Throwable r){
                return "溯源码无效";
            }
        }


    }




    /*数据总览 茶区*/
    @ResponseBody
    @GetMapping("/areas")
    public String  teaarea(@Param("companyName") String companyName,@RequestParam("offset") int offset,@RequestParam("limit")int limit, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaAreaServiceImpl mapper = context.getBean("TeaAreaServiceImpl", TeaAreaServiceImpl.class);

        Contract contract = FabricUtil.getContract();
        companyName = request.getParameter("companyName");
        offset = Integer.parseInt(request.getParameter("offset"));
        limit = Integer.parseInt(request.getParameter("limit"));

        QueryResultList resultList = mapper.selectOffsetLimit(contract,companyName,offset,limit); // 拿到数据

        ArrayList<TeaArea> listarea = new ArrayList<>();

        List<QueryResult> resultList1 = resultList.getResultList(); // 提取数据
        for ( QueryResult a :resultList1) { // 将数据中的json字符串转为实体对象，然后存入数组，给前端
            String jsonData = a.getJson();
            JSONObject jsonObject = JSONObject.parseObject(jsonData);
            TeaArea temparea = JSON.toJavaObject(jsonObject, TeaArea.class);
            listarea.add(temparea);
        }

        return JSON.toJSONString(listarea);
    }


    /*数据总览 茶园*/
    @ResponseBody
    @GetMapping("/gardens")
    public String  gardens(@Param("companyName") String companyName,@RequestParam("offset") int offset,@RequestParam("limit")int limit, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaGardenServiceImpl mapper = context.getBean("TeaGardenServiceImpl", TeaGardenServiceImpl.class);

        Contract contract = FabricUtil.getContract();
        companyName = request.getParameter("companyName");
        offset = Integer.parseInt(request.getParameter("offset"));
        limit = Integer.parseInt(request.getParameter("limit"));

        QueryResultList resultList = mapper.selectOffsetLimit(contract,companyName,offset,limit); // 拿到数据

        ArrayList<TeaGarden> listGarden = new ArrayList<>(); // 记录数据

        List<QueryResult> resultList1 = resultList.getResultList(); // 提取数据
        for ( QueryResult a :resultList1) { // 将数据中的json字符串转为实体对象，然后存入数组，给前端
            String jsonData = a.getJson();
            JSONObject jsonObject = JSONObject.parseObject(jsonData);
            TeaGarden tempGarden = JSON.toJavaObject(jsonObject, TeaGarden.class);
            listGarden.add(tempGarden);
        }
        return JSON.toJSONString(listGarden);
    }



    /*数据总览 茶叶*/
    @ResponseBody
    @GetMapping("/leafs")
    public String leafs(@Param("companyName") String companyName,@RequestParam("offset") int offset,@RequestParam("limit")int limit, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaPickServiceImpl mapper = context.getBean("TeaPickServiceImpl", TeaPickServiceImpl.class);

        Contract contract = FabricUtil.getContract();
        companyName = request.getParameter("companyName");
        offset = Integer.parseInt(request.getParameter("offset"));
        limit = Integer.parseInt(request.getParameter("limit"));

        QueryResultList resultList = mapper.selectOffsetLimit(contract,companyName,offset,limit); // 拿到数据

        ArrayList<TeaPick> listPick = new ArrayList<>(); // 记录数据

        List<QueryResult> resultList1 = resultList.getResultList(); // 提取数据
        for ( QueryResult a :resultList1) { // 将数据中的json字符串转为实体对象，然后存入数组，给前端
            String jsonData = a.getJson();
            JSONObject jsonObject = JSONObject.parseObject(jsonData);
            TeaPick tempPick = JSON.toJavaObject(jsonObject, TeaPick.class);
            listPick.add(tempPick);
        }
        return JSON.toJSONString(listPick);
    }


    /*数据总览 茶叶*/
    @ResponseBody
    @GetMapping("/leafs2")
    public String leafs2(@Param("companyName") String companyName,@RequestParam("offset") int offset,@RequestParam("limit")int limit, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaLeafServiceImpl mapper = context.getBean("TeaLeafServiceImpl", TeaLeafServiceImpl.class);

        Contract contract = FabricUtil.getContract();
        companyName = request.getParameter("companyName");
        offset = Integer.parseInt(request.getParameter("offset"));
        limit = Integer.parseInt(request.getParameter("limit"));

        String s = mapper.getLeft(contract,companyName,offset,limit); // 拿到数据

        return s;
    }





    /*  *//**
     * 数据总览data
     * 测试 菜园TeaGarden
     *//*
    @ResponseBody
    @GetMapping("/teagarden")
    public String  teagarden(HttpServletRequest request, HttpServletResponse response)throws JsonProcessingException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaGardenServiceImpl mapper = context.getBean("TeaGardenServiceImpl", TeaGardenServiceImpl.class);

        // 创建 返回对象
        //TeaGarden teaGarden = new TeaGarden("3b9cbf4c3699462a9e8c9151b6fddf6d","安徽合肥","13000公顷","132-332");
        TeaGarden teaGarden = new TeaGarden();

        return JsonUtil.getJson(teaGarden);
    }*/












}
