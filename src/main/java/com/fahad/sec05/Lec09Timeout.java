package com.fahad.sec05;

import com.fahad.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.time.Duration;

/*

    timeout - will produce timeout error.
    - we can handle as part of onError methods
    there is also an overloaded method to accept a publisher
    we can have multiple timeouts. the closest one to the subscriber will take effect for the subscriber

*/

public class Lec09Timeout {

    private static final Logger log = LoggerFactory.getLogger(Lec09Timeout.class);

    public static void main(String[] args) {

        var mono = getProductName()
                .timeout(Duration.ofSeconds(1), fallback()) // fallback() is  publisher here
                .onErrorReturn("fallback");

        mono.subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }

    private static Mono<String> getProductName(){
        return Mono.fromSupplier(()-> "service->" + Util.faker().commerce().productName())
                //.delayElement(Duration.ofSeconds(3));
                .delayElement((Duration.ofMillis(1900)));
    }

    private static Mono<String> fallback(){
        return Mono.fromSupplier(()-> "fallback service->" + Util.faker().commerce().productName())
                //.delayElement(Duration.ofSeconds(3));
                .delayElement((Duration.ofMillis(300)))
                .doFirst(()->log.info("do first"));
    }

}
