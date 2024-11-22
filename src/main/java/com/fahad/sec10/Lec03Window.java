package com.fahad.sec10;


import com.fahad.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec03Window {
    public static void main(String[] args) {
        eventStream()
                //.window(5)
                .window(Duration.ofMillis(1800))
                .flatMap(Lec03Window::processEvents)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }


    private static Flux<String> eventStream(){
        return Flux.interval(Duration.ofMillis(500))
                .map(i-> "event-" + (i+1));
    }

    private static Mono<Void> processEvents(Flux<String> flux){
        return flux.doOnNext(e-> System.out.print("*"))
                .doOnComplete(System.out::println)
                .then();
    }
}
