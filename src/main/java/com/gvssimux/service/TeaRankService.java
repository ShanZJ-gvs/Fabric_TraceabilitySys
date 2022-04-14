package com.gvssimux.service;

import com.gvssimux.pojo.TeaGarden;
import com.gvssimux.pojo.TeaRank;
import org.apache.ibatis.annotations.Select;
import org.hyperledger.fabric.gateway.Contract;

import java.util.List;

public interface TeaRankService {
    /*插入*/
    String insertOne(Contract contract, TeaRank record);


}
