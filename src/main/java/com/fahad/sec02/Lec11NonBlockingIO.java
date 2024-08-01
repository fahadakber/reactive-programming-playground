package com.fahad.sec02;

import com.fahad.common.Util;
import com.fahad.sec02.client.ExternalServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec11NonBlockingIO {

    private static final Logger log = LoggerFactory.getLogger(Lec11NonBlockingIO.class);

    public static void main(String[] args) {

        var client = new ExternalServiceClient();
        log.info("starting");
        for(int i = 1; i <= 5; i++){
            var name = client.getProductName(i)
                    //.subscribe(Util.subscriber());
                    .block();
            log.info(name);
        }

        Util.sleepSeconds(2);
    }

}
