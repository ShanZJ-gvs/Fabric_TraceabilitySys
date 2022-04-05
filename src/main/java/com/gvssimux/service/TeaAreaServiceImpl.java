package com.gvssimux.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gvssimux.dao.TeaAreaDao;
import com.gvssimux.pojo.TeaArea;
import com.gvssimux.pojo.fabquery.TeaAreaQueryResult;
import com.gvssimux.pojo.fabquery.TeaAreaQueryResultList;
import com.gvssimux.util.FabricUtil;
import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

public class TeaAreaServiceImpl implements TeaAreaService {
    private String k;

    public String insertOne(TeaArea record) {
        Contract contract = null;
        try {
            contract = FabricUtil.getContract();
        } catch (Exception e) {
            System.out.println("FabUtil异常");
            e.printStackTrace();
        }
        byte[] bytes = new byte[0];
        byte[] bytes2 = new byte[0];
        int size = 0;
        try {
            bytes2 = contract.submitTransaction("queryAll", k);
            String s = new String(bytes2);
            //System.out.println("提交交易" + s);
            JSONObject jsonObject = JSONObject.parseObject(s);
            TeaAreaQueryResultList resultList = JSON.toJavaObject(jsonObject, TeaAreaQueryResultList.class);
            List<TeaAreaQueryResult> teaAreas = resultList.getTeaAreas();
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

    /*全部查询*/
    @Override
    public TeaAreaQueryResultList selectOffsetLimit(int offset,int limit) {
        byte[] bytes;
        try {
            bytes = FabricUtil.getContract().submitTransaction("queryAll", k);
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
        TeaAreaQueryResultList resultList = JSON.toJavaObject(jsonObject, TeaAreaQueryResultList.class);
        //System.out.println(resultList);
        List<TeaAreaQueryResult> teaAreas = resultList.getTeaAreas();
        int size = teaAreas.size();
        try {
            List<TeaAreaQueryResult> teaAreaQueryResults = teaAreas.subList(offset, offset + limit);
            resultList.setTeaAreas(teaAreaQueryResults);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("开始下标小于 0 或大于数组的长度，");
            e.printStackTrace();
        }catch (IllegalArgumentException e){
            System.out.println("结束下标大于 toIndex 的值");
            e.printStackTrace();
        }
        return resultList;
    }




    /*Spring自动装配*/
    public void setK(String k) {
        this.k = k;
    }
}
