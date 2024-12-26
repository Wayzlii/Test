package animal.util;

import animal.commands.Commands;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerUtil {
    public static int inputNumber (Scanner scanner, String title){
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
    public static Commands getCommand(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Введите команду add/list/delete/exit/search_by_type/update.");
                String inputCommand = scanner.next().trim().toUpperCase();
                return Commands.valueOf(inputCommand);
            } catch (IllegalArgumentException e) {
                System.out.println("Вы ввели неверную команду.");
            }
        }
    }
}
