package com.fahad.sec06;

import com.fahad.common.Util;
import com.fahad.sec04.helper.NameGenerator;
import reactor.core.publisher.Flux;

public class Lec05FluxCreateIssueFix {

    public static void main(String[] args) {
        var generator = new NameGenerator();

        var flux = Flux.create(generator).share();
        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));


        for(int i = 0; i < 5; i++){
            generator.generate();
        }
    }
}
