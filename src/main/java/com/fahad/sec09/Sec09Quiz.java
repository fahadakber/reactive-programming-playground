package com.fahad.sec09;

import com.fahad.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;



public class Sec09Quiz {

    private static final Logger log = LoggerFactory.getLogger(Sec09Quiz.class);

    public static void main(String[] args) {

        //Q2
        /*Flux<String> flux = Flux.just("a", "b", "c");
        flux.startWith(flux)
                .subscribe(Util.subscriber());*/

        //Q3
       /* Mono<Integer> mono1 = Mono.just(1).delayElement(Duration.ofSeconds(1));
        Flux<Integer> flux1 = Flux.just(2);
        Flux<Integer> flux2 = flux1.map(i -> i + 1).delayElements(Duration.ofMillis(500));

        Flux.merge(mono1, flux1, flux2)
                .subscribe(Util.subscriber());*/

        Util.sleepSeconds(5);

    }


}
