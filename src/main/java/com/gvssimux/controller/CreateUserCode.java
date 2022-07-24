package com.gvssimux.controller;

import com.alibaba.fastjson.JSONObject;
import com.gvssimux.pojo.*;
import com.gvssimux.util.FabricUtil;
import com.gvssimux.util.JsonUtil;
import org.hyperledger.fabric.client.Contract;


public class CreateUserCode {
/*

    public static String CreateUserCode(String key,String value) throws Exception {
        Contract contract = FabricUtil.getContract();
        byte[] bytes = contract.submitTransaction("createData", key,value);
        String s = new String(bytes);
        return s;
    }

    public static String createUserCodeByKey(String key) throws Exception {
        TeaPack pack;
        AllPojo allPojo = new AllPojo();

        String json = FabricUtil.fz1("teaPackBigBoxId", key,  0);

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
            */
/*TeaTree tree = new TeaTree();
            tree.setTeaGardenId2("gt23");
            tree.setTeaAreaId2("a676");
            tree.setTeaTreeCultivate("wadad");
            tree.setTeaTreeState("好！！");
            tree.setTeaTreeKind("毛峰");
            tree.setTeaTreeLongitude("wafwafa");
            tree.setTeaTreeGrowingEnv("潮湿");*//*

        allPojo.setTeaTree(tree);
        System.out.println("成功获取tree");



        json = FabricUtil.fz1("teaGardenId1", "gt23",0);
        jsonObj = JSONObject.parseObject(json);//转JSONObject对象
        TeaGarden garden = jsonObj.toJavaObject(TeaGarden.class);
        allPojo.setTeaGarden(garden);
        System.out.println("成功获取garden");

        json = FabricUtil.fz1("teaAreaId1","a676",0);
        jsonObj = JSONObject.parseObject(json);//转JSONObject对象
        TeaArea area = jsonObj.toJavaObject(TeaArea.class);
        allPojo.setTeaArea(area);
        System.out.println("成功获取area");

        String s = CreateUserCode.CreateUserCode(key, JsonUtil.getJson(allPojo));
        System.out.println(s);
        return s;
    }
*/


}
