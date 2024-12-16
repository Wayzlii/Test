package animal.util;

import animal.enums.AnimalType;
import animal.model.Animal;
import animal.model.Cat;
import animal.model.Dog;
import animal.model.Duck;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AnimalBuilder {
    public static Animal createAnimal(Scanner in) {
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
            int age = inputNumber(in,"Введите возраст животного.");
            int weight = inputNumber(in,"Введите вес животного.");

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
            System.out.println("Во время создания что-то пошло не так. Повторное создание животного.");
        }
        return null;
    }
    private static int inputNumber (Scanner scanner, String title){
        int value = 0;
        boolean valueGreaterThanZero = false;
        while (!valueGreaterThanZero) {
            try {
                System.out.println(title);
                value = scanner.nextInt();
                if (value > 0) {
                    valueGreaterThanZero = true;
                } else {
                    System.out.println("Некорректное значение. " +
                            "Значение должно быть целым числом и больше ноля.");
                }
            } catch (InputMismatchException a) {
                System.out.println("Некорректное значение. " +
                        "Значение должно быть целым числом и больше ноля.");
                scanner.nextLine();
            }
        }
        return value;
    }
}
