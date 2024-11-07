package com.fahad.sec07;

import com.fahad.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/*
 publis on for downstream
 */

public class Lec05PublisOn {

    private static final Logger log = LoggerFactory.getLogger(Lec05PublisOn.class);

    public static void main(String[] args) {

        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("generating: {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .publishOn(Schedulers.parallel())
                .doOnNext((v -> log.info("value: {}", v)))
                .doFirst(() -> log.info("first1"))
                .publishOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("first2"));

        Runnable runnable1 = () -> flux
                .subscribe(Util.subscriber("sub1"));


        Thread.ofPlatform().start(runnable1);
        Util.sleepSeconds(2);

    }
}
