package com.fahad.sec09.assignment;

import com.fahad.common.AbstractHttpClient;
import reactor.core.publisher.Mono;



public class ExternalServiceClient extends AbstractHttpClient {

    public Mono<Product> getProduct(int productId) {

        return Mono.zip(productPriceService(productId), productNameService(productId),productReviewService(productId))
                .map(p->new Product(p.getT1(),p.getT2(),p.getT3()));

    }

    private Mono<String> productPriceService(int productId){
        var productPricePath = "/demo05/price/"+productId;
        return getProductDetails(productPricePath);
    }

    private Mono<String> productNameService(int productId){
        var productNamePath = "/demo05/product/"+productId;
         return getProductDetails(productNamePath);
    }

    private Mono<String> productReviewService(int productId){
        var productReviewPath = "/demo05/review/"+productId;
        return getProductDetails(productReviewPath);
    }

    private Mono<String> getProductDetails(String path) {
        return this.httpClient.get()
                .uri(path)
                .responseContent()
                .asString()
                .next();
    }
}
