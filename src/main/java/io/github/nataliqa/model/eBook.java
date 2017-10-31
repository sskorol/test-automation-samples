package io.github.nataliqa.model;

public class eBook extends Book {


    private String internetArchive;


    public eBook(int id, String title, String author, int numberOfPages, Category category, LanguageType language, BookType bookType, String internetArchive) {
        super(id, title, author, numberOfPages, category, language, bookType  );
        this.internetArchive = internetArchive;

    }







    public String getInternetArchive() {

        return internetArchive;
    }


}

