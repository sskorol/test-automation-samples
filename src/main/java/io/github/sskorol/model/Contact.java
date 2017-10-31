package io.github.sskorol.model;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

public class Contact {

    private final String firstName;
    private final String lastName;
    private final String phoneNumber;

    public Contact(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public static Contact dummy() {
        return new Contact(randomAlphabetic(10), randomAlphabetic(10), randomNumeric(12));
    }
}
