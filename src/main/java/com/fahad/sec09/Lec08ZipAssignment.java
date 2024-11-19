package com.fahad.sec09;

import com.fahad.common.Util;
import com.fahad.sec09.assignment.ExternalServiceClient;


public class Lec08ZipAssignment {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();

        for (int i = 1; i <= 5; i++) {
            client.getProduct(i)
                    .subscribe(Util.subscriber());
        }

        Util.sleepSeconds(6);
    }
}
