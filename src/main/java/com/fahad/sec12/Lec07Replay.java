package com.fahad.sec12;

import com.fahad.common.Util;
import reactor.core.publisher.Sinks;

public class Lec07Replay {

    public static void main(String[] args) {
        demo1();
    }

    private static void demo1(){
        // handle through which would push items
        // all msgs will be stored in unbounded queue, so it can be recevied by all subscribers
        var sink = Sinks.many().replay().all();
        //var sink = Sinks.many().replay().limit(1); // jake will see the '?' message

        // handle through which subscibers will receive items
        var flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.subscribe(Util.subscriber("mike"));

        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

        Util.sleepSeconds(2);

        flux.subscribe(Util.subscriber("jake"));
        sink.tryEmitNext("new message");
    }
}
