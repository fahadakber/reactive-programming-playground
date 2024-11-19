package com.fahad.sec09;

import com.fahad.common.Util;
import com.fahad.sec09.helper.Kayak;

public class Lec06MergeUseCase {
    public static void main(String[] args) {
        Kayak.getFlights()
                .subscribe(Util.subscriber());

        Util.sleepSeconds(4);
    }
}
