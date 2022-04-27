package com.gvssimux.service;




import org.hyperledger.fabric.client.Contract;

import java.util.List;
import java.util.concurrent.TimeoutException;

public interface TeaKindService {
    /*全部插入*/
    String insertOne(List<String> record);


    int getSum(Contract contract);






}
