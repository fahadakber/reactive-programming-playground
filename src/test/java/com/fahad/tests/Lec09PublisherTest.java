package com.fahad.tests;

import com.fahad.common.Util;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

import java.util.function.UnaryOperator;

public class Lec09PublisherTest {

    private UnaryOperator<Flux<String>> processor(){
        return flux-> flux
                .filter(s->s.length()>1)
                .map(String::toUpperCase)
                .map(s->s + ":" + s.length());
    }
    @Test
    public void publisherTest1(){
        var publisher = TestPublisher.<String>create();
        var flux = publisher.flux();

        flux.subscribe(Util.subscriber());
        /*
//        publisher.next("a","b");
//        publisher.complete();
        //diff between emit and complete is that emit method also sends a complete signal after passing the values
        publisher.emit("a","b");
        */

        StepVerifier.create(flux.transform(processor()))
                .then(()->publisher.emit("hi","hello"))
                .expectNext("HI:2")
                .expectNext("HELLO:5")
                .expectComplete()
                .verify();

    }

    @Test
    public void publisherTest2(){
        var publisher = TestPublisher.<String>create();
        var flux = publisher.flux();

        flux.subscribe(Util.subscriber());

        StepVerifier.create(flux.transform(processor()))
                .then(()->publisher.emit("a","b","c"))
                .expectComplete()
                .verify();

    }
}
