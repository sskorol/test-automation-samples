package com.cinema.model;

public class Place {
    private static int count = 1;
    private final int id;
    private PlaceType placeType;
    private int row;
    private int number;
    private boolean isAvailable;

    public Place(PlaceType placeType, int row, int number) {
        this.id = count++;
        this.placeType = placeType;
        this.isAvailable = true;
        this.row = row;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Place{" +
                "placeType=" + placeType +
                ", row=" + row +
                ", number=" + number +
                ", isAvailable=" + isAvailable +
                ", id=" + id +
                '}';
    }

    public PlaceType getPlaceType() {
        return placeType;
    }

    public int getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public final void book() {
        this.isAvailable = false;
    }
}

