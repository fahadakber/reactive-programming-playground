package com.fahad.sec12;

import com.fahad.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

public class Lec01SinkOne {

    public static final Logger log = LoggerFactory.getLogger(Lec01SinkOne.class);

    public static void main(String[] args) {

        demo3();


    }
    // exploring sink methods to emit item / empty / error
    private static void demo1(){
        //Sinks.One<Object> sink = Sinks.one();
        var sink = Sinks.one();
        //Mono<Object> mono = sink.asMono();
        var mono = sink.asMono();
        mono.subscribe(Util.subscriber());
        //sink.tryEmitValue("hi");
        //sink.tryEmitEmpty();
        sink.tryEmitError(new RuntimeException("oops"));
    }

    // we can have multiple subscribers
    private static void demo2(){
        var sink = Sinks.one();
        var mono = sink.asMono();
        mono.subscribe(Util.subscriber("sam"));
        mono.subscribe(Util.subscriber("mike"));
        sink.tryEmitValue("hi");
    }

    private static void demo3(){

        var sink = Sinks.one();
        var mono = sink.asMono();

        mono.subscribe(Util.subscriber("sam"));
        sink.emitValue("hi",((signalType, emitResult) -> {
            log.info("hi");
            log.info(signalType.name());
            log.info(emitResult.name());
          return false;
        }));

        sink.emitValue("hello",((signalType, emitResult) -> {
            log.info("hello");
            log.info(signalType.name());
            log.info(emitResult.name());
            return false;
        }));


    }
}
