package com.gvssimux.service;

import com.gvssimux.pojo.TeaPack;
import org.hyperledger.fabric.client.Contract;


public interface TeaPackService{
    /*插入*/
    String insertOne(Contract contract, TeaPack record);
}
