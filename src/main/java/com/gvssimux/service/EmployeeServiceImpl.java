package com.gvssimux.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.gvssimux.pojo.Employee;
import com.gvssimux.pojo.TeaRank;
import com.gvssimux.pojo.fabquery.QueryResult;
import com.gvssimux.pojo.fabquery.QueryResultList;
import com.gvssimux.util.JsonUtil;
import org.hyperledger.fabric.client.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class EmployeeServiceImpl implements EmployeeService{
    private String k;


    /*限制插入，区块链中的key会根据总数自增,但是同一公司下不能出现相同编号的人*/
    public String insertOne(Contract contract, Employee record) {

        byte[] bytes = new byte[0];
        byte[] bytes2 = new byte[0];
        int size = 0;
        List<QueryResult> list = null;
        try {
            bytes2 = contract.submitTransaction("queryAllByKey", k);
            String s = new String(bytes2);
            //System.out.println("提交交易" + s);
            JSONObject jsonObject = JSONObject.parseObject(s);
            QueryResultList resultList = JSON.toJavaObject(jsonObject, QueryResultList.class);
            list = resultList.getResultList();
            size = list.size();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String company = record.getCompany(); // 当前公司是哪家

        QueryResultList queryResultList1 = selectEmployeeById(contract, company, record.getEid()); // 根据公司和员工编号查员工
        if (queryResultList1.getResultList()==null){//没有这个人
            try {
                bytes = contract.submitTransaction("createData", k + (size + 1), JSON.toJSONString(record));

            } catch (Exception e) {
                String errorMessage = "交易提交失败~";
                System.out.println(errorMessage);
                e.printStackTrace();
            }
            String s = new String(bytes);
            if (bytes != null) {
                System.out.println("===>交易提交成功===>");
            }
            return s;
        } else { // 有此员工编号
            System.out.println("此公司已有此员工编号");
            QueryResult queryResult = queryResultList1.getResultList().get(0);
            String jsonStr = queryResult.getJson();
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            Employee peo = JSON.toJavaObject(jsonObject, Employee.class);//拿到了员工对象
            try {
                return JsonUtil.getJson(peo);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                System.out.println("EmployeeServiceImpl 异常=====》插入员工异常");
                return null;
            }
        }

    }

    /*限制查询，一个公司下的全部员工*/
    public QueryResultList selectOffsetLimit(Contract contract,String companyName,int offset,int limit) {
        byte[] bytes = new byte[0];
        String str = "{\"selector\":{\"company\":\""+companyName+"\",\"type\":\"Employee\"}, \"use_index\":[]}";// 富查询字符串

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

    /*全部查询，一个公司下的全部员工*/
    public QueryResultList selectAllEmployee(Contract contract,String companyName) {
        byte[] bytes = new byte[0];
        String str = "{\"selector\":{\"company\":\""+companyName+"\",\"type\":\"Employee\"}, \"use_index\":[]}";// 富查询字符串

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
        return resultList;
    }

    /*查询，一个公司下的某个员工 根据编号*/
    public QueryResultList selectEmployeeById(Contract contract,String companyName,String id) {
        byte[] bytes = new byte[0];
        String str = "{\"selector\":{\"company\":\""+companyName+"\",\"eid\":\""+id+"\",\"type\":\"Employee\"}, \"use_index\":[]}";// 富查询字符串

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
        return resultList;
    }


    /*spring 注入k */
    public void setK(String k) {
        this.k = k;
    }
}
