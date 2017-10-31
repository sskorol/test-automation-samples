package io.github.sskorol;

import io.github.sskorol.facade.Employee;
import io.github.sskorol.facade.TrainTicketsFacade;
import io.github.sskorol.model.*;
import io.vavr.Tuple;
import io.vavr.control.Try;

import java.util.Scanner;

import static io.github.sskorol.utils.DateTimeUtils.format;
import static io.vavr.API.*;
import static org.apache.commons.lang3.math.NumberUtils.toInt;

public class Main {

    private static void printMenu() {
        System.out.println("\nSelect an option:\n" +
                "1. Login.\n" +
                "2. Print schedule.\n" +
                "3. Choose a train.\n" +
                "4. Choose a carriage.\n" +
                "5. Book place.\n" +
                "6. Buy ticket.\n" +
                "7. Exit.\n");
    }

    public static void main(String[] args) {

        final TrainTicketsFacade facade = new TrainTicketsFacade();

        facade.findEmployee(Employee::isHired);

        try (Scanner console = new Scanner(System.in)) {
            while (true) {
                printMenu();

                Match(toInt(console.nextLine())).of(
                        Case($(1), () -> run(() -> {
                            System.out.print("Type username: ");
                            Try.of(console::nextLine)
                               .onSuccess(name -> {
                                   System.out.print("Type password: ");
                                   facade.loginWith(name, console.nextLine());
                               })
                               .onFailure(System.out::println);
                        })),
                        Case($(2), () -> run(() -> Try.run(facade::showSchedule).onFailure(System.out::println))),
                        Case($(3), () -> run(() -> {
                            System.out.print("Type train number: ");
                            Try.run(() -> facade.showCarriages(console.nextLine())).onFailure(System.out::println);
                        })),
                        Case($(4), () -> run(() -> {
                            System.out.print("Type carriage class: ");
                            Try.of((() -> Clazz.valueOf(console.nextLine().toUpperCase())))
                               .onSuccess(clazz -> {
                                   System.out.print("Type carriage number: ");
                                   facade.showAvailablePlaces(clazz, toInt(console.nextLine()));
                               })
                               .onFailure(ex -> System.out.println("Invalid data specified."));
                        })),
                        Case($(5), () -> run(() -> {
                            System.out.print("Type place number: ");
                            Try.of((() -> toInt(console.nextLine())))
                               .onSuccess(placeNumber -> {
                                   System.out.print("Type passenger name: ");
                                   facade.bookPlace(placeNumber, console.nextLine());
                               })
                               .onFailure(System.out::println);
                        })),
                        Case($(6), () -> run(() -> {
                                System.out.print("Type card number: ");
                                Try.of(console::nextLine)
                                   .map(number -> {
                                       System.out.print("Type expiration date: ");
                                       return Tuple.of(number, format(console.nextLine()));
                                   })
                                   .onSuccess(pair -> {
                                       System.out.print("Type cvv: ");
                                       facade.buyTicket(pair._1, pair._2, console.nextLine());
                                   })
                                   .onFailure(System.out::println);
                                })),
                        Case($(7), () -> run(() -> System.exit(0))),
                        Case($(), () -> run(() -> System.out.println("There's no command found matching your input.")))
                );
            }
        }
    }
}
