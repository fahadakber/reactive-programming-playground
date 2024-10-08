package com.fahad.sec04;

import com.fahad.common.Util;
import com.fahad.sec04.helper.NameGenerator;
import reactor.core.publisher.Flux;

public class Lec02FluxCreateRefactor {

    public static void main(String[] args) {
        var generator = new NameGenerator();
        var flux = Flux.create(generator);
        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));

        for(int i = 0; i < 2; i++){
            generator.generate();
        }
    }
}
