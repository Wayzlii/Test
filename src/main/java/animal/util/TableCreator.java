package animal.util;

import animal.config.TableName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableCreator {
    private static String commandCreate = """
            CREATE TABLE IF NOT EXISTS `animals`.`%s` (
              `id` INT NOT NULL AUTO_INCREMENT,
              `type` VARCHAR(45) NOT NULL,
              `name` VARCHAR(45) NOT NULL,
              `color` VARCHAR(45) NOT NULL,
              `age` INT NOT NULL,
              `weight` INT NOT NULL,
              PRIMARY KEY (`id`))
            """;

    public static void createTable (Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(commandCreate.formatted(TableName.tableName))){
            statement.execute();
        }
    }
}
