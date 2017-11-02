package com.cinema.model.hall;

import com.cinema.model.Place;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.cinema.model.hall.HallType.IMAX;

public class ImaxHall extends CinemaHall {
    private final String id;
    private List<Place> places;

    public ImaxHall() {
        this.id = UUID.randomUUID().toString();
        this.places = generatePlaces().collect(Collectors.toList());
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public HallType hallType() {
        return IMAX;
    }

    @Override
    public List<Place> getPlaces() {
        return places;
    }

    @Override
    public String toString() {
        return "ImaxHall{" +
                "id='" + id + '\'' +
                '}';
    }
}
