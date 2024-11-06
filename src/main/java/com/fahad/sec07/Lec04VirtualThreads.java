package com.fahad.sec07;

import com.fahad.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*
    reactor supports virtual threads
    System.setProperty("reactor.schedulers.defaultBoundedElasticOnVirtualThreads","true");
*/

public class Lec04VirtualThreads {

    private static final Logger log = LoggerFactory.getLogger(Lec04VirtualThreads.class);

    public static void main(String[] args) {

        // explicitly setting this property to true if the BoundedElastic() will use Virtual Threads to
        // complete the task
        System.setProperty("reactor.schedulers.defaultBoundedElasticOnVirtualThreads","true");
        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("generating: {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .doOnNext((v -> log.info("value: {}", v)))
                .doFirst(() -> log.info("first1 - {}", Thread.currentThread().isVirtual()))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("first2"));

        Runnable runnable1 = () -> flux
                /*.doFirst(() -> log.info("first1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("first2"))*/
                .subscribe(Util.subscriber("sub1"));


        Thread.ofPlatform().start(runnable1);
        Util.sleepSeconds(2);
    }
}
