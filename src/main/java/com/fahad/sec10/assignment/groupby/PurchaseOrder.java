package com.fahad.sec10.assignment.groupby;

import com.fahad.common.Util;

public record PurchaseOrder(String item, String category, Integer price) {
    // faker has 'commerce()' to create this object with random values
    // commerce.productname, commerce.department
    // interested in only  'Kids' and 'Automotive' categories
    // if u see Automotive orders then add $100
    // if u see Kids order, then add one more purchase order (buy one get 1 free)
    public static PurchaseOrder create() {
        var order = Util.faker().commerce();
        return new PurchaseOrder(order.productName(), order.department(), Util.faker().random().nextInt(10, 100));
    }
}
