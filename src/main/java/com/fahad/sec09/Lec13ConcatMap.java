package com.fahad.sec09;

import com.fahad.common.Util;
import com.fahad.sec09.assignment.ExternalServiceClient;
import reactor.core.publisher.Flux;

/*
Ensure that external service is up and running
 */
public class Lec13ConcatMap {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        Flux.range(1,10)
                .concatMap(client::getProduct)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(20);
    }
}
