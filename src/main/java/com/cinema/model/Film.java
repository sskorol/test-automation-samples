package com.cinema.model;

import java.util.UUID;

public class Film {
    private String title;
    private String id;

    public Film(String title) {
        this.title = title;
        this.id = UUID.randomUUID().toString();
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Film: " + title + ". \n";
    }
}
