package com.fahad.sec07;

import com.fahad.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;


public class Lec01DefaultBehaviorDemo {

    private static final Logger log = LoggerFactory.getLogger(Lec01DefaultBehaviorDemo.class);

    public static void main(String[] args) {

        var flux = Flux.create(sink->{
            for(int i = 1; i < 3; i++){
                log.info("generating: {}",i);
                sink.next(i);
            }
            sink.complete();
        }).doOnNext((v->log.info("value: {}",v)));

        Runnable runnable = ()-> flux.subscribe(Util.subscriber("sub1"));
        Thread.ofPlatform().start(runnable);
        //flux.subscribe(Util.subscriber("sub2"));
    }
}
