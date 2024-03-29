package com.gvssimux.fabric;

import com.gvssimux.util.FabricGatewayUtil;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import org.hyperledger.fabric.client.*;
import org.hyperledger.fabric.client.identity.Identities;
import org.hyperledger.fabric.client.identity.Signers;
import org.hyperledger.fabric.client.identity.X509Identity;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;


public class FabricGateway {
    String networkConnectionConfigPath = "/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com/connection-org1.json";
    String networkConnectionConfigPathRemote = "src/main/resources/peerOrganizations/org1.example.com/connection-org1.json";

    //String tlsCertPath = "/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt";
    //String privateKeyPath="/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com/users/User1@org1.example.com/msp/keystore/priv_sk";
    //String certificatePath="/usr/software/fabric-samples/test-network/organizations/peerOrganizations/org1.example.com/users/User1@org1.example.com/msp/signcerts/User1@org1.example.com-cert.pem";

//    String tlsCertPath = "src/main/resources/peerOrganizations/org1.example.com/peers/peer0.org1.example.com/tls/ca.crt";
//    String certificatePath="src/main/resources/peerOrganizations/org1.example.com/users/User1@org1.example.com/msp/signcerts/User1@org1.example.com-cert.pem";
//    String privateKeyPath="src/main/resources/peerOrganizations/org1.example.com/users/User1@org1.example.com/msp/keystore/priv_sk";


    String tlsCertPath = "D:\\JavaProject\\Fabric_TraceabilitySys\\src\\main\\resources\\peerOrganizations\\org1.example.com\\peers\\peer0.org1.example.com\\tls\\ca.crt";
    String certificatePath="D:\\JavaProject\\Fabric_TraceabilitySys\\src\\main\\resources\\peerOrganizations\\org1.example.com\\users\\User1@org1.example.com\\msp\\signcerts\\User1@org1.example.com-cert.pem";
    String privateKeyPath="D:\\JavaProject\\Fabric_TraceabilitySys\\src\\main\\resources\\peerOrganizations\\org1.example.com\\users\\User1@org1.example.com\\msp\\keystore\\priv_sk";




    String mspId="Org1MSP";
    String channel="mychannel";
    String chaincode="teaArea-java-demo";

    public Gateway gateway() throws Exception {


        BufferedReader certificateReader = Files.newBufferedReader(Paths.get(certificatePath), StandardCharsets.UTF_8);

        X509Certificate certificate = Identities.readX509Certificate(certificateReader);

        BufferedReader privateKeyReader = Files.newBufferedReader(Paths.get(privateKeyPath), StandardCharsets.UTF_8);

        PrivateKey privateKey = Identities.readPrivateKey(privateKeyReader);

        Gateway gateway = Gateway.newInstance()
                .identity(new X509Identity(mspId , certificate))
                .signer(Signers.newPrivateKeySigner(privateKey))
                .connection(newGrpcConnection())
                .evaluateOptions(CallOption.deadlineAfter(5, TimeUnit.SECONDS))
                .endorseOptions(CallOption.deadlineAfter(15, TimeUnit.SECONDS))
                .submitOptions(CallOption.deadlineAfter(5, TimeUnit.SECONDS))
                .commitStatusOptions(CallOption.deadlineAfter(1, TimeUnit.MINUTES))
                .connect();

       // System.out.println("=========================================== connected fabric gateway {} " + gateway);

        return gateway;
    }

    private ManagedChannel newGrpcConnection() throws IOException, CertificateException {
        Reader tlsCertReader = Files.newBufferedReader(Paths.get(tlsCertPath));
        X509Certificate tlsCert = Identities.readX509Certificate(tlsCertReader);

        /*return NettyChannelBuilder.forTarget("peer0.org1.example.com:7051")
                .sslContext(GrpcSslContexts.forClient().trustManager(tlsCert).build())
                .overrideAuthority("peer0.org1.example.com")
                .build();*/
        return NettyChannelBuilder.forTarget("192.168.0.119:7051")
                .sslContext(GrpcSslContexts.forClient().trustManager(tlsCert).build())
                .overrideAuthority("peer0.org1.example.com")
                .build();
    }

    public Network network(Gateway gateway) {
        return gateway.getNetwork(channel);
    }

   /* public Contract catContract(Network network) {
        return network.getContract("teaArea-java-demo");
    }*/

    public ChaincodeEventListener chaincodeEventListener(Network network) {
        return new ChaincodeEventListener(network);
    }


    public Contract getContract() throws Exception {
        Gateway gateway = gateway();
        Network network = network(gateway);
        Contract contract = network.getContract(chaincode);
        // return contract;
        /* 下面是为了验证是否能调用链码*/
        SubmittedTransaction submittedTransaction = contract.newProposal("queryData")
                .addArguments("FabricGeneralChainCode")//参数
                .build()
                .endorse()
                .submitAsync();
        Status status = submittedTransaction.getStatus();
        System.out.println("=======>"+status);
        System.out.println();
        byte[] result = submittedTransaction.getResult();
        System.out.println("初识数据=======>"+new String(result));
        System.out.println();
        byte[] bytes = contract.evaluateTransaction("queryData", "FabricGeneralChainCode");
        System.out.println("初识数据=======>"+new String(bytes));
        System.out.println();
        return contract;

    }

    @Test
    public void getContractTest() throws Exception {
        Contract contract = FabricGatewayUtil.getContract();
    }
}
