package com.cinema.model.cinema;

import com.cinema.model.Film;
import com.cinema.model.Session;
import com.cinema.model.hall.CinemaHall;
import com.cinema.model.hall.HallType;
import one.util.streamex.StreamEx;

import java.util.List;

public interface Cinema {

    CinemaName name();

    String id();

    List<CinemaHall> halls();

    List<Session> getSessions();

    List<HallType> hallTypes();

    StreamEx<Film> films();

}
