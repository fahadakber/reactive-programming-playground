package com.fahad.sec09.applications;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

/*
    just for Demo
    Imagine user-service, as an application, has 2 end points.
    This is a client which represents to call those 2 endpoints (IO requests)
 */

public class UserService {
    private static final Map<String,Integer> userTable = Map.of(
            "sam",1,
            "mike",2,
            "jake",3
    );

    public static Flux<User> getAllUsers(){
        return Flux.fromIterable(userTable.entrySet())
                .map(entry->new User(entry.getValue(), entry.getKey()));
    }

    public static Mono<Integer> getUserId(String userName){
        return Mono.fromSupplier(()->userTable.get(userName));
    }
}
