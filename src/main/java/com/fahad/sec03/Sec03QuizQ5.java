package com.fahad.sec03;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

public class Sec03QuizQ5 {
    public static void main(String[] args) {
        // output is no items will be printed becuase subscriber has to request for items
        Flux.range(1, 10)
                .subscribe(new Subscriber<Integer>() {

                    private Subscription subscription;

                    @Override
                    public void onSubscribe(Subscription subscription) {
                        this.subscription = subscription;
                        // if below line is uncomment then it will print first 3 values from given range
                        //subscription.request(3);
                    }

                    @Override
                    public void onNext(Integer next) {
                        System.out.println(next);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("done");
                    }

                });
    }
}
