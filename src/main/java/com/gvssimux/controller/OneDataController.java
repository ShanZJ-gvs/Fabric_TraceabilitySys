package com.gvssimux.controller;

import com.gvssimux.util.FabricUtil;
import lombok.extern.java.Log;
import org.hyperledger.fabric.gateway.Contract;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@Log
public class OneDataController {

    @ResponseBody
    @GetMapping("/teaarea/key")
    public String  teaarea(@RequestParam("key") String key, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("===>请求:/teaarea/key:查询key对应茶区===>");
        String  CCP = "/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com" +
                "/connection-org1.yaml";
        Path walletPath = Paths.get("/usr/software/Fabric_TraceabilitySys/wallet");
        Contract contract = FabricUtil.createContract(walletPath,CCP,"mychannel","teaArea-java-demo");
        byte[] result = contract.evaluateTransaction("getTeaArea", key);
        log.info("===>请求:/teaarea/key:查询完毕===>");
        return new String(result);
    }


    @ResponseBody
    @GetMapping("/teagarden/key")
    public String  teagarden(@RequestParam("key") String key, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("===>请求:/teagarden/key:查询key对应茶园===>");
        String  CCP = "/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com" +
                "/connection-org1.yaml";
        Path walletPath = Paths.get("/usr/software/Fabric_TraceabilitySys/wallet");
        Contract contract = FabricUtil.createContract(walletPath,CCP,"mychannel","teaArea-java-demo");
        byte[] result = contract.evaluateTransaction("getTeaGarden", key);
        log.info("===>请求:/teagarden/key:查询完毕===>");
        return new String(result);
    }


    @ResponseBody
    @GetMapping("/teatree/key")
    public String  teatree(@RequestParam("key") String key, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("===>请求:/teatree/key:查询key对应茶树===>");
        String  CCP = "/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com" +
                "/connection-org1.yaml";
        Path walletPath = Paths.get("/usr/software/Fabric_TraceabilitySys/wallet");
        Contract contract = FabricUtil.createContract(walletPath,CCP,"mychannel","teaArea-java-demo");
        byte[] result = contract.evaluateTransaction("getTeaTree", key);
        log.info("===>请求:/teatree/key:查询完毕===>");
        return new String(result);
    }



    @ResponseBody
    @GetMapping("/teapick/key")
    public String  teapick(@RequestParam("key") String key, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("===>请求:/teapick/key:查询key对应茶树===>");
        String  CCP = "/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com" +
                "/connection-org1.yaml";
        Path walletPath = Paths.get("/usr/software/Fabric_TraceabilitySys/wallet");
        Contract contract = FabricUtil.createContract(walletPath,CCP,"mychannel","teaArea-java-demo");
        byte[] result = contract.evaluateTransaction("getTeaPick", key);
        log.info("===>请求:/teapick/key:查询完毕===>");
        return new String(result);
    }


    @ResponseBody
    @GetMapping("/teamake/key")
    public String  teamake(@RequestParam("key") String key, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("===>请求:/teamake/key:查询key对应茶树===>");
        String  CCP = "/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com" +
                "/connection-org1.yaml";
        Path walletPath = Paths.get("/usr/software/Fabric_TraceabilitySys/wallet");
        Contract contract = FabricUtil.createContract(walletPath,CCP,"mychannel","teaArea-java-demo");
        byte[] result = contract.evaluateTransaction("getTeaMake", key);
        log.info("===>请求:/teamake/key:查询完毕===>");
        return new String(result);
    }


    @ResponseBody
    @GetMapping("/tearank/key")
    public String  tearank(@RequestParam("key") String key, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("===>请求:/tearank/key:查询key对应茶树===>");
        String  CCP = "/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com" +
                "/connection-org1.yaml";
        Path walletPath = Paths.get("/usr/software/Fabric_TraceabilitySys/wallet");
        Contract contract = FabricUtil.createContract(walletPath,CCP,"mychannel","teaArea-java-demo");
        byte[] result = contract.evaluateTransaction("getTeaRank", key);
        log.info("===>请求:/tearank/key:查询完毕===>");
        return new String(result);
    }

    @ResponseBody
    @GetMapping("/teapack/key")
    public String  teapack(@RequestParam("key") String key, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("===>请求:/teapack/key:查询key对应茶树===>");
        String  CCP = "/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com" +
                "/connection-org1.yaml";
        Path walletPath = Paths.get("/usr/software/Fabric_TraceabilitySys/wallet");
        Contract contract = FabricUtil.createContract(walletPath,CCP,"mychannel","teaArea-java-demo");
        byte[] result = contract.evaluateTransaction("getTeaPack", key);
        log.info("===>请求:/teapack/key:查询完毕===>");
        return new String(result);
    }


    @ResponseBody
    @GetMapping("/teatesting/key")
    public String  teatesting(@RequestParam("key") String key, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("===>请求:/teatesting/key:查询key对应茶树===>");
        String  CCP = "/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com" +
                "/connection-org1.yaml";
        Path walletPath = Paths.get("/usr/software/Fabric_TraceabilitySys/wallet");
        Contract contract = FabricUtil.createContract(walletPath,CCP,"mychannel","teaArea-java-demo");
        byte[] result = contract.evaluateTransaction("getTeaTesting", key);
        log.info("===>请求:/teatesting/key:查询完毕===>");
        return new String(result);
    }


}
