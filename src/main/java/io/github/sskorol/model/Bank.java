package io.github.sskorol.model;

import one.util.streamex.StreamEx;

import java.util.List;
import java.util.Optional;

import static io.vavr.API.*;
import static io.vavr.Patterns.$None;
import static io.vavr.Patterns.$Some;
import static io.vavr.control.Option.ofOptional;
import static java.util.Arrays.asList;

public class Bank {

    private final List<ClientInfo> contacts;

    public Bank() {
        this.contacts = asList(ClientInfo.individual(), ClientInfo.company());
    }

    public boolean hasClient(final String number) {
        return StreamEx.of(contacts)
                       .map(ClientInfo::getAccount)
                       .anyMatch(account -> account.getNumber().equals(number)
                               || account.getCreditCard().getNumber().equals(number));
    }

    public PaymentStatus transfer(final Payment payment) {
        final Optional<CreditCard> clientCard = StreamEx.of(contacts)
                                                        .map(ClientInfo::getAccount)
                                                        .map(Account::getCreditCard)
                                                        .findFirst(creditCard -> creditCard.getNumber().equals(payment.getPayerCardNumber())
                                                                && creditCard.getExpirationDate().compareTo(payment.getPayerExpiration()) == 0
                                                                && creditCard.getCvv().equals(payment.getPayerCvv()));

        final Optional<CreditCard> targetCard = StreamEx.of(contacts)
                                                        .map(ClientInfo::getAccount)
                                                        .filter(account -> account.getNumber().equals(payment.getTargetAccountNumber()))
                                                        .map(Account::getCreditCard)
                                                        .findFirst();

        if (!targetCard.isPresent()) {
            return PaymentStatus.INVALID_RECEIVER_CREDIT_CARD;
        }

        return Match(ofOptional(clientCard)).of(
                Case($Some($(val -> val.getBalance() < payment.getAmount())), () -> PaymentStatus.INSUFFICIENT_FUNDS),
                Case($Some($(val -> val.getBalance() >= payment.getAmount())), val -> {
                    val.charge(payment.getAmount());
                    targetCard.ifPresent(receiver -> receiver.enroll(payment.getAmount()));
                    return PaymentStatus.CHARGED;
                }),
                Case($None(), () -> PaymentStatus.INVALID_PAYER_CREDIT_CARD)
        );
    }
}
