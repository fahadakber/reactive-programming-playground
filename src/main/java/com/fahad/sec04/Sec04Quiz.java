package com.fahad.sec04;

import com.fahad.common.Util;
import reactor.core.publisher.Flux;

public class Sec04Quiz {

    public static void main(String[] args) {
        //Question 2
        /*Flux.generate(synchronousSink -> {

                    synchronousSink.next(1);
                    synchronousSink.next(2);

                })
                .subscribe(System.out::println);*/

        //Question 3
        /*Flux.range(1, 100)
                .takeWhile(i -> i < 3)
                .subscribe(Util.subscriber());*/

        // Question 4
        Flux.range(1, 5)
                .takeUntil(i -> i < 3)
                .subscribe(Util.subscriber());
    }



}
