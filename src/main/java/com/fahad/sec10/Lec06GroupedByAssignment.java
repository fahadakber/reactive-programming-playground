package com.fahad.sec10;


import com.fahad.common.Util;

import com.fahad.sec10.assignment.groupby.OrderProcessingService;
import com.fahad.sec10.assignment.groupby.PurchaseOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;


import java.time.Duration;

public class Lec06GroupedByAssignment {
    private static final Logger log = LoggerFactory.getLogger(Lec06GroupedByAssignment.class);
    public static void main(String[] args) {

        orderStream()
                .filter(OrderProcessingService.canProcess())
                .groupBy(PurchaseOrder::category)
                .flatMap(gf->gf.transform(OrderProcessingService.getProcessor(gf.key())))
                .subscribe(Util.subscriber());


        Util.sleepSeconds(60);
    }


    private static Flux<PurchaseOrder> orderStream(){
        return Flux.interval(Duration.ofMillis(200))
                .map(i->PurchaseOrder.create());
    }
}
