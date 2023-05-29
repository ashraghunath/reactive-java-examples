package io.javabrains.reactiveworkshop;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

import java.io.IOException;

public class Exercise5 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFlux and ReactiveSources.userMono()

        // Subscribe to a flux using the error and completion hooks
        ReactiveSources.intNumbersFlux().subscribe(
                number -> System.out.println(number),
                err -> System.out.println(err),
                () -> System.out.println("Complete")
        );

        // Subscribe to a flux using an implementation of BaseSubscriber
        //Instead of passing individual lambdas you can pass a class instance which knows what to do.
//        But if you do this you need to ask for the item using request method (Backpressure control)
        ReactiveSources.intNumbersFlux().subscribe(new MySubscriber<>());

        System.out.println("Press a key to end");
        System.in.read();
    }

}

/**
 * @author Ashwin
 */

class MySubscriber<T> extends BaseSubscriber<T> {

    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        super.hookOnSubscribe(subscription);
        System.out.println("Subscription happened");
        //Means I am okay with handling 1 onSubscribe
        request(1);
    }

    @Override
    protected void hookOnNext(T value) {
        super.hookOnNext(value);
        System.out.println(value.toString() + " received");
        //Ready for one more onNext every time I handle 1
        request(1);
    }
}