package com.gvssimux.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.gvssimux.pojo.*;
import com.gvssimux.service.TeaAreaServiceImpl;
import com.gvssimux.service.TeaGardenServiceImpl;
import com.gvssimux.util.FabricUtil;
import com.gvssimux.util.JsonUtil;
import lombok.extern.java.Log;
import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractException;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.channels.Pipe;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeoutException;


@Log
@Controller
public class FormsController {

    /**
     * form-wizards.html 页面的控制逻辑
     * 给 “茶区” 添加数据
     * 2022年3月16日14:26:26
     */
    @ResponseBody
    @PostMapping("/teaarea")
    public String setteaarea(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaAreaServiceImpl mapper = context.getBean("TeaAreaServiceImpl", TeaAreaServiceImpl.class);

        // 创建接收对象
        TeaArea pojo = new TeaArea();

        // 将request中的数据拿给实体teaArea
        pojo.setTeaAreaId1(request.getParameter("tea_area_id1"));
        pojo.setTeaAreaAddress(request.getParameter("tea_area_address"));
        pojo.setTeaAreaArea(request.getParameter("tea_area_area"));
        pojo.setTeaAreaLongitude(request.getParameter("tea_area_longitude"));
        pojo.setTeaAreaId2(request.getParameter("tea_area_id2"));
        pojo.setTeaGardenId2(request.getParameter("tea_garden_id2"));
        request.getParameter("key");

        /*往区块中加数据*/
        String  CCP = "/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com" +
                "/connection-org1.yaml";
        Path walletPath = Paths.get("/usr/software/Fabric_TraceabilitySys/wallet");

        Contract contract = FabricUtil.createContract(walletPath,CCP,"mychannel","teaArea-java-demo");
        byte[] result;
        log.info("===>开始提交区块链交易===>");
        log.info("key===> "+request.getParameter("key"));

        byte[] bytes = contract.submitTransaction("createOneTeaArea", request.getParameter("key"),
                request.getParameter("tea_area_id1"),
                request.getParameter("tea_area_address"), request.getParameter("tea_area_longitude"),
                request.getParameter("tea_area_area"),request.getParameter("tea_area_id2"),
                request.getParameter("tea_garden_id2"));

        String s = new String(bytes);
        if (bytes!=null){
            log.info("===>交易提交成功===>");
        }
        return s;
    }


    /**
     * form-wizards.html 页面的控制逻辑
     * 给 “茶园” 添加数据
     * 2022年3月17日13:46:56
     */
    @ResponseBody
    @PostMapping("/teagarden")
    public String teagarden(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaGardenServiceImpl mapper = context.getBean("TeaGardenServiceImpl", TeaGardenServiceImpl.class);

        // 创建接收对象
        TeaGarden pojo = new TeaGarden();
        // 将request中的数据拿给实体teaArea
        pojo.setTeaGardenId1(request.getParameter("tea_garden_id1"));
        pojo.setTeaGardenAddress(request.getParameter("tea_garden_address"));
        pojo.setTeaGardenArea(request.getParameter("tea_garden_area"));
        pojo.setTeaGardenLongitude(request.getParameter("tea_garden_longitude"));
        pojo.setTeaAreaId2(request.getParameter("tea_area_id2"));
        pojo.setTeaGardenId2(request.getParameter("tea_garden_id2"));
        request.getParameter("key");


        /*往区块中加数据*/
        String  CCP = "/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com" +
                "/connection-org1.yaml";
        Path walletPath = Paths.get("/usr/software/Fabric_TraceabilitySys/wallet");

        Contract contract = FabricUtil.createContract(walletPath,CCP,"mychannel","teaArea-java-demo");
        log.info("===>开始提交区块链交易===>");

        byte[] bytes = contract.submitTransaction("createOneTeaGarden",request.getParameter("key"),
                request.getParameter("tea_garden_id1"),request.getParameter("tea_garden_address"),
                request.getParameter("tea_garden_area"),request.getParameter("tea_garden_longitude"),
                request.getParameter("tea_area_id2"),request.getParameter("tea_garden_id2"));

        String s = new String(bytes);

        if (bytes!=null){
            log.info("===>交易提交成功===>");
        }

        return s;
    }


