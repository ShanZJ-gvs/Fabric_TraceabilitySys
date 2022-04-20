package com.gvssimux.service;

import com.gvssimux.pojo.TeaArea;
import com.gvssimux.pojo.TeaPick;
import com.gvssimux.pojo.fabquery.QueryResultList;
import org.hyperledger.fabric.client.Contract;


public interface TeaPickService {


    /*插入*/
    String insertOne(Contract contract, TeaPick record);

    /*限制查询*/
    QueryResultList selectOffsetLimit(Contract contract,String companyName, int offset, int limit) throws Exception;



}
