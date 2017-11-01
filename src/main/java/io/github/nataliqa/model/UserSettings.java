package io.github.nataliqa.model;

import java.util.List;

import static io.github.nataliqa.model.BookType.EBOOK;
import static io.github.nataliqa.model.BookType.PHYSICAL_BOOK;
import static io.github.nataliqa.model.Category.ADVENTURE;
import static io.github.nataliqa.model.Category.IT;
import static io.github.nataliqa.model.LanguageType.English;
import static io.github.nataliqa.model.LanguageType.Russian;
import static java.util.Arrays.asList;

public class UserSettings {

    private List<Book> favouriteBooks;
    private int id;


    public UserSettings(User user) {

        this.id = user.getId();
        this.getUserFavouriteBooks(this.id);
    }

    private void getUserFavouriteBooks(int userId) {

        if (userId == 001) {
            this.favouriteBooks = asList(new eBook(001, "Java for dummies", "Java author", 590, IT, English, EBOOK, "https://archive.org/stream/williamcromptonr00cromiala?ref=ol#page/n3/mode/2up"));
        } else if (userId == 002) {
            this.favouriteBooks = asList(new PrintableBook(002, "Henry IV, Part Two (Henry IV, Part II)", "William Shakespeare", 320, ADVENTURE, Russian, PHYSICAL_BOOK));
        }
    }

    public List<Book> getFavouriteBooks() {
        return favouriteBooks;
    }

    public void addToFavourite(Book book) {
    }
}
