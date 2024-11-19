package com.fahad.sec09;

import com.fahad.common.Util;
import com.fahad.sec09.applications.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;


/*
    Get all users and build 1 object as shown here
    record UserInformation(Integer userId, String username, Integer balance, List<Order> orders{}
*/
public class Lec16Assignment {

    record UserInformation(Integer userId, String userName, Integer balance, List<Order> orders){}

    private static final Logger log = LoggerFactory.getLogger(Lec16Assignment.class);

    public static void main(String[] args) {

        UserService.getAllUsers()
                .flatMap(Lec16Assignment::getUserInformation)
                .subscribe(Util.subscriber());


        Util.sleepSeconds(3);

    }

    private static Mono<UserInformation> getUserInformation(User user){
        return Mono.zip(
                PaymentService.getUserBalance(user.id()),
                OrderService.getUserOrders(user.id()).collectList()
        )
                .map(t->new UserInformation(user.id(),user.userName(),t.getT1(),t.getT2()));
    }

}
