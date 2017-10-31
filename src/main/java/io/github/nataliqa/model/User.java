package io.github.nataliqa.model;

public class User {
    private String name;
    private String username;
    private String password;
    private int id;

    public User(String name, String username, String password, int id) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }
}
