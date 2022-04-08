package com.gvssimux.service;



import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractException;

import java.util.List;
import java.util.concurrent.TimeoutException;

public interface TeaKindService {
    /*全部插入*/
    String insertOne(List<String> record);


    int getSum(Contract contract);






}
