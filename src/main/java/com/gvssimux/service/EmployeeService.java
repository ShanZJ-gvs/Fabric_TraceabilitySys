package com.gvssimux.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gvssimux.pojo.Employee;
import org.hyperledger.fabric.client.Contract;


public interface EmployeeService {
    /*限制插入，区块链中的key会根据总数自增,但是同一公司下不能出现相同编号的人*/
    String insertOne(Contract contract, Employee record);
}
