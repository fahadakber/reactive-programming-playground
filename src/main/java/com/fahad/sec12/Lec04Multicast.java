package com.fahad.sec12;

import com.fahad.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;


public class Lec04Multicast {

    public static final Logger log = LoggerFactory.getLogger(Lec04Multicast.class);

    public static void main(String[] args) {

        demo2();

    }

    private static void demo1(){
        // handle through which would push items
        // onBackPressureBuffer - bounded queue
        var sink = Sinks.many().multicast().onBackpressureBuffer();

        // handle through which subscibers will receive items
        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

        Util.sleepSeconds(2);

        flux.subscribe(Util.subscriber("jake"));
        sink.tryEmitNext("new message");
    }

    private static void demo2(){
        // handle through which would push items
        // onBackPressureBuffer - bounded queue
        var sink = Sinks.many().multicast().onBackpressureBuffer();

        // handle through which subscibers will receive items
        var flux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

        Util.sleepSeconds(2);

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));
        flux.subscribe(Util.subscriber("jake"));

        sink.tryEmitNext("new message");
    }
}
