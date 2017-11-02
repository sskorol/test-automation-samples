package com.cinema.model;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

public class ClubCard {
    private String cardNumber;
    private int bonus;

    public ClubCard() {
        this.cardNumber = randomNumeric(12);
        this.bonus = 0;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getBonus() {
        return bonus;
    }
}
