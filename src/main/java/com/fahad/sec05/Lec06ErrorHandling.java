package com.fahad.sec05;

import com.fahad.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec06ErrorHandling {

    private static final Logger log = LoggerFactory.getLogger(Lec06ErrorHandling.class);

    public static void main(String[] args) {
        // Below scenario will also work for Mono

        Flux.range(1,10)
                .map(i-> i == 5 ? 5/0 : i)
                .onErrorContinue((ex,obj)-> log.error("===> {} ", obj, ex)) // produce the error and further continue with iteration->onErrorContinue
                .subscribe(Util.subscriber());

    }

    // when you want to return a hardcoded value / simple computation
    private static void onErrorReturn(){
        Mono.just(5)
                .map(i-> i == 5 ? 5/0 : i)
                .onErrorReturn(IllegalArgumentException.class,-1)
                .onErrorReturn(ArithmeticException.class,-2)
                .onErrorReturn(-3) // fallback value, if there is any error
                .subscribe(Util.subscriber());
    }

    // when u have to use another publisher in case of error
    private static void onErrorResume(){

        Mono.error(new RuntimeException("Error!"))
                .onErrorResume(ArithmeticException.class,ex-> fallback1())
                .onErrorResume(ex->fallback2())
                .onErrorReturn(-5)
                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> fallback1(){
        log.info("executing fallback-1");
        return Mono.fromSupplier(()->Util.faker().random().nextInt(10,100));
    }

    private static Mono<Integer> fallback2(){
        log.info("executing fallback-2");
        return Mono.fromSupplier(()->Util.faker().random().nextInt(10,1000));
        //return Mono.error(new IllegalArgumentException());
    }
}
