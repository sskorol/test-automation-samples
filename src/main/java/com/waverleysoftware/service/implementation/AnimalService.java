package com.waverleysoftware.service.implementation;

import com.waverleysoftware.model.Animal;
import com.waverleysoftware.service.provider.IAnimalService;
import com.waverleysoftware.storage.AnimalStorage;
import one.util.streamex.StreamEx;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AnimalService implements IAnimalService {

    private List<Animal> animals;

    public AnimalService() {
        animals = new AnimalStorage().getAnimals();
    }


    @Override
    public List<Animal> getAnimals() {
        return StreamEx.of(animals)
                .toList();
    }

    @Override
    public List<Animal> getAnimalByType(Class<? extends Animal> unknownAnimal) {
        return StreamEx.of(animals)
                .filter(animalInstance -> unknownAnimal.isInstance(animalInstance))
                .toList();
    }

    @Override
    public Animal getAnimalByID(UUID uuid) {
        Optional<Animal> opt = StreamEx.of(animals)
                .findFirst(animal -> animal.getId().equals(uuid));
        return opt.isPresent() ? opt.get() : null;
    }

    @Override
    public Animal deleteAnimal(UUID uuid) {

        Animal result = null;
        for (Animal a : animals) {
            if (uuid.equals(a.getId())) {
                result = a;
                break;
            }
        }

        if (result != null) {
            animals.remove(result);
            return result;
        } else {
            return null;
        }
    }

}
