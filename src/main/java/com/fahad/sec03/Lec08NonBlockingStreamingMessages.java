package com.fahad.sec03;

import com.fahad.common.Util;
import com.fahad.sec03.client.ExternalServiceClient;

public class Lec08NonBlockingStreamingMessages {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        client.getNames()
                .subscribe(Util.subscriber("sub-1"));

        client.getNames()
                .subscribe(Util.subscriber("sub-2"));

        Util.sleepSeconds(6);
    }
}
