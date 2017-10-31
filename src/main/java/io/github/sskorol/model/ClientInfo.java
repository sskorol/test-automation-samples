package io.github.sskorol.model;

import static io.github.sskorol.model.Account.Type.INDIVIDUAL_PERSON;
import static io.github.sskorol.model.Account.Type.LEGAL_ENTITY;

public class ClientInfo {

    private final Contact contact;
    private final Account account;

    public ClientInfo(Contact contact, Account account) {
        this.contact = contact;
        this.account = account;
    }

    public static ClientInfo individual() {
        return new ClientInfo(Contact.dummy(), new Account(INDIVIDUAL_PERSON));
    }

    public static ClientInfo company() {
        return new ClientInfo(Contact.dummy(), new Account(LEGAL_ENTITY));
    }

    public Account getAccount() {
        return account;
    }

    public Contact getContact() {
        return contact;
    }
}
