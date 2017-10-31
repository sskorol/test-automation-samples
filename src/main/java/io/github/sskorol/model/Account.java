package io.github.sskorol.model;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

public class Account {

    public enum Type {
        INDIVIDUAL_PERSON,
        LEGAL_ENTITY
    }

    private final String number;
    private final CreditCard creditCard;

    public Account(Account.Type type) {
        this.number = type == Type.INDIVIDUAL_PERSON ? randomNumeric(12) : "1234567890";
        this.creditCard = type == Type.INDIVIDUAL_PERSON ? CreditCard.client() : CreditCard.company();
    }

    public String getNumber() {
        return number;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }
}
