package com.travel.pay;

import com.travel.pay.facade.TravelTourFacade;
import io.vavr.Tuple;
import io.vavr.control.Try;

import java.util.Scanner;

import static io.vavr.API.*;
import static org.apache.commons.lang3.math.NumberUtils.toInt;

class Main {

    private static void printMenu() {
        System.out.println("" +
                "1. Show accessible tours for determined date;\n" +
                "2. Show tours on floor price;\n" +
                "3. Choose tour by id and to look up detailed information;\n" +
                "4. Select class of room;\n" +
                "5. Book tour;\n" +
                "6. Buy tour;\n" +
                "7. Exit;\n");
    }

    public static void main(String[] args) {

        TravelTourFacade facade = new TravelTourFacade();

        try (Scanner console = new Scanner(System.in)) {
            while (true) {
                printMenu();

                Match(toInt(console.nextLine())).of(
                        Case($(1), () -> run(() -> {
                            System.out.println("Input month and date(example: 2017/5/1): ");
                            Try.of(console::nextLine)
                                    .onSuccess(facade::getToursByDate)
                                    .onFailure(System.out::println);
                        })),
                        Case($(2), () -> run(() -> {
                            System.out.println("Input condition for price: ");
                            Try.of(console::nextLine)
                                    .onSuccess(price -> facade.getToursByPrice(Double.parseDouble(price)))
                                    .onFailure(System.out::println);
                        })),
                        Case($(3), () -> run(() -> {
                            System.out.println("Input tour Id for more details: ");
                            Try.of(console::nextLine)
                                    .onSuccess(id -> facade.selectTour(Integer.parseInt(id)))
                                    .onFailure(System.out::println);
                        })),
                        Case($(4), () -> run(() -> {
                            System.out.println("Select one of available the class of room: ");
                            Try.run(facade::getCurrentRoomType);
                            Try.of(console::nextLine)
                                    .onSuccess(facade::selectRoomType)
                                    .onFailure(System.out::println);
                        })),
                        Case($(5), () -> run(() -> {
                            System.out.println("Type your username: ");
                            Try.of(console::nextLine)
                                    .onSuccess(facade::bookTour)
                                    .onFailure(System.out::println);
                        })),

                        Case($(6), () -> run(() -> {
                            System.out.println("Type card owner firs name: ");
                            Try.of(console::nextLine)
                                    .map(ownerNames -> {
                                        System.out.println("Type card owner last name: ");
                                        String lastName = console.nextLine();
                                        System.out.println("Type card number: ");
                                        String number = console.nextLine();
                                        System.out.println("Type expiration date: ");
                                        return Tuple.of(ownerNames, lastName, number, console.nextLine());

                                    })
                                    .onSuccess(pair -> {
                                        System.out.println("Type cvv number: ");
                                        facade.buyTour(pair._1, pair._2, pair._3, pair._4, console.nextLine());
                                    })
                                    .onFailure(System.out::println);
                        })),

                        /*Case($(6), () -> run(() -> {
                            System.out.println("Type card number: ");
                            Try.of(console::nextLine)
                                    .map(number -> {
                                        System.out.println("Type expiration date: ");
                                        return Tuple.of(number, console.nextLine());
                                    })
                                    .onSuccess(pair -> {
                                        System.out.println("Type cvv number: ");
                                        facade.buyTour(pair._1, pair._2, console.nextLine());
                                    })
                                    .onFailure(System.out::println);
                        })),*/
                        Case($(7), () -> run(() -> System.exit(0))),
                        Case($(), () -> run(() -> System.out.println("There's no command"))));
            }
        }
    }

}
