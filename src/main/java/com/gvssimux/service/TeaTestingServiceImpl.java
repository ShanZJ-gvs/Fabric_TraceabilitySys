package com.gvssimux.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.gvssimux.pojo.TeaTesting;
import com.gvssimux.pojo.fabquery.QueryResult;
import com.gvssimux.pojo.fabquery.QueryResultList;
import org.hyperledger.fabric.client.Contract;

import java.util.List;

public class TeaTestingServiceImpl implements TeaTestingService{
    private String k;


    /*插入*/
    public String insertOne( Contract contract,TeaTesting record){

        byte[] bytes = new byte[0];
        byte[] bytes2 = new byte[0];
        int size = 0;
        try {
            bytes2 = contract.submitTransaction("queryAllByKey", k);
            String s = new String(bytes2);
            //System.out.println("提交交易" + s);
            JSONObject jsonObject = JSONObject.parseObject(s);
            QueryResultList resultList = JSON.toJavaObject(jsonObject, QueryResultList.class);
            List<QueryResult> list = resultList.getResultList();
            size = list.size();
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            bytes = contract.submitTransaction("createData", k+(size+1),JSON.toJSONString(record));

        } catch (Exception e) {
            String errorMessage = "交易提交失败~";
            System.out.println(errorMessage);
            e.printStackTrace();
        }
        String s = new String(bytes);
        if (bytes!=null){
            System.out.println("===>交易提交成功===>");
        }
        return s;

    }

    /*查询有多少个质检记录*/
    public int querySum(Contract contract,String companyName){
        byte[] bytes = new byte[0];
        String str = "{\"selector\":{\"company\":\""+companyName+"\",\"type\":\"TeaTesting\"}, \"use_index\":[]}";// 富查询字符串
        try {
            bytes = contract.submitTransaction("richQuery", str);
            String s = new String(bytes);
            System.out.println("s====>"+s);
            JSONObject jsonObject = JSONObject.parseObject(s);
            QueryResultList resultList = JSON.toJavaObject(jsonObject, QueryResultList.class);
            System.out.println("resultList======>"+resultList);
            return resultList.getResultList().size();// 返回茶区数量
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /*spring 注入k */
    public void setK(String k) {
        this.k = k;
    }
}
