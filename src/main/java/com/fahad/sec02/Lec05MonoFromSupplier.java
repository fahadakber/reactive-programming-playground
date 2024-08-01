package com.fahad.sec02;

import com.fahad.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec05MonoFromSupplier {

    private static final Logger log = LoggerFactory.getLogger(Lec05MonoFromSupplier.class);

    public static void main(String[] args) {
        var list = List.of(1,2,3);
        //Mono.just(sum(list))
        Mono.fromSupplier(()->sum(list))
                .subscribe(Util.subscriber());
    }

    private static int sum(List<Integer> list){
        log.info("finding the sum of {} ", list);
        return list.stream().mapToInt(a->a).sum();
    }
}
