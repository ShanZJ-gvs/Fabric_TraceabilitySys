package com.gvssimux.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gvssimux.dao.TeaLeafDao;
import com.gvssimux.pojo.TeaLeaf;
import com.gvssimux.pojo.TeaMake;
import com.gvssimux.pojo.TeaPick;
import com.gvssimux.pojo.TeaRank;
import com.gvssimux.pojo.fabquery.QueryResult;
import com.gvssimux.pojo.fabquery.QueryResultList;
import com.gvssimux.util.JsonUtil;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.EndorseException;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class TeaLeafServiceImpl implements TeaLeafService{



    /*根据公司 限制查询*/
    public QueryResultList selectOffsetLimit(Contract contract, String companyName, int offset, int limit) throws Exception{
        byte[] bytes;
        String str = "{\"selector\":{\"company\":\""+companyName+"\",\"type2\":\"TeaLeft\"}, \"use_index\":[]}";// 富查询字符串
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

    /*一批茶叶的采摘、制茶、定级信息*/
    public String getLeft(Contract contract, String companyName, int offset, int limit) throws Exception{
        QueryResultList queryResultList = selectOffsetLimit(contract, companyName, offset, limit);//拿到结果集
        List<QueryResult> resultList = queryResultList.getResultList(); //提取数据集
        HashMap<String,TeaLeaf> list = new HashMap<>();

        System.out.println("所有结果集=====>"+resultList);
        System.out.println();
        String id = null;

        for (QueryResult a:resultList) {   //循环数据集
            String jsonStr = a.getJson();
            TeaLeaf teaLeaf = new TeaLeaf();
            if (jsonStr.contains("TeaPick")){  // 判断结果是否是pick实体
                JSONObject jsonObject = JSONObject.parseObject(jsonStr);
                TeaPick teaPick = JSON.toJavaObject(jsonObject, TeaPick.class);
                id = teaPick.getTeaPickId(); //获取茶叶批id

                if (list.containsKey(id)){ //已包含这个key了
                    System.out.println();
                    TeaLeaf temp = list.get(id);
                    System.out.println("拿到temp===》"+temp);
                    temp.setTeaPick(teaPick);
                    list.replace(id, temp);
                    continue;
                }
                teaLeaf.setTeaPick(teaPick); // 存入茶叶实体
                System.out.println();
                System.out.println("if pick====>"+teaLeaf);
            }else if (jsonStr.contains("TeaMake")){
                JSONObject jsonObject = JSONObject.parseObject(jsonStr);
                TeaMake teaMake = JSON.toJavaObject(jsonObject, TeaMake.class);
                id = teaMake.getTeaMakeId(); //获取茶叶批id
                if (list.containsKey(id)){ //已包含这个key了
                    TeaLeaf temp = list.get(id);
                    temp.setTeaMake(teaMake);
                    list.replace(id, temp);
                    continue;
                }
                teaLeaf.setTeaMake(teaMake); // 存入茶叶实体
                System.out.println();
                System.out.println("if make====>"+teaLeaf);
            }else if (jsonStr.contains("TeaRank")){
                JSONObject jsonObject = JSONObject.parseObject(jsonStr);
                TeaRank teaRank = JSON.toJavaObject(jsonObject, TeaRank.class);
                id = teaRank.getTeaRankId(); //获取茶叶批id
                if (list.containsKey(id)){ //已包含这个key了
                    TeaLeaf temp = list.get(id);
                    temp.setTeaRank(teaRank);
                    list.replace(id, temp);
                    continue;
                }
                teaLeaf.setTeaRank(teaRank); // 存入茶叶实体
                System.out.println();
                System.out.println("if rank====>"+teaLeaf);
            }
            list.put(id,teaLeaf); // 新增
            System.out.println();
            System.out.println("for ===>"+list);
            System.out.println();
        }
        Collection<TeaLeaf> values = list.values();

        ArrayList<TeaLeaf> list0 = new ArrayList<>();

        for (TeaLeaf a:values) {
            if (a.getTeaPick()!=null&& a.getTeaMake()!=null && a.getTeaRank()!=null){
                list0.add(a);
            }
        }
        return JsonUtil.getJson(list0);
    }



}
