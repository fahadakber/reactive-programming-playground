package com.fahad.sec04;

import com.fahad.common.Util;
import reactor.core.publisher.Flux;

public class Lec05TakeOperator {

    public static void main(String[] args) {
        //take()
        //takeWhile();
        takeUnitl();

    }

    private static void take(){
        Flux.range(1,10)
                .log("take")
                .take(3)
                .log("sub")
                .subscribe(Util.subscriber());
    }

    private static void takeWhile(){
        Flux.range(1,10)
                .log("takeWhile")
                .takeWhile(i-> i < 5) // stop when the condition become false
                .log("sub")
                .subscribe(Util.subscriber());
    }

    private static void takeUnitl(){
        Flux.range(1,10)
                .log("takeUntil")
                .takeUntil(i-> i < 5) // stop when the condition is met, also allow the last item
                .log("sub")
                .subscribe(Util.subscriber());
    }
}
