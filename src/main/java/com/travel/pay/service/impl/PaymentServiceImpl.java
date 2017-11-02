package com.travel.pay.service.impl;

import com.travel.pay.model.Bank;
import com.travel.pay.service.api.PaymentService;

import java.time.YearMonth;

import static com.travel.pay.model.PaymentStatus.*;
import static io.vavr.API.*;

public class PaymentServiceImpl implements PaymentService {


    public PaymentServiceImpl() {
    }

    @Override
    public Bank partner() {
        return new Bank();
    }


    @Override
    public void payment(String firstName, String lastName, String cardNumber, YearMonth expirationDate, String cvv, double priceOfTour) {
        Match(partner().checkAccounts(firstName, lastName, cardNumber, expirationDate, cvv, priceOfTour)).of(
                Case($(CHARGED), () -> run(() -> System.out.println("Using the following card info: " + cardNumber + ", " + cvv + "\n" + "Bought the following Tour: "))),
                Case($(INSUFFICIENT_FUNDS), () -> run(() -> System.out.println("Insufficient funding for Credit Card. Can't buy the following Tour: "))),
                Case($(INVALID_DATA), () -> run(() -> System.out.println("Invalid data. Can't buy the following Tour: "))),
                Case($(), () -> run(() -> System.out.println("Invalid data of Client Credit Card. Can't buy the following Tour: ")))
        );
    }
}