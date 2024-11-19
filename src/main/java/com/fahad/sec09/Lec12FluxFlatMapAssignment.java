package com.fahad.sec09;

import com.fahad.common.Util;

import com.fahad.sec09.assignment.ExternalServiceClient;
import reactor.core.publisher.Flux;

/*
Ensure that external service is up and running
 */
public class Lec12FluxFlatMapAssignment {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();

        Flux.range(1,10)
                .flatMap(client::getProduct)
                //.flatMap(client::getProduct,2)
                .subscribe(Util.subscriber());



        Util.sleepSeconds(20);
    }
}
