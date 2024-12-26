package animal.config;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionSql {
    private static Connection connection;
    public static Connection getConnection(Properties properties) throws SQLException {
        if (connection==null) {
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);

            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }
}
