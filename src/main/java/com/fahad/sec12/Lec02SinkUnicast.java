package com.fahad.sec12;

import com.fahad.common.Util;
import reactor.core.publisher.Sinks;
/*
    We can emit multiple messages. but there will be only one subscriber.
*/
public class Lec02SinkUnicast {
    public static void main(String[] args) {

        demo2();

    }

    private static void demo1(){
        // handle through which would push items
        // onBackPressureBuffer - unbounded queue
        var sink = Sinks.many().unicast().onBackpressureBuffer();

        // handle through which subscibers will receive items
        var flux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

        flux.subscribe(Util.subscriber("sam"));
    }

    private static void demo2(){
        // handle through which would push items
        // onBackPressureBuffer - unbounded queue
        var sink = Sinks.many().unicast().onBackpressureBuffer();

        // handle through which subscibers will receive items
        var flux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));
    }
}
