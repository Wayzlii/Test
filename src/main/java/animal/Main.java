package animal;

import animal.commands.*;
import animal.model.*;
import animal.util.AnimalBuilder;
import animal.config.PropertiesHolder;
import animal.config.TableName;
import animal.util.ScannerUtil;
import animal.util.TableCreator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static animal.config.ConnectionSql.getConnection;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {

        Scanner in = new Scanner(System.in);
        boolean running = true;

        PropertiesHolder propertiesHolder = new PropertiesHolder();
        Connection connection = getConnection(propertiesHolder.getProperties());

        TableName.setTableName(propertiesHolder.getProperties());

        AddAnimal add = new AddAnimal();
        DeleteAnimal delete = new DeleteAnimal();
        ListAnimal list = new ListAnimal();
        SearchByTypeAnimal searchByType = new SearchByTypeAnimal();
        UpdateAnimal update = new UpdateAnimal();

        TableCreator.createTable(connection);

        while (running) {
            Commands command = ScannerUtil.getCommand(in);
            switch (command) {
                case ADD -> {
                    Animal newAnimal = null;
                    while(newAnimal == null) {
                        newAnimal = AnimalBuilder.createAnimal(in);
                    }
                    add.addAnimal(connection, newAnimal);
                    newAnimal.say();
                    in.nextLine();// Непонятная ошибка, считывается строка, которой нет
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
                    Animal newAnimal = null;
                    while(newAnimal == null) {
                        newAnimal = AnimalBuilder.createAnimal(in);
                    }
                    newAnimal.setId(id);
                    update.updateAnimal(connection, newAnimal);
                    in.nextLine();// Непонятная ошибка, считывается строка, которой нет
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
}
