package com.gvssimux.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gvssimux.dao.TeaTreeDao;
import com.gvssimux.pojo.TeaArea;
import com.gvssimux.pojo.TeaTree;
import com.gvssimux.pojo.fabquery.QueryResult;
import com.gvssimux.pojo.fabquery.QueryResultList;
import com.gvssimux.util.FabricUtil;
import com.gvssimux.util.JsonUtil;
import org.hyperledger.fabric.client.*;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class TeaTreeServiceImpl implements TeaTreeService{
    private String k;


    public String insertOne(Contract contract, TeaTree record) {
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
            ArrayList<String> kinds = new ArrayList<String>();
            kinds.add(record.getTeaTreeKind());
            TeaKindServiceImpl teaKindService = new TeaKindServiceImpl();
            teaKindService.setK("TeaKind");
            teaKindService.insertOne(kinds);//插入树的种类
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

    /*全部查询*/
    @Override
    public QueryResultList selectOffsetLimit(Contract contract,int offset,int limit) {
        byte[] bytes = new byte[0];

        try {
            bytes = contract.submitTransaction("queryAllByKey", k);
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
        try {
            List<QueryResult> QueryResults = teaAreas.subList(offset, offset + limit);
            resultList.setResultList(QueryResults);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("开始下标小于 0 或大于数组的长度，");
            List<QueryResult> QueryResults = teaAreas.subList(offset,size);
            resultList.setResultList(QueryResults);
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            System.out.println("结束下标大于 toIndex 的值");
            e.printStackTrace();
        }
        return resultList;
    }


    public int getSum(Contract contract){

        byte[] bytes = new byte[0];

        try {
            bytes = contract.submitTransaction("queryAllByKey", k);
        } catch (EndorseException e) {
            e.printStackTrace();
        } catch (SubmitException e) {
            e.printStackTrace();
        } catch (CommitStatusException e) {
            e.printStackTrace();
        } catch (CommitException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = JSONObject.parseObject(new String(bytes));
        QueryResultList resultList = JSON.toJavaObject(jsonObject, QueryResultList.class);
        List<QueryResult> list = resultList.getResultList();

        return list.size();
    }


    /*查询一个公司下有多少茶树*/
    public int getTreeSumByCompany(Contract contract,String companyName){
        byte[] bytes = new byte[0];
        String str = "{\"selector\":{\"company\":\""+companyName+"\",\"type\":\"TeaTree\"}, \"use_index\":[]}";// 富查询字符串
        try {
            bytes = contract.submitTransaction("richQuery", str);
            String s = new String(bytes);
            JSONObject jsonObject = JSONObject.parseObject(s);
            QueryResultList resultList = JSON.toJavaObject(jsonObject, QueryResultList.class);
            System.out.println(resultList);

            List<QueryResult> teaAreas = resultList.getResultList();
            System.out.println(str);
            System.out.println(s);
            return resultList.getResultList().size();// 返回茶树数量
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }

    }


    /*查询一个公司下，茶种对应有多少棵茶树*/
    public HashMap getTreeSumByCompanyToKind(Contract contract,String companyName) {
        byte[] bytes = new byte[0];
        String str = "{\"selector\":{\"company\":\"" + companyName + "\",\"type\":\"TeaTree\"}, \"use_index\":[]}";// 富查询字符串
        HashMap<String, Integer> Sites = null;
        try {
            bytes = contract.submitTransaction("richQuery", str);
            String s = new String(bytes);
            List<QueryResult> list = JsonUtil.jsonStrToList(s);
            for (QueryResult a : list) {    // for循环可以打印某公司所有的茶树
                String json = a.getJson(); // 一棵茶树的jsonStr
                JSONObject jsonObject = JSONObject.parseObject(json);
                TeaTree tree1 = JSON.toJavaObject(jsonObject, TeaTree.class);
                System.out.println(tree1);
            }

            System.out.println();
            Sites = new HashMap<String, Integer>();
            List<String> kinds = new ArrayList<>();// new 一个来接收这些茶树的种类
            for (QueryResult a : list) {
                String json = a.getJson(); // 一棵茶树的jsonStr
                JSONObject jsonObject = JSONObject.parseObject(json);
                TeaTree tree2 = JSON.toJavaObject(jsonObject, TeaTree.class);// 拿到一棵茶树
                String kind = tree2.getTeaTreeKind();  // 茶树种类
                if (!Sites.containsKey(kind)) { // 判断集合是否存在 这棵茶树的茶种
                    Sites.put(kind, 1); // 加入记录
                } else {// 如果茶种类已被纪录
                    Integer sum = Sites.get(kind);
                    Sites.replace(kind, sum + 1);
                }
            }
            System.out.println("茶树种类以及对应数量的Map映射集===》" + Sites);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return Sites;
    }



    public void setK(String k) {
        this.k = k;
    }
}
