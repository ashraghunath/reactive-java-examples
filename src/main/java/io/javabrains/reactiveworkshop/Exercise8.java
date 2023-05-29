package io.javabrains.reactiveworkshop;

import reactor.core.publisher.Flux;

import java.io.IOException;

public class Exercise8 {


    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFluxWithException()

        // Print values from intNumbersFluxWithException and print a message when error happens
        //Swallow the error here
        //Note: new flux is created on error
        ReactiveSources.intNumbersFluxWithException().subscribe(
                e -> System.out.println(e),
                err -> System.out.println("error occurred " + err.getMessage())
        );

        //Error is not swallowed
        ReactiveSources.intNumbersFluxWithException()
                .doOnError(e -> System.out.println("error!"))
                .subscribe(e -> System.out.println(e));

        // Print values from intNumbersFluxWithException and continue on errors
        ReactiveSources.intNumbersFluxWithException()
                .onErrorContinue((e, item) -> System.out.println("error!"))
                .subscribe(e -> System.out.println(e));

        // Print values from intNumbersFluxWithException and when errors
        // happen, replace with a fallback sequence of -1 and -2
        //Creates a new flux when error occurs
        ReactiveSources.intNumbersFluxWithException()
                .onErrorResume(err -> Flux.just(-1, -2))
                .subscribe(e -> System.out.println(e));


        System.out.println("Press a key to end");
        System.in.read();
    }

}
