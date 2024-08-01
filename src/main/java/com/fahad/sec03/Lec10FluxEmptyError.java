package com.fahad.sec03;

import com.fahad.common.Util;
import reactor.core.publisher.Flux;

public class Lec10FluxEmptyError {
    public static void main(String[] args) {
        Flux.empty()
                .subscribe(Util.subscriber());

        Flux.error(new RuntimeException("oops"))
                .subscribe(Util.subscriber());
    }
}
