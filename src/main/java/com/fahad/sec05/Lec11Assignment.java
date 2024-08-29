package com.fahad.sec05;

import com.fahad.common.Util;
import com.fahad.sec05.client.ExternalServiceClient;
import reactor.core.publisher.Flux;

import java.time.Duration;



public class Lec11Assignment {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        for (int i = 1; i < 5; i++) {
            client.getProductName(i)
                    .subscribe(Util.subscriber());
        }

        Util.sleepSeconds(3);

    }
}
