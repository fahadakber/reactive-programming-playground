package com.fahad.tests;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/*
    To write a simple test using StepVerifier
    StepVerifiers acts like a subscriber
*/
public class Lec01MonoTest {

    private static final Logger log = LoggerFactory.getLogger(Lec01MonoTest.class);

    private Mono<String> getProduct(int id){
        return Mono.fromSupplier(()->"product-"+id)
                .doFirst(()->log.info("invoked"));
    }

    @Test
    public void productTest(){
        StepVerifier.create(getProduct(1))
                // expect actual result which is 'product-1
                .expectNext("product-1")
                // then expect complete signal from publisher
                .expectComplete()
                //triggering point, verify is similar to subscribe
                .verify();


    }

}
