package io.github.nataliqa.model;

import java.util.List;

import static io.github.nataliqa.model.BookType.EBOOK;
import static io.github.nataliqa.model.BookType.PHYSICAL_BOOK;
import static io.github.nataliqa.model.Category.ADVENTURE;
import static io.github.nataliqa.model.Category.IT;
import static io.github.nataliqa.model.LanguageType.English;
import static io.github.nataliqa.model.LanguageType.Russian;
import static java.util.Arrays.asList;

public class Library {

    private List<Book> library;

    public Library() {
        this.library = asList( new eBook(001,"Java for dummies", "Java author", 590, IT, English, EBOOK, "https://archive.org/stream/williamcromptonr00cromiala?ref=ol#page/n3/mode/2up"),
        new PrintableBook(002,"Henry IV, Part Two (Henry IV, Part II)", "William Shakespeare", 320, ADVENTURE, Russian, PHYSICAL_BOOK));
    }

    public List<Book> getLibrary() {
        return library;
    }
}
