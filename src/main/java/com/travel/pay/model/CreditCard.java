package com.travel.pay.model;

import java.time.YearMonth;

public class CreditCard {

    private final String numberOfCard;
    private final YearMonth expirationDate;
    private final String firstName;
    private final String lastName;
    private final String cvv;
    private double amountOfMoney;

    public CreditCard(String numberOfCard, YearMonth expirationDate, String firstName, String lastName, String cvv, double amountOfMoney) {
        this.numberOfCard = numberOfCard;
        this.expirationDate = expirationDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cvv = cvv;
        this.amountOfMoney = amountOfMoney;
    }

    public String getNumberOfCard() {
        return numberOfCard;
    }

    public YearMonth getExpirationDate() {
        return expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void withdrawMoney(double money) {
        this.amountOfMoney -= money;
    }

    public void enroll(double money) {
        this.amountOfMoney += money;
    }

    public double amountOfMoney() {
        return this.amountOfMoney;
    }
}
