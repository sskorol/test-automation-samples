package com.cinema;

import com.cinema.facade.CinemaTicketsFacade;
import com.cinema.model.hall.HallType;
import io.vavr.control.Try;

import java.util.Scanner;

import static com.cinema.utils.DateTimeUtils.formatToLocalDate;
import static com.cinema.utils.DateTimeUtils.formatToLocalTime;
import static io.vavr.API.*;
import static org.apache.commons.lang3.math.NumberUtils.toInt;

public class Main {
    public static final CinemaTicketsFacade facade = new CinemaTicketsFacade();

    private static void printMenu() {
        System.out.println("\n Select an option:\n" +
                "1. Log in.\n" +
                "2. Show all films in the cinema.\n" +
                "3. Show all dates for the selected film.\n" +
                "4. Show halls for the selected date.\n" +
                "5. Show time of sessions in the selected hall.\n" +
                "6. Show available places for the session with selected time.\n" +
                "7. Book place.\n" +
                "8. Exit.");
    }

    private static void auth(Scanner console) {
        System.out.print("Enter your phone number: ");
        Try.of(() -> Long.parseLong(console.nextLine()))
                .onSuccess(phone -> {
                    System.out.print("Type your password: ");
                    Try.of(console::nextLine)
                            .onSuccess(password -> Try.run(() -> facade.auth(phone, password))
                                    .onFailure(ex -> System.out.print("***ERROR*** : " + ex.getMessage())))
                            .onFailure(System.out::println);
                })
                .onFailure(System.out::println);
    }

    public static void main(String[] args) {
        try (Scanner console = new Scanner(System.in)) {
            while (true) {
                printMenu();

                Match(toInt(console.nextLine())).of(
                        Case($(1), () -> run(() -> auth(console))),
                        Case($(2), () -> run(() -> Try.run(facade::allFilms).onFailure(System.out::println))),
                        Case($(3), () -> run(() -> {
                            System.out.print("Type the film title : ");
                            Try.of(console::nextLine)
                                    .onSuccess(facade::showDatesOfFilm)
                                    .onFailure(System.out::println);
                        })),
                        Case($(4), () -> run(() -> {
                            System.out.print("Type the date: ");
                            Try.of(() -> formatToLocalDate(console.nextLine()))
                                    .onSuccess(facade::showAvailableHalls)
                                    .onFailure(System.out::println);
                        })),
                        Case($(5), () -> run(() -> {
                            System.out.print("Type a hall type: ");
                            Try.of(() -> HallType.valueOf(console.nextLine().toUpperCase()))
                                    .onSuccess(facade::showSessionTimesInHall)
                                    .onFailure(System.out::println);


                        })),
                        Case($(6), () -> run(() -> {
                            System.out.print("Type the time: ");
                            Try.of(() -> formatToLocalTime(console.nextLine()))
                                    .onSuccess(facade::showAvailablePlaces)
                                    .onFailure(System.out::println);
                        })),
                        Case($(7), () -> run(() -> {
                            System.out.print("Select a row: ");
                            Try.of(() -> Integer.parseInt(console.nextLine()))
                                    .onSuccess(row -> {
                                        System.out.print("Select a place number: ");
                                        Try.of(() -> Integer.parseInt(console.nextLine()))
                                                .onSuccess(number -> Try.run(() -> facade.bookTicket(row, number))
                                                        .onFailure(ex -> {
                                                            System.out.println(ex.getMessage());
                                                            auth(console);
                                                        }));
                                    })
                                    .onFailure(System.out::println);
                        })),
                        Case($(8), () -> run(() -> System.exit(0))),
                        Case($(), () -> run(() -> System.out.println("Unexpected input.")))
                );
            }
        }
    }
}
