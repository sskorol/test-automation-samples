package com.cinema.model.cinema;

import com.cinema.model.Film;
import com.cinema.model.Session;
import com.cinema.model.hall.CinemaHall;
import com.cinema.model.hall.HallType;
import com.cinema.model.hall.ImaxHall;
import com.cinema.model.hall._3DHall;
import one.util.streamex.StreamEx;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static com.cinema.model.hall.HallType.IMAX;
import static com.cinema.model.hall.HallType._3D;
import static com.cinema.utils.RandomUtils.random;
import static java.util.Arrays.asList;

public class PlanetaKinoKharkov implements Cinema {

    private final List<CinemaHall> halls;
    private final List<Session> sessions;
    private final List<Film> films;
    private final AtomicInteger mins = new AtomicInteger(0);

    public PlanetaKinoKharkov() {
        this.films = asList(
                new Film("Matrix"),
                new Film("It"),
                new Film("Garry Potter"));
        this.halls = generateCinemaHalls();
        this.sessions = generateSessions();
    }

    @Override
    public CinemaName name() {
        return CinemaName.PLANETA_KINO_KHARKOV;
    }

    @Override
    public String id() {
        return UUID.randomUUID().toString();
    }

    @Override
    public List<CinemaHall> halls() {
        return halls;
    }

    @Override
    public List<Session> getSessions() {
        return sessions;
    }

    @Override
    public List<HallType> hallTypes() {
        return asList(IMAX, _3D);
    }

    public StreamEx<Film> films() {
        return StreamEx.of(films);
    }

    private List<Session> generateSessions() {
        final List<LocalDateTime> sessionsDateTime = asList(
                LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)),
                LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(10, 0)),
                LocalDateTime.of(LocalDate.now().plusDays(2), LocalTime.of(10, 0)),
                LocalDateTime.of(LocalDate.now().plusDays(3), LocalTime.of(10, 0)),
                LocalDateTime.of(LocalDate.now().plusDays(4), LocalTime.of(10, 0))
        );
        final AtomicInteger hour = new AtomicInteger(0);

        return StreamEx.iterate(0, i -> i + 1).limit(sessionsDateTime.size() - 1)
                .flatMap(i -> StreamEx.generate(() -> new Session(sessionsDateTime.get(i).plusHours(hour.incrementAndGet()).plusMinutes(seqMins()),
                        halls.get(random(3)), films.get(random(3)))).limit(10))
                .toList();
    }

    private List<CinemaHall> generateCinemaHalls() {
        return StreamEx.generate(ImaxHall::new).limit(1).select(CinemaHall.class)
                .append(StreamEx.generate(_3DHall::new).limit(2).select(CinemaHall.class))
                .toList();
    }

    private int seqMins() {
        final int currentMins = mins.get() <= 60 ? mins.addAndGet(15) : 0;
        if (currentMins == 0) {
            mins.set(0);
        }
        return currentMins;
    }
}