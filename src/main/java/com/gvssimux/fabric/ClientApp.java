package com.gvssimux.fabric;

import lombok.extern.java.Log;
import org.hyperledger.fabric.gateway.*;

import java.nio.file.Path;
import java.nio.file.Paths;

@Log
public class ClientApp {
    static {
        System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");
    }

    public  String get(String s1,String s2) throws Exception {
        // Load a file system based wallet for managing identities.
        Path walletPath = Paths.get("wallet");
        Wallet wallet = Wallets.newFileSystemWallet(walletPath);
        // load a CCP
        String CCP = "D:/JavaProject/fabric-tea-app-java-demo/src/main/resources/com/shanzj/fabric/app/java/" +
                "peerOrganizations/org2.example.com/connection-org2.yaml";
        //Path networkConfigPath = Paths.get("..", "..", "test-network", "organizations", "peerOrganizations", "org1.example.com", "connection-org1.yaml");
        Path networkConfigPath = Paths.get(CCP);

        Gateway.Builder builder = Gateway.createBuilder();
        builder.identity(wallet, "appUser").networkConfig(networkConfigPath).discovery(true);
        String sr;
        // create a gateway connection
        try (Gateway gateway = builder.connect()) {

            // get the network and contract
            Network network = gateway.getNetwork("mychannel");

            Contract contract = network.getContract("teaArea-java-demo");
            byte[] result;

            //result = contract.evaluateTransaction("getTeaArea","茶区");
            log.info("执行到了查询区块了----");
            result = contract.evaluateTransaction(s1,s2);
            sr = new String(result);
            System.out.println(sr);


        }
        return sr;
    }


    public static void main(String[] args) throws Exception {
        // Load a file system based wallet for managing identities.
        Path walletPath = Paths.get("wallet");
        Wallet wallet = Wallets.newFileSystemWallet(walletPath);
        // load a CCP
        String CCP = "D:/JavaProject/fabric-tea-app-java-demo/src/main/resources/com/shanzj/fabric/app/java/" +
                "peerOrganizations/org2.example.com/connection-org2.yaml";
        //Path networkConfigPath = Paths.get("..", "..", "test-network", "organizations", "peerOrganizations", "org1.example.com", "connection-org1.yaml");
        Path networkConfigPath = Paths.get(CCP);

        Gateway.Builder builder = Gateway.createBuilder();
        builder.identity(wallet, "appUser").networkConfig(networkConfigPath).discovery(true);

        // create a gateway connection
        try (Gateway gateway = builder.connect()) {

            // get the network and contract
            Network network = gateway.getNetwork("mychannel");

            // Contract contract = network.getContract("fabcar");
            Contract contract = network.getContract("teaArea-java-demo");

            byte[] result;

            System.out.println("--------------------");
            // result = contract.evaluateTransaction("getTeaArea","茶区");
            String s1 = "getTeaArea";
            String s2 = "茶区";
            result = contract.evaluateTransaction(s1,s2);
            System.out.println(new String(result));

           /* System.out.println("--------------------");
            result = contract.evaluateTransaction("queryAllCars");
            System.out.println(new String(result));

            //contract.submitTransaction("createCar", "CAR10", "VW", "Polo", "Grey", "Mary");

            System.out.println("--------------------");
            result = contract.evaluateTransaction("queryCar", "CAR9");
            System.out.println(new String(result));


            System.out.println("--------------------");
            result = contract.evaluateTransaction("queryAllCars");
            System.out.println(new String(result));*/

            //contract.submitTransaction("changeCarOwner", "CAR10", "Archie");

            //result = contract.evaluateTransaction("queryCar", "CAR10");
            //System.out.println(new String(result));
        }
    }




}
