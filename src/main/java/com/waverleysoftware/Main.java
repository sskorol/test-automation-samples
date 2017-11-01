package com.waverleysoftware;

import com.waverleysoftware.facade.ShelterFacade;
import com.waverleysoftware.model.Animal;
import java.util.Scanner;
import static io.vavr.API.*;
import static org.apache.commons.lang3.math.NumberUtils.toInt;

public class Main {

    private static void dogPicture(){
        System.out.println("\n" +
                "     /^-----^\\\n" +
                "     V  o o  V\n" +
                "      |  Y  |\n" +
                "       \\ Q /\n" +
                "       / - \\\n" +
                "       |    \\\n" +
                "       |     \\    )\n" +
                "       || (___\\");
    }

    private static void mainMenu() {
        System.out.println("\nWELCOME TO SHELTER!!!!!\n" +
                "Please, select an option:\n" +
                "1. Display all available animals\n" +
                "2. Filter animals by type\n" +
                "3. Choose animal by id\n" +
                "4. Pick up animal\n" +
                "5. Exit.\n");
    }

    public static void main(String[] args) {

        ShelterFacade facade = new ShelterFacade();
        dogPicture();

        try (Scanner console = new Scanner(System.in)) {
            while (true) {
                mainMenu();

                Match(toInt(console.nextLine())).of(
                        Case($(1), () -> run(() -> {
                            System.out.println(facade.getAnimals());
                        })),

                        Case($(2), () -> run(() -> {
                            System.out.println("Cat or dog ?");
                            System.out.println(facade.getAnimalByType(console.nextLine()));
                        })),

                        Case($(3), () -> run(() -> {
                            System.out.println("Please enter UUID");
                            String userInput = console.nextLine();
                            Animal choosenAnimal = facade.getAnimalById(userInput);
                            if(choosenAnimal == null){
                                System.out.println("No such animal in our shelter");
                            } else {
                                System.out.println(choosenAnimal);
                            }
                        })),
                    
                        Case($(4), () -> run(() -> {
                            System.out.println("Please enter UUID");
                            String uuidStr = console.nextLine();
                            System.out.println("Please enter your first name:");
                            String firstName = console.nextLine();
                            System.out.println("Please enter your last name:");
                            String lastName = console.nextLine();
                            System.out.println("Please enter your age:");
                            int age = toInt(console.nextLine());
                            facade.pickUpAnimal(uuidStr, firstName, lastName, age);
                        })),
                    
                        Case($(5), () -> run(() -> System.exit(0)))
                );
            }
        }
    }
}