    /**
     * form-wizards.html 页面的控制逻辑
     * 给 茶树 添加数据
     * 2022年3月17日13:46:52
     */
    @ResponseBody
    @PostMapping("/teatree")
    public String teatree(HttpServletRequest request,HttpServletResponse response) throws Exception {
        TeaTree pojo = new TeaTree();
        pojo.setTeaTreeId(request.getParameter("tea_tree_id"));
        pojo.setTeaTreeAddress(request.getParameter("tea_tree_address"));
        pojo.setTeaTreeLongitude(request.getParameter("tea_tree_longitude"));
        pojo.setTeaTreeHeight(Double.valueOf(request.getParameter("tea_tree_height")));
        pojo.setTeaTreeKind(request.getParameter("tea_tree_kind"));
        pojo.setTeaTreeState(request.getParameter("tea_tree_state"));
        pojo.setTeaTreeCultivate(request.getParameter("tea_tree_cultivate"));
        pojo.setTeaTreeGrowingEnv(request.getParameter("tea_tree_Growing_Env"));
        pojo.setTeaAreaId2(request.getParameter("tea_area_id2"));
        pojo.setTeaGardenId2(request.getParameter("tea_garden_id2"));

        request.getParameter("key");

        /*往区块中加数据*/
        String  CCP = "/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com" +
                "/connection-org1.yaml";
        Path walletPath = Paths.get("/usr/software/Fabric_TraceabilitySys/wallet");

        Contract contract = FabricUtil.createContract(walletPath,CCP,"mychannel","teaArea-java-demo");
        log.info("===>开始提交区块链交易===>");

        byte[] bytes = contract.submitTransaction("createOneTeaTree",request.getParameter("key"),
                request.getParameter("tea_tree_id"),request.getParameter("tea_tree_address"),request.getParameter("tea_tree_longitude"),
                request.getParameter("tea_tree_height"),request.getParameter("tea_tree_kind"),request.getParameter("tea_tree_state"),
                request.getParameter("tea_tree_cultivate"),request.getParameter("tea_tree_Growing_Env"),
                request.getParameter("tea_area_id2"),request.getParameter("tea_garden_id2"));

        String s = new String(bytes);

        if (bytes!=null){
            log.info("===>交易提交成功===>");
        }

        return s;
    }


    /**
     * 采摘表 添加数据
     * 其中关于采摘师、采摘人姓名、采摘人性别还未定，预定用employee类去代替
     * 2022年3月17日13:53:16
     * */
    @ResponseBody
    @PostMapping("teapick")
    public String teapick(HttpServletRequest request,HttpServletResponse response) throws Exception {

        /**
         * 给采摘实体添加数据
         *
         * 采摘时间是data类型，还未解决问题
         *  pojo.setTeaPickTime(request.getParameter("tea_pick_time"));
         * */
        TeaPick pojo = new TeaPick();
        pojo.setTeaPickTime(request.getParameter("tea_pick_time"));
        pojo.setTeaPickId(request.getParameter("tea_pick_id"));// 用teaPickId充当茶叶批id，因为一批茶叶的是同时采摘的
        pojo.setTeaTreeId2(request.getParameter("tea_tree_id2"));
        pojo.setTeaPickQuality(request.getParameter("tea_pick_quality"));


        /**
         * 给采摘师添加数据
         *
         * */
        Employee employee = new Employee();// 采摘师
        employee.setEid(request.getParameter("tea_pick_per_id"));// 采摘师编号
        employee.setEname(request.getParameter("tea_pick_per_name"));// 采摘师姓名
        employee.setEsex(request.getParameter("tea_pick_per_sex"));// 采摘师性别

        request.getParameter("key");

        /*往区块中加数据*/
        String  CCP = "/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com" +
                "/connection-org1.yaml";
        Path walletPath = Paths.get("/usr/software/Fabric_TraceabilitySys/wallet");

        Contract contract = FabricUtil.createContract(walletPath,CCP,"mychannel","teaArea-java-demo");
        log.info("===>开始提交区块链交易===>");

        byte[] bytes = contract.submitTransaction("createOneTeaPick",request.getParameter("key"),
                request.getParameter("tea_pick_per_id"),request.getParameter("tea_pick_per_name"),request.getParameter("tea_pick_per_sex"),
                request.getParameter("tea_pick_time"),request.getParameter("tea_pick_id"),request.getParameter("tea_tree_id2"),
                request.getParameter("tea_pick_quality"));

        String s = new String(bytes);

        if (bytes!=null){
            log.info("===>交易提交成功===>");
        }

        return s;// 返回字符串
    }


