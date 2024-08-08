package com.fahad.sec04;

import com.fahad.common.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxCreate {
    public static void main(String[] args) {

        Flux.create(fluxSink -> {
           String country;
            do{
               country = Util.faker().country().name();
               fluxSink.next(country);
           }while(!country.equalsIgnoreCase("pakistan"));
           fluxSink.complete();
        })
                .subscribe(Util.subscriber());
    }
}
