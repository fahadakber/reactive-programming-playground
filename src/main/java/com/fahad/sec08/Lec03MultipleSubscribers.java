package com.fahad.sec08;

import com.fahad.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;



public class Lec03MultipleSubscribers {
    private static final Logger log = LoggerFactory.getLogger(Lec03MultipleSubscribers.class);

    public static void main(String[] args) {

        var producer = Flux.generate(
                        ()-> 1,
                        (state,sink)->{
                            log.info("generating {} ",state);
                            sink.next(state);
                            return ++state;
                        }
                )
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        producer
                .limitRate(5)
                .publishOn(Schedulers.boundedElastic())
                .map(Lec03MultipleSubscribers::timeConsumingtask)
                .subscribe(Util.subscriber("sub-1"));

        producer
                .take(100)
                .publishOn(Schedulers.boundedElastic())
                .subscribe(Util.subscriber("sub-2"));

        Util.sleepSeconds(60);
    }

    private static int timeConsumingtask(int i){
        log.info("{} ",i);
        Util.sleepSeconds(1);
        return i;
    }


}
