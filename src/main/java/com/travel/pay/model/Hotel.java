package com.travel.pay.model;

import java.util.List;

import static com.travel.pay.model.Hotel.RoomType.*;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.capitalize;

public class Hotel {

    public enum RoomType {

        STANDART("Standard"),
        LUX("Lux"),
        PRESIDENT("President"),
        VIP("Vip");

        private final String name;

        RoomType(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }
    }

    private final String name;
    private final List<String> availableRoomType;
    private final Preference preference;

    public Hotel(String name, Preference preference) {
        this.name = name;
        this.preference = preference;
        this.availableRoomType = createAvailableRoomType();
    }

    public Preference getPreference() {
        return preference;
    }

    public List<String> getAvailableRoomType() {
        return availableRoomType;
    }

    public String bookTypeRoom(String type) {
        return capitalize(type);
    }

    public String getName() {
        return name;
    }

    private List<String> createAvailableRoomType() {
        List<String> res;
        if (preference.getNumberOfStar() == 5) {
            res = asList(STANDART.getName(), LUX.getName(), PRESIDENT.getName(), VIP.getName());
        } else if (preference.getNumberOfStar() == 4) {
            res = asList(STANDART.getName(), LUX.getName(), PRESIDENT.getName());
        } else {
            res = asList(STANDART.getName(), LUX.getName());
        }
        return res;
    }

    @Override
    public String toString() {
        return "Hotel: " + name + ", " + "\n" +
                "Available class of the hotel rooms: " + getAvailableRoomType() + "\n" + "Description: " + preference + "\n";
    }

}
