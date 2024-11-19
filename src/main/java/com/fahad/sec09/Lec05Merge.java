package com.fahad.sec09;

import com.fahad.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05Merge {

    private static final Logger log = LoggerFactory.getLogger(Lec05Merge.class);
    public static void main(String[] args) {
        demo2();

        Util.sleepSeconds(3);
    }

    private static void demo1(){
        // this merging of publishers is not sequential, whichever publishers has data it will be transmitted from
        // that publisher. there is no specific order
        Flux.merge(producer1(),producer3(),producer2())
                .take(3)
                .subscribe(Util.subscriber());
    }
    private static void demo2(){
                // .mergeWith() is also similar to Flux.merge(...)
                producer2()
                        .mergeWith(producer1())
                        .mergeWith(producer3())
                .take(2)
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> producer1(){
        return Flux.just(1,2,3)
                .transform(Util.fluxLogger("producer1"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer2(){
        return Flux.just(51,52,53)
                .transform(Util.fluxLogger("producer2"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer3(){
        return Flux.just(11,12,13)
                .transform(Util.fluxLogger("producer3"))
                .delayElements(Duration.ofMillis(10));
    }
}
