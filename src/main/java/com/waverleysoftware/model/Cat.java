package com.waverleysoftware.model;

import static com.waverleysoftware.model.HomeType.INDOOR;

public class Cat extends Animal {

    public Cat(String name, GenderType gender, CatBreed catBreed) {
        super(name, gender, catBreed);
        this.homeType = INDOOR;
    }

    @Override
    public String toString() {
        return "Cat: " +
                super.toString();
    }
}
