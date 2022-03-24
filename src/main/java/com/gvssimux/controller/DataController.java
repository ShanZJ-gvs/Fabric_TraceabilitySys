package com.gvssimux.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.gvssimux.fabric.ClientApp;
import com.gvssimux.pojo.*;
import com.gvssimux.service.TeaGardenServiceImpl;
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
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@Log
public class DataController {

    /**
     * 用户溯源码查询
     * 2022-3-24 21:59:53
     * */
    @ResponseBody
    @GetMapping("/userCode/key")
    public String userCode(@RequestParam("key") String key,HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("===>请求:userCode:用户溯源码===>");
        String  CCP = "/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com" +
                "/connection-org1.yaml";
        Path walletPath = Paths.get("/usr/software/Fabric_TraceabilitySys/wallet");

        Contract contract = FabricUtil.createContract(walletPath,CCP,"mychannel","teaArea-java-demo");


        // 将查询出来的json，转化为对象，提取teaPackID
        //TeaPack teaPack = JSON.toJavaObject(JSONObject.parseObject(new String(contract.submitTransaction("getTeaPack",key))),TeaPack.class);

        byte[] result = contract.evaluateTransaction("getTeaPack", key);

        return new String(result);
       /* // 将查询出来的json，转化为对象，提取teaPackID
        TeaRank teaRank = JSON.toJavaObject(JSONObject.parseObject(new String(contract.submitTransaction("getTeaRank",teaPack.getTeaPackID()))),TeaRank.class);
        TeaPick teaPick = JSON.toJavaObject(JSONObject.parseObject(new String(contract.submitTransaction("getTeaPick",teaPack.getTeaPackID()))),TeaPick.class);
        TeaMake teaMake = JSON.toJavaObject(JSONObject.parseObject(new String(contract.submitTransaction("getTeaMake",teaPack.getTeaPackID()))), TeaMake.class);

        TeaTree teaTree = JSON.toJavaObject(JSONObject.parseObject(new String(contract.submitTransaction("getTeaTree",teaPick.getTeaTreeId2()))), TeaTree.class);
        TeaGarden teaGarden = JSON.toJavaObject(JSONObject.parseObject(new String(contract.submitTransaction("getTeaTree",teaPick.getTeaTreeId2()))), TeaGarden.class);
       */
     /*   return JsonUtil.getJson(teaPack);*/
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
