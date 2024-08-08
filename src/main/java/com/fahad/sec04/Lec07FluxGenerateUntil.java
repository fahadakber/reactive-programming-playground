package com.fahad.sec04;

import com.fahad.common.Util;
import reactor.core.publisher.Flux;

public class Lec07FluxGenerateUntil {

    public static void main(String[] args) {
        //demo1();
        demo2();

    }

    private static void demo1(){
        Flux.generate(synchronousSink -> {
                    var country = Util.faker().country().name();
                    synchronousSink.next(country);
                    if(country.equalsIgnoreCase("pakistan")){
                        synchronousSink.complete();
                    }

                })
                .subscribe(Util.subscriber());
    }

    private static void demo2(){

        Flux.<String>generate(synchronousSink -> {
                    var country = Util.faker().country().name();
                    synchronousSink.next(country);

                })
                .takeUntil(c->c.equalsIgnoreCase("pakistan"))
                .subscribe(Util.subscriber());

    }

}
