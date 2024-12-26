package animal.commands;

import animal.config.TableName;
import animal.model.Animal;
import animal.model.AnimalType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateAnimal {
    private final String commandUpdate = "UPDATE " + TableName.tableName +
                                         " SET type = ?, name = ?, color = ?, age = ?, weight = ? WHERE id = ?";

    public void updateAnimal(Connection connection, Animal animal) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(commandUpdate)) {
            statement.setInt(6, animal.getId());
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
