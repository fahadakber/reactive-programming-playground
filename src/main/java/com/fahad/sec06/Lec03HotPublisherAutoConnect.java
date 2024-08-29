package com.fahad.sec06;

import com.fahad.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec03HotPublisherAutoConnect {

    public static final Logger log = LoggerFactory.getLogger(Lec02HotPublisher.class);

    public static void main(String[] args) {
        //var movieFlux = movieStream().share(); // when .share() is used the publisher become hot, meaning the publisher will be
        // shared between subscribers

        //var movieFlux = movieStream().publish().refCount(2); // refCount() method requires min subscribers to get started
        // in this case min 2 subscribers need before publishers
        // starts to produce.

        // autoConnect() will continue to publish data, even if there are no subscribers or the subscribers cancel the request
        // before receiving all the data
        var movieFlux = movieStream().publish().autoConnect(0);


        Util.sleepSeconds(2);
        movieFlux
                .take(4)
                .subscribe(Util.subscriber("Fahad"));

        Util.sleepSeconds(3);
        movieFlux
                .take(4)
                .subscribe(Util.subscriber("Adeel"));

        Util.sleepSeconds(15);

    }

    // movie theater
    private static Flux<String> movieStream(){
        return Flux.generate(
                        ()->{
                            log.info("received the request");
                            return 1;
                        },
                        (state,sink)->{
                            var scene = "movie scene " + state;
                            log.info("playing {}", scene);
                            sink.next(scene);
                            return ++state;
                        }
                )
                .take(10)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }

}
