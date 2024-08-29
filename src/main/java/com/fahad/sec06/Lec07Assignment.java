package com.fahad.sec06;


import com.fahad.common.Util;
import com.fahad.sec06.assignment.ExternalServiceClient;
import com.fahad.sec06.assignment.InventoryService;
import com.fahad.sec06.assignment.RevenueService;

public class Lec07Assignment {

    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        var inventoryService = new InventoryService();
        var revenueService = new RevenueService();

        client.orderStream().subscribe(inventoryService::consume);
        client.orderStream().subscribe(revenueService::consume);

        inventoryService.stream()
                .subscribe(Util.subscriber("inventory"));

        inventoryService.stream()
                .subscribe(Util.subscriber("revenue"));


        Util.sleepSeconds(30);
    }

}
