package com.gvssimux.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.gvssimux.util.FabricUtil;
import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractException;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class TeaKindServiceImpl implements TeaKindService{
    private String k;

    @Override
    public String insertOne(List record) {
        Contract contract = null;
        String s = "";
        byte[] result = new byte[0];


        try {
            contract = FabricUtil.getContract();
        } catch (Exception e) {
            java.lang.String errorMessage = "交易提交失败~";
            System.out.println(errorMessage);
            e.printStackTrace();
            return null;
        }



        try {
            result = contract.submitTransaction("queryData", k);
        } catch (ContractException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("---23--"+new String(result) );
        if (result.length!=0) {// 有种类记录
            try {
                // 两个数组要合并
                s = new String(result);
                JSONArray jsonArr = JSONArray.parseArray(s);
                List<String> list1 = JSONArray.toJavaObject(jsonArr, List.class);

                System.out.println("list1====" + list1);
                list1.addAll(record);
                result = contract.submitTransaction("updateData", k, JSON.toJSONString(list1));
                return new String(result);
            } catch (ContractException e) {
                e.printStackTrace();
                return null;
            } catch (TimeoutException e) {
                e.printStackTrace();
                return null;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        } else {// 没有记录，创建纪律
            try {
                byte[] createData = contract.submitTransaction("createData", k, JSON.toJSONString(record));
                return new String(createData);
            } catch (ContractException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        return s;
    }





    public void setK(String k) {
        this.k = k;
    }

   
}
