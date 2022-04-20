package com.gvssimux.service;

import com.gvssimux.pojo.TeaRank;
import org.hyperledger.fabric.client.Contract;

;

public interface TeaRankService {
    /*插入*/
    String insertOne(Contract contract, TeaRank record);


}
