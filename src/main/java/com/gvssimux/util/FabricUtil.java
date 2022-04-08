package com.gvssimux.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.gvssimux.pojo.fabquery.QueryResult;
import com.gvssimux.pojo.fabquery.QueryResultList;
import lombok.Data;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.hyperledger.fabric.gateway.*;

import java.nio.file.Path;
import java.nio.file.Paths;

@Data
@Log
public class FabricUtil<T> {
    static {
        System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
    }

    static String  CCP = "/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com" +
            "/connection-org1.yaml";

    static Path walletPath = Paths.get("/usr/software/Fabric_TraceabilitySys/wallet");

    public static String fz1(String key,String value,int index) throws Exception {
        Contract contract = getContract();
        QueryResultList resultList = JSON.toJavaObject(JSONObject.parseObject(
                new String(
                        contract.submitTransaction("queryById" , "{\"selector\":{\""+key+"\":\""+value+"\"}, \"use_index\":[]}")
                )
        ),QueryResultList.class);
        System.out.println("提交交易"+new String(
                contract.submitTransaction("queryById" , "{\"selector\":{\""+key+"\":\""+value+"\"}, \"use_index\":[]}")
        ));
        System.out.println(resultList);
        QueryResult result = resultList.getResultList().get(index);
        System.out.println(result);
        String json = result.getJson();
        return json;
    }



    public static JSON queryById(String key,String value,String clas,int index) throws Exception {
        Contract contract = getContract();
        // 用户溯源码查询pack实体
        QueryResultList resultList = JSON.toJavaObject(JSONObject.parseObject(
                new String(
                        contract.submitTransaction("queryById" , "{\"selector\":{\""+key+"\":\""+value+"\"}, \"use_index\":[]}", clas)
                )
        ),QueryResultList.class);
        QueryResult a = JSON.toJavaObject(
                JSONObject.parseObject(
                        String.valueOf(resultList.getResultList().get(index))
                ),QueryResult.class
        );
        JSON json = JSON.parseObject(JSON.toJSONString(a.getJson()));
        //TeaPack pack = json.toJavaObject(TeaPack.class);// 拿到TeaPack实体
        return json;
    }



    public static QueryResultList queryByIdList(String key, String value, String clas) throws Exception {
        Contract contract = getContract();
        // 用户溯源码查询pack实体
        QueryResultList resultList = JSON.toJavaObject(JSONObject.parseObject(
                new String(
                        contract.submitTransaction("queryById" , "{\"selector\":{\""+key+"\":\""+value+"\"}, \"use_index\":[]}", clas)
                )
        ),QueryResultList.class);

        return resultList;
    }




    /**
     * create a gateway connection
     */
    public static Gateway createGateway(Path walletPath,String CCP) throws Exception{
        Wallet wallet = Wallets.newFileSystemWallet(walletPath);
        Path networkConfigPath = Paths.get(CCP);
        Gateway.Builder builder = Gateway.createBuilder();
        builder.identity(wallet, "appUser").networkConfig(networkConfigPath).discovery(true);
        // create a gateway connection
        log.info("创建getway");
        try (Gateway gateway = builder.connect()) {
            return gateway;
        }
    }

    /**
     * get the network
     * 如  "mychannel"
     */
    public static Network createNetwork(Path walletPath,String CCP,String channel) throws Exception{
        Network network = createGateway(walletPath,CCP).getNetwork(channel);
        log.info("创建network");
        return network;
    }


    /**
     * get the contract
     * 如 "teaArea-tea-java"
     */
    public static Contract createContract(Path walletPath,String CCP,String channel,String chaincodeId) throws Exception{
        Contract contract = createNetwork(walletPath,CCP,channel).getContract(chaincodeId);
        log.info("获取合约");
        return contract;
    }

    /**
     * 直接拿到 contract 去操作
     * */
    public static Contract getContract() throws Exception {
        Contract contract = createContract(walletPath,CCP,"mychannel","teaArea-java-demo");
        return contract;
    }










}
