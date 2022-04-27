package com.gvssimux.service;


import com.gvssimux.pojo.TeaTesting;
import org.hyperledger.fabric.client.Contract;


public interface TeaTestingService {
    /*插入*/
    String insertOne(Contract contract, TeaTesting record);
}
