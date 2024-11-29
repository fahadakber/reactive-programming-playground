package com.fahad.sec12;

import com.fahad.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;


public class Lec03SinkThreadSafety {

    public static final Logger log = LoggerFactory.getLogger(Lec03SinkThreadSafety.class);

    public static void main(String[] args) {

        demo2();

    }

    private static void demo1(){
        // handle through which would push items
        // onBackPressureBuffer - unbounded queue
        var sink = Sinks.many().unicast().onBackpressureBuffer();

        // handle through which subscibers will receive items
        var flux = sink.asFlux();

        var list = new ArrayList<>();
        flux.subscribe(list::add);

        for(int i = 0; i < 1000; i++){
            var j = i;
            CompletableFuture.runAsync(()->{
                sink.tryEmitNext(j); // not thread safe
            });
        }
        Util.sleepSeconds(2);
        log.info("list size: {}",list.size());
    }

    private static void demo2(){

        var sink = Sinks.many().unicast().onBackpressureBuffer();

        var flux = sink.asFlux();

        var list = new ArrayList<>();
        flux.subscribe(list::add);

        for(int i = 0; i < 1000; i++){
            var j = i;
            CompletableFuture.runAsync(()->{
                sink.emitNext(j,(signal,emitResult)->{
                    return Sinks.EmitResult.FAIL_NON_SERIALIZED.equals(emitResult);
                });
            });
        }
        Util.sleepSeconds(2);
        log.info("list size: {}",list.size());
    }

}
