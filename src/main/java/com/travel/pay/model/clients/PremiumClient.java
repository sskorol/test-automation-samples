package com.travel.pay.model.clients;

public class PremiumClient extends Client {

    public PremiumClient(String firstName, String lastName, int age) {
        super(firstName, lastName, age);
    }

    @Override
    public double getDiscount() {
        return 20;
    }

    public static PremiumClient dummyPremiumClient() {
        return new PremiumClient("Marina", "Sergeevna", 22);
    }
}
