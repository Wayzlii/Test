package animal.commands;

import animal.config.TableName;
import animal.model.Animal;
import animal.model.AnimalType;

import java.sql.*;

public class AddAnimal {
    private final String commandAdd = "INSERT INTO " + TableName.tableName +
                                      " (type, name, color, age, weight) VALUES (?,?,?,?,?)";


    public void addAnimal(Connection connection, Animal animal) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(commandAdd)) {
            AnimalType type = animal.getAnimalType();
            statement.setString(1, type.name());
            String name = animal.getName();
            statement.setString(2, name);
            String color = animal.getColor();
            statement.setString(3, color);
            int age = animal.getAge();
            statement.setInt(4, age);
            int weight = animal.getWeight();
            statement.setInt(5, weight);
            statement.execute();
        }
    }
}