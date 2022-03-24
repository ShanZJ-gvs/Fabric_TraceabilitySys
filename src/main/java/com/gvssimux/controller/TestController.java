package com.gvssimux.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@Controller
@Log
public class TestController {

    @ResponseBody
    @GetMapping("/teaarea")
    public String  teaarea(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("===>请求:/teagarden/key:查询key对应茶园===>");

        String s ="{\n" +
                "    \"teaAreaLongitude\": \"未知\",\n" +
                "    \"teaAreaArea\": \"30000公顷\",\n" +
                "    \"teaGardenId2\": \"最高级\",\n" +
                "    \"teaAreaId2\": \"最高级\",\n" +
                "    \"teaAreaId1\": \"ea299bf88f0d476a89a562e1913deb31\",\n" +
                "    \"teaAreaAddress\": \"中国一号茶区\"\n" +
                "}";

        log.info("===>请求:/teagarden/key:查询完毕===>");


        return s;
    }

    @ResponseBody
    @GetMapping("/teagarden")
    public String  teagarden(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("===>请求:/teagarden/key:查询key对应茶园===>");
        String s =" {\n" +
                "    \"teaGardenId2\": \"无\",\n" +
                "    \"teaGardenId1\": \"安徽茶园一号\",\n" +
                "    \"teaGardenArea\": \"2000公顷\",\n" +
                "    \"teaAreaId2\": \"ea299bf88f0d476a89a562e1913deb31\",\n" +
                "    \"teaGardenLongitude\": \"未知\",\n" +
                "    \"teaGardenAddress\": \"安徽黄山市\"\n" +
                "}";
        log.info("===>请求:/teagarden/key:查询完毕===>");
        return s;
    }


    @ResponseBody
    @GetMapping("/teatree")
    public String  teatree(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String s ="{\n" +
                "    \"teaTreeAddress\": \"安徽黄山市\",\n" +
                "    \"teaTreeLongitude\": \"未知\",\n" +
                "    \"teaGardenId2\": \"安徽茶园一号\",\n" +
                "    \"teaTreeCultivate\": \"合格\",\n" +
                "    \"teaAreaId2\": \"ea299bf88f0d476a89a562e1913deb31\",\n" +
                "    \"teaTreeId\": \"05b850cf40124e4f8be9218e9dc19885\",\n" +
                "    \"teaTreeHeight\": 300,\n" +
                "    \"teaTreeKind\": \"ea299bf88f0d476a89a562e1913deb31\",\n" +
                "    \"teaTreeState\": \"状态良好\",\n" +
                "    \"teaTreeGrowingEnv\": \"湿润\"\n" +
                "}";
        return s;
    }

    @ResponseBody
    @GetMapping("/teapick")
    public String  teapick(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String s ="{\n" +
                "    \"teaPickPer\": {\n" +
                "        \"eid\": \"802b588237164a2c8ea70e549889bd50\",\n" +
                "        \"ename\": \"张三疯\",\n" +
                "        \"esex\": \"男\"\n" +
                "    },\n" +
                "    \"teaTreeId2\": \"05b850cf40124e4f8be9218e9dc19885\",\n" +
                "    \"teaPickId\": \"d3a106cdd79a4241874573755dcfc588\",\n" +
                "    \"teaPickQuality\": \"合格\",\n" +
                "    \"teaPickTime\": \"2022年3月23日20:30:20\"\n" +
                "}";
        return s;
    }

    @ResponseBody
    @GetMapping("/teamake")
    public String  teamake(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String s ="{\n" +
                "    \"teaMakeId\": \"d3a106cdd79a4241874573755dcfc588\",\n" +
                "    \"teaMakeWay\": \"炒制\",\n" +
                "    \"teaMakeTime\": \"2022年3月23日20:38:36\",\n" +
                "    \"teaMakePer\": {\n" +
                "        \"eid\": \"abbca5e86db246cbb3f90ca9be64f5ae\",\n" +
                "        \"ename\": \"小龙女\",\n" +
                "        \"esex\": \"女\"\n" +
                "    }\n" +
                "}";
        return s;
    }

    @ResponseBody
    @GetMapping("/tearank")
    public String  tearank(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String s ="{\n" +
                "    \"teaRankId\": \"d3a106cdd79a4241874573755dcfc588\",\n" +
                "    \"teaRankRank\": \"合格\",\n" +
                "    \"teaRankPer\": {\n" +
                "        \"eid\": \"6386b24032bd4e0db08fb2e4231f389d\",\n" +
                "        \"ename\": \"单子健\",\n" +
                "        \"esex\": \"男\"\n" +
                "    },\n" +
                "    \"teaRankTime\": \"2022年3月23日20:43:50\"\n" +
                "}";
        return s;
    }

    @ResponseBody
    @GetMapping("/teapack")
    public String teapack(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String s ="{\n" +
                "    \"teaPackTime\": \"2022年3月23日20:48:46\",\n" +
                "    \"teaPackSmllBoxId\": \"2996d90caac749a3b61067b4e80b2e0a\",\n" +
                "    \"teaPackPer\": {\n" +
                "        \"eid\": \"cd09adee145f4ac995d349127e623efb\",\n" +
                "        \"ename\": \"杨过\",\n" +
                "        \"esex\": \"男\"\n" +
                "    },\n" +
                "    \"teaPackID\": \"d3a106cdd79a4241874573755dcfc588\",\n" +
                "    \"teaPackBigBoxId\": \"24c0e81c573147589634ae12faa32236\"\n" +
                "}";
        return s;
    }


    @ResponseBody
    @GetMapping("/teatesting")
    public String teatesting(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String s ="{\n" +
                "    \"teaTestingId\": \"dfd1e3b68ecf42a4b9c24e83f9591738\",\n" +
                "    \"teaTestingSmllBoxId\": \"2996d90caac749a3b61067b4e80b2e0a\",\n" +
                "    \"teaTestingTime\": \"2022年3月23日20:54:35\",\n" +
                "    \"teaTestingBigBoxId\": \"24c0e81c573147589634ae12faa32236\",\n" +
                "    \"teaTestingResult\": \"通过\"\n" +
                "}";
        return s;
    }



}
