package com.gvssimux.service;


import com.gvssimux.pojo.TeaGarden;
import com.gvssimux.pojo.fabquery.QueryResultList;
import org.hyperledger.fabric.client.Contract;


public interface TeaGardenService {


    /*插入*/
    String insertOne(Contract contract, TeaGarden record);

    /*查询*/
    QueryResultList selectOffsetLimit(Contract contract,String companyName,int offset,int limit) throws Exception;


    int getSum(Contract contract);


}
