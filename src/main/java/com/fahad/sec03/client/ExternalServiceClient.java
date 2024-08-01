package com.fahad.sec03.client;

import com.fahad.common.AbstractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

    public Flux<String> getNames(){
        return this.httpClient.get()
                .uri("/demo02/name/stream")
                .responseContent()
                .asString();

    }

    public Flux<Integer> getStockPrices(){
        return this.httpClient.get()
                .uri("/demo02/stock/stream")
                .responseContent()
                .asString()
                .map(Integer::parseInt);
    }
}
