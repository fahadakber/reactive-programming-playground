package com.fahad.sec07;

import com.fahad.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec02SubscribeOn {
    private static final Logger log = LoggerFactory.getLogger(Lec02SubscribeOn.class);

    // Schedulers (Thread pool) in project reactor library
    // boundedElastic - for Network / time-consuming / blocking operations
    // parallel - CPU intensive tasks
    // single - A single dedicated thread for one-off tasks
    // immediate - current thread

    // operators for schedulers
    // 1. subscribeOn - for up stream
    // 2. publishOn for downStream

    public static void main(String[] args) {

        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("generating: {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                }).doOnNext((v -> log.info("value: {}", v)))
                .doFirst(() -> log.info("first1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("first2"));

        Runnable runnable1 = () -> flux
                /*.doFirst(() -> log.info("first1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("first2"))*/
                .subscribe(Util.subscriber("sub1"));

        Runnable runnable2 = () -> flux.subscribe(Util.subscriber("sub2"));

        Thread.ofPlatform().start(runnable1);
        Thread.ofPlatform().start(runnable2);
        Util.sleepSeconds(2);
    }
}
