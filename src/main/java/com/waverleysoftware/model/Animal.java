package com.waverleysoftware.model;

import com.waverleysoftware.behaviour.Breed;
import com.waverleysoftware.behaviour.Eatable;

import java.util.ArrayList;
import java.util.UUID;

public abstract class Animal {

    protected HomeType homeType;
    private String name;
    private GenderType gender;
    private Breed breed;
    private ArrayList<Eatable> eatableList;
    private UUID id;

    public Animal(String name, GenderType gender, Breed breed) {
        this.name = name;
        this.gender = gender;
        this.breed = breed;
        this.id = UUID.randomUUID();
        this.eatableList = new ArrayList<Eatable>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public GenderType getGender() {
        return this.gender;
    }

    public void setGender(GenderType newGender) {
        this.gender = newGender;
    }

    public HomeType getHomeType() {
        return this.homeType;
    }

    public void setHomeType(HomeType newHomeType) {
        this.homeType = newHomeType;
    }

    public int foodCount() {
        return eatableList.size();
    }

    public void eat(Eatable food) {
        eatableList.add(food);
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", gender=" + gender +
                ", homeType=" + homeType +
                ",breed = " + breed +
                ", eatableList=" + eatableList +
                ",id = " + id;
    }
}