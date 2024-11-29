package com.fahad.sec12;

import com.fahad.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;
import reactor.util.concurrent.Queues;

import java.time.Duration;
import java.util.Queue;

public class Lec05MulticastDirectBestEffort {
    public static final Logger log = LoggerFactory.getLogger(Lec05MulticastDirectBestEffort.class);

    public static void main(String[] args) {
        demo3();
        //Util.sleepSeconds(10);
        Util.sleepSeconds(30);
    }

    private static void demo1(){

        System.setProperty("reactor.bufferSize.small", "16");

        // handle through which would push items
        // onBackPressureBuffer - bounded queue
        var sink = Sinks.many().multicast().onBackpressureBuffer();

        // handle through which subscibers will receive items
        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam")); // fast subscriber
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike")); // slow subscriber

        for (int i = 01; i <= 100 ; i++) {
            var result = sink.tryEmitNext(i);
            log.info("item: {}, result: {} ", i, result);
        }

    }
    private static void demo2(){

        System.setProperty("reactor.bufferSize.small", "16");

        // handle through which would push items
        // this will help in overcome the above problem (in demo()1) of slow and fast subscriber.
        // this fast subscriber will get all the msgs
        var sink = Sinks.many().multicast().directBestEffort();


        // handle through which subscibers will receive items
        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam")); // fast subscriber
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike")); // slow subscriber

        for (int i = 01; i <= 100 ; i++) {
            var result = sink.tryEmitNext(i);
            log.info("item: {}, result: {} ", i, result);
        }

    }
    private static void demo3(){

        System.setProperty("reactor.bufferSize.small", "16");

        // handle through which would push items
        // this will help in overcome the above problem (in demo()1) of slow and fast subscriber.
        // this fast subscriber will get all the msgs
        var sink = Sinks.many().multicast().directBestEffort();


        // handle through which subscibers will receive items
        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam")); // fast subscriber
        // add 'onBackPressureBuffer' here will buffer the messages for this subscriber, so it can be picked up later.
        flux.onBackpressureBuffer().delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike")); // slow subscriber

        for (int i = 01; i <= 100 ; i++) {
            var result = sink.tryEmitNext(i);
            log.info("item: {}, result: {} ", i, result);
        }

    }
}
