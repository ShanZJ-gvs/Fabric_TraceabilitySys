package com.gvssimux.controller.testapi;

import com.gvssimux.pojo.fabquery.QueryResultList;
import com.gvssimux.service.TeaMakeServiceImpl;
import com.gvssimux.service.TeaRankServiceImpl;
import com.gvssimux.util.FabricUtil;
import com.gvssimux.util.JsonUtil;
import org.apache.ibatis.annotations.Param;
import org.hyperledger.fabric.client.Contract;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class GetAllPojoData {

    /* 查询制茶实体在区块链中的数据*/
    @ResponseBody
    @GetMapping("/makes")
    public String makeper(@Param("companyName") String companyName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaMakeServiceImpl mapper = context.getBean("TeaMakeServiceImpl", TeaMakeServiceImpl.class);
        Contract contract = FabricUtil.getContract();

        QueryResultList resultList = mapper.selectOffsetLimit(contract, companyName,0,100);


        return JsonUtil.getJson(resultList);
    }


    /* 查询制茶实体在区块链中的数据*/
    @ResponseBody
    @GetMapping("/ranks")
    public String ranks(@Param("companyName") String companyName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TeaRankServiceImpl mapper = context.getBean("TeaRankServiceImpl", TeaRankServiceImpl.class);
        Contract contract = FabricUtil.getContract();

        QueryResultList resultList = mapper.selectOffsetLimit(contract, companyName,0,100);


        return JsonUtil.getJson(resultList);
    }
}
