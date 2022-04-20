package com.gvssimux.service;

import com.gvssimux.pojo.TeaTree;
import com.gvssimux.pojo.fabquery.QueryResultList;
import org.hyperledger.fabric.client.Contract;


public interface TeaTreeService {

    /*全部插入*/
    String insertOne(Contract contract, TeaTree record);

    /*查询全部*/
    QueryResultList selectOffsetLimit(Contract contract, int offset,int limit) throws Exception;




}
