package com.fahad.tests;

import com.fahad.common.Util;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec04RangeTest {
    private Flux<Integer> getItems(){
        return Flux.range(1,50);
    }
    private Flux<Integer> getRandomItems(){
        return Flux.range(1,50)
                .map(i-> Util.faker().random().nextInt(1,10));


    }

    @Test
    public void rangeTest1(){
        StepVerifier.create(getItems())
                // validating first 3 items
                .expectNext(1,2,3)
                // validated next 47 items count
                .expectNextCount(47)
                .expectComplete()
                .verify();
    }

    @Test
    public void rangeTest2(){
        StepVerifier.create(getItems())
                // validating first 3 items
                .expectNext(1,2,3)
                // validated next 22 items count
                .expectNextCount(22)
                // validating next expected value to be 26,27, and 28
                .expectNext(26,27,28)
                // validated next 22 items count
                .expectNextCount(22)
                // expecting complete signal
                .expectComplete()
                .verify();
    }

    @Test
    public void rangeTest3(){
        StepVerifier.create(getRandomItems())
                // expecting the first item to be in below range
                .expectNextMatches(i-> i > 0 && i < 101)
                // then expecting the same for the remaining 49 items
                .expectNextCount(49)
                // expecting complete signal
                .expectComplete()
                .verify();
    }
    @Test
    public void rangeTest4(){
        StepVerifier.create(getRandomItems())
                // expecting all items to be in below range
                .thenConsumeWhile(i-> i > 0 && i < 101)
                // expecting complete signal
                .expectComplete()
                .verify();
    }
}
