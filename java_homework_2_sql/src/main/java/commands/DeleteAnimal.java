package commands;

import config.TableName;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteAnimal {
    private final String commandDelete = "DELETE FROM " + TableName.TableName + " WHERE id = ?";

    public void deleteAnimal(Connection connection, int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(commandDelete);
        statement.setInt(1, id);
        statement.execute();
        statement.close();
    }
}
