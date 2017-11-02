package com.travel.pay.model.clients;

public class GoldClient extends Client {

    public GoldClient(String firstName, String lastName, int age) {
        super(firstName, lastName, age);
    }

    @Override
    public double getDiscount() {
        return 15;
    }

    public static GoldClient dummyGoldClient() {
        return new GoldClient("Maksim", "Maksimov", 26);
    }


}
