package com.fahad.sec05;

import com.fahad.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04Delay {
    public static void main(String[] args) {

        Flux.range(1,10)
                .log()
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(12);
    }
}
