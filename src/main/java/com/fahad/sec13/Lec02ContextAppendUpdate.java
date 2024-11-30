package com.fahad.sec13;


import com.fahad.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

/*
Context is an immutable map. We can append additional info!
*/
public class Lec02ContextAppendUpdate {

    public static final Logger log = LoggerFactory.getLogger(Lec02ContextAppendUpdate.class);

    public static void main(String[] args) {
        getWelcomeMessage()
                .contextWrite(ctx->ctx.put("user",ctx.get("user").toString().toUpperCase()))
                //.contextWrite(ctx->Context.of("user","mike"))
                //.contextWrite((ctx->ctx.delete("c")))
                .contextWrite(Context.of("a","b").put("c","d").put("e","f"))
                .contextWrite(Context.of("user","fahad"))
                .subscribe(Util.subscriber());
    }
    private static void append(){
        getWelcomeMessage()
                .contextWrite(Context.of("a","b").put("c","d").put("e","f"))
                .contextWrite(Context.of("user","fahad"))
                .subscribe(Util.subscriber());
    }
    private static Mono<String> getWelcomeMessage(){
        return Mono.deferContextual(ctx->{
            log.info("{}",ctx);
            if(ctx.hasKey("user")){
                return Mono.just("Welcome!! %s ".formatted(ctx.get("user").toString()));
            }
            return Mono.error(new RuntimeException("unauthenticated"));
        });
    }


}
