package com.travel.pay.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;

public class Names {
    private static List<String> citiesNames = new LinkedList<>();
    private static List<String> hotelsNames = new LinkedList<>();
    private static List<String> tourNames = new LinkedList<>();
    private static List<LocalDate> dates = new LinkedList<>();


    private Names() {
        citiesNames = asList("Odessa", "Kirilovka", "Egipt", "Kharkiv", "Kiev", "Lvov", "Turkey", "Tunisia", "Thailand", "Bahamas");
        hotelsNames = asList("Emirates Palace", "Sofitel Grand Sopot", "Mulia Resort", "Waldorf Astoria", "The David Citadel Hotel", "Las Ventanas al Paraiso", "The Westin Palace", "The Ritz-Carlton", "Marina Park", "Sunrise");
        tourNames = asList("VIP", "STANDART", "GOLD", "PREMIUM", "SILVER", "FAMILY", "CHILD", "HARD", "ROMANTIC");
        dates = asList(LocalDate.of(2017, 5, 1), LocalDate.of(2017, 5, 14), LocalDate.of(2017, 6, 6),
                LocalDate.of(2017, 6, 10), LocalDate.of(2017, 7, 11), LocalDate.of(2017, 8, 5));
    }


    static String getRandomCityName() {
        new Names();
        int i = (int) (Math.random() * citiesNames.size());
        return citiesNames.get(i);
    }

    static String getRandomHotelName() {
        new Names();
        final int i = (int) (Math.random() * hotelsNames.size());
        return hotelsNames.get(i);
    }

    static String getRandomTourName() {
        new Names();
        final int i = (int) (Math.random() * tourNames.size());
        return tourNames.get(i);
    }

    static LocalDate getRandomDate() {
        new Names();
        final int i = (int) (Math.random() * dates.size());
        return dates.get(i);
    }
}
