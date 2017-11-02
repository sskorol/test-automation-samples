package com.travel.pay.model;

import com.travel.pay.model.clients.Client;
import com.travel.pay.model.clients.GoldClient;
import com.travel.pay.model.clients.PremiumClient;
import one.util.streamex.StreamEx;

import java.time.YearMonth;
import java.util.*;
import java.util.List;
import java.util.Map;

import static com.travel.pay.model.clients.GoldClient.dummyGoldClient;
import static com.travel.pay.model.clients.PremiumClient.dummyPremiumClient;
import static io.vavr.API.*;
import static io.vavr.Patterns.$None;
import static io.vavr.Patterns.$Some;
import static io.vavr.control.Option.ofOptional;
import static java.util.Arrays.asList;


public class Bank {
    private final Map<Integer, BankAccount> clientAccount = new HashMap<>();
    private final Map<Integer, BankAccount> tourOperatorAccount = new HashMap<>();


    public Bank() {
    }

    private void createClientBankAccount() {
        clientAccount.put(dummyGoldClient().clientId(), new BankAccount(
                new CreditCard("111111111111", YearMonth.of(2020, 06),
                        dummyGoldClient().getFirstName(),
                        dummyGoldClient().getLastName(), "111", setMoney(dummyGoldClient()))));
        clientAccount.put(dummyPremiumClient().clientId(), new BankAccount(
                new CreditCard("222222222222", YearMonth.of(2021, 07),
                        dummyPremiumClient().getFirstName(),
                        dummyPremiumClient().getLastName(), "222", setMoney(dummyPremiumClient()))));
    }

    private void createTourOperatorBankAccount() {
        TourOperator tourOperator = new TourOperator("Bird", "Kharkiv");
        tourOperatorAccount.computeIfAbsent(tourOperator.tourOperatorId(), k -> new BankAccount(
                new CreditCard("333333333333", YearMonth.of(2022, 9), "Tour", "Tourovich", "222", 10000)));
    }


    private double setMoney(Client client) {
        double res = 5000;

        if (client.getClass() == GoldClient.class) {
            res = 20000;
        } else if (client.getClass() == PremiumClient.class) {
            res = 40000;
        }
        return res;
    }

    private Client findClient(String firstName, String lastName) {
        List<Client> clients = asList(dummyGoldClient(), dummyPremiumClient());

        final Optional<Client> resClient = StreamEx.of(clients)
                .filter(client -> client.getFirstName().equals(firstName)
                        && client.getLastName().equals(lastName))
                .findFirst();

        if (!resClient.isPresent()) {
            throw new NoSuchElementException("No client found");
        }
        return resClient.get();
    }

    public PaymentStatus checkAccounts(String firstName, String lastName, String cardNumber, YearMonth expirationDate, String cvv, double price) {
        createClientBankAccount();
        createTourOperatorBankAccount();

        Optional<CreditCard> tourOperatorCard = StreamEx.of(tourOperatorAccount.values())
                .map(BankAccount::getCard)
                .findFirst();

        Optional<CreditCard> clientCard = StreamEx.of(clientAccount.values())
                .map(BankAccount::getCard)
                .filter(card -> card.getNumberOfCard().equals(cardNumber)
                        && card.getCvv().equals(cvv)
                        && card.getExpirationDate().equals(expirationDate))
                .findFirst();

        if (!tourOperatorCard.isPresent()) {
            return PaymentStatus.INVALID_RECEIVER_CREDIT_CARD;
        }
        return Match(ofOptional(clientCard)).of(
                Case($Some($(card -> card.amountOfMoney() < price)), () -> PaymentStatus.INSUFFICIENT_FUNDS),
                Case($Some($(card -> card.amountOfMoney() >= price)), card -> {
                    double discount = (findClient(firstName, lastName).getDiscount() / 100) * price;
                    card.withdrawMoney(price - discount);
                    tourOperatorCard.ifPresent(receiver -> receiver.enroll(price - discount));
                    return PaymentStatus.CHARGED;
                }),
                Case($None(), () -> PaymentStatus.INVALID_PAYER_CREDIT_CARD)
        );
    }
}
