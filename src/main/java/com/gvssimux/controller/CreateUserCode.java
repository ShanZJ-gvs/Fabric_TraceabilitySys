package com.gvssimux.controller;

import com.gvssimux.util.FabricUtil;
import org.hyperledger.fabric.gateway.Contract;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CreateUserCode {


    public static String CreateUserCode(String key,String value) throws Exception {
        Contract contract = FabricUtil.getContract();
        byte[] bytes = contract.submitTransaction("createUserCode", key,value);
        String s = new String(bytes);
        return s;
    }

}
