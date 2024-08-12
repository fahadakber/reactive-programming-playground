package com.fahad.sec05;

import com.fahad.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec08SwitchIfEmpty {

    private static final Logger log = LoggerFactory.getLogger(Lec08SwitchIfEmpty.class);

    public static void main(String[] args) {
        /*
            The below case (switchIfEmpty) can be used in a scenario where
            you first send a request to cache (e.g. Redis), if the data is not
            available in cache, then you send another request to DB. So, 'switchIfEmpty
            can be used in such scenario

            DeafualtIfEmpty can be used, if you want to provide a fixed value if the publisher is
            empty

        */

        Flux.range(1,10)
                .filter(i-> i > 10)
                .switchIfEmpty(fallback())
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> fallback(){
        return Flux.range(100,3);
    }

}
