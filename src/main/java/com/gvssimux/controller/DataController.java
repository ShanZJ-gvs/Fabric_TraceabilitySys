package com.gvssimux.controller;

import com.gvssimux.pojo.*;
import com.gvssimux.util.FabricUtil;
import lombok.extern.java.Log;
import org.apache.ibatis.annotations.Param;
import org.hyperledger.fabric.gateway.Contract;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
