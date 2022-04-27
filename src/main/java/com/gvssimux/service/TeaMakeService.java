package com.gvssimux.service;

import com.gvssimux.pojo.TeaMake;
import org.hyperledger.fabric.client.Contract;


import java.util.List;

public interface TeaMakeService {
    /*插入*/
    String insertOne(Contract contract, TeaMake record);


}
