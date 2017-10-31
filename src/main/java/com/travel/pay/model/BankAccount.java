package com.travel.pay.model;

public class BankAccount {

    private final int number;
    private static int counter = 1;
    private final CreditCard card;


    public BankAccount(CreditCard card) {
        number = counter++;
        this.card = card;
    }

    public CreditCard getCard() {
        return card;
    }
}
