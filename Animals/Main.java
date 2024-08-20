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
                inputCommand = scanner.nextLine();
            }

            if (Menu.ADD.toString().equalsIgnoreCase(inputCommand)) {

                System.out.println("Введите тип животного cat/dog/duck: ");
                while (true) {
                    String animalType = scanner.nextLine();
                    if (animalType.equals("cat") || animalType.equals("dog") || animalType.equals("duck")) {

                        System.out.println("Введите окрас животного: ");
                        String animalColor = scanner.nextLine();

                        System.out.println("Введите имя животного: ");
                        String animalName = scanner.nextLine();

                        int animalAge = 0;
                        boolean valueAge = false;
                        while (!valueAge) {
                            try {
                                System.out.println("Введите возраст животного: ");
                                animalAge = scanner.nextInt();
                                if (animalAge>0) {
                                    valueAge = true;
                                } else {
                                    System.out.println("Не верно введен возраст.");
                                }
                            } catch (InputMismatchException a) {
                                System.out.println("Не верно введен возраст.");
                                scanner.nextLine();
                            }
                        }

                        int animalWeight = 0;
                        boolean valueWeight = false;
                        while (!valueWeight) {
                            try {
                                System.out.println("Введите вес животного: ");
                                animalWeight = scanner.nextInt();
                                if (animalWeight>0) {
                                    valueWeight = true;
                                } else {
                                    System.out.println("Не верно введен вес.");
                                }
                            }catch (InputMismatchException a) {
                                System.out.println("Не верно введен вес.");
                                scanner.nextLine();
                            }
                        }

                        if (animalType.equals("cat")) {
                            animals.add(new Cat(animalName, animalAge, animalWeight, animalColor));
                        } else if (animalType.equals("dog")) {
                            animals.add(new Dog(animalName, animalAge, animalWeight, animalColor));
                        } else {
                            animals.add(new Duck(animalName, animalAge, animalWeight, animalColor));
                        } break;

                    } else {
                        System.out.println("Недопустимый тип животного.\nВведите тип животного cat/dog/duck: ");
                    }
                }

            } else if (Menu.LIST.toString().equalsIgnoreCase(inputCommand)) {
                for (int i = 0; i < animals.size(); i++) {
                    System.out.println(animals.get(i));
                }
            } else if (Menu.EXIT.toString().equalsIgnoreCase(inputCommand)) {
                break;
            } else {
                System.out.println("Неверная команда.");
            }
        }
    }
}
