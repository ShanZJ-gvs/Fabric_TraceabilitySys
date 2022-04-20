package com.gvssimux.fabric;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.StringUtils;
import org.hyperledger.fabric.client.ChaincodeEvent;
import org.hyperledger.fabric.client.CloseableIterator;
import org.hyperledger.fabric.client.Network;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

/**
 * @author he peng
 * @date 2022/3/4
 */

@Slf4j
public class ChaincodeEventListener implements Runnable {

    final Network network;

    public ChaincodeEventListener(Network network) {
        this.network = network;

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                thread.setName(this.getClass() + "chaincode_event_listener");
                return thread;
            }
        });

        executor.execute(this);
    }

    @Override
    public void run() {
        CloseableIterator<ChaincodeEvent> events = network.getChaincodeEvents("hyperledger-fabric-contract-java-demo");
        log.info("chaincodeEvents {} " , events);


        // events.hasNext() 会阻塞等待
        while (events.hasNext()) {
            ChaincodeEvent event = events.next();

            log.info("receive chaincode event {} , transaction id {} ,  block number {} , payload {} "
                    , event.getEventName() , event.getTransactionId() , event.getBlockNumber() , StringUtils.newStringUtf8(event.getPayload()));

        }
    }
}
