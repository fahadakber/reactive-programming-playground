package com.fahad.sec05;

/*

    Similar to error handling.
    Handling.empty!

 */

import com.fahad.common.Util;
import com.fahad.sec02.Lec04MonoEmptyError;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec07DefaultIfEmpty {

    public static void main(String[] args) {

        Mono.empty()
                .defaultIfEmpty("fallback")
                .subscribe(Util.subscriber());

        Flux.range(1,10)
                .filter(i-> i > 10)
                .defaultIfEmpty(-1)
                .subscribe(Util.subscriber());

    }
}
