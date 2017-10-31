package com.cinema.model;

import com.cinema.model.hall.CinemaHall;
import com.cinema.model.hall.HallType;
import one.util.streamex.StreamEx;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class Session {
    private final LocalDateTime dateTime;
    private final String filmId;
    private final String filmTitle;
    private final HallType hallType;
    private final List<Place> availablePlaces;
    private final String id;

    public Session(LocalDateTime dateTime, CinemaHall cinemaHall, Film film) {
        this.id = UUID.randomUUID().toString();
        this.dateTime = dateTime;
        this.hallType = cinemaHall.hallType();
        this.availablePlaces = cinemaHall.getPlaces();
        this.filmId = film.getId();
        this.filmTitle = film.getTitle();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public HallType getHallType() {
        return hallType;
    }

    public String getFilmId() {
        return filmId;
    }

    public String getId() {
        return id;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public List<Place> getAvailablePlaces() {
        return StreamEx.of(availablePlaces)
                .filter(Place::isAvailable)
                .toList();
    }

    public void bookPlace(int row, int number) {
        StreamEx.of(getAvailablePlaces())
                .findFirst(place -> place.getRow() == row && place.getNumber() == number)
                .ifPresent(Place::book);
    }

    @Override
    public String toString() {
        return "\nSession: {\n" +
                "date = " + dateTime.toLocalDate() + ",\n" +
                "time = " + dateTime.toLocalTime() + ",\n" +
                "filmTitle = '" + filmTitle + "',\n" +
                "hallType = ' " + hallType + "'" +

                "}";
    }
}
