package animal.util;

import animal.model.*;

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
            int age = ScannerUtil.inputNumber(in,"Введите возраст животного.");
            int weight = ScannerUtil.inputNumber(in,"Введите вес животного.");

            switch (type) {
                case "CAT":
                    return new Cat(name, color, age, weight);
                case "DOG":
                    return new Dog(name, color, age, weight);
                case "DUCK":
                    return new Duck(name, color, age, weight);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Не верный тип животного.");
        }
        return null;
    }

}
