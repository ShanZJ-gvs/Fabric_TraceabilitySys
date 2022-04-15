package com.gvssimux.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gvssimux.dao.TeaPickDao;
import com.gvssimux.pojo.TeaPick;
import com.gvssimux.pojo.TeaTree;
import com.gvssimux.pojo.fabquery.QueryResult;
import com.gvssimux.pojo.fabquery.QueryResultList;
import com.gvssimux.util.DataUtil;
import com.gvssimux.util.JsonUtil;
import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class TeaPickServiceImpl implements TeaPickService{
    private String k;


    /*插入*/
    public String insertOne(Contract contract, TeaPick record){

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

    /*根据公司 限制查询*/
    public QueryResultList selectOffsetLimit(Contract contract, String companyName,int offset, int limit) throws Exception{
        byte[] bytes;
        String str = "{\"selector\":{\"company\":\""+companyName+"\",\"type\":\"TeaPick\"}, \"use_index\":[]}";// 富查询字符串
        try {
            bytes = contract.submitTransaction("richQuery", str);
        } catch (ContractException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        String s = new String(bytes);
        //System.out.println("提交交易" + s);
        JSONObject jsonObject = JSONObject.parseObject(s);
        QueryResultList resultList = JSON.toJavaObject(jsonObject, QueryResultList.class);
        //System.out.println(resultList);
        List<QueryResult> list = resultList.getResultList();
        int size = list.size();
        try {
            List<QueryResult> QueryResults = list.subList(offset, offset + limit);
            resultList.setResultList(QueryResults);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("开始下标小于 0 或大于数组的长度，");
            List<QueryResult> QueryResults = list.subList(offset,size);
            resultList.setResultList(QueryResults);
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            System.out.println("结束下标大于 toIndex 的值");
            e.printStackTrace();
        }
        return resultList;
    }

    /*获取每月的采摘量*/
    public HashMap getPickPerSumByCompany(Contract contract, String companyName) {
        byte[] bytes;
        String str = "{\"selector\":{\"company\":\""+companyName+"\",\"type\":\"TeaPick\"}, \"use_index\":[]}";// 富查询字符串
        try {
            bytes = contract.submitTransaction("richQuery", str);
        } catch (ContractException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        String s = new String(bytes);
        //System.out.println("提交交易" + s);
        JSONObject jsonObject = JSONObject.parseObject(s);
        QueryResultList resultList = JSON.toJavaObject(jsonObject, QueryResultList.class);
        //System.out.println(resultList);
        List<QueryResult> list = resultList.getResultList();


        HashMap<String, Integer> map = new HashMap<>();

        for (QueryResult a : list) {
            jsonObject = JSONObject.parseObject( a.getJson() );
            TeaPick teaPick = JSON.toJavaObject(jsonObject, TeaPick.class);
            String time = teaPick.getTeaPickTime(); // 时间
            String key = DataUtil.getMouth(time);
            Integer value = teaPick.getOutput(); // 产量作为value
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

    /*获取指定批次菜叶的采摘量*/
    public Integer getPickOutputByPickId(Contract contract, String companyName,String teaPickId) {
        byte[] bytes;
        String str = "{\"selector\":{\"company\":\""+companyName+"\",\"type\":\"TeaPick\",\"teaPickId\":\""+teaPickId+"\"}, \"use_index\":[]}";// 富查询字符串
        try {
            bytes = contract.submitTransaction("richQuery", str);
        } catch (ContractException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        String s = new String(bytes);
        //System.out.println("提交交易" + s);
        JSONObject jsonObject = JSONObject.parseObject(s);
        QueryResultList resultList = JSON.toJavaObject(jsonObject, QueryResultList.class);// 获取结果集
        QueryResult queryResult = resultList.getResultList().get(0); // 获取结果
        String jsonStr = queryResult.getJson(); // 提取jsonStr
        JSONObject jsonObject2 = JSONObject.parseObject(jsonStr);
        TeaPick teaPick = JSON.toJavaObject(jsonObject2, TeaPick.class);
        Integer output = teaPick.getOutput();

        return output;
    }

    public void setK(String k) {
        this.k = k;
    }
}
