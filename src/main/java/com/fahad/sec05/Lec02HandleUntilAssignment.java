package com.fahad.sec05;

import com.fahad.common.Util;
import reactor.core.publisher.Flux;

public class Lec02HandleUntilAssignment {

    // follow the example mention in Lec07FluxGenerateUntil.java and implement the same using handle

    public static void main(String[] args) {
        Flux.<String>generate(sink->sink.next(Util.faker().country().name()))
                .handle((item,sink)->{
                    sink.next(item);
                    if(item.equalsIgnoreCase("pakistan")){
                        sink.complete();
                    }
                })
        .subscribe(Util.subscriber());



    }

}
