package io.github.nataliqa;

import io.github.nataliqa.facade.LibraryFacade;
import io.github.nataliqa.model.Category;
import io.vavr.control.Try;

import java.util.Scanner;

import static io.vavr.API.*;
import static org.apache.commons.lang3.math.NumberUtils.toInt;

public class Main {

    private static void showMenu() {
        System.out.println("\n 1.Login\n" +
                "\n 2. Browse book by category\n" +
                "\n 3.Show all books\n" +
                "\n 4.Search book (search type, criteria)\n" +
                "\n 5. Show favourite \n" +
                "\n 6. Close \n" +
                "\n 7. add \n");

    }

    public static void main(String[] args) {

        final LibraryFacade facade = new LibraryFacade();

        try (Scanner consolelog = new Scanner(System.in)) {
            while (true) {
                showMenu();
                Match(toInt(consolelog.nextLine())).of(
                        Case($(1), () -> run(() -> {
                            System.out.print("Enter username:");
                            Try.of(consolelog::nextLine)
                                    .onSuccess(username -> {
                                        System.out.print("Enter password:");
                                        facade.login(username, consolelog.nextLine());

                                    })
                                    .onFailure(System.out::println);
                        })),
                        Case($(2), () -> run(() -> {
                            System.out.print("Enter category(ADVENTURE, IT, SCIENCE):");

                            Try.run(() -> facade.searchByCategory(Category.valueOf(consolelog.nextLine().toUpperCase())));
                        })),
                        Case($(3), () -> run(() -> facade.showAllBooks())),
                        Case($(4), () -> run(() -> {
                            System.out.print("Enter search criteria for title:");
                            Try.run(() -> facade.searchByCriteria(consolelog.nextLine())).onFailure(System.out::println);
                        })),
                        Case($(5), () -> run(() -> facade.showFavouriteBooks())),
                        Case($(7), () -> run(() -> facade.addToFavourite())),
                        Case($(6), () -> run(() -> System.exit(0))));


            }
        }
    }
}







