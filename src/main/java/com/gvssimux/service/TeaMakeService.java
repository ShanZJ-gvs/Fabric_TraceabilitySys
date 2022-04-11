package com.gvssimux.service;

import com.gvssimux.pojo.TeaMake;
import com.gvssimux.pojo.TeaPick;
import com.gvssimux.pojo.fabquery.QueryResultList;
import org.apache.ibatis.annotations.Select;
import org.hyperledger.fabric.gateway.Contract;

import java.util.List;

public interface TeaMakeService {
    /*插入*/
    String insertOne(Contract contract, TeaMake record);


}
