package com.travel.pay.model;

import one.util.streamex.StreamEx;

import java.util.List;

import static com.travel.pay.model.Names.*;
import static org.apache.commons.lang3.RandomUtils.nextDouble;

public class TourOperator {
    private final String name;
    private final String locationInCity;
    private final List<Tour> tours;
    private final int id;
    private static int count = 1;

    public TourOperator(String name, String locationInCity) {
        id = count++;
        this.name = name;
        this.locationInCity = locationInCity;
        this.tours = StreamEx.generate(() -> new Tour(getRandomTourName(), getRandomCityName(),
                new Hotel(getRandomHotelName(), new Preference()),
                getRandomDate(), getRandomDate().plusDays(10), nextDouble(1000, 10000), true))
                .limit(10)
                .toList();
    }

    public List<Tour> getTours() {
        return tours;
    }

    public int tourOperatorId() {
        return id;
    }

    @Override
    public String toString() {
        return "TourOperator{" +
                "name='" + name + '\'' +
                ", locationInCity='" + locationInCity + '\'' +
                ", tours=" + tours +
                '}';
    }
}
