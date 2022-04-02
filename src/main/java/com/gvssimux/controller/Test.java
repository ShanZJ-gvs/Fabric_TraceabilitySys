package com.gvssimux.controller;

import com.alibaba.fastjson.JSON;
import com.gvssimux.pojo.*;
import com.gvssimux.pojo.fabquery.TeaAreaQueryResultList;
import com.gvssimux.util.FabricUtil;
import com.gvssimux.util.JsonUtil;
import lombok.extern.java.Log;
import org.apache.ibatis.annotations.Param;
import org.hyperledger.fabric.gateway.Contract;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Test {

    /**
     * 用户溯源码查询
     * 2022-3-24 21:59:53
     * */
    @ResponseBody
    @GetMapping("/t1")
    public String userCode(@RequestParam("userCode") String key, HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSON json;
        AllPojo allPojo = new AllPojo();
        TeaPack pack;
        Contract contract = FabricUtil.getContract();

        json = FabricUtil.queryById("teaAreaId1","a676", "TeaArea",0);
        TeaArea area = json.toJavaObject(TeaArea.class);
        allPojo.setTeaArea(area);

        return JsonUtil.getJson(allPojo);
    }

    /**
     * 用户溯源码查询
     * 2022-3-24 21:59:53
     * */
    @ResponseBody
    @GetMapping("/t2")
    public String userCode1(@RequestParam("userCode") String key, HttpServletRequest request, HttpServletResponse response) throws Exception {
        TeaAreaQueryResultList list;
        AllPojo allPojo = new AllPojo();
        TeaPack pack;
        Contract contract = FabricUtil.getContract();



        list = FabricUtil.queryByIdList("teaAreaTree","a676", "TeaArea");


        return JsonUtil.getJson(list);

    }

    /**
     * 用户溯源码查询
     * 2022-3-24 21:59:53
     * */
    @ResponseBody
    @GetMapping("/t3")
    public String userCod1(@Param("userCode") String key, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String s ="{\"teaArea\":{\"teaAreaId1\":\"a676\",\"teaAreaAddress\":\"中国一号茶区\",\"teaAreaLongitude\":\"未知\",\"teaAreaArea\":\"30000公顷\",\"teaAreaDay\":null,\"teaAreaId2\":\"最高级\",\"teaGardenId2\":\"最高级\",\"teaAreaName\":\"中华园区\",\"teaKinds\":null,\"teaGardenIds\":null,\"teaAreaSonids\":null},\"teaGarden\":{\"teaGardenId1\":\"gt23\",\"teaGardenName\":\"山海一家亲\",\"teaGardenAddress\":\"安徽黄山市\",\"teaGardenArea\":\"2000公顷\",\"teaGardenLongitude\":\"未知\",\"teaAreaId2\":\"a676\",\"teaGardenId2\":\"无\",\"teaGardenAreaid\":null,\"teaGardenTreekind\":null,\"teaGardenTeaid\":null},\"teaTree\":{\"teaTreeId\":\"t355\",\"teaTreeAddress\":\"安徽黄山市\",\"teaTreeLongitude\":\"未知\",\"teaTreeHeight\":300.0,\"teaTreeKind\":\"毛峰\",\"teaTreeState\":\"状态良好\",\"teaTreeCultivate\":\"合格\",\"teaTreeGrowingEnv\":\"湿润\",\"teaAreaId2\":\"a676\",\"teaGardenId2\":\"gt23\"},\"teaPick\":{\"teaPickPer\":{\"eid\":\"802b588237164a2c8ea70e549889bd50\",\"ename\":\"张三疯\",\"esex\":\"男\"},\"teaPickTime\":\"2022年3月23日20:30:20\",\"teaPickId\":\"389d\",\"teaTreeId2\":\"t355\",\"teaPickQuality\":\"合格\"},\"teaMake\":{\"teaMakePer\":{\"eid\":\"abbca5e86db246cbb3f90ca9be64f5ae\",\"ename\":\"小龙女\",\"esex\":\"女\"},\"teaMakeTime\":\"2022年3月23日20:38:36\",\"teaMakeWay\":\"炒制\",\"teaMakeId\":\"389d\"},\"teaRank\":{\"teaRankPer\":{\"eid\":\"6386b24032bd4e0db08fb2e4231f389d\",\"ename\":\"单子健\",\"esex\":\"男\"},\"teaRankTime\":\"2022年3月23日20:43:50\",\"teaRankId\":\"389d\",\"teaRankRank\":\"合格\"},\"teaPack\":{\"teaPackPer\":{\"eid\":\"cd09adee145f4ac995d349127e623efb\",\"ename\":\"杨过\",\"esex\":\"男\"},\"teaPackTime\":\"2022年3月23日20:48:46\",\"teaPackSmllBoxId\":\"2996d90caac749a3b61067b4e80b2e0a\",\"teaPackBigBoxId\":\"24c0e81c573147589634ae12faa32236\",\"teaPackID\":\"389d\"},\"teaTesting\":{\"teaTestingPer\":{\"eid\":\"9cd934845a8a4d24800e381271e3ab3c\",\"ename\":\"老光\",\"esex\":\"男\"},\"teaTestingId\":\"dfd1e3b68ecf42a4b9c24e83f9591738\",\"teaTestingResult\":\"通过\",\"teaTestingTime\":\"2022年3月23日20:54:35\",\"teaTestingSmllBoxId\":\"2996d90caac749a3b61067b4e80b2e0a\",\"teaTestingBigBoxId\":\"24c0e81c573147589634ae12faa32236\"}}";

        key = request.getParameter("userCode");
        System.out.println("key===> "+key);
        return s;
    }

}
