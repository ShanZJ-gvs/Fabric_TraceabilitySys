package com.gvssimux.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gvssimux.pojo.TeaTree;
import com.gvssimux.pojo.fabquery.QueryResult;
import com.gvssimux.util.FabricUtil;
import com.gvssimux.util.JsonUtil;
import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractException;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class TeaKindServiceImpl implements TeaKindService{
    private String k;


    public String insertOne(List<String> record) {
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
                // list1.addAll(record);
                for (String a:record){
                    int i = list1.indexOf(a);
                    if (i == -1){// 说明a不在list1中
                        list1.add(a);
                    }
                }
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

    @Override
    public int getSum(Contract contract){
        String s = "";
        byte[] result = new byte[0];
        try {
            result = contract.submitTransaction("queryData", k);
        } catch (ContractException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        s = new String(result);
        JSONArray jsonArr = JSONArray.parseArray(s);
        List<String> list1 = JSONArray.toJavaObject(jsonArr, List.class);
        return list1.size();
    }

    // 获取一个公司有哪些种茶树
    public List getKindByCompany(Contract contract,String companyName) {
        byte[] bytes = new byte[0];
        String str = "{\"selector\":{\"company\":\"" + companyName + "\",\"type\":\"TeaTree\"}, \"use_index\":[]}";// 富查询字符串
        ArrayList<String> l1 = new ArrayList<>();
        try {
            bytes = contract.submitTransaction("richQuery", str);
            String s = new String(bytes);
            List<QueryResult> list = JsonUtil.jsonStrToList(s);
            for (QueryResult a : list) {    // for循环可以打印某公司所有的茶树
                String json = a.getJson(); // 一棵茶树的jsonStr
                JSONObject jsonObject = JSONObject.parseObject(json);
                TeaTree tree1 = JSON.toJavaObject(jsonObject, TeaTree.class); // 拿到一颗茶树
                String kind = tree1.getTeaTreeKind(); // 拿到茶种
                if (!l1.contains(kind)){
                    l1.add(kind);  // 茶种不在记录，便添加
                }
            }
            return l1;     //  ["瓜片","瓜片1","猴魁"]
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }





        public void setK(String k) {
        this.k = k;
    }

   
}
