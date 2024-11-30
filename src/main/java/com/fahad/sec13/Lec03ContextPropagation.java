package com.fahad.sec13;


import com.fahad.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;


public class Lec03ContextPropagation {

    public static final Logger log = LoggerFactory.getLogger(Lec03ContextPropagation.class);

    public static void main(String[] args) {
        getWelcomeMessage()
                //.concatWith(producer1())
                .concatWith(Flux.merge(producer1(),producer2().contextWrite(context -> Context.empty())))
                .contextWrite(Context.of("user","fahad"))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(2);
    }

    private static Mono<String> getWelcomeMessage(){
        return Mono.deferContextual(ctx->{
            //log.info("{}",ctx);
            if(ctx.hasKey("user")){
                return Mono.just("Welcome!! %s ".formatted(ctx.get("user").toString()));
            }
            return Mono.error(new RuntimeException("unauthenticated"));
        });
    }

    private static Mono<String> producer1(){
        return Mono.<String>deferContextual(ctx->{
            log.info("producer1: {}",ctx);
            return Mono.empty();
        })
                .subscribeOn(Schedulers.boundedElastic());
    }

    private static Mono<String> producer2(){
        return Mono.<String>deferContextual(ctx->{
                    log.info("producer2: {}",ctx);
                    return Mono.empty();
                })
                .subscribeOn(Schedulers.parallel());
    }

}
