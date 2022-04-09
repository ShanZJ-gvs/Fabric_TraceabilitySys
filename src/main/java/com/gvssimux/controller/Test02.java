package com.gvssimux.controller;

import com.alibaba.fastjson.JSONObject;
import com.gvssimux.util.FabricUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Test02 {
    @GetMapping("/test03")
    public String fz3(Model model) throws Exception {
        model.addAttribute("msg","www123");
        return "test03";
    }
}
