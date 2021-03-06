package com.gvssimux.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gvssimux.pojo.TeaArea;
import com.gvssimux.pojo.fabquery.QueryResult;
import com.gvssimux.pojo.fabquery.QueryResultList;
import org.hyperledger.fabric.client.*;

import java.util.List;




public class TeaAreaServiceImpl implements TeaAreaService {
    private String k;

    public String insertOne(Contract contract, TeaArea record) {
        byte[] bytes = new byte[0];
        byte[] bytes2 = new byte[0];
        int size = 0;
        try {
            bytes2 = contract.submitTransaction("queryAllByKey", k);
            String s = new String(bytes2);
            //System.out.println("提交交易" + s);
            JSONObject jsonObject = JSONObject.parseObject(s);
            QueryResultList resultList = JSON.toJavaObject(jsonObject, QueryResultList.class);
            List<QueryResult> teaAreas = resultList.getResultList();
            size = teaAreas.size();
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


    /*限制查询*/
    @Override
    public QueryResultList selectOffsetLimit(Contract contract,String companyName,int offset,int limit) {
        byte[] bytes = new byte[0];
        String str = "{\"selector\":{\"company\":\""+companyName+"\",\"type\":\"TeaArea\"}, \"use_index\":[]}";// 富查询字符串

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
        //System.out.println(resultList);
        List<QueryResult> teaAreas = resultList.getResultList();
        int size = teaAreas.size();
        System.out.println("try前"+resultList);
        try {
            List<QueryResult> QueryResults = teaAreas.subList(offset, offset + limit);
            resultList.setResultList(QueryResults);
            System.out.println("try中"+resultList);
        } catch (IndexOutOfBoundsException e) {
            List<QueryResult> QueryResults = teaAreas.subList(offset,size);
            resultList.setResultList(QueryResults);
            System.out.println("开始下标小于 0 或大于数组的长度，");
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            System.out.println("结束下标大于 toIndex 的值");
            e.printStackTrace();
        }
        System.out.println("try后"+resultList);
        return resultList;
    }


    @Override
    public int getSum(Contract contract) {
        byte[] bytes = new byte[0];
        try {
            bytes = contract.submitTransaction("queryAllByKey", k);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = JSONObject.parseObject(new String(bytes));
        QueryResultList resultList = JSON.toJavaObject(jsonObject, QueryResultList.class);

        List<QueryResult> teaAreas = resultList.getResultList();
        return teaAreas.size();
    }


    /*查询一个公司下有多少茶区*/
    public int getAreaSumByCompany(Contract contract,String companyName){
        byte[] bytes = new byte[0];
        String str = "{\"selector\":{\"company\":\""+companyName+"\",\"type\":\"TeaArea\"}, \"use_index\":[]}";// 富查询字符串
        try {
            bytes = contract.submitTransaction("richQuery", str);
            String s = new String(bytes);
            JSONObject jsonObject = JSONObject.parseObject(s);
            QueryResultList resultList = JSON.toJavaObject(jsonObject, QueryResultList.class);
            System.out.println(resultList);

            List<QueryResult> teaAreas = resultList.getResultList();
            System.out.println(str);
            System.out.println(s);
            return resultList.getResultList().size();// 返回茶区数量
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }



    /*Spring自动装配*/
    public void setK(String k) {
        this.k = k;
    }
}
