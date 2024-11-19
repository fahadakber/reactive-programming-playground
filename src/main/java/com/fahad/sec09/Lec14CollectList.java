package com.fahad.sec09;

import com.fahad.common.Util;
import reactor.core.publisher.Flux;


/*
To collect the items received via Flux. Assuming we will have finite items!
 */
public class Lec14CollectList {

    public static void main(String[] args) {

        Flux.range(1,10)
                .collectList()  // it will collect all items from Flux and form a list
                //.collectSortedList()
                .subscribe(Util.subscriber());


    }
}
