package com.fahad.sec02;

import com.fahad.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec06MonoFromCallable {

    private static final Logger log = LoggerFactory.getLogger(Lec06MonoFromCallable.class);

    public static void main(String[] args) {
        var list = List.of(1,2,3);

        Mono.fromCallable(()->sum(list))   // diff between supplier and callable. they both are functional interfaces in
                                            // java. the supplier will not throw exception (check exception) but can throw runtime exception
                                            // the callable can throw exception as part of its method signature
                .subscribe(Util.subscriber());
    }

    private static int sum(List<Integer> list) throws Exception{
        log.info("finding the sum of {} ", list);
        return list.stream().mapToInt(a->a).sum();
    }
}
