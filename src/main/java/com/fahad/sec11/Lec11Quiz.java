package com.fahad.sec11;

import com.fahad.common.Util;
import reactor.core.publisher.Flux;

public class Lec11Quiz {
    public static void main(String[] args) {
        //Question 1; Ans: 6
       /* Flux.just("a")
                .repeat(1)
                .repeat(2)
                .subscribe(Util.subscriber());*/

        // Question 2; Ans: 1
        Flux.just("a")
                .retry(1)
                .retry(2)
                .subscribe(Util.subscriber());
    }


}
