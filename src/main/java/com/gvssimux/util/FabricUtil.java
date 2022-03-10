package com.gvssimux.util;

import lombok.Data;
import org.hyperledger.fabric.gateway.*;

import java.nio.file.Path;
import java.nio.file.Paths;

@Data
public class FabricUtil {
    static {
        System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");

    }


    /**
     * create a gateway connection
     */
    public static Gateway createGateway() throws Exception{
        // Load a file system based wallet for managing identities.
        Path walletPath = Paths.get("wallet");
        walletPath = Paths.get("D:\\JavaProject\\Fabric_TraceabilitySys\\src\\main\\java\\com\\gvssimux\\wallet");
        Wallet wallet = Wallets.newFileSystemWallet(walletPath);
        // load a CCP
        String CCP = "D:/JavaProject/Fabric_TraceabilitySys/src/main/resources/com/shanzj/fabric/app/java/" +
                "peerOrganizations/org2.example.com/connection-org2.yaml";
        //Path networkConfigPath = Paths.get("..", "..", "test-network", "organizations", "peerOrganizations", "org1.example.com", "connection-org1.yaml");
        Path networkConfigPath = Paths.get(CCP);
        Gateway.Builder builder = Gateway.createBuilder();
        builder.identity(wallet, "appUser").networkConfig(networkConfigPath).discovery(true);
        // create a gateway connection
        try (Gateway gateway = builder.connect()) {
            return gateway;
        }

    }

    /**
     * get the network
     */
    public static Network createNetwork() throws Exception{
        Network network = createGateway().getNetwork("mychannel");
        return network;
    }


    /**
     * get the contract
     */
    public static Contract createContract() throws Exception{
        Contract contract = createNetwork().getContract("teaArea-java-demo");
        return contract;
    }










}
