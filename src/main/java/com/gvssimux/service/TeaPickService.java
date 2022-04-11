package com.gvssimux.service;

import com.gvssimux.pojo.TeaArea;
import com.gvssimux.pojo.TeaPick;
import com.gvssimux.pojo.fabquery.QueryResultList;
import org.hyperledger.fabric.gateway.Contract;

import java.util.List;

public interface TeaPickService {


    /*插入*/
    String insertOne(Contract contract, TeaPick record);

    /*限制查询*/
    QueryResultList selectOffsetLimit(Contract contract,String companyName,String teaPickId, int offset, int limit) throws Exception;



}
