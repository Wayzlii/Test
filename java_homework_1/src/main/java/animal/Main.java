package animal;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean running = true;
        ArrayList<Animal> animals = new ArrayList<Animal>();
        while (running) {
            System.out.println("Введите команду add/list/exit.");
            String inputCommand = in.next().trim().toUpperCase();
            Commands command = Commands.valueOf(inputCommand);
            switch (command) {
                case ADD:
                    Animal newAnimal = createAnimal(in);
                    if (newAnimal != null) {
                        animals.add(newAnimal);
                        newAnimal.say();
                    }
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

    private static Animal createAnimal(Scanner in) {
        System.out.println("Введите тип животного, которого хотите добавить : cat/dog/duck.");
        String type = in.next().trim().toUpperCase();
        try {
            //Если введёный тип не найдет в animalType, то вызывается исключение IllegalArgumentException
            AnimalType.valueOf(type);

            System.out.println("Введите имя животного.");
            in.nextLine(); //Непонятная ошибка, пропускает следующий ввод
            String name = in.nextLine();
            System.out.println("Введите какого окраса животное.");
            String color = in.nextLine();
            System.out.println("Введите возраст животного.");
            int age = in.nextInt();
            System.out.println("Введите вес животного.");
            int weight = in.nextInt();

            switch (type) {
                case "CAT":
                    return new Cat(name, age, weight, color);
                case "DOG":
                    return new Dog(name, age, weight, color);
                case "DUCK":
                    return new Duck(name, age, weight, color);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
