package com.fahad.tests;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec06VirtualTimeTest {
    private Flux<Integer> getItems(){
        return Flux.range(1,5)
                .delayElements(Duration.ofSeconds(10));
    }
    // @Test We cannot run test like this
    public void rangeTest1(){
        StepVerifier.create(getItems())
                .expectNext(1,2,3,4,5)
                .expectComplete()
                .verify();
    }

    @Test
    public void virtualTimeTest1(){
        StepVerifier.withVirtualTime(this::getItems)
                .thenAwait(Duration.ofSeconds(50))
                .expectNext(1,2,3,4,5)
                .expectComplete()
                .verify();
    }
    @Test
    public void virtualTimeTest2(){
        StepVerifier.withVirtualTime(this::getItems)
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(9))
                .thenAwait(Duration.ofSeconds(1))
                .expectNext(1)
                .thenAwait(Duration.ofSeconds(40))
                .expectNext(2,3,4,5)
                .expectComplete()
                .verify();
    }
}
