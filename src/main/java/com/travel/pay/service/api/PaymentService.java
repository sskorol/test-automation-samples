package com.travel.pay.service.api;

import com.travel.pay.model.Bank;

import java.time.YearMonth;

public interface PaymentService {

    void payment(String firstName, String lastName, String cardNumber, YearMonth expirationDate, String cvv, double priceOfTour);

    Bank partner();

}
