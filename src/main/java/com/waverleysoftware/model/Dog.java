package com.waverleysoftware.model;

import static com.waverleysoftware.model.HomeType.OUTDOOR;

public class Dog extends Animal {

    public Dog(String name, GenderType gender, DogBreed dogBreed) {
        super(name, gender, dogBreed);
        this.homeType = OUTDOOR;

    }

    @Override
    public String toString() {
        return "Dog:" +
                super.toString();
    }
}