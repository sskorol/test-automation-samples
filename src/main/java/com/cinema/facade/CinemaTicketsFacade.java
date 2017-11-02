package com.cinema.facade;

import com.cinema.model.Place;
import com.cinema.model.Session;
import com.cinema.model.Ticket;
import com.cinema.model.User;
import com.cinema.model.cinema.Cinema;
import com.cinema.model.cinema.PlanetaKinoKharkov;
import com.cinema.model.hall.HallType;
import one.util.streamex.StreamEx;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Objects.isNull;

public class CinemaTicketsFacade {
    private final Cinema cinema;
    private final Ticket currentTicket;
    private final List<Session> schedule;
    private final List<User> users;
    private User currentUser;

    public CinemaTicketsFacade() {
        this.cinema = new PlanetaKinoKharkov();
        this.currentTicket = new Ticket();
        this.schedule = cinema.getSessions();
        this.users = asList(
                new User(380501234567L, "1234", "Ivan Ivanov"),
                new User(380671234567L, "12345", "Black Cat")
        );
    }

    public final void auth(long phoneNumber, String password) {
        this.currentUser = StreamEx.of(users)
                .findFirst(user -> user.getPnoneNumber() == phoneNumber
                        && user.getPassword().equals(password))
                .orElseThrow(() -> new IllegalArgumentException("Unable to find user with entered credentials."));
        System.out.println("Welcome to the Planeta Kino, " + currentUser.getUsername());
    }


    public final void allFilms() {
        cinema.films().forEach(System.out::println);
    }

    public final void showDatesOfFilm(final String title) {
        final List<LocalDate> sessionDates = StreamEx.of(schedule)
                .filter(session -> session.getFilmTitle().equalsIgnoreCase(title))
                .map(Session::getDate)
                .sorted()
                .distinct()
                .toList();
        System.out.println("The film is available in: " + sessionDates);
        currentTicket.withFilmTitle(title);
    }

    public final void showAvailableHalls(final LocalDate date) {
        if (!currentTicket.hasFilm()) {
            System.out.println("You need to select the film before choosing hall.");
            return;
        }
        final List<HallType> halls = StreamEx.of(schedule)
                .filter(session -> session.getDate().equals(date)
                        && session.getFilmTitle().equalsIgnoreCase(currentTicket.getFilmTitle()))
                .map(Session::getHallType)
                .distinct()
                .toList();
        System.out.println("The available halls are : " + halls);
        currentTicket.withSessionDate(date);
    }

    public final void showSessionTimesInHall(final HallType hallType) {
        if (!currentTicket.hasFilm() || !currentTicket.hasDate()) {
            System.out.println("You need to select the film and session date before choosing hall.");
            return;
        }

        final LocalDate date = currentTicket.getSessionDate();
        final String title = currentTicket.getFilmTitle();

        final List<LocalTime> sessionsTime = StreamEx.of(schedule)
                .filter(session -> session.getDate().equals(date)
                        && session.getFilmTitle().equalsIgnoreCase(title)
                        && session.getHallType().equals(hallType))
                .map(session -> session.getDateTime().toLocalTime())
                .sorted()
                .toList();
        if (sessionsTime.isEmpty()) {
            System.out.println("Unable to find sessions for film \'" + title + "\' in "
                    + hallType + " hall on selected date " + date + ".");
        } else {
            System.out.println("Available sessions: " + sessionsTime);
            currentTicket.withHallType(hallType);
        }

    }

    public final void showAvailablePlaces(final LocalTime time) {
        if (!currentTicket.hasFilm() || !currentTicket.hasHall() || !currentTicket.hasDate()) {
            System.out.println("You need to select the film, hall and session date before choosing time.");
            return;
        }
        final LocalDate date = currentTicket.getSessionDate();
        final String title = currentTicket.getFilmTitle();
        final HallType hallType = currentTicket.getHallType();
        final List<Place> availablePlaces = availablePlaces(time);

        if (availablePlaces.isEmpty()) {
            System.out.println("Unable to find available places for film \'" + title + "\' in "
                    + hallType + " hall on selected session " + date + " " + time + ".");
        } else {
            System.out.println("Available places are: " + availablePlaces);
            currentTicket.withSessionTime(time);
        }
    }

    public final void bookTicket(int row, int number) {
        checkAuth();

        if (!currentTicket.hasTicketInfo()) {
            System.out.println("You need to select the film, hall, session date and time before booking.");
            return;
        } else if (!StreamEx.of(availablePlaces(currentTicket.getSessionTime()))
                .filter(place -> place.getRow() == row)
                .map(Place::getNumber)
                .has(number)) {
            System.out.println("Selected place number " + number + "on row " + row + "is already booked");
            return;
        }

        StreamEx.of(schedule)
                .findFirst(session -> session.getDate().equals(currentTicket.getSessionDate())
                        && session.getFilmTitle().equalsIgnoreCase(currentTicket.getFilmTitle())
                        && session.getHallType().equals(currentTicket.getHallType())
                        && session.getTime().equals(currentTicket.getSessionTime()))
                .ifPresent(session -> session.bookPlace(row, number));

        System.out.println("You've booked place " + number + " on row " + row
                + " on the film " + currentTicket.getFilmTitle()
                + " (" + currentTicket.getHallType() + ") "
                + "on " + currentTicket.getSessionDate() + " "
                + currentTicket.getSessionTime() + "."
        );
        currentTicket.withPlaceRow(row).withPlaceNumber(number);
    }

    private void checkAuth() {
        if (isNull(currentUser)) {
            throw new IllegalStateException("***ERROR***: Unauthorized.");
        }
    }

    private List<Place> availablePlaces(final LocalTime time) {
        return StreamEx.of(schedule)
                .filter(session -> session.getDate().equals(currentTicket.getSessionDate())
                        && session.getFilmTitle().equalsIgnoreCase(currentTicket.getFilmTitle())
                        && session.getHallType().equals(currentTicket.getHallType())
                        && session.getTime().equals(time))
                .flatMap(session -> session.getAvailablePlaces().stream())
                .toList();
    }
}
