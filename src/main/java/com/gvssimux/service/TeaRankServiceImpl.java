package com.gvssimux.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.gvssimux.pojo.TeaRank;
import com.gvssimux.pojo.fabquery.QueryResult;
import com.gvssimux.pojo.fabquery.QueryResultList;

import org.hyperledger.fabric.client.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class TeaRankServiceImpl implements TeaRankService{
    private String k;


    /*插入*/
    @Override
    public String insertOne(Contract contract, TeaRank record){

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


    /*获取公司下各级茶叶有多少量*/
    public HashMap getRankPerSum(Contract contract,String companyName) {
        byte[] bytes = new byte[0];
        String str = "{\"selector\":{\"company\":\""+companyName+"\",\"type\":\"TeaRank\"}, \"use_index\":[]}";// 富查询字符串

        try {
            bytes = contract.submitTransaction("richQuery", str);
        } catch (EndorseException e) {
            e.printStackTrace();
        } catch (SubmitException e) {
            e.printStackTrace();
        } catch (CommitStatusException e) {
            e.printStackTrace();
        } catch (CommitException e) {
            e.printStackTrace();
        }

        String s = new String(bytes);
        //System.out.println("提交交易" + s);
        JSONObject jsonObject = JSONObject.parseObject(s);
        QueryResultList resultList = JSON.toJavaObject(jsonObject, QueryResultList.class);
        List<QueryResult> list = resultList.getResultList();

        HashMap<String, Integer> map = new HashMap<>();

        for (QueryResult a : list) {
            String jsonStr = a.getJson();
            JSONObject jsonObject2 = JSONObject.parseObject(jsonStr);
            TeaRank rank = JSON.toJavaObject(jsonObject2, TeaRank.class);// 拿到TeaRank对象
            String teaRankRank = rank.getTeaRankRank();// 获取茶叶等级
            String teaRankId = rank.getTeaRankId();// 获取这是哪批茶叶

            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
            TeaPickServiceImpl mapper = context.getBean("TeaPickServiceImpl", TeaPickServiceImpl.class);

            Integer output = mapper.getPickOutputByPickId(contract, companyName, teaRankId);// 查询这批茶叶的output

            if (!map.containsKey(teaRankRank)){ // key不在map中
                map.put(teaRankRank,output);
            }else {  // key在map中
                Integer temp = map.get(teaRankRank);  // 当前output
                Integer newValue = temp + output;// 增加output
                map.replace(teaRankRank, newValue);
            }
        }

        return map;
    }



    /*spring 注入k */
    public void setK(String k) {
        this.k = k;
    }
}
