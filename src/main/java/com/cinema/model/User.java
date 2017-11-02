package com.cinema.model;

public class User {
    private long loginPhoneNumber;
    private String password;
    private String username;
    private ClubCard clubCard;

    public User(long phoneNumber, String password, String username) {
        this.loginPhoneNumber = phoneNumber;
        this.password = password;
        this.username = username;
        this.clubCard = new ClubCard();
    }

    public long getPnoneNumber() {
        return loginPhoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public ClubCard getClubCard() {
        return clubCard;
    }

    public String getUsername() {
        return username;
    }
}
