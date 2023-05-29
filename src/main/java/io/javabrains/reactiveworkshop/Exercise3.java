package io.javabrains.reactiveworkshop;

import java.io.IOException;
import java.util.List;

public class Exercise3 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFlux()

        // Get all numbers in the ReactiveSources.intNumbersFlux stream
        // into a List and print the list and its size
        List<Integer> integers = ReactiveSources.intNumbersFlux().toStream().toList(); //It becomes blocking here since we use toStream()
        System.out.println("List is " + integers);
        System.out.println("Size is " + integers.size());

        System.out.println("Press a key to end");
        System.in.read();
    }

}
