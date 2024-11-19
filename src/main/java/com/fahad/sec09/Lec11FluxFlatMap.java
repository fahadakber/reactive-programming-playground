package com.fahad.sec09;

import com.fahad.common.Util;
import com.fahad.sec09.applications.Order;
import com.fahad.sec09.applications.OrderService;
import com.fahad.sec09.applications.User;
import com.fahad.sec09.applications.UserService;

/*
    Sequential non-blocking IO calls!
    flatMap is used to flatten the inner publisher / to subscribe to the inner publisher
 */
public class Lec11FluxFlatMap {

    public static void main(String[] args) {
        /*
            get all the orders from the order service
        */

        UserService.getAllUsers()
                .map(User::id)
                //.flatMap(OrderService::getUserOrders,1)
                .flatMap(OrderService::getUserOrders)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);
    }
}
