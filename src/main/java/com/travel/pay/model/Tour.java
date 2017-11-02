package com.travel.pay.model;

import java.time.LocalDate;

public class Tour implements Comparable<Tour> {

    private final String nameOfTour;
    private final String city;
    private final Hotel hotel;
    private final LocalDate fromDate;
    private final LocalDate toDate;
    private final double price;
    private boolean isAvailable;
    private final int id;
    private static int count = 1;

    public Tour(String nameOfTour, String city, Hotel hotel, LocalDate fromDate, LocalDate toDate, double price, boolean isAvailable) {
        id = count++;
        this.nameOfTour = nameOfTour;
        this.city = city;
        this.hotel = hotel;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.price = price;
        this.isAvailable = isAvailable;

    }

    public Hotel getHotel() {
        return hotel;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public double getPrice() {
        return price;
    }

    public void book() {
        this.isAvailable = false;
    }

    public int tourId() {
        return id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }


    public String withShortDescription() {
        return id + ". " + "Tour: " + nameOfTour + ", " +
                "city = " + city + ", " +
                "price = " + price + ", " + "\n" +
                "Date: from -> " + fromDate + ", to -> " + toDate + "\n";
    }

    public String withFinalDescription(String type) {
        return id + ". " + "Tour: " + nameOfTour + ", " +
                "city = " + city + ", " +
                "price = " + price + ", " + "\n" +
                "Date: from -> " + fromDate + ", to -> " + toDate +
                " \n" + "Hotel: " + hotel.getName() + "\n" + "Class of the Room: " + hotel.bookTypeRoom(type) +
                "\n" + "Description: " + hotel.getPreference() + "\n";

    }

    @Override
    public String toString() {
        return id + ". " + "Tour: " + nameOfTour + ", " +
                "city = " + city + ", " +
                "price = " + price + ", " + "\n" +
                "Date: from -> " + fromDate + ", to -> " + toDate +
                " \n" + hotel + "\n";
    }


    @Override
    public int compareTo(Tour o) {
        int res = 0;
        double diff = this.price - o.price;
        if (diff != 0) {
            res = diff > 0 ? 1 : -1;
        }
        return res;
    }


}
