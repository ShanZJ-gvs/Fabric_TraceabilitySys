package com.gvssimux.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.gvssimux.fabric.ClientApp;
import com.gvssimux.pojo.*;
import com.gvssimux.pojo.fabquery.TeaAreaQueryResult;
import com.gvssimux.pojo.fabquery.TeaAreaQueryResultList;
import com.gvssimux.service.TeaGardenServiceImpl;
import com.gvssimux.util.FabricUtil;
import com.gvssimux.util.JsonUtil;
import lombok.extern.java.Log;
import org.apache.ibatis.annotations.Param;
import org.bouncycastle.util.Pack;
import org.hyperledger.fabric.gateway.Contract;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.reflect.generics.tree.Tree;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
        String json;
        AllPojo allPojo = new AllPojo();
        TeaPack pack;


        key = request.getParameter("userCode");
        log.info("key===> "+key);
        Contract contract = FabricUtil.getContract();

        /*逻辑判断*/
        if (key==null){
            return "UserCode为空";
        }

        json = FabricUtil.fz1("teaPackBigBoxId", key,  0);

        JSONObject jsonObj = JSONObject.parseObject(json);//转JSONObject对象
        pack = jsonObj.toJavaObject(TeaPack.class);// 拿到TeaPack实体
        allPojo.setTeaPack(pack);
        System.out.println("成功获取pack");


        // 再次提交查询
        json = FabricUtil.fz1("teaPickId", pack.getTeaPackID(),  0);

        jsonObj = JSONObject.parseObject(json);//转JSONObject对象
        TeaPick pick = jsonObj.toJavaObject(TeaPick.class);// 拿到TeaPick实体
        allPojo.setTeaPick(pick);
        System.out.println("成功获取pick");

        // 再次提交查询
        json = FabricUtil.fz1("teaRankId", pack.getTeaPackID(),  0);

        jsonObj = JSONObject.parseObject(json);//转JSONObject对象
        TeaRank rank = jsonObj.toJavaObject(TeaRank.class);// 拿到TeaRank实体
        allPojo.setTeaRank(rank);
        System.out.println("成功获取rank");

        // 再次提交查询
        json = FabricUtil.fz1("teaMakeId", pack.getTeaPackID(), 0);
        jsonObj = JSONObject.parseObject(json);//转JSONObject对象
        TeaMake make = jsonObj.toJavaObject(TeaMake.class);
        allPojo.setTeaMake(make);
        System.out.println("成功获取make");

        // 再次提交查询
        json = FabricUtil.fz1("teaTestingBigBoxId", key, 0);
        jsonObj = JSONObject.parseObject(json);//转JSONObject对象
        TeaTesting testing = jsonObj.toJavaObject(TeaTesting.class);
        allPojo.setTeaTesting(testing);
        System.out.println("成功获取testing");


        json = FabricUtil.fz1("teaTreeId","t356",0);
        jsonObj = JSONObject.parseObject(json);//转JSONObject对象
        TeaTree tree = jsonObj.toJavaObject(TeaTree.class);
        /*TeaTree tree = new TeaTree();
        tree.setTeaGardenId2("gt23");
        tree.setTeaAreaId2("a676");
        tree.setTeaTreeCultivate("wadad");
        tree.setTeaTreeState("好！！");
        tree.setTeaTreeKind("毛峰");
        tree.setTeaTreeLongitude("wafwafa");
        tree.setTeaTreeGrowingEnv("潮湿");*/
        allPojo.setTeaTree(tree);



        json = FabricUtil.fz1("teaGardenId1", "gt23",0);
        jsonObj = JSONObject.parseObject(json);//转JSONObject对象
        TeaGarden garden = jsonObj.toJavaObject(TeaGarden.class);
        allPojo.setTeaGarden(garden);

        json = FabricUtil.fz1("teaAreaId1","a676",0);
        jsonObj = JSONObject.parseObject(json);//转JSONObject对象
        TeaArea area = jsonObj.toJavaObject(TeaArea.class);
        allPojo.setTeaArea(area);

        String s = CreateUserCode.CreateUserCode(key, JsonUtil.getJson(allPojo));
        System.out.println(s);
        return JsonUtil.getJson(allPojo);
    }





  /*  *//**
     * 数据总览data
     * 测试 茶区TeaArea
     *//*
    @ResponseBody
    @GetMapping("/teaareas")
    public String  teaarea(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("===>请求:teaareas:查询所有茶区===>");
        String  CCP = "/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com" +
                "/connection-org1.yaml";
        Path walletPath = Paths.get("/usr/software/Fabric_TraceabilitySys/wallet");
        Contract contract = FabricUtil.createContract(walletPath,CCP,"mychannel","teaArea-java-demo");
        byte[] result = contract.evaluateTransaction("queryAllTeaArea");
        log.info("===>请求:teaareas:查询完毕===>");
        return new String(result);
    }
*/



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
