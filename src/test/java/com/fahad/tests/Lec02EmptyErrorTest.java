package com.fahad.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


public class Lec02EmptyErrorTest {

    private static final Logger log = LoggerFactory.getLogger(Lec02EmptyErrorTest.class);

    private static Mono<String> getUsername(int userId){
        return switch (userId){
            case 1 -> Mono.just("sam");
            case 2 -> Mono.empty();   // do not have the data
            default->Mono.error(new RuntimeException("invalid input"));
        };
    }

    @Test
    public void userTest(){
        StepVerifier.create(getUsername(1))
                // expect actual result which is 'sam'
                .expectNext("sam")
                // then expect complete signal from publisher
                .expectComplete()
                //triggering point, verify is similar to subscribe
                .verify();


    }

    @Test
    public void emptyTest(){
        StepVerifier.create(getUsername(2))
                // then expect complete signal from publisher
                .expectComplete()
                //triggering point, verify is similar to subscribe
                .verify();


    }

    @Test
    public void errorTest1(){
        StepVerifier.create(getUsername(3))
                // then expect error signal from publisher
                .expectError()
                //triggering point, verify is similar to subscribe
                .verify();


    }

    @Test
    public void errorTest2(){
        StepVerifier.create(getUsername(3))
                // then expect RuntimeException
                .expectError(RuntimeException.class)
                //triggering point, verify is similar to subscribe
                .verify();


    }

    @Test
    public void errorTest3(){
        StepVerifier.create(getUsername(3))

                .expectErrorMessage("invalid input")
                //triggering point, verify is similar to subscribe
                .verify();
    }

    @Test
    public void errorTest4(){
        StepVerifier.create(getUsername(3))
                .consumeErrorWith(ex->{
                    Assertions.assertEquals(RuntimeException.class,ex.getClass());
                    Assertions.assertEquals("invalid input", ex.getMessage());
                })
                //triggering point, verify is similar to subscribe
                .verify();


    }
}
