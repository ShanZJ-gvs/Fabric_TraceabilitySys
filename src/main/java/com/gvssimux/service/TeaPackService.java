package com.gvssimux.service;

import com.gvssimux.pojo.TeaPack;
import com.gvssimux.pojo.TeaRank;
import org.hyperledger.fabric.gateway.Contract;

import java.util.List;

public interface TeaPackService{
    /*插入*/
    String insertOne(Contract contract, TeaPack record);
}
