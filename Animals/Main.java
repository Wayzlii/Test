package Homework.Animals;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Animal> animals = new ArrayList<>();

        while (true) {
            String inputCommand = "";
            System.out.println("Введите команду ADD/LIST/EXIT: ");
            while (inputCommand.length() == 0) {
                inputCommand = scanner.nextLine().trim().toUpperCase();
            }

            if (Menu.ADD.toString().equalsIgnoreCase(inputCommand)) {
                System.out.println("Введите тип животного cat/dog/duck: ");

                while (true) {
                    String animalType = scanner.nextLine().trim().toLowerCase();

                    if (animalType.equals("cat") || animalType.equals("dog") || animalType.equals("duck")) {
                        System.out.println("Введите окрас животного: ");
                        String animalColor = scanner.nextLine().trim();

                        System.out.println("Введите имя животного: ");
                        String animalName = scanner.nextLine().trim();

                        int animalAge = inputNumber(scanner,"Введите возрраст животного.");

                        int animalWeight = inputNumber(scanner,"Введите вес животного.");

                        AnimalBuilder animalBuilder = new AnimalBuilder();
                        animals.add(animalBuilder.animalBuilder(animalType,
                                animalName,
                                animalAge,
                                animalWeight,
                                animalColor));
                        break;

                    } else {
                        System.out.println("Недопустимый тип животного.\nВведите тип животного cat/dog/duck: ");
                    }
                }

            } else if (Menu.LIST.toString().equalsIgnoreCase(inputCommand)) {
                for (Animal animal : animals) {
                    System.out.println(animal);
                }
            } else if (Menu.EXIT.toString().equalsIgnoreCase(inputCommand)) {
                break;
            } else {
                System.out.println("Неверная команда.");
            }
        }
    }
    static int inputNumber(Scanner scanner, String zagolovok) {
        int value = 0;
        boolean valueGreaterThanZero = false;
        while (!valueGreaterThanZero) {
            try {
                System.out.println(zagolovok);
                value = scanner.nextInt();
                if (value>0) {
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
        } return value;
    }
}