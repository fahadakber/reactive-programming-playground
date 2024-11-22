package com.fahad.sec10;


import com.fahad.common.Util;
import com.fahad.sec10.assignment.window.FileWriter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec04WindowAssignment {
    public static void main(String[] args) {
        var counter = new AtomicInteger(0);
        var fileNameFormat = "src/main/resources/sec10/file%d.txt";

        eventStream()
                .window(Duration.ofMillis(1800))
                .flatMap(flux-> FileWriter.create(flux, Path.of(fileNameFormat.formatted(counter.incrementAndGet()))))
                .subscribe();

        Util.sleepSeconds(60);
    }


    private static Flux<String> eventStream(){
        return Flux.interval(Duration.ofMillis(500))
                .map(i-> "event-" + (i+1));
    }


}
