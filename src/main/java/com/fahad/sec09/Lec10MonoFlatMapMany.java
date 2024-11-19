package com.fahad.sec09;

import com.fahad.common.Util;
import com.fahad.sec09.applications.OrderService;
import com.fahad.sec09.applications.UserService;

/*
    Sequential non-blocking IO calls!
    flatMap is used to flatten the inner publisher / to subscribe to the inner publisher
 */
public class Lec10MonoFlatMapMany {

    public static void main(String[] args) {
        /*
            We have a username
            Get all user orders
        */

        UserService.getUserId("mike")
                .flatMapMany(OrderService::getUserOrders)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);
    }
}
