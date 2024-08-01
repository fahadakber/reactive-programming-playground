package com.fahad.sec03;

import com.fahad.common.Util;
import com.fahad.sec03.assignment3.StockPriceObserver;
import com.fahad.sec03.client.ExternalServiceClient;

public class Lec12Assignment {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        var subscriber = new StockPriceObserver();
        client.getStockPrices()
                .subscribe(subscriber);

        //blocking main thread by adding below line to view the output on console. In real scenario this will not be
        // required as application will be running in server. An alternate way is to use count down latch
        Util.sleepSeconds(20);
    }
}
