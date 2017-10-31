package io.github.sskorol.service;

import io.github.sskorol.model.Bank;
import io.github.sskorol.model.Payment;
import io.github.sskorol.model.PaymentStatus;
import one.util.streamex.StreamEx;

import java.util.List;

import static java.util.Collections.singletonList;

public class PayPal implements PaymentService {

    @Override
    public List<Bank> partners() {
        return singletonList(new Bank());
    }

    @Override
    public PaymentStatus transfer(final Payment payment) {
        return StreamEx.of(partners())
                       .findFirst(bank -> bank.hasClient(payment.getPayerCardNumber())
                               && bank.hasClient(payment.getTargetAccountNumber()))
                       .map(bank -> bank.transfer(payment))
                       .orElse(PaymentStatus.INVALID_DATA);
    }
}
