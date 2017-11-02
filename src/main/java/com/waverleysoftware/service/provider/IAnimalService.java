package com.waverleysoftware.service.provider;

import com.waverleysoftware.model.Animal;

import java.util.List;
import java.util.UUID;

public interface IAnimalService {
    List<Animal> getAnimals();
    List<Animal> getAnimalByType(Class<? extends Animal> cls);
    Animal getAnimalByID(UUID uuid);
    Animal deleteAnimal(UUID uuid);

}
