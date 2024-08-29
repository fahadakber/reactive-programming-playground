package com.fahad.sec04.helper;

import com.fahad.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class NameGenerator implements Consumer<FluxSink<String>> {

    public static final Logger log = LoggerFactory.getLogger(NameGenerator.class);

    private FluxSink<String> sink;

    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        log.info("got a flux sink");
        this.sink = stringFluxSink;
    }

    public void generate(){
        this.sink.next(Util.faker().name().firstName());
    }
}
