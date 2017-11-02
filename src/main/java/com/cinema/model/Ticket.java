package com.cinema.model;

import com.cinema.model.hall.HallType;

import java.time.LocalDate;
import java.time.LocalTime;

import static java.util.Objects.nonNull;

public class Ticket {
    private String filmTitle;
    private HallType hallType;
    private LocalDate sessionDate;
    private LocalTime sessionTime;
    private int placeNumber;
    private int placeRow;

    public Ticket withFilmTitle(final String filmTitle) {
        this.filmTitle = filmTitle;
        return this;
    }

    public Ticket withHallType(final HallType hallType) {
        this.hallType = hallType;
        return this;
    }

    public Ticket withSessionDate(final LocalDate sessionDate) {
        this.sessionDate = sessionDate;
        return this;
    }

    public Ticket withSessionTime(final LocalTime sessionTime) {
        this.sessionTime = sessionTime;
        return this;
    }

    public Ticket withPlaceNumber(final int placeNumber) {
        this.placeNumber = placeNumber;
        return this;
    }

    public Ticket withPlaceRow(final int placeRow) {
        this.placeRow = placeRow;
        return this;
    }

    public boolean hasTicketInfo() {
        return (nonNull(filmTitle) && nonNull(hallType) && nonNull(sessionDate) && nonNull(sessionTime));
    }

    public boolean hasFilm() {
        return (nonNull(filmTitle));
    }

    public boolean hasHall() {
        return (nonNull(hallType));
    }

    public boolean hasDate() {
        return (nonNull(sessionDate));
    }

    public boolean hasTime() {
        return (nonNull(sessionTime));
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public HallType getHallType() {
        return hallType;
    }

    public LocalTime getSessionTime() {
        return sessionTime;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public int getPlaceRow() {
        return placeRow;
    }

}
