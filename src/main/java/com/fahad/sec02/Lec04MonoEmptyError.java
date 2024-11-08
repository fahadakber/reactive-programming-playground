package com.fahad.sec02;

import com.fahad.common.Util;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyError {
    public static void main(String[] args) {
        getUsername(2)
                .subscribe(Util.subscriber());
        /*getUsername(3)
                .subscribe(
                       s-> System.out.println(s),
                        err->{} // error handler, if not provided then reactor library will throw a dropped error
                );*/
    }

    private static Mono<String> getUsername(int userId){
        return switch (userId){
            case 1 -> Mono.just("sam");
            case 2 -> Mono.empty();   // do not have the data
            default->Mono.error(new RuntimeException("invalid input"));
        };
    }
}
