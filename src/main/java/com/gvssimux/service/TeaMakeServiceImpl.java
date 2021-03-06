package com.gvssimux.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gvssimux.pojo.TeaMake;
import com.gvssimux.pojo.fabquery.QueryResult;
import com.gvssimux.pojo.fabquery.QueryResultList;
import com.gvssimux.util.DataUtil;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.EndorseException;


import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class TeaMakeServiceImpl implements TeaMakeService{
    private String k;


    /*插入*/
    public String insertOne(Contract contract, TeaMake record){

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


    /*获取每月的制茶量*/
    public HashMap getMakePerSumByCompany(Contract contract, String companyName) {
        byte[] bytes;
        String str = "{\"selector\":{\"company\":\""+companyName+"\",\"type\":\"TeaMake\"}, \"use_index\":[]}";// 富查询字符串
        try {
            bytes = contract.submitTransaction("richQuery", str);
        } catch (EndorseException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        String s = new String(bytes);

        JSONObject jsonObject = JSONObject.parseObject(s);
        QueryResultList resultList = JSON.toJavaObject(jsonObject, QueryResultList.class);

        List<QueryResult> list = resultList.getResultList();

        HashMap<String, Integer> map = new HashMap<>();
        
        for (QueryResult a : list) {
            jsonObject = JSONObject.parseObject( a.getJson() );
            TeaMake pojo = JSON.toJavaObject(jsonObject, TeaMake.class);
            String time = pojo.getTeaMakeTime(); // 时间
            String key = DataUtil.getMouth(time);// 月份
            Integer value = pojo.getOutput(); // 数量作为value
            if (!map.containsKey(key)){ // key不在map中
                map.put(key,value);
            }else {  // key在map中
                Integer temp = map.get(key);  // 当前采摘量
                Integer newValue = temp + value;// 增加采摘量
                map.replace(key, newValue);
            }
        }
        return map;
    }

    /*spring自动装配*/
    public void setK(String k) {
        this.k = k;
    }
}
