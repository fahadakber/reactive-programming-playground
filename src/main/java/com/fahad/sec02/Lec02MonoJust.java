package com.fahad.sec02;

import com.fahad.sec01.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;


public class Lec02MonoJust {
    private static final Logger log = LoggerFactory.getLogger(Lec02MonoJust.class);

    public static void main(String[] args) {
        var mono = Mono.just("fahad");
        var subscriber = new SubscriberImpl();
        mono.subscribe(subscriber);

        subscriber.getSubscription().request(10);

        // adding these will have no effect as producer already sent complete
        subscriber.getSubscription().request(10);
        subscriber.getSubscription().cancel();

    }
}
