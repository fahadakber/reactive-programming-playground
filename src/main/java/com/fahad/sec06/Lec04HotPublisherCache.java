package com.fahad.sec06;

import com.fahad.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec04HotPublisherCache {
    public static final Logger log = LoggerFactory.getLogger(Lec02HotPublisher.class);

    public static void main(String[] args) {
        //var movieFlux = movieStream().share(); // when .share() is used the publisher become hot, meaning the publisher will be
        // shared between subscribers

        //var movieFlux = movieStream().publish().refCount(2); // refCount() method requires min subscribers to get started
        // in this case min 2 subscribers needed before publishers starts to produce.

        // autoConnect() will continue to publish data, even if there are no subscribers or the subscribers cancel the request
        // before receving all the data
        //var stockFlux = stockStream().publish().autoConnect(0);

        // replay() will try to cache the values and replay back to  subscribers who joined late
        var stockFlux = stockStream().replay(1).autoConnect(0);


        Util.sleepSeconds(3);
        log.info("Fahad joining: ");
        stockFlux
                .subscribe(Util.subscriber("Fahad"));

        Util.sleepSeconds(3);
        log.info("Adeel joining: ");
        stockFlux
                .subscribe(Util.subscriber("Adeel"));

        Util.sleepSeconds(15);

    }

    // movie theater
    private static Flux<Integer> stockStream(){
        return Flux.generate(sink->sink.next(Util.faker().random().nextInt(10,100)))

                .delayElements(Duration.ofSeconds(3))
                .doOnNext(price->log.info("emitting price: {}", price))
                .cast(Integer.class);
    }

}
