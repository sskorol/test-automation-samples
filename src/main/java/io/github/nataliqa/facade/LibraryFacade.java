package io.github.nataliqa.facade;

import io.github.nataliqa.model.*;
import one.util.streamex.StreamEx;

import java.awt.print.Book;
import java.util.List;

import static io.github.nataliqa.model.BookType.PHYSICAL_BOOK;
import static io.github.nataliqa.model.Category.ADVENTURE;
import static io.github.nataliqa.model.LanguageType.Russian;
import static java.util.Arrays.asList;
import static java.util.Objects.isNull;

public class LibraryFacade {


    private Library books;
    // private List<CatalogItem> catalog;
    private List<User> users;
    private User currentUser;
    private String searchCriteria;
    private List<Book> catalog;
    private UserSettings userSettings;
    private List<Book> test;
    //public String category1;

    public LibraryFacade() {


        this.books = new Library();
        System.out.println(this.books.getLibrary());

        this.users = asList(
                new User("Dana", "dan123", "123456789", 001),
                new User("Nata", "nata123", "1234567890", 002));


    }


    public void showAllBooks() {

        StreamEx.of(books.getLibrary()).forEach(System.out::println);

    }

    public void showFavouriteBooks() {

        StreamEx.of(userSettings.getFavouriteBooks()).forEach(System.out::println);

    }

    public void addToFavourite() {
        this.userSettings.addToFavourite(new PrintableBook(002, "Henry IV, Part Two (Henry IV, Part II)", "William Shakespeare", 320, ADVENTURE, Russian, PHYSICAL_BOOK));
    }

    public void searchByCriteria(String searchCriteria) {
        StreamEx.of(books.getLibrary())
                .filter(book -> book.getTitle().contains(searchCriteria))
                .forEach(System.out::println);
    }

    public void searchByCategory(Category category) {
        StreamEx.of(books.getLibrary())
                .filter(book -> book.getCategory().equals(category))
                .forEach(System.out::println);

    }


    public void login(final String username, final String password) {
        this.currentUser = StreamEx.of(users)
                .findFirst(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
        System.out.println("Hello " + currentUser.getUsername());
        this.userSettings = new UserSettings(this.currentUser);
    }

    public void verifyUser() {
        if (isNull(currentUser)) {
            throw new IllegalStateException("Unuthorized. Please login to continue action");
        }
    }
}
