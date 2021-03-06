package com.gvssimux.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.gvssimux.pojo.*;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



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
        pojo.setTeaAreaName(request.getParameter("tea_area_name"));
        pojo.setTeaAreaAddress(request.getParameter("tea_area_address"));
        pojo.setTeaAreaArea(request.getParameter("tea_area_area"));
        pojo.setTeaAreaLongitude(request.getParameter("tea_area_longitude"));
        pojo.setTeaAreaId2(request.getParameter("tea_area_id2"));
        pojo.setTeaGardenId2(request.getParameter("tea_garden_id2"));
        pojo.setCompany(request.getParameter("companyName"));
        request.getParameter("key");

        log.info("key===> "+request.getParameter("key"));

        /*byte[] bytes = contract.submitTransaction("createOneTeaArea", request.getParameter("key"),
                request.getParameter("tea_area_id1"),request.getParameter("tea_area_name"),
                request.getParameter("tea_area_address"), request.getParameter("tea_area_longitude"),
                request.getParameter("tea_area_area"),request.getParameter("tea_area_id2"),
                request.getParameter("tea_garden_id2"));

        String s = new String(bytes);
        if (bytes!=null){
            log.info("===>交易提交成功===>");
        }*/
        Contract contract = FabricUtil.getContract();
        return mapper.insertOne(contract,pojo);
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
        pojo.setTeaGardenName(request.getParameter("tea_garden_name"));
        pojo.setTeaGardenAddress(request.getParameter("tea_garden_address"));
        pojo.setTeaGardenArea(request.getParameter("tea_garden_area"));
        pojo.setTeaGardenLongitude(request.getParameter("tea_garden_longitude"));
        pojo.setTeaAreaId2(request.getParameter("tea_area_id2"));
        pojo.setTeaGardenId2(request.getParameter("tea_garden_id2"));
        pojo.setCompany(request.getParameter("companyName"));
        request.getParameter("key");


       /* byte[] bytes = contract.submitTransaction("createOneTeaGarden",request.getParameter("key"),
                request.getParameter("tea_garden_id1"),request.getParameter("tea_garden_name"),
                request.getParameter("tea_garden_address"), request.getParameter("tea_garden_area"),request.getParameter("tea_garden_longitude"),
                request.getParameter("tea_area_id2"),request.getParameter("tea_garden_id2"));

        String s = new String(bytes);

        if (bytes!=null){
            log.info("===>交易提交成功===>");
        }*/
        Contract contract = FabricUtil.getContract();
        return mapper.insertOne(contract,pojo);
    }


    /**
     * form-wizards.html 页面的控制逻辑
     * 给 茶树 添加数据
     * 2022年3月17日13:46:52
     */
    @ResponseBody
    @PostMapping("/teatree")
    public String teatree(HttpServletRequest request,HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaTreeServiceImpl mapper = context.getBean("TeaTreeServiceImpl", TeaTreeServiceImpl.class);

        TeaTree pojo = new TeaTree();
        pojo.setTeaTreeId(request.getParameter("tea_tree_id"));
        pojo.setTeaTreeAddress(request.getParameter("tea_tree_address"));
        pojo.setTeaTreeLongitude(request.getParameter("tea_tree_longitude"));
        pojo.setTeaTreeHeight(request.getParameter("tea_tree_height"));
        pojo.setTeaTreeKind(request.getParameter("tea_tree_kind"));
        pojo.setTeaTreeState(request.getParameter("tea_tree_state"));
        pojo.setTeaTreeCultivate(request.getParameter("tea_tree_cultivate"));
        pojo.setTeaTreeGrowingEnv(request.getParameter("tea_tree_Growing_Env"));
        pojo.setTeaAreaId2(request.getParameter("tea_area_id2"));
        pojo.setTeaGardenId2(request.getParameter("tea_garden_id2"));
        pojo.setCompany(request.getParameter("companyName"));

        request.getParameter("key");
/*
        byte[] bytes = contract.submitTransaction("createOneTeaTree",request.getParameter("key"),
                request.getParameter("tea_tree_id"),request.getParameter("tea_tree_address"),request.getParameter("tea_tree_longitude"),
                request.getParameter("tea_tree_height"),request.getParameter("tea_tree_kind"),request.getParameter("tea_tree_state"),
                request.getParameter("tea_tree_cultivate"),request.getParameter("tea_tree_Growing_Env"),
                request.getParameter("tea_area_id2"),request.getParameter("tea_garden_id2"));

        String s = new String(bytes);

        if (bytes!=null){
            log.info("===>交易提交成功===>");
        }*/

        return mapper.insertOne(FabricUtil.getContract(),pojo);
    }


    /**
     * 采摘表 添加数据
     * 其中关于采摘师、采摘人姓名、采摘人性别还未定，预定用employee类去代替
     * 2022年3月17日13:53:16
     * */
    @ResponseBody
    @PostMapping("teapick")
    public String teapick(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaPickServiceImpl mapper = context.getBean("TeaPickServiceImpl", TeaPickServiceImpl.class);
        Contract contract = FabricUtil.getContract();


        /**
         * 给采茶师添加数据
         *
         * */
        Employee employee = new Employee();// 采茶师
        employee.setEid(request.getParameter("tea_pick_per_id"));// 采茶师编号
        employee.setEname(request.getParameter("tea_pick_per_name"));// 采茶师姓名
        employee.setEsex(request.getParameter("tea_pick_per_sex"));// 采茶师性别
        employee.setCompany(request.getParameter("companyNameToEm"));// 采茶师公司
        employee.setStatus("采茶师");// 员工身份
        postPeople(contract,employee);

        TeaPick pojo = new TeaPick();
        pojo.setTeaPickTime(request.getParameter("tea_pick_time"));
        pojo.setTeaPickId(request.getParameter("tea_pick_id"));// 用teaPickId充当茶叶批id，因为一批茶叶的是同时采摘的
        pojo.setTeaTreeId2(request.getParameter("tea_tree_id2"));
        pojo.setTeaPickQuality(request.getParameter("tea_pick_quality"));
        pojo.setCompany(request.getParameter("companyName"));// 公司
        pojo.setOutput(Integer.valueOf(request.getParameter("output"))); //产量

        pojo.setTeaPickPer(employee);


        request.getParameter("key");



        /*
         往区块中加数据
        String  CCP = "/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com" +
                "/connection-org1.yaml";
        Path walletPath = Paths.get("/usr/software/Fabric_TraceabilitySys/wallet");

        Contract contract = FabricUtil.createContract(walletPath,CCP,"mychannel","teaArea-java-demo");
        log.info("===>开始提交区块链交易===>");

        byte[] bytes = contract.submitTransaction("createData",request.getParameter("key"),
                request.getParameter("tea_pick_per_id"),request.getParameter("tea_pick_per_name"),request.getParameter("tea_pick_per_sex"),
                request.getParameter("tea_pick_time"),request.getParameter("tea_pick_id"),request.getParameter("tea_tree_id2"),
                request.getParameter("tea_pick_quality"));

        String s = new String(bytes);

        if (bytes!=null){
            log.info("===>交易提交成功===>");
        }*/

        return mapper.insertOne(contract, pojo);// 返回字符串
    }


    /**
     * 制茶表添加数据
     * 2022年3月17日14:08:15
     * */
    @ResponseBody
    @PostMapping("/teamake")
    public String teamake(HttpServletRequest request,HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaMakeServiceImpl mapper = context.getBean("TeaMakeServiceImpl", TeaMakeServiceImpl.class);
        Contract contract = FabricUtil.getContract();


        /**
         * 给制茶师添加数据
         * */
        Employee employee = new Employee();
        employee.setEid(request.getParameter("tea_make_per_id"));
        employee.setEname(request.getParameter("tea_make_per_name"));
        employee.setEsex(request.getParameter("tea_make_per_sex"));
        employee.setCompany(request.getParameter("companyNameToEm"));
        employee.setStatus("制茶师");
        postPeople(contract,employee);

        /**
         * 给制茶实体添加数据
         * */
        TeaMake teaMake = new TeaMake();
        teaMake.setTeaMakePer(employee);// 制茶师
        teaMake.setTeaMakeTime(request.getParameter("tea_make_time"));
        teaMake.setTeaMakeWay(request.getParameter("tea_make_way"));// 制茶方式
        teaMake.setTeaMakeId(request.getParameter("tea_make_id"));// 茶叶批编号
        teaMake.setCompany(request.getParameter("companyName"));  //公司
        teaMake.setOutput(Integer.valueOf(request.getParameter("output")));  //产量


        return mapper.insertOne(contract, teaMake);// 返回字符串
    }


    /**
     * 定级表添加数据
     * 2022年3月17日14:54:09
     * */
    @ResponseBody
    @PostMapping("/tearank")
    public String  tearank(HttpServletRequest request,HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaRankServiceImpl mapper = context.getBean("TeaRankServiceImpl", TeaRankServiceImpl.class);
        Contract contract = FabricUtil.getContract();

        /**
         * 定级人
         * */
        Employee employee = new Employee();
        employee.setEid(request.getParameter("tea_rank_per_id"));
        employee.setEname(request.getParameter("tea_rank_per_name"));
        employee.setEsex(request.getParameter("tea_rank_per_sex"));
        employee.setCompany(request.getParameter("companyNameToEm"));
        employee.setStatus("定级人");
        postPeople(contract,employee);

        /**
         * 定级实体
         * */
        TeaRank teaRank = new TeaRank();
        teaRank.setTeaRankPer(employee);// 定级人
        teaRank.setTeaRankTime(request.getParameter("tea_rank_time")); // 定级时间
        teaRank.setTeaRankId(request.getParameter("tea_rank_id"));// 茶叶批编号
        teaRank.setTeaRankRank(request.getParameter("tea_rank_rank"));// 茶叶等级
        teaRank.setCompany(request.getParameter("companyName"));// 公司

        request.getParameter("key");// ssl密钥



        return mapper.insertOne(contract,teaRank);// 返回字符串
    }


    /**
     * 包装表添加数据
     * 2022年3月17日15:07:30
     * */
    @ResponseBody
    @PostMapping("/teapack")
    public String teapack(HttpServletRequest request,HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaPackServiceImpl mapper = context.getBean("TeaPackServiceImpl", TeaPackServiceImpl.class);

        Contract contract = FabricUtil.getContract();
        /**
         * 包装人
         * */
        Employee employee = new Employee();
        employee.setEid(request.getParameter("tea_pack_per_id"));
        employee.setEname(request.getParameter("tea_pack_per_name"));
        employee.setEsex(request.getParameter("tea_pack_per_sex"));
        employee.setCompany(request.getParameter("companyNameToEm"));
        employee.setStatus("包装人");
        postPeople(contract,employee);

        /**
         * 包装实体
         * */
        TeaPack teaPack = new TeaPack();
        teaPack.setTeaPackPer(employee);// 包装人
        teaPack.setTeaPackTime(request.getParameter("tea_pack_time")); // 包装时间
        teaPack.setTeaPackBigBoxId(request.getParameter("tea_pack_big_box_id"));// 小盒编号
        teaPack.setTeaPackSmllBoxId(request.getParameter("tea_pack_smll_box_id"));// 大盒编号
        teaPack.setTeaPackID(request.getParameter("tea_pack_id")); //包装id
        teaPack.setCompany(request.getParameter("companyName")); // 公司



        return mapper.insertOne(contract,teaPack);// 返回字符串
    }


    /**
     * 质检表添加数据
     * 2022年3月17日16:50:56
     * */
    @ResponseBody
    @PostMapping("/teatesting")
    public String teatesting(HttpServletRequest request,HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaTestingServiceImpl mapper = context.getBean("TeaTestingServiceImpl", TeaTestingServiceImpl.class);

        Contract contract = FabricUtil.getContract();
        /**
         * 质检人
         * */
        Employee employee = new Employee();
        employee.setEid(request.getParameter("tea_testing_per_id"));
        employee.setEname(request.getParameter("tea_testing_per_name"));
        employee.setEsex(request.getParameter("tea_testing_per_sex"));
        employee.setCompany(request.getParameter("companyNameToEm"));
        employee.setStatus("质检人");
        postPeople(contract,employee);


        /**
         * 质检实体
         * */
        TeaTesting pojo = new TeaTesting();
        pojo.setTeaTestingPer(employee); // 质检人
        pojo.setTeaTestingTime(request.getParameter("tea_testing_time"));// 质检时间
        pojo.setTeaTestingId(request.getParameter("tea_testing_id"));// 质检批次id
        pojo.setTeaTestingResult(request.getParameter("tea_testing_result"));// 质检结果
        pojo.setTeaTestingSmllBoxId(request.getParameter("tea_testing_smll_box_id"));// 小盒编号
        pojo.setTeaTestingBigBoxId(request.getParameter("tea_testing_big_box_id"));// 小盒编号
        request.getParameter("key");// ssl密钥


        return mapper.insertOne(contract,pojo);// 返回字符串
    }


    /**
     * 人员上链
     * 2022年3月17日16:50:56
     * */
    @ResponseBody
    @PostMapping("/people")
    public String people(HttpServletRequest request,HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        EmployeeServiceImpl mapper = context.getBean("EmployeeServiceImpl", EmployeeServiceImpl.class);

        /**
         * 质检人
         * */
        Employee employee = new Employee();
        employee.setEid(request.getParameter("eid"));
        employee.setEname(request.getParameter("ename"));
        employee.setEsex(request.getParameter("esex"));
        employee.setCompany(request.getParameter("companyName"));
        employee.setStatus(request.getParameter("status"));


        return mapper.insertOne(FabricUtil.getContract(),employee);// 返回字符串
    }



    /**
     * 用于给生产上链的页面调用，用于添加员工
     * 2022年3月17日16:50:56
     * */
    public String postPeople(Contract contract,Employee employee) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        EmployeeServiceImpl mapper = context.getBean("EmployeeServiceImpl", EmployeeServiceImpl.class);


        return mapper.insertOne(contract,employee);// 返回字符串
    }



}
