package io.github.sskorol.service;

import io.github.sskorol.model.Bank;
import io.github.sskorol.model.Payment;
import io.github.sskorol.model.PaymentStatus;

import java.util.List;

public interface PaymentService {

    List<Bank> partners();

    PaymentStatus transfer(Payment payment);
}
