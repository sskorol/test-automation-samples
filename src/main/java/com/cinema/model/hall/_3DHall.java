package com.cinema.model.hall;

import com.cinema.model.Place;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.cinema.model.hall.HallType._3D;

public class _3DHall extends CinemaHall {
    private final String id;
    private List<Place> places;

    public _3DHall() {
        this.id = UUID.randomUUID().toString();
        this.places = generatePlaces().collect(Collectors.toList());
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public HallType hallType() {
        return _3D;
    }

    @Override
    public List<Place> getPlaces() {
        return places;
    }
}
