import animal.*;
import commands.*;
import config.ConnectionSql;
import config.PropertiesHolder;
import config.TableName;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {

        Scanner in = new Scanner(System.in);
        boolean running = true;

        ConnectionSql connectionSql = new ConnectionSql();
        PropertiesHolder propertiesHolder = new PropertiesHolder();
        Connection connection = connectionSql.getConnection(propertiesHolder.getProperties());

        TableName.setTableName(propertiesHolder.getProperties());

        AddAnimal add = new AddAnimal();
        DeleteAnimal delete = new DeleteAnimal();
        ListAnimal list = new ListAnimal();
        SearchByTypeAnimal searchByType = new SearchByTypeAnimal();
        UpdateAnimal update = new UpdateAnimal();

        while (running) {
            System.out.println("Введите команду add/list/delete/exit/search_by_type/update.");
            String inputCommand = in.nextLine().trim().toUpperCase();
            Command command = Command.valueOf(inputCommand);
            switch (command) {
                case ADD -> {
                    Animal newAnimal = createAnimal(in);
                    if (newAnimal != null) {
                        add.addAnimal(connection, newAnimal);
                        newAnimal.say();
                        in.nextLine();// Непонятная ошибка, считывается строка, которой нет
                    }
                }
                case LIST -> {
                    for (Animal animal : list.listAnimal(connection)) {
                        System.out.println(animal.toString());
                    }
                }
                case SEARCH_BY_TYPE -> {
                    System.out.println("Введите type животного, список которых желаете вывести cat/dog/duck");
                    AnimalType type = AnimalType.valueOf(in.nextLine().trim().toUpperCase());
                    for (Animal animal : searchByType.searchByTypeAnimal(connection, type)) {
                        System.out.println(animal.toString());
                    }
                }
                case UPDATE -> {
                    System.out.println("Введите id животного, которого желаете изменить.");
                    int id = in.nextInt();
                    Animal newAnimal = createAnimal(in);
                    if (newAnimal != null) {
                        newAnimal.setId(id);
                        update.updateAnimal(connection, newAnimal);
                        in.nextLine();// Непонятная ошибка, считывается строка, которой нет
                    }
                }
                case DELETE -> {
                    System.out.println("Введите id животного, которого желаете удалить.");
                    int id = in.nextInt();
                    delete.deleteAnimal(connection, id);
                    in.nextLine();// Непонятная ошибка, считывается строка, которой нет
                }
                case EXIT -> {
                    in.close();
                    connection.close();
                    running = false;
                }
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
                    return new Cat(name, color, age, weight);
                case "DOG":
                    return new Dog(name, color, age, weight);
                case "DUCK":
                    return new Duck(name, color, age, weight);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
