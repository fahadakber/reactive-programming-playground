package com.fahad.sec07;

import com.fahad.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec08Parallel {
    private static final Logger log = LoggerFactory.getLogger(Lec08Parallel.class);

    public static void main(String[] args) {

        Flux.range(1,10)
                .parallel()// process all tasks in parallel
                //.parallel(3)  // meaning, process 3 tasks in parallel at a time
                .runOn(Schedulers.parallel())
                .map(Lec08Parallel::process)
                //.sequential()
                //.map(i-> i + " a")
                .subscribe(Util.subscriber());

        Util.sleepSeconds(20);

    }

    private static int process(int i){
        log.info("Time Consuming task {} ",i);
        Util.sleepSeconds(1);
        return i * 2;
    }
}
