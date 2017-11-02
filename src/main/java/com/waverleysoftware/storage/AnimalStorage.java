package com.waverleysoftware.storage;

import com.waverleysoftware.model.Animal;
import com.waverleysoftware.model.Cat;
import com.waverleysoftware.model.Dog;

import java.util.ArrayList;
import java.util.List;

import static com.waverleysoftware.model.CatBreed.BRITANEC;
import static com.waverleysoftware.model.CatBreed.SIAMSKIY;
import static com.waverleysoftware.model.DogBreed.OVCHARKA;
import static com.waverleysoftware.model.GenderType.FEMALE;
import static com.waverleysoftware.model.GenderType.MALE;
import static java.util.Arrays.asList;


public class AnimalStorage {

    private List<Animal> animals;

    public AnimalStorage() {
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
