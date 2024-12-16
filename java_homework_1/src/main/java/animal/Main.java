package animal;

import animal.enums.Commands;
import animal.model.Animal;
import animal.util.AnimalBuilder;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean running = true;
        ArrayList<Animal> animals = new ArrayList<>();
        while (running) {
            Commands command = getCommand(in);
            switch (command) {
                case ADD:
                    Animal newAnimal = null;
                    while (newAnimal == null) {
                        newAnimal = AnimalBuilder.createAnimal(in);
                    }
                    animals.add(newAnimal);
                    newAnimal.say();
                    break;
                case LIST:
                    for (Animal animal : animals) {
                        System.out.println(animal.toString());
                    }
                    break;
                case EXIT:
                    in.close();
                    running = false;
                    break;
            }
        }
    }
    private static Commands getCommand(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Введите команду add/list/exit.");
                String inputCommand = scanner.next().trim().toUpperCase();
                return Commands.valueOf(inputCommand);
            } catch (IllegalArgumentException e) {
                System.out.println("Вы ввели неверную команду.");
            }
        }
    }
}
