package com.gvssimux.controller;

import com.gvssimux.pojo.AllPojo;
import com.gvssimux.pojo.TeaPack;
import com.gvssimux.pojo.fabquery.TeaAreaQueryResultList;
import com.gvssimux.util.FabricUtil;
import com.gvssimux.util.JsonUtil;
import org.hyperledger.fabric.gateway.Contract;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class allpojo {



    @ResponseBody
    @GetMapping("/al")
    public String userCode1(@RequestParam("userCode") String key, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Contract contract = FabricUtil.getContract();
        byte[] bytes = contract.submitTransaction("getAllPojo", "tt2");
        String s = new String(bytes);
        return s;
    }
}
