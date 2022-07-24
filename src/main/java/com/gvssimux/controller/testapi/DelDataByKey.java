package com.gvssimux.controller.testapi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gvssimux.pojo.fabquery.QueryResult;
import com.gvssimux.pojo.fabquery.QueryResultList;
import com.gvssimux.service.TeaMakeServiceImpl;
import com.gvssimux.util.FabricUtil;
import com.gvssimux.util.JsonUtil;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.hyperledger.fabric.client.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class DelDataByKey {


    @ResponseBody
    @Delete("/delete/key")
    public String makeper(@Param("key")String key, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Contract contract = FabricUtil.getContract();

        byte[] deleteData = contract.submitTransaction("deleteData", key);
        String s = new String(deleteData);


        return s;
    }
}
