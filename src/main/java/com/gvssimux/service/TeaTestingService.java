package com.gvssimux.service;

import com.gvssimux.pojo.TeaRank;
import com.gvssimux.pojo.TeaTesting;
import org.apache.ibatis.annotations.Select;
import org.hyperledger.fabric.gateway.Contract;

import java.util.List;

public interface TeaTestingService {
    /*插入*/
    String insertOne(Contract contract, TeaTesting record);
}
