package io.github.sskorol.model;

import java.time.YearMonth;

public class Payment {

    private String payerCardNumber;
    private YearMonth payerExpiration;
    private String payerCvv;
    private String description;
    private String targetAccountNumber;
    private double amount;

    public Payment withDescription(final String description) {
        this.description = description;
        return this;
    }

    public Payment withAmount(final double amount) {
        this.amount = amount;
        return this;
    }

    public Payment withTargetAccountNumber(final String targetAccountNumber) {
        this.targetAccountNumber = targetAccountNumber;
        return this;
    }

    public Payment withPayerInfo(final String payerCardNumber, final YearMonth payerExpiration, final String payerCvv) {
        this.payerCardNumber = payerCardNumber;
        this.payerExpiration = payerExpiration;
        this.payerCvv = payerCvv;
        return this;
    }

    public String getPayerCardNumber() {
        return payerCardNumber;
    }

    public YearMonth getPayerExpiration() {
        return payerExpiration;
    }

    public String getPayerCvv() {
        return payerCvv;
    }

    public String getDescription() {
        return description;
    }

    public String getTargetAccountNumber() {
        return targetAccountNumber;
    }

    public double getAmount() {
        return amount;
    }
}