    /**
     * 制茶表添加数据
     * 2022年3月17日14:08:15
     * */
    @ResponseBody
    @PostMapping("/teamake")
    public String teamake(HttpServletRequest request,HttpServletResponse response) throws Exception {
        /**
         * 给制茶师添加数据
         * */
        Employee employee = new Employee();
        employee.setEid(request.getParameter("tea_make_per_id"));
        employee.setEname(request.getParameter("tea_make_per_name"));
        employee.setEsex(request.getParameter("tea_make_per_sex"));

        /**
         * 给制茶实体添加数据
         * */
        TeaMake teaMake = new TeaMake();
        teaMake.setTeaMakePer(employee);// 制茶师
        request.getParameter("tea_make_time");
        teaMake.setTeaMakeWay(request.getParameter("tea_make_way"));// 制茶方式
        teaMake.setTeaMakeId(request.getParameter("tea_make_id"));// 茶叶批编号


        /*往区块中加数据*/
        String  CCP = "/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com" +
                "/connection-org1.yaml";
        Path walletPath = Paths.get("/usr/software/Fabric_TraceabilitySys/wallet");

        Contract contract = FabricUtil.createContract(walletPath,CCP,"mychannel","teaArea-java-demo");
        log.info("===>开始提交区块链交易===>");

        byte[] bytes = contract.submitTransaction("createOneTeaMake",request.getParameter("key"),
                request.getParameter("tea_make_per_id"),request.getParameter("tea_make_per_name"),request.getParameter("tea_make_per_sex"),
                request.getParameter("tea_make_time"),request.getParameter("tea_make_way"),request.getParameter("tea_make_id"));

        String s = new String(bytes);
        if (bytes!=null){
            log.info("===>交易提交成功===>");
        }

        return s;// 返回字符串
    }


    /**
     * 定级表添加数据
     * 2022年3月17日14:54:09
     * */
    @ResponseBody
    @PostMapping("/tearank")
    public String  tearank(HttpServletRequest request,HttpServletResponse response) throws Exception {
        /**
         * 定级人
         * */
        Employee employee = new Employee();
        employee.setEid(request.getParameter("tea_rank_per_id"));
        employee.setEname(request.getParameter("tea_rank_per_name"));
        employee.setEsex(request.getParameter("tea_rank_per_sex"));

        /**
         * 定级实体
         * */
        TeaRank teaRank = new TeaRank();
        teaRank.setTeaRankPer(employee);// 定级人
        request.getParameter("tea_rank_time"); // 定级时间
        teaRank.setTeaRankId(request.getParameter("tea_rank_id"));// 茶叶批编号
        teaRank.setTeaRankRank(request.getParameter("tea_rank_rank"));// 茶叶等级

        request.getParameter("key");// ssl密钥

        /*往区块中加数据*/
        String  CCP = "/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com" +
                "/connection-org1.yaml";
        Path walletPath = Paths.get("/usr/software/Fabric_TraceabilitySys/wallet");

        Contract contract = FabricUtil.createContract(walletPath,CCP,"mychannel","teaArea-java-demo");
        log.info("===>开始提交区块链交易===>");

        byte[] bytes = contract.submitTransaction("createOneTeaRank",request.getParameter("key"),
                request.getParameter("tea_rank_per_id"),request.getParameter("tea_rank_per_name"),request.getParameter("tea_rank_per_sex"),
                request.getParameter("tea_rank_time"),request.getParameter("tea_rank_id"),request.getParameter("tea_rank_rank"));

        String s = new String(bytes);

        if (bytes!=null){
            log.info("===>交易提交成功===>");
        }

        return s;// 返回字符串
    }


