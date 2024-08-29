package com.fahad.sec07;

import com.fahad.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec03MultipleSubscribeOn {

    private static final Logger log = LoggerFactory.getLogger(Lec03MultipleSubscribeOn.class);

    public static void main(String[] args) {

        var flux = Flux.create(sink -> {
                    for (int i = 1; i < 3; i++) {
                        log.info("generating: {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                // we can have multiple subscribeon(), but the one which is closest to producer (in this case 'subscribeOn(Schedulers.newParallel("fahad"))')
                // will take priority , other SubscribeOn() will delgate the work to the closest SubscribeOn, which will do all the work

                .subscribeOn(Schedulers.newParallel("fahad"))
                //.subscribeOn(Schedulers.immediate())
                .doOnNext((v -> log.info("value: {}", v)))
                .doFirst(() -> log.info("first1"))
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
