package com.travel.pay.model.clients;

import com.travel.pay.model.CreditCard;

public abstract class Client {

    private final int id;
    private static int counter = 1;
    private final String firstName;
    private final String lastName;
    private final int age;
    private CreditCard card;

    Client(String firstName, String lastName, int age) {
        id = counter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.card = card;
    }

    public abstract double getDiscount();

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int clientId() {
        return id;
    }

}