    /**
     * 包装表添加数据
     * 2022年3月17日15:07:30
     * */
    @ResponseBody
    @PostMapping("/teapack")
    public String teapack(HttpServletRequest request,HttpServletResponse response) throws Exception {
        /**
         * 包装人
         * */
        Employee employee = new Employee();
        employee.setEid(request.getParameter("tea_pack_per_id"));
        employee.setEname(request.getParameter("tea_pack_per_name"));
        employee.setEsex(request.getParameter("tea_pack_per_sex"));

        /**
         * 包装实体
         * */
        TeaPack teaPack = new TeaPack();
        teaPack.setTeaPackPer(employee);// 包装人
        request.getParameter("tea_pack_time"); // 包装时间
        teaPack.setTeaPackBigBoxId(request.getParameter("tea_pack_big_box_id"));// 小盒编号
        teaPack.setTeaPackSmllBoxId(request.getParameter("tea_pack_smll_box_id"));// 大盒编号
        teaPack.setTeaPackID(request.getParameter("tea_pack_id"));

        request.getParameter("key");// ssl密钥

        /*往区块中加数据*/
        String  CCP = "/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com" +
                "/connection-org1.yaml";
        Path walletPath = Paths.get("/usr/software/Fabric_TraceabilitySys/wallet");

        Contract contract = FabricUtil.createContract(walletPath,CCP,"mychannel","teaArea-java-demo");
        log.info("===>开始提交区块链交易===>");

        byte[] bytes = contract.submitTransaction("createOneTeaPack",request.getParameter("key"),
                request.getParameter("tea_pack_per_id"),request.getParameter("tea_pack_per_name"),request.getParameter("tea_pack_per_sex"),
                request.getParameter("tea_pack_time"),request.getParameter("tea_pack_smll_box_id"),request.getParameter("tea_pack_big_box_id"),
                request.getParameter("tea_pack_id"));

        String s = new String(bytes);

        if (bytes!=null){
            log.info("===>交易提交成功===>");
        }

        return s;// 返回字符串
    }


    /**
     * 质检表添加数据
     * 2022年3月17日16:50:56
     * */
    @ResponseBody
    @PostMapping("/teatesting")
    public String teatesting(HttpServletRequest request,HttpServletResponse response) throws Exception {
        /**
         * 质检人
         * */
        Employee employee = new Employee();
        employee.setEid(request.getParameter("tea_testing_per_id"));
        employee.setEname(request.getParameter("tea_testing_per_name"));
        employee.setEsex(request.getParameter("tea_testing_per_sex"));

        /**
         * 质检实体
         * */
        TeaTesting pojo = new TeaTesting();
        pojo.setTeaTestingPer(employee); // 质检人
        request.getParameter("tea_testing_time");// 质检时间
        pojo.setTeaTestingId(request.getParameter("tea_testing_id"));// 质检批次id
        pojo.setTeaTestingResult(request.getParameter("tea_testing_result"));// 质检结果
        pojo.setTeaTestingSmllBoxId(request.getParameter("tea_testing_smll_box_id"));// 小盒编号
        pojo.setTeaTestingBigBoxId(request.getParameter("tea_testing_big_box_id"));// 小盒编号
        request.getParameter("key");// ssl密钥


        /*往区块中加数据*/
        String  CCP = "/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com" +
                "/connection-org1.yaml";
        Path walletPath = Paths.get("/usr/software/Fabric_TraceabilitySys/wallet");

        Contract contract = FabricUtil.createContract(walletPath,CCP,"mychannel","teaArea-java-demo");
        log.info("===>开始提交区块链交易===>");

        byte[] bytes = contract.submitTransaction("createOneTeaTesting",request.getParameter("key"),
                request.getParameter("tea_testing_per_id"),request.getParameter("tea_testing_per_name"),request.getParameter("tea_testing_per_sex"),
                request.getParameter("tea_testing_id"),request.getParameter("tea_testing_result"),request.getParameter("tea_testing_time")
                ,request.getParameter("tea_testing_smll_box_id"),request.getParameter("tea_testing_big_box_id"));

        String s = new String(bytes);

        if (bytes!=null){
            log.info("===>交易提交成功===>");
        }

        return s;// 返回字符串
    }


}
