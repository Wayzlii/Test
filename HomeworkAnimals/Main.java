package OTUS.ПодготовительныйКурсJava.Модуль1.Lesson5.HomeworkAnimals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Animal> animals = new ArrayList<>();

        while (true) {
            System.out.print("Введите команду ADD/LIST/EXIT: ");
            String inputCommand = scanner.nextLine().trim().toLowerCase();

            if (Menu.ADD.toString().equalsIgnoreCase(inputCommand)) {
                System.out.print("Введите тип животного cat/dog/duck: ");
                String animalType = scanner.nextLine();

                if (animalType.equals("cat") || animalType.equals("dog") || animalType.equals("duck")) {
                    System.out.print("Введите окрас животного: ");
                    String animalColor = scanner.nextLine();
                    System.out.print("Введите имя животного: ");
                    String animalName = scanner.nextLine();
                    System.out.print("Введите возраст животного: ");
                    int animalAge = scanner.nextInt();
                    System.out.print("Введите вес животного: ");
                    int animalWeight = scanner.nextInt();

                    if (animalType.equals("cat")) {
                        animals.add(new Cat(animalName, animalAge, animalWeight, animalColor));
                    } else if (animalType.equals("dog")) {
                        animals.add(new Dog(animalName, animalAge, animalWeight, animalColor));
                    } else {
                        animals.add(new Duck(animalName, animalAge, animalWeight, animalColor));
                    }

                } else {
                    System.out.print("Недопустимый тип животного.");
                }
            } else if (Menu.LIST.toString().equalsIgnoreCase(inputCommand)) {
                System.out.println(animals);
            } else if (Menu.EXIT.toString().equalsIgnoreCase(inputCommand)) {
                break;
            }
        }
    }
}
