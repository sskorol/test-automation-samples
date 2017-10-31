package io.github.nataliqa.model;

public abstract class Book {
    private int id;
    private String title;
    private String author;
    private int numberOfPages;
    private Category language;
    private LanguageType category;
    private BookType bookType;

    public Book(int id, String title, String author, int numberOfPages, Category language, LanguageType category, BookType bookType) {
        this.language = language;
        // this.id = UUID.randomUUID().toString();
        this.id = id;
        this.title = title;
        this.author = author;
        this.numberOfPages = numberOfPages;
        this.category = category;
        this.bookType = bookType;
    }


    @Override
    public String toString() {
        return "Title :" + this.title + "\n"
                + "by" + this.author + "\n"
                + " " + this.bookType + "\n"
                + "Number of pages: " + this.numberOfPages + "\n"
                + "Category:" + this.category + "\n"
                + "Book_id:" + this.id + "\n";
    }

    public BookAction actionForBook() {
        return this.bookType == BookType.EBOOK ? BookAction.READ_ONLINE : BookAction.BUY;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public LanguageType getCategory() {
        return category;
    }

    public BookType getBookType() {
        return bookType;
    }

    public Category getLanguage() {
        return language;
    }
}

