package com.gvssimux.service;

import com.gvssimux.pojo.TeaArea;
import com.gvssimux.pojo.fabquery.QueryResultList;
import org.apache.ibatis.annotations.Select;
import org.hyperledger.fabric.gateway.Contract;

import java.util.List;

public interface TeaAreaService {



    /*全部插入*/
    String insertOne(Contract contract,TeaArea record);

    /*查询全部*/
    QueryResultList selectOffsetLimit(Contract contract,int offset,int limit) throws Exception;

    int getSum(Contract contract);






//

//
//    /*部分插入*/
//    int insertSelective(TeaArea record);
}
