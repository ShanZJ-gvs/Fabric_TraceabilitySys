package com.gvssimux.util;


import com.gvssimux.fabric.FabricGateway;
import org.hyperledger.fabric.client.Contract;

public class FabricGatewayUtil {
    public static Contract getContract() throws Exception {
        FabricGateway fabricGateway = new FabricGateway();
        Contract contract = fabricGateway.getContract();
        return contract;
    }
}
