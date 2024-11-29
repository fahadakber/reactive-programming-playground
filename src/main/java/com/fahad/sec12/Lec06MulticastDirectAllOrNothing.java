package com.fahad.sec12;

import com.fahad.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class Lec06MulticastDirectAllOrNothing {
    public static final Logger log = LoggerFactory.getLogger(Lec06MulticastDirectAllOrNothing.class);

    public static void main(String[] args) {
        demo1();
        Util.sleepSeconds(10);

    }


    private static void demo1(){

        System.setProperty("reactor.bufferSize.small", "16");

        // handle through which would push items
        // directAllOrNothing means if one subsc is slow, the other subscriber will also receive no messages
        var sink = Sinks.many().multicast().directAllOrNothing();


        // handle through which subscibers will receive items
        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam")); // fast subscriber
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike")); // slow subscriber

        for (int i = 01; i <= 100 ; i++) {
            var result = sink.tryEmitNext(i);
            log.info("item: {}, result: {} ", i, result);
        }

    }

}
