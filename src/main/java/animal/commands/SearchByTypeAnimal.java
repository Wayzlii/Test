package animal.commands;

import animal.config.TableName;
import animal.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchByTypeAnimal {
    private final String commandSearchByType = "SELECT * FROM " + TableName.tableName + " WHERE type = ?";

    public ArrayList<Animal> searchByTypeAnimal(Connection connection, AnimalType type) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(commandSearchByType)) {
            ArrayList<Animal> animals = new ArrayList<>();
            statement.setString(1, type.name());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                type = AnimalType.valueOf(resultSet.getString(2));
                String name = resultSet.getString(3);
                String color = resultSet.getString(4);
                int age = resultSet.getInt(5);
                int weight = resultSet.getInt(6);
                Animal animal = null;
                switch (type) {
                    case CAT -> animal = new Cat(id, name, color, age, weight);
                    case DOG -> animal = new Dog(id, name, color, age, weight);
                    case DUCK -> animal = new Duck(id, name, color, age, weight);
                }
                animals.add(animal);
            }
            statement.execute();
            return animals;
        }
    }
}
