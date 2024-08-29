package com.fahad.sec05;

import com.fahad.common.Util;
import reactor.core.publisher.Flux;

public class Sec05Quiz {

    public static void main(String[] args) {

        //q2
        Flux.range(1, 100)
                .take(25)
                .takeWhile(i -> i < 10)
                .takeUntil(i -> i > 1 && i < 5)
                .take(3)
                .subscribe(Util.subscriber());

        //q3
        Flux<Integer> range = Flux.range(1, 10);
        range.map(i -> i * 10);
        range.subscribe(System.out::println);

        //q4
        Flux.range(1, 10)
                .filter(i -> i > 5)
                .take(2)
                .next()
                .subscribe(System.out::println);

        // q5
        Flux.range(1, 3)
                .map(i -> i / (2 - i))
                .onErrorReturn(3);
    }

}
