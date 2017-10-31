package io.github.sskorol.model;

import java.time.YearMonth;

import static org.apache.commons.lang3.RandomUtils.nextDouble;

public class CreditCard {

    private final String number;
    private final String owner;
    private final YearMonth expirationDate;
    private final String cvv;
    private double balance;

    public CreditCard(String number, String owner, YearMonth expirationDate, String cvv, double balance) {
        this.number = number;
        this.owner = owner;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.balance = balance;
    }

    public static CreditCard client() {
        return new CreditCard("1234567890", "Sergey Korol", YearMonth.of(2020, 11), "123",
                nextDouble(100, 1000));
    }

    public static CreditCard company() {
        return new CreditCard("0987654321", "Ivan Ivanov", YearMonth.of(2022, 9), "123",
                nextDouble(1000, 10000));
    }

    public String getNumber() {
        return number;
    }

    public YearMonth getExpirationDate() {
        return expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void charge(final double amount) {
        this.balance -= amount;
    }

    public void enroll(final double amount) {
        this.balance += amount;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "number='" + number + '\'' +
                ", owner='" + owner + '\'' +
                ", expirationDate=" + expirationDate +
                ", cvv='" + cvv + '\'' +
                '}';
    }
}
