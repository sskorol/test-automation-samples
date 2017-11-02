package com.waverleysoftware.facade;

import com.waverleysoftware.model.Animal;
import com.waverleysoftware.model.Cat;
import com.waverleysoftware.model.Dog;
import com.waverleysoftware.model.Person;
import com.waverleysoftware.service.implementation.AnimalService;

import java.util.List;
import java.util.UUID;

public class ShelterFacade {
    private final AnimalService animalService;


    public ShelterFacade() {
        this.animalService = new AnimalService();
    }

    public void pickUpAnimal(String uuidStr, String firstName, String lastName, int age) {
        UUID uuid = null;

        try {
            uuid = UUID.fromString(uuidStr);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid uuid");
            return;
        }
        Person person = new Person(firstName, lastName, age);
        Animal deleted = animalService.deleteAnimal(uuid);
        String answer = "You," + person + ", took an animal" + deleted;
        if (deleted == null) {
            answer = "There is no this animal :(";
        }
        System.out.println(answer);
    }

    public List<Animal> getAnimals() {
        return animalService.getAnimals();
    }

    public Animal getAnimalById(String userInput) {
        UUID uuid = null;

        try {
            uuid = UUID.fromString(userInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid uuid");
            return null;
        }
        return animalService.getAnimalByID(uuid);
    }

    public List<Animal> getAnimalByType(String type) {
        if (type.toLowerCase().matches("cat|kisa|kotia")) {
            return animalService.getAnimalByType(Cat.class);
        } else if (type.toLowerCase().matches("dog|sobaka|pes")) {
            return animalService.getAnimalByType(Dog.class);
        } else {
            System.out.println("Unknown animal");
            return null;
        }
    }
}


