package com.fahad.sec07;

import com.fahad.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Quiz {
    private static final Logger log = LoggerFactory.getLogger(Quiz.class);
    public static void main(String[] args) {

        // Question 4, ans: bounded elastic
        /*Flux.create(fluxSink -> {
                    for (int i = 0; i < 5; i++) {
                        fluxSink.next(i);
                    }
                    fluxSink.complete();
                })
                .map(i -> i + "a")
                .publishOn(Schedulers.boundedElastic())
                .subscribeOn(Schedulers.parallel())
                .subscribe(Util.subscriber());*/


        // Question 5 Ans: bounded elastic
        /*Flux<Object> flux = Flux.create(fluxSink -> {
                    for (int i = 0; i < 5; i++) {
                        fluxSink.next(i);
                    }
                    fluxSink.complete();
                })
                .subscribeOn(Schedulers.boundedElastic());

        flux.subscribeOn(Schedulers.parallel())
                .map(i -> i + "a")
                .subscribe(Util.subscriber());*/

        // Question 6, Ans parallel
        /*Flux.create(fluxSink -> {
                    for (int i = 0; i < 5; i++) {
                        fluxSink.next(i);
                    }
                    fluxSink.complete();
                })
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("first"))
                .subscribeOn(Schedulers.parallel())
                .map(i -> i + "a")
                .subscribe(Util.subscriber())*/;
        // Question 7, Ans main thread
/*        Flux.create(fluxSink -> {
                    for (int i = 0; i < 5; i++) {
                        fluxSink.next(i);
                    }
                    fluxSink.complete();
                })
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("first"))
                .publishOn(Schedulers.parallel())
                .map(i -> i + "a")
                .subscribe(Util.subscriber());*/

        // Question 8, Ans: parallel
        Flux.create(fluxSink -> {
                    for (int i = 0; i < 5; i++) {
                        fluxSink.next(i);
                    }
                    fluxSink.complete();
                })
                .publishOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("first"))
                .subscribeOn(Schedulers.parallel())
                .map(i -> i + "a")
                .subscribe(Util.subscriber());
        Util.sleepSeconds(10);
    }
}
