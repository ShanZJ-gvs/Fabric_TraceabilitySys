package com.gvssimux.service;

import com.gvssimux.pojo.TeaArea;
import com.gvssimux.pojo.TeaGarden;
import com.gvssimux.pojo.fabquery.QueryResultList;
import org.apache.ibatis.annotations.Select;
import org.hyperledger.fabric.gateway.Contract;

import java.util.List;

public interface TeaGardenService {


    /*插入*/
    String insertOne(Contract contract,TeaGarden record);

    /*查询全部*/
    QueryResultList selectOffsetLimit(Contract contract,String companyName,int offset,int limit) throws Exception;


    int getSum(Contract contract);


}
