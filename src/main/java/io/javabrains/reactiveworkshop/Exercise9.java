package io.javabrains.reactiveworkshop;

import java.io.IOException;

public class Exercise9 {


    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFlux()

        // Print size of intNumbersFlux after the last item returns
//        count returns a mono of 1 and then we subscribe to it
        ReactiveSources.intNumbersFlux().count().subscribe(System.out::println);

        // Collect all items of intNumbersFlux into a single list and print it
        ReactiveSources.intNumbersFlux().collectList().subscribe(System.out::println);

        // Transform to a sequence of sums of adjacent two numbers
        //1+2, 3+4.....
        //buffer(2) means every 2 event in flux results in 1 event in output flux
        ReactiveSources.intNumbersFlux().buffer(2)
                .map(list -> list.get(0) + list.get(1))
                .subscribe(System.out::println);

        System.out.println("Press a key to end");
        System.in.read();
    }

}
