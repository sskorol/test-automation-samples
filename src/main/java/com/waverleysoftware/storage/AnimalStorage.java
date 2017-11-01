package com.waverleysoftware.storage;

import com.waverleysoftware.model.*;


import java.util.List;
import java.util.ArrayList;

import static com.waverleysoftware.model.CatBreed.*;
import static com.waverleysoftware.model.DogBreed.*;
import static com.waverleysoftware.model.GenderType.*;
import static java.util.Arrays.asList;


public class AnimalStorage {

    private List<Animal> animals;

    public AnimalStorage(){
        animals = new ArrayList(asList(
                new Cat("Alisa", FEMALE, BRITANEC),
                new Cat("Browny", MALE, SIAMSKIY),
                new Dog("Black", MALE, OVCHARKA)
        ));
    }

    public List<Animal> getAnimals() {
        return animals;
    }

}
